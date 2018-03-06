/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers.materiales;

import application.config.Generic;
import application.controllers.Materiales;
import application.helpers.Item;
import application.third_party.WaitLayerUI;
import application.views.materiales.mdlIEditar;
import application.views.materiales.mdlINuevo;
import application.views.vMateriales;
import application.views.vMenu;
import datechooser.model.exeptions.IncompatibleDataExeption;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLayer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Administrador
 */
public class CtrlMateriales {

    mdlINuevo nuevo;
    mdlIEditar editar;
    Generic g;
    Materiales materiales;
    vMateriales vmateriales;
    int temp = 0;
    boolean tiene_foto = false;
    ArrayList<Item> departamentos = new ArrayList<>();
    ArrayList<Item> familias = new ArrayList<>();
    ArrayList<Item> unidades_de_compra = new ArrayList<>();
    ArrayList<Item> unidades_de_consumo = new ArrayList<>();
    ArrayList<Item> tipos = new ArrayList<>();
    ArrayList<Item> estatus = new ArrayList<>();
    JFileChooser fc;
    vMenu menu;

    public CtrlMateriales(JInternalFrame parent, Generic g, Materiales materiales, JFrame menu) {

        this.menu = (vMenu) menu;
        /*NO SE DEBE DE LLAMAR NADA SI NO SE DEFINEN ESTAS ASIGNACIONES*/
        nuevo = new mdlINuevo();
        editar = new mdlIEditar();
        this.vmateriales = (vMateriales) parent;
        this.g = g;
        this.materiales = materiales;

        //Ayuda en captura combo box
        AutoCompleteDecorator.decorate(this.nuevo.Departamento);
        AutoCompleteDecorator.decorate(this.nuevo.Familia);
        AutoCompleteDecorator.decorate(this.nuevo.UnidadCompra);
        AutoCompleteDecorator.decorate(this.nuevo.UnidadConsumo);
        AutoCompleteDecorator.decorate(this.nuevo.Tipo);
        AutoCompleteDecorator.decorate(this.nuevo.Estatus);

        AutoCompleteDecorator.decorate(this.editar.Departamento);
        AutoCompleteDecorator.decorate(this.editar.Familia);
        AutoCompleteDecorator.decorate(this.editar.UnidadCompra);
        AutoCompleteDecorator.decorate(this.editar.UnidadConsumo);
        AutoCompleteDecorator.decorate(this.editar.Tipo);
        AutoCompleteDecorator.decorate(this.editar.Estatus);

        nuevo.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Materiales mat = materiales;
                mat.setVisible();
            }
        });
        editar.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Materiales mat = materiales;
                mat.setVisible();
            }
        });

        /*NUEVO*/
        nuevo.PrecioTope.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))
                        && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                        && (e.getKeyChar() != '.' || nuevo.PrecioTope.getText().contains("."))) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        nuevo.PrecioLista.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))
                        && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                        && (e.getKeyChar() != '.' || nuevo.PrecioLista.getText().contains("."))) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        nuevo.Minimo.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))
                        && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                        && (e.getKeyChar() != '.' || nuevo.Minimo.getText().contains("."))) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        nuevo.Maximo.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))
                        && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                        && (e.getKeyChar() != '.' || nuevo.Maximo.getText().contains("."))) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        nuevo.Existencia.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))
                        && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                        && (e.getKeyChar() != '.' || nuevo.Existencia.getText().contains("."))) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
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


        /*EDITAR*/
        editar.PrecioTope.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))
                        && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                        && (e.getKeyChar() != '.' || editar.PrecioTope.getText().contains("."))) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        editar.PrecioLista.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))
                        && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                        && (e.getKeyChar() != '.' || editar.PrecioLista.getText().contains("."))) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        editar.Minimo.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))
                        && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                        && (e.getKeyChar() != '.' || editar.Minimo.getText().contains("."))) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        editar.Maximo.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))
                        && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                        && (e.getKeyChar() != '.' || editar.Maximo.getText().contains("."))) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        editar.Existencia.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9'))
                        && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE)
                        && (e.getKeyChar() != '.' || editar.Existencia.getText().contains("."))) {
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

        /*PLACEHOLDERS*/

 /*INVOCAR METODOS QUE RELLENAN DATOS*/
        getFamilias();
        getDepartamentos();
        getUnidades();
    }

    public void setVisible() {
        if (!nuevo.isShowing()) {

            nuevo.Descripcion.setText("");
            nuevo.Existencia.setText("");
            nuevo.Material.setText("");
            nuevo.FechaUltimoInventario.setText("");
            nuevo.Maximo.setText("");
            nuevo.Minimo.setText("");
            nuevo.PrecioLista.setText("");
            nuevo.PrecioTope.setText("");

            nuevo.UnidadCompra.setSelectedIndex(0);
            nuevo.UnidadConsumo.setSelectedIndex(0);
            nuevo.Departamento.setSelectedIndex(0);
            nuevo.Estatus.setSelectedIndex(0);
            nuevo.Familia.setSelectedIndex(0);
            nuevo.Tipo.setSelectedIndex(0);

            menu.dpContenedor.add(nuevo);
            Dimension desktopSize = menu.dpContenedor.getSize();
            Dimension jInternalFrameSize = nuevo.getSize();
            nuevo.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            nuevo.setFrameIcon(null);
            nuevo.show();
            nuevo.toFront();
        }
        nuevo.Material.requestFocus();
    }

    BufferedImage bufi = null;
    File files_dir = new File("files");

    public void onGuardar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            Object x = null;
            if (!nuevo.Material.getText().equals("")) {
                a.add(nuevo.Material.getText());
                if (nuevo.Departamento.getSelectedIndex() != -1) {
                    x = getID(departamentos, nuevo.Departamento.getSelectedItem().toString());
                    if (Integer.parseInt(String.valueOf(x)) != 0) {
                        a.add(x);
                    } else {
                        a.add(null);
                    }
                } else {
                    a.add(null);
                }
                x = getID(familias, nuevo.Familia.getSelectedItem().toString());
                if (Integer.parseInt(String.valueOf(x)) != 0) {
                    a.add(x);
                } else {
                    a.add(null);
                }
                a.add((nuevo.Descripcion.getText().equals("")) ? "" : nuevo.Descripcion.getText());
                x = getID(unidades_de_compra, nuevo.UnidadCompra.getSelectedItem().toString());
                if (Integer.parseInt(String.valueOf(x)) != 0) {
                    a.add(x);
                } else {
                    a.add(null);
                }
                x = getID(unidades_de_consumo, nuevo.UnidadConsumo.getSelectedItem().toString());
                if (Integer.parseInt(String.valueOf(x)) != 0) {
                    a.add(x);
                } else {
                    a.add(null);
                }
                a.add(nuevo.Tipo.getSelectedItem().toString());
                a.add(nuevo.Minimo.getText().equals("") ? 0 : nuevo.Minimo.getText());
                a.add(nuevo.Maximo.getText().equals("") ? 0 : nuevo.Maximo.getText());
                a.add(nuevo.PrecioLista.getText().equals("") ? 0 : nuevo.PrecioLista.getText());
                a.add(nuevo.PrecioTope.getText().equals("") ? 0 : nuevo.PrecioTope.getText());
                a.add(nuevo.FechaUltimoInventario.getText());
                a.add(nuevo.Existencia.getText().equals("") ? 0 : nuevo.Existencia.getText());
                a.add(nuevo.Estatus.getSelectedItem().toString());
                if (g.addUpdateOrDelete("SP_AGREGAR_MATERIAL", a)) {
                    JOptionPane.showMessageDialog(null, "MATERIAL AGREGADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                    nuevo.dispose();
                    materiales.getRecords();
                    menu.dpContenedor.remove(nuevo);
                    materiales.setVisible();

                } else {
                    JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AGREGAR EL MATERIAL", "NO SE HA PODIDO AGREGAR EL MATERIAL", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE ELEGIR UNA LINEA Y UNA CLAVE", "NO SE HA PODIDO AGREGAR EL MATERIAL", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO REGISTRAR EL MATERIAL", "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEditar(int IDX) {
        try {
            
            editar.Descripcion.setText("");
            editar.Existencia.setText("");
            editar.Material.setText("");
            editar.FechaUltimoInventario.setText("");
            editar.Maximo.setText("");
            editar.Minimo.setText("");
            editar.PrecioLista.setText("");
            editar.PrecioTope.setText("");

            editar.UnidadCompra.setSelectedIndex(0);
            editar.UnidadConsumo.setSelectedIndex(0);
            editar.Departamento.setSelectedIndex(0);
            editar.Estatus.setSelectedIndex(0);
            editar.Familia.setSelectedIndex(0);
            editar.Tipo.setSelectedIndex(0);
            
            
            temp = IDX;
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            ArrayList<Object[][]> x = g.findByParams("SP_MATERIAL_X_ID", a);
            Object[][] data = x.get(0);
            editar.Material.setText(String.valueOf(data[0][1]));
            if (data[0][2] != null) {
                editar.Departamento.getModel().setSelectedItem(data[0][2]);
            }
            if (data[0][3] != null) {
                editar.Familia.getModel().setSelectedItem(data[0][3]);
            }
            editar.Descripcion.setText(String.valueOf(data[0][4]));
            if (data[0][5] != null) {
                editar.UnidadCompra.getModel().setSelectedItem(data[0][5]);
            }
            if (data[0][6] != null) {
                editar.UnidadConsumo.getModel().setSelectedItem(data[0][6]);
            }
            if (data[0][7] != null) {
                editar.Tipo.getModel().setSelectedItem(data[0][7]);
            }
            if (!String.valueOf(data[0][8]).equals("")) {
                editar.Minimo.setText(String.valueOf(data[0][8]));
            }
            if (!String.valueOf(data[0][9]).equals("")) {
                editar.Maximo.setText(String.valueOf(data[0][9]));
            }
            if (!String.valueOf(data[0][10]).equals("")) {
                editar.PrecioLista.setText(String.valueOf(data[0][10]));
            }
            if (!String.valueOf(data[0][11]).equals("")) {
                editar.PrecioTope.setText(String.valueOf(data[0][11]));
            }
            String fecha = String.valueOf(data[0][12]);
            String[] partes = fecha.split("/");/*0 = dias, 1 = meses, 2 = años*/
            System.out.println("AÑO: " + partes[2] + ", MES: " + partes[1] + ", DIAS:" + partes[0]);
            editar.FechaUltimoInventario.setDefaultPeriods(new datechooser.model.multiple.PeriodSet(new datechooser.model.multiple.Period(
                    new java.util.GregorianCalendar(Integer.parseInt(partes[2]), Integer.parseInt(partes[1]) - 1, Integer.parseInt(partes[0])),
                    new java.util.GregorianCalendar(Integer.parseInt(partes[2]), Integer.parseInt(partes[1]) - 1, Integer.parseInt(partes[0])))));

            editar.Existencia.setText(String.valueOf(data[0][13]));
            if (data[0][14] != null) {
                editar.Estatus.getModel().setSelectedItem(data[0][14]);
            }
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
            editar.Material.requestFocus();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR EL MATERIAL", "ERROR AL EDITAR", JOptionPane.ERROR_MESSAGE);
        } catch (IncompatibleDataExeption ex) {
            Logger.getLogger(CtrlMateriales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onModificar() {
//        try {
        ArrayList<Object> a = new ArrayList<>();
        Object x = null;
        a.add(temp);/*ID*/
        if (!editar.Material.getText().equals("")) {
            a.add(editar.Material.getText());
            if (editar.Departamento.getSelectedIndex() != -1) {
                x = getID(departamentos, editar.Departamento.getSelectedItem().toString());
                if (Integer.parseInt(String.valueOf(x)) != 0) {
                    a.add(x);
                } else {
                    a.add(null);
                }
            } else {
                a.add(null);
            }
            if (editar.Familia.getSelectedIndex() != -1) {

                x = getID(familias, editar.Familia.getSelectedItem().toString());
               
                if (Integer.parseInt(String.valueOf(x)) != 0) {
                    a.add(x);
                } else {
                    a.add(null);
                }
            } else {
                a.add(null);
            }
            a.add((editar.Descripcion.getText().equals("")) ? "" : editar.Descripcion.getText());
            if (editar.UnidadCompra.getSelectedIndex() != -1) {
                x = getID(unidades_de_compra, editar.UnidadCompra.getSelectedItem().toString());
                if (Integer.parseInt(String.valueOf(x)) != 0) {
                    a.add(x);
                } else {
                    a.add(null);
                }
            } else {
                a.add(null);
            }
            if (editar.UnidadConsumo.getSelectedIndex() != -1) {
                x = getID(unidades_de_consumo, editar.UnidadConsumo.getSelectedItem().toString());
                if (Integer.parseInt(String.valueOf(x)) != 0) {
                    a.add(x);
                } else {
                    a.add(null);
                }
            } else {
                a.add(null);
            }
            a.add(editar.Tipo.getSelectedItem().toString());
            a.add(editar.Minimo.getText().equals("") ? 0 : editar.Minimo.getText());
            a.add(editar.Maximo.getText().equals("") ? 0 : editar.Maximo.getText());
            a.add(editar.PrecioLista.getText().equals("") ? 0 : editar.PrecioLista.getText());
            a.add(editar.PrecioTope.getText().equals("") ? 0 : editar.PrecioTope.getText());
            a.add(editar.FechaUltimoInventario.getText());
            a.add(editar.Existencia.getText().equals("") ? 0 : (editar.Existencia.getText()));
            a.add(editar.Estatus.getSelectedItem().toString());
        }

//                puede servir para testear
//        for (int i = 0; i < a.size(); i++) {
//            System.out.println(i + ".-" + String.valueOf(a.get(i)));
//        }
        if (g.addUpdateOrDelete("SP_MODIFICAR_MATERIAL", a)) {
            JOptionPane.showMessageDialog(null, "MATERIAL MODIFICADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
            editar.dispose();
            materiales.getRecords();
            menu.dpContenedor.remove(editar);
            materiales.setVisible();
        } else {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL MATERIAL", "NO SE HA PODIDO MODIFICAR EL MATERIAL", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEliminar(int IDX) {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            if (g.addUpdateOrDelete("SP_ELIMINAR_MATERIAL", a)) {
                JOptionPane.showMessageDialog(null, "MATERIAL ELIMINADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                materiales.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL MATERIAL", "ERROR AL ELIMINAR EL MATERIAL", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL MATERIAL", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public final void getDepartamentos() {
        try {
            departamentos = new ArrayList<>();
            Item departamento = null;
            nuevo.Departamento.addItem("");
            editar.Departamento.addItem("");
            for (Iterator it = g.fill("SP_OBTENER_DEPARTAMENTOS").iterator(); it.hasNext();) {
                Object[] item = (Object[]) it.next();
                departamento = new Item(Integer.parseInt(String.valueOf(item[0])), String.valueOf(item[1]));
                departamentos.add(departamento);
                nuevo.Departamento.addItem(String.valueOf(item[1]));
                editar.Departamento.addItem(String.valueOf(item[1]));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER DEPARTAMENTOS", "ERROR AL OBTENER", JOptionPane.ERROR_MESSAGE);
            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }

    public final void getFamilias() {
        try {
            familias = new ArrayList<>();
            Item familia = null;
            nuevo.Familia.addItem("");
            editar.Familia.addItem("");
            for (Iterator it = g.fill("SP_OBTENER_FAMILIAS").iterator(); it.hasNext();) {
                Object[] item = (Object[]) it.next();
                familia = new Item(Integer.parseInt(String.valueOf(item[0])), String.valueOf(item[1]));
                familias.add(familia);
                nuevo.Familia.addItem(String.valueOf(item[1]));
                editar.Familia.addItem(String.valueOf(item[1]));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER FAMILIAS", "ERROR AL OBTENER", JOptionPane.ERROR_MESSAGE);
            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }

    public final void getUnidades() {
        try { 
            Item unidades = null;
            nuevo.UnidadCompra.addItem("");
            nuevo.UnidadConsumo.addItem("");
            editar.UnidadCompra.addItem("");
            editar.UnidadConsumo.addItem("");
            for (Iterator it = g.fill("SP_OBTENER_UNIDADES").iterator(); it.hasNext();) {
                Object[] item = (Object[]) it.next();
                unidades = new Item(Integer.parseInt(String.valueOf(item[0])), String.valueOf(item[1]));
                unidades_de_compra.add(unidades);
                unidades_de_consumo.add(unidades);
                nuevo.UnidadCompra.addItem(String.valueOf(item[1]));
                nuevo.UnidadConsumo.addItem(String.valueOf(item[1]));
                editar.UnidadCompra.addItem(String.valueOf(item[1]));
                editar.UnidadConsumo.addItem(String.valueOf(item[1]));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER UNIDADES", "ERROR AL OBTENER", JOptionPane.ERROR_MESSAGE);

            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }

    public int getID(ArrayList<Item> x, String selected_item) {

        int id = 0;
        if (!selected_item.equals("")) {
            for (Item o : x) {
                if (o.getDescription().equals(selected_item)) {
                    id = o.getID();
                    break;
                }
            }
        }
        return id;
    }

    /*
        HoldOn implementation for Java
     */
    final WaitLayerUI layerUI = new WaitLayerUI();
    JPanel pnl = new JPanel();
    Timer stopper;

    public void HoldOn(JPanel pnlContenedor) {

        this.pnl = pnlContenedor;
        JLayer<JPanel> jlayer = new JLayer<>(this.pnl, layerUI);
        stopper = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                layerUI.stop();
            }
        });
        stopper.setRepeats(false);
        jlayer.setSize(pnlContenedor.getSize());
        pnlContenedor.add(jlayer);
        layerUI.start();
        if (!stopper.isRunning()) {
            stopper.start();
        }
    }
}
