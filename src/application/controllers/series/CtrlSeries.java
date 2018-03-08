/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers.series;

import application.config.Generic;
import application.controllers.Series;
import application.third_party.Resources;
import application.views.series.mdlEditar;
import application.views.series.mdlNuevo;
import application.views.vMenu;
import application.views.vSeries;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Christian
 */
public class CtrlSeries {

    mdlNuevo nuevo;
    mdlEditar editar;
    Generic g;
    Series series;
    vSeries vseries;
    int temp = 0;
    Resources rsc;
    vMenu menu;
    DefaultTableModel dtm;
    int f2 = 113, suprimir = 127, mas = 107, menos = 109;

    public CtrlSeries(JInternalFrame parent, Generic g, Series series, JFrame menu) {
        this.menu = (vMenu) menu;
        nuevo = new mdlNuevo();
        editar = new mdlEditar();
        this.vseries = (vSeries) parent;
        this.g = g;
        this.series = series;
        rsc = new Resources();
        nuevo.tblSerieDetalle.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == f2) {
                    int selected = nuevo.tblSerieDetalle.getSelectedRow();
                    if (selected >= 0) {
                        System.out.println("FILA " + selected);

                    } else {
                        JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO");
                    }
                }
            }
        });
        editar.tblSerieDetalle.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                int selected = editar.tblSerieDetalle.getSelectedRow();
                if (e.getKeyCode() == f2) {
                    if (selected >= 0) {
                        System.out.println("FILA " + selected);
                        editar.tblSerieDetalle.isCellEditable(selected, 1);
                        editar.tblSerieDetalle.editCellAt(selected, 1);
                    } else {
                        JOptionPane.showConfirmDialog(null, "DEBE DE SELECCIONAR UN REGISTRO");
                    }
                }
            }
        });
        nuevo.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Series fra = series;
                fra.setVisible();
            }
        });
        editar.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Series fra = series;
                fra.setVisible();
            }
        });

        nuevo.btnGuardar.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    onGuardar();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        nuevo.btnGuardar.addActionListener((e) -> {
            onGuardar();
        });

        nuevo.txtPuntoInicial.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))
                        && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                        && (e.getKeyChar() != '.' || nuevo.txtPuntoInicial.getText().contains("."))) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        nuevo.txtPuntoFinal.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))
                        && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                        && (e.getKeyChar() != '.' || nuevo.txtPuntoFinal.getText().contains("."))) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        editar.txtPuntoInicial.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))
                        && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                        && (e.getKeyChar() != '.' || editar.txtPuntoInicial.getText().contains("."))) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        editar.txtPuntoFinal.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))
                        && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                        && (e.getKeyChar() != '.' || editar.txtPuntoFinal.getText().contains("."))) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        editar.btnGuardar.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    onModificar();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        editar.btnGuardar.addActionListener((e) -> {
            onModificar();
        });

        editar.txtPuntoInicial.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))
                        && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                        && (e.getKeyChar() != '.' || editar.txtPuntoInicial.getText().contains("."))) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        editar.txtPuntoFinal.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))
                        && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                        && (e.getKeyChar() != '.' || editar.txtPuntoFinal.getText().contains("."))) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

    }

    public void setVisible() {
        if (!nuevo.isShowing()) {

            nuevo.txtPuntoFinal.setText("");
            nuevo.txtPuntoInicial.setText("");
            nuevo.txtDescripcion.setText("");

            menu.dpContenedor.add(nuevo);
            Dimension desktopSize = menu.dpContenedor.getSize();
            Dimension jInternalFrameSize = nuevo.getSize();
            nuevo.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            nuevo.setFrameIcon(null);
            nuevo.show();
            nuevo.toFront();
        }
        nuevo.txtDescripcion.requestFocus();
    }

    public void onGuardar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            ArrayList<Object> detalle = new ArrayList<>();
            int temp_ID = 0;

            Object x = null;
            a.add(nuevo.txtDescripcion.getText());

            a.add(nuevo.txtPuntoInicial.getText().equals("") ? 0.0 : nuevo.txtPuntoInicial.getText());
            a.add(nuevo.txtPuntoFinal.getText().equals("") ? 0.0 : nuevo.txtPuntoFinal.getText());

            Double limite = Double.parseDouble(nuevo.txtPuntoFinal.getText());
            Double total = Double.parseDouble(nuevo.txtPuntoInicial.getText());

            if (Double.parseDouble(nuevo.txtPuntoInicial.getText())
                    < Double.parseDouble(nuevo.txtPuntoFinal.getText())) {

                if (!nuevo.txtDescripcion.getText().equals("")
                        && !nuevo.txtPuntoInicial.getText().equals("")
                        && !nuevo.txtPuntoFinal.getText().equals("")) {
                    //&& g.addUpdateOrDelete("SP_AGREGAR_SERIE", a)
                    ArrayList<Object[][]> data = g.addUpdateOrDeleteAndGetLastId("SP_AGREGAR_SERIE", a);

                    if (data.size() > 0) {
                        Object[][] ID = data.get(0);/*OBTENER EL ID DEL ENCABEZADO*/
                        System.out.println("ID " + String.valueOf(ID[0][0]));

                        temp_ID = Integer.parseInt(String.valueOf(ID[0][0]));
                        /*DETALLE*/

                        while (total <= limite) {
                            detalle.add(temp_ID);
                            detalle.add(total);
                            detalle.add(0);
                            if (g.addUpdateOrDelete("SP_AGREGAR_SERIE_DETALLE", detalle)) {
                                detalle.clear();
                            }
                            total = total + 0.5;
                        }
                        /*FIN DETALLE*/
                    } else {
                        JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AGREGAR EL REGISTRO", "NO SE HA PODIDO AGREGAR EL REGISTRO", JOptionPane.ERROR_MESSAGE);
                    }

                    JOptionPane.showMessageDialog(null, "REGISTRO AGREGADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                    nuevo.dispose();
                    series.getRecords();
                    menu.dpContenedor.remove(nuevo);
                    //series.setVisible();
                    onEditar(temp_ID);
                } else {
                    JOptionPane.showMessageDialog(null, "DEBE DE LLENAR TODOS LOS CAMPOS", "ERROR EN CAPTURA", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "EL PUNTO INICIAL NO DEBE SER MAYOR AL PUNTO FINAL", "ERROR EN CAPTURA", JOptionPane.ERROR_MESSAGE);
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO REGISTRAR EL REGISTRO", "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEditar(int IDX) {
        try {
            editar.txtPuntoFinal.setText("");
            editar.txtPuntoInicial.setText("");
            editar.txtDescripcion.setText("");

            temp = IDX;
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            ArrayList<Object[][]> combinacion = g.findByParams("SP_SERIE_X_ID", a);
            Object[][] data = combinacion.get(0);
            editar.txtDescripcion.setText(String.valueOf((data[0][1] != null) ? data[0][1] : ""));
            editar.txtPuntoInicial.setText(String.valueOf((data[0][2] != null) ? data[0][2] : ""));
            editar.txtPuntoFinal.setText(String.valueOf((data[0][3] != null) ? data[0][3] : ""));
            editar.cmbEstatus.getModel().setSelectedItem(data[0][4]);

            ArrayList<Object> parametrosDetalle = new ArrayList<>();
            parametrosDetalle.add(IDX);
            ArrayList<Object[][]> det = g.findByParams("SP_SERIES_DETALLE", parametrosDetalle);
            dtm = g.getModelFillEditable(det.get(0), g.getDimensional(det.get(1)));
            editar.tblSerieDetalle.setModel(dtm);
            editar.tblSerieDetalle.removeColumn(editar.tblSerieDetalle.getColumnModel().getColumn(0));
            if (!editar.isShowing()) {
                menu.dpContenedor.add(editar);
                Dimension desktopSize = menu.dpContenedor.getSize();
                Dimension jInternalFrameSize = editar.getSize();
                editar.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                        (desktopSize.height - jInternalFrameSize.height) / 2);
                editar.setFrameIcon(null);
                editar.show();
                editar.toFront();
            }
            editar.txtDescripcion.requestFocus();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR EL REGISTRO", "ERROR AL EDITAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onModificar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            Object x = null;
            a.add(temp);
            a.add(editar.txtDescripcion.getText());
            a.add(editar.txtPuntoInicial.getText().equals("") ? 0.0 : editar.txtPuntoInicial.getText());
            a.add(editar.txtPuntoFinal.getText().equals("") ? 0.0 : editar.txtPuntoFinal.getText());
            a.add(editar.cmbEstatus.getSelectedItem().toString());

            if (Double.parseDouble(editar.txtPuntoInicial.getText()) < Double.parseDouble(editar.txtPuntoFinal.getText())) {
                if (!editar.txtDescripcion.getText().equals("") && !editar.txtPuntoInicial.getText().equals("") && !editar.txtPuntoFinal.getText().equals("") && g.addUpdateOrDelete("SP_MODIFICAR_SERIE", a)) {
                    JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                    /*MODIFICAR SERIE DETALLE*/
                    int n = 0;
                    a.clear();
                    for (int i = 0; i < editar.tblSerieDetalle.getRowCount(); i++) {
                        a.add(editar.tblSerieDetalle.getModel().getValueAt(i, 0).toString());
                        a.add(editar.tblSerieDetalle.getValueAt(i, 1).toString());
                        if (g.addUpdateOrDelete("SP_MODIFICAR_SERIE_DETALLE", a)) {
//                            System.out.println("IDSD "+editar.tblSerieDetalle.getModel().getValueAt(i, 0).toString());
                            n++;
                            a.clear();
                        }
                    }
                    JOptionPane.showMessageDialog(null, n + " TALLAS MODIFICADAS");
                    /*FIN MODIFICAR SERIE DETALLE*/
                    editar.dispose();
                    series.getRecords();
                    menu.dpContenedor.remove(editar);
                    series.setVisible();
                } else {
                    JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL REGISTRO", "ERROR AL MODIFICAR EL REGISTRO", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "EL PUNTO INICIAL NO DEBE SER MAYOR AL PUNTO FINAL", "ERROR EN CAPTURA", JOptionPane.ERROR_MESSAGE);
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL REGISTRO", "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEliminar(int IDX) {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            if (g.addUpdateOrDelete("SP_ELIMINAR_SERIE", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                series.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL REGISTRO", "ERROR AL ELIMINAR EL REGISTRO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL REGISTRO", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
        }
    }

}
