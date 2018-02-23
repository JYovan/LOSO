/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.views.estilos;

import application.views.lineas.*;
import application.config.ManejadorTecla;

/**
 *
 * @author Christian
 */
public class mdlNuevo extends javax.swing.JDialog {

    private static final long serialVersionUID = 1553479543827182909L;
    ManejadorTecla manejador = new ManejadorTecla();

    public mdlNuevo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.Clave.addKeyListener(manejador);
        this.Descripcion.addKeyListener(manejador);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        plnNuevo = new javax.swing.JPanel();
        Familia = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        Clave = new javax.swing.JTextField();
        Descripcion = new javax.swing.JTextField();
        Serie = new javax.swing.JComboBox<>();
        Horma = new javax.swing.JComboBox<>();
        Tipo = new javax.swing.JComboBox<>();
        Maquila = new javax.swing.JComboBox<>();
        Temporada = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Genero = new javax.swing.JComboBox<>();
        Foto = new javax.swing.JLabel();
        Desperdicio = new javax.swing.JTextField();
        Liberado = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Notas = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        Ano = new javax.swing.JTextField();
        PuntoCentral = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Herramental = new javax.swing.JTextField();
        TipoDeConstruccion = new javax.swing.JTextField();
        MaquilaPlantilla = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        Linea = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("NUEVO ESTILO");

        plnNuevo.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar.setText("Guardar");

        Serie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Serie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SerieActionPerformed(evt);
            }
        });

        Horma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Tipo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Maquila.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Temporada.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setText("Familia");

        jLabel2.setText("Serie");

        jLabel3.setText("Horma");

        jLabel4.setText("Genero");

        Genero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MASCULINO", "FEMENINO" }));
        Genero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Foto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/camera.png"))); // NOI18N
        Foto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Liberado.setBackground(new java.awt.Color(255, 255, 255));
        Liberado.setText("Liberado");

        jLabel5.setText("Maquila");

        Notas.setColumns(20);
        Notas.setRows(5);
        jScrollPane1.setViewportView(Notas);

        jLabel6.setText("Notas");

        jLabel7.setText("Temporada");

        jLabel8.setText("Tipo de Estilo");

        TipoDeConstruccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoDeConstruccionActionPerformed(evt);
            }
        });

        MaquilaPlantilla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MAQUILA", "PLANTILLA", "MAQUILAS EXTERNAS" }));
        MaquilaPlantilla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel9.setText("Maquila o Plantilla");

        jLabel10.setText("Linea");

        jLabel11.setText("Clave");

        jLabel12.setText("Desperdicio");

        jLabel13.setText("Herramental");

        jLabel14.setText("Descripción");

        jLabel15.setText("Año");

        jLabel16.setText("Punto Central");

        jLabel17.setText("Tipo de Contrucción");

        javax.swing.GroupLayout plnNuevoLayout = new javax.swing.GroupLayout(plnNuevo);
        plnNuevo.setLayout(plnNuevoLayout);
        plnNuevoLayout.setHorizontalGroup(
            plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnNuevoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Foto, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(plnNuevoLayout.createSequentialGroup()
                            .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel10))
                            .addGap(8, 8, 8))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plnNuevoLayout.createSequentialGroup()
                            .addComponent(Familia, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(46, 46, 46)))
                    .addGroup(plnNuevoLayout.createSequentialGroup()
                        .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Linea, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Maquila, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Temporada, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MaquilaPlantilla, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(Clave, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9)
                            .addComponent(jLabel1)
                            .addComponent(Genero, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(46, 46, 46)))
                .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plnNuevoLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(plnNuevoLayout.createSequentialGroup()
                        .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Desperdicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Serie, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TipoDeConstruccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Herramental, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Descripcion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15)
                            .addComponent(Ano, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PuntoCentral, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Horma, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel16)
                            .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnGuardar)
                                .addComponent(Liberado, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 22, Short.MAX_VALUE))))
        );
        plnNuevoLayout.setVerticalGroup(
            plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnNuevoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Foto, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(plnNuevoLayout.createSequentialGroup()
                        .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(plnNuevoLayout.createSequentialGroup()
                                .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plnNuevoLayout.createSequentialGroup()
                                        .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel11))
                                        .addGap(0, 0, 0)
                                        .addComponent(Clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11)
                                        .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel2)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plnNuevoLayout.createSequentialGroup()
                                        .addComponent(Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(25, 25, 25)))
                                .addGap(0, 0, 0)
                                .addComponent(Serie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12))
                            .addGroup(plnNuevoLayout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(jLabel1))
                            .addGroup(plnNuevoLayout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(Linea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Familia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Desperdicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(plnNuevoLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(0, 0, 0)
                                .addComponent(Ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(1, 1, 1)
                                .addComponent(Horma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addGap(4, 4, 4)
                                .addComponent(PuntoCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(plnNuevoLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(Liberado))
                            .addGroup(plnNuevoLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel13))
                            .addGroup(plnNuevoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Genero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Herramental, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel17))
                                .addGap(1, 1, 1)
                                .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Maquila, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TipoDeConstruccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(plnNuevoLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(1, 1, 1)
                                .addComponent(Temporada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addGap(1, 1, 1)
                                .addComponent(Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(plnNuevoLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(1, 1, 1)
                        .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MaquilaPlantilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        plnNuevoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Ano, Clave, Descripcion, Desperdicio, Familia, Genero, Herramental, Horma, Liberado, Maquila, PuntoCentral, Serie, Temporada, Tipo, TipoDeConstruccion});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(plnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TipoDeConstruccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoDeConstruccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipoDeConstruccionActionPerformed

    private void SerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SerieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SerieActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField Ano;
    public javax.swing.JTextField Clave;
    public javax.swing.JTextField Descripcion;
    public javax.swing.JTextField Desperdicio;
    public javax.swing.JComboBox<String> Familia;
    public javax.swing.JLabel Foto;
    public javax.swing.JComboBox<String> Genero;
    public javax.swing.JTextField Herramental;
    public javax.swing.JComboBox<String> Horma;
    public javax.swing.JCheckBox Liberado;
    public javax.swing.JComboBox<String> Linea;
    public javax.swing.JComboBox<String> Maquila;
    public javax.swing.JComboBox<String> MaquilaPlantilla;
    private javax.swing.JTextArea Notas;
    public javax.swing.JTextField PuntoCentral;
    public javax.swing.JComboBox<String> Serie;
    public javax.swing.JComboBox<String> Temporada;
    public javax.swing.JComboBox<String> Tipo;
    public javax.swing.JTextField TipoDeConstruccion;
    public javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel plnNuevo;
    // End of variables declaration//GEN-END:variables
}
