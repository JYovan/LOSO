/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.usuarios.CtrlUsuarios;
import application.views.vUsuarios;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class Usuarios {

    vUsuarios vusuarios;
    DefaultTableModel dtm;

    Generic g;
    public Usuarios(Generic g) {
        this.g = g;
        vusuarios = new vUsuarios();
        vusuarios.btnNuevo.addActionListener((e) -> {
            (new CtrlUsuarios(vusuarios, g, this)).setVisible();
        });
        vusuarios.btnEditar.addActionListener((e) -> {
            try {
                if (vusuarios.tblUsuarios.getSelectedRow() >= 0) {
                    int ID = Integer.parseInt(vusuarios.tblUsuarios.getValueAt(vusuarios.tblUsuarios.getSelectedRow(), 0).toString());
                    (new CtrlUsuarios(vusuarios, g, this)).onEditar(ID);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
                }
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "DEBE DE COLOCAR EL ID AL INICIO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }

        });
        vusuarios.btnEliminar.addActionListener((e) -> {
            if (vusuarios.tblUsuarios.getSelectedRow() >= 0) {
                int ID = Integer.parseInt(vusuarios.tblUsuarios.getValueAt(vusuarios.tblUsuarios.getSelectedRow(), 0).toString());
                (new CtrlUsuarios(vusuarios, g, this)).onEliminar(ID);
            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }
        });
        vusuarios.btnRefrescar.addActionListener((e) -> {
            getRecords();
        });
        vusuarios.tblUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getClickCount()) {
                    case 2:
                        System.out.println("UN DOBLE CLICK");
                        vusuarios.btnEditar.doClick();
                        break;
                }
            }
        });

        vusuarios.txtBusqueda.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                ArrayList<Object> o = new ArrayList<>();
                String string = vusuarios.txtBusqueda.getText();
                String[] campos = string.split(",");
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (vusuarios.cmbTamano.getSelectedItem().toString().equals("TODOS")) {
                        o.add(99999999);
                    } else {
                        o.add(vusuarios.cmbTamano.getSelectedItem().toString());
                    }
                    for (int i = 0; i < campos.length; i++) {
                        if (campos.length > 0 && !campos[i].equals("")) {
                            o.add(campos[i]);
                        } else {
                            o.add("");
                        }
                    }
                    if (campos.length < 3) {
                        o.add("");
                    }
                    ArrayList<Object[][]> a = g.findByParams("SP_BUSCAR_USUARIO", o);
                    dtm = g.getModelFill(a.get(0), g.getDimensional(a.get(1)));
                    vusuarios.tblUsuarios.setModel(dtm);
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        vusuarios.btnBuscar.addActionListener((e) -> {
            ArrayList<Object> o = new ArrayList<>();
            String string = vusuarios.txtBusqueda.getText();
            String[] campos = string.split(",");

            if (vusuarios.cmbTamano.getSelectedItem().toString().equals("TODOS")) {
                o.add(99999999);
            } else {
                o.add(vusuarios.cmbTamano.getSelectedItem().toString());
            }
            for (int i = 0; i < campos.length; i++) {
                if (campos.length > 0 && !campos[i].equals("")) {
                    o.add(campos[i]);
                } else {
                    o.add("");
                }
            }
            if (campos.length < 3) {
                o.add("");
            }
            ArrayList<Object[][]> a = g.findByParams("SP_BUSCAR_USUARIO", o);
            dtm = g.getModelFill(a.get(0), g.getDimensional(a.get(1)));
            vusuarios.tblUsuarios.setModel(dtm);
            vusuarios.tblUsuarios.setColumnControlVisible(true);
        });

        vusuarios.cmbTamano.addActionListener((e) -> {
            getRecords();
        });
        TextPrompt placeholder = new TextPrompt("USUARIO,CORREO", vusuarios.txtBusqueda);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);
        getRecords();
    }

    public void setVisible() {
        vusuarios.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
        vusuarios.setLocationRelativeTo(null);
        vusuarios.setVisible(true);
    }

    public final void getRecords() {
        try {

            ArrayList<Object> o = new ArrayList<>();
            if (vusuarios.cmbTamano.getSelectedItem().toString().equals("TODOS")) {
                o.add(99999999);
            } else {
                o.add(vusuarios.cmbTamano.getSelectedItem().toString());
            }
            ArrayList<Object[][]> a = g.findByParams("SP_USUARIOS", o);
            dtm = g.getModelFill(a.get(0), g.getDimensional(a.get(1)));
            vusuarios.tblUsuarios.setModel(dtm);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
        }
