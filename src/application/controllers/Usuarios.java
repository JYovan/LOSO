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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class Usuarios {

    vUsuarios vusuarios;
    Generic g = new Generic();

    public Usuarios() {
        vusuarios = new vUsuarios();
        vusuarios.btnNuevo.addActionListener((e) -> {
            (new CtrlUsuarios(vusuarios, g, this)).setVisible();
        });
        vusuarios.btnEditar.addActionListener((e) -> {
            try {
                if (vusuarios.tblUsuarios.getSelectedRow() >= 0) {
                    int ID = Integer.parseInt(vusuarios.tblUsuarios.getValueAt(vusuarios.tblUsuarios.getSelectedRow(), 0).toString());
                    (new CtrlUsuarios(vusuarios, g, this)).onEditar(ID);
                } else {Toolkit.getDefaultToolkit().beep(); 
                    JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "DEBE DE COLOCAR EL ID AL INICIO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }

        });
        vusuarios.btnEliminar.addActionListener((e) -> {
            if (vusuarios.tblUsuarios.getSelectedRow() >= 0) {
                int ID = Integer.parseInt(vusuarios.tblUsuarios.getValueAt(vusuarios.tblUsuarios.getSelectedRow(), 0).toString());
                (new CtrlUsuarios(vusuarios, g, this)).onEliminar(ID);
            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }
        });
        vusuarios.tblUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getClickCount()) {
                    case 2:
                        System.out.println("UN DOBLE CLICK");
                        vusuarios.btnEditar.doClick();
                        break;
                }
            }
        });
        getRecords();
    }

    public void setVisible() {
        vusuarios.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
        vusuarios.setLocationRelativeTo(null);
        vusuarios.setVisible(true);
    }

    public final void getRecords() {
        try {
            ArrayList<Object[][]> a = g.findAll("SP_USUARIOS");
            DefaultTableModel dtm = g.getModelFill(a.get(0), g.getDimensional(a.get(1)));
            vusuarios.tblUsuarios.setModel(dtm);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
