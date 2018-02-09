/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.third_party.Resources;
import application.views.vMenu;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class Menu {

    vMenu menu;
    Resources src = new Resources();

    public Menu() {
        menu = new vMenu();
        src.toSysTray(menu);
        menu.mSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "¿Realmente Desea Salir?", "Confirmar Salida", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    System.exit(0);
                }
            }
        });

        menu.mProveedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        menu.mnuUsuarios.addActionListener((e) -> {
            (new Usuarios()).setVisible();
        });
    }

    public void setVisible() {
        menu.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/LS.png")));
        menu.setLocationRelativeTo(null);
        menu.setVisible(true); 
        //menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
