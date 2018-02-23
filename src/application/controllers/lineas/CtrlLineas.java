/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers.lineas;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.Lineas;
import application.helpers.Item;
import application.views.lineas.mdlEditar;
import application.views.lineas.mdlNuevo;
import application.views.vLineas;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Christian
 */
public class CtrlLineas {

    mdlNuevo nuevo;
    mdlEditar editar;
    Generic g;
    Lineas lineas;
    vLineas vlineas;
    int temp = 0;

    public CtrlLineas(JFrame parent, Generic g, Lineas lineas) {
        nuevo = new mdlNuevo(parent, true);
        editar = new mdlEditar(parent, true);
        this.vlineas = (vLineas) parent;
        this.g = g;
        this.lineas = lineas;
        nuevo.cmbTemporada.addActionListener((e) -> {
            System.out.println("TEMPORADA: " + getID(temporadas, nuevo.cmbTemporada.getSelectedItem().toString()));
        });
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

        nuevo.txtClave.addKeyListener(new KeyListener() {
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
        nuevo.txtDescripcion.addKeyListener(new KeyListener() {
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
        editar.txtClave.addKeyListener(new KeyListener() {
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
        editar.txtDescripcion.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    onModificar();
                }
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

        /*PLACEHOLDERS*/
        TextPrompt placeholders = new TextPrompt("AÑO", nuevo.txtAno);
        placeholders.changeAlpha(0.75f);
        placeholders.changeStyle(Font.BOLD);
        placeholders = new TextPrompt("CLAVE", nuevo.txtClave);
        placeholders.changeAlpha(0.75f);
        placeholders.changeStyle(Font.BOLD);
        placeholders = new TextPrompt("DESCRIPCIÓN", nuevo.txtDescripcion);
        placeholders.changeAlpha(0.75f);
        placeholders.changeStyle(Font.BOLD);

        getTemporadas();
    }

    public void setVisible() {
        nuevo.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
        nuevo.setLocationRelativeTo(null);
        nuevo.setVisible(true);
    }

    public void onGuardar() {
        try {
            ArrayList<Object> a = new ArrayList<>(); 
            a.add(nuevo.txtClave.getText());
            a.add(nuevo.txtDescripcion.getText());
            a.add(nuevo.cmbEstatusMuestra.getSelectedItem().toString());
            a.add(nuevo.txtAno.getText());
            a.add(getID(temporadas, nuevo.cmbTemporada.getSelectedItem().toString()));
            a.add(nuevo.cmbEstatus.getSelectedItem().toString());
            if (!nuevo.txtClave.getText().equals("") && g.addUpdateOrDelete("SP_AGREGAR_LINEA", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO AGREGADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                nuevo.dispose();
                lineas.getRecords();
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
            ArrayList<Object[][]> usuario = g.findByParams("SP_USUARIO_X_ID", a);
            Object[][] data = usuario.get(0);
            editar.txtClave.setText(String.valueOf((data[0][1] != null) ? data[0][1] : ""));
            editar.txtDescripcion.setText(String.valueOf((data[0][2] != null) ? data[0][2] : ""));
            editar.cmbEstatusMuestra.setSelectedItem((data[0][3] != null) ? data[0][3].toString() : "");
            editar.txtAno.setText(String.valueOf((data[0][4] != null) ? data[0][4] : ""));
            editar.cmbTemporada.setSelectedItem((data[0][5] != null) ? data[0][5].toString() : "");
            editar.cmbEstatus.setSelectedItem((data[0][6] != null) ? data[0][6].toString() : "");
            editar.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
            editar.setLocationRelativeTo(null);
            editar.setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR EL REGISTRO", "ERROR AL EDITAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onModificar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(temp);
            a.add(editar.txtClave.getText());
            a.add(editar.txtDescripcion.getText());
            a.add(editar.cmbEstatusMuestra.getSelectedItem().toString());
            a.add(editar.txtAno.getText());
            a.add(editar.cmbTemporada.getSelectedItem().toString());
            a.add(editar.cmbEstatus.getSelectedItem().toString());

            if (!editar.txtClave.getText().equals("") && g.addUpdateOrDelete("SP_MODIFICAR_LINEA", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                editar.dispose();
                lineas.getRecords();
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
            for (Iterator it = g.fill("SP_OBTENER_TEMPORADAS").iterator(); it.hasNext();) {
                Object[] util = (Object[]) it.next();
                nuevo.cmbTemporada.addItem(String.valueOf(util[1]));
                editar.cmbTemporada.addItem(String.valueOf(util[1]));
                temporada = new Item(Integer.parseInt(String.valueOf(util[0])), String.valueOf(util[1]));
                temporadas.add(temporada);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER LOS USUARIOS", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
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
