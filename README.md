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
3. Input log - defalu log file is picked from current directory name 'logfile.txt'

### Sample Output
Log level - info
```
2022-07-27 09:16:36 INFO  Run:22 com.troy.Run.main() - ******************************************
2022-07-27 09:16:36 INFO  Run:23 com.troy.Run.main() - ****  Credit Suisse Demo Application  ****
2022-07-27 09:16:36 INFO  Run:24 com.troy.Run.main() - ****    Reading log event to HSQLDB   ****
2022-07-27 09:16:36 INFO  Run:25 com.troy.Run.main() - ****        	By Tanmoy Roy.         ****
2022-07-27 09:16:36 INFO  Run:26 com.troy.Run.main() - ******************************************
2022-07-27 09:16:36 INFO  LogReader:35 Thread-1 - Reading log file : logfile.txt
2022-07-27 09:16:36 INFO  DbConnection:21 Thread-2 - Creating new Database connection.
2022-07-27 09:16:36 INFO  LogReader:68 Thread-1 - Log read completed. Total events counts : 10
2022-07-27 09:16:37 INFO  ENGINE:-2 Thread-2 - checkpointClose start
2022-07-27 09:16:37 INFO  ENGINE:-2 Thread-2 - checkpointClose synched
2022-07-27 09:16:37 INFO  ENGINE:-2 Thread-2 - checkpointClose script done
2022-07-27 09:16:37 INFO  ENGINE:-2 Thread-2 - checkpointClose end
2022-07-27 09:16:37 INFO  DbHandler:81 Thread-2 - All events are inserted to Database.
Log processing completed in : 0 secs
```

Log level -debug
```
2022-07-27 09:39:27 INFO  Run:22 com.troy.Run.main() - ******************************************
2022-07-27 09:39:27 INFO  Run:23 com.troy.Run.main() - ****  Credit Suisse Demo Application  ****
2022-07-27 09:39:27 INFO  Run:24 com.troy.Run.main() - ****    Reading log event to HSQLDB   ****
2022-07-27 09:39:27 INFO  Run:25 com.troy.Run.main() - ****        	By Tanmoy Roy.         ****
2022-07-27 09:39:27 INFO  Run:26 com.troy.Run.main() - ******************************************
2022-07-27 09:39:27 INFO  LogReader:35 Thread-1 - Reading log file : logfile.txt
2022-07-27 09:39:27 INFO  DbConnection:21 Thread-2 - Creating new Database connection.
2022-07-27 09:39:27 DEBUG LogReader:61 Thread-1 - New event added : str1
2022-07-27 09:39:27 DEBUG LogReader:50 Thread-1 - ID already present : str1
2022-07-27 09:39:27 DEBUG LogReader:61 Thread-1 - New event added : str2
2022-07-27 09:39:27 DEBUG LogReader:50 Thread-1 - ID already present : str2
2022-07-27 09:39:27 DEBUG LogReader:61 Thread-1 - New event added : str3
2022-07-27 09:39:27 DEBUG LogReader:50 Thread-1 - ID already present : str3
...
2022-07-27 09:39:27 DEBUG LogReader:61 Thread-1 - New event added : str9
2022-07-27 09:39:27 DEBUG LogReader:50 Thread-1 - ID already present : str9
2022-07-27 09:39:27 DEBUG LogReader:61 Thread-1 - New event added : str10
2022-07-27 09:39:27 DEBUG LogReader:50 Thread-1 - ID already present : str10
2022-07-27 09:39:27 DEBUG LogReader:66 Thread-1 - id : str1 state : STARTED type : App_logs host : x.y.z.o timestamp : 6
2022-07-27 09:39:27 DEBUG LogReader:66 Thread-1 - id : str2 state : STARTED type : App_logs host : x.y.z.o timestamp : 3
2022-07-27 09:39:27 DEBUG LogReader:66 Thread-1 - id : str3 state : STARTED type : App_logs host : x.y.z.o timestamp : 3
2022-07-27 09:39:27 DEBUG LogReader:66 Thread-1 - id : str4 state : STARTED type : App_logs host : x.y.z.o timestamp : 3
2022-07-27 09:39:27 DEBUG LogReader:66 Thread-1 - id : str5 state : FINISHED type : null host : null timestamp : 5
2022-07-27 09:39:27 DEBUG LogReader:66 Thread-1 - id : str6 state : STARTED type : App_logs host : x.y.z.o timestamp : 4
2022-07-27 09:39:27 DEBUG LogReader:66 Thread-1 - id : str7 state : STARTED type : App_logs host : x.y.z.o timestamp : 3
2022-07-27 09:39:27 DEBUG LogReader:66 Thread-1 - id : str8 state : STARTED type : App_logs host : x.y.z.o timestamp : 3
2022-07-27 09:39:27 DEBUG LogReader:66 Thread-1 - id : str9 state : STARTED type : App_logs host : x.y.z.o timestamp : 3
2022-07-27 09:39:27 DEBUG LogReader:66 Thread-1 - id : str10 state : FINISHED type : null host : null timestamp : 5
2022-07-27 09:39:27 INFO  LogReader:68 Thread-1 - Log read completed. Total events counts : 10
2022-07-27 09:39:28 INFO  ENGINE:-2 Thread-2 - Checkpoint start
2022-07-27 09:39:28 INFO  ENGINE:-2 Thread-2 - checkpointClose start
2022-07-27 09:39:28 INFO  ENGINE:-2 Thread-2 - checkpointClose synched
2022-07-27 09:39:28 INFO  ENGINE:-2 Thread-2 - checkpointClose script done
2022-07-27 09:39:28 INFO  ENGINE:-2 Thread-2 - checkpointClose end
2022-07-27 09:39:28 INFO  ENGINE:-2 Thread-2 - Checkpoint end - txts: 1
2022-07-27 09:39:28 DEBUG DbHandler:51 Thread-2 - DB EVENT Table created.
2022-07-27 09:39:28 DEBUG DbHandler:56 Thread-2 - Starting DB insertion...
2022-07-27 09:39:28 DEBUG DbHandler:57 Thread-2 - Total batch size : 10
2022-07-27 09:39:28 DEBUG DbHandler:73 Thread-2 - Inserted event id 'str1' to database
2022-07-27 09:39:28 DEBUG DbHandler:73 Thread-2 - Inserted event id 'str2' to database
2022-07-27 09:39:28 DEBUG DbHandler:73 Thread-2 - Inserted event id 'str3' to database
...
2022-07-27 09:39:28 DEBUG DbHandler:73 Thread-2 - Inserted event id 'str10' to database
2022-07-27 09:39:28 INFO  DbHandler:81 Thread-2 - All events are inserted to Database.
2022-07-27 09:39:28 INFO  DbConnection:21 com.troy.Run.main() - Creating new Database connection.

========================= Event Table =========================
str1 | 6 | App_logs | x.y.z.o | true
str2 | 3 | App_logs | x.y.z.o | false
str3 | 3 | App_logs | x.y.z.o | false
str4 | 3 | App_logs | x.y.z.o | false
str5 | 5 | null | null | true
str6 | 4 | App_logs | x.y.z.o | false
str7 | 3 | App_logs | x.y.z.o | false
str8 | 3 | App_logs | x.y.z.o | false
str9 | 3 | App_logs | x.y.z.o | false
str10 | 5 | null | null | true
Log processing completed in : 0 secs
```
