/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers.estilos;

import application.controllers.estilos.*;
import application.config.Generic;
import application.controllers.Estilos;
import application.helpers.Item;
import application.third_party.ImageUtils;
import application.third_party.WaitLayerUI;
import application.views.estilos.mdlIEditar;
import application.views.estilos.mdlINuevo;
import application.views.vEstilos;
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
import javax.swing.Timer;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Administrador
 */
public class CtrlEstilos {

    mdlINuevo nuevo;
    mdlIEditar editar;
    Generic g;
    Estilos estilos;
    vEstilos vestilos;
    int temp = 0;
    boolean tiene_foto = false;
    ArrayList<Item> lineas = new ArrayList<>();
    ArrayList<Item> familias = new ArrayList<>();
    ArrayList<Item> series = new ArrayList<>();
    ArrayList<Item> hormas = new ArrayList<>();
    ArrayList<Item> maquilas = new ArrayList<>();
    ArrayList<Item> temporadas = new ArrayList<>();
    ArrayList<Item> tipo_estilos = new ArrayList<>();
    JFileChooser fc;
    vMenu menu;

    public CtrlEstilos(JInternalFrame parent, Generic g, Estilos estilos, JFrame menu) {
        /*NO SE DEBE DE LLAMAR NADA SI NO SE DEFINEN ESTAS ASIGNACIONES*/
        this.menu = (vMenu) menu;
        nuevo = new mdlINuevo();
        editar = new mdlIEditar();
        this.vestilos = (vEstilos) parent;
        this.g = g;
        this.estilos = estilos;
        //Ayuda en captura combos nuevo estilo

        AutoCompleteDecorator.decorate(this.nuevo.Familia);
        AutoCompleteDecorator.decorate(this.nuevo.Serie);
        AutoCompleteDecorator.decorate(this.nuevo.Linea);
        AutoCompleteDecorator.decorate(this.nuevo.Horma);
        AutoCompleteDecorator.decorate(this.nuevo.Maquila);
        AutoCompleteDecorator.decorate(this.nuevo.Temporada);
        AutoCompleteDecorator.decorate(this.nuevo.Tipo);
        AutoCompleteDecorator.decorate(this.nuevo.MaquilaPlantilla);
        //Ayuda en captura combos editar estilo

        AutoCompleteDecorator.decorate(this.editar.Familia);
        AutoCompleteDecorator.decorate(this.editar.Serie);
        AutoCompleteDecorator.decorate(this.editar.Linea);
        AutoCompleteDecorator.decorate(this.editar.Horma);
        AutoCompleteDecorator.decorate(this.editar.Maquila);
        AutoCompleteDecorator.decorate(this.editar.Temporada);
        AutoCompleteDecorator.decorate(this.editar.Tipo);
        AutoCompleteDecorator.decorate(this.editar.MaquilaPlantilla);
        
        
        nuevo.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Estilos est = estilos;
                est.setVisible();

            }
        });
        editar.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                Estilos est = estilos;
                est.setVisible();
            }
        });
        
        /*NUEVO*/
        nuevo.Ano.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < '0' || e.getKeyChar() > '9' && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        nuevo.PuntoCentral.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < '0' || e.getKeyChar() > '9' && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
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
        editar.Ano.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < '0' || e.getKeyChar() > '9' && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        editar.PuntoCentral.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < '0' || e.getKeyChar() > '9' && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
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
       
        editar.Foto.addMouseListener(new MouseAdapter() {
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
                    tiene_foto = true;
                    File selectedFile = fc.getSelectedFile();
                    editar.Foto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(selectedFile.getAbsolutePath()).getScaledInstance(350, 350, Image.SCALE_SMOOTH)));
                    try {
                        bufi = ImageUtils.resizeImage(ImageIO.read(fc.getSelectedFile()), 1, 350, 350);
                    } catch (IOException ex) {
                        Logger.getLogger(CtrlEstilos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        /*PLACEHOLDERS*/

 /*INVOCAR METODOS QUE RELLENAN DATOS*/
    }

    public void setVisible() {
        if (!nuevo.isShowing()) {
            getLineas();
            getSeries();
            getFamilias();
            getHormas();
            getMaquilas();
            getTemporadas();
            getTipoEstilo();

            nuevo.Ano.setText("");
            nuevo.Clave.setText("");
            nuevo.Descripcion.setText("");
            nuevo.Desperdicio.setText("");
            nuevo.Herramental.setText("");
            nuevo.Notas.setText("");
            nuevo.PuntoCentral.setText("");
            nuevo.TipoDeConstruccion.setText("");
            nuevo.Liberado.setSelected(false);

            nuevo.Estatus.setSelectedIndex(0);
            nuevo.Familia.setSelectedIndex(0);
            nuevo.Horma.setSelectedIndex(0);
            nuevo.Linea.setSelectedIndex(0);
            nuevo.Maquila.setSelectedIndex(0);
            nuevo.MaquilaPlantilla.setSelectedIndex(0);
            nuevo.Serie.setSelectedIndex(0);
            nuevo.Temporada.setSelectedIndex(0);
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
        nuevo.Clave.requestFocus();
    }

    BufferedImage bufi = null;
    File files_dir = new File("files");

    public void onGuardar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            Object x = null;
            String ext = "";
            String Foto = "";
            if (bufi != null) {
                ext = FilenameUtils.getExtension(fc.getSelectedFile().getName());
                Foto = "files/" + nuevo.Clave.getText() + "." + ext;
            }

            if (!nuevo.Linea.getSelectedItem().toString().equals("") && !nuevo.Clave.getText().equals("")) {
                a.add(getID(lineas, nuevo.Linea.getSelectedItem().toString()));
                a.add((nuevo.Clave.getText().equals("") ? "" : nuevo.Clave.getText()));
                a.add(nuevo.Descripcion.getText());
                x = getID(familias, nuevo.Familia.getSelectedItem().toString());
                if (Integer.parseInt(String.valueOf(x)) != 0) {
                    a.add(x);
                } else {
                    a.add(null);
                }
                x = getID(series, nuevo.Serie.getSelectedItem().toString());
                if (Integer.parseInt(String.valueOf(x)) != 0) {
                    a.add(x);
                } else {
                    a.add(null);
                }
                x = getID(hormas, nuevo.Horma.getSelectedItem().toString());
                if (Integer.parseInt(String.valueOf(x)) != 0) {
                    a.add(x);
                } else {
                    a.add(null);
                }
                a.add(nuevo.Genero.getSelectedItem().toString());
                a.add(Foto);
                a.add(nuevo.Estatus.getSelectedItem().toString());
                a.add(nuevo.Desperdicio.getText());
                a.add(nuevo.Liberado.isSelected() ? 1 : 0);
                a.add(nuevo.Herramental.getText());
                x = getID(maquilas, nuevo.Maquila.getSelectedItem().toString());
                if (Integer.parseInt(String.valueOf(x)) != 0) {
                    a.add(x);
                } else {
                    a.add(null);
                }
                a.add(nuevo.Notas.getText());
                a.add(nuevo.Ano.getText().equals("") ? null : Integer.parseInt(nuevo.Ano.getText()));
                x = getID(temporadas, nuevo.Temporada.getSelectedItem().toString());
                if (Integer.parseInt(String.valueOf(x)) != 0) {
                    a.add(x);
                } else {
                    a.add(null);
                }
                a.add(nuevo.PuntoCentral.getText().equals("") ? null : Integer.parseInt(nuevo.PuntoCentral.getText()));
                x = getID(tipo_estilos, nuevo.Tipo.getSelectedItem().toString());
                if (Integer.parseInt(String.valueOf(x)) != 0) {
                    a.add(x);
                } else {
                    a.add(null);
                }
                a.add(nuevo.MaquilaPlantilla.getSelectedItem().toString());
                a.add(nuevo.TipoDeConstruccion.getText());
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
                Date date = new Date();
                a.add(dateFormat.format(date));
                if (!files_dir.exists()) {
                    files_dir.mkdir();
                }
                System.out.println(fc.getSelectedFile().getName());
                if (bufi != null) {
                    ImageIO.write(bufi, "png", new File(Foto));
                }

                if (g.addUpdateOrDelete("SP_AGREGAR_ESTILO", a)) {
                    JOptionPane.showMessageDialog(null, "ESTILO AGREGADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                    nuevo.dispose();
                    estilos.getRecords();
                    menu.dpContenedor.remove(nuevo);
                    estilos.setVisible();
                } else {
                    JOptionPane.showMessageDialog(null, "NO SE HA PODIDO AGREGAR EL ESTILO", "NO SE HA PODIDO AGREGAR EL ESTILO", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE ELEGIR UNA LINEA Y UNA CLAVE", "NO SE HA PODIDO AGREGAR EL ESTILO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO REGISTRAR EL ESTILO", "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(CtrlEstilos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onEditar(int IDX) {
        try {
            
            getLineas();
            getSeries();
            getFamilias();
            getHormas();
            getMaquilas();
            getTemporadas();
            getTipoEstilo();
            
            editar.Ano.setText("");
            editar.Clave.setText("");
            editar.Descripcion.setText("");
            editar.Desperdicio.setText("");
            editar.Herramental.setText("");
            editar.Notas.setText("");
            editar.PuntoCentral.setText("");
            editar.TipoDeConstruccion.setText("");
            editar.Liberado.setSelected(false);

            editar.Estatus.setSelectedIndex(0);
            editar.Familia.setSelectedIndex(0);
            editar.Horma.setSelectedIndex(0);
            editar.Linea.setSelectedIndex(0);
            editar.Maquila.setSelectedIndex(0);
            editar.MaquilaPlantilla.setSelectedIndex(0);
            editar.Serie.setSelectedIndex(0);
            editar.Temporada.setSelectedIndex(0);
            editar.Tipo.setSelectedIndex(0);
            

            
            

            bufi = null;
            temp = IDX;
            ArrayList<Object> a = new ArrayList<>();
            a.add(IDX);
            ArrayList<Object[][]> x = g.findByParams("SP_ESTILO_X_ID", a);
            Object[][] data = x.get(0);
            if (data[0][1] != null) {
                editar.Linea.getModel().setSelectedItem(data[0][1]);
            }
            editar.Clave.setText(String.valueOf(data[0][2]));
            editar.Descripcion.setText(String.valueOf(data[0][3]));
            if (data[0][4] != null) {
                editar.Familia.getModel().setSelectedItem(data[0][4]);
            }
            if (data[0][5] != null) {
                editar.Serie.getModel().setSelectedItem(data[0][5]);
            }
            if (data[0][6] != null) {
                editar.Horma.getModel().setSelectedItem(data[0][6]);
            }
            if (data[0][7] != null) {
                editar.Genero.getModel().setSelectedItem(data[0][7]);
            }
            editar.Foto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage((String.valueOf(data[0][8]))).getScaledInstance(350, 350, Image.SCALE_SMOOTH)));
            if (data[0][9] != null) {
                editar.Estatus.getModel().setSelectedItem(data[0][9]);
            }
            editar.Desperdicio.setText(String.valueOf(data[0][10]));
            if (String.valueOf(data[0][11]).equals("0") || String.valueOf(data[0][11]).equals("null")) {
                editar.Liberado.setSelected(false);
            } else {
                editar.Liberado.setSelected(true);
            }
            editar.Herramental.setText(String.valueOf(data[0][12]));
            editar.Maquila.getModel().setSelectedItem(String.valueOf(data[0][13]));
            editar.Notas.setText(String.valueOf(data[0][14]));
            editar.Ano.setText((String.valueOf(data[0][15]).equals("null")) ? "" : String.valueOf(data[0][15]));
            editar.Temporada.getModel().setSelectedItem(String.valueOf(data[0][16]));
            editar.PuntoCentral.setText((String.valueOf(data[0][17]).equals("null") ? "" : String.valueOf(data[0][17])));
            editar.Tipo.getModel().setSelectedItem(data[0][18]);
            editar.MaquilaPlantilla.getModel().setSelectedItem(String.valueOf(data[0][19]));
            editar.TipoDeConstruccion.setText(String.valueOf(data[0][20]));
            editar.Clave.requestFocus();
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
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO EDITAR EL ESTILO", "ERROR AL EDITAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onModificar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            Object x = null;
            String Foto = "";
            if (!editar.Linea.getSelectedItem().toString().equals("") && !editar.Clave.getText().equals("")) {
                a.add(temp);/*ID*/
                a.add(getID(lineas, editar.Linea.getSelectedItem().toString()));
                a.add((editar.Clave.getText().equals("") ? "" : editar.Clave.getText()));
                a.add(editar.Descripcion.getText());
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
                if (editar.Serie.getSelectedIndex() != -1) {
                    x = getID(series, editar.Serie.getSelectedItem().toString());
                    if (Integer.parseInt(String.valueOf(x)) != 0) {
                        a.add(x);
                    } else {
                        a.add(null);
                    }
                } else {
                    a.add(null);
                }
                if (editar.Horma.getSelectedIndex() != -1) {
                    x = getID(hormas, editar.Horma.getSelectedItem().toString());
                    if (Integer.parseInt(String.valueOf(x)) != 0) {
                        a.add(x);
                    } else {
                        a.add(null);
                    }
                } else {
                    a.add(null);
                }
                a.add(editar.Genero.getSelectedItem().toString());
                if (bufi != null) {
                    String ext = FilenameUtils.getExtension(fc.getSelectedFile().getName());
                    Foto = "files/" + editar.Clave.getText() + "." + ext;
                    a.add(Foto);
                    ImageIO.write(bufi, "png", new File(Foto));
                } else {
                    a.add(0);
                }
                a.add(editar.Estatus.getSelectedItem().toString());
                a.add(editar.Desperdicio.getText());
                a.add(editar.Liberado.isSelected() ? 1 : 0);
                a.add(editar.Herramental.getText());
                if (editar.Maquila.getSelectedIndex() != -1) {
                    x = getID(maquilas, editar.Maquila.getSelectedItem().toString());
                    if (Integer.parseInt(String.valueOf(x)) != 0) {
                        a.add(x);
                    } else {
                        a.add(null);
                    }
                } else {
                    a.add(null);
                }
                a.add(editar.Notas.getText());
                a.add(editar.Ano.getText().equals("") ? null : Integer.parseInt(editar.Ano.getText()));
                if (editar.Temporada.getSelectedIndex() != -1) {
                    x = getID(temporadas, editar.Temporada.getSelectedItem().toString());
                    if (Integer.parseInt(String.valueOf(x)) != 0) {
                        a.add(x);
                    } else {
                        a.add(null);
                    }
                } else {
                    a.add(null);
                }
                a.add(editar.PuntoCentral.getText().equals("") ? null : Integer.parseInt(editar.PuntoCentral.getText()));

                if (editar.Tipo.getSelectedIndex() != -1) {
                    x = getID(tipo_estilos, editar.Tipo.getSelectedItem().toString());
                    if (Integer.parseInt(String.valueOf(x)) != 0) {
                        a.add(x);
                    } else {
                        a.add(null);
                    }
                } else {
                    a.add(null);
                }
                a.add(editar.MaquilaPlantilla.getSelectedItem().toString());
                a.add(editar.TipoDeConstruccion.getText());
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
                    estilos.getRecords();
                    menu.dpContenedor.remove(editar);
                    estilos.setVisible();
                } else {
                    JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL ESTILO", "NO SE HA PODIDO MODIFICAR EL ESTILO", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE ELEGIR UNA LINEA Y UNA CLAVE", "NO SE HA PODIDO MODIFICAR EL ESTILO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL ESTILO\n" + e.getMessage(), "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "NO SE HA PODIDO MODIFICAR EL ESTILO\n" + ex.getMessage(), "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onEliminar(int IDX) {
        try {
//            HoldOn(vestilos.jPanel2);
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
            nuevo.Linea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));/*REINICIA EL MODELO*/
            editar.Linea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));/*REINICIA EL MODELO*/

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
            nuevo.Familia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));/*REINICIA EL MODELO*/
            editar.Familia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));/*REINICIA EL MODELO*/
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
            nuevo.Serie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));/*REINICIA EL MODELO*/
            editar.Serie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));/*REINICIA EL MODELO*/
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
            nuevo.Horma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));/*REINICIA EL MODELO*/
            editar.Horma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));/*REINICIA EL MODELO*/
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
            nuevo.Maquila.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));/*REINICIA EL MODELO*/
            editar.Maquila.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));/*REINICIA EL MODELO*/
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
            nuevo.Temporada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));/*REINICIA EL MODELO*/
            editar.Temporada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));/*REINICIA EL MODELO*/
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
            nuevo.Tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));/*REINICIA EL MODELO*/
            editar.Tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));/*REINICIA EL MODELO*/
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
