package com.customer.rewards.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.customer.rewards.exception.RewardsAppNoDataException;
import com.customer.rewards.model.RewardResponse;
import com.customer.rewards.model.RewardsSummary;
import com.customer.rewards.model.Metadata;
import com.customer.rewards.service.RewardService;

/**
 * Handles the endpoint uri for the rewards calculation.
 */
@RestController
public class RewardsAppController {

	private static final String STATUS_SUCCESS = "SUCCESS";

	private static final String STATUS_FAILED = "FAILED";

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RewardService rewardService;

	/**
	 * Retrieves rewards summary for the given customer id.
	 * @param customerId the customer id
	 *
	 * @return the response with {@link RewardsSummary}
	 */
	@RequestMapping(value = "/rewards/{customerId}", produces = {
			"application/json" }, method = RequestMethod.GET)
	public ResponseEntity<RewardResponse<RewardsSummary>> getRewardsPointsForCustomer(@PathVariable String customerId) {
		RewardResponse<RewardsSummary> response = new RewardResponse<>();
		Metadata metadata = new Metadata();
		RewardsSummary rewardsSummary;
		try {
			logger.info("Fetching Reward Summary for CustomerID : {}", customerId);
			rewardsSummary = rewardService.getRewardsPointsForCustomer(customerId);
			logger.info("Successfully received response for CustomerID : {}", customerId);
			response.setCustomerInfo(rewardsSummary);
			metadata.setStatus(STATUS_SUCCESS);
			metadata.setDescription("Successfully calculated rewards points!");
			response.setMetadata(metadata);
		} catch (RewardsAppNoDataException e) {
			metadata.setStatus(STATUS_FAILED);
			metadata.setDescription(e.getMessage());
			response.setMetadata(metadata);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
