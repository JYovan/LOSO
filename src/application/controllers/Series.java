/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.series.CtrlSeries;
import application.views.vMenu;
import application.views.vSeries;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Christian
 */
public class Series {

    private static vSeries vseries;
    DefaultTableModel dtm;
    Generic g;

    vMenu menu;
    CtrlSeries ser;

    private TableRowSorter<TableModel> filtrador;
    
    
    public Series(Generic g, JFrame parent) {
        this.g = g;
        vseries = new vSeries();
        this.menu = (vMenu) parent;
        ser = new CtrlSeries(vseries, g, this, menu);
        vseries.btnNuevo.addActionListener((e) -> {
            vseries.dispose();
            menu.dpContenedor.remove(vseries);
            ser.setVisible();
        });
      
        vseries.btnEditar.addActionListener((e) -> {
            try {
                if (vseries.tblSeries.getSelectedRow() >= 0) {
                    int ID = Integer.parseInt(vseries.tblSeries.getValueAt(vseries.tblSeries.getSelectedRow(), 0).toString());
                    vseries.dispose();
                    menu.dpContenedor.remove(vseries);
                    ser.onEditar(ID);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
                }
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "DEBE DE COLOCAR EL ID AL INICIO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }

        });
        vseries.btnEliminar.addActionListener((e) -> {
            if (vseries.tblSeries.getSelectedRow() >= 0) {

                int i = JOptionPane.showConfirmDialog(null, "¿Estás Seguro?", "Confirmar Eliminar", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    int ID = Integer.parseInt(vseries.tblSeries.getValueAt(vseries.tblSeries.getSelectedRow(), 0).toString());
                    ser.onEliminar(ID);
                }

            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }
        });
        vseries.btnRefrescar.addActionListener((e) -> {
            getRecords();
        });
        vseries.tblSeries.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getClickCount()) {
                    case 2:
                        vseries.btnEditar.doClick();
                        break;
                }
            }
        });
        vseries.txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = vseries.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = vseries.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                JOptionPane.showMessageDialog(null, "changeUpdate en la tabla no sooportada");
            }
        });

        vseries.cmbTamano.addActionListener((e) -> {
            getRecords();
        });
        TextPrompt placeholder = new TextPrompt("BUSCA LINEA, DESCRIPCIÓN", vseries.txtBusqueda);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);
        
    }
    
    
      public void setVisible() {
        if (vseries.isShowing()) {
            //mensaje de que está abierto si se desea
        } else {
            menu.dpContenedor.add(vseries);

            Dimension desktopSize = menu.dpContenedor.getSize();
            Dimension jInternalFrameSize = vseries.getSize();
            vseries.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            vseries.setFrameIcon(null);
            vseries.show();
        }

    }

    public final void getRecords() {
        try {

            ArrayList<Object> o = new ArrayList<>();
            if (vseries.cmbTamano.getSelectedItem().toString().equals("TODOS")) {
                o.add(99999999);
            } else {
                o.add(vseries.cmbTamano.getSelectedItem().toString());
            }
            ArrayList<Object[][]> a = g.findByParams("SP_SERIES", o);
            dtm = g.getModelFill(a.get(0), g.getDimensional(a.get(1)));
            vseries.tblSeries.setModel(dtm);
            filtrador = new TableRowSorter<>(dtm);
            vseries.tblSeries.setRowSorter(filtrador);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

 

    /*NO EDITAR ESTA PARTE*/
    public static vSeries getInstance() {
        return vseries;
    }

}
