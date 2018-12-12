package com.putnin.model;

import java.math.BigDecimal;

/**
 * Params for transfer money from one person account to another account of another person.
 */
public class TransferParams {
    /**
     * Id of sender person.
     */
    private Long senderId;

    /**
     * Phone of recipient phone.
     */
    private String recipientPhone;

    /**
     * Transfer money sum.
     */
    private BigDecimal sum;

    public TransferParams(){
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
