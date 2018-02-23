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
import application.third_party.ImageUtils;
import application.views.estilos.mdlEditar;
import application.views.estilos.mdlNuevo;
import application.views.vEstilos;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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
    ArrayList<Item> lineas = new ArrayList<>();
    ArrayList<Item> familias = new ArrayList<>();
    ArrayList<Item> series = new ArrayList<>();
    ArrayList<Item> hormas = new ArrayList<>();
    ArrayList<Item> maquilas = new ArrayList<>();
    ArrayList<Item> temporadas = new ArrayList<>();
    ArrayList<Item> tipo_estilos = new ArrayList<>();
    JFileChooser fc;

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
        AutoCompleteDecorator.decorate(this.nuevo.MaquilaPlantilla);
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
        nuevo.btnGuardar.addKeyListener(new KeyListener() {
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
                fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("images", "jpg", "gif", "png");
                fc.setFileFilter(filter);
                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                fc.setCurrentDirectory(new File(System.getProperty("user.home") + "/Pictures"));
                fc.setDialogTitle("SELECCIONE UNA IMAGEN");
                int file = fc.showOpenDialog(null);
                if (file == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    nuevo.Foto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(selectedFile.getAbsolutePath()).getScaledInstance(350, 350, Image.SCALE_SMOOTH)));
                    try {
                        bufi = ImageUtils.resizeImage(ImageIO.read(fc.getSelectedFile()), 1, 350, 350);
                    } catch (IOException ex) {
                        Logger.getLogger(CtrlEstilos.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
//        TextPrompt TP = new TextPrompt("CLAVE", nuevo.Clave);
//        TP.changeAlpha(0.75f);
//        TP.changeStyle(Font.ITALIC);
//        TP = new TextPrompt("DESPERDICIO", nuevo.Descripcion);
//        TP.changeAlpha(0.75f);
//        TP.changeStyle(Font.ITALIC);
//        TP = new TextPrompt("DESPERDICIO", nuevo.Desperdicio);
//        TP.changeAlpha(0.75f);
//        TP.changeStyle(Font.ITALIC);
//        TP = new TextPrompt("2018", nuevo.Ano);
//        TP.changeAlpha(0.75f);
//        TP.changeStyle(Font.ITALIC);
//        TP = new TextPrompt("PUNTO CENTRAL", nuevo.PuntoCentral);
//        TP.changeAlpha(0.75f);
//        TP.changeStyle(Font.ITALIC);
//        TP = new TextPrompt("HERRAMENTAL", nuevo.Herramental);
//        TP.changeAlpha(0.75f);
//        TP.changeStyle(Font.ITALIC);
//        TP = new TextPrompt("TIPO DE CONSTRUCCION", nuevo.TipoDeConstruccion);
//        TP.changeAlpha(0.75f);
//        TP.changeStyle(Font.ITALIC);

        /*INVOCAR METODOS QUE RELLENAN DATOS*/
        getLineas();
        getFamilias();
        getHormas();
        getMaquilas();
        getTemporadas();
        getTipoEstilo();

    }

    public void setVisible() {
        nuevo.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
        nuevo.setLocationRelativeTo(null);
        nuevo.setVisible(true);
    }

    BufferedImage bufi = null;
    File files_dir = new File("files");

    public void onGuardar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            a.add(getID(lineas,nuevo.Linea.getSelectedItem().toString()));
            a.add(nuevo.Clave.getText());
            a.add(nuevo.Descripcion.getText());
            a.add(getID(familias, nuevo.Familia.getSelectedItem().toString()));
            a.add(getID(series, nuevo.Serie.getSelectedItem().toString()));
            a.add(getID(series, nuevo.Serie.getSelectedItem().toString()));

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
            Date date = new Date();
            a.add(dateFormat.format(date));
            if (!files_dir.exists()) {
                files_dir.mkdir();
            }
            System.out.println(fc.getSelectedFile().getName());
            ImageIO.write(bufi, "png", new File("files/" + fc.getSelectedFile().getName() + ".png"));
            if (g.addUpdateOrDelete("SP_AGREGAR_ESTILO", a)) {
                JOptionPane.showMessageDialog(null, "ESTILO AGREGADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                nuevo.dispose();
                estilos.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AGREGAR EL ESTILO", "NO SE HA PODIDO AGREGAR EL ESTILO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO REGISTRAR EL ESTILO", "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(CtrlEstilos.class.getName()).log(Level.SEVERE, null, ex);
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
            lineas = new ArrayList<>();
            Item linea = null;
            nuevo.Linea.addItem("");
            for (Iterator it = g.fill("SP_OBTENER_LINEAS").iterator(); it.hasNext();) {
                Object[] item = (Object[]) it.next();
                linea = new Item(Integer.parseInt(String.valueOf(item[0])), String.valueOf(item[1]));
                lineas.add(linea);
                nuevo.Linea.addItem(String.valueOf(item[1]));
                editar.Linea.addItem(String.valueOf(item[1]));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER LINEAS", "ERROR AL OBTENER", JOptionPane.ERROR_MESSAGE);

            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }

    public final void getFamilias() {
        try {
            familias = new ArrayList<>();
            Item familia = null;
            nuevo.Familia.addItem("");
            for (Iterator it = g.fill("SP_OBTENER_FAMILIAS").iterator(); it.hasNext();) {
                Object[] item = (Object[]) it.next();
                familia = new Item(Integer.parseInt(String.valueOf(item[0])), String.valueOf(item[1]));
                familias.add(familia);
                nuevo.Familia.addItem(String.valueOf(item[1]));
                editar.Familia.addItem(String.valueOf(item[1]));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER FAMILIAS", "ERROR AL OBTENER", JOptionPane.ERROR_MESSAGE);

            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }

    public final void getSeries() {
        try {
            series = new ArrayList<>();
            Item serie = null;
            nuevo.Serie.addItem("");
            for (Iterator it = g.fill("SP_OBTENER_SERIES").iterator(); it.hasNext();) {
                Object[] item = (Object[]) it.next();
                serie = new Item(Integer.parseInt(String.valueOf(item[0])), String.valueOf(item[1]));
                series.add(serie);
                nuevo.Serie.addItem(String.valueOf(item[1]));
                editar.Serie.addItem(String.valueOf(item[1]));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER SERIES", "ERROR AL OBTENER", JOptionPane.ERROR_MESSAGE);

            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }

    public final void getHormas() {
        try {
            hormas = new ArrayList<>();
            Item horma = null;
            nuevo.Serie.addItem("");
            for (Iterator it = g.fill("SP_OBTENER_HORMAS").iterator(); it.hasNext();) {
                Object[] item = (Object[]) it.next();
                horma = new Item(Integer.parseInt(String.valueOf(item[0])), String.valueOf(item[1]));
                hormas.add(horma);
                nuevo.Horma.addItem(String.valueOf(item[1]));
                editar.Horma.addItem(String.valueOf(item[1]));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER HORMAS", "ERROR AL OBTENER", JOptionPane.ERROR_MESSAGE);

            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }

    public final void getMaquilas() {
        try {
            maquilas = new ArrayList<>();
            Item maquila = null;
            nuevo.Maquila.addItem("");
            for (Iterator it = g.fill("SP_OBTENER_MAQUILAS").iterator(); it.hasNext();) {
                Object[] item = (Object[]) it.next();
                maquila = new Item(Integer.parseInt(String.valueOf(item[0])), String.valueOf(item[1]));
                maquilas.add(maquila);
                nuevo.Maquila.addItem(String.valueOf(item[1]));
                editar.Maquila.addItem(String.valueOf(item[1]));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER MAQUILAS", "ERROR AL OBTENER", JOptionPane.ERROR_MESSAGE);

            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }

    public final void getTemporadas() {
        try {
            temporadas = new ArrayList<>();
            Item temporada = null;
            nuevo.Temporada.addItem("");
            for (Iterator it = g.fill("SP_OBTENER_TEMPORADAS").iterator(); it.hasNext();) {
                Object[] item = (Object[]) it.next();
                temporada = new Item(Integer.parseInt(String.valueOf(item[0])), String.valueOf(item[1]));
                temporadas.add(temporada);
                nuevo.Temporada.addItem(String.valueOf(item[1]));
                editar.Temporada.addItem(String.valueOf(item[1]));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER TEMPORADAS", "ERROR AL OBTENER", JOptionPane.ERROR_MESSAGE);

            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }

    public final void getTipoEstilo() {
        try {
            tipo_estilos = new ArrayList<>();
            Item tipo_estilo = null;
            nuevo.Tipo.addItem("");
            for (Iterator it = g.fill("SP_OBTENER_TIPOS_ESTILO").iterator(); it.hasNext();) {
                Object[] item = (Object[]) it.next();
                tipo_estilo = new Item(Integer.parseInt(String.valueOf(item[0])), String.valueOf(item[1]));
                tipo_estilos.add(tipo_estilo);
                nuevo.Tipo.addItem(String.valueOf(item[1]));
                editar.Tipo.addItem(String.valueOf(item[1]));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER TEMPORADAS", "ERROR AL OBTENER", JOptionPane.ERROR_MESSAGE);

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
