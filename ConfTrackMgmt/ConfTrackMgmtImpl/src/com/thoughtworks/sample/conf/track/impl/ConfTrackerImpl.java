package com.thoughtworks.sample.conf.track.impl;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.sample.conf.track.api.ConfTrackerAPI;
import com.thoughtworks.sample.conf.track.api.exceptions.ConfTrackerException;
import com.thoughtworks.sample.conf.track.api.exceptions.InvaildArgumentException;
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
	private static Integer BUFFER_TIME = VALID_TRACK_MAX_DUR
			- VALID_TRACK_MIN_DUR;

	@Override
	public List<Track> getTrackInfo(List<Topic> topics)
			throws ConfTrackerException {

		if (topics == null || topics.isEmpty()) {
			throw new InvaildArgumentException(
					"Input list is either null or empty");
		}
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
		// quick failure checks
		initialFaliureCheck(minTrack, minTrackRem, maxTrack, maxTrackRem);

		// for min to max possible tracks
		// try to form tracks
		boolean validSolutionFound = false;
		List<Track> validTracks = new ArrayList<Track>();
		for (int numTrack = minTrack; (numTrack <= maxTrack && !validSolutionFound); numTrack++) {
			validSolutionFound = prepareTracks(numTrack, topics, validTracks);
		}
		if (!validSolutionFound) {
			throw new ConfTrackerException("No Valid Tracks could be created");
		}
		return validTracks;
	}

	private boolean prepareTracks(int numTrack, List<Topic> topics,
			List<Track> validTracks) {

		List<List<Topic>> morningBuckets = prepareMorningBuckets(numTrack,
				topics);
		if (null == morningBuckets) {
			return false;
		}
		List<List<Topic>> eveningBuckets = prepareEveningBuckets(numTrack,
				topics);
		if (null == eveningBuckets) {
			return false;
		}
		if (0 == topics.size()) {
			for (int index = 0; index < numTrack; index++) {
				Track track = new Track();
				track.setMorningBucket(morningBuckets.get(index));
				track.setEveningBucket(eveningBuckets.get(index));
				validTracks.add(track);
			}
			return true;
		}

		return false;
	}

	private List<List<Topic>> prepareMorningBuckets(int numTrack,
			List<Topic> topics) {
		List<Topic> morningBucket = prepareMorningBucket(topics);
		return null;
	}

	private List<Topic> prepareMorningBucket(List<Topic> topics) {
		int totalTime = 0;
		int diff = MORNING_SESS_DUR - totalTime;

		return null;
	}

	private void initialFaliureCheck(int minTrack, int minTrackRem,
			int maxTrack, int maxTrackRem) throws ConfTrackerException {
		// Not a single track can be completed..
		if (maxTrack < 1) {
			throw new ConfTrackerException("No track can be created.");
		}
		// Incomplete track
		if (maxTrackRem < VALID_TRACK_MIN_DUR
				&& minTrackRem > (minTrack * BUFFER_TIME)
				&& minTrackRem < VALID_TRACK_MIN_DUR) {
			throw new ConfTrackerException("One of track can not be completed.");
		}

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
