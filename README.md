# TASK Logstash: Filters
<details>
  <summary>TASK Description</summary>
Create small Java application that will log messages to the file. Log messages should have the following pattern:

${MESSAGE_ID} [${UUID} key1="value1" key2="value2" key3="value3" keyN="ValueN"] ${PROCESS_ID} ${COMPONENT_NAME}: ${MESSAGE}


Example:

00-00-00000 [c133ee9a7bfa11e6ae2256b6b6499611 app_name="application-name" app_version="1.0.0-SNAPSHOT" hostname="localhost"] 69427d6c966046c58804d7f4128f7505 DataBase: message here


Read all the logs from the file and send them to logstash.


Using Logstash filters parse log messages and extract all the fields specified in the message pattern (result document in the ElasticSearch should contains all of them in the root of log document).

Add ${AUTHOR} field to every log messages using logstash filters. 

If ${MESSAGE} contains keyword "error" - add tag [failed] to this document.

Use Kibana to make sure logs are properly parsed and contains all required fields.

Task result: application, filebeat and logstash configuration file.

Links:

Logstash filters: https://www.elastic.co/guide/en/logstash/current/filter-plugins.html

Logstash KV filter: https://www.elastic.co/guide/en/logstash/current/plugins-filters-kv.html

Logstash mutate filter: https://www.elastic.co/guide/en/logstash/current/plugins-filters-mutate.html

Logstash Grok filter: https://www.elastic.co/guide/en/logstash/current/plugins-filters-grok.html

</details>

## Configuration

Copy ***filebeat.yml*** to filebeat home folder.

Copy ***logstash.conf*** to logstash home folder.


## RUN 

I propose to follow the next order of actions
(To avoid errors)

<details>
<summary>Start ElasticSearch</summary>
  
```
 bin\elasticsearch
```
</details>
 
<details>
<summary>Start Kibana (optional)</summary> 
  
  ```
   bin\kibana
  ```
</details>

<details>
<summary>Start Logstash</summary> 
   
  ``` 
   bin\logstash -f logstash.conf  --config.reload.automatic
  ``` 
</details>
 
 
<details>
<summary>Start Filebeat</summary> 

  ```  
   filebeat.exe -e -c filebeat.yml -d "publish"
  ``` 
</details>
 
### Run logstash-filter-app

For  configuration test use ***logstash-filter-app*** application.

This application create records for logfile.

(my default path: C:\TEMP\VIKHLIAYEU\logs\logstash-filter-app\*.log)

<details>
<summary>Build application with maven</summary> 
Go to folder ***logstash-filter-app*** and build application with maven

  ```
  cd logstash-filter-app
  
  mvn clean package install
  ```
</details>

You can run this application from IDE or use run jar (executable file)/

<details>
<summary>Execute runnable jar file</summary> 
 
  ```
  java -jar logstash-filter-app-1.0-jar-with-dependencies.jar
  ```
</details>

After all step show result in kibana 
http://localhost:5601