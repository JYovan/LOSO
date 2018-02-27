
package application.controllers.fracciones;

import application.config.Generic;
import application.controllers.Fracciones;
import application.helpers.Item;
import application.third_party.Resources;
import application.views.fracciones.mdlEditar;
import application.views.fracciones.mdlNuevo;
import application.views.vFracciones;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class CtrlFracciones {
    mdlNuevo nuevo;
    mdlEditar editar;
    Generic g;
    Fracciones fracciones;
    vFracciones vfracciones;
    int temp = 0;
    Resources rsc;
    
    
    public CtrlFracciones(JFrame parent, Generic g, Fracciones fracciones) {
        nuevo = new mdlNuevo(parent, true);
        editar = new mdlEditar(parent, true);
        this.vfracciones = (vFracciones) parent;
        this.g = g;
        this.fracciones = fracciones;
        rsc = new Resources();

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

        nuevo.cmbDepartamento.addKeyListener(new KeyListener() {
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

        editar.cmbEstatus.addKeyListener(new KeyListener() {
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

        getDepartamentos();
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
            a.add(getID(departamentos, nuevo.cmbDepartamento.getSelectedItem().toString()));

            if (!nuevo.txtClave.getText().equals("") && g.addUpdateOrDelete("SP_AGREGAR_FRACCION", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO AGREGADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                nuevo.dispose();
                fracciones.getRecords();
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
            ArrayList<Object[][]> combinacion = g.findByParams("SP_FRACCION_X_ID", a);
            Object[][] data = combinacion.get(0);
            editar.txtClave.setText(String.valueOf((data[0][1] != null) ? data[0][1] : ""));
            editar.txtDescripcion.setText(String.valueOf((data[0][2] != null) ? data[0][2] : ""));
            editar.cmbDepartamento.setSelectedItem((data[0][3] != null) ? data[0][3].toString() : "");
            editar.cmbEstatus.setSelectedItem((data[0][4] != null) ? data[0][4].toString() : "");
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
            a.add(getID(departamentos, editar.cmbDepartamento.getSelectedItem().toString()));
            a.add(editar.cmbEstatus.getSelectedItem().toString());

            if (!editar.txtClave.getText().equals("") && g.addUpdateOrDelete("SP_MODIFICAR_FRACCION", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                editar.dispose();
                fracciones.getRecords();
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
            if (g.addUpdateOrDelete("SP_ELIMINAR_FRACCION", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                fracciones.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL REGISTRO", "ERROR AL ELIMINAR EL REGISTRO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL REGISTRO", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
        }
    }
 

    ArrayList<Item> departamentos;

    public final void getDepartamentos() {
        try {
            departamentos = new ArrayList<>();
            Item departamento = null;
            for (Iterator it = g.fill("SP_OBTENER_DEPARTAMENTOS").iterator(); it.hasNext();) {
                Object[] util = (Object[]) it.next();
                nuevo.cmbDepartamento.addItem(String.valueOf(util[1]));
                editar.cmbDepartamento.addItem(String.valueOf(util[1]));
                departamento = new Item(Integer.parseInt(String.valueOf(util[0])), String.valueOf(util[1]));
                departamentos.add(departamento);
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
