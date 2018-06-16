package com.iknowhow.springboot.service;

import com.iknowhow.springboot.model.User;
import com.iknowhow.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findById(long id) {
        List<User> users = userRepository.readUsers();
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User findByName(String name) {
        List<User> users = userRepository.readUsers();
        return users.stream()
                .filter(u -> name.equals(u.getName()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    @CachePut(value = "users")
    @Transactional
    public void saveUser(User user) {
        List<User> users = userRepository.readUsers();
        long lastIndex = users.get(users.size() - 1).getId();
        user.setId(++lastIndex);
        userRepository.createUser(user);
    }

    @Override
    @CachePut(value = "users")
    @Transactional
    public void updateUser(User user) {
        if (isUserExist(user)) {
            userRepository.updateUser(user);
        }
    }

    @Override
    @CachePut(value = "users")
    @Transactional
    public void deleteUserById(long id) {
        if (isUserExist(findById(id))) {
            userRepository.deleteUser(id);
        }
    }

    @Override
    @Cacheable(value = "users")
    public List<User> findAllUsers() {
        return userRepository.readUsers();
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void deleteAllUsers() {
        userRepository.deleteUsers();
    }

    @Override
    public boolean isUserExist(User user) {
        return findById(user.getId()) != null;
    }
}
