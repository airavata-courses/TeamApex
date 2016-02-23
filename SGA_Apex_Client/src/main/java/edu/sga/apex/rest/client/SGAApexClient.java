package edu.sga.apex.rest.client;

import java.util.Scanner;

public class SGAApexClient {
	
	public static void submitJob(){
		
	}
	
	public static void monitorJob(){
		
	}
	
	public static void deleteJob(){
		
	}

	public static void main(String[] args) {
		int choice;
		Scanner input = new Scanner(System.in);
		do
		{	
			System.out.println("Please enter 1 - Submit job, 2 - Monitor job, 3 - Cancel job and 4 - exit");
			choice = input.nextInt();
			switch(choice){
			case 1:
				SGAApexClient.submitJob();
				break;
			case 2:
				SGAApexClient.monitorJob();
				break;
			case 3:
				SGAApexClient.deleteJob();
				break;
			case 4:
				break;
			default:
				System.out.println("Incorrect input - Must enter 1 - Submit job, 2 - Monitor job, 3 - Cancel job and 4 - exit");
				break;
			}	
		}while(choice != 4);
		input.close();
	}

}
