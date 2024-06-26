package com.tienda.utilities;

import com.tienda.data_access_layer.MySqlConnectionFactory;
import com.tienda.entity.*;
import com.tienda.presentation_layer.LoginPanel;
import com.tienda.service_layer.LoginServiceImpl;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.function.Function;

/**
 * Clase utilitaria para operaciones de acceso a datos genéricas.
 *
 * @author isai_
 */
public class DataAccessUtilities {

    /**
     * Objeto para manejar alertas.
     */
    public static final AlertClass alerta = AlertClass.getAlert();

    /**
     * Obtiene un objeto por su ID de la tabla especificada.
     *
     * @param id El ID del objeto a obtener.
     * @param tableName El nombre de la tabla de la base de datos.
     * @param <T> El tipo de objeto a obtener.
     * @return El objeto obtenido, o null si no se encuentra.
     * @throws SQLException Si ocurre un error de SQL.
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     */
    public <T> T getByIdGeneric(int id, String tableName) throws SQLException, ClassNotFoundException {

        String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (Connection connection = MySqlConnectionFactory.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    T rowData = (T) getRowGeneric(tableName, connection).apply(resultSet);
                    return rowData;
                }
            }
        }

        return null;
    }

    public <T> T getEntityByOtherParameterGeneric(String tableName, String parameterName, Object parameter) throws SQLException, ClassNotFoundException {
        String query = parameter instanceof String ? "SELECT * FROM " + tableName + " WHERE " + parameterName + " COLLATE utf8_bin = ?"
                : "SELECT * FROM " + tableName + " WHERE " + parameterName + " = ?";
        try (Connection con = MySqlConnectionFactory.getInstance().getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setObject(1, parameter);
            try (ResultSet resultSet = pst.executeQuery()) {
                if (resultSet.next()) {
                    T rowData = (T) getRowGeneric(tableName, con).apply(resultSet);
                    return rowData;
                }
            }
        }
        return null; // Devolver nulo si no se encuentra el usuario
    }

    /**
     * Registra un nuevo objeto en la tabla especificada.
     *
     * @param tableName El nombre de la tabla de la base de datos.
     * @param entity El objeto a registrar.
     * @param <T> El tipo de objeto a registrar.
     * @return
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public <T> boolean registrarGeneric(String tableName, T entity) throws ClassNotFoundException, SQLException {
        if (amIConected()) {
            try (Connection connection = MySqlConnectionFactory.getInstance().getConnection()) {
                String query = generateInsertQuery(tableName, connection); // Generar la consulta de inserción
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    setInsertStatementValues(statement, entity);
                    statement.executeUpdate();
                    return true;
                }
            }
        } else {
            ServiceUtilities.volverLogin();
            return false;
        }

    }

    /**
     * Obtiene una lista de objetos de la tabla especificada.
     *
     * @param tableName El nombre de la tabla de la base de datos.
     * @param <T> El tipo de objetos a listar.
     * @return Una lista de objetos de la tabla.
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public <T> java.util.List<T> listarGeneric(String tableName) throws ClassNotFoundException, SQLException {
        java.util.List<T> lista = new ArrayList<>();
        if (amIConected()) {

            try (Connection connection = MySqlConnectionFactory.getInstance().getConnection()) {
                String query = "SELECT * FROM " + tableName;
                try (PreparedStatement statement = connection.prepareStatement(query); ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        T rowData = (T) getRowGeneric(tableName, connection).apply(result);
                        lista.add(rowData);
                    }
                }
            }
        } else {
            ServiceUtilities.volverLogin();
        }
        return lista;
    }

    /**
     * Actualiza un objeto en la tabla especificada.
     *
     * @param tableName El nombre de la tabla de la base de datos.
     * @param entity El objeto con los datos actualizados.
     * @param <T> El tipo de objeto a actualizar.
     * @return
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public <T> boolean actualizarGeneric(String tableName, T entity) throws ClassNotFoundException, SQLException {
        if (amIConected() || LoginPanel.getInstance().isVisible()) {
            try (Connection connection = MySqlConnectionFactory.getInstance().getConnection()) {
                String query = generateUpdateQuery(tableName, connection);
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    setUpdateStatementValues(statement, entity);
                    statement.executeUpdate();
                    return true;
                }
            }
        } else {
            ServiceUtilities.volverLogin();
            return false;
        }
    }

    /**
     * Elimina un objeto de la tabla especificada por su ID.
     *
     * @param tableName El nombre de la tabla de la base de datos.
     * @param id El ID del objeto a eliminar.
     * @return
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public boolean eliminarGeneric(String tableName, int id) throws ClassNotFoundException, SQLException {
        if (amIConected()) {
            try (Connection connection = MySqlConnectionFactory.getInstance().getConnection()) {
                String query = "DELETE FROM " + tableName + " WHERE id = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, id);
                    statement.executeUpdate();
                    return true;
                }
            }
        } else {
            ServiceUtilities.volverLogin();
            return false;
        }

    }

    /**
     * Obtiene una función para mapear filas de la tabla especificada a objetos.
     *
     * @param nameTable El nombre de la tabla de la base de datos.
     * @param con La conexión a la base de datos.
     * @return Una función que mapea filas de la tabla a objetos.
     * @throws SQLException Si ocurre un error de SQL.
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     */
    public Function<ResultSet, ?> getRowGeneric(String nameTable, Connection con) throws SQLException, ClassNotFoundException {
        String[] tableColumns = getTableColumns(nameTable, con);
        return (ResultSet resultSetReceived) -> {
            try {
                Object[] row = new Object[tableColumns.length];
                for (int i = 0; i < tableColumns.length; i++) {
                    row[i] = resultSetReceived.getObject(i + 1);
                }

                return switch (nameTable) {
                    case "users" ->
                        new User((int) row[0], row[1].toString(), row[2].toString(), (byte[]) row[3], (byte[]) row[4], row[5].toString());
                    case "productos" ->
                        new Producto((int) row[0], row[1].toString(), (int) row[2], (double) row[3], (double) row[4], row[5].toString());
                    case "proveedores" ->
                        new Proveedor((int) row[0], row[1].toString(), row[2].toString(), row[3].toString(), row[4].toString(),
                        row[5].toString(), row[6].toString(), row[7].toString(), row[8].toString(), row[9].toString(),
                        row[10].toString(), (java.sql.Date) row[11], row[12].toString());
                    case "transacciones" ->
                        new Transaccion((int) row[0], row[1].toString(), (int) row[2], (java.sql.Date) row[3], (double) row[4]);
                    case "detalle_pedidos" ->
                        new DetallePedido((int) row[0], row[1].toString(), (int) row[2], (int) row[3], (java.sql.Date) row[4], (java.sql.Date) row[5],
                        (double) row[6], row[7].toString(), (double) row[8], row[9].toString());
                    default ->
                        null;
                };

            } catch (SQLException e) {
                alerta.manejarErrorConexion(this.getClass(), e);
                return null;
            }
        };
    }

    /**
     * Obtiene las propiedades de la base de datos desde un archivo de
     * configuración.
     *
     * @return Un array de strings que contiene las propiedades: host, puerto,
     * base de datos, usuario y contraseña.
     */
    public String[] getProperties() {
        Properties propiedades = new Properties();
        InputStream entrada = null;
        try {
            entrada = getClass().getResourceAsStream("/properties/connection.properties");
            propiedades.load(entrada);

            String remoteHost = propiedades.getProperty("db.host");
            String localHost = "localhost"; //Para conectarse a un servidor local
            String port = propiedades.getProperty("db.port");
            String database = propiedades.getProperty("db.database");
            String user = propiedades.getProperty("db.user");
            String password = propiedades.getProperty("db.password");

            return new String[]{remoteHost, port, database, user, password};
        } catch (IOException ex) {
        } finally {
            if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    public boolean amIConected() throws SQLException, ClassNotFoundException {
        User user = getByIdGeneric(LoginServiceImpl.userLogued.getId(), "users");
        return user.getStatus().equals("logged in");
    }

    public Object getByOtherParameter(String query, Object parameter, String tableName) throws ClassNotFoundException, SQLException {
        try (Connection con = MySqlConnectionFactory.getInstance().getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setObject(1, parameter);
            try (ResultSet resultSet = pst.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getObject(1);
                }
            }
        }
        return null;
    }

    /**
     * Obtiene los nombres de las columnas de una tabla de la base de datos.
     *
     * @param tableName El nombre de la tabla.
     * @param con La conexión a la base de datos.
     * @return Un array de strings que contiene los nombres de las columnas de
     * la tabla.
     * @throws SQLException Si ocurre un error de SQL.
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     */
    private String[] getTableColumns(String tableName, Connection con) throws SQLException, ClassNotFoundException {
        java.util.List<String> columns = new ArrayList<>();
        DatabaseMetaData metaData = con.getMetaData();

        try (ResultSet resultSet = metaData.getColumns(con.getCatalog(), null, tableName, null)) {
            while (resultSet.next()) {
                columns.add(resultSet.getString("COLUMN_NAME"));
            }
        }

        return columns.toArray(String[]::new);
    }

    /**
     * Establece los valores de una sentencia de inserción para un objeto.
     *
     * @param statement La sentencia de inserción preparada.
     * @param entity El objeto del que se obtienen los valores.
     * @param <T> El tipo de objeto.
     * @throws SQLException Si ocurre un error de SQL.
     */
    private <T> void setInsertStatementValues(PreparedStatement statement, T entity) throws SQLException {

        switch (entity) {
            case User user -> {
                statement.setInt(1, 0); //Porque es autoincrementable
                statement.setString(2, user.getNombreCompleto());
                statement.setString(3, user.getUsername());
                statement.setBytes(4, user.getHashed_password());
                statement.setBytes(5, user.getSalt());
                statement.setString(6, user.getStatus());
            }
            case Producto producto -> {
                statement.setInt(1, 0); //Porque es autoincrementable
                statement.setString(2, producto.getNombre());
                statement.setInt(3, producto.getProveedor());
                statement.setDouble(4, producto.getCantidad());
                statement.setDouble(5, producto.getPrecio());
                statement.setString(6, producto.getMedida());
            }
            case Proveedor proveedor -> {
                statement.setInt(1, 0);
                statement.setString(2, proveedor.getRuc());
                statement.setString(3, proveedor.getRazon_social());
                statement.setString(4, proveedor.getDescripcion());
                statement.setString(5, proveedor.getDireccion());
                statement.setString(6, proveedor.getTelefono());
                statement.setString(7, proveedor.getCorreo());
                statement.setString(8, proveedor.getWeb());
                statement.setString(9, proveedor.getContacto());
                statement.setString(10, proveedor.getCategoria());
                statement.setString(11, proveedor.getEstado());
                statement.setDate(12, proveedor.getFecha_registro());
                statement.setString(13, proveedor.getObservaciones());
            }
            case Transaccion transaccion -> {
                statement.setInt(1, 0);
                statement.setString(2, transaccion.getTipo());
                statement.setInt(3, transaccion.getId_detalle());
                statement.setDate(4, transaccion.getFecha_registro());
                statement.setDouble(5, transaccion.getMonto());
            }
            case DetallePedido detallepedido ->{
                
            }
            default -> {
            }
        }
    }

    /**
     * Establece los valores de una sentencia de actualización para un objeto.
     *
     * @param statement La sentencia de actualización preparada.
     * @param entity El objeto del que se obtienen los valores.
     * @param <T> El tipo de objeto.
     * @throws SQLException Si ocurre un error de SQL.
     */
    private <T> void setUpdateStatementValues(PreparedStatement statement, T entity) throws SQLException {

        switch (entity) {
            case User user -> {
                statement.setString(1, user.getNombreCompleto());
                statement.setString(2, user.getUsername());
                statement.setBytes(3, user.getHashed_password());
                statement.setBytes(4, user.getSalt());
                statement.setString(5, user.getStatus());
                statement.setInt(6, user.getId());
            }
            case Producto producto -> {
                statement.setString(1, producto.getNombre());
                statement.setInt(2, producto.getProveedor());
                statement.setDouble(3, producto.getCantidad());
                statement.setDouble(4, producto.getPrecio());
                statement.setString(5, producto.getMedida());
                statement.setInt(6, producto.getId());
            }
            case Proveedor proveedor -> {
                statement.setString(1, proveedor.getRuc());
                statement.setString(2, proveedor.getRazon_social());
                statement.setString(3, proveedor.getDescripcion());
                statement.setString(4, proveedor.getDireccion());
                statement.setString(5, proveedor.getTelefono());
                statement.setString(6, proveedor.getCorreo());
                statement.setString(7, proveedor.getWeb());
                statement.setString(8, proveedor.getContacto());
                statement.setString(9, proveedor.getCategoria());
                statement.setString(10, proveedor.getEstado());
                statement.setDate(11, proveedor.getFecha_registro());
                statement.setString(12, proveedor.getObservaciones());
                statement.setInt(13, proveedor.getId());
            }
            case Transaccion transaccion -> {

            }
            case DetallePedido detallepedido ->{             
            }
            default -> {
            }
        }
    }

    /**
     * Genera una consulta de inserción para una tabla.
     *
     * @param tableName El nombre de la tabla.
     * @param con La conexión a la base de datos.
     * @return La consulta de inserción generada.
     * @throws SQLException Si ocurre un error de SQL.
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     */
    private String generateInsertQuery(String tableName, Connection con) throws SQLException, ClassNotFoundException {
        int numValues = getTableColumns(tableName, con).length;
        StringBuilder queryBuilder = new StringBuilder("INSERT INTO ");
        queryBuilder.append(tableName).append(" VALUES (");
        for (int i = 0; i < numValues; i++) {
            queryBuilder.append("?");
            if (i < numValues - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(")");
        return queryBuilder.toString();
    }

    /**
     * Genera una consulta de actualización para una tabla.
     *
     * @param tableName El nombre de la tabla.
     * @param con La conexión a la base de datos.
     * @return La consulta de actualización generada.
     * @throws SQLException Si ocurre un error de SQL.
     * @throws ClassNotFoundException Si no se encuentra la clase especificada.
     */
    private String generateUpdateQuery(String tableName, Connection con) throws SQLException, ClassNotFoundException {
        String[] columnNames = getTableColumns(tableName, con);
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("UPDATE ").append(tableName).append(" SET ");
        for (int i = 1; i < columnNames.length; i++) { // Comienza desde la segunda columna
            queryBuilder.append(columnNames[i]).append(" = ?");
            if (i < columnNames.length - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(" WHERE id = ?");
        return queryBuilder.toString();
    }

}
