/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers.materialesxcombinacion;

import application.controllers.materialesxcombinacion.*;
import application.controllers.materialesxcombinacion.*;
import application.config.Generic;
import application.controllers.MaterialesXCombinacion;
import static application.controllers.MaterialesXCombinacion.vmaterialesxcombinacion;
import application.helpers.Item;
import application.third_party.ImageUtils;
import application.third_party.WaitLayerUI;
import application.views.materialesxcombinacion.mdlEditar;
import application.views.materialesxcombinacion.mdlNuevo;
import application.views.vMaterialesXCombinacion;
import application.views.vMenu;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLayer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.commons.io.FilenameUtils;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Administrador
 */
public class CtrlMaterialesXCombinacion {

    mdlNuevo nuevo;
    mdlEditar editar;
    Generic g;
    MaterialesXCombinacion materialesxcombinacion;
    vMaterialesXCombinacion vmaterialesxcombinacion;
    int temp = 0;
    boolean tiene_foto = false;
    ArrayList<Item> estilos = new ArrayList<>();
    ArrayList<Item> combinaciones = new ArrayList<>();
    JFileChooser fc;
    vMenu mnu;

    public CtrlMaterialesXCombinacion(JInternalFrame parent, Generic g, MaterialesXCombinacion materialesxcombinacion, JFrame menu) {
        /*NO SE DEBE DE LLAMAR NADA SI NO SE DEFINEN ESTAS ASIGNACIONES*/
        nuevo = new mdlNuevo();
        editar = new mdlEditar();
        this.vmaterialesxcombinacion = (vMaterialesXCombinacion) parent;
        this.g = g;
        this.materialesxcombinacion = materialesxcombinacion;
        this.mnu = (vMenu) menu;
        //Ayuda en captura combos nuevo estilo
        AutoCompleteDecorator.decorate(this.nuevo.Estilo);
        AutoCompleteDecorator.decorate(this.nuevo.Combinacion);
        //Ayuda en captura combos editar estilo
        AutoCompleteDecorator.decorate(this.editar.Estilo);
        AutoCompleteDecorator.decorate(this.editar.Combinacion);
        /*NUEVO*/
        nuevo.btnAgregar.addActionListener((e) -> {
            Float Consumo = Float.parseFloat(nuevo.Consumo.getValue().toString());
            if (Consumo > 0) {
                System.out.println("CONSUMO: " + Consumo);
                nuevo.Consumo.setValue(0);
            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE ESTABLECER UN CONSUMO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }
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

        /*EDITAR*/
        editar.btnGuardar.addActionListener((e) -> {
            onModificar();
        });
        editar.btnGuardar.addKeyListener(new KeyListener() {
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
        nuevo.BuscarMateriales.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = nuevo.BuscarMateriales.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = nuevo.BuscarMateriales.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                JOptionPane.showMessageDialog(null, "changeUpdate en la tabla Materiales no sooportada Linea 174");
            }
        });
        /*PLACEHOLDERS*/

 /*INVOCAR METODOS QUE RELLENAN DATOS*/
        getEstilos();
        getCombinaciones();

    }

    public void setVisible() {
        if (!nuevo.isShowing()) {
            mnu.dpContenedor.add(nuevo);
            Dimension desktopSize = mnu.dpContenedor.getSize();
            Dimension jInternalFrameSize = nuevo.getSize();
            nuevo.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            nuevo.setFrameIcon(null);
            nuevo.show();
            nuevo.toFront();
            getMateriales();
        }
    }

    BufferedImage bufi = null;
    File files_dir = new File("files");

    public void onGuardar() {
        try {
            ArrayList<Object> encabezado = new ArrayList<>();
            ArrayList<Object> detalle = new ArrayList<>();
            Object x = null;
            /*ENCABEZADO*/
            if (nuevo.Estilo.getSelectedIndex() != -1
                    && nuevo.Combinacion.getSelectedIndex() != -1
                    && !nuevo.Estilo.getSelectedItem().toString().equals("")
                    && !nuevo.Combinacion.getSelectedItem().toString().equals("")) {
                encabezado.add(getID(estilos, nuevo.Estilo.getSelectedItem().toString()));
                encabezado.add(getID(combinaciones, nuevo.Combinacion.getSelectedItem().toString()));
                encabezado.add(0);/*PROVISIONAL*/
                String fechatiempo = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                encabezado.add(fechatiempo);
                ArrayList<Object[][]> data = g.addUpdateOrDeleteAndGetLastId("SP_AGREGAR_MATERIALES_X_COMBINACION", encabezado);
                /*FIN ENCABEZADO*/
                if (data.size() > 0) {
                    Object[][] ID = data.get(0);/*OBTENER EL ID DEL ENCABEZADO*/
                    System.out.println("ID " + String.valueOf(ID[0][0]));
                    /*DETALLE*/
                    System.out.println("* * * DETALLE * * *");
                    for (int i = 0; i < nuevo.tblMaterialesAgregados.getRowCount(); i++) {
                        detalle.add(Integer.parseInt(String.valueOf(ID[0][0])));
                        detalle.add(Integer.parseInt(nuevo.tblMaterialesAgregados.getValueAt(i, 0).toString()));
                        detalle.add(Integer.parseInt(nuevo.tblMaterialesAgregados.getValueAt(i, 1).toString()));
                        fechatiempo = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                        detalle.add(Integer.parseInt(nuevo.tblMaterialesAgregados.getValueAt(i, 2).toString()));
                        detalle.add(fechatiempo);
                        System.out.println("ID: " + nuevo.tblMaterialesAgregados.getValueAt(i, 0).toString() + "\tMATERIAL: " + nuevo.tblMaterialesAgregados.getValueAt(i, 1).toString());
                    }
                    System.out.println("* * * FIN DETALLE * * *");

                    /*FIN DETALLE*/
                    JOptionPane.showMessageDialog(null, "MATERIAL POR COMBINACION AGREGADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                    nuevo.dispose();
                    materialesxcombinacion.getRecords();
                } else {
                    JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AGREGAR EL MATERIAL POR COMBINACION", "NO SE HA PODIDO AGREGAR EL ESTILO", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN ESTILO Y UNA COMBINACIÓN", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL AGREGAR MXC " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void onEditar(int IDX) {
        try {
            bufi = null;
            temp = IDX;
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            ArrayList<Object[][]> x = g.findByParams("SP_ESTILO_X_ID", a);
            Object[][] data = x.get(0);
//            if (data[0][1] != null) {
//                editar.Linea.getModel().setSelectedItem(data[0][1]);
//            }
//            editar.Clave.setText(String.valueOf(data[0][2]));
//            editar.Descripcion.setText(String.valueOf(data[0][3]));
//            if (data[0][4] != null) {
//                editar.Familia.getModel().setSelectedItem(data[0][4]);
//            }
//            if (data[0][5] != null) {
//                editar.Serie.getModel().setSelectedItem(data[0][5]);
//            }
//            if (data[0][6] != null) {
//                editar.Horma.getModel().setSelectedItem(data[0][6]);
//            }
//            if (data[0][7] != null) {
//                editar.Genero.getModel().setSelectedItem(data[0][7]);
//            }
//            editar.Foto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage((String.valueOf(data[0][8]))).getScaledInstance(350, 350, Image.SCALE_SMOOTH)));
//            if (data[0][9] != null) {
//                editar.Estatus.getModel().setSelectedItem(data[0][9]);
//            }
//            editar.Desperdicio.setText(String.valueOf(data[0][10]));
//            if (String.valueOf(data[0][11]).equals("0") || String.valueOf(data[0][11]).equals("null")) {
//                editar.Liberado.setSelected(false);
//            } else {
//                editar.Liberado.setSelected(true);
//            }
//            editar.Herramental.setText(String.valueOf(data[0][12]));
//            editar.Maquila.getModel().setSelectedItem(String.valueOf(data[0][13]));
//            editar.Notas.setText(String.valueOf(data[0][14]));
//            editar.Ano.setText((String.valueOf(data[0][15]).equals("null")) ? "" : String.valueOf(data[0][15]));
//            editar.Temporada.getModel().setSelectedItem(String.valueOf(data[0][16]));
//            editar.PuntoCentral.setText((String.valueOf(data[0][17]).equals("null") ? "" : String.valueOf(data[0][17])));
//            editar.Tipo.getModel().setSelectedItem(data[0][18]);
//            editar.MaquilaPlantilla.getModel().setSelectedItem(String.valueOf(data[0][19]));
//            editar.TipoDeConstruccion.setText(String.valueOf(data[0][20]));
//
//            editar.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
//            editar.setLocationRelativeTo(null);
            editar.setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR EL ESTILO", "ERROR AL EDITAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onModificar() {
//        try {
        ArrayList<Object> a = new ArrayList<>();
        Object x = null;
        String Foto = "";
//            if (!editar.Linea.getSelectedItem().toString().equals("") && !editar.Clave.getText().equals("")) {
//                a.add(temp);/*ID*/
//                a.add(getID(lineas, editar.Linea.getSelectedItem().toString()));
//                a.add((editar.Clave.getText().equals("") ? "" : editar.Clave.getText()));
//                a.add(editar.Descripcion.getText());
//                if (editar.Familia.getSelectedIndex() != -1) {
//                    x = getID(familias, editar.Familia.getSelectedItem().toString());
//                    if (Integer.parseInt(String.valueOf(x)) != 0) {
//                        a.add(x);
//                    } else {
//                        a.add(null);
//                    }
//                } else {
//                    a.add(null);
//                }
//                if (editar.Serie.getSelectedIndex() != -1) {
//                    x = getID(series, editar.Serie.getSelectedItem().toString());
//                    if (Integer.parseInt(String.valueOf(x)) != 0) {
//                        a.add(x);
//                    } else {
//                        a.add(null);
//                    }
//                } else {
//                    a.add(null);
//                }
//                if (editar.Horma.getSelectedIndex() != -1) {
//                    x = getID(hormas, editar.Horma.getSelectedItem().toString());
//                    if (Integer.parseInt(String.valueOf(x)) != 0) {
//                        a.add(x);
//                    } else {
//                        a.add(null);
//                    }
//                } else {
//                    a.add(null);
//                }
//                a.add(editar.Genero.getSelectedItem().toString());
//                if (bufi != null) {
//                    String ext = FilenameUtils.getExtension(fc.getSelectedFile().getName());
//                    Foto = "files/" + editar.Clave.getText() + "." + ext;
//                    a.add(Foto);
//                    ImageIO.write(bufi, "png", new File(Foto));
//                } else {
//                    a.add(0);
//                }
//                a.add(editar.Estatus.getSelectedItem().toString());
//                a.add(editar.Desperdicio.getText());
//                a.add(editar.Liberado.isSelected() ? 1 : 0);
//                a.add(editar.Herramental.getText());
//                if (editar.Maquila.getSelectedIndex() != -1) {
//                    x = getID(maquilas, editar.Maquila.getSelectedItem().toString());
//                    if (Integer.parseInt(String.valueOf(x)) != 0) {
//                        a.add(x);
//                    } else {
//                        a.add(null);
//                    }
//                } else {
//                    a.add(null);
//                }
//                a.add(editar.Notas.getText());
//                a.add(editar.Ano.getText().equals("") ? null : Integer.parseInt(editar.Ano.getText()));
//                if (editar.Temporada.getSelectedIndex() != -1) {
//                    x = getID(estilos, editar.Temporada.getSelectedItem().toString());
//                    if (Integer.parseInt(String.valueOf(x)) != 0) {
//                        a.add(x);
//                    } else {
//                        a.add(null);
//                    }
//                } else {
//                    a.add(null);
//                }
//                a.add(editar.PuntoCentral.getText().equals("") ? null : Integer.parseInt(editar.PuntoCentral.getText()));
//
//                if (editar.Tipo.getSelectedIndex() != -1) {
//                    x = getID(tipo_materialesxcombinacion, editar.Tipo.getSelectedItem().toString());
//                    if (Integer.parseInt(String.valueOf(x)) != 0) {
//                        a.add(x);
//                    } else {
//                        a.add(null);
//                    }
//                } else {
//                    a.add(null);
//                }
//                a.add(editar.MaquilaPlantilla.getSelectedItem().toString());
//                a.add(editar.TipoDeConstruccion.getText());
        if (!files_dir.exists()) {
            files_dir.mkdir();
        }
//                puede servir para testear
//                for (int i = 0; i < a.size(); i++) {
//                    System.out.println(i + ".-" + String.valueOf(a.get(i)));
//                }
        if (g.addUpdateOrDelete("SP_MODIFICAR_ESTILO", a)) {
            JOptionPane.showMessageDialog(null, "ESTILO MODIFICADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
            editar.dispose();
            materialesxcombinacion.getRecords();
        } else {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL ESTILO", "NO SE HA PODIDO MODIFICAR EL ESTILO", JOptionPane.ERROR_MESSAGE);
        }
//            } else {
//                JOptionPane.showMessageDialog(null, "DEBE DE ELEGIR UNA LINEA Y UNA CLAVE", "NO SE HA PODIDO MODIFICAR EL ESTILO", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (HeadlessException e) {
//            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL ESTILO\n" + e.getMessage(), "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
//        } catch (IOException ex) {
//            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL ESTILO\n" + ex.getMessage(), "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
//        }
    }

    public void onEliminar(int IDX) {
        try {
//            HoldOn(vmaterialesxcombinacion.jPanel2);
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            if (g.addUpdateOrDelete("SP_ELIMINAR_ESTILO", a)) {
                JOptionPane.showMessageDialog(null, "ESTILO ELIMINADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                materialesxcombinacion.getRecords();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL ESTILO", "ERROR AL ELIMINAR EL ESTILO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO ELIMINAR EL ESTILO", "ERROR AL ELIMINAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public final void getEstilos() {
        try {
            estilos = new ArrayList<>();
            Item estilo = null;
            nuevo.Estilo.addItem("");
            editar.Estilo.addItem("");
            for (Iterator it = g.fill("SP_OBTENER_ESTILOS_MXC").iterator(); it.hasNext();) {
                Object[] item = (Object[]) it.next();
                estilo = new Item(Integer.parseInt(String.valueOf(item[0])), String.valueOf(item[1]));
                estilos.add(estilo);
                nuevo.Estilo.addItem(String.valueOf(item[1]));
                editar.Estilo.addItem(String.valueOf(item[1]));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER LOS ESTILOS", "ERROR AL OBTENER", JOptionPane.ERROR_MESSAGE);

            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }

    public final void getCombinaciones() {
        try {
            combinaciones = new ArrayList<>();
            Item combinacion = null;
            nuevo.Combinacion.addItem("");
            editar.Combinacion.addItem("");
            for (Iterator it = g.fill("SP_OBTENER_COMBINACIONES_MXC").iterator(); it.hasNext();) {
                Object[] item = (Object[]) it.next();
                combinacion = new Item(Integer.parseInt(String.valueOf(item[0])), String.valueOf(item[1]));
                combinaciones.add(combinacion);
                nuevo.Combinacion.addItem(String.valueOf(item[1]));
                editar.Combinacion.addItem(String.valueOf(item[1]));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HAN PODIDO OBTENER LAS COMBINACIONES", "ERROR AL OBTENER", JOptionPane.ERROR_MESSAGE);

            System.out.println("ERROR\n" + e.getMessage());
            e.printStackTrace();/*INDICA LA LINEA DONDE OCURRE EL PROBLEMA*/
        }
    }

    DefaultTableModel dtm;
    private TableRowSorter<TableModel> filtrador;

    public final void getMateriales() {
        try {
            ArrayList<Object> o = new ArrayList<>();
            o.add(99999999);
            ArrayList<Object[][]> a = g.findByParams("SP_OBTENER_MATERIALES_MXC", o);
            dtm = g.getModelFill(a.get(0), g.getDimensional(a.get(1)));
            nuevo.tblMateriales.setModel(dtm);
            filtrador = new TableRowSorter<>(dtm);
            nuevo.tblMateriales.setRowSorter(filtrador);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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
}
