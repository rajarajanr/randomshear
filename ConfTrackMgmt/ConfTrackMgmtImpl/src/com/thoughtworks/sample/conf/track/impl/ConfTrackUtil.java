package com.thoughtworks.sample.conf.track.impl;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.sample.conf.track.api.models.Topic;

/**
 * This is an utility class which contains methods to prepare the track
 * 
 */
public class ConfTrackUtil {

	public static List<Topic> prepareSingleBucket(List<Topic> numbers,
			int target, boolean isMorningSession) {
		List<Topic> list = new ArrayList<Topic>();
		subsetSumRecur(numbers, target, list, isMorningSession);
		// System.out.println("### result found::" + found);
		// System.out.println("LIST :" + list);
		return list;

	}

	private static boolean subsetSumRecur(List<Topic> numbers, int target,
			List<Topic> partial, boolean isMorningSession) {
		Integer sum = 0;
		for (Topic topic : partial) {
			sum += topic.getDuration();
		}
		// check if the partial sum is equal to target
		if (isMorningSession && sum == target) {
			// System.out.println("Sum=" + partial + "=" + target);
			return true;
		} else if (!isMorningSession && sum >= target && sum <= (target + 60)) {
			return true;
		} else if (isMorningSession && sum > target) {
			partial.remove(partial.size() - 1);
			return false;
		} else if (!isMorningSession && sum > (target)) {
			partial.remove(partial.size() - 1);
			return false;
		}

		for (int i = 0; i < numbers.size(); i++) {
			Topic n = numbers.get(i);
			List<Topic> remaining = numbers.subList(i + 1, numbers.size());
			partial.add(n);
			if (subsetSumRecur(remaining, target, partial, isMorningSession)) {
				return true;
			}
		}
		if (!partial.isEmpty()) {
			partial.remove(partial.size() - 1);
		}
		return false;
	}
}
