/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers.usuarios;

import application.views.usuarios.mdlNuevo; 
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class CtrlUsuarios {

    mdlNuevo usuario;

    public CtrlUsuarios(JFrame parent) {
        usuario = new mdlNuevo(parent,true); 
    }

    public void setVisible() {
        usuario.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
        usuario.setLocationRelativeTo(null);
        usuario.setVisible(true);
    }

    public void getRecords() {
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
