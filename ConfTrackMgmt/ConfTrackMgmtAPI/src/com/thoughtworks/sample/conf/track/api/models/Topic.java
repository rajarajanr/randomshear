package com.thoughtworks.sample.conf.track.api.models;

/**
 * This class will contain the information about each topic( Topic title and
 * duration). Assuming that duration will be always in minutes.
 * 
 */
public class Topic {

	public static final Integer LIGHTNING = 5;// unit in minute

	private final String title;

	/**
	 * Duration will always be in minutes.
	 */
	private final Integer duration;

	public Topic(String title, Integer duration) {
		this.title = title;
		this.duration = duration;
	}

	public String getTitle() {
		return title;
	}

	public Integer getDuration() {
		return duration;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Topic [title=");
		builder.append(title);
		builder.append(", duration=");
		builder.append(duration);
		builder.append("]");
		return builder.toString();
	}

}
