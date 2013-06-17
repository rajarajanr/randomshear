package com.thoughtworks.sample.conf.track.api;

import java.util.List;

import com.thoughtworks.sample.conf.track.api.exceptions.ConfTrackerException;
import com.thoughtworks.sample.conf.track.api.models.Topic;
import com.thoughtworks.sample.conf.track.api.models.Track;

/**
 * This Interface provides mechanism to manage conferences.
 * 
 */
public interface ConfTrackerAPI {

	/**
	 * This method returns the list of {@link Track} information for a given set
	 * of topic list. It returns at least one complete track or else throws
	 * exception containing meaningful message.
	 */
	List<Track> getTrackInfo(List<Topic> topics) throws ConfTrackerException;

	/**
	 * This method is helpful to display all the topics of each session of eack
	 * track in a printable format.
	 * 
	 */
	void displayTrackInfo(List<Track> trackList);

}
