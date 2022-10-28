package com.customer.rewards.service;

import com.customer.rewards.exception.RewardsAppNoDataException;
import com.customer.rewards.model.RewardsSummary;

/**
 * Service class to calculate rewards points for the customer.
 */
public interface RewardService {
    /**
     * Gets the reward points for the given customer id.
     * @param customerId the customer id
     * @return total reward points
     * @throws RewardsAppNoDataException in case of no data found.
     */
    public RewardsSummary getRewardsPointsForCustomer(String customerId) throws RewardsAppNoDataException;
}
