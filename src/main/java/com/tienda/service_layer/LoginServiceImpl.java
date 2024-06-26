package com.tienda.service_layer;

import com.tienda.data_access_layer.DAOImplements.UserDAOImpl;
import com.tienda.data_access_layer.UserDAO;
import com.tienda.entity.User;
import com.tienda.presentation_layer.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.sql.SQLException;

/**
 * La clase LoginServiceImpl implementa la interfaz LoginService y maneja el
 * inicio de sesión de usuario.
 *
 * Este servicio gestiona la autenticación de usuarios y proporciona
 * funcionalidades para interactuar con el formulario de inicio de sesión.
 *
 * @author isai_
 */
public final class LoginServiceImpl extends com.tienda.utilities.ServiceUtilities implements ActionListener, FrameService {

    // Declaración de variables de instancia
    /**
     * Instancia única de la clase LoginServiceImpl.
     */
    private static volatile LoginServiceImpl instanceOfLoginServiceImpl;

    /**
     * Usuario que ha iniciado sesión.
     */
    public static User userLogued;

    /**
     * Instancia
     */
    private final LoginPanel instanceOfLoginPanel;

    /**
     * Componentes del formulario de inicio de sesión.
     */
    private final JButton btnAceptar, btnSalir;
    private final JPasswordField txtPassword;
    private final JTextField txtUsuario;

    /**
     * Contador de intentos de inicio de sesión.
     */
    private int intentos = 0;

    /**
     * Constructor privado para garantizar la implementación del patrón
     * Singleton.
     */
    private LoginServiceImpl() {
        instanceOfLoginPanel = LoginPanel.getInstance();
        // Obtención de los componentes del formulario
        btnAceptar = instanceOfLoginPanel.getBtnAceptar();
        btnSalir = instanceOfLoginPanel.getBtnSalir();
        txtPassword = instanceOfLoginPanel.getTxtContraseña();
        txtUsuario = instanceOfLoginPanel.getTxtUsuario();
        configureTheme();
    }

    public void loadPanel() {
        addPanelToFrame(instanceOfLoginPanel);
        instanceOfFrame.setLocationRelativeTo(null); // Centrar el formulario en pantalla
        txtUsuario.requestFocus(); // Foco en el campo de usuario
        instanceOfFrame.setTitle("Acceder");
        cargarKeyListeners(); // Cargar los KeyListeners para los campos de texto
        cargarActionListeners(); // Cargar los ActionListeners para los botones
    }

    /**
     * Método estático para obtener la instancia única de LoginServiceImpl.
     *
     * @return Instancia única de LoginServiceImpl.
     */
    @SuppressWarnings("DoubleCheckedLocking")
    public static LoginServiceImpl getInstance() {
        if (instanceOfLoginServiceImpl == null) {
            synchronized (LoginServiceImpl.class) { // Sincronización para hilos
                if (instanceOfLoginServiceImpl == null) {
                    instanceOfLoginServiceImpl = new LoginServiceImpl();
                }
            }
        }
        return instanceOfLoginServiceImpl;
    }

    /**
     * Método para iniciar sesión.
     *
     * @param evt Evento recibido
     */
    public void IniciarSesion(AWTEvent evt) {

        try {

            setCursores(waitCursor);
            // Obtener usuario y contraseña del formulario de inicio de sesión
            String usuario = txtUsuario.getText();
            String password = String.valueOf(txtPassword.getPassword());

            // Verificar si los campos están vacíos
            if (usuario.isEmpty() || password.isEmpty()) {
                // Mostrar mensaje de alerta si hay campos vacíos
                alerta.faltanDatos();
                return; // Finalizar el método si hay campos vacíos
            }

            User userLog = new User();
            userLog.setUsername(usuario);
            UserDAO userDao = new UserDAOImpl(userLog);
            User userReceived = userDao.getUserByUsername();

            // Verificar si se encontró el usuario en la base de datos
            if (userReceived == null) {
                // Mostrar mensaje de error si el usuario no existe
                alerta.mostrarError(this.getClass(), "El usuario no existe.", null);
                txtPassword.setText("");
                txtUsuario.requestFocus();
                return; // Finalizar el método si el usuario no existe
            }

            if (userReceived.getStatus().equals("logged in")) {
                alerta.mostrarError(this.getClass(), "El usuario ya se encuentra conectado.", null);
                txtUsuario.setText("");
                txtPassword.setText("");
                txtUsuario.requestFocus();
                return;
            }

            // Calcular el hash de la contraseña ingresada por el usuario
            byte[] inputHashedPassword = hashPassword(password, userReceived.getSalt());

            // Verificar si la contraseña ingresada coincide con la almacenada en la base de datos
            if (!MessageDigest.isEqual(userReceived.getHashed_password(), inputHashedPassword)) {
                if (intentos == 3) {
                    // Mostrar mensaje de error y cerrar la aplicación si se supera el límite de intentos
                    alerta.mostrarError(LoginServiceImpl.class, "Límite de intentos excedido.", null);
                    System.exit(0);
                } else {
                    // Mostrar mensaje de error si la contraseña es incorrecta
                    alerta.mostrarError(LoginServiceImpl.class, "Contraseña incorrecta. Verifique nuevamente.", null);
                    txtPassword.setText("");
                    txtUsuario.requestFocus();
                    ++intentos;
                    return; // Finalizar el método si la contraseña es incorrecta
                }
            }

            // Limpiar campos de usuario y contraseña después de una autenticación exitosa
            txtUsuario.setText("");
            txtPassword.setText("");

            // Almacenar información del usuario autenticado
            userLogued = userReceived;

            userLogued.setStatus("logged in");
            userDao = new UserDAOImpl(userLogued);
            userDao.actualizar();
            // Cerrar la ventana de inicio de sesión y mostrar el menú principal
            MenuServiceImpl.getInstance().loadPanel();
            removePanelFromFrame(instanceOfLoginPanel);
            intentos = 0;
        } catch (SQLException | ClassNotFoundException ex) {
            errorSQL(this.getClass(), ex);
        } finally {
            setCursores(defaultCursor);
        }
    }

    private void setCursores(Cursor cursor) {
        setCursoresGeneric(instanceOfLoginPanel.getComponents(), cursor);
    }

    /**
     * Método para cargar los ActionListeners de los botones.
     */
    @Override
    public void cargarActionListeners() {
        quitActionListeners(); // Eliminar los ActionListeners anteriores
        btnAceptar.addActionListener(this); // Agregar ActionListener para el botón de aceptar
        btnSalir.addActionListener(this); // Agregar ActionListener para el botón de salir
    }

    /**
     * Método para cargar los KeyListeners de los campos de texto.
     */
    @Override
    public void cargarKeyListeners() {
        quitKeyListener(txtPassword); // Eliminar los KeyListeners anteriores del campo de contraseña
        // Agregar KeyListeners para los campos de texto
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    IniciarSesion(evt); // Iniciar sesión al presionar la tecla Enter en el campo de contraseña
                }
            }

            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    IniciarSesion(evt); // Iniciar sesión al soltar la tecla Enter en el campo de contraseña
                }
            }
        });
    }

    /**
     * Método para cargar los MouseListeners.
     */
    @Override
    public void cargarMouseListeners() {
        // No tiene MouseListeners
    }

    /**
     * Método para eliminar los ActionListeners de los botones.
     */
    @Override
    public void quitActionListeners() {
        btnAceptar.removeActionListener(this); // Eliminar ActionListener del botón de aceptar
        btnSalir.removeActionListener(this); // Eliminar ActionListener del botón de salir
    }

    /**
     * Método para eliminar los KeyListeners de un componente.
     *
     * @param componente El componente del cual se eliminarán los KeyListeners.
     */
    @Override
    public void quitKeyListener(Component componente) {
        for (KeyListener ml : componente.getKeyListeners()) {
            componente.removeKeyListener(ml); // Eliminar los MouseListeners del componente
        }
    }

    /**
     * Método para eliminar los MouseListeners de un componente.
     *
     * @param componente El componente del cual se eliminarán los
     * MouseListeners.
     */
    @Override
    public void quitMouseListener(Component componente) {
        // No tiene MouseListeners
    }

    /**
     * Método para manejar los eventos de los botones.
     *
     * @param evt El evento que ha ocurrido.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnAceptar) {
            IniciarSesion(evt); // Iniciar sesión si se presiona el botón de aceptar
        } else if (evt.getSource() == btnSalir) {
            System.exit(0);
        }
    }

    public void limpiar() {
        txtUsuario.setText("");
        txtPassword.setText("");
        txtUsuario.requestFocus();
    }
}
