package application.config;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class Generic {

    Conexion c;
    PreparedStatement ps;
    ResultSet rs;
    ResultSetMetaData rsmd;
    Connection cs;

    public Generic() {
        c = Conexion.getCurrentInstance();
        cs = c.getConnection();
    }

    /**
     * @author Giovanni
     * @param stored Nombre del stored procedure
     * @return Regresa un ArrayList de objetos bidimensionales en caso de ser
     * exitoso, usar el metodo getDimensional para obtener las columnas del
     * index 1
     * @since v1.0
     * @see Conexion
     *
     */
    public ArrayList<Object[][]> findAll(String stored) {
        Object[][] data = new Object[0][0];
        ArrayList<Object[][]> obj = new ArrayList<>();
        Object[][] cols = new Object[0][0];
        try {
            ps = c.getConnection().prepareCall("CALL " + stored + "();");
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            int col = 0, coldb = 1;
            if (rs.last()) {
                data = new Object[rs.getRow()][rsmd.getColumnCount()];
                rs.beforeFirst();
                while (rs.next()) {
                    for (int filas = 0; filas < rsmd.getColumnCount(); filas++) {
                        data[col][filas] = rs.getObject(coldb);
                        coldb++;
                    }
                    col++;
                    coldb = 1;
                }
            }
            coldb = 1;
            cols = new Object[rsmd.getColumnCount()][rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                cols[0][i] = rsmd.getColumnLabel(coldb);
                coldb++;
            }
            obj = new ArrayList<>();
            obj.add(data);//index 0
            obj.add(cols);//index 1  
            rs.close();
            ps.close();
            return obj;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    /**
     * @author Giovanni
     * @param stored Nombre del stored procedure
     * @param o Pametros para el stored procedure
     * @return Regresa un ArrayList de objetos bidimensionales en caso de ser
     * exitoso, usar el metodo getDimensional para obtener las columnas del
     * index 1
     * @since v1.0
     * @see Conexion
     */
    public ArrayList<Object[][]> findByParams(String stored, ArrayList<Object> o) {
        Object[][] data = null;
        ArrayList<Object[][]> obj = null;
        Object[][] cols = null;
        int j = 0;
        try {
            stored = "CALL " + stored + "(";
            for (int i = 1; i <= o.size(); i++) {
                if (i == o.size()) {
                    stored += "?";
                } else {
                    stored += "?,";
                }
            }
            stored += ");";
            ps = c.getConnection().prepareCall(stored);
            for (int nal = 1; nal <= o.size(); nal++) {
                ps.setObject(nal, o.get(j));
                j++;
            }
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            int col = 0, coldb = 1;
            if (rs.last()) {
                data = new Object[rs.getRow()][rsmd.getColumnCount()];
                rs.beforeFirst();
                while (rs.next()) {
                    for (int filas = 0; filas < rsmd.getColumnCount(); filas++) {
                        data[col][filas] = rs.getObject(coldb);
                        coldb++;
                    }
                    col++;
                    coldb = 1;
                }
            }
            coldb = 1;
            cols = new Object[rsmd.getColumnCount()][rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                cols[0][i] = rsmd.getColumnLabel(coldb);
                coldb++;
            }
            obj = new ArrayList<>();
            obj.add(data);//index 0
            obj.add(cols);//index 1   
            rs.close();
            ps.close();
            return obj;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public Object[] getOnlyOneData(String sp) {
        Object[] obj = null;
        try {
            int j = 0;
            String stored = "CALL " + sp + "();";
            ps = c.getConnection().prepareCall(stored);
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            if (rs.last()) {
                rs.beforeFirst();
                j = 1;
                while (rs.next()) {
                    obj = new Object[rsmd.getColumnCount()];
                    for (int i = 0; i < rsmd.getColumnCount(); i++) {
                        obj[i] = rs.getObject(j);// ID
                        j++;
                    }
                }
                if (obj != null) {
                    return obj;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "No es posible obtener el dato principal.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * @author Giovanni
     * @param sp Nombre del stored procedure
     * @param al Pametros para el stored procedure
     * @return Regresa un booleano en caso de ser exitoso
     * @since v1.0
     * @see Conexion
     */
    public boolean addUpdateOrDelete(String sp, ArrayList<Object> al) {
        try {
            int j = 0;
            String stored = "CALL " + sp;
            stored += "(";
            for (int i = 1; i <= al.size(); i++) {
                if (i == al.size()) {
                    stored += "?";
                } else {
                    stored += "?,";
                }
            }
            stored += ");";
            ps = c.getConnection().prepareCall(stored);
            for (int nal = 1; nal <= al.size(); nal++) {
                ps.setObject(nal, al.get(j));
                j++;
            }
            ps.execute();
            ps.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @author Giovanni
     * @param sp Nombre del stored procedure
     * @param al Pametros para el stored procedure
     * @return Regresa un booleano en caso de ser exitoso
     * @since v1.0
     * @see Conexion
     */
    public ArrayList<Object> addUpdateOrDeleteAndGetLastId(String sp, ArrayList<Object> al) {

        ArrayList<Object> obj = null;
        try {
            int j;
            String stored = "CALL " + sp;
            stored += "(";
            for (int i = 1; i <= al.size(); i++) {
                if (i == al.size()) {
                    stored += "?";
                } else {
                    stored += "?,";
                }
            }
            stored += ");";
            ps = c.getConnection().prepareCall(stored);
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            if (rs.last()) {
                rs.beforeFirst();
                j = 1;
                while (rs.next()) {
                    obj = new ArrayList<>();
                    for (int i = 0; i < rsmd.getColumnCount(); i++) {
                        obj.add(rs.getObject(j));// ID
                        j++;
                    }
                }
                if (obj != null) {
                    return obj;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "No es posible obtener el dato principal.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException | HeadlessException e) {
            e.printStackTrace();
        }
        return null;
    }
//Metodo para validar y obtener la informacion de un usuario en el sistema

    /**
     * @author Giovanni
     * @param sp Nombre del stored procedure
     * @param o Datos a verificar en la base de datos
     * @return Retorna los valores clave en caso de ser exitoso
     * @since v1.0
     * @see Conexion
     */
    public Object[] checkDataByParams(String sp, ArrayList<Object> o) {
        Object[] obj = null;
        try {
            int j = 0;
            String stored = "CALL " + sp;
            stored += "(";
            for (int i = 1; i <= o.size(); i++) {
                if (i == o.size()) {
                    stored += "?";
                } else {
                    stored += "?,";
                }
            }
            stored += ");";
            ps = c.getConnection().prepareCall(stored);
            for (int nal = 1; nal <= o.size(); nal++) {
                ps.setObject(nal, o.get(j));
                j++;
            }
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            o.clear();
            if (rs.last()) {
                rs.beforeFirst();
                j = 1;
                while (rs.next()) {
                    obj = new Object[rsmd.getColumnCount()];
                    for (int i = 0; i < rsmd.getColumnCount(); i++) {
                        obj[i] = rs.getObject(j);// ID
                        j++;
                    }
                }
                if (obj != null) {
                    return obj;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "El empleado no puede iniciar "
                            + "sesion en esta sucursal.",
                            "SIGESUM 1.0 - Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * @author Giovanni
     * @param obj Variable bidimensional a leer
     * @return Retorna un String Bidimensional con los datos encontrados para
     * rellenar
     * @since v1.0
     * @see Conexion
     */
    public Object[] getDimensional(Object[][] obj) {
        Object str[] = new Object[obj.length];
        try {
            for (int i = 0; i < obj.length; i++) {
                str[i] = String.valueOf(obj[0][i]);
            }
            return str;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * @author Giovanni
     * @param stored Nombre del stored procedure
     * @return Retorna un ArrayList con los datos encontrados para rellenar
     * @since v1.0
     * @see Conexion
     */
    public ArrayList fill(String stored) {
        ArrayList<Object> obj = new ArrayList<>();
        try {
            int j = 1;
            ps = c.getConnection().prepareCall("CALL " + stored + "();");
            rs = ps.executeQuery();
            if (rs.last()) {
                rs.beforeFirst();
                while (rs.next()) {
                    obj.add(rs.getObject(1));
                }
            }
            rs.close();
            ps.close();
            return obj;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * @author Giovanni
     * @param stored Nombre del stored procedure
     * @param criterios Valor a buscar
     * @return Retorna un ArrayList segun el criterio
     * @since v1.0
     * @see Conexion
     *
     */
    public ArrayList fillByParams(String stored, ArrayList<Object> criterios) {
        ArrayList<Object> obj = new ArrayList<>();
        int j = 0;
        try {
            stored = "CALL " + stored + "(";
            for (int i = 1; i <= criterios.size(); i++) {
                if (i == criterios.size()) {
                    stored += "?";
                } else {
                    stored += "?,";
                }
            }
            stored += ");";
            ps = c.getConnection().prepareCall(stored);
            for (int nal = 1; nal <= criterios.size(); nal++) {
                ps.setObject(nal, criterios.get(j));
                j++;
            }
            rs = ps.executeQuery();
            if (rs.last()) {
                rs.beforeFirst();
                while (rs.next()) {
                    obj.add(rs.getObject(1));
                }
            }
            rs.close();
            ps.close();
            return obj;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * @author Giovanni
     * @param data Objeto[][]
     * @param col Objeto[] previamente formateado con el metodo getDimensional
     * @return Retorna un DefaultTableModel con celdas no editables
     * @since v1.0
     *
     */
    public DefaultTableModel getModelFill(Object[][] data, Object[] col) {
        return new DefaultTableModel(data, col) {
            private static final long serialVersionUID = -4082996669139353200L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    /**
     * @author Giovanni
     * @param data Objeto[][]
     * @param col Objeto[] previamente formateado con el metodo getDimensional
     * @return Retorna un DefaultTableModel con celdas no editables
     * @since v1.0
     *
     */
    public DefaultTableModel getModelFillEditable(Object[][] data, Object[] col) {
        return new DefaultTableModel(data, col) {
            private static final long serialVersionUID = 1L;
        };
    }

    public DefaultTableModel getCustomModel(JTable tbl) {
        String[] cols = new String[tbl.getColumnCount()];
        for (int i = 0; i < tbl.getColumnCount(); i++) {
            cols[i] = tbl.getColumnName(i);
        }
        return new DefaultTableModel(new Object[][]{}, cols) {
            private static final long serialVersionUID = -4082996669139353200L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public DefaultTableModel getModelNoneEditable() {
        return new DefaultTableModel() {
            private static final long serialVersionUID = 2793957061432039023L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

    }

    public Connection getCurrentConnection() {
        return cs;
    }

}
