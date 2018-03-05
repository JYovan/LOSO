/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers.permisos;

import application.config.Generic;
import application.controllers.Permisos;
import application.helpers.Item;
import application.views.permisos.mdlIEditar;
import application.views.permisos.mdlINuevo;
import application.views.vPermisos;
import application.views.vMenu;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Administrador
 */
public class CtrlPermisos {

    mdlINuevo nuevo;
    mdlIEditar editar;
    Generic g;
    Permisos permisos;
    vPermisos vpermisos;
    int temp = 0;
    vMenu menu;
    ArrayList<Item> modulos = new ArrayList<>();
    ArrayList<Item> usuarios = new ArrayList<>();

    public CtrlPermisos(JInternalFrame parent, Generic g, Permisos permisos, JFrame menu) {
        this.menu = (vMenu) menu;
        /*NO SE DEBE DE LLAMAR NADA SI NO SE DEFINEN ESTAS ASIGNACIONES*/
        nuevo = new mdlINuevo();
        editar = new mdlIEditar();
        this.vpermisos = (vPermisos) parent;
        this.g = g;
        this.permisos = permisos;
        AutoCompleteDecorator.decorate(this.nuevo.Modulo);
        AutoCompleteDecorator.decorate(this.nuevo.Usuario);
        
        nuevo.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Permisos per = permisos;
                per.setVisible();
            }
        });
        editar.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Permisos per = permisos;
                per.setVisible();
            }
        });
        
        
        nuevo.Modulo.addActionListener((e) -> {
            for (Item modulo : modulos) {
                if (modulo.getDescription().equals(nuevo.Modulo.getSelectedItem().toString())) {
                    System.out.println("ID : " + modulo.getID());
                }
            }
        });

        nuevo.btnGuardar.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    onGuardar();
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
       
     
        /*INVOCAR METODOS QUE RELLENAN DATOS*/
        getModulos();
        getUsuarios();
    }

    public void setVisible() {
        if (!nuevo.isShowing()) {
            nuevo.Ver.setSelected(false);
            nuevo.Reportes.setSelected(false);
            nuevo.Modificar.setSelected(false);
            nuevo.Eliminar.setSelected(false);
            nuevo.Crear.setSelected(false);
            nuevo.Consultar.setSelected(false);
            nuevo.Buscar.setSelected(false);
            nuevo.Usuario.setSelectedIndex(0);
            nuevo.Modulo.setSelectedIndex(0);
            
            menu.dpContenedor.add(nuevo);
            Dimension desktopSize = menu.dpContenedor.getSize();
            Dimension jInternalFrameSize = nuevo.getSize();
            nuevo.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            nuevo.setFrameIcon(null);
            nuevo.show();
            nuevo.toFront();
        }
        nuevo.Modulo.requestFocus();
    }

    public void onGuardar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(nuevo.Usuario.getSelectedItem().toString());
            a.add(nuevo.Modulo.getSelectedItem().toString());
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
            if (!nuevo.Modulo.getSelectedItem().toString().equals("") && !nuevo.Usuario.getSelectedItem().toString().equals("") && g.addUpdateOrDelete("SP_AGREGAR_PERMISO", a)) {
                JOptionPane.showMessageDialog(null, "PERMISO AGREGADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                nuevo.dispose();
                permisos.getRecords();
                menu.dpContenedor.remove(nuevo);
                permisos.setVisible();
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
            ArrayList<Object[][]> x = g.findByParams("SP_PERMISO_X_ID", a);
            Object[][] data = x.get(0);
            editar.Usuario.getModel().setSelectedItem(data[0][1]);
            editar.Modulo.getModel().setSelectedItem(data[0][2]);
            editar.Ver.setSelected((data[0][3] != null) ? (String.valueOf(data[0][3]).equals("1")) : false);
            editar.Crear.setSelected((data[0][4] != null) ? (String.valueOf(data[0][4]).equals("1")) : false);
            editar.Modificar.setSelected((data[0][5] != null) ? (String.valueOf(data[0][5]).equals("1")) : false);
            editar.Eliminar.setSelected((data[0][6] != null) ? (String.valueOf(data[0][6]).equals("1")) : false);
            editar.Consultar.setSelected((data[0][7] != null) ? (String.valueOf(data[0][7]).equals("1")) : false);
            editar.Reportes.setSelected((data[0][8] != null) ? (String.valueOf(data[0][8]).equals("1")) : false);
            editar.Buscar.setSelected((data[0][9] != null) ? (String.valueOf(data[0][9]).equals("1")) : false);
            if (!editar.isShowing()) {
                menu.dpContenedor.add(editar);
                Dimension desktopSize = menu.dpContenedor.getSize();
                Dimension jInternalFrameSize = editar.getSize();
                editar.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                        (desktopSize.height - jInternalFrameSize.height) / 2);
                editar.setFrameIcon(null);
                editar.show();
                editar.toFront();
            }
            editar.Modulo.requestFocus();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR EL PERMISO", "ERROR AL EDITAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onModificar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(temp);/*ID*/
            a.add(editar.Ver.isSelected() ? 1 : 0);
            a.add(editar.Crear.isSelected() ? 1 : 0);
            a.add(editar.Modificar.isSelected() ? 1 : 0);
            a.add(editar.Eliminar.isSelected() ? 1 : 0);
            a.add(editar.Consultar.isSelected() ? 1 : 0);
            a.add(editar.Reportes.isSelected() ? 1 : 0);
            a.add(editar.Buscar.isSelected() ? 1 : 0);
            if (!editar.Modulo.getSelectedItem().toString().equals("") && !editar.Usuario.getSelectedItem().toString().equals("") && g.addUpdateOrDelete("SP_MODIFICAR_PERMISO", a)) {
                JOptionPane.showMessageDialog(null, "PERMISO MODIFICADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                editar.dispose();
                permisos.getRecords();
                menu.dpContenedor.remove(editar);
                permisos.setVisible();
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
                Object[] item = (Object[]) it.next();
                nuevo.Usuario.addItem(String.valueOf(item[1]));
                editar.Usuario.addItem(String.valueOf(item[1]));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER LOS USUARIOS", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }

    public final void getModulos() {
        try {
            Item itm = null;
            for (Iterator it = g.fill("SP_OBTENER_MODULOS").iterator(); it.hasNext();) {
                Object[] item = (Object[]) it.next();
                if (item != null) {
                    itm = new Item(Integer.parseInt(String.valueOf(item[0])), String.valueOf(item[1]));
                    nuevo.Modulo.addItem(String.valueOf(item[1]));
                    editar.Modulo.addItem(String.valueOf(item[1]));
                    modulos.add(itm);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER LOS MODULOS", "ERROR AL OBTENER LOS MODULOS", JOptionPane.ERROR_MESSAGE);

            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }
}
