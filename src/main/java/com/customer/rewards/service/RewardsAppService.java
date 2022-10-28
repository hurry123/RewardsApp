package com.customer.rewards.service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.customer.rewards.exception.RewardsAppNoDataException;
import com.customer.rewards.model.Purchase;
import com.customer.rewards.model.RewardsSummary;

/**
 * Service class to calculate the rewards points.
 */
@Service
public class RewardsAppService implements RewardService{

    private final Map<String, Purchase> purchaseMap = new HashMap<>();

    /**
     * Sets up the data as soon as bean {@link RewardService} is constructed.
     */
    @PostConstruct
    public void setPurchaseMap() {
        purchaseMap.put("customer01-purchase01", buildPurchase("customer01", "purchase01", LocalDate.of(2022, 8, 27) , BigDecimal.valueOf(66.54)));
        purchaseMap.put("customer01-purchase02", buildPurchase("customer01", "purchase02", LocalDate.of(2022, 9, 11), BigDecimal.valueOf(130.44)));
        purchaseMap.put("customer01-purchase03", buildPurchase("customer01", "purchase03", LocalDate.of(2022, 9, 2), BigDecimal.valueOf(180.23)));
        purchaseMap.put("customer01-purchase04", buildPurchase("customer01", "purchase04", LocalDate.of(2022, 10, 11), BigDecimal.valueOf(49.99)));

    }

    /**
     * Builds instance of {@link Purchase}
     * @param customerId the customer id
     * @param purchaseId the purchase id
     * @param purchaseDate the purchase date
     * @param purchaseAmount the purchase amount
     * @return instance of {@link Purchase}
     */
    private Purchase buildPurchase(String customerId, String purchaseId, LocalDate purchaseDate, BigDecimal purchaseAmount) {
        return new Purchase(customerId, purchaseId, purchaseDate, purchaseAmount);
    }

    @Override
    public RewardsSummary getRewardsPointsForCustomer(String customerId) throws RewardsAppNoDataException {
        Set<Map.Entry<String, Purchase>> entries = purchaseMap.entrySet();

        List<Purchase> purchases = new ArrayList<>();

        for(Map.Entry<String,Purchase> entry : entries) {
            String customerKey = entry.getKey().split("-")[0];
            if(customerKey.equals(customerId)) {
                purchases.add(entry.getValue());
            }
        }

        List<Purchase> purchasesForFirstMonth = purchases.stream().filter(purchase -> purchase.getPurchaseDate().isBefore(LocalDate.now().minusMonths(1)))
                .collect(Collectors.toList());

        List<Purchase> purchasesForSecondMonth = purchases.stream().filter(purchase -> purchase.getPurchaseDate().isBefore(LocalDate.now().minusMonths(3)))
                .collect(Collectors.toList());

        List<Purchase> purchasesForThirdMonth = purchases.stream().filter(purchase -> purchase.getPurchaseDate().isBefore(LocalDate.now().minusMonths(3)))
                .collect(Collectors.toList());

        int rewardsPointsForFirstMonth = calculateRewardPointsPerMonth(purchasesForFirstMonth);
        int rewardsPointsForSecondMonth = calculateRewardPointsPerMonth(purchasesForSecondMonth);
        int rewardsPointsForThirdMonth = calculateRewardPointsPerMonth(purchasesForThirdMonth);

        RewardsSummary rewardsSummary = new RewardsSummary();
        rewardsSummary.setRewardsPointsForFirstMonth(rewardsPointsForFirstMonth);
        rewardsSummary.setRewardsPointsForSecondMonth(rewardsPointsForSecondMonth);
        rewardsSummary.setRewardsPointsForThirdMonth(rewardsPointsForThirdMonth);

        int calculatedRewardPoints = rewardsPointsForFirstMonth +  rewardsPointsForSecondMonth + rewardsPointsForThirdMonth;

        rewardsSummary.setTotalRewardPoints(calculatedRewardPoints);
        rewardsSummary.setCustomerId(customerId);

        if(CollectionUtils.isEmpty(purchases) || calculatedRewardPoints == -1) {
            throw new RewardsAppNoDataException("Customer Id doesn't exists, hence no data found", "FAILURE");
        }
        return rewardsSummary;

    }

    /**
     * Calculates the reward points for the given list of {@link Purchase}
     * @param purchases the purchases
     * @return total rewards points
     */
    private int calculateRewardPointsPerMonth(List<Purchase> purchases) {

        int totalRewardPoints = 0;
        for(Purchase purchase : purchases) {
                BigDecimal totalAmount = purchase.getPurchaseAmount();
                if(totalAmount.compareTo(BigDecimal.valueOf(50)) > 0) { // >50 // 120
                    if(totalAmount.compareTo(BigDecimal.valueOf(100)) < 0) { // >50 && < 100
                        totalAmount = BigDecimal.valueOf(100).subtract(totalAmount);
                        totalRewardPoints += totalAmount.intValue();
                    } else {
                        totalAmount = totalAmount.subtract(BigDecimal.valueOf(100)); // > 100
                        totalRewardPoints += 50; // total amount is now 20
                        totalRewardPoints += 2 * totalAmount.intValue();
                    }
                }
        }
        return totalRewardPoints;
    }

}

