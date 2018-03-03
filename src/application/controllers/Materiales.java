/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.materiales.CtrlMateriales;
import application.views.vMateriales; 
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
public class Materiales {

    private static vMateriales vmateriales;
    DefaultTableModel dtm;
    Generic g;
    JasperDesign jd;
    JRDesignQuery jq;
    JasperReport report;
    JasperPrint print;
    JDialog viewer;
    JasperViewer jv;
    CtrlMateriales mat;
    vMenu menu;

    private TableRowSorter<TableModel> filtrador;

    public Materiales(Generic g,JFrame parent) {
        this.menu =  (vMenu)parent;
        this.g = g;
        vmateriales = new vMateriales();
        mat =  new CtrlMateriales(vmateriales, g, this,menu);
        vmateriales.btnNuevo.addActionListener((e) -> {
            mat.setVisible();
        });
        vmateriales.btnEditar.addActionListener((e) -> {
            try {
                if (vmateriales.tblMateriales.getSelectedRow() >= 0) {
                    int ID = Integer.parseInt(vmateriales.tblMateriales.getValueAt(vmateriales.tblMateriales.getSelectedRow(), 0).toString());
                    mat.onEditar(ID);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
                }
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "DEBE DE COLOCAR EL ID AL INICIO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }

        });
        vmateriales.btnEliminar.addActionListener((e) -> {
            if (vmateriales.tblMateriales.getSelectedRow() >= 0) {
                int i = JOptionPane.showConfirmDialog(null, "¿Estás Seguro?", "Confirmar Eliminar", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    int ID = Integer.parseInt(vmateriales.tblMateriales.getValueAt(vmateriales.tblMateriales.getSelectedRow(), 0).toString());
                    mat.onEliminar(ID);
                }

            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }
        });
        vmateriales.btnRefrescar.addActionListener((e) -> {
            getRecords();
        });

        vmateriales.tblMateriales.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getClickCount()) {
                    case 2:
                        vmateriales.btnEditar.doClick();
                        break;
                }
            }
        });

        vmateriales.txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = vmateriales.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = vmateriales.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                JOptionPane.showMessageDialog(null, "changeUpdate en la tabla Materiales no sooportada");
            }
        });

        vmateriales.cmbTamano.addActionListener((e) -> {
            getRecords();
        });
        TextPrompt placeholder = new TextPrompt("BUSCAR...", vmateriales.txtBusqueda);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);
        getRecords();
    }

    public void setVisible() {
         if (vmateriales.isShowing()) {
            //mensaje de que está abierto si se desea
        } else {
            menu.dpContenedor.add(vmateriales);

            Dimension desktopSize = menu.dpContenedor.getSize();
            Dimension jInternalFrameSize = vmateriales.getSize();
            vmateriales.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            vmateriales.setFrameIcon(null);
            vmateriales.show();
        }
    }

    public final void getRecords() {
        try {

            ArrayList<Object> o = new ArrayList<>();
            if (vmateriales.cmbTamano.getSelectedItem().toString().equals("TODOS")) {
                o.add(99999999);
            } else {
                o.add(vmateriales.cmbTamano.getSelectedItem().toString());
            }
            ArrayList<Object[][]> a = g.findByParams("SP_MATERIALES", o);
            dtm = g.getModelFill(a.get(0), g.getDimensional(a.get(1)));
            vmateriales.tblMateriales.setModel(dtm);
            filtrador = new TableRowSorter<>(dtm);
            vmateriales.tblMateriales.setRowSorter(filtrador);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /*NO EDITAR ESTA PARTE*/
    public static vMateriales getInstance() {
        return vmateriales;
    }
}

