/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers.usuarios;

import application.config.Generic;
import application.controllers.Usuarios;
import application.views.usuarios.mdlIFEditar;
import application.views.usuarios.mdlIFNuevo;
import application.views.vUsuarios;
import application.views.vMenu;
import java.awt.Dimension;
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
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Administrador
 */
public class CtrlUsuarios {

    mdlIFNuevo nuevo;
    mdlIFEditar editar;
    Generic g;
    Usuarios usuarios;
    vUsuarios vusuarios;
    int temp = 0;
    vMenu mnu;

    public CtrlUsuarios(JInternalFrame parent, Generic g, Usuarios usuarios, JFrame menu) {
        nuevo = new mdlIFNuevo();
        editar = new mdlIFEditar();
        this.vusuarios = (vUsuarios) parent;
        this.g = g;
        this.usuarios = usuarios;
        this.mnu = (vMenu) menu;
        AutoCompleteDecorator.decorate(nuevo.Tipo);
        
        nuevo.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Usuarios u = usuarios;
                u.setVisible();
            }
        });
        editar.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Usuarios u = usuarios;
                u.setVisible();
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

    }

    public void setVisible() {
        if (!nuevo.isShowing()) {
            nuevo.Usuario.setText("");
            nuevo.Contrasena.setText("");
            nuevo.Correo.setText("");
            nuevo.Foto.setText("");
            nuevo.Tipo.setSelectedIndex(0);

            mnu.dpContenedor.add(nuevo);
            Dimension desktopSize = mnu.dpContenedor.getSize();
            Dimension jInternalFrameSize = nuevo.getSize();
            nuevo.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            nuevo.setFrameIcon(null);
            nuevo.show();
            nuevo.toFront();
        }
        nuevo.Usuario.requestFocus();
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
                 mnu.dpContenedor.remove(nuevo);
                usuarios.setVisible();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AGREGAR EL USUARIO", "NO SE HA PODIDO AGREGAR EL USUARIO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO REGISTRAR EL USUARIO", "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEditar(int IDX) {
        try {
            editar.Usuario.setText("");
            editar.Contrasena.setText("");
            editar.Correo.setText("");
            editar.Foto.setText("");
            editar.Tipo.setSelectedIndex(0);
            
            temp = IDX;
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            ArrayList<Object[][]> usuario = g.findByParams("SP_USUARIO_X_ID", a);
            Object[][] data = usuario.get(0);
            editar.Usuario.setText(String.valueOf((data[0][1] != null) ? data[0][1] : ""));
            editar.Contrasena.setText(String.valueOf((data[0][2] != null) ? data[0][2] : ""));
            editar.Correo.setText(String.valueOf((data[0][3] != null) ? data[0][3] : ""));
            editar.Tipo.setSelectedItem((data[0][5] != null) ? data[0][5].toString() : "");

            if (!editar.isShowing()) {
                mnu.dpContenedor.add(editar);
                Dimension desktopSize = mnu.dpContenedor.getSize();
                Dimension jInternalFrameSize = editar.getSize();
                editar.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                        (desktopSize.height - jInternalFrameSize.height) / 2);
                editar.setFrameIcon(null);
                editar.show();
                editar.toFront();
            }
            editar.Usuario.requestFocus();

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
            a.add(editar.Tipo.getSelectedItem().toString());
            if (!editar.Usuario.getText().equals("") && g.addUpdateOrDelete("SP_MODIFICAR_USUARIO", a)) {
                JOptionPane.showMessageDialog(null, "USUARIO MODIFICADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                editar.dispose();
                usuarios.getRecords();
                mnu.dpContenedor.remove(editar);
                usuarios.setVisible();
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
