/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers.materialesxcombinacion;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.MaterialesXCombinacion;
import application.helpers.Item;
import application.views.materialesxcombinacion.mdlEditar;
import application.views.materialesxcombinacion.mdlNuevo;
import application.views.vMaterialesXCombinacion;
import application.views.vMenu;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.IntStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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
        AutoCompleteDecorator.decorate(this.nuevo.Tipo);
        //Ayuda en captura combos editar estilo
        AutoCompleteDecorator.decorate(this.editar.Estilo);
        AutoCompleteDecorator.decorate(this.editar.Combinacion);
        AutoCompleteDecorator.decorate(this.editar.Tipo);
        /*NUEVO*/
        nuevo.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                MaterialesXCombinacion mxc = materialesxcombinacion;
                mxc.setVisible();
            }
        });
        nuevo.btnRefrescar.addActionListener((e) -> {
            getMateriales();
        });
        nuevo.btnEliminar.addActionListener((e) -> {
            int row = nuevo.tblMaterialesAgregados.getSelectedRow();
            if (row != -1) {
                DefaultTableModel model = (DefaultTableModel) nuevo.tblMaterialesAgregados.getModel();
                IntStream.of(nuevo.tblMaterialesAgregados.getSelectedRows()).boxed().sorted(Collections.reverseOrder()).forEach(model::removeRow);
            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN MATERIAL AGREGADO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }
        });
        nuevo.btnAgregar.addActionListener((e) -> {
            int row = nuevo.tblMateriales.getSelectedRow();
            if (row != -1) {
                Float Consumo = Float.parseFloat(nuevo.Consumo.getValue().toString());
                if (Consumo > 0) {
                    int ID = Integer.parseInt(nuevo.tblMateriales.getModel().getValueAt(nuevo.tblMateriales.getSelectedRow(), 0).toString());
                    DefaultTableModel model = (DefaultTableModel) nuevo.tblMaterialesAgregados.getModel();
                    model.addRow(new Object[]{
                        ID/*ID*/,
                        nuevo.tblMateriales.getValueAt(nuevo.tblMateriales.getSelectedRow(), 0).toString()/*MATERIAL*/,
                        nuevo.tblMateriales.getValueAt(nuevo.tblMateriales.getSelectedRow(), 1).toString()/*U.M.*/,
                        nuevo.tblMateriales.getValueAt(nuevo.tblMateriales.getSelectedRow(), 2).toString()/*PRECIO*/,
                        String.valueOf(nuevo.Consumo.getValue()),
                        nuevo.Tipo.getSelectedItem().toString()});
                    nuevo.Consumo.setValue(0);
                } else {
                    JOptionPane.showMessageDialog(null, "DEBE DE ESTABLECER UN CONSUMO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN MATERIAL", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
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
        editar.btnRefrescar.addActionListener((e) -> {
            getMateriales();
        });
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
        editar.BuscarMateriales.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = editar.BuscarMateriales.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = editar.BuscarMateriales.getText();
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
        TextPrompt pnuevo = new TextPrompt("BUSCA POR MATERIAL...", nuevo.BuscarMateriales);
        pnuevo.changeAlpha(0.75f);
        pnuevo.changeStyle(Font.ITALIC);
        TextPrompt peditar = new TextPrompt("BUSCA POR MATERIAL...", editar.BuscarMateriales);
        peditar.changeAlpha(0.75f);
        peditar.changeStyle(Font.ITALIC);

        /*INVOCAR METODOS QUE RELLENAN DATOS*/
    }

    public void onReiniciarValores() {
        nuevo.tblMaterialesAgregados.removeRowSelectionInterval(0, 99999);
        editar.tblMaterialesAgregados.removeRowSelectionInterval(0, 99999);
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
            getEstilos();
            getCombinaciones();
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
//                        @IDX INT, @Material INT, @Consumo FLOAT, @Tipo INT,@Registro VARCHAR(25)
                        detalle.add(Integer.parseInt(String.valueOf(ID[0][0])));/*ID ENCABEZADO*/
                        int IDM = Integer.parseInt(nuevo.tblMaterialesAgregados.getModel().getValueAt(i, 0).toString());
                        detalle.add(IDM);/*ID MATERIAL*/
                        detalle.add(Float.parseFloat(nuevo.tblMaterialesAgregados.getValueAt(i, 3).toString()));/*CONSUMO*/
                        fechatiempo = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                        detalle.add(nuevo.tblMaterialesAgregados.getValueAt(i, 2).toString().equals("DIR") ? 1 : 2);
                        detalle.add(fechatiempo);/*REGISTRO*/
                        if (g.addUpdateOrDelete("SP_AGREGAR_MATERIALES_X_COMBINACION_DETALLE", detalle)) {
                            detalle.clear();
                            JOptionPane.showMessageDialog(null, "MATERIAL POR COMBINACION AGREGADO\n", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                        }
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
            ArrayList<Object[][]> x = g.findByParams("SP_MATERIALES_X_COMBINACION_X_ID", a);
            Object[][] data = x.get(0);
            editar.setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR EL ESTILO", "ERROR AL EDITAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onModificar() {
        ArrayList<Object> a = new ArrayList<>();
        if (g.addUpdateOrDelete("SP_MODIFICAR_ESTILO", a)) {
            JOptionPane.showMessageDialog(null, "ESTILO MODIFICADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
            editar.dispose();
            materialesxcombinacion.getRecords();
        } else {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL ESTILO", "NO SE HA PODIDO MODIFICAR EL ESTILO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEliminar(int IDX) {
        try {
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

    public void getEstilos() {
        try {
            nuevo.Estilo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));/*REINICIA EL MODELO*/
            editar.Estilo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));/*REINICIA EL MODELO*/
            estilos = new ArrayList<>();
            Item estilo = null;
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
            nuevo.Combinacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));/*REINICIA EL MODELO*/
            editar.Combinacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));/*REINICIA EL MODELO*/

            g.fill("SP_OBTENER_COMBINACIONES_MXC").forEach((t) -> {
                System.out.println(t);
            });
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
            editar.tblMateriales.setRowSorter(filtrador);

            editar.tblMateriales.removeColumn(editar.tblMateriales.getColumnModel().getColumn(0));
            editar.tblMaterialesAgregados.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "ID(OCULTO)", "MATERIAL", "U.M", "PRECIO", "CONSUMO", "TIPO"
                    }
            ));
            editar.tblMaterialesAgregados.removeColumn(nuevo.tblMaterialesAgregados.getColumnModel().getColumn(0));

            nuevo.tblMateriales.removeColumn(nuevo.tblMateriales.getColumnModel().getColumn(0));
            nuevo.tblMaterialesAgregados.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "ID(OCULTO)", "MATERIAL", "U.M", "PRECIO", "CONSUMO", "TIPO"
                    }
            ));
            nuevo.tblMaterialesAgregados.removeColumn(nuevo.tblMaterialesAgregados.getColumnModel().getColumn(0));
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
