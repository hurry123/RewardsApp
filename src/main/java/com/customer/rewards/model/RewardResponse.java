package com.customer.rewards.model;

public class RewardResponse<T> {

	Metadata metadata;
	T customerInfo;

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public T getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(T customerInfo) {
		this.customerInfo = customerInfo;
	}

}
