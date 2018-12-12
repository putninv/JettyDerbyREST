package com.putnin.service;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

/**
 * Test for money transfer service.
 * Use when have, created db tables.
 *
 * @author putnin.v@gmail.com
 */
public class MoneyServiceTest {
    private static final int SENDER_ID = 123123;
    private static final int SENDER_ACCOUNT_ID = 981111;
    private static final String RECIPIENT_PHONE = "+79779082837";
    private static final int RECIPIENT_ACCOUNT_ID = 951111;
    private static final BigDecimal TRANSFER_SUM = new BigDecimal(250);
    private static MoneyService service;

    @BeforeClass
    public static void initService(){
        service = new MoneyService();
    }

    @Ignore
    @Test
    public void transferMoneyFromSenderTest() throws Exception {
        BigDecimal senderSum = service.getAccountSum(new Long(SENDER_ACCOUNT_ID));
        service.transferMoneyToPerson(new Long(SENDER_ID), RECIPIENT_PHONE, TRANSFER_SUM);
        BigDecimal senderSumAfterTransfer = service.getAccountSum(new Long(SENDER_ACCOUNT_ID));

        assertEquals(senderSumAfterTransfer, senderSum.subtract(TRANSFER_SUM));
    }

    @Ignore
    @Test
    public void transferMoneyToRecipientTest() throws Exception {
        BigDecimal recipientSum = service.getAccountSum(new Long(RECIPIENT_ACCOUNT_ID));
        service.transferMoneyToPerson(new Long(SENDER_ID), RECIPIENT_PHONE, TRANSFER_SUM);
        BigDecimal recipientSumAfterTransfer = service.getAccountSum(new Long(RECIPIENT_ACCOUNT_ID));

        assertEquals(recipientSumAfterTransfer, recipientSum.add(TRANSFER_SUM));
    }
}
