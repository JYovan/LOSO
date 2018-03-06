/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.permisos.CtrlPermisos;
import application.views.vMenu;
import application.views.vPermisos;
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
public class Permisos {

    private static vPermisos vpermisos;
    DefaultTableModel dtm;
    Generic g;
    JasperDesign jd;
    JRDesignQuery jq;
    JasperReport report;
    JasperPrint print;
    JDialog viewer;
    JasperViewer jv;
    vMenu menu;
    CtrlPermisos per;

    private TableRowSorter<TableModel> filtrador;

    public Permisos(Generic g, JFrame parent) {
        this.g = g;
        this.menu = (vMenu) parent;
        per = new CtrlPermisos(vpermisos, g, this, menu);
        vpermisos = new vPermisos();
        vpermisos.btnNuevo.addActionListener((e) -> {
            vpermisos.dispose();
            menu.dpContenedor.remove(vpermisos);
            per.setVisible();
        });
        vpermisos.btnEditar.addActionListener((e) -> {
            try {
                if (vpermisos.tblPermisos.getSelectedRow() >= 0) {
                    int ID = Integer.parseInt(vpermisos.tblPermisos.getValueAt(vpermisos.tblPermisos.getSelectedRow(), 0).toString());
                    vpermisos.dispose();
                    menu.dpContenedor.remove(vpermisos);
                    per.onEditar(ID);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
                }
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "DEBE DE COLOCAR EL ID AL INICIO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }

        });
        vpermisos.btnEliminar.addActionListener((e) -> {
            if (vpermisos.tblPermisos.getSelectedRow() >= 0) {
                int i = JOptionPane.showConfirmDialog(null, "¿Estás Seguro?", "Confirmar Eliminar", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    int ID = Integer.parseInt(vpermisos.tblPermisos.getValueAt(vpermisos.tblPermisos.getSelectedRow(), 0).toString());
                    per.onEliminar(ID);
                }

            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }
        });
        vpermisos.btnRefrescar.addActionListener((e) -> {
            getRecords();
        });

        vpermisos.tblPermisos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getClickCount()) {
                    case 2:
                        vpermisos.btnEditar.doClick();
                        break;
                }
            }
        });

        vpermisos.txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = vpermisos.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = vpermisos.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                JOptionPane.showMessageDialog(null, "changeUpdate en la tabla Permisos no sooportada");
            }
        });

        vpermisos.cmbTamano.addActionListener((e) -> {
            getRecords();
        });
        TextPrompt placeholder = new TextPrompt("BUSCA POR ID,MODULO,USUARIO", vpermisos.txtBusqueda);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);
       
    }

    public void setVisible() {
        if (vpermisos.isShowing()) {
            //mensaje de que está abierto si se desea
        } else {
            menu.dpContenedor.add(vpermisos);

            Dimension desktopSize = menu.dpContenedor.getSize();
            Dimension jInternalFrameSize = vpermisos.getSize();
            vpermisos.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            vpermisos.setFrameIcon(null);
            vpermisos.show();
        }
    }

    public final void getRecords() {
        try {

            ArrayList<Object> o = new ArrayList<>();
            if (vpermisos.cmbTamano.getSelectedItem().toString().equals("TODOS")) {
                o.add(99999999);
            } else {
                o.add(vpermisos.cmbTamano.getSelectedItem().toString());
            }
            ArrayList<Object[][]> a = g.findByParams("SP_PERMISOS", o);
            dtm = g.getModelFill(a.get(0), g.getDimensional(a.get(1)));
            vpermisos.tblPermisos.setModel(dtm);
            filtrador = new TableRowSorter<>(dtm);
            vpermisos.tblPermisos.setRowSorter(filtrador);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /*NO EDITAR ESTA PARTE*/
    public static vPermisos getInstance() {
        return vpermisos;
    }
}
