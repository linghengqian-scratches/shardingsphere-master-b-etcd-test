package com.lingh.commons.repository;

import com.lingh.commons.entity.Address;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
public final class AddressRepository {
    
    private final DataSource dataSource;
    
    public AddressRepository(final DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    /**
     * create table t_address if not exists.
     * @throws SQLException SQL exception
     */
    public void createTableIfNotExists() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS t_address (address_id BIGINT NOT NULL, address_name VARCHAR(100) NOT NULL, PRIMARY KEY (address_id))";
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    /**
     * drop table t_address.
     * @throws SQLException SQL exception
     */
    public void dropTable() throws SQLException {
        String sql = "DROP TABLE IF EXISTS t_address";
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }
    
    /**
     * truncate table t_address.
     * @throws SQLException SQL exception
     */
    public void truncateTable() throws SQLException {
        String sql = "TRUNCATE TABLE t_address";
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }
    
    /**
     * insert something to table t_address.
     *
     * @param address address
     * @throws SQLException SQL exception
     */
    public void insert(final Address address) throws SQLException {
        String sql = "INSERT INTO t_address (address_id, address_name) VALUES (?, ?)";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, address.getAddressId());
            preparedStatement.setString(2, address.getAddressName());
            preparedStatement.executeUpdate();
        }
    }
    
    /**
     * delete by id.
     * @param id id
     * @throws SQLException SQL exception
     */
    public void delete(final Long id) throws SQLException {
        String sql = "DELETE FROM t_address WHERE address_id=?";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }
    
    /**
     * select all.
     * @return list of address
     * @throws SQLException SQL exception
     */
    public List<Address> selectAll() throws SQLException {
        String sql = "SELECT * FROM t_address";
        List<Address> result = new LinkedList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Address address = new Address();
                address.setAddressId(resultSet.getLong(1));
                address.setAddressName(resultSet.getString(2));
                result.add(address);
            }
        }
        return result;
    }
}
