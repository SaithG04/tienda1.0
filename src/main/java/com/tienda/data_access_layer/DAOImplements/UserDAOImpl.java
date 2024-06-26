package com.tienda.data_access_layer.DAOImplements;

import com.tienda.data_access_layer.*;
import com.tienda.entity.User;
import com.tienda.presentation_layer.UsersPanel;
import com.tienda.utilities.*;
import java.io.Serializable;
import java.sql.*;
import java.util.List;

/**
 * Implementación concreta de la interfaz UserDAO que proporciona métodos para
 * realizar operaciones CRUD en la tabla de usuarios.
 *
 * @author isai_
 */
public class UserDAOImpl extends DataAccessUtilities implements UserDAO, Serializable {

    private User usuario;
    private static final String NAMETABLE = "users";

    /**
     * Constructor que inicializa la instancia del DAO con un objeto de usuario.
     *
     * @param usuario El usuario asociado al DAO.
     */
    public UserDAOImpl(User usuario) {
        this.usuario = usuario;
    }

    @Override
    public void setEntity(User usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Obtiene un usuario por su ID.
     *
     * @return El usuario encontrado, o null si no se encuentra.
     * @throws SQLException Si ocurre un error de SQL.
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     */
    @Override
    public User getById() throws SQLException, ClassNotFoundException {
        return getByIdGeneric(usuario.getId(), NAMETABLE);
    }

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    @Override
    public boolean registrar() throws ClassNotFoundException, SQLException {
        return registrarGeneric(NAMETABLE, usuario);
    }

    /**
     * Lista todos los usuarios almacenados en la base de datos.
     *
     * @return Una lista de todos los usuarios almacenados.
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    @Override
    public List<User> listar() throws ClassNotFoundException, SQLException {
        return listarGeneric(NAMETABLE);
    }

    /**
     * Actualiza los datos de un usuario en la base de datos.
     *
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    @Override
    public boolean actualizar() throws ClassNotFoundException, SQLException {
        return actualizarGeneric(NAMETABLE, usuario);
    }

    /**
     * Elimina un usuario de la base de datos.
     *
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    @Override
    public boolean eliminar() throws ClassNotFoundException, SQLException {
        return eliminarGeneric(NAMETABLE, usuario.getId());
    }

    /**
     * Obtiene un usuario por su nombre de usuario. Solo usado por el Login
     *
     * @return El usuario encontrado, o null si no se encuentra.
     * @throws SQLException Si ocurre un error de SQL.
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     */
    @Override
    public User getUserByUsername() throws SQLException, ClassNotFoundException {
        if (ServiceUtilities.containsPanel(UsersPanel.getInstance())) {
            if (amIConected()) {
                return (User) getEntityByOtherParameterGeneric(NAMETABLE, "username", usuario.getUsername());
            } else {
                ServiceUtilities.volverLogin();
            }
        } else {
            return (User) getEntityByOtherParameterGeneric(NAMETABLE, "username", usuario.getUsername());
        }
        return null; // Devolver nulo si no se encuentra el usuario
    }

}
