# credit-suisse
A demo project for log processing (multi threaded).

### Tech Stack
1. Apache commons-io
2. Apache log4j
3. Google gson
4. Hyper SQLDB
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

### 

