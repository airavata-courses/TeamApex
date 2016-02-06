package edu.sga.apex.impl;

import java.io.*;
import java.util.Scanner;
import edu.sga.apex.bean.SCPRequestBean;
import edu.sga.apex.interfaces.SCInterface;

public class KarstSCImpl implements SCInterface{
	
	private static final String script_path = "template/karst_job.script";
	private static final String local_output_path = System.getProperty("user.home") + "/temp.script";
	
	public void createJobScript(){
		Scanner input = new Scanner(System.in);
		
		File tempFile = new File(local_output_path);
				
		try 
		{
			InputStreamReader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(script_path));
			BufferedReader br = new BufferedReader(reader);
			
			tempFile.getParentFile().mkdirs();
			PrintWriter pw = new PrintWriter(tempFile);
			
			String line = null, job_name = null;
			while((line = br.readLine()) != null){
				if(line.contains("$nodes")){
					System.out.println("Please enter the number of nodes");
					String nodes = input.nextLine();
					line = line.replace("$nodes", nodes);
				}
				if(line.contains("$processors_per_node")){
					System.out.println("Please enter the processors per node");
					String ppn = input.nextLine();
					line = line.replace("$processors_per_node", ppn);
					
				}
				if(line.contains("$emailId")){
					System.out.println("Please enter the email id");
					String emailId = input.nextLine();
					line = line.replace("$emailId", emailId);
				}
				if(line.contains("$job_name") && job_name == null){
					System.out.println("Please enter the job");
					 job_name = input.nextLine();
					line = line.replace("$job_name", job_name);
				}				
				pw.println(line);
				pw.flush();
			}
			pw.close();
			br.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
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
	
	public static void main(String[] args) {
		KarstSCImpl karst = new KarstSCImpl();
		karst.createJobScript();		
	}

}
