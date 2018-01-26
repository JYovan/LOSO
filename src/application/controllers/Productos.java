/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.views.vProductos;
import java.awt.Toolkit;

/**
 *
 * @author Administrador
 */
public class Productos {

    vProductos auto;

    public Productos() {
        auto = new vProductos();
    }

    public void setVisible() {
        auto.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("com/st/media/sticon.png")));
        auto.setLocationRelativeTo(null);
        auto.setVisible(true);
    }
}
