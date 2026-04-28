package util;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Validaciones {

    // SOLO NUMEROS
    public static void soloNumeros(JTextField txt) {

        txt.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if(!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    }

    // SOLO LETRAS
    public static void soloLetras(JTextField txt) {

        txt.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if(!Character.isLetter(c) && c!=' ') {
                    e.consume();
                }
            }
        });
    }

    // CAMPOS VACIOS
    public static boolean campoVacio(JTextField txt, String nombre){

        if(txt.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null,
                    "Ingrese " + nombre);
            txt.requestFocus();
            return true;
        }

        return false;
    }
}
