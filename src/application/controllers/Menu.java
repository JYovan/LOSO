/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.config.Generic;
import application.third_party.Resources;
import application.views.vMenu;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class Menu {

    vMenu menu;
    Resources src = new Resources();
    Generic g;

    /*INSTANCIAS*/
    Catalogos c;
    Modulos m;
    Usuarios u;
    Permisos p;
    Lineas lin;
    Estilos est;
    Maquilas maq;
    Combinaciones comb;
    Materiales mat;
    
    public Menu(Generic g) {
        this.g = g;
        menu = new vMenu();

        m = new Modulos(g);
        u = new Usuarios(g);
        p = new Permisos(g);
        c = new Catalogos(g);
        lin = new Lineas(g);
        est = new Estilos(g);
        maq = new Maquilas(g);
        comb =  new Combinaciones(g);
        mat =  new Materiales(g);

        src.toSysTray(menu);
        menu.mSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "¿Realmente Desea Salir?", "Confirmar Salida", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    System.exit(0);
                }
            }
        });
        
        menu.mnuMateriales.addActionListener((e) -> {
            mat.getRecords();
            mat.setVisible();
        });
        
        menu.mnuCombinaciones.addActionListener((e) -> {
            comb.getRecords();
            comb.setVisible();
        });
        
        menu.mnuMaquilas.addActionListener((e) -> {
            maq.getRecords();
            maq.setVisible();
        });
        
        menu.mnuEstilos.addActionListener((e) -> {
            est.getRecords();
            est.setVisible();
        });
        menu.mnuLineas.addActionListener((e) -> {
            lin.getRecords();
            lin.setVisible();
        });

       
        menu.mnuHormas.addActionListener((e) -> {
            c.setTipoCatalogo("HORMAS");
            c.getRecords();
            c.setVisible();
        });

        menu.mnuTemporadas.addActionListener((e) -> {
            c.setTipoCatalogo("TEMPORADAS");
            c.getRecords();
            c.setVisible();
        });

        menu.mnuMetodosPago.addActionListener((e) -> {
            c.setTipoCatalogo("METODOS PAGO");
            c.getRecords();
            c.setVisible();
        });

        menu.mnuSeries.addActionListener((e) -> {
            c.setTipoCatalogo("SERIES");
            c.getRecords();
            c.setVisible();
        });

        menu.mnuTiposEstilo.addActionListener((e) -> {
            c.setTipoCatalogo("TIPOS ESTILO");
            c.getRecords();
            c.setVisible();
        });

        menu.mnuMetodosPago.addActionListener((e) -> {
            c.setTipoCatalogo("METODOS PAGO");
            c.getRecords();
            c.setVisible();
        });

        menu.mnuDepartamentos.addActionListener((e) -> {
            c.setTipoCatalogo("DEPARTAMENTOS");
            c.getRecords();
            c.setVisible();
        });

        menu.mnuPartesZapato.addActionListener((e) -> {
            c.setTipoCatalogo("PARTES ZAPATO");
            c.getRecords();
            c.setVisible();
        });

        menu.mnuFamilias.addActionListener((e) -> {
            c.setTipoCatalogo("FAMILIAS");
            c.getRecords();
            c.setVisible();
        });

        menu.mnuCondicionesPago.addActionListener((e) -> {
            c.setTipoCatalogo("CONDICIONES DE PAGO");
            c.getRecords();
            c.setVisible();
        });

        menu.mnuBancos.addActionListener((e) -> {
            c.setTipoCatalogo("BANCOS");
            c.getRecords();
            c.setVisible();
        });

        menu.mnuMonedas.addActionListener((e) -> {
            c.setTipoCatalogo("MONEDAS");
            c.getRecords();
            c.setVisible();
        });

        menu.mnuBancos.addActionListener((e) -> {
            c.setTipoCatalogo("BANCOS");
            c.getRecords();
            c.setVisible();
        });

        menu.mnuUnidades.addActionListener((e) -> {
            c.setTipoCatalogo("UNIDADES");
            c.getRecords();
            c.setVisible();
        });

        menu.mnuModulos.addActionListener((e) -> {
            m.setVisible();
        });
        menu.mnuUsuarios.addActionListener((e) -> {
            u.setVisible();
        });
        menu.mnuPermisos.addActionListener((e) -> {
            p.setVisible();
        });
    }

    public void setVisible() {
        menu.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/LS.png")));
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
        //menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
