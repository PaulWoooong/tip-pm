package com.cie2.tip.services;

import com.cie2.tip.entities.TaskItem;
import com.cie2.tip.entities.User;

public class VoteManager {

	public void addVote(TaskItem taskItem, User user) {
		taskItem.setVote(taskItem.getVote() + 1);
	}
}
