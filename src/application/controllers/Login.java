/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.config.Generic;
import application.config.TextPrompt;
import application.views.vLogin;
import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueIceLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueSteelLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaGreenDreamLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaMauveMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSilverMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSkyMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import org.jvnet.substance.SubstanceLookAndFeel;

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
//SyntheticaAluOxideLookAndFeel
//SaharaSkin  ModerateSkin

    public static void main(String[] args) {

        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                (new Login()).setVisible();
            }
        });

//substanc error en combo box
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                   SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.ModerateSkin");
//
//                } catch (Exception e) {
//                    System.out.println("Substance Graphite failed to initialize");
//                }
//                JFrame.setDefaultLookAndFeelDecorated(Boolean.TRUE);
//                (new Login()).setVisible();
//            }
//        });
//sintetica error al ocultar barra de herramientas
//        try {
//            UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
//            
//        } catch (ParseException | UnsupportedLookAndFeelException e) {
//            JOptionPane.showMessageDialog(null, "Error en Look an Feel" + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
//        }
//        JFrame.setDefaultLookAndFeelDecorated(Boolean.TRUE);
//        (new Login()).setVisible();
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
