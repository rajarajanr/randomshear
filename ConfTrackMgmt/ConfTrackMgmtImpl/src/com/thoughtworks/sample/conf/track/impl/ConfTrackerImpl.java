package com.thoughtworks.sample.conf.track.impl;

import java.util.List;

import com.thoughtworks.sample.conf.track.api.ConfTrackerAPI;
import com.thoughtworks.sample.conf.track.api.exceptions.ConfTrackerException;
import com.thoughtworks.sample.conf.track.api.models.Topic;
import com.thoughtworks.sample.conf.track.api.models.Track;

/**
 * This is an implementation class, contains logic to prepare Track(s) for a day
 * of the conference.
 * 
 */
public class ConfTrackerImpl implements ConfTrackerAPI {

	private static final Integer MORNING_SESS_DUR = 180;
	private static final Integer MIN_EVE_SESS_DUR = 180;
	private static final Integer MAX_EVE_SESS_DUR = 240;

	@Override
	public List<Track> getTrackInfo(List<Topic> topics)
			throws ConfTrackerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void displayTrackInfo(List<Track> trackList) {
		// TODO Auto-generated method stub

	}

}
