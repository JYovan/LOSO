/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.helpers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 *
 * @author Administrador
 */
public class NumberFormat {

    static public void customFormat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        System.out.println(value + "  " + pattern + "  " + output);
    }

    static public void main(String[] args) {
        /*SEGUN LA DOCUMENTACION SI EL FORMATO NO ES EL QUE SE ESPERA, 
        LA CONFIGURACION REGIONAL DEBE DE ESTAR ACORDE AL PAÍS DE LO CONTRARIO SERÁ AMBIGUO EL FORMATO RESULTADO
        https://docs.oracle.com/javase/tutorial/java/data/numberformat.html
         */
        customFormat("###,###.###", 123456.789);
        customFormat("###.##", 123456.789);
        customFormat("000000.000", 123.78);
        customFormat("$###,###.###", 12345.67);

        BigDecimal test = new BigDecimal(0.598253);
        System.out.println(test);
        test = test.add(new BigDecimal(54.83567000000000035697667044587433338165283203125));
        System.out.println(test);
        test = test.add(new BigDecimal(45));
        System.out.println(test);
    }
}
