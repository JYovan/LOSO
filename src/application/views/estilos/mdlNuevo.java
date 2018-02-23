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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("NUEVO ESTILO");

        plnNuevo.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar.setText("Guardar");

        Serie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Horma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Tipo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Maquila.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Temporada.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setText("FAMILIA");

        jLabel2.setText("SERIE");

        jLabel3.setText("HORMA");

        jLabel4.setText("GENERO");

        Genero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MASCULINO", "FEMENINO" }));
        Genero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Foto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/camera.png"))); // NOI18N
        Foto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Liberado.setText("LIBERADO");

        jLabel5.setText("MAQUILA");

        Notas.setColumns(20);
        Notas.setRows(5);
        jScrollPane1.setViewportView(Notas);

        jLabel6.setText("NOTAS");

        jLabel7.setText("TEMPORADA");

        jLabel8.setText("TIPO DE ESTILO");

        MaquilaPlantilla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MAQUILA", "PLANTILLA", "MAQUILAS EXTERNAS" }));
        MaquilaPlantilla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel9.setText("MAQUILA O PLANTILLA");

        javax.swing.GroupLayout plnNuevoLayout = new javax.swing.GroupLayout(plnNuevo);
        plnNuevo.setLayout(plnNuevoLayout);
        plnNuevoLayout.setHorizontalGroup(
            plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnNuevoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Foto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(plnNuevoLayout.createSequentialGroup()
                        .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(Genero, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(plnNuevoLayout.createSequentialGroup()
                                .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Familia, 0, 241, Short.MAX_VALUE)
                                    .addComponent(Linea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(24, 24, 24)
                                .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(Serie, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Desperdicio)
                                    .addComponent(Clave, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Horma, 0, 239, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(Liberado))
                        .addGap(228, 228, 228))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, plnNuevoLayout.createSequentialGroup()
                        .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, plnNuevoLayout.createSequentialGroup()
                                .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Maquila, 0, 241, Short.MAX_VALUE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(Temporada, 0, 241, Short.MAX_VALUE)
                                    .addComponent(jLabel8)
                                    .addComponent(Tipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9)
                                    .addComponent(MaquilaPlantilla, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnGuardar)
                                            .addGroup(plnNuevoLayout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(Ano)
                                                    .addComponent(PuntoCentral, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(plnNuevoLayout.createSequentialGroup()
                                        .addComponent(Herramental, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TipoDeConstruccion, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        plnNuevoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Ano, Desperdicio, Familia, Genero, Herramental, Horma, Liberado, Maquila, MaquilaPlantilla, PuntoCentral, Serie, Temporada, Tipo, TipoDeConstruccion});

        plnNuevoLayout.setVerticalGroup(
            plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plnNuevoLayout.createSequentialGroup()
                .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plnNuevoLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Linea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Familia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Serie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Horma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Genero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Desperdicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Liberado))
                        .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(plnNuevoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(plnNuevoLayout.createSequentialGroup()
                                        .addComponent(Ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(PuntoCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(113, 113, 113)
                                .addComponent(btnGuardar))
                            .addGroup(plnNuevoLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Maquila, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Temporada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(plnNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Herramental, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TipoDeConstruccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MaquilaPlantilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(plnNuevoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Foto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        plnNuevoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Ano, Clave, Descripcion, Desperdicio, Familia, Genero, Herramental, Horma, Liberado, Maquila, PuntoCentral, Serie, Temporada, Tipo, TipoDeConstruccion});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


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
