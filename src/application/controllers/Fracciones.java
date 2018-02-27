
package application.controllers;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.fracciones.CtrlFracciones;
import application.views.vFracciones;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class Fracciones {
    private static vFracciones vfracciones;
    DefaultTableModel dtm;
    Generic g;


    private TableRowSorter<TableModel> filtrador;
    
        public Fracciones(Generic g) {
        this.g = g;
        vfracciones = new vFracciones();
        vfracciones.btnNuevo.addActionListener((e) -> {
            (new CtrlFracciones(vfracciones, g, this)).setVisible();
        });
     
        vfracciones.btnEditar.addActionListener((e) -> {
            try {
                if (vfracciones.tblFracciones.getSelectedRow() >= 0) {
                    int ID = Integer.parseInt(vfracciones.tblFracciones.getValueAt(vfracciones.tblFracciones.getSelectedRow(), 0).toString());
                    (new CtrlFracciones(vfracciones, g, this)).onEditar(ID);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
                }
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "DEBE DE COLOCAR EL ID AL INICIO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }

        });
        vfracciones.btnEliminar.addActionListener((e) -> {
            if (vfracciones.tblFracciones.getSelectedRow() >= 0) {

                int i = JOptionPane.showConfirmDialog(null, "¿Estás Seguro?", "Confirmar Eliminar", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    int ID = Integer.parseInt(vfracciones.tblFracciones.getValueAt(vfracciones.tblFracciones.getSelectedRow(), 0).toString());
                    (new CtrlFracciones(vfracciones, g, this)).onEliminar(ID);
                }

            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }
        });
        vfracciones.btnRefrescar.addActionListener((e) -> {
            getRecords();
        });
        vfracciones.tblFracciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getClickCount()) {
                    case 2:
                        vfracciones.btnEditar.doClick();
                        break;
                }
            }
        });
        vfracciones.txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = vfracciones.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = vfracciones.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                JOptionPane.showMessageDialog(null, "changeUpdate en la tabla no sooportada");
            }
        });

        vfracciones.cmbTamano.addActionListener((e) -> {
            getRecords();
        });
        TextPrompt placeholder = new TextPrompt("BUSCA CLAVE, DESCRIPCIÓN", vfracciones.txtBusqueda);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);
        getRecords();
    }

    public void setVisible() {
        vfracciones.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/LS.png")));
        vfracciones.setLocationRelativeTo(null);
        vfracciones.setVisible(true);
    }

    public final void getRecords() {
        try {

            ArrayList<Object> o = new ArrayList<>();
            if (vfracciones.cmbTamano.getSelectedItem().toString().equals("TODOS")) {
                o.add(99999999);
            } else {
                o.add(vfracciones.cmbTamano.getSelectedItem().toString());
            }
            ArrayList<Object[][]> a = g.findByParams("SP_FRACCIONES", o);
            dtm = g.getModelFill(a.get(0), g.getDimensional(a.get(1)));
            vfracciones.tblFracciones.setModel(dtm);
            filtrador = new TableRowSorter<>(dtm);
            vfracciones.tblFracciones.setRowSorter(filtrador);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static vFracciones getInstance() {
        return vfracciones;
    }
    
}
