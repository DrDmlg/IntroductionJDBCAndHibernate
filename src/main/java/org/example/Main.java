package org.example;

import org.example.dao.UserDao;
import org.example.dao.UserDaoJDBCImpl;
import org.example.model.User;
import org.example.util.Util;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUserTable();
    }
}