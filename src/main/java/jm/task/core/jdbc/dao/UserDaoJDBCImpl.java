package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection con = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String create = "CREATE TABLE IF NOT EXISTS users (" +
                "id int auto_increment," +
                "name varchar(45) not null," +
                "lastname varchar(45) not null," +
                "age int not null,constraint users_pk primary key (id));";
        try {
            Statement statement = con.createStatement();
            statement.execute(create);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try {
            Statement statement = con.createStatement();
            statement.execute("DROP TABLE IF EXISTS users");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?, ?, ?);");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String remove = "DELETE from `users` WHERE id=" + id + ";";
        try {
            Statement statement = con.createStatement();
            statement.execute(remove);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //
    public List<User> getAllUsers() {
        List<User> all = new ArrayList<>();
        String sql = "SELECT * FROM users;";
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                long id = rs.getInt(1);
                String name = rs.getString(2);
                String lastname = rs.getString(3);
                int age = rs.getInt(4);
                User some = new User(name, lastname, (byte) age);
                some.setId(id);
                all.add(some);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return all;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = con.createStatement();
            statement.execute("DELETE FROM `users`");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
