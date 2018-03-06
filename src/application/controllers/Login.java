/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.config.Generic;
import application.config.TextPrompt;
import application.views.vLogin;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.KeyboardFocusManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Administrador
 */
public class Login {

    vLogin login;

    Generic g = new Generic();

    public Login() {

        login = new vLogin();

        login.txtContrasena.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!"".equals(login.txtUsuario.getText()) && !"".equals(String.valueOf(login.txtContrasena.getPassword()))) {
                        onAcceder();
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

        TextPrompt placeholder = new TextPrompt("NOMBRE DE USUARIO", login.txtUsuario);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.BOLD);
        TextPrompt placeholders = new TextPrompt("CONTRASEÑA", login.txtContrasena);
        placeholders.changeAlpha(0.75f);
        placeholders.changeStyle(Font.BOLD);
    }

    public void setVisible() {

        login.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/LS.png")));
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }

    public static void main(String[] args) {

        //KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        //Set<KeyStroke> keys = new HashSet<>();
        //keys.add(enter);
        //KeyboardFocusManager.getCurrentKeyboardFocusManager().setDefaultFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, keys);

        try {
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

            UIManager.put("MenuBar.background", Color.WHITE);
            UIManager.put("Menu.background", Color.WHITE);
            UIManager.put("MenuItem.background", Color.WHITE);
            UIManager.put("Separator.background", Color.WHITE);
            UIManager.put("MenuItem.opaque", true);
            UIManager.put("Menu.opaque", true);
            UIManager.put("MenuBar.opaque", true);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                (new Login()).setVisible();
            }
        });

    }

    public void onAcceder() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(login.txtUsuario.getText());
            a.add(String.valueOf(login.txtContrasena.getPassword()));
            ArrayList<Object[][]> usuario = g.findByParams("SP_ACCEDER", a);
            Object[][] data = usuario.get(0);
            if (data != null && data[0][0] != null && data[0][1] != null) {
                login.dispose();
                (new Menu(g)).setVisible();
            } else {
                JOptionPane.showMessageDialog(null, "DATOS INVÁLIDOS, INTENTE DE NUEVO", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "DATOS INVÁLIDOS, INTENTE DE NUEVO", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }
}
