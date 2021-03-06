/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import application.config.Fondo;
import application.config.Generic;
import application.third_party.Resources;
import application.views.vMenu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

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
    Fracciones fra;
    MaterialesXCombinacion mxc;
    Series ser;

    public Menu(Generic g) {
        this.g = g;
        menu = new vMenu();

        m = new Modulos(g, menu);
        u = new Usuarios(g, menu);
        p = new Permisos(g, menu);
        c = new Catalogos(g, menu);
        lin = new Lineas(g, menu);
        est = new Estilos(g, menu);
        maq = new Maquilas(g, menu);
        comb = new Combinaciones(g, menu);
        mat = new Materiales(g, menu);
        fra = new Fracciones(g, menu);
        mxc = new MaterialesXCombinacion(g, menu);
        ser = new Series(g,menu);

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
        
        menu.mnuSeries.addActionListener((e) -> {
            ser.getRecords();
            ser.setVisible();
        });

        menu.mnuMatxCombinacion.addActionListener((e) -> {
            mxc.getRecords();
            mxc.setVisible();
        });

        menu.mnuFracciones.addActionListener((e) -> {
            fra.getRecords();
            fra.setVisible();
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

        menu.mnuRutas.addActionListener((e) -> {
            c.setTipoCatalogo("RUTAS");
            c.getRecords();
            c.setVisible();
        });

        menu.mnuTrasnportes.addActionListener((e) -> {
            c.setTipoCatalogo("TRANSPORTES");
            c.getRecords();
            c.setVisible();
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


        menu.mnuTiposEstilo.addActionListener((e) -> {
            c.setTipoCatalogo("TIPOS ESTILO");
            c.getRecords();
            c.setVisible();
        });

        menu.mnuDepartamentos.addActionListener((e) -> {
            c.setTipoCatalogo("DEPARTAMENTOS");
            c.getRecords();
            c.setVisible();
        });

        menu.mnuPiezas.addActionListener((e) -> {
            c.setTipoCatalogo("PIEZAS");
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
            m.getRecords();
            m.setVisible();
            
        });
        menu.mnuUsuarios.addActionListener((e) -> {
            u.getRecords();
            u.setVisible();
        });
        menu.mnuPermisos.addActionListener((e) -> {
            p.getRecords();
            p.setVisible();
        });
    }

    public void setVisible() {

        

        InputStream foto1 = this.getClass().getResourceAsStream("/media/lsbck.png");
        cargarImagen(menu.dpContenedor, foto1);
        menu.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/LS.png")));
        menu.setExtendedState(menu.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        menu.setVisible(true);

//        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//        Rectangle r = new Rectangle(1, 1, d.width, d.height - 30);
//        menu.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("media/LS.png")));
//        menu.setSize(d.width, d.height - 30);
//        menu.setMaximizedBounds(r);
//        menu.setLocationRelativeTo(null);
//        menu.setVisible(true);
    }

    public void cargarImagen(javax.swing.JDesktopPane jDeskp, InputStream fileImagen) {
        try {
            BufferedImage image = ImageIO.read(fileImagen);
            jDeskp.setBorder(new Fondo(image));
        } catch (Exception e) {
            System.out.println("Imagen no disponible");
        }
    }

}
