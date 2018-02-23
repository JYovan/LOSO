/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers.usuarios;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.Usuarios;
import application.views.usuarios.mdlEditar;
import application.views.usuarios.mdlNuevo;
import application.views.vUsuarios;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class CtrlUsuarios {

    mdlNuevo nuevo;
    mdlEditar editar;
    Generic g;
    Usuarios usuarios;
    vUsuarios vusuarios;
    int temp = 0;

    public CtrlUsuarios(JFrame parent, Generic g, Usuarios usuarios) {
        nuevo = new mdlNuevo(parent, true);
        editar = new mdlEditar(parent, true);
        this.vusuarios = (vUsuarios) parent;
        this.g = g;
        this.usuarios = usuarios;
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
        nuevo.Foto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("SELECCIONE UNA IMAGEN");
                int file = fc.showOpenDialog(null);
                if (file == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    nuevo.Foto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(selectedFile.getAbsolutePath()).getScaledInstance(350, 350, Image.SCALE_SMOOTH)));
                }
            }

        });
        nuevo.Usuario.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
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
        nuevo.Correo.addKeyListener(new KeyListener() {
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
        editar.Usuario.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
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
        editar.Correo.addKeyListener(new KeyListener() {
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
        editar.Foto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("SELECCIONE UNA IMAGEN");
                int file = fc.showOpenDialog(null);
                if (file == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    editar.Foto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(selectedFile.getAbsolutePath()).getScaledInstance(350, 350, Image.SCALE_SMOOTH)));
                }
            }

        });
        /*PLACEHOLDERS*/
//        TextPrompt placeholders = new TextPrompt("NOMBRE DE USUARIO", nuevo.Usuario);
//        placeholders.changeAlpha(0.75f);
//        placeholders.changeStyle(Font.BOLD);
//        placeholders = new TextPrompt("CONTRASEÑA", nuevo.Contrasena);
//        placeholders.changeAlpha(0.75f);
//        placeholders.changeStyle(Font.BOLD);
//        placeholders = new TextPrompt("lobosolo@lobosolo.com", nuevo.Correo);
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
            a.add(nuevo.Usuario.getText());
            a.add(String.valueOf(nuevo.Contrasena.getPassword()));
            a.add(nuevo.Correo.getText());
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
            Date date = new Date();
            a.add(dateFormat.format(date));
            if (!nuevo.Usuario.getText().equals("") && g.addUpdateOrDelete("SP_AGREGAR_USUARIO", a)) {
                JOptionPane.showMessageDialog(null, "USUARIO AGREGADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                nuevo.dispose();
                usuarios.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AGREGAR EL USUARIO", "NO SE HA PODIDO AGREGAR EL USUARIO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO REGISTRAR EL USUARIO", "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEditar(int IDX) {
        try {
            temp = IDX;
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            ArrayList<Object[][]> usuario = g.findByParams("SP_USUARIO_X_ID", a);
            Object[][] data = usuario.get(0);
            editar.Usuario.setText(String.valueOf((data[0][1] != null) ? data[0][1] : ""));
            editar.Contrasena.setText(String.valueOf((data[0][2] != null) ? data[0][2] : ""));
            editar.Correo.setText(String.valueOf((data[0][3] != null) ? data[0][3] : ""));
            editar.Tipo.setSelectedItem((data[0][5] != null) ? data[0][5].toString() : "");
            editar.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
            editar.setLocationRelativeTo(null);
            editar.setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR EL USUARIO", "ERROR AL EDITAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onModificar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(temp);
            a.add(editar.Usuario.getText());
            a.add(String.valueOf(editar.Contrasena.getPassword()));
            a.add(editar.Correo.getText());
            if (!editar.Usuario.getText().equals("") && g.addUpdateOrDelete("SP_MODIFICAR_USUARIO", a)) {
                JOptionPane.showMessageDialog(null, "USUARIO MODIFICADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                editar.dispose();
                usuarios.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL USUARIO", "ERROR AL MODIFICAR EL USUARIO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL USUARIO", "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEliminar(int IDX) {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            if (g.addUpdateOrDelete("SP_ELIMINAR_USUARIO", a)) {
                JOptionPane.showMessageDialog(null, "USUARIO ELIMINADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                usuarios.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL USUARIO", "ERROR AL ELIMINAR EL USUARIO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL USUARIO", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
