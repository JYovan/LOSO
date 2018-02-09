package application.third_party;

import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 *
 * @author Administrator
 */
public class Resources {

    boolean ctrlPressed = false, cPressed = false, xPressed = false;

    PopupMenu popupmenu;
    MenuItem mnuiExit;
    MenuItem mnuiMinimize;
    MenuItem mnuiRestore;
    MenuItem mnuiTitulo;
    SystemTray sysTray;
    TrayIcon iconSysTray = null;

    public Image getIconImage(String r) {
        Image imagenvalue = Toolkit.getDefaultToolkit().getImage(
                ClassLoader.getSystemResource(r));
        return imagenvalue;
    }

    public void setOnlyNumbers(KeyEvent ke) {
        if (!(ke.getKeyChar() >= 48 && ke.getKeyChar() <= 57 || ke.getKeyChar() == 8)) {
            ke.consume();
            setBeep();
        }
    }

    public void Letters(KeyEvent k) {
        if (!(k.getKeyCode() >= 65 && k.getKeyCode() <= 91)) {
            k.consume();
            setBeep();
        }
    }

    public void verificarcaracternumerico(KeyEvent evt) {
        if (!(evt.getKeyChar() >= 48 && evt.getKeyChar() <= 57 || evt
                .getKeyChar() == 8)) {
            evt.consume();
            setBeep();
        }
    }

    // NUMEROS Y PUNTO
    public void verificarcaracternumericoconpunto(KeyEvent evt) {
        if (!(evt.getKeyChar() == 46 || evt.getKeyChar() >= 48
                && evt.getKeyChar() <= 57 || evt.getKeyChar() == 8)) {
            evt.consume();
            setBeep();
        }
    }

    public void setTamChar(KeyEvent k, JTextField txt, int id) {
        if (txt.getText().length() == 15 && id == 1) {
            k.consume();
        } else if (txt.getText().length() == 5 && id == 2) {
            k.consume();
        } else if (txt.getText().length() == 15 && id == 3) {
            k.consume();
        } else if (txt.getText().length() == 200 && id == 4) {
            k.consume();
        } else if (txt.getText().length() == 450 && id == 5) {
            k.consume();
        } else if (txt.getText().length() == 2 && id == 6) {
            k.consume();
        }
    }

    public void setTaChar(KeyEvent k, JTextArea txt, int id) {
        if (txt.getText().length() == 20 && id == 1) {
            k.consume();
        } else if (txt.getText().length() == 5 && id == 2) {
            k.consume();
        } else if (txt.getText().length() == 15 && id == 3) {
            k.consume();
        } else if (txt.getText().length() == 200 && id == 4) {
            k.consume();
        } else if (txt.getText().length() == 450 && id == 5) {
            k.consume();
        }
    }

    public void setOnlyNumbersAndLetters(KeyEvent ke) {
        if (!(ke.getKeyChar() >= 'a' && ke.getKeyChar() <= 'z'
                || ke.getKeyChar() >= 'A' && ke.getKeyChar() <= 'Z'
                || ke.getKeyChar() == '_' || ke.getKeyChar() == 'ñ'
                || ke.getKeyChar() == 'Ñ' || ke.getKeyChar() == ' '
                || ke.getKeyChar() == 'á' || ke.getKeyChar() == 'Á'
                || ke.getKeyChar() == 'é' || ke.getKeyChar() == 'É'
                || ke.getKeyChar() == 'í' || ke.getKeyChar() == 'Í'
                || ke.getKeyChar() == 'ó' || ke.getKeyChar() == 'Ó'
                || ke.getKeyChar() == 'ú' || ke.getKeyChar() == 'Ú'
                || ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')) {
            ke.consume();
            setBeep();
        } else {

        }
    }

    public void setOnlyLetters(KeyEvent ke) {
        if (!(ke.getKeyChar() >= 'a' && ke.getKeyChar() <= 'z'
                || ke.getKeyChar() >= 'A' && ke.getKeyChar() <= 'Z'
                || ke.getKeyChar() == '_' || ke.getKeyChar() == 'ñ'
                || ke.getKeyChar() == 'á' || ke.getKeyChar() == 'Á'
                || ke.getKeyChar() == 'é' || ke.getKeyChar() == 'É'
                || ke.getKeyChar() == 'í' || ke.getKeyChar() == 'Í'
                || ke.getKeyChar() == 'ó' || ke.getKeyChar() == 'Ó'
                || ke.getKeyChar() == 'ú' || ke.getKeyChar() == 'Ú'
                || ke.getKeyChar() == 'Ñ' || ke.getKeyChar() == ' ')) {
            ke.consume();
            setBeep();
        }
    }

    public void setSizeTxt(int tam) {
        if (tam > 45) {
            setBeep();
        }
    }

    Pattern p;
    Matcher regex;
    int nob = 0;

    public boolean validateLetters(ArrayList<Object> o) {
        for (nob = 1; nob < o.size() - 1; nob++) {
            if (!((String) o.get(nob)).equals("")) {
                p = Pattern.compile("^[a-zA-Z ]{3,44}$");
                regex = p.matcher((String) o.get(nob));
                if (!regex.matches()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void limitTxt(KeyEvent ke, JTextField txt, int limite) {
        if (txt.getText().length() == limite) {
            ke.consume();
            setBeep();
        }

    }

    public boolean validateNumbers(Object o) {
        p = Pattern.compile("^[0-9]{3,5}$");
        regex = p.matcher((String) o);
        return regex.matches();
    }

    public void setBeep() {
        Toolkit.getDefaultToolkit().beep();
    }

    public void setGHint(JTextField tx, String text) {
        if (tx.getText().equals(text)) {
            tx.setText("");
            tx.setForeground(Color.black);
        }
    }

    private int idc = 0;

    public void setLHint(JTextField tx, int id) {
        if (tx.getText().equals("")) {
            switch (id) {
                case 1: {
                    tx.setForeground(Color.GRAY);
                    tx.setText("Escriba un Codigo Postal...");
                    break;
                }
                case 2: {
                    tx.setForeground(Color.GRAY);
                    tx.setText("Escriba una Calle...");
                    break;
                }
                case 3: {
                    tx.setForeground(Color.GRAY);
                    tx.setText("Escriba una Colonia...");
                    break;
                }
                case 4: {
                    tx.setForeground(Color.GRAY);
                    tx.setText("Escriba un Estado...");
                    break;
                }
                case 5: {
                    tx.setForeground(Color.GRAY);
                    tx.setText("Escriba un Municipio...");
                    break;
                }
                case 6: {
                    tx.setForeground(Color.GRAY);
                    tx.setText("Escriba una Sucursal...");
                    break;
                }
                case 7: {
                    tx.setForeground(Color.GRAY);
                    tx.setText("Escriba un Numero...");
                    break;
                }
                case 8: {
                    tx.setForeground(Color.GRAY);
                    tx.setText("Escriba un Nombre...");
                    break;
                }
                case 9: {
                    tx.setForeground(Color.GRAY);
                    tx.setText("Escriba Su Primer Apellido...");
                    break;
                }
                case 10: {
                    tx.setForeground(Color.GRAY);
                    tx.setText("Escriba Su Segundo Apellido...");
                    break;
                }
                case 11: {
                    tx.setForeground(Color.GRAY);
                    tx.setText("Escriba un Telefono...");
                    break;

                }
            }
        }
    }

    public void disablePasteAction(JComponent[] components) {
        for (JComponent campo : components) {
            InputMap map = campo.getInputMap();
            map.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK),
                    "null");
            map.put(KeyStroke
                    .getKeyStroke(KeyEvent.VK_INSERT, Event.SHIFT_MASK), "null");
        }

    }

    public void disableCopyAction(Component[] components) {
        for (Component campo : components) {
            if (campo instanceof JComponent) {
                InputMap map = ((JComponent) campo).getInputMap();
                map.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK),
                        "null");
            }
        }
    }

    /* Validar Ctrl+V, Ctrl+X, Ctrl */
    public void blockCtrlVR(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_V:
                cPressed = false;
                break;
            case KeyEvent.VK_CONTROL:
                ctrlPressed = false;
                break;
        }
        if (ctrlPressed && cPressed || ctrlPressed && xPressed) {
            JOptionPane
                    .showMessageDialog(
                            null,
                            "No puede estar pegando o cortando texto en ninguna de las cajas.",
                            "Zhuus 1.0 - Alerta", JOptionPane.WARNING_MESSAGE);
            evt.consume();
        }
    }

    public void blockCtrlV(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_V:
                cPressed = true;
                break;
            case KeyEvent.VK_CONTROL:
                ctrlPressed = true;
                break;
        }
        if (ctrlPressed && cPressed || ctrlPressed && xPressed) {
            evt.consume();
        }
    }

    /* Block Ctrl+V */
    public void keyPCtrlPlusV(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_V
                && e.getKeyCode() == KeyEvent.VK_CONTROL) {
            e.consume();
        }
    }

    public void keyTCtrlPlusV(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_V
                && e.getKeyCode() == KeyEvent.VK_CONTROL) {
            e.consume();
        }
    }

    /**
     * @return the idc
     */
    public int getIdc() {
        return idc;
    }

    /**
     * @param idc the idc to set
     */
    public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getFechaActual() {
        Date fecha = new Date();
        String formato = "dd/MM/YYYY";
        java.text.SimpleDateFormat xx = new java.text.SimpleDateFormat(formato);
        return xx.format(fecha);
    }

    public String getFechaActualImg() {
        Date fecha = new Date();
        String formato = "dd-MM-YYYY";
        java.text.SimpleDateFormat xx = new java.text.SimpleDateFormat(formato);
        return xx.format(fecha);
    }

    public String getHoraActual() {
        Date hora = new Date();
        String formato = "hh:mm:ss a";
        java.text.SimpleDateFormat xx = new java.text.SimpleDateFormat(formato);
        return xx.format(hora);
    }

    public String getReloj() {
        Date hora = new Date();
        String formato = "hh_mm_ss a";
        java.text.SimpleDateFormat xx = new java.text.SimpleDateFormat(formato);
        return xx.format(hora);
    }

    public void setOnlyNumbersAndPoint(KeyEvent ke) {
        if (!(ke.getKeyChar() >= 48 && ke.getKeyChar() <= 57
                || ke.getKeyChar() == 8 || ke.getKeyChar() == 46)) {
            ke.consume();
            setBeep();
        }
    }

    private String setComaForBigNumber(String str) {
        String strs = "";
        int i = str.length();
        while (i >= 0) {
            char c = str.charAt(i);
            if (str.length() % 2 == 0) {

            }
        }
        return "";
    }

    private void setWithOutComaForBigNumber(String str) {

    }

    public void showUM(ArrayList<Object> o) {
        Toolkit.getDefaultToolkit().beep();
        // PanelGlassGaussian glass = new PanelGlassGaussian((JFrame) o.get(0),
        // 5);
        // ((JFrame) o.get(0)).setGlassPane(glass);
        // ((JFrame) o.get(0)).getGlassPane().setVisible(true);
        // o.add(new IntUltraMessage(o));
        // CtrlUMessage ctrlum = new CtrlUMessage(o);
        // ctrlum.setVisible();
        // ((JFrame) o.get(0)).getGlassPane().setVisible(false);
        JOptionPane.showMessageDialog(null, (String) o.get(3), "Zhuus 1.0 - "
                + (String) o.get(2), JOptionPane.INFORMATION_MESSAGE);
    }

    public void toSysTray(final JFrame jf) {
        Image icon = null;
        if (!SystemTray.isSupported()) {
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            return;
        }
        jf.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                super.windowClosing(we); // To change body of generated methods,
                // choose Tools | Templates.
                jf.setVisible(false);
                iconSysTray.displayMessage(
                        "Se oculto el programa en la area de notificación",
                        "Haga clic derecho sobre icono para ver mas opciones.",
                        TrayIcon.MessageType.INFO);
            }
        });
        try {
            jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

            sysTray = SystemTray.getSystemTray();
            icon = Toolkit.getDefaultToolkit().getImage(
                    ClassLoader.getSystemResource("media/LS32X32.png"));

            MouseListener mouseOcultar = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == 1 && e.getClickCount() == 2) {
                        jf.setVisible(true);
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // System.out.println("Icono del System Tray - Mouse entered!");
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // System.out.println("Icono del System Tray - Mouse exited!");
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    // System.out.println("Icono del System Tray - Mouse pressed!");
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    // System.out.println("Icono del System Tray - Mouse released!");
                }
            };
            ActionListener listenerExit = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        System.exit(0);
                    }
                }
            };

            ActionListener listenerMinimize = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        jf.setVisible(false);
                        iconSysTray
                                .displayMessage(
                                        "Se Oculto el programa en la area de notificacion",
                                        "Haga clic derecho sobre icono para ver mas opciones.",
                                        TrayIcon.MessageType.INFO);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            };

            ActionListener listenerRestore = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        jf.setVisible(true);
                        jf.setState(JFrame.NORMAL);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            };

            popupmenu = new PopupMenu();
            mnuiTitulo = new MenuItem("Calzado Lobo Solo v.1.0.0");
            popupmenu.add(mnuiTitulo);
            mnuiRestore = new MenuItem("Restaurar");
            mnuiRestore.addActionListener(listenerRestore);
            popupmenu.add(mnuiRestore);

            mnuiMinimize = new MenuItem("Ocultar");
            mnuiMinimize.addActionListener(listenerMinimize);
            popupmenu.add(mnuiMinimize);

            // mnuiExit = new MenuItem("Salir");
            // mnuiExit.addActionListener(listenerExit);
            // popupmenu.add(mnuiExit);
            iconSysTray = new TrayIcon(icon, "Calzado Lobo Solo v.1.0.0", popupmenu);
            iconSysTray.setImageAutoSize(true);
            iconSysTray.addMouseListener(mouseOcultar);
            sysTray.add(iconSysTray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getaWithAcent() {
        return "\u00e1";
    }

    public String geteWithAcent() {
        return "\u00e9";
    }

    public String getiWithAcent() {
        return "\u00ed";
    }

    public String getoWithAcent() {
        return "\u00f3";
    }

    public String getuWithAcent() {
        return "\u00fa";
    }

    public String getntildeWithAcent() {
        return "\u00f1";
    }

    public String getCopyRigth() {
        return "\u00a9";
    }
    /*
     Mas codigos unicode aqui:
     http://unicode-table.com/es/#latin-1-supplement
     */
    /*
     Construct	Description
     .	Any character (may or may not match line terminators)
     \d	A digit: [0-9]
     \D	A non-digit: [^0-9]
     \s	A whitespace character: [ \t\n\x0B\f\r]
     \S	A non-whitespace character: [^\s]
     \w	A word character: [a-zA-Z_0-9]
     \W	A non-word character: [^\w]
     */

    public boolean compileNumber(String str) {
        return str.matches(".*\\d.*");
    }

    public boolean compileNoNumber(String str) {
        return str.matches(".*\\D.*");
    }

    public boolean compileNumberAndNoNumber(String str) {
        return str.matches(".*\\d\\D.*");
    }

    public BigDecimal getBigDecimal(String str) {
        return new BigDecimal(str);
    }

//    public String getDateWithCustomFormat(String current_format, String my_format, String my_date) {
//        try {
//            DateFormat dffrom = new SimpleDateFormat(current_format);
//            DateFormat dfto = new SimpleDateFormat(my_format);
//            Date femision = dffrom.parse(my_date);
//            String my_custom_date = dfto.format(femision);
//            return my_custom_date;
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "No hemos podido generar la fecha.\n"+e.getMessage());
//        }
//        return my_date;
//    }
//    
    public String getDateWithCustomFormat(String current_format, String my_format, String my_date) {
        try {
            DateFormat dffrom = new SimpleDateFormat(current_format);
            DateFormat dfto = new SimpleDateFormat(my_format);
            Date femision = dffrom.parse(my_date);
            String my_custom_date = dfto.format(femision);
            return my_custom_date;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No hemos podido generar la fecha.\n" + e.getMessage());
        }
        return my_date;
    }

}
