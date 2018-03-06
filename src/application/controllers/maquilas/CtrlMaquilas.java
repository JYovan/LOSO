package application.controllers.maquilas;

import application.config.Generic;
import application.controllers.Maquilas;
import application.helpers.Item;
import application.third_party.Resources;
import application.views.maquilas.mdlIEditar;
import application.views.maquilas.mdlINuevo;
import application.views.vMaquilas;
import application.views.vMenu;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class CtrlMaquilas {

    mdlINuevo nuevo;
    mdlIEditar editar;
    Generic g;
    Maquilas maquilas;
    vMaquilas vmaquilas;
    int temp = 0;
    Resources rsc;
    vMenu menu;

    public CtrlMaquilas(JInternalFrame parent, Generic g, Maquilas maquilas, JFrame menu) {
        this.menu = (vMenu) menu;
        nuevo = new mdlINuevo();
        editar = new mdlIEditar();
        this.vmaquilas = (vMaquilas) parent;
        this.g = g;
        this.maquilas = maquilas;
        rsc = new Resources();
        
        
        nuevo.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Maquilas maq = maquilas;
                maq.setVisible();
            }
        });
        editar.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Maquilas maq = maquilas;
                maq.setVisible();
            }
        });

        //Ayuda en captura combo box
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

 

 

    }

    public void setVisible() {
        if (!nuevo.isShowing()) {

            nuevo.txtClave.setText("");
            nuevo.txtContacto.setText("");
            nuevo.txtDireccion.setText("");
            nuevo.txtNombre.setText("");
            nuevo.txtTelefono.setText("");

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

            a.add(nuevo.txtClave.getText());
            a.add(nuevo.txtNombre.getText());
            a.add(nuevo.txtDireccion.getText());
            a.add(nuevo.txtTelefono.getText());
            a.add(nuevo.txtContacto.getText());

            if (!nuevo.txtClave.getText().equals("") && g.addUpdateOrDelete("SP_AGREGAR_MAQUILA", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO AGREGADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                nuevo.dispose();
                maquilas.getRecords();
                menu.dpContenedor.remove(nuevo);
                maquilas.setVisible();
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
            ArrayList<Object[][]> usuario = g.findByParams("SP_MAQUILA_X_ID", a);
            Object[][] data = usuario.get(0);
            editar.txtClave.setText(String.valueOf((data[0][1] != null) ? data[0][1] : ""));
            editar.txtNombre.setText(String.valueOf((data[0][2] != null) ? data[0][2] : ""));
            editar.txtDireccion.setText((data[0][3] != null) ? data[0][3].toString() : "");
            editar.txtTelefono.setText(String.valueOf((data[0][4] != null) ? data[0][4] : ""));
            editar.txtContacto.setText((data[0][5] != null) ? data[0][5].toString() : "");
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
            a.add(temp);
            a.add(editar.txtClave.getText());
            a.add(editar.txtNombre.getText());
            a.add(editar.txtDireccion.getText());
            a.add(editar.txtTelefono.getText());
            a.add(editar.txtContacto.getText());
            a.add(editar.cmbEstatus.getSelectedItem().toString());

            if (!editar.txtClave.getText().equals("") && g.addUpdateOrDelete("SP_MODIFICAR_MAQUILA", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                editar.dispose();
                maquilas.getRecords();
                menu.dpContenedor.remove(editar);
                maquilas.setVisible();
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
            if (g.addUpdateOrDelete("SP_ELIMINAR_MAQUILA", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                maquilas.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL REGISTRO", "ERROR AL ELIMINAR EL REGISTRO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL REGISTRO", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
        }
    }

}
