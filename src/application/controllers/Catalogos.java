/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.catalogos.CtrlCatalogos;
import application.controllers.usuarios.CtrlUsuarios;
import application.views.vCatalogos;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

/**
 *
 * @author Christian
 */
public class Catalogos {

    private static vCatalogos vcatalogos;
    DefaultTableModel dtm;
    Generic g;
    JDialog viewer;
    private TableRowSorter<TableModel> filtrador;
    String TipoCatalogo;

    public Catalogos(Generic g, String TipoCatalogo) {
        this.g = g;
        this.TipoCatalogo = TipoCatalogo;
        vcatalogos = new vCatalogos();
        vcatalogos.btnNuevo.addActionListener((e) -> {
            (new CtrlCatalogos(vcatalogos, g, this, TipoCatalogo)).setVisible();
        });

        vcatalogos.btnEditar.addActionListener((e) -> {
            try {
                if (vcatalogos.tblCatalogos.getSelectedRow() >= 0) {
                    int ID = Integer.parseInt(vcatalogos.tblCatalogos.getValueAt(vcatalogos.tblCatalogos.getSelectedRow(), 0).toString());
                    (new CtrlCatalogos(vcatalogos, g, this, TipoCatalogo)).onEditar(ID);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
                }
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "DEBE DE COLOCAR EL ID AL INICIO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }

        });
        vcatalogos.btnEliminar.addActionListener((e) -> {
            if (vcatalogos.tblCatalogos.getSelectedRow() >= 0) {
                int ID = Integer.parseInt(vcatalogos.tblCatalogos.getValueAt(vcatalogos.tblCatalogos.getSelectedRow(), 0).toString());
                (new CtrlCatalogos(vcatalogos, g, this, TipoCatalogo)).onEliminar(ID);
            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }
        });
        vcatalogos.btnRefrescar.addActionListener((e) -> {
            getRecords();
        });
        vcatalogos.tblCatalogos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getClickCount()) {
                    case 2:
                        vcatalogos.btnEditar.doClick();
                        break;
                }
            }
        });
        vcatalogos.txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = vcatalogos.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = vcatalogos.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                JOptionPane.showMessageDialog(null, "changeUpdate en la tabla Usuarios no sooportada");
            }
        });

        vcatalogos.cmbTamano.addActionListener((e) -> {
            getRecords();
        });
        TextPrompt placeholder = new TextPrompt("BUSCA NOMBRE Y DESCRIPCIÓN ", vcatalogos.txtBusqueda);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);
        getRecords();

    }

    public void setVisible() {
        vcatalogos.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/LS.png")));
        vcatalogos.setLocationRelativeTo(null);
        vcatalogos.setVisible(true);
    }

    public final void getRecords() {
        try {

            ArrayList<Object> o = new ArrayList<>();
            if (vcatalogos.cmbTamano.getSelectedItem().toString().equals("TODOS")) {
                o.add(99999999);
            } else {
                o.add(vcatalogos.cmbTamano.getSelectedItem().toString());
            }
            o.add(this.TipoCatalogo);
            ArrayList<Object[][]> a = g.findByParams("SP_CATALOGOS", o);
            dtm = g.getModelFill(a.get(0), g.getDimensional(a.get(1)));
            vcatalogos.tblCatalogos.setModel(dtm);
            filtrador = new TableRowSorter<>(dtm);
            vcatalogos.tblCatalogos.setRowSorter(filtrador);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /*NO EDITAR ESTA PARTE*/
    public static vCatalogos getInstance() {
        return vcatalogos;
    }
}
