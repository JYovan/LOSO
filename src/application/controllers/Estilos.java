/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.estilos.CtrlEstilos;
import application.views.vEstilos;
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
 * @author Administrador
 */
public class Estilos {

    private static vEstilos vestilos;
    DefaultTableModel dtm;
    Generic g;
    JasperDesign jd;
    JRDesignQuery jq;
    JasperReport report;
    JasperPrint print;
    JDialog viewer;
    JasperViewer jv;
    vMenu menu;
    CtrlEstilos est;

    private TableRowSorter<TableModel> filtrador;

    public Estilos(Generic g, JFrame parent) {
        this.g = g;
        this.menu =  (vMenu)parent;
        vestilos = new vEstilos();
        est = new CtrlEstilos(vestilos, g, this,menu);
        
        vestilos.btnNuevo.addActionListener((e) -> {
            est.setVisible();
        });
        vestilos.btnEditar.addActionListener((e) -> {
            try {
                if (vestilos.tblEstilos.getSelectedRow() >= 0) {
                    int ID = Integer.parseInt(vestilos.tblEstilos.getValueAt(vestilos.tblEstilos.getSelectedRow(), 0).toString());
                    est.onEditar(ID);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
                }
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "DEBE DE COLOCAR EL ID AL INICIO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }

        });
        vestilos.btnEliminar.addActionListener((e) -> {
            if (vestilos.tblEstilos.getSelectedRow() >= 0) {
                int i = JOptionPane.showConfirmDialog(null, "¿Estás Seguro?", "Confirmar Eliminar", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    int ID = Integer.parseInt(vestilos.tblEstilos.getValueAt(vestilos.tblEstilos.getSelectedRow(), 0).toString());
                    est.onEliminar(ID);
                }

            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }
        });
        vestilos.btnRefrescar.addActionListener((e) -> {
            getRecords();
        });

        vestilos.tblEstilos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getClickCount()) {
                    case 2:
                        vestilos.btnEditar.doClick();
                        break;
                }
            }
        });

        vestilos.txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = vestilos.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = vestilos.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                JOptionPane.showMessageDialog(null, "changeUpdate en la tabla Estilos no sooportada");
            }
        });

        vestilos.cmbTamano.addActionListener((e) -> {
            getRecords();
        });
        TextPrompt placeholder = new TextPrompt("BUSCAR...", vestilos.txtBusqueda);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);
        getRecords();
    }

    public void setVisible() {
        if (vestilos.isShowing()) {
            //mensaje de que está abierto si se desea
        } else {
            menu.dpContenedor.add(vestilos);

            Dimension desktopSize = menu.dpContenedor.getSize();
            Dimension jInternalFrameSize = vestilos.getSize();
            vestilos.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            vestilos.setFrameIcon(null);
            vestilos.show();
        }
    }

    public final void getRecords() {
        try {

            ArrayList<Object> o = new ArrayList<>();
            if (vestilos.cmbTamano.getSelectedItem().toString().equals("TODOS")) {
                o.add(99999999);
            } else {
                o.add(vestilos.cmbTamano.getSelectedItem().toString());
            }
            ArrayList<Object[][]> a = g.findByParams("SP_ESTILOS", o);
            dtm = g.getModelFill(a.get(0), g.getDimensional(a.get(1)));
            vestilos.tblEstilos.setModel(dtm);
            filtrador = new TableRowSorter<>(dtm);
            vestilos.tblEstilos.setRowSorter(filtrador);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /*NO EDITAR ESTA PARTE*/
    public static vEstilos getInstance() {
        return vestilos;
    }
}
