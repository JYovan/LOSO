/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.views.vUsuarios;
import java.awt.Toolkit;

/**
 *
 * @author Administrador
 */
public class Usuarios {
    vUsuarios usuario;

    public Usuarios() {
        usuario = new vUsuarios();
    }

    public void setVisible() {
        usuario.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
        usuario.setLocationRelativeTo(null);
        usuario.setVisible(true);
    }
}
