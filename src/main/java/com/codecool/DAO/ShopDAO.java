package com.codecool.DAO;

import com.codecool.models.Shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopDAO extends AbstractDAO {

    private Connection connection;

    public ShopDAO(Connection connectionToDB) {
        connection = connectionToDB;
    }

    public List<Shop> readAll() throws SQLException {
        List<Shop> shopList = new ArrayList<>();

        String query = "SELECT * FROM shops;";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Shop shop = this.createShopFromResultSet(resultSet);
            shopList.add(shop);
        }
        return shopList;
    }

    public Shop getByID(String ID) throws SQLException {
        ResultSet rs = null;
        String query = "SELECT * FROM shops WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, Integer.parseInt(ID));
        rs = statement.executeQuery();

        if (rs.next()) {
            return this.createShopFromResultSet(rs);
        }
        return null;
    }

    public void create(Shop shop) throws SQLException {
        String query = "INSERT INTO shops (location) VALUES (?);";
        PreparedStatement statement = connection.prepareStatement(query);
        setPreparedCreateStatement(statement, shop);
        statement.executeUpdate();
    }

    private void setPreparedCreateStatement(PreparedStatement statement, Shop shop) throws SQLException {
        statement.setString(1, shop.getLocation());
    }

    public void update(Shop shop) throws SQLException {
        String query = "UPDATE shops SET location = ? WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        setPreparedUpdateStatement(statement, shop);
        statement.executeUpdate();
    }

    private void setPreparedUpdateStatement(PreparedStatement statement, Shop shop) throws SQLException {
        statement.setString(1, shop.getLocation());
        statement.setInt(2, shop.getID());
    }

    public void delete(Shop shop) throws SQLException {
        String query = "DELETE FROM shops WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, shop.getID());
        statement.executeUpdate();
    }

    Shop createShopFromResultSet(ResultSet resultSet) throws SQLException {
        int ID = Integer.parseInt(resultSet.getString("id"));
        String location = resultSet.getString("location");

        return new Shop(ID, location);
    }
}