/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;
 
import application.views.vMenu;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Administrador
 */
public class Menu {

    vMenu menu;

    public Menu() {
        menu = new vMenu();
        menu.btnSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        menu.btnProducto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                (new Productos()).setVisible();
            }
        });
    }

    public void setVisible() {
        menu.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("com/st/media/sticon.png")));
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }
}
