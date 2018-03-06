/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.config;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author Christian
 */
public class ManejadorTecla implements KeyListener {
    
    

    @Override
    public void keyPressed(KeyEvent e) {
        Robot Tecla;
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                Tecla = new Robot();
                Tecla.keyPress(KeyEvent.VK_TAB);
            } catch (AWTException e1) {
                e1.printStackTrace();
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    @Override
    public void keyTyped(KeyEvent e) {
        Character c = e.getKeyChar();
        if (Character.isLetter(c)) {
            e.setKeyChar(Character.toUpperCase(c));
        }

    }
    
      public void manejaCombo(JComboBox comboBox) {

        final JTextField editorComponent = (JTextField) comboBox.getEditor().getEditorComponent();

        editorComponent.addActionListener((ActionEvent e) -> {
            editorComponent.transferFocus();
        });

    }
      
     
      
      
    
}



