package com.customer.rewards.model;

public class RewardsSummary {

	private String customerId;
	private int rewardsPointsForFirstMonth;
	private int rewardsPointsForSecondMonth;
	private int rewardsPointsForThirdMonth;

	public int getRewardsPointsForFirstMonth() {
		return rewardsPointsForFirstMonth;
	}

	public void setRewardsPointsForFirstMonth(int rewardsPointsForFirstMonth) {
		this.rewardsPointsForFirstMonth = rewardsPointsForFirstMonth;
	}

	public int getRewardsPointsForSecondMonth() {
		return rewardsPointsForSecondMonth;
	}

	public void setRewardsPointsForSecondMonth(int rewardsPointsForSecondMonth) {
		this.rewardsPointsForSecondMonth = rewardsPointsForSecondMonth;
	}

	public int getRewardsPointsForThirdMonth() {
		return rewardsPointsForThirdMonth;
	}

	public void setRewardsPointsForThirdMonth(int rewardsPointsForThirdMonth) {
		this.rewardsPointsForThirdMonth = rewardsPointsForThirdMonth;
	}

	private int totalRewardPoints;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getTotalRewardPoints() {
		return totalRewardPoints;
	}

	public void setTotalRewardPoints(int totalRewardPoints) {
		this.totalRewardPoints = totalRewardPoints;
	}
}
