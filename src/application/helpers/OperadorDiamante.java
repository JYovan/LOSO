/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrador
 */
public class OperadorDiamante {

    public static void main(String[] args) {
        /*DIAMANTE SIMPLE*/
        List<String> lista = new ArrayList<>();
        lista.add("hola");
        lista.add("adios");
        lista.forEach(System.out::println);

        /*DIAMANTE COMPLEJO*/
        List<Map<String, String>> listaDC = new ArrayList<>();
        Map<String, String> mapa = new HashMap<>();
        mapa.put("clave1", "valor1");
        listaDC.add(mapa);
        listaDC.forEach(System.out::println);
    }
}
