package com.thoughtworks.sample.conf.track.api.models;

import java.util.List;

/**
 * This class contains information about a track, containing topic information
 * about each session.
 * 
 */
public class Track {

	/**
	 * Contains list of topics which can be covered in morning session.
	 */
	private List<Topic> morningBucket;

	/**
	 * Contains list of topics which can be covered in evening session.
	 */
	private List<Topic> eveningBucket;

	public List<Topic> getMorningBucket() {
		return morningBucket;
	}

	public void setMorningBucket(List<Topic> morningBucket) {
		this.morningBucket = morningBucket;
	}

	public List<Topic> getEveningBucket() {
		return eveningBucket;
	}

	public void setEveningBucket(List<Topic> eveningBucket) {
		this.eveningBucket = eveningBucket;
	}

}
