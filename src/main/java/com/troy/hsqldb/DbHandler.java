package com.troy.hsqldb;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.troy.Event;

public class DbHandler extends Thread {

	final static Logger logger =  LogManager.getLogger(DbHandler.class);
	
	List<Event> eventsList = new LinkedList<Event>();
	boolean isContinue = true;
	
	public DbHandler(List<Event> eventsList) {
		super();
		this.eventsList = eventsList;
	}

	@Override
	public void run() {

		String createTable = "CREATE TABLE IF NOT EXISTS EVENT "
				+ "(id varchar(30), duration int, type varchar(30), host varchar(20), alert boolean)";
		
		String insertSQl = "INSERT INTO EVENT (id, duration, type, host, alert)"
				+ "values(?,?,?,?,?);";
		
		try (Connection connection = DbConnection.getConnection();
	            Statement statement = connection.createStatement();) {

			DatabaseMetaData dbm = connection.getMetaData();
			// check if Event table is there
			ResultSet tables = dbm.getTables(null, null, "EVENT", null);
			if (tables.next()) 
			{
	        		logger.debug("DB EVENT Table already present.");
			}
			else {
				statement.execute(createTable);
        		logger.debug("DB EVENT Table created.");
			}
        	statement.close();
			
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQl);
            logger.debug("Starting DB insertion...");
            logger.debug("Total batch size : " + eventsList.size());
            
            List<Event> removeList = new ArrayList<Event>();
            
            //Continue the loop this the time log read is on going or event list has elements
            while (isContinue || eventsList.size() >0) {
	            for (Iterator<Event> iterator = eventsList.iterator(); iterator.hasNext();) {
	            	Event event = iterator.next();
	            	
	                preparedStatement.setString(1, event.getId());
	                preparedStatement.setFloat(2, event.getTimestamp());
	                preparedStatement.setString(3, event.getType());
	                preparedStatement.setString(4, event.getHost());
	                preparedStatement.setBoolean(5, (event.getTimestamp() > 4)? true :false );
	
	                preparedStatement.executeUpdate();
	                logger.debug("Inserted event id '" + event.getId() + "' to database");
	                //Once record inserted to DB, remove from eventlist array to free up memory.
	                removeList.add(event);
	            }
	            //remove inserted events from events list
	            eventsList.removeAll(removeList);
            }
            connection.close();
            logger.info("All events are inserted to Database.");
	     }
		catch(Exception e) {
			e.printStackTrace();
			logger.error("Execution failed" );
		}
		
	}
	
	//method to indicate read is complete and while loop to break when list will be empty
	public void changeState(boolean isContinue) {
		this.isContinue = isContinue;
	}

}
