package org.example.service;

import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceJDBCImplTest extends UserServiceTest {

    private UserService userService = new UserServiceJDBCImpl();

    @BeforeEach
    public void testTuning() {
        userService.dropUserTable();
        userService.createUserTable();
        userService.saveUser(getTestName(), getTestLastName(), getTestAge());
    }

    @Test
    void createUserTable() {
        try {
            userService.dropUserTable();
            userService.createUserTable();
        } catch (Exception e) {
            fail("При создании таблицы произошла ошибка \n" + e.getMessage());
        }
    }

    @Test
    void dropUserTable() {
        try {
            userService.dropUserTable();
        } catch (Exception e) {
            fail("При удалении таблицы произошла ошибка \n" + e.getMessage());
        }
    }

    @Test
    void saveUser() {
        try {
            User user = userService.getAllUsers().get(0);
            assertEquals(getTestName(), user.getName());
            assertEquals(getTestLastName(), user.getLastName());
            assertEquals(getTestAge(), user.getAge());
        } catch (Exception e) {
            fail("При сохранении пользователя произошла ошибка \n" + e.getMessage());
        }
    }

    @Test
    void removeUserById() {
        try {
            userService.removeUserById(1L);
            assertFalse(userService.getAllUsers().size() != 0, "Удаление пользователя реализованно некорректно");
        } catch (Exception e) {
            fail("При удалении пользователя по id произошла ошибка \n" + e.getMessage());
        }
    }

    @Test
    void getAllUsers() {
        try {
            assertFalse(userService.getAllUsers().size() != 1, "Некорректно реализован метод удаления/сохранения пользователей или создания таблицы");
        } catch (Exception e) {
            fail("При получении пользователей произошла ошибка \n" + e.getMessage());
        }
    }

    @Test
    void clearUserTable() {
        try {
            userService.clearUserTable();
            assertFalse(userService.getAllUsers().size() != 0, "Метод очистки таблицы реализован некорректно");
        } catch (Exception e) {
            fail("При очистки  таблицы произошла ошибка \n" + e.getMessage());
        }
    }
}