/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers.estilos;

import application.controllers.estilos.*;
import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.Estilos;
import application.helpers.Item;
import application.views.estilos.mdlEditar;
import application.views.estilos.mdlNuevo;
import application.views.vEstilos;
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
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Administrador
 */
public class CtrlEstilos {

    mdlNuevo nuevo;
    mdlEditar editar;
    Generic g;
    Estilos estilos;
    vEstilos vestilos;
    int temp = 0;
    ArrayList<Item> lineas, familias, series = new ArrayList<>();

    public CtrlEstilos(JFrame parent, Generic g, Estilos estilos) {
        /*NO SE DEBE DE LLAMAR NADA SI NO SE DEFINEN ESTAS ASIGNACIONES*/
        nuevo = new mdlNuevo(parent, true);
        editar = new mdlEditar(parent, true);
        this.vestilos = (vEstilos) parent;
        this.g = g;
        this.estilos = estilos;
        AutoCompleteDecorator.decorate(this.nuevo.Linea);
        AutoCompleteDecorator.decorate(this.nuevo.Familia);
        AutoCompleteDecorator.decorate(this.nuevo.Serie);
        AutoCompleteDecorator.decorate(this.nuevo.Horma);
        AutoCompleteDecorator.decorate(this.nuevo.Maquila);
        AutoCompleteDecorator.decorate(this.nuevo.Temporada);
        AutoCompleteDecorator.decorate(this.nuevo.Tipo);
        AutoCompleteDecorator.decorate(this.nuevo.Linea);
        /*NUEVO*/
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
        nuevo.Clave.addKeyListener(new KeyListener() {
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
        nuevo.Foto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("SELECCIONE UNA IMAGEN");
                int file = fc.showOpenDialog(null);
                if (file == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    nuevo.Foto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(selectedFile.getAbsolutePath()).getScaledInstance(350, 350, Image.SCALE_SMOOTH)));
                }
            }

        });

        /*EDITAR*/
        editar.Clave.addKeyListener(new KeyListener() {
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
        editar.btnGuardar.addActionListener((e) -> {
            onModificar();
        });

        /*PLACEHOLDERS*/
        TextPrompt TP = new TextPrompt("CLAVE", nuevo.Clave);
        TP.changeAlpha(0.75f);
        TP.changeStyle(Font.ITALIC);
        TP = new TextPrompt("DESPERDICIO", nuevo.Descripcion);
        TP.changeAlpha(0.75f);
        TP.changeStyle(Font.ITALIC);
        TP = new TextPrompt("DESPERDICIO", nuevo.Desperdicio);
        TP.changeAlpha(0.75f);
        TP.changeStyle(Font.ITALIC);
        TP = new TextPrompt("2018", nuevo.Ano);
        TP.changeAlpha(0.75f);
        TP.changeStyle(Font.ITALIC);
        TP = new TextPrompt("PUNTO CENTRAL", nuevo.PuntoCentral);
        TP.changeAlpha(0.75f);
        TP.changeStyle(Font.ITALIC);
        TP = new TextPrompt("HERRAMENTAL", nuevo.Herramental);
        TP.changeAlpha(0.75f);
        TP.changeStyle(Font.ITALIC);

        /*INVOCAR METODOS QUE RELLENAN DATOS*/
        getLineas();
        getFamilias();

    }

    public void setVisible() {
        nuevo.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
        nuevo.setLocationRelativeTo(null);
        nuevo.setVisible(true);
    }

    public void onGuardar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(nuevo.Linea.getSelectedItem().toString());
            a.add(nuevo.Clave.getText());
            a.add(nuevo.Descripcion.getText());
            a.add(nuevo.Descripcion.getText());
            int Familia = 0;
//            for (Item modulo : modulos) {
//                if (modulo.getDescription().equals(nuevo.Modulo.getSelectedItem().toString())) {
//                    System.out.println("ID : " + modulo.getID());
//                }
//            }

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
            Date date = new Date();
            a.add(dateFormat.format(date));
            if (g.addUpdateOrDelete("SP_AGREGAR_ESTILO", a)) {
                File files = new File("C:\\Directory2\\Sub2\\Sub-Sub2");
                if (!files.exists()) {
                    if (files.mkdirs()) {
                        System.out.println("Multiple directories are created!");
                    } else {
                        System.out.println("Failed to create multiple directories!");
                    }
                }
                JOptionPane.showMessageDialog(null, "ESTILO AGREGADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                nuevo.dispose();
                estilos.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AGREGAR EL ESTILO", "NO SE HA PODIDO AGREGAR EL ESTILO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO REGISTRAR EL ESTILO", "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEditar(int IDX) {
        try {
            temp = IDX;
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            ArrayList<Object[][]> x = g.findByParams("SP_ESTILO_X_ID", a);
            Object[][] data = x.get(0);
            editar.Linea.getModel().setSelectedItem(data[0][1]);
//            editar.Modulo.getModel().setSelectedItem(data[0][2]);
//            editar.Ver.setSelected((data[0][3] != null) ? (String.valueOf(data[0][3]).equals("1")) : false);
//            editar.Crear.setSelected((data[0][4] != null) ? (String.valueOf(data[0][4]).equals("1")) : false);
//            editar.Modificar.setSelected((data[0][5] != null) ? (String.valueOf(data[0][5]).equals("1")) : false);
//            editar.Eliminar.setSelected((data[0][6] != null) ? (String.valueOf(data[0][6]).equals("1")) : false);
//            editar.Consultar.setSelected((data[0][7] != null) ? (String.valueOf(data[0][7]).equals("1")) : false);
//            editar.Reportes.setSelected((data[0][8] != null) ? (String.valueOf(data[0][8]).equals("1")) : false);
//            editar.Buscar.setSelected((data[0][9] != null) ? (String.valueOf(data[0][9]).equals("1")) : false);
            editar.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
            editar.setLocationRelativeTo(null);
            editar.setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR EL ESTILO", "ERROR AL EDITAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onModificar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(temp);/*ID*/
//            a.add(editar.Ver.isSelected() ? 1 : 0);
//            a.add(editar.Crear.isSelected() ? 1 : 0);
//            a.add(editar.Modificar.isSelected() ? 1 : 0);
//            a.add(editar.Eliminar.isSelected() ? 1 : 0);
//            a.add(editar.Consultar.isSelected() ? 1 : 0);
//            a.add(editar.Reportes.isSelected() ? 1 : 0);
//            a.add(editar.Buscar.isSelected() ? 1 : 0);
            if (g.addUpdateOrDelete("SP_MODIFICAR_ESTILO", a)) {
                JOptionPane.showMessageDialog(null, "ESTILO MODIFICADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                editar.dispose();
                estilos.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL ESTILO", "ERROR AL MODIFICAR EL ESTILO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL ESTILO", "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEliminar(int IDX) {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            if (g.addUpdateOrDelete("SP_ELIMINAR_ESTILO", a)) {
                JOptionPane.showMessageDialog(null, "ESTILO ELIMINADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                estilos.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL ESTILO", "ERROR AL ELIMINAR EL ESTILO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL ESTILO", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public final void getLineas() {
        try {
            for (Iterator it = g.fill("SP_OBTENER_LINEAS").iterator(); it.hasNext();) {
                Object[] item = (Object[]) it.next();
                System.out.println(String.valueOf(item[0] + ":" + item[1]));
                lineas.add(new Item(Integer.parseInt(String.valueOf(item[0])), String.valueOf(item[1])));
                nuevo.Linea.addItem(String.valueOf(item[1]));
                editar.Linea.addItem(String.valueOf(item[1]));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER FAMILIAS", "ERROR AL OBTENER", JOptionPane.ERROR_MESSAGE);

            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }

    public final void getFamilias() {
        try {
            for (Iterator it = g.fill("SP_OBTENER_FAMILIAS").iterator(); it.hasNext();) {
                Object[] item = (Object[]) it.next();
                System.out.println(String.valueOf(item[0] + ":" + item[1]));
                familias.add(new Item(Integer.parseInt(String.valueOf(item[0])), String.valueOf(item[1])));
                nuevo.Familia.addItem(String.valueOf(item[1]));
                editar.Familia.addItem(String.valueOf(item[1]));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER FAMILIAS", "ERROR AL OBTENER", JOptionPane.ERROR_MESSAGE);

            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }
}
