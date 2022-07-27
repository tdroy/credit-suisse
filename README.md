# credit-suisse
A demo project for log processing (multi threaded).

### Tech Stack
1. Apache commons-io
2. Apache log4j
3. Google gson
4. Hyper SQLDB (Embedded)
5. Apache maven (build)

### Usage
The utility can be build using maven.
1. Checkout the project.
2. Run 'mvn test'
3. Project contain a dummy log file 'logfile.txt' (10k events in log)
4. Test will be done using above mention log file.
5. This will create executable jar file 'credit-suisse-0.0.1-SNAPSHOT-jar-with-dependencies.jar' inside target/ folder. Include all dependencies.
6. OPTIONAL. In case need to change the log file name at test phase, then edit the pom to pass the file name as arrument.
``` xml
	  <arguments>
	     <argument>logfile.txt</argument>
	  </arguments>
```

Run the utility standalone.
```
java -jar credit-suisse-0.0.1-SNAPSHOT-jar-with-dependencies.jar <Path to log file>
```

### Utility for dummy log generation
It is poosible to generate dummy log file. B
```
java -cp credit-suisse-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.troy.dummy.JsonLogGenerator <number of events>
```

### Default
1. Hsqldb - Database name 'demo' will be create in the present directoy under 'DB" folder.
2. log4j - configuration file is set to console appender with log level 'info'. 
3. log file - defalu log file is picked from current directory name 'logfile.txt'

