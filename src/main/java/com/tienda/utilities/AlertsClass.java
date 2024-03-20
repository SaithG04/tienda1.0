package com.tienda.utilities;

import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.*;
import javax.swing.*;

/**
 * Clase para manejar alertas y diálogos comunes en la aplicación.
 */
public class AlertsClass {

    // Botones comunes para los diálogos
    private final String botones[] = {"Aceptar"};
    private final String botones2[] = {"Aceptar", "Cancelar"};
    private static AlertsClass instance;

    // Constructor privado para el patrón Singleton
    private AlertsClass() {
    }

    /**
     * Método estático para obtener la instancia única de AlertsClass.
     *
     * @return Instancia única de AlertsClass.
     */
    public static AlertsClass getAlert() {
        if (instance == null) {
            synchronized (AlertsClass.class) { // Sincronización para hilos
                if (instance == null) {
                    instance = new AlertsClass();
                }
            }
        }
        return instance;
    }

    /**
     * Muestra un mensaje de error.
     *
     * @param clase Clase donde ocurrió el error.
     * @param mensaje Mensaje de error.
     * @param ex Excepción que causó el error.
     */
    public void mostrarError(Class<?> clase, String mensaje, Exception ex) {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showOptionDialog(null, mensaje, "Error", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, CommonUtilities.icono("/images/den.png", 30, 30), botones, botones[0]);
        if (ex != null) {
            Logger.getLogger(clase.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Maneja un error de conexión a la base de datos.
     *
     * @param clase Clase donde ocurrió el error de conexión.
     * @param ex Excepción que causó el error.
     */
    public void manejarErrorConexion(Class<?> clase, Exception ex) {
        if (ex instanceof SQLException) {
            mostrarError(clase, "Error de base de datos. Contacte al administrador.", ex);
        } else {
            mostrarError(clase, ex.getMessage(), ex);
        }
    }

    /**
     * Muestra un mensaje de aviso.
     *
     * @param mensaje Mensaje de aviso.
     */
    public void aviso(String mensaje) {
        JOptionPane.showOptionDialog(null, mensaje, "Aviso", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, CommonUtilities.icono("/images/correcto.jpg", 30, 30), botones, botones[0]);
    }

    /**
     * Muestra un mensaje de advertencia.
     *
     * @param mensaje Mensaje de advertencia.
     */
    public void advertencia(String mensaje) {
        JOptionPane.showOptionDialog(null, mensaje, "Advertencia", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, CommonUtilities.icono("/images/warning.png", 30, 30), botones, botones[0]);
    }

    /**
     * Muestra un mensaje de información.
     *
     * @param mensaje Mensaje de información.
     */
    public void info(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un mensaje de advertencia de que faltan datos.
     */
    public void faltanDatos() {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showOptionDialog(null, "Complete todos los campos.", "Advertencia", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, CommonUtilities.icono("/images/warning.png", 30, 30), botones, botones[0]);
//        System.out.println("");
    }

    /**
     * Muestra un mensaje de confirmación con botones "Aceptar" y "Cancelar".
     *
     * @param txt Texto del mensaje de confirmación.
     * @return Retorna la opción seleccionada (JOptionPane.OK_OPTION o
     * JOptionPane.CANCEL_OPTION).
     */
    public int confirmacion(String txt) {

        Toolkit.getDefaultToolkit().beep();
        int value = JOptionPane.showOptionDialog(null, txt, "Confirmar", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, CommonUtilities.icono("/images/question.png", 30, 30), botones2, botones2[1]);

        return value;
    }

    /**
     * Muestra un cuadro de diálogo de entrada.
     *
     * @param mensaje Mensaje que se muestra.
     * @param titulo Título del cuadro de diálogo.
     * @return Texto ingresado por el usuario.
     */
    public String entrada(String mensaje, String titulo) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(mensaje));
        JTextField textField = new JTextField(10);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                CommonUtilities.validarValorDecimal(evt, textField.getText());
            }
        });

        panel.add(textField);
        String texto = "";

        int option = JOptionPane.showOptionDialog(null, panel, titulo,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, UIManager.getIcon("OptionPane.informationIcon"), null, null);

        if (option == JOptionPane.OK_OPTION) {
            return textField.getText();
        }
        return texto;
    }
}
