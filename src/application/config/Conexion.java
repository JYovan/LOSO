/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author Administrator Gio
 */
public final class Conexion {

    private static Connection conn;
    private static String header;
    private static String driver;
    private static String user;
    private static String pwd;
    private static int nmax = 0;// Numero de conexiones a la base de datos
    private static int time = 0;// Tiempo de espera
    //BasicDataSource 
    final BasicDataSource bds = new BasicDataSource();
    private static final Conexion cc = new Conexion();

    private Conexion() {
        try {
            loadSettings();
            //Incializa las propiedades de la conexion
            bds.setDriverClassName(driver);
            bds.setUsername(user);
            bds.setPassword(pwd);
            bds.setUrl(header);
            bds.setMaxActive(nmax);
            bds.setMaxIdle(time);
            conn = getConnectionPool().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,
                    null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public static void loadSettings() throws Exception {
        FileReader fr = null;
        Properties props = null;
        File cnxf = new File("Conecction.ini");
        try {
            if (cnxf.exists()) {
                fr = new FileReader("Conecction.ini");
                props = new Properties();
                props.load(fr);
                header = props.getProperty("header");
                driver = props.getProperty("driver");
                user = props.getProperty("usr");
                pwd = props.getProperty("pwd");
                nmax = Integer.parseInt(props.getProperty("NumOfCon"));
                time = Integer.parseInt(props.getProperty("time"));
            } else {
                if (!cnxf.exists()) {
                    try (PrintWriter pw = new PrintWriter(cnxf)) {
                        pw.println("header=");
                        pw.println("driver=");
                        pw.println("usr=");
                        pw.println("pwd=");
                        pw.println("NumOfCon=");
                        pw.println("time=");
                        pw.close();
                        System.out.println("File: Connection.ini has been created");
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Doesn't exist Conexion.ini.\n Check if the configuration file exist.", "Menssage of System", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
        }
    }

    public static ArrayList<Object> getPropiedades() {

        ArrayList<Object> a = null;
        try {
            loadSettings();
            a = new ArrayList<>();
            a.add(driver);
            a.add(header);
            a.add(user);
            a.add(pwd);
            a.add(nmax);
            a.add(time);
            return a;
        } catch (Exception e) {
            a.clear();
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
        }
        return null;
    }

    public Connection getConnection() {
        try {
            return conn;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }

    public static Conexion getCurrentInstance() {
        return cc;
    }

    // CON POOL DE CONEXIONES
    public DataSource getConnectionPool() {
        //Retorna la conexion actual
        return bds;
    }
}
