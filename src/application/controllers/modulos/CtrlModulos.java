/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers.modulos;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.Modulos;
import application.views.modulos.mdlIEditar;
import application.views.modulos.mdlINuevo;
import application.views.vModulos;
import application.views.vMenu;
import java.awt.Dimension;
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
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Administrador
 */
public class CtrlModulos {

    mdlINuevo nuevo;
    mdlIEditar editar;
    Generic g;
    Modulos modulos;
    vModulos vmodulos;
    int temp = 0;
    vMenu menu;

    public CtrlModulos(JInternalFrame parent, Generic g, Modulos modulos, JFrame menu) {
        nuevo = new mdlINuevo();
        editar = new mdlIEditar();
        this.vmodulos = (vModulos) parent;
        this.g = g;
        this.modulos = modulos;
        this.menu = (vMenu) menu;
        
        
        nuevo.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Modulos mod = modulos;
                mod.setVisible();
            }
        });
        editar.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Modulos mod = modulos;
                mod.setVisible();
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

     
    }

    public void setVisible() {
        if (!nuevo.isShowing()) {
            
            nuevo.Modulo.setText("");
            
            menu.dpContenedor.add(nuevo);
            Dimension desktopSize = menu.dpContenedor.getSize();
            Dimension jInternalFrameSize = nuevo.getSize();
            nuevo.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            nuevo.setFrameIcon(null);
            nuevo.show();
            nuevo.toFront();
        }
        nuevo.Modulo.requestFocus();
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
                menu.dpContenedor.remove(nuevo);
                modulos.setVisible();
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
                editar.Modulo.requestFocus();
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
                menu.dpContenedor.remove(editar);
                modulos.setVisible();
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
