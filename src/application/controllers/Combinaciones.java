/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.combinaciones.CtrlCombinaciones;
import application.views.vCombinaciones;
import application.views.vMenu;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Christian
 */
public class Combinaciones {

    private static vCombinaciones vcombinaciones;
    DefaultTableModel dtm;
    Generic g;
    JasperDesign jd;
    JRDesignQuery jq;
    JasperReport report;
    JasperPrint print;
    JDialog viewer;
    JasperViewer jv;
    vMenu mnu;
    CtrlCombinaciones comb;
    
    private TableRowSorter<TableModel> filtrador;

    public Combinaciones(Generic g, JFrame parent ) {
        this.g = g;
        vcombinaciones = new vCombinaciones();
        this.mnu = (vMenu) parent;
        comb = new CtrlCombinaciones(vcombinaciones, g, this, mnu);
        vcombinaciones.btnNuevo.addActionListener((e) -> {
            vcombinaciones.dispose();
            mnu.dpContenedor.remove(vcombinaciones);
            comb.setVisible();
        });
     
        vcombinaciones.btnEditar.addActionListener((e) -> {
            try {
                if (vcombinaciones.tblCombinaciones.getSelectedRow() >= 0) {
                    int ID = Integer.parseInt(vcombinaciones.tblCombinaciones.getValueAt(vcombinaciones.tblCombinaciones.getSelectedRow(), 0).toString());
                    vcombinaciones.dispose();
                    mnu.dpContenedor.remove(vcombinaciones);
                    comb.onEditar(ID);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
                }
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "DEBE DE COLOCAR EL ID AL INICIO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }

        });
        vcombinaciones.btnEliminar.addActionListener((e) -> {
            if (vcombinaciones.tblCombinaciones.getSelectedRow() >= 0) {

                int i = JOptionPane.showConfirmDialog(null, "¿Estás Seguro?", "Confirmar Eliminar", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    int ID = Integer.parseInt(vcombinaciones.tblCombinaciones.getValueAt(vcombinaciones.tblCombinaciones.getSelectedRow(), 0).toString());
                    comb.onEliminar(ID);
                }

            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }
        });
        vcombinaciones.btnRefrescar.addActionListener((e) -> {
            getRecords();
        });
        vcombinaciones.tblCombinaciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getClickCount()) {
                    case 2:
                        vcombinaciones.btnEditar.doClick();
                        break;
                }
            }
        });
        vcombinaciones.txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = vcombinaciones.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = vcombinaciones.txtBusqueda.getText();
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

        vcombinaciones.cmbTamano.addActionListener((e) -> {
            getRecords();
        });
        TextPrompt placeholder = new TextPrompt("BUSCA CLAVE, DESCRIPCIÓN", vcombinaciones.txtBusqueda);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);
        
    }

    public void setVisible() {
        
         if (vcombinaciones.isShowing()) {
            //mensaje de que está abierto si se desea
        } else {
            mnu.dpContenedor.add(vcombinaciones);

            Dimension desktopSize = mnu.dpContenedor.getSize();
            Dimension jInternalFrameSize = vcombinaciones.getSize();
            vcombinaciones.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            vcombinaciones.setFrameIcon(null);
            vcombinaciones.show();
        }

    }

    public final void getRecords() {
        try {

            ArrayList<Object> o = new ArrayList<>();
            if (vcombinaciones.cmbTamano.getSelectedItem().toString().equals("TODOS")) {
                o.add(99999999);
            } else {
                o.add(vcombinaciones.cmbTamano.getSelectedItem().toString());
            }
            ArrayList<Object[][]> a = g.findByParams("SP_COMBINACIONES", o);
            dtm = g.getModelFill(a.get(0), g.getDimensional(a.get(1)));
            vcombinaciones.tblCombinaciones.setModel(dtm);
            filtrador = new TableRowSorter<>(dtm);
            vcombinaciones.tblCombinaciones.setRowSorter(filtrador);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static vCombinaciones getInstance() {
        return vcombinaciones;
    }

}
