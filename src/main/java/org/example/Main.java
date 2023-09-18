package org.example;

import org.example.dao.UserDao;
import org.example.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUserTable();
    }
}