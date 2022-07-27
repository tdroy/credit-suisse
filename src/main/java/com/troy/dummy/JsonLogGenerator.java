package com.troy.dummy;

import java.io.FileWriter;

import org.json.simple.JSONObject;

public class JsonLogGenerator {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		System.out.println("******************************************");
		System.out.println("****  Credit Suisse Demo Application  ****");
		System.out.println("****     Genrating dummy test logs    ****");
		System.out.println("****        	By Tanmoy Roy.        ****");
		System.out.println("******************************************");
		
		System.out.println("Usage : java -cp credit-suisse-0.0.1-SNAPSHOT-jar-with-dependencies.jar JsonLogGenerator <number of lines> ");
		
		
		JSONObject objStarted = new JSONObject();  
		JSONObject objFinished = new JSONObject();
		
		int i = 1;
		int max = 100000;
		
		if (args.length > 0)
			max = Integer.parseInt(args[0]);
		
		if (args.length > 1)
		{
			System.out.println("Invalid arguments..!");
			System.exit(0);
		}
		
		try 
		{
			FileWriter file = new FileWriter("output.json");
			
			while (i <= max) {

			  
			  if ( i % 5 == 0)
			  {
				  //After every 5 iteration, write 'FINISHED' line first the 'STARTED' line, as Start evet will always
				  //have eralier time stamp, making it sleep 
				  objStarted.put("id","str" + i);    
				  objStarted.put("state","STARTED");    
				  objStarted.put("timestamp",System.currentTimeMillis()); 
				  System.out.println(objStarted); 
				  
				  
				  objFinished.put("id","str" + i);    
				  objFinished.put("state","FINISHED");  
				  objFinished.put("timestamp",System.currentTimeMillis() + 5); // adding i to timestamp for artificial delay. 
				  System.out.println(objFinished); 
				  
				  file.write(objFinished.toJSONString());
				  file.write(System.getProperty( "line.separator" ));


				  file.write(objStarted.toJSONString());
				  file.write(System.getProperty( "line.separator" ));
				  
				  objStarted.clear();
				  objStarted.clear();
			  }
			  else {
				  objStarted.put("id","str" + i);    
				  objStarted.put("state","STARTED");    
				  objStarted.put("type","App_logs");  
				  objStarted.put("host","x.y.z.o");  
				  objStarted.put("timestamp",System.currentTimeMillis()); 
				  System.out.println(objStarted);
				  
				  
				  objFinished.put("id","str" + i);    
				  objFinished.put("state","FINISHED"); 
				  objFinished.put("type","App_logs");  
				  objFinished.put("host","x.y.z.o");
				  objFinished.put("timestamp",System.currentTimeMillis() + 3); 
				  
				  System.out.println(objFinished); 

				  file.write(objStarted.toJSONString());
				  file.write(System.getProperty( "line.separator" ));
				  
				  file.write(objFinished.toJSONString());
				  file.write(System.getProperty( "line.separator" ));
				  
				  objStarted.clear();
				  objFinished.clear();
			  }

			  i++;
			}
			  file.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
