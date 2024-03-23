package com.tienda.service_layer.serviceImplements;

import com.tienda.data_access_layer.DAOimplements.UserDAOImpl;
import com.tienda.data_access_layer.UserDAO;
import com.tienda.entity.User;
import com.tienda.presentation_layer.LoginFrame;
import com.tienda.service_layer.LoginService;
import com.tienda.utilities.ServiceUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.sql.SQLException;

/**
 * La clase LoginServiceImpl implementa la interfaz LoginService y maneja el
 * inicio de sesión de usuario.
 */
public class LoginServiceImpl extends ServiceUtilities implements ActionListener, LoginService {

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
     * Instancia del formulario de inicio de sesión.
     */
    private final LoginFrame instanceOfLoginFrame;

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
        // Obtención de la instancia del formulario de inicio de sesión (LoginFrame)
        instanceOfLoginFrame = LoginFrame.getInstance();
        // Obtención de los componentes del formulario
        btnAceptar = instanceOfLoginFrame.getBtnAceptar();
        btnSalir = instanceOfLoginFrame.getBtnSalir();
        txtPassword = instanceOfLoginFrame.getTxtContraseña();
        txtUsuario = instanceOfLoginFrame.getTxtUsuario();
    }

    /**
     * Método estático para obtener la instancia única de LoginServiceImpl.
     *
     * @return Instancia única de LoginServiceImpl.
     */
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
     * Método para obtener la instancia del formulario de inicio de sesión.
     *
     * @return Instancia del formulario de inicio de sesión.
     */
    @Override
    public LoginFrame GetInstanceOfFrame() {
        instanceOfLoginFrame.setLocationRelativeTo(null); // Centrar el formulario en pantalla
        txtUsuario.requestFocus(); // Foco en el campo de usuario
        CargarKeyListeners(); // Cargar los KeyListeners para los campos de texto
        CargarActionListeners(); // Cargar los ActionListeners para los botones
        return instanceOfLoginFrame;
    }

    /**
     * Método para iniciar sesión.
     */
    @Override
    public void IniciarSesion() {
        try {
            // Obtener usuario y contraseña del formulario de inicio de sesión
            String usuario = txtUsuario.getText();
            String password = String.valueOf(txtPassword.getPassword());

            // Verificar si los campos están vacíos
            if (usuario.isEmpty() || password.isEmpty()) {
                // Mostrar mensaje de alerta si hay campos vacíos
                alerta.faltanDatos();
                return; // Finalizar el método si hay campos vacíos
            }

            // Crear un DTO con el usuario y contraseña proporcionados
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
            // Calcular el hash de la contraseña ingresada por el usuario
            byte[] inputHashedPassword = hashPassword(password, userReceived.getSalt());

            // Verificar si la contraseña ingresada coincide con la almacenada en la base de datos
            if (!MessageDigest.isEqual(userReceived.getHashed_password(), inputHashedPassword)) {
                // Mostrar mensaje de error si la contraseña es incorrecta
                alerta.mostrarError(LoginServiceImpl.class, "Contraseña incorrecta. Verifique nuevamente.", null);
                txtPassword.setText("");
                txtPassword.requestFocus();
                return; // Finalizar el método si la contraseña es incorrecta
            }

            // Limpiar campos de usuario y contraseña después de una autenticación exitosa
            txtUsuario.setText("");
            txtPassword.setText("");

            // Almacenar información del usuario autenticado
            userLogued = userReceived;

            // Cerrar la ventana de inicio de sesión y mostrar el menú principal
            instanceOfLoginFrame.dispose();
            MenuServiceImpl.getInstance().GetInstanceOfFrame().setVisible(true);

        } catch (SQLException | ClassNotFoundException ex) {
            // Manejar excepciones de base de datos y de configuración del sistema
            alerta.mostrarError(LoginServiceImpl.class, "Error al acceder a la base de datos.", ex);
        }

        // Incrementar el contador de intentos de inicio de sesión
        if (++intentos == 3) {
            // Mostrar mensaje de error y cerrar la aplicación si se supera el límite de intentos
            alerta.mostrarError(LoginServiceImpl.class, "Límite de intentos excedido.", null);
            System.exit(0);
        }
    }

    /**
     * Método para cargar los ActionListeners de los botones.
     */
    @Override
    public void CargarActionListeners() {
        QuitActionListeners(); // Eliminar los ActionListeners anteriores
        btnAceptar.addActionListener(this); // Agregar ActionListener para el botón de aceptar
        btnSalir.addActionListener(this); // Agregar ActionListener para el botón de salir
    }

    /**
     * Método para cargar los KeyListeners de los campos de texto.
     */
    @Override
    public void CargarKeyListeners() {
        QuitKeyListener(txtPassword); // Eliminar los KeyListeners anteriores del campo de contraseña
        QuitKeyListener(txtUsuario); // Eliminar los KeyListeners anteriores del campo de usuario
        // Agregar KeyListeners para los campos de texto
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    IniciarSesion(); // Iniciar sesión al presionar la tecla Enter en el campo de contraseña
                }
            }
        });
        txtUsuario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    IniciarSesion(); // Iniciar sesión al presionar la tecla Enter en el campo de usuario
                }
            }
        });
    }

    /**
     * Método para cargar los MouseListeners.
     */
    @Override
    public void CargarMouseListeners() {
        // No tiene MouseListeners
    }

    /**
     * Método para eliminar los ActionListeners de los botones.
     */
    @Override
    public void QuitActionListeners() {
        btnAceptar.removeActionListener(this); // Eliminar ActionListener del botón de aceptar
        btnSalir.removeActionListener(this); // Eliminar ActionListener del botón de salir
    }

    /**
     * Método para eliminar los KeyListeners de un componente.
     *
     * @param componente El componente del cual se eliminarán los KeyListeners.
     */
    @Override
    public void QuitKeyListener(Component componente) {
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
    public void QuitMouseListener(Component componente) {
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
            IniciarSesion(); // Iniciar sesión si se presiona el botón de aceptar
        } else if (evt.getSource() == btnSalir) {
            System.exit(0);
        }
    }
}
