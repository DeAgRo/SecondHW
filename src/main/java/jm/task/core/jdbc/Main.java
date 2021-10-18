package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl someServ = new UserServiceImpl();
        someServ.createUsersTable();
        someServ.saveUser("Федя", "Волков", (byte)22);
        someServ.saveUser("Олег", "Волков", (byte)22);
        someServ.saveUser("Игорь", "Волков", (byte)22);
        someServ.saveUser("Мавр", "Волков", (byte)22);
        System.out.println(someServ.getAllUsers());
        someServ.cleanUsersTable();
        someServ.dropUsersTable();
    }
}
