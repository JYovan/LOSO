package application.controllers.catalogos;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.Catalogos;
import application.views.catalogos.mdlEditar;
import application.views.catalogos.mdlNuevo;
import application.views.vCatalogos;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CtrlCatalogos {

    mdlNuevo nuevo;
    mdlEditar editar;
    Generic g;
    Catalogos catalogos;
    vCatalogos vcatalogos;
    int temp = 0;
    String TipoCatalogo;

    public CtrlCatalogos(JFrame parent, Generic g, Catalogos catalogos, String TipoCatalogo) {
        
     
        this.TipoCatalogo = TipoCatalogo;
        nuevo = new application.views.catalogos.mdlNuevo(parent, true);
        editar = new application.views.catalogos.mdlEditar(parent, true);
        this.vcatalogos = (vCatalogos) parent;
        this.g = g;
        this.catalogos = catalogos;
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

        nuevo.SValue.addKeyListener(new KeyListener() {
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
        nuevo.Special.addKeyListener(new KeyListener() {
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
        editar.SValue.addKeyListener(new KeyListener() {
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
        editar.Special.addKeyListener(new KeyListener() {
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

        /*PLACEHOLDERS*/
        TextPrompt placeholders = new TextPrompt("CLAVE", nuevo.SValue);
        placeholders.changeAlpha(0.75f);
        placeholders.changeStyle(Font.BOLD);
        placeholders = new TextPrompt("DESCRIPCIÓN", nuevo.Valor_Text);
        placeholders.changeAlpha(0.75f);
        placeholders.changeStyle(Font.BOLD);
        placeholders = new TextPrompt("VALOR", nuevo.Valor_Num);
        placeholders.changeAlpha(0.75f);
        placeholders.changeStyle(Font.BOLD);
        placeholders = new TextPrompt("CAMPO ESPECIAL", nuevo.Special);
        placeholders.changeAlpha(0.75f);
        placeholders.changeStyle(Font.BOLD);
    }

    public void setVisible() {
        nuevo.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/96/icons8_Idea_96px.png")));
        nuevo.setLocationRelativeTo(null);
        nuevo.setVisible(true);
    }

    public void onGuardar() {
        try {
            ArrayList<Object> a = new ArrayList<>();
            
            System.out.println(this.TipoCatalogo);
            
            a.add(this.TipoCatalogo);
            a.add(nuevo.SValue.getText());
            a.add(nuevo.Special.getText());
            a.add(nuevo.Valor_Num.getText());
            a.add(nuevo.Valor_Text.getText());

            if (!nuevo.SValue.getText().equals("") && g.addUpdateOrDelete("SP_AGREGAR_CATALOGO", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO AGREGADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                nuevo.dispose();
                catalogos.getRecords();
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
            ArrayList<Object[][]> catalogo = g.findByParams("SP_CATALOGO_X_ID", a);
            Object[][] data = catalogo.get(0);
            editar.SValue.setText(String.valueOf((data[0][0] != null) ? data[0][0] : ""));
            editar.Valor_Text.setText(String.valueOf((data[0][1] != null) ? data[0][1] : ""));
            editar.Valor_Num.setText(String.valueOf((data[0][2] != null) ? data[0][2] : ""));
            editar.Special.setText(String.valueOf((data[0][3] != null) ? data[0][3] : ""));
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
            a.add((editar.SValue.getText() != null) ? editar.SValue.getText() : "");
            a.add((editar.Valor_Num.getText() != null && editar.Valor_Num.getText().equals("")) ? editar.Valor_Num.getText() : "0.0");
            a.add((editar.Valor_Text.getText() != null) ? editar.Valor_Text.getText() : "");
            a.add((editar.Special.getText() != null) ? editar.Special.getText() : "");
            a.add(editar.cmbEstatus.getSelectedItem().toString());

            if (!editar.SValue.getText().equals("") && g.addUpdateOrDelete("SP_MODIFICAR_CATALOGO", a)) {
                JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO", "INFORMACIÓN DEL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
                editar.dispose();
                catalogos.getRecords();
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
