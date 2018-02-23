/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.helpers;

/**
 *
 * @author Administrador
 */
public class Item {

    private int ID;
    private String DESCRIPCION;

    public Item(int ID, String DESCRIPCION) {
        this.ID = ID;
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getID() {
        return ID;
    }

    public String getDescription() {
        return DESCRIPCION;
    }

    @Override
    public String toString() {
        return DESCRIPCION;
    }
}