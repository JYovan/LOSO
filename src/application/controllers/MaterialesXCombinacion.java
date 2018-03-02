/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.materialesxcombinacion.CtrlMaterialesXCombinacion;
import application.views.vMaterialesXCombinacion;
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
public class MaterialesXCombinacion {

    public static vMaterialesXCombinacion vmaterialesxcombinacion;
    DefaultTableModel dtm;
    Generic g;
    JasperDesign jd;
    JRDesignQuery jq;
    JasperReport report;
    JasperPrint print;
    JDialog viewer;
    JasperViewer jv;
    vMenu mnu;
    CtrlMaterialesXCombinacion cu;

    private TableRowSorter<TableModel> filtrador;

    public MaterialesXCombinacion(Generic g, JFrame parent) {
        this.mnu = (vMenu) parent;
        this.g = g;
        cu = new CtrlMaterialesXCombinacion(vmaterialesxcombinacion, g, this, mnu);
        vmaterialesxcombinacion = new vMaterialesXCombinacion();
        vmaterialesxcombinacion.btnNuevo.addActionListener((e) -> {
            cu.setVisible();
        });
        vmaterialesxcombinacion.btnExportar.addActionListener((e) -> { 
        });
        vmaterialesxcombinacion.btnEditar.addActionListener((e) -> {
            try {
                if (vmaterialesxcombinacion.tblMaterialesXCombinacion.getSelectedRow() >= 0) {
                    int ID = Integer.parseInt(vmaterialesxcombinacion.tblMaterialesXCombinacion.getValueAt(vmaterialesxcombinacion.tblMaterialesXCombinacion.getSelectedRow(), 0).toString());
                    cu.onEditar(ID);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
                }
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "DEBE DE COLOCAR EL ID AL INICIO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }

        });
        vmaterialesxcombinacion.btnEliminar.addActionListener((e) -> {
            if (vmaterialesxcombinacion.tblMaterialesXCombinacion.getSelectedRow() >= 0) {

                int i = JOptionPane.showConfirmDialog(null, "¿Estás Seguro?", "Confirmar Eliminar", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    int ID = Integer.parseInt(vmaterialesxcombinacion.tblMaterialesXCombinacion.getValueAt(vmaterialesxcombinacion.tblMaterialesXCombinacion.getSelectedRow(), 0).toString());
                    (new CtrlMaterialesXCombinacion(vmaterialesxcombinacion, g, this, mnu)).onEliminar(ID);
                }

            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }
        });
        vmaterialesxcombinacion.btnRefrescar.addActionListener((e) -> {
            getRecords();
        });
        vmaterialesxcombinacion.tblMaterialesXCombinacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getClickCount()) {
                    case 2:
                        vmaterialesxcombinacion.btnEditar.doClick();
                        break;
                }
            }
        });
        vmaterialesxcombinacion.txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = vmaterialesxcombinacion.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = vmaterialesxcombinacion.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                JOptionPane.showMessageDialog(null, "changeUpdate en la tabla MaterialesXCombinacion no sooportada");
            }
        });

        vmaterialesxcombinacion.cmbTamano.addActionListener((e) -> {
            getRecords();
        });
        TextPrompt placeholder = new TextPrompt("BUSCA POR ID,USUARIO,CORREO,ETC", vmaterialesxcombinacion.txtBusqueda);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);
        getRecords();
    }

    public void setVisible() {
        if (vmaterialesxcombinacion.isShowing()) {
            //mensaje de que está abierto si se desea
        } else {
            mnu.dpContenedor.add(vmaterialesxcombinacion);
            Dimension desktopSize = mnu.dpContenedor.getSize();
            Dimension jInternalFrameSize = vmaterialesxcombinacion.getSize();
            vmaterialesxcombinacion.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            vmaterialesxcombinacion.setFrameIcon(null);
            vmaterialesxcombinacion.show();
        }

    }

    public final void getRecords() {
        try {

            ArrayList<Object> o = new ArrayList<>();
            if (vmaterialesxcombinacion.cmbTamano.getSelectedItem().toString().equals("TODOS")) {
                o.add(99999999);
            } else {
                o.add(vmaterialesxcombinacion.cmbTamano.getSelectedItem().toString());
            }
            ArrayList<Object[][]> a = g.findByParams("SP_MATERIALES_X_COMBINACION", o);
            dtm = g.getModelFill(a.get(0), g.getDimensional(a.get(1)));
            vmaterialesxcombinacion.tblMaterialesXCombinacion.setModel(dtm);
            filtrador = new TableRowSorter<>(dtm);
            vmaterialesxcombinacion.tblMaterialesXCombinacion.setRowSorter(filtrador);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    } 
    /*NO EDITAR ESTA PARTE*/
    public static vMaterialesXCombinacion getInstance() {
        return vmaterialesxcombinacion;
    }
}
