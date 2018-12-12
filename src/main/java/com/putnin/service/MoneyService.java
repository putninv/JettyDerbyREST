package com.putnin.service;

import com.putnin.model.User;
import com.putnin.repository.MoneyRepository;
import com.putnin.repository.UserRepository;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * Service for money transfer between accounts.
 *
 * @author putnin.v@gmail.com
 */
public class MoneyService {
    private MoneyRepository moneyRepository;
    private UserRepository userRepository;

    public MoneyService(){
        moneyRepository = new MoneyRepository();
        userRepository = new UserRepository();
    }

    /**
     * Transfer money from sender user account to recipient user account;
     *
     * @param senderId id of person who send money
     * @param recipientPhone phone of person who receive money
     * @param sum money sum
     * @return true - money transfer success complete, false - money transfer not success complete
     */
    public boolean transferMoneyToPerson(Long senderId, String recipientPhone, BigDecimal sum) throws SQLException {
       User currentRecipient = userRepository.getUserByPhone(recipientPhone);
       return moneyRepository.transferMoneyToPerson(senderId, currentRecipient, sum);
    }

    /**
     * Get count of money on account.
     * @param accountId accountId
     * @return count of money
     */
    public BigDecimal getAccountSum(Long accountId) throws SQLException {
        return moneyRepository.getAccountSum(accountId);
    }
}
