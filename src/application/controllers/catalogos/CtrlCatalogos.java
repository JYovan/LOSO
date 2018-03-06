package application.controllers.catalogos;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.Catalogos;
import application.third_party.Resources;
import application.views.catalogos.mdlIEditar;
import application.views.catalogos.mdlINuevo;
import application.views.vCatalogos;
import application.views.vMenu;
import java.awt.Dimension;
import java.awt.Font;
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

public class CtrlCatalogos {

    mdlINuevo nuevo;
    mdlIEditar editar;
    Generic g;
    Catalogos catalogos;
    vCatalogos vcatalogos;
    int temp = 0;
    String TipoCatalogo;
    vMenu menu;

    Resources rsc = new Resources();

    public CtrlCatalogos(JInternalFrame parent, Generic g, Catalogos catalogos, String TipoCatalogo, JFrame menu) {
        this.TipoCatalogo = TipoCatalogo;
        this.menu = (vMenu) menu;
        nuevo = new application.views.catalogos.mdlINuevo();
        editar = new application.views.catalogos.mdlIEditar();
        this.vcatalogos = (vCatalogos) parent;
        this.g = g;
        this.catalogos = catalogos;

        nuevo.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Catalogos cat = catalogos;
                cat.setVisible();

            }
        });
        editar.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Catalogos cat = catalogos;
                cat.setVisible();
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

        nuevo.IValue.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyTyped(KeyEvent e) {
                rsc.setOnlyNumbers(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        nuevo.Valor_Num.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))
                        && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                        && (e.getKeyChar() != '.' || nuevo.Valor_Num.getText().contains("."))) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
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

        editar.IValue.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyTyped(KeyEvent e) {
                rsc.setOnlyNumbers(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        editar.Valor_Num.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))
                        && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                        && (e.getKeyChar() != '.' || editar.Valor_Num.getText().contains("."))) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

    }

    public void onMostrarTablero() {
        menu.dpContenedor.add(vcatalogos);

        Dimension desktopSize = menu.dpContenedor.getSize();
        Dimension jInternalFrameSize = vcatalogos.getSize();
        vcatalogos.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        vcatalogos.setFrameIcon(null);
        vcatalogos.setTitle(this.TipoCatalogo);
        vcatalogos.show();
    }

    public void setVisible() {

        if (!nuevo.isShowing()) {

            nuevo.IValue.setText("");
            nuevo.SValue.setText("");
            nuevo.Special.setText("");
            nuevo.Valor_Num.setText("");
            nuevo.Valor_Text.setText("");

            menu.dpContenedor.add(nuevo);
            Dimension desktopSize = menu.dpContenedor.getSize();
            Dimension jInternalFrameSize = nuevo.getSize();
            nuevo.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            nuevo.setFrameIcon(null);
            nuevo.show();
            nuevo.toFront();
        }

        nuevo.SValue.requestFocus();

    }

    public void onGuardar() {
        try {
            ArrayList<Object> a = new ArrayList<>();

            a.add(this.TipoCatalogo);
            a.add((nuevo.IValue.getText().equals("")) ? 0 : Integer.parseInt(nuevo.IValue.getText()));
            a.add(nuevo.SValue.getText());
            a.add(nuevo.Special.getText());
            a.add((nuevo.Valor_Num.getText().equals("")) ? 0.00 : nuevo.Valor_Num.getText());
            a.add(nuevo.Valor_Text.getText());

            if (!nuevo.SValue.getText().equals("") && g.addUpdateOrDelete("SP_AGREGAR_CATALOGO", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO AGREGADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                nuevo.dispose();
                catalogos.getRecords();
                menu.dpContenedor.remove(nuevo);
                catalogos.setVisible();

            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AGREGAR EL REGISTRO", "NO SE HA PODIDO AGREGAR EL REGISTRO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO REGISTRAR EL REGISTRO", "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEditar(int IDX) {
        try {
            
            
            editar.IValue.setText("");
            editar.SValue.setText("");
            editar.Special.setText("");
            editar.Valor_Num.setText("");
            editar.Valor_Text.setText("");
            
            temp = IDX;
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            ArrayList<Object[][]> catalogo = g.findByParams("SP_CATALOGO_X_ID", a);
            Object[][] data = catalogo.get(0);
            editar.IValue.setText(String.valueOf((data[0][0] != null) ? data[0][0] : ""));
            editar.SValue.setText(String.valueOf((data[0][1] != null) ? data[0][1] : ""));
            editar.Valor_Text.setText(String.valueOf((data[0][2] != null) ? data[0][2] : ""));
            editar.Valor_Num.setText(String.valueOf((data[0][3] != null) ? data[0][3] : ""));
            editar.Special.setText(String.valueOf((data[0][4] != null) ? data[0][4] : ""));
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
            editar.SValue.requestFocus();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR EL REGISTRO", "ERROR AL EDITAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onModificar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(temp);
            a.add((editar.IValue.getText().equals("")) ? 0 : Integer.parseInt(editar.IValue.getText()));
            a.add((editar.SValue.getText() != null) ? editar.SValue.getText() : "");
            a.add((editar.Valor_Num.getText().equals("")) ? 0.00 : editar.Valor_Num.getText());
            a.add((editar.Valor_Text.getText() != null) ? editar.Valor_Text.getText() : "");
            a.add((editar.Special.getText() != null) ? editar.Special.getText() : "");
            a.add(editar.cmbEstatus.getSelectedItem().toString());

            if (!editar.SValue.getText().equals("") && g.addUpdateOrDelete("SP_MODIFICAR_CATALOGO", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                editar.dispose();
                catalogos.getRecords();
                menu.dpContenedor.remove(editar);
                catalogos.setVisible();

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
            if (g.addUpdateOrDelete("SP_ELIMINAR_CATALOGO", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                catalogos.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL REGISTRO", "ERROR AL ELIMINAR EL REGISTRO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL REGISTRO", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
        }
    }

}
