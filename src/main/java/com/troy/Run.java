package com.troy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.troy.Event;
import com.troy.hsqldb.*;


public class Run {
	
	final static Logger logger =  LogManager.getLogger(Run.class);
	
	public static void main(String[] args) throws InterruptedException {
		
		logger.info("******************************************");
		logger.info("****  Credit Suisse Demo Application  ****");
		logger.info("****    Reading log event to HSQLDB   ****");
		logger.info("****        	By Tanmoy Roy.         ****");
		logger.info("******************************************");
		
		Run run = new Run();
        long start = System.currentTimeMillis();

        //Default log file name & path
        String logFilePath = "logfile.txt";
        
        //Log file path arrgument passed then set the file path
        if (args.length > 0){
        	logFilePath = args[0];
        }

        if(args.length >= 2) {
        	logger.info("Invlaid command.\n Usage : java -jar credit-suisse-0.0.1-SNAPSHOT-jar-with-dependencies.jar <Log file path>");
        	System.exit(0);
        }
        
        //List<Event> eventsList = Collections.synchronizedList(new ArrayList<Event>());
        List<Event> events = new ArrayList<Event>();
        List<Event> eventsList = new CopyOnWriteArrayList<>(events);
        
        LogReader logReader = new LogReader(logFilePath, eventsList);
        logReader.start();        
        
        DbHandler dbHandler = new DbHandler(eventsList);
        dbHandler.start();
        
        logReader.join();
        dbHandler.changeState(false);
        dbHandler.join();

        if (logger.isDebugEnabled())
        	run.dbView();

        System.out.println("Log processing completed in : " + (System.currentTimeMillis() -start )/1000 + " secs");
	}

	
	public void dbView() {
		String SQL = "SELECT * FROM EVENT";
				
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement  preparedStatement  = connection.prepareStatement(SQL);) {
			
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("\n========================= Event Table =========================");
            while (rs.next()) {
                System.out.println(rs.getString("id")+ " | " + rs.getString("duration") + " | " + rs.getString("type") + " | " + rs.getString("host") + " | " + rs.getBoolean("alert"));
            }
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
