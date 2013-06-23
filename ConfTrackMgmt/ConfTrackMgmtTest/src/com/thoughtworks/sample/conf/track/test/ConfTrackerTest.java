package com.thoughtworks.sample.conf.track.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.sample.conf.track.api.ConfTrackerAPI;
import com.thoughtworks.sample.conf.track.api.models.Topic;
import com.thoughtworks.sample.conf.track.api.models.Track;
import com.thoughtworks.sample.conf.track.impl.ConfTrackerImpl;

public class ConfTrackerTest {

	@Test
	public void getTrackInfoTest() throws Exception {

		ConfTrackerAPI API = new ConfTrackerImpl();
		List<Track> output = API.getTrackInfo(prepareInputs());
		System.out.println("FINAL Output:" + output);
		// Assert.assertNotNull(output);
		// Assert.assertTrue(output.size() > 0);
	}

	private List<Topic> prepareInputs() {

		List<Topic> topics = new ArrayList<Topic>();
		topics.add(new Topic("Writing Fast Tests Against Enterprise Rails", 60));
		topics.add(new Topic("Overdoing it in Python", 45));
		topics.add(new Topic("Lua for the Masses", 30));
		topics.add(new Topic("Ruby Errors from Mismathed Gem V", 45));
		topics.add(new Topic("Common Ruby Error", 45));
		topics.add(new Topic("Rails for Python Developers", Topic.LIGHTNING));
		topics.add(new Topic("Communicating over Over Distance", 60));
		topics.add(new Topic("Accounting Driven Developement", 45));
		topics.add(new Topic("Woah", 30));
		topics.add(new Topic("Sit down and write", 30));
		topics.add(new Topic("Pair Programming vs Noise", 45));
		topics.add(new Topic("Rails Magic", 60));
		topics.add(new Topic("Ruby on Rails", 60));
		topics.add(new Topic("Clojure Ate Scala", 45));
		topics.add(new Topic("Programming on boondocks of Seattle", 30));
		topics.add(new Topic("Ruby vs Clojure for BackEnd Devlopement", 30));
		topics.add(new Topic("Ruby on Rails Legacy App Maintenance", 60));
		topics.add(new Topic("A World without Hacker News", 30));
		topics.add(new Topic("User Interface CSS with Rails Apps", 30));

		return topics;
	}
}
