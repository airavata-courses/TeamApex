package edu.sga.apex.impl;

import java.util.Scanner;
import edu.sga.apex.bean.SCPRequestBean;
import edu.sga.apex.interfaces.SCInterface;

public class KarstSCImpl implements SCInterface{
	
	/**
	 * 
	 */
	public void createJobScript(){
		Scanner input = new Scanner(System.in);
		input.close();
	}
	
	@Override
	public String submitJob(SCPRequestBean SCPRequestBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String copyFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String monitorJob() {
		// TODO Auto-generated method stub
		return null;
	}

}
