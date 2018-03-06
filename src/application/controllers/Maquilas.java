/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.maquilas.CtrlMaquilas;
import application.views.vMaquilas;
import application.views.vMenu;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Christian
 */
public class Maquilas {

    private static vMaquilas vmaquilas;
    DefaultTableModel dtm;
    Generic g;
    JasperDesign jd;
    JRDesignQuery jq;
    JasperReport report;
    JasperPrint print;
    JDialog viewer;
    JasperViewer jv;
    vMenu menu;
    CtrlMaquilas maq;

    private TableRowSorter<TableModel> filtrador;

    public Maquilas(Generic g, JFrame parent) {
        this.g = g;
        this.menu = (vMenu) parent;
        maq = new CtrlMaquilas(vmaquilas, g, this, menu);
        vmaquilas = new vMaquilas();
        vmaquilas.btnNuevo.addActionListener((e) -> {
            vmaquilas.dispose();
            menu.dpContenedor.remove(vmaquilas);
            maq.setVisible();
        });
        vmaquilas.btnExportar.addActionListener((e) -> {

        });
        vmaquilas.btnEditar.addActionListener((e) -> {
            try {
                if (vmaquilas.tblMaquilas.getSelectedRow() >= 0) {
                    int ID = Integer.parseInt(vmaquilas.tblMaquilas.getValueAt(vmaquilas.tblMaquilas.getSelectedRow(), 0).toString());
                    vmaquilas.dispose();
                    menu.dpContenedor.remove(vmaquilas);
                    maq.onEditar(ID);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
                }
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "DEBE DE COLOCAR EL ID AL INICIO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }

        });
        vmaquilas.btnEliminar.addActionListener((e) -> {
            if (vmaquilas.tblMaquilas.getSelectedRow() >= 0) {

                int i = JOptionPane.showConfirmDialog(null, "¿Estás Seguro?", "Confirmar Eliminar", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    int ID = Integer.parseInt(vmaquilas.tblMaquilas.getValueAt(vmaquilas.tblMaquilas.getSelectedRow(), 0).toString());
                    maq.onEliminar(ID);
                }

            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }
        });
        vmaquilas.btnRefrescar.addActionListener((e) -> {
            getRecords();
        });
        vmaquilas.tblMaquilas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getClickCount()) {
                    case 2:
                        vmaquilas.btnEditar.doClick();
                        break;
                }
            }
        });
        vmaquilas.txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = vmaquilas.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = vmaquilas.txtBusqueda.getText();
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

        vmaquilas.cmbTamano.addActionListener((e) -> {
            getRecords();
        });
        TextPrompt placeholder = new TextPrompt("BUSCA CLAVE, NOMBRE", vmaquilas.txtBusqueda);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);
       
    }

    public void setVisible() {
        if (vmaquilas.isShowing()) {
            //mensaje de que está abierto si se desea
        } else {
            menu.dpContenedor.add(vmaquilas);

            Dimension desktopSize = menu.dpContenedor.getSize();
            Dimension jInternalFrameSize = vmaquilas.getSize();
            vmaquilas.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            vmaquilas.setFrameIcon(null);
            vmaquilas.show();
        }
    }

    public final void getRecords() {
        try {
            System.out.println("se ejecuta");
            ArrayList<Object> o = new ArrayList<>();
            if (vmaquilas.cmbTamano.getSelectedItem().toString().equals("TODOS")) {
                o.add(99999999);
            } else {
                o.add(vmaquilas.cmbTamano.getSelectedItem().toString());
            }
            ArrayList<Object[][]> a = g.findByParams("SP_MAQUILAS", o);
            dtm = g.getModelFill(a.get(0), g.getDimensional(a.get(1)));
            vmaquilas.tblMaquilas.setModel(dtm);
            filtrador = new TableRowSorter<>(dtm);
            vmaquilas.tblMaquilas.setRowSorter(filtrador);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

//    public void getReporteUsuarios() {
//        try {
//            viewer = new JDialog(vmaquilas, "Usuarios - Reporte de Listado de Usuarios", true);
//            report = JasperCompileManager.compileReport("jrxml/Usuarios.jrxml");
//            print = JasperFillManager.fillReport(report, null, g.getCurrentConnection());
//            jv = new JasperViewer(print, false);
//            viewer.getContentPane().add(jv.getContentPane());
//            jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
//            viewer.setSize(jv.getSize());
//            viewer.setLocationRelativeTo(null);
//            viewer.setVisible(true);
//        } catch (JRException e) {
//            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO GENERAR EL REPORTE DE USUARIOS\n" + e.getMessage(), "ERROR AL GENERAR", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//    public void getReporteUsuariosSQL() {
//        try {
//            viewer = new JDialog(vmaquilas, "Usuarios - Reporte de Listado de Usuarios", true);
//            report = JasperCompileManager.compileReport("jrxml/Usuarios.jrxml");
//            String sql = "SELECT v.vm_id Id, v.vm_folio Folio, v.vm_ncuenta NoCuenta, v.vm_femision Emitido, v.vm_nrecibo NoRecibo, v.vm_fentrega Entrega, v.vm_ingreso Ingreso,dp.dp_nombre Dependencia,lt.lugt_nombre LugarTrabajo, v.vm_estatus Estatus, v.vm_registro Registro\n"
//                    + "FROM vale_m v INNER JOIN lugar_trabajo lt INNER JOIN dependencias dp\n"
//                    + "ON v.fk_lugt_vm = lt.lugt_id AND lt.fk_dp_lugt = dp.dp_id ORDER BY vm_id desc limit 1";
//            JRDesignQuery newQuery = new JRDesignQuery();
//            newQuery.setText(sql);
//            jd.setQuery(newQuery);
//            JasperReport jr = JasperCompileManager.compileReport(jd);
//            JasperPrint jp = JasperFillManager.fillReport(jr, null, g.getCurrentConnection());
//            jv = new JasperViewer(jp, false);
//            viewer.getContentPane().add(jv.getContentPane());
//            jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
//            viewer.setSize(jv.getSize());
//            viewer.setLocationRelativeTo(null);
//            viewer.setVisible(true);
//        } catch (JRException e) {
//            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO GENERAR EL REPORTE DE USUARIOS\n" + e.getMessage(), "ERROR AL GENERAR", JOptionPane.ERROR_MESSAGE);
//        }
//    }
    public static boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

    /*NO EDITAR ESTA PARTE*/
    public static vMaquilas getInstance() {
        return vmaquilas;
    }

}
