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
	private static final int VALID_TRACK_MIN_DUR = MORNING_SESS_DUR
			+ MIN_EVE_SESS_DUR;
	private static final Integer MAX_EVE_SESS_DUR = 240;
	private static final int VALID_TRACK_MAX_DUR = MORNING_SESS_DUR
			+ MAX_EVE_SESS_DUR;
	private static Integer TOTAL_INPUT_TIME = 0;

	@Override
	public List<Track> getTrackInfo(List<Topic> topics)
			throws ConfTrackerException {

		for (Topic topic : topics) {
			System.out.println(topic.toString());
			TOTAL_INPUT_TIME = TOTAL_INPUT_TIME + topic.getDuration();
			System.out.println("TOTAL: " + TOTAL_INPUT_TIME);

		}

		System.out.println("Total I/P time ::" + TOTAL_INPUT_TIME);
		int maxTrack = TOTAL_INPUT_TIME / VALID_TRACK_MIN_DUR;
		int maxTrackRem = TOTAL_INPUT_TIME % VALID_TRACK_MIN_DUR;
		System.out.println("MAX Track : <" + maxTrack + "> MAX Track Rem :<"
				+ maxTrackRem + ">");
		int minTrack = TOTAL_INPUT_TIME / VALID_TRACK_MAX_DUR;
		int minTrackRem = TOTAL_INPUT_TIME % VALID_TRACK_MAX_DUR;

		System.out.println("MIN Track : <" + minTrack + "> MIN Track Rem :<"
				+ minTrackRem + ">");

		return null;
	}

	@Override
	public void displayTrackInfo(List<Track> trackList) {
		if (trackList != null && !trackList.isEmpty())
			System.out.println("********** PRINTING TRACKS INFORMATION\n");
		for (int i = 0; i < trackList.size(); i++) {
			System.out.println("Track " + (i + 1) + "\n");
			System.out.println("Morning session 9am -12pm");
			List<Topic> morningSlot = trackList.get(i).getMorningBucket();
			for (Topic topic : morningSlot) {
				System.out.println(topic.getTitle() + "FOR"
						+ topic.getDuration() + "\n");
			}
			System.out.println("Lunch Break from 12pm -1pm \n");
			System.out.println("Evening session 1pm-4/5pm \n");

			List<Topic> eveningSlot = trackList.get(i).getEveningBucket();
			for (Topic topic : eveningSlot) {
				System.out.println(topic.getTitle() + "FOR"
						+ topic.getDuration() + "\n");
			}
			System.out.println("Networking session....");
		}

	}
}
