package com.cie2.tip.pages;

import java.util.List;

import org.apache.tapestry5.annotations.Property;

import com.cie2.tip.components.base.CieUserPage;
import com.cie2.tip.entities.User;

public class MyProfile extends CieUserPage{

	private Boolean newTask;
	
	private List userProject;
	
	
	boolean isCondition() {
		System.out.println("TESTING CONDITION");
		return true;
	}	
}
