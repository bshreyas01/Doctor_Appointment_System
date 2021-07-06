package client;
import presentation.*;
import service.*;

import java.util.Scanner;


public class Appmain {

	public static void main(String[] args) throws Exception {
		
		Scanner sc=new Scanner(System.in);
		
		work();
		
	}
	public static void work() throws Exception {
		
		boolean flag=true;
		
		while(flag) {
			Applpresentation.startappmenu();
			Applpresentation.performmenu();
			flag=false;
		}
	  }
}

