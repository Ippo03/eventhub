package com.example.eventhub.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Test class for the {@link Money} utility class.
 */
public class MoneyTest {
    private Currency euroCurrency = Currency.getInstance("EUR");
    private Currency usdCurrency = Currency.getInstance("USD");
    Money money1;
    Money money2;
    Money money3;

    /**
     * Set up method to initialize test objects.
     */
    @Before
    public void setUp() {
        money1 = new Money(new BigDecimal(100), euroCurrency);
        money2 = new Money(new BigDecimal(50), euroCurrency);
        money3 = new Money(new BigDecimal(50), usdCurrency);
    }

    /**
     * Test case for setting null amount in {@link Money}.
     */
    @Test
    public void setNullForAmount() {
        money1.setAmount(null);
        Assertions.assertEquals(new BigDecimal(100).setScale(2, BigDecimal.ROUND_HALF_UP), money1.getAmount());
    }

    /**
     * Test case for setting amount in {@link Money}.
     */
    @Test
    public void setAmount() {
        money1.setAmount(new BigDecimal(50));
        Assertions.assertEquals(new BigDecimal(50).setScale(2, BigDecimal.ROUND_HALF_UP), money1.getAmount());
    }

    /**
     * Test case for setting null currency in {@link Money}.
     */
    @Test
    public void setNullForCurrency() {
        money1.setCurrency(null);
        Assertions.assertEquals(euroCurrency, money1.getCurrency());
    }

    /**
     * Test case for setting USD currency in {@link Money}.
     */
    @Test
    public void setUsdCurrency() {
        money1.setCurrency(usdCurrency);
        Assertions.assertEquals(usdCurrency, money1.getCurrency());
    }

    /**
     * Test case for adding {@link Money} instances with the same currencies.
     */
    @Test
    public void plusSameCurrencies() {
        Money result = money1.plus(money2);
        Assertions.assertEquals(new BigDecimal(150).setScale(2, BigDecimal.ROUND_HALF_UP), result.getAmount());
        Assertions.assertEquals(euroCurrency, result.getCurrency());
    }

    /**
     * Test case for attempting to add {@link Money} instances with different currencies.
     */
    @Test(expected = IllegalArgumentException.class)
    public void plusDifferentCurrencies() {
        money1.plus(money3);
    }

    /**
     * Test case for subtracting {@link Money} instances with the same currencies.
     */
    @Test
    public void minusSameCurrencies() {
        Money result = money1.minus(money2);
        Assertions.assertEquals(new BigDecimal(50).setScale(2, BigDecimal.ROUND_HALF_UP), result.getAmount());
    }

    /**
     * Test case for attempting to subtract {@link Money} instances with different currencies.
     */
    @Test(expected = IllegalArgumentException.class)
    public void minusDifferentCurrencies() {
        money1.minus(money3);
    }

    /**
     * Test case for multiplying {@link Money} with a {@link BigDecimal}.
     */
    @Test
    public void multiplyWithBigDecimal() {
        Money result = money1.times(money2.getAmount());
        Assertions.assertEquals(new BigDecimal(5000).setScale(2, BigDecimal.ROUND_HALF_UP), result.getAmount());
    }

    /**
     * Test case for multiplying {@link Money} with a double value.
     */
    @Test
    public void multiplyWithDouble() {
        Money result = money1.times(0.5);
        Assertions.assertEquals(new BigDecimal(50).setScale(2, BigDecimal.ROUND_HALF_UP), result.getAmount());
    }

    /**
     * Test case for testing equals and hashCode methods.
     */
    @Test
    public void equalsAndHashCode() {
        com.example.eventhub.util.BasicEqualTester<Money> equalsTester = new BasicEqualTester<Money>();

        equalsTester.setObjectUnderTest(new Money(null,null));
        equalsTester.otherObjectIsNull();
        equalsTester.otherObjectIsOfDifferentType(new Object());
        equalsTester.bothObjectsHaveNoState(new Money(null,null));

        equalsTester.setObjectUnderTest(Money.euros(new BigDecimal(5)));
        equalsTester.otherObjectIsNull();
        equalsTester.otherObjectsHasNoState(new Money(null,null));
        equalsTester.objectsHaveDifferentState(Money.euros(new BigDecimal(10)));

        equalsTester.sameReferences(equalsTester.getObjectUnderTest());
        equalsTester.bothObjectsHaveSameState(Money.euros(new BigDecimal(5)));
    }
}