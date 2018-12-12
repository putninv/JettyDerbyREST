package com.putnin.service;

import com.putnin.model.User;
import com.putnin.repository.UserRepository;

import java.sql.SQLException;

/**
 * Repository for money transfer between accounts.
 *
 * @author putnin.v@gmail.com
 */
public class UserService {
    private UserRepository userRepository;

    public UserService(){
        userRepository = new UserRepository();
    }

    /**
     * Get user by phone.
     *
     * @param phone user phone
     * @return founded user
     */
    public User getUserByPhone(String phone) throws SQLException {
        return userRepository.getUserByPhone(phone);
    }
}
