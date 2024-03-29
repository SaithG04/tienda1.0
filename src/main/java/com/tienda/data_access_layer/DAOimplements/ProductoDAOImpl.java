package com.tienda.data_access_layer.DAOimplements;

import com.tienda.data_access_layer.*;
import com.tienda.entity.Producto;
import com.tienda.utilities.DataAccessUtilities;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

/**
 * Implementación del DAO de usuario para acceder a la base de datos.
 */
public class ProductoDAOImpl extends DataAccessUtilities implements Serializable, ProductoDAO {

    private Producto producto;
    private static final String NAMETABLE = "productos";

    public ProductoDAOImpl(Producto producto) {
        this.producto = producto;
    }

    @Override
    public void setEntity(Producto producto) {
        this.producto = producto;
    }
    
    @Override
    public Producto getById() throws SQLException, ClassNotFoundException {
        return getByIdGeneric(producto.getId(), NAMETABLE);
    }

    @Override
    public boolean registrar() throws ClassNotFoundException, SQLException {
        return registrarGeneric(NAMETABLE, producto);
    }

    @Override
    public List<Producto> listar() throws ClassNotFoundException, SQLException {
        return listarGeneric(NAMETABLE);
    }

    @Override
    public boolean actualizar() throws ClassNotFoundException, SQLException {
        return actualizarGeneric(NAMETABLE, producto.getId(), producto);
    }

    @Override
    public boolean eliminar() throws ClassNotFoundException, SQLException {
        return eliminarGeneric(NAMETABLE, producto.getId());
    }

    public static Map<Integer, String> getNameProveedor() throws ClassNotFoundException, SQLException {
        String query = "SELECT id, razon_social from proveedores";
        try (Connection connection = MySqlConnectionFactory.getInstance().getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                // Extraer los datos de la fila del ResultSet usando la función de extracción
                Map<Integer, String> rowData = new HashMap<Integer, String>();
                rowData.put(Integer.parseInt(resultSet.getString("id")),resultSet.getString("razon_social"));
                // Mapear los datos extraídos a un objeto DTO usando la función de mapeo
                return rowData;
            }
            return null;
        }
    }
}
