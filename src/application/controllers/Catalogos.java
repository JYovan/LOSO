/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.catalogos.CtrlCatalogos;
import application.views.vCatalogos;
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
    vMenu mnu;
    CtrlCatalogos cat;

    public String getTipoCatalogo() {
        return TipoCatalogo;
    }

    public void setTipoCatalogo(String TipoCatalogo) {
        this.TipoCatalogo = TipoCatalogo;
    }
    String TipoCatalogo;

    public Catalogos(Generic g, JFrame parent) {
        this.g = g;
        this.mnu = (vMenu) parent;
        cat = new CtrlCatalogos(vcatalogos, g, this, TipoCatalogo, mnu);
        vcatalogos = new vCatalogos();

        vcatalogos.btnNuevo.addActionListener((e) -> {
            //(new CtrlCatalogos(vcatalogos, g, this, TipoCatalogo)).setVisible();
            vcatalogos.dispose();
            mnu.dpContenedor.remove(vcatalogos);

            cat.setVisible();

        });

        vcatalogos.btnEditar.addActionListener((e) -> {
            try {
                if (vcatalogos.tblCatalogos.getSelectedRow() >= 0) {
                    int ID = Integer.parseInt(vcatalogos.tblCatalogos.getModel().getValueAt(vcatalogos.tblCatalogos.getSelectedRow(), 0).toString());
                    vcatalogos.dispose();
                    mnu.dpContenedor.remove(vcatalogos);
                    cat.onEditar(ID);
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

                int i = JOptionPane.showConfirmDialog(null, "¿Estás Seguro?", "Confirmar Eliminar", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    int ID = Integer.parseInt(vcatalogos.tblCatalogos.getValueAt(vcatalogos.tblCatalogos.getSelectedRow(), 0).toString());
                    cat.onEliminar(ID);
                }

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
    

    }

    public void setVisible() {
        if (vcatalogos.isShowing()) {
            //mensaje de que está abierto si se desea
        } else {
            mnu.dpContenedor.add(vcatalogos);

            Dimension desktopSize = mnu.dpContenedor.getSize();
            Dimension jInternalFrameSize = vcatalogos.getSize();
            vcatalogos.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            vcatalogos.setFrameIcon(null);
            vcatalogos.setTitle(TipoCatalogo);
            vcatalogos.show();
        }

    }

    public final void getRecords() {
        try {

            ArrayList<Object> o = new ArrayList<>();
            if (vcatalogos.cmbTamano.getSelectedItem().toString().equals("TODOS")) {
                o.add(99999999);
            } else {
                o.add(vcatalogos.cmbTamano.getSelectedItem().toString());
            }
            o.add(TipoCatalogo);
            ArrayList<Object[][]> a = g.findByParams("SP_CATALOGOS", o);
            dtm = g.getModelFill(a.get(0), g.getDimensional(a.get(1)));
            vcatalogos.tblCatalogos.setModel(dtm);
            filtrador = new TableRowSorter<>(dtm);
            vcatalogos.tblCatalogos.setRowSorter(filtrador);
//            int row = 1;
//            for (int i = 0; i < vcatalogos.tblCatalogos.getRowCount(); i++) {
//                vcatalogos.tblCatalogos.setValueAt(row, i, 1);
//                row++;
//            }
            vcatalogos.tblCatalogos.removeColumn(vcatalogos.tblCatalogos.getColumnModel().getColumn(0));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /*NO EDITAR ESTA PARTE*/
    public static vCatalogos getInstance() {
        return vcatalogos;
    }
}
