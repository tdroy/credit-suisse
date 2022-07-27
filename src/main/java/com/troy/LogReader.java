package com.troy;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class LogReader extends Thread{
	
	final static Logger logger =  LogManager.getLogger(LogReader.class);
	
	String logFilePath;
	List<Event> eventsList = new LinkedList<Event>();
	
	public LogReader(String logFilePath,List<Event> eventsList) {
		super();
		this.logFilePath = logFilePath;
		this.eventsList = eventsList;
	}


	@Override
	public void run() {

        List<Event> eventsListTemp = new LinkedList<Event>();
        
        logger.info("Reading log file : " + logFilePath);
        
        try(JsonReader jsonReader = new JsonReader(new InputStreamReader(new FileInputStream(logFilePath), StandardCharsets.UTF_8))) {
            Gson gson = new GsonBuilder().create();
            jsonReader.setLenient(true);
            int numberOfEvents = 0;

            while (jsonReader.hasNext()){
                Event event = gson.fromJson(jsonReader, Event.class);
                
                //Check if event already ccapture
                Event result =  eventsListTemp.stream().filter(evt -> evt.id.equalsIgnoreCase(event.id)).findFirst().orElse(null);
                //If capture then calculate time taken, else 1st entry add to list.
                if (result != null) 
                {
               		logger.debug("ID already present : " + result.id);
                	//Calculate time different. As order of entry is not maintained for start & finished, time subtraction
                	//leads to -ve number. Mat.abs used for making absolute number. 
                	result.setTimestamp(Math.abs(event.getTimestamp() - result.getTimestamp()));

                	eventsList.add(result);
                	numberOfEvents++;
                	//Once operation is complete remove the event object from list.
                	eventsListTemp.remove(result);
                }
                else {
                	logger.debug("New event added : " + event.id);
                	eventsListTemp.add(event);
                } 
            }
            jsonReader.close();
            eventsList.forEach(evt -> logger.debug("id : " + evt.id + " state : " + evt.state + " type : " + evt.type 
            		+ " host : " + evt.host + " timestamp : " + evt.timestamp) );
            logger.info("Log read completed. Total events counts : " + numberOfEvents);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("Execution failed !" );
        }
        
	}
	
	

}
