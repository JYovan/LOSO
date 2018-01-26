/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;
 
import application.views.vLogin;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class Login {

    vLogin login;

    public Login() {

        login = new vLogin();
        /**
         *
         */

        login.btnEntrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!"".equals(login.txtUsuario.getText()) && !"".equals(String.valueOf(login.txtContrasena.getPassword()))) {
                    login.dispose();
                    (new Menu()).setVisible();
                } else {
                    JOptionPane.showMessageDialog(null, "DEBE DE INTRODUCIR SUS CREDENCIALES", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        login.btnSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

    }

    public void setVisible() {
        login.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("com/st/media/sticon.png")));
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }

    public static void main(String[] args) {
        (new Login()).setVisible();
    }
}
