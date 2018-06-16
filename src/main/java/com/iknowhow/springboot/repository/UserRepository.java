package com.iknowhow.springboot.repository;


import com.iknowhow.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createUser(User user) {
        String sql = "INSERT INTO USER (id, name, age, salary) VALUES (?, ?, ?, ?)";
        long id = user.getId();
        String name = user.getName();
        int age = user.getAge();
        double salary = user.getSalary();
        jdbcTemplate.update(sql, new Object[] { id, name, age, salary });
    }

    public List<User> readUsers() {
        String sql = "SELECT * FROM USER ORDER BY ID";
        List<User> users = jdbcTemplate.query(sql, new UserMapper());
        return users;
    }

    public void updateUser(User user) {
        String sql = "UPDATE USER SET name = ?, age = ?, salary = ? WHERE id = ?";
        long id = user.getId();
        String name = user.getName();
        int age = user.getAge();
        double salary = user.getSalary();
        jdbcTemplate.update(sql, new Object[] { name, age, salary, id });
    }

    public void deleteUser(long id) {
        String sql = "DELETE FROM USER WHERE id = ?";
        jdbcTemplate.update(sql, new Object[] { id });
    }

    public void deleteUsers() {
        String sql = "TRUNCATE TABLE USER";
        jdbcTemplate.execute(sql);
    }
}
