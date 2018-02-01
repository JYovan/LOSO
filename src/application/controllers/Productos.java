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

    vProductos producto;

    public Productos() {
        producto = new vProductos();
    }

    public void setVisible() {
        producto.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
        producto.setLocationRelativeTo(null);
        producto.setVisible(true);
    }
}
