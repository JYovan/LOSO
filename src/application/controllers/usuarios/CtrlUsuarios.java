/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers.usuarios;

import application.config.Generic;
import application.views.usuarios.mdlNuevo;
import java.awt.Toolkit;
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
public class CtrlUsuarios {

    mdlNuevo usuario;
    Generic g;

    public CtrlUsuarios(JFrame parent, Generic g) {
        usuario = new mdlNuevo(parent, true);
        this.g = g;
        usuario.btnGuardar.addActionListener((e) -> {
            onGuardar();
        });
    }

    public void setVisible() {
        usuario.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
        usuario.setLocationRelativeTo(null);
        usuario.setVisible(true);
    }

    public void onGuardar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(usuario.Usuario.getText());
            a.add(String.valueOf(usuario.Contrasena.getPassword()));
            a.add(usuario.Correo.getText());
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            a.add(dateFormat.format(date));
            if (!usuario.Usuario.getText().equals("") && g.addUpdateOrDelete("SP_AGREGAR_USUARIO", a)) {
                JOptionPane.showMessageDialog(null, "USUARIO AGREGADO");
                usuario.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AGREGAR EL USUARIO", "NO SE HA PODIDO AGREGAR EL USUARIO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
        }
    }
}
