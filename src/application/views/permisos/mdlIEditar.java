/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.views.permisos;

import application.config.ManejadorTecla;

/**
 *
 * @author Christian
 */
public class mdlIEditar extends javax.swing.JInternalFrame {

    /**
     * Creates new form mdlIEditar
     */
    ManejadorTecla manejador =  new ManejadorTecla();
    public mdlIEditar() {
        initComponents();
        
        manejador.manejaCombo(Modulo);
        Usuario.addKeyListener(manejador);
        
        this.Buscar.addKeyListener(manejador);
        this.Consultar.addKeyListener(manejador);
        this.Crear.addKeyListener(manejador);
        this.Eliminar.addKeyListener(manejador);
        this.Modificar.addKeyListener(manejador);
        this.Reportes.addKeyListener(manejador);
        this.Ver.addKeyListener(manejador);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        Ver = new javax.swing.JCheckBox();
        Crear = new javax.swing.JCheckBox();
        Modificar = new javax.swing.JCheckBox();
        Eliminar = new javax.swing.JCheckBox();
        Consultar = new javax.swing.JCheckBox();
        Reportes = new javax.swing.JCheckBox();
        Buscar = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Usuario = new javax.swing.JTextField();
        Modulo = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setTitle("EDITAR PERMISOS");
        setToolTipText("");
        setFrameIcon(null);
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar.setText("Guardar");

        Ver.setBackground(new java.awt.Color(255, 255, 255));
        Ver.setText("VER");

        Crear.setBackground(new java.awt.Color(255, 255, 255));
        Crear.setText("CREAR");

        Modificar.setBackground(new java.awt.Color(255, 255, 255));
        Modificar.setText("MODIFICAR");

        Eliminar.setBackground(new java.awt.Color(255, 255, 255));
        Eliminar.setText("ELIMINAR");

        Consultar.setBackground(new java.awt.Color(255, 255, 255));
        Consultar.setText("CONSULTAR");

        Reportes.setBackground(new java.awt.Color(255, 255, 255));
        Reportes.setText("REPORTES");

        Buscar.setBackground(new java.awt.Color(255, 255, 255));
        Buscar.setText("BUSCAR");

        jLabel1.setText("Usuario");

        jLabel2.setText("Módulo");

        Usuario.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Usuario, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Ver)
                                .addComponent(Modificar)
                                .addComponent(Buscar))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Eliminar)
                                .addComponent(Consultar)
                                .addComponent(Crear))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Reportes)
                                .addComponent(btnGuardar)))
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addComponent(Modulo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Modulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ver)
                    .addComponent(Consultar)
                    .addComponent(Reportes))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Modificar)
                    .addComponent(Eliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Buscar)
                    .addComponent(Crear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox Buscar;
    public javax.swing.JCheckBox Consultar;
    public javax.swing.JCheckBox Crear;
    public javax.swing.JCheckBox Eliminar;
    public javax.swing.JCheckBox Modificar;
    public javax.swing.JComboBox<String> Modulo;
    public javax.swing.JCheckBox Reportes;
    public javax.swing.JTextField Usuario;
    public javax.swing.JCheckBox Ver;
    public javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
