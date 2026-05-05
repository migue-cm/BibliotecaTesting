package util;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.text.*;

public class Validaciones {

	public static void limitarDNI(JTextField txt){

        ((AbstractDocument) txt.getDocument())
        .setDocumentFilter(new DocumentFilter(){

            @Override
            public void insertString(FilterBypass fb,
                                     int offset,
                                     String string,
                                     AttributeSet attr)
                    throws BadLocationException {

                if(string == null) return;

                if(esNumero(string)
                        && (fb.getDocument().getLength()
                        + string.length()) <= 8){

                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb,
                                int offset,
                                int length,
                                String text,
                                AttributeSet attrs)
                    throws BadLocationException {

                if(text == null) return;

                if(esNumero(text)
                        && (fb.getDocument().getLength()
                        - length + text.length()) <= 8){

                    super.replace(fb, offset, length, text, attrs);
                }
            }

            private boolean esNumero(String texto){
                return texto.matches("\\d+");
            }
        });
    }

	
	
	
    // solo numeros
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

    // solo letras
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

    // campos vacios
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
