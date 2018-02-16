/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers.permisos;

import application.controllers.permisos.*;
import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.Permisos;
import application.views.permisos.mdlEditar;
import application.views.permisos.mdlNuevo;
import application.views.vPermisos;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Administrador
 */
public class CtrlPermisos {

    mdlNuevo nuevo;
    mdlEditar editar;
    Generic g;
    Permisos permisos;
    vPermisos vpermisos;
    int temp = 0;

    public CtrlPermisos(JFrame parent, Generic g, Permisos permisos) {
        /*NO SE DEBE DE LLAMAR NADA SI NO SE DEFINEN ESTAS ASIGNACIONES*/
        nuevo = new mdlNuevo(parent, true);
        editar = new mdlEditar(parent, true);
        this.vpermisos = (vPermisos) parent;
        this.g = g;
        this.permisos = permisos;

        nuevo.btnGuardar.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    onGuardar();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    nuevo.dispose();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        nuevo.btnGuardar.addActionListener((e) -> {
            onGuardar();
        });
        editar.btnGuardar.addActionListener((e) -> {
            onModificar();
        });
        nuevo.Usuario.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    nuevo.dispose();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        editar.Usuario.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    editar.dispose();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        /*INVOCAR METODOS QUE RELLENAN DATOS*/
        getModulos();
        getUsuarios();
    }

    public void setVisible() {
        nuevo.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
        nuevo.setLocationRelativeTo(null);
        nuevo.setVisible(true);
    }

    public void onGuardar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(nuevo.Modulo.getSelectedItem().toString());
            a.add(nuevo.Usuario.getSelectedItem().toString());
            a.add(nuevo.Ver.isSelected() ? 1 : 0);
            a.add(nuevo.Crear.isSelected() ? 1 : 0);
            a.add(nuevo.Modificar.isSelected() ? 1 : 0);
            a.add(nuevo.Eliminar.isSelected() ? 1 : 0);
            a.add(nuevo.Consultar.isSelected() ? 1 : 0);
            a.add(nuevo.Reportes.isSelected() ? 1 : 0);
            a.add(nuevo.Buscar.isSelected() ? 1 : 0);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
            Date date = new Date();
            a.add(dateFormat.format(date));
            if (!nuevo.Modulo.getSelectedItem().toString().equals("") && nuevo.Usuario.getSelectedItem().toString().equals("") && g.addUpdateOrDelete("SP_AGREGAR_PERMISO", a)) {
                JOptionPane.showMessageDialog(null, "PERMISO AGREGADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                nuevo.dispose();
                permisos.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AGREGAR EL PERMISO", "NO SE HA PODIDO AGREGAR EL PERMISO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO REGISTRAR EL PERMISO", "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEditar(int IDX) {
        try {
            temp = IDX;
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            ArrayList<Object[][]> usuario = g.findByParams("SP_PERMISO_X_ID", a);
            Object[][] data = usuario.get(0);
            editar.Usuario.setSelectedItem(String.valueOf((data[0][1] != null) ? data[0][1] : ""));
            editar.Modulo.setSelectedItem(String.valueOf((data[0][2] != null) ? data[0][2] : ""));

            editar.Ver.setSelected(true);
            editar.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
            editar.setLocationRelativeTo(null);
            editar.setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR EL PERMISO", "ERROR AL EDITAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onModificar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(temp);
            a.add(nuevo.Modulo.getSelectedItem().toString());
            a.add(nuevo.Usuario.getSelectedItem().toString());
            a.add(nuevo.Ver.isSelected() ? 1 : 0);
            a.add(nuevo.Crear.isSelected() ? 1 : 0);
            a.add(nuevo.Modificar.isSelected() ? 1 : 0);
            a.add(nuevo.Eliminar.isSelected() ? 1 : 0);
            a.add(nuevo.Consultar.isSelected() ? 1 : 0);
            a.add(nuevo.Reportes.isSelected() ? 1 : 0);
            a.add(nuevo.Buscar.isSelected() ? 1 : 0);
            if (!editar.Modulo.getSelectedItem().toString().equals("") && !editar.Usuario.getSelectedItem().toString().equals("") && g.addUpdateOrDelete("SP_MODIFICAR_PERMISO", a)) {
                JOptionPane.showMessageDialog(null, "PERMISO MODIFICADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                editar.dispose();
                permisos.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL PERMISO", "ERROR AL MODIFICAR EL PERMISO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL PERMISO", "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEliminar(int IDX) {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            if (g.addUpdateOrDelete("SP_ELIMINAR_PERMISO", a)) {
                JOptionPane.showMessageDialog(null, "PERMISO ELIMINADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                permisos.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL PERMISO", "ERROR AL ELIMINAR EL PERMISO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL PERMISO", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public final void getUsuarios() {
        try {
            for (Iterator it = g.fill("SP_OBTENER_USUARIOS").iterator(); it.hasNext();) {
                Object util = it.next();
                nuevo.Usuario.addItem(String.valueOf(util));
                editar.Usuario.addItem(String.valueOf(util));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER LOS USUARIOS", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }

    public final void getModulos() {
        try {
            for (Iterator it = g.fill("SP_OBTENER_MODULOS").iterator(); it.hasNext();) {
                Object util = it.next();
                nuevo.Modulo.addItem(String.valueOf(util));
                editar.Modulo.addItem(String.valueOf(util));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER LOS MODULOS", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);

            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }
}
