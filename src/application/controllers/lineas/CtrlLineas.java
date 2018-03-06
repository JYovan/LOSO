/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers.lineas;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.Fracciones;
import application.controllers.Lineas;
import application.helpers.Item;
import application.third_party.Resources;
import application.views.lineas.mdlIEditar;
import application.views.lineas.mdlINuevo;
import application.views.vLineas;
import application.views.vMenu;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Christian
 */
public class CtrlLineas {

    mdlINuevo nuevo;
    mdlIEditar editar;
    Generic g;
    Lineas lineas;
    vLineas vlineas;
    int temp = 0;
    Resources rsc;
    vMenu menu;

    public CtrlLineas(JInternalFrame parent, Generic g, Lineas lineas, JFrame menu) {
        this.menu = (vMenu) menu;
        nuevo = new mdlINuevo();
        editar = new mdlIEditar();
        this.vlineas = (vLineas) parent;
        this.g = g;
        this.lineas = lineas;
        rsc = new Resources();
        nuevo.cmbTemporada.addActionListener((e) -> {
           // System.out.println("TEMPORADA: " + getID(temporadas, nuevo.cmbTemporada.getSelectedItem().toString()));
        });

        //Ayuda en captura combo box
        AutoCompleteDecorator.decorate(this.nuevo.cmbEstatusMuestra);
        AutoCompleteDecorator.decorate(this.editar.cmbEstatusMuestra);
        
        
        
         nuevo.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Lineas lin = lineas;
                lin.setVisible();
            }
        });
        editar.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Lineas lin = lineas;
                lin.setVisible();
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
        
        editar.btnGuardar.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    onModificar();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        editar.btnGuardar.addActionListener((e) -> {
            onModificar();
        });

  



        getTemporadas();
        getTiposEstilo();
    }

    public void setVisible() {
        if (!nuevo.isShowing()) {

            nuevo.txtAno.setText("");
            nuevo.txtClave.setText("");
            nuevo.txtDescripcion.setText("");

            nuevo.cmbEstatusMuestra.setSelectedIndex(0);
            nuevo.cmbTemporada.setSelectedIndex(0);

            menu.dpContenedor.add(nuevo);
            Dimension desktopSize = menu.dpContenedor.getSize();
            Dimension jInternalFrameSize = nuevo.getSize();
            nuevo.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            nuevo.setFrameIcon(null);
            nuevo.show();
            nuevo.toFront();
        }
        nuevo.txtClave.requestFocus();
    }

    public void onGuardar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            Object x = null;

            a.add(nuevo.txtClave.getText());
            a.add(nuevo.txtDescripcion.getText());
            x = getID(tiposestilo, nuevo.cmbEstatusMuestra.getSelectedItem().toString());
            if (Integer.parseInt(String.valueOf(x)) != 0) {
                a.add(x);
            } else {
                a.add(null);
            }

            a.add(nuevo.txtAno.getText());
            x = getID(temporadas, nuevo.cmbTemporada.getSelectedItem().toString());
            if (Integer.parseInt(String.valueOf(x)) != 0) {
                a.add(x);
            } else {
                a.add(null);
            }

            if (!nuevo.txtClave.getText().equals("") && g.addUpdateOrDelete("SP_AGREGAR_LINEA", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO AGREGADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                nuevo.dispose();
                lineas.getRecords();
                menu.dpContenedor.remove(nuevo);
                lineas.setVisible();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AGREGAR EL REGISTRO", "NO SE HA PODIDO AGREGAR EL REGISTRO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO REGISTRAR EL REGISTRO", "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEditar(int IDX) {
        try {
            temp = IDX;
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            ArrayList<Object[][]> linea = g.findByParams("SP_LINEA_X_ID", a);
            Object[][] data = linea.get(0);
            editar.txtClave.setText(String.valueOf((data[0][1] != null) ? data[0][1] : ""));
            editar.txtDescripcion.setText(String.valueOf((data[0][2] != null) ? data[0][2] : ""));
            editar.cmbEstatusMuestra.setSelectedItem((data[0][3] != null) ? data[0][3].toString() : "");
            editar.txtAno.setText(String.valueOf((data[0][4] != null) ? data[0][4] : ""));
            editar.cmbTemporada.setSelectedItem((data[0][5] != null) ? data[0][5].toString() : "");
            editar.cmbEstatus.setSelectedItem((data[0][6] != null) ? data[0][6].toString() : "");
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
            editar.txtClave.requestFocus();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR EL REGISTRO", "ERROR AL EDITAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onModificar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            Object x = null;
            a.add(temp);
            a.add(editar.txtClave.getText());
            a.add(editar.txtDescripcion.getText());

            if (editar.cmbEstatusMuestra.getSelectedIndex() != -1) {
                x = getID(tiposestilo, editar.cmbEstatusMuestra.getSelectedItem().toString());
                if (Integer.parseInt(String.valueOf(x)) != 0) {
                    a.add(x);
                } else {
                    a.add(null);
                }
            } else {
                a.add(null);
            }

            a.add(editar.txtAno.getText());

            if (editar.cmbTemporada.getSelectedIndex() != -1) {
                x = getID(temporadas, editar.cmbTemporada.getSelectedItem().toString());
                if (Integer.parseInt(String.valueOf(x)) != 0) {
                    a.add(x);
                } else {
                    a.add(null);
                }
            } else {
                a.add(null);
            }
            a.add(editar.cmbEstatus.getSelectedItem().toString());

            if (!editar.txtClave.getText().equals("") && g.addUpdateOrDelete("SP_MODIFICAR_LINEA", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                editar.dispose();
                lineas.getRecords();
                menu.dpContenedor.remove(editar);
                lineas.setVisible();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL REGISTRO", "ERROR AL MODIFICAR EL REGISTRO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL REGISTRO", "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEliminar(int IDX) {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            if (g.addUpdateOrDelete("SP_ELIMINAR_LINEA", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                lineas.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL REGISTRO", "ERROR AL ELIMINAR EL REGISTRO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL REGISTRO", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
        }
    }
    ArrayList<Item> temporadas;

    public final void getTemporadas() {
        try {
            temporadas = new ArrayList<>();
            Item temporada = null;
            nuevo.cmbTemporada.addItem("");
            editar.cmbTemporada.addItem("");
            for (Iterator it = g.fill("SP_OBTENER_TEMPORADAS").iterator(); it.hasNext();) {
                Object[] util = (Object[]) it.next();
                nuevo.cmbTemporada.addItem(String.valueOf(util[1]));
                editar.cmbTemporada.addItem(String.valueOf(util[1]));
                temporada = new Item(Integer.parseInt(String.valueOf(util[0])), String.valueOf(util[1]));
                temporadas.add(temporada);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER LOS REGISTROS", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }

    ArrayList<Item> tiposestilo;

    public final void getTiposEstilo() {
        try {
            tiposestilo = new ArrayList<>();
            Item tipoestilo = null;
            nuevo.cmbEstatusMuestra.addItem("");
            editar.cmbEstatusMuestra.addItem("");
            for (Iterator it = g.fill("SP_OBTENER_TIPOS_ESTILO").iterator(); it.hasNext();) {
                Object[] util = (Object[]) it.next();
                nuevo.cmbEstatusMuestra.addItem(String.valueOf(util[1]));
                editar.cmbEstatusMuestra.addItem(String.valueOf(util[1]));
                tipoestilo = new Item(Integer.parseInt(String.valueOf(util[0])), String.valueOf(util[1]));
                tiposestilo.add(tipoestilo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER LOS REGISTROS", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }

    public int getID(ArrayList<Item> x, String selected_item) {
        int id = 0;
        for (Item o : x) {
            if (o.getDescription().equals(selected_item)) {
                id = o.getID();
                break;
            }
        }
        return id;
    }
}
