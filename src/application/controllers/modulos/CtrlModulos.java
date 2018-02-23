/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers.modulos;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.Modulos;
import application.views.modulos.mdlEditar;
import application.views.modulos.mdlNuevo;
import application.views.vModulos;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class CtrlModulos {

    mdlNuevo nuevo;
    mdlEditar editar;
    Generic g;
    Modulos modulos;
    vModulos vmodulos;
    int temp = 0;

    public CtrlModulos(JFrame parent, Generic g, Modulos modulos) {
        nuevo = new mdlNuevo(parent, true);
        editar = new mdlEditar(parent, true);
        this.vmodulos = (vModulos) parent;
        this.g = g;
        this.modulos = modulos;

        nuevo.btnGuardar.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    onGuardar();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    nuevo.dispose();
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

        editar.btnGuardar.addActionListener((e) -> {
            onModificar();
        });

        nuevo.Modulo.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    onGuardar();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    nuevo.dispose();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        editar.Modulo.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    onModificar();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    editar.dispose();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        /*PLACEHOLDERS*/
//        TextPrompt placeholders = new TextPrompt("NOMBRE DEL MODULO", nuevo.Modulo);
//        placeholders.changeAlpha(0.75f);
//        placeholders.changeStyle(Font.BOLD);
    }

    public void setVisible() {
        nuevo.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
        nuevo.setLocationRelativeTo(null);
        nuevo.setVisible(true);
    }

    public void onGuardar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(nuevo.Modulo.getText());
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            a.add(dateFormat.format(date));
            if (!nuevo.Modulo.getText().equals("") && g.addUpdateOrDelete("SP_AGREGAR_MODULO", a)) {
                JOptionPane.showMessageDialog(null, "MODULO AGREGADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                nuevo.dispose();
                modulos.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AGREGAR EL MODULO", "NO SE HA PODIDO AGREGAR EL MODULO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO REGISTRAR EL MODULO", "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEditar(int IDX) {
        try {
            temp = IDX;
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            ArrayList<Object[][]> usuario = g.findByParams("SP_MODULO_X_ID", a);
            Object[][] data = usuario.get(0);
            if (data != null) {
                editar.Modulo.setText(String.valueOf((data[0][1] != null) ? data[0][1] : ""));
                editar.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
                editar.setLocationRelativeTo(null);
                editar.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR EL MODULO", "ERROR AL EDITAR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR EL MODULO", "ERROR AL EDITAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onModificar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(temp);
            a.add(editar.Modulo.getText());
            if (!editar.Modulo.getText().equals("") && g.addUpdateOrDelete("SP_MODIFICAR_MODULO", a)) {
                JOptionPane.showMessageDialog(null, "MODULO MODIFICADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                editar.dispose();
                modulos.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL MODULO", "ERROR AL MODIFICAR EL MODULO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL MODULO", "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEliminar(int IDX) {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            if (g.addUpdateOrDelete("SP_ELIMINAR_MODULO", a)) {
                JOptionPane.showMessageDialog(null, "MODULO ELIMINADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                modulos.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL MODULO", "ERROR AL ELIMINAR EL MODULO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL MODULO", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
