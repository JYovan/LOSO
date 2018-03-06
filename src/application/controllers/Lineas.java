package application.controllers;

import application.config.Generic;
import application.config.TextPrompt;
import application.controllers.lineas.CtrlLineas;
import application.views.vLineas;
import application.views.vLineas;
import application.views.vMenu;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;

/**
 *
 * @author Christian
 */
public class Lineas {

    private static vLineas vlineas;
    DefaultTableModel dtm;
    Generic g;
    JasperDesign jd;
    JRDesignQuery jq;
    JasperReport report;
    JasperPrint print;
    JDialog viewer;
    JasperViewer jv;
    vMenu menu;
    CtrlLineas lin;

    private TableRowSorter<TableModel> filtrador;

    public Lineas(Generic g, JFrame parent) {
        this.g = g;
        vlineas = new vLineas();
        this.menu = (vMenu) parent;
        lin = new CtrlLineas(vlineas, g, this, menu);
        vlineas.btnNuevo.addActionListener((e) -> {
            vlineas.dispose();
            menu.dpContenedor.remove(vlineas);
            lin.setVisible();
        });
        vlineas.btnExportar.addActionListener((e) -> {

        });
        vlineas.btnEditar.addActionListener((e) -> {
            try {
                if (vlineas.tblLineas.getSelectedRow() >= 0) {
                    int ID = Integer.parseInt(vlineas.tblLineas.getValueAt(vlineas.tblLineas.getSelectedRow(), 0).toString());
                    vlineas.dispose();
                    menu.dpContenedor.remove(vlineas);
                    lin.onEditar(ID);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
                }
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "DEBE DE COLOCAR EL ID AL INICIO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }

        });
        vlineas.btnEliminar.addActionListener((e) -> {
            if (vlineas.tblLineas.getSelectedRow() >= 0) {

                int i = JOptionPane.showConfirmDialog(null, "¿Estás Seguro?", "Confirmar Eliminar", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    int ID = Integer.parseInt(vlineas.tblLineas.getValueAt(vlineas.tblLineas.getSelectedRow(), 0).toString());
                    lin.onEliminar(ID);
                }

            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR UN REGISTRO", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
            }
        });
        vlineas.btnRefrescar.addActionListener((e) -> {
            getRecords();
        });
        vlineas.tblLineas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getClickCount()) {
                    case 2:
                        vlineas.btnEditar.doClick();
                        break;
                }
            }
        });
        vlineas.txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = vlineas.txtBusqueda.getText();
                if (text.trim().length() == 0) {
                    filtrador.setRowFilter(null);
                } else {
                    filtrador.setRowFilter(RowFilter.regexFilter(text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = vlineas.txtBusqueda.getText();
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

        vlineas.cmbTamano.addActionListener((e) -> {
            getRecords();
        });
        TextPrompt placeholder = new TextPrompt("BUSCA LINEA, DESCRIPCIÓN", vlineas.txtBusqueda);
        placeholder.changeAlpha(0.75f);
        placeholder.changeStyle(Font.ITALIC);
        
    }

    public void setVisible() {
        if (vlineas.isShowing()) {
            //mensaje de que está abierto si se desea
        } else {
            menu.dpContenedor.add(vlineas);

            Dimension desktopSize = menu.dpContenedor.getSize();
            Dimension jInternalFrameSize = vlineas.getSize();
            vlineas.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            vlineas.setFrameIcon(null);
            vlineas.show();
        }

    }

    public final void getRecords() {
        try {

            ArrayList<Object> o = new ArrayList<>();
            if (vlineas.cmbTamano.getSelectedItem().toString().equals("TODOS")) {
                o.add(99999999);
            } else {
                o.add(vlineas.cmbTamano.getSelectedItem().toString());
            }
            ArrayList<Object[][]> a = g.findByParams("SP_LINEAS", o);
            dtm = g.getModelFill(a.get(0), g.getDimensional(a.get(1)));
            vlineas.tblLineas.setModel(dtm);
            filtrador = new TableRowSorter<>(dtm);
            vlineas.tblLineas.setRowSorter(filtrador);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

    /*NO EDITAR ESTA PARTE*/
    public static vLineas getInstance() {
        return vlineas;
    }

}
