package application.controllers.combinaciones;

import application.config.Generic;
import application.controllers.Combinaciones;
import application.helpers.Item;
import application.third_party.Resources;
import application.views.combinaciones.mdlIEditar;
import application.views.combinaciones.mdlINuevo;
import application.views.vCombinaciones;
import application.views.vMenu;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
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

public class CtrlCombinaciones {

    mdlINuevo nuevo;
    mdlIEditar editar;
    Generic g;
    Combinaciones combinaciones;
    vCombinaciones vcombinaciones;
    int temp = 0;
    Resources rsc;
    vMenu menu;

    public CtrlCombinaciones(JInternalFrame parent, Generic g, Combinaciones combinaciones, JFrame menu) {
        this.menu = (vMenu) menu;
        nuevo = new mdlINuevo();
        editar = new mdlIEditar();
        this.vcombinaciones = (vCombinaciones) parent;
        this.g = g;
        this.combinaciones = combinaciones;
        rsc = new Resources();
        //Ayuda en captura combo box
        AutoCompleteDecorator.decorate(this.nuevo.cmbEstilo);
        AutoCompleteDecorator.decorate(this.nuevo.cmbLinea);
        AutoCompleteDecorator.decorate(this.editar.cmbEstilo);
        AutoCompleteDecorator.decorate(this.editar.cmbLinea);

        nuevo.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Combinaciones com = combinaciones;
                com.setVisible();

            }
        });
        editar.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Combinaciones com = combinaciones;
                com.setVisible();
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

        

        getLineas();
        getEstilos();
    }

    public void setVisible() {
        if (!nuevo.isShowing()) {
            nuevo.txtClave.setText("");
            nuevo.txtDescripcion.setText("");
            nuevo.cmbEstilo.setSelectedIndex(0);
            nuevo.cmbLinea.setSelectedIndex(0);

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
            Object x = null;
            ArrayList<Object> a = new ArrayList<>();

            a.add(nuevo.txtClave.getText());
            a.add(nuevo.txtDescripcion.getText());

            x = getID(lineas, nuevo.cmbLinea.getSelectedItem().toString());
            if (Integer.parseInt(String.valueOf(x)) != 0) {
                a.add(x);
            } else {
                a.add(null);
            }
            x = getID(estilos, nuevo.cmbEstilo.getSelectedItem().toString());
            if (Integer.parseInt(String.valueOf(x)) != 0) {
                a.add(x);
            } else {
                a.add(null);
            }
            if (!nuevo.txtClave.getText().equals("") && g.addUpdateOrDelete("SP_AGREGAR_COMBINACION", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO AGREGADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                nuevo.dispose();
                combinaciones.getRecords();
                menu.dpContenedor.remove(nuevo);
                combinaciones.setVisible();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AGREGAR EL REGISTRO", "NO SE HA PODIDO AGREGAR EL REGISTRO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO REGISTRAR EL REGISTRO", "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEditar(int IDX) {
        try {
            
            editar.txtClave.setText("");
            editar.txtDescripcion.setText("");
            editar.cmbEstilo.setSelectedIndex(0);
            editar.cmbLinea.setSelectedIndex(0);
            
            temp = IDX;
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            ArrayList<Object[][]> combinacion = g.findByParams("SP_COMBINACION_X_ID", a);
            Object[][] data = combinacion.get(0);
            editar.txtClave.setText(String.valueOf((data[0][1] != null) ? data[0][1] : ""));
            editar.txtDescripcion.setText(String.valueOf((data[0][2] != null) ? data[0][2] : ""));
            editar.cmbLinea.setSelectedItem((data[0][3] != null) ? data[0][3].toString() : "");
            editar.cmbEstilo.setSelectedItem((data[0][4] != null) ? data[0][4].toString() : "");
            editar.cmbEstatus.setSelectedItem((data[0][5] != null) ? data[0][5].toString() : "");
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

            if (editar.cmbLinea.getSelectedIndex() != -1) {
                x = getID(lineas, editar.cmbLinea.getSelectedItem().toString());
                if (Integer.parseInt(String.valueOf(x)) != 0) {
                    a.add(x);
                } else {
                    a.add(null);
                }
            } else {
                a.add(null);
            }

            if (editar.cmbEstilo.getSelectedIndex() != -1) {
                x = getID(estilos, editar.cmbEstilo.getSelectedItem().toString());
                if (Integer.parseInt(String.valueOf(x)) != 0) {
                    a.add(x);
                } else {
                    a.add(null);
                }
            } else {
                a.add(null);
            }

            a.add(editar.cmbEstatus.getSelectedItem().toString());

            if (!editar.txtClave.getText().equals("") && g.addUpdateOrDelete("SP_MODIFICAR_COMBINACION", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                editar.dispose();
                combinaciones.getRecords();
                menu.dpContenedor.remove(editar);
                combinaciones.setVisible();
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
            if (g.addUpdateOrDelete("SP_ELIMINAR_COMBINACION", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                combinaciones.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL REGISTRO", "ERROR AL ELIMINAR EL REGISTRO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL REGISTRO", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
        }
    }
    ArrayList<Item> lineas;

    public final void getLineas() {
        try {
            lineas = new ArrayList<>();
            nuevo.cmbLinea.addItem("");
            editar.cmbLinea.addItem("");
            Item linea = null;
            for (Iterator it = g.fill("SP_OBTENER_LINEAS").iterator(); it.hasNext();) {
                Object[] util = (Object[]) it.next();
                nuevo.cmbLinea.addItem(String.valueOf(util[1]));
                editar.cmbLinea.addItem(String.valueOf(util[1]));
                linea = new Item(Integer.parseInt(String.valueOf(util[0])), String.valueOf(util[1]));
                lineas.add(linea);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER LOS REGISTROS", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }

    ArrayList<Item> estilos;

    public final void getEstilos() {
        try {
            estilos = new ArrayList<>();
            Item estilo = null;
            nuevo.cmbEstilo.addItem("");
            editar.cmbEstilo.addItem("");
            for (Iterator it = g.fill("SP_OBTENER_ESTILOS").iterator(); it.hasNext();) {
                Object[] util = (Object[]) it.next();
                nuevo.cmbEstilo.addItem(String.valueOf(util[1]));
                editar.cmbEstilo.addItem(String.valueOf(util[1]));
                estilo = new Item(Integer.parseInt(String.valueOf(util[0])), String.valueOf(util[1]));
                estilos.add(estilo);
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
