/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.config.Generic;
import application.controllers.usuarios.CtrlUsuarios;
import application.views.vUsuarios;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class Usuarios {

    vUsuarios usuario;
    Generic g = new Generic();

    public Usuarios() {
        usuario = new vUsuarios();
        usuario.btnNuevo.addActionListener((e) -> {
            (new CtrlUsuarios(usuario, g)).setVisible();
        });
    }

    public void setVisible() {
        usuario.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
        usuario.setLocationRelativeTo(null);
        usuario.setVisible(true);
    }

    public void getRecords() {
        try {
            DefaultTableModel dtm = new DefaultTableModel();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
