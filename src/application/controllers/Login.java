/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.views.vLogin;
import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueIceLookAndFeel;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;
import java.text.ParseException;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Administrador
 */
public class Login {

    vLogin login;

    public Login() {

        login = new vLogin();
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

        login.btnEntrar.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!"".equals(login.txtUsuario.getText()) && !"".equals(String.valueOf(login.txtContrasena.getPassword()))) {
                        login.dispose();
                        (new Menu()).setVisible();
                    } else {
                        JOptionPane.showMessageDialog(null, "DEBE DE INTRODUCIR SUS CREDENCIALES", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        login.txtContrasena.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!"".equals(login.txtUsuario.getText()) && !"".equals(String.valueOf(login.txtContrasena.getPassword()))) {
                        login.dispose();
                        (new Menu()).setVisible();
                    } else {
                        JOptionPane.showMessageDialog(null, "DEBE DE INTRODUCIR SUS CREDENCIALES", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

    }

    public void setVisible() {
        login.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/LS.png")));
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new SyntheticaSimple2DLookAndFeel());
        } catch (ParseException | UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, "Error en Look an Feel" + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        (new Login()).setVisible();
    }
}