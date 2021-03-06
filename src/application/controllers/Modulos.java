/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.modulos.CtrlModulos;
import application.views.vMenu;
import application.views.vModulos;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.regex.Pattern;
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
public class Modulos {

    private static vModulos vmodulos;
    DefaultTableModel dtm;
    Generic g;
    JasperDesign jd;
    JRDesignQuery jq;
    JasperReport report;
    JasperPrint print;
    JDialog viewer;
    JasperViewer jv;
    vMenu menu;
    CtrlModulos mod;

    private TableRowSorter<TableModel> filtrador;

    public Modulos(Generic g, JFrame parent) {
        this.g = g;
        this.menu = (vMenu) parent;
        vmodulos = new vModulos();
        mod = new CtrlModulos(vmodulos, g, this, menu);

        vmodulos.btnNuevo.addActionListener((e) -> {
            vmodulos.dispose();
            menu.dpContenedor.remove(vmodulos);
            mod.setVisible();
        });
        vmodulos.btnEditar.addActionListener((e) -> {
            try {
                if (vmodulos.tblModulos.getSelectedRow() >= 0) {
                    int ID = Integer.parseInt(vmodulos.tblModulos.getValueAt(vmodulos.tblModulos.getSelectedRow(), 0).toString());
                    vmodulos.dispose();
                    menu.dpContenedor.remove(vmodulos);
                    mod.onEditar(ID);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
                }
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "DEBE DE COLOCAR EL ID AL INICIO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }

        });
        vmodulos.btnEliminar.addActionListener((e) -> {
            if (vmodulos.tblModulos.getSelectedRow() >= 0) {
                int i = JOptionPane.showConfirmDialog(null, "¿Estás Seguro?", "Confirmar Eliminar", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    int ID = Integer.parseInt(vmodulos.tblModulos.getValueAt(vmodulos.tblModulos.getSelectedRow(), 0).toString());
                    mod.onEliminar(ID);
                }

            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }
        });
        vmodulos.btnRefrescar.addActionListener((e) -> {
            getRecords();
        });

        vmodulos.tblModulos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getClickCount()) {
                    case 2:
                        vmodulos.btnEditar.doClick();
                        break;
                }
            }
        });

        vmodulos.txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = vmodulos.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = vmodulos.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                JOptionPane.showMessageDialog(null, "changeUpdate en la tabla Modulos no sooportada");
            }
        });

        vmodulos.cmbTamano.addActionListener((e) -> {
            getRecords();
        });
        TextPrompt placeholder = new TextPrompt("BUSCA POR ID,MODULO", vmodulos.txtBusqueda);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);
       
    }

    public void setVisible() {
        if (vmodulos.isShowing()) {
            //mensaje de que está abierto si se desea
        } else {
            menu.dpContenedor.add(vmodulos);

            Dimension desktopSize = menu.dpContenedor.getSize();
            Dimension jInternalFrameSize = vmodulos.getSize();
            vmodulos.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            vmodulos.setFrameIcon(null);
            vmodulos.show();
        }

    }

    public final void getRecords() {
        try {

            ArrayList<Object> o = new ArrayList<>();
            if (vmodulos.cmbTamano.getSelectedItem().toString().equals("TODOS")) {
                o.add(99999999);
            } else {
                o.add(vmodulos.cmbTamano.getSelectedItem().toString());
            }
            ArrayList<Object[][]> a = g.findByParams("SP_MODULOS", o);
            dtm = g.getModelFill(a.get(0), g.getDimensional(a.get(1)));
            vmodulos.tblModulos.setModel(dtm);
            filtrador = new TableRowSorter<>(dtm);
            vmodulos.tblModulos.setRowSorter(filtrador);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

    /*NO EDITAR ESTA PARTE*/
    public static vModulos getInstance() {
        return vmodulos;
    }
}
