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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

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

    public CtrlSeries(JInternalFrame parent, Generic g, Series series, JFrame menu) {
        this.menu = (vMenu) menu;
        nuevo = new mdlNuevo();
        editar = new mdlEditar();
        this.vseries = (vSeries) parent;
        this.g = g;
        this.series = series;
        rsc = new Resources();

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
            Object x = null;
            a.add(nuevo.txtDescripcion.getText());

            a.add(nuevo.txtPuntoInicial.getText().equals("") ? 0.0 : nuevo.txtPuntoInicial.getText());
            a.add(nuevo.txtPuntoFinal.getText().equals("") ? 0.0 : nuevo.txtPuntoFinal.getText());

            if (Double.parseDouble(nuevo.txtPuntoInicial.getText()) < Double.parseDouble(nuevo.txtPuntoFinal.getText())) {
                if (!nuevo.txtDescripcion.getText().equals("") && !nuevo.txtPuntoInicial.getText().equals("") && !nuevo.txtPuntoFinal.getText().equals("") && g.addUpdateOrDelete("SP_AGREGAR_SERIE", a)) {
                    JOptionPane.showMessageDialog(null, "REGISTRO AGREGADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                    nuevo.dispose();
                    series.getRecords();
                    menu.dpContenedor.remove(nuevo);
                    series.setVisible();
                } else {
                    JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AGREGAR EL REGISTRO", "NO SE HA PODIDO AGREGAR EL REGISTRO", JOptionPane.ERROR_MESSAGE);
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
