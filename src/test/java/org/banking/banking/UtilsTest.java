package org.banking.banking;

import org.banking.banking.utils.BankingUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsTest {

    @Test
    public void testPhoneValidation() {
        String phone = "+79819668126";
        boolean result = BankingUtils.isValidPhone(phone);
        Assert.assertFalse(result);

        phone = "abc";
        result = BankingUtils.isValidPhone(phone);
        Assert.assertFalse(result);

        phone = "7981+9668126";
        result = BankingUtils.isValidPhone(phone);
        Assert.assertFalse(result);

        phone = "79819asf668126";
        result = BankingUtils.isValidPhone(phone);
        Assert.assertFalse(result);

        phone = "79819668126";
        result = BankingUtils.isValidPhone(phone);
        Assert.assertTrue(result);

    }

}
