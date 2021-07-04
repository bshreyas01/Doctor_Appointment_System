package service;

import presentation.Applpresentation;

public class Work {
	
	public void work() throws Exception {

//	Applpresentation presentation=new Applpresentation();
	
	boolean flag=true;
	
	while(flag) {
		Applpresentation.startappmenu();
		Applpresentation.performmenu();
		flag=false;
	}
  }
}
