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
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JDialog;
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

    private TableRowSorter<TableModel> filtrador;

    public Combinaciones(Generic g) {
        this.g = g;
        vcombinaciones = new vCombinaciones();
        vcombinaciones.btnNuevo.addActionListener((e) -> {
            (new CtrlCombinaciones(vcombinaciones, g, this)).setVisible();
        });
     
        vcombinaciones.btnEditar.addActionListener((e) -> {
            try {
                if (vcombinaciones.tblCombinaciones.getSelectedRow() >= 0) {
                    int ID = Integer.parseInt(vcombinaciones.tblCombinaciones.getValueAt(vcombinaciones.tblCombinaciones.getSelectedRow(), 0).toString());
                    (new CtrlCombinaciones(vcombinaciones, g, this)).onEditar(ID);
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
                    (new CtrlCombinaciones(vcombinaciones, g, this)).onEliminar(ID);
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
        getRecords();
    }

    public void setVisible() {
        vcombinaciones.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/LS.png")));
        vcombinaciones.setLocationRelativeTo(null);
        vcombinaciones.setVisible(true);
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
