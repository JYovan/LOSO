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
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
//            g.addUpdateOrDelete("SP_AGREGAR_USUARIO", a);
        } catch (Exception e) {
        }
    }
}
