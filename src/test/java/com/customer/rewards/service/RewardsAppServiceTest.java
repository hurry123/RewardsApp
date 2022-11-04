package com.customer.rewards.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpServerErrorException;

import com.customer.rewards.exception.RewardsAppNoDataException;
import com.customer.rewards.model.RewardsSummary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the {@link RewardsAppService} class
 */
class RewardsAppServiceTest {

    RewardsAppService rewardService = new RewardsAppService();

    private static final String CUSTOMER = "customer01";

    /**
     * Sets up the test class
     */
    @BeforeEach
    public void setup() {
        rewardService.setPurchaseMap();
    }

    /**
     * Tests when valid input of customer id is given, reward points for the customer is returned for three previous months and the total reward points obtained.
     *
     * @throws RewardsAppNoDataException in case of any exception
     */
    @Test
    void testGetRewardsPointsForCustomerWhenValidInputIsGivenExpectValidOutput() throws RewardsAppNoDataException {
        // given //when
        RewardsSummary resultedRewardsSummary = rewardService.getRewardsPointsForCustomer(CUSTOMER);

        // then
        assertEquals(CUSTOMER, resultedRewardsSummary.getCustomerId());
        assertEquals(0, resultedRewardsSummary.getRewardsPointsForFirstMonth());
        assertEquals(320, resultedRewardsSummary.getRewardsPointsForSecondMonth());
        assertEquals(16, resultedRewardsSummary.getRewardsPointsForThirdMonth());
        assertEquals(336, resultedRewardsSummary.getTotalRewardPoints());
    }

    /**
     * Tests when in-valid input of customer id is given, {@link RewardsAppNoDataException} is thrown
     */
    @Test
    void testGetRewardsPointsForCustomerWhenInValidInputIsGivenExpectValidOutput() {
        // given //when
        RewardsAppNoDataException thrown = assertThrows(
                RewardsAppNoDataException.class,
                () -> rewardService.getRewardsPointsForCustomer("123d"),
                "Expected getRewardsPointsForCustomer() to throw, but it didn't"
        );

        // then
        assertTrue(thrown.getMessage().contains("Customer Id doesn't exists, hence no data found"));
    }

}