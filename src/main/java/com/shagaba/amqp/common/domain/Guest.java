package com.shagaba.amqp.common.domain;

public class Guest {

	private String content;

	/**
	 * 
	 */
	public Guest() {
	}

	/**
	 * @param content
	 */
	public Guest(String content) {
		this.content = content;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Guest {content=%s}", content);
	}

}
