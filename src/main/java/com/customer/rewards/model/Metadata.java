package com.customer.rewards.model;

/**
 * Model to carry metadata.
 */
public class Metadata {

	private String status;
	private String description;

	/**
	 * Gets the status.
	 *
	 * @return the status string
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 * @param status the status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description string
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 * @param description the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
