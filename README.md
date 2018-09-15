# SparkScalaApplication #

#Project creation command->

g8 scalatra/scalatra-sbt

## Build & Run ##
1. sbt package
2. startnamenode
3. startyarn
4. formatnamenode
5. startnamenode
6. hadoop fs -mkdir /input
7. /home/abhi/eclipse-welcome/sparkscalaapplication/src/main/resources
8. hadoop fs -put transactions.txt /input/
9. hadoop fs -put users.txt /input/
10. cd /home/abhi/eclipse-welcome/sparkscalaapplication/target/scala-2.11
11.mkdir -p /tmp/spark-events
12.cd /home/abhi/Downloads/spark-2.3.0-bin-hadoop2.7
13. ./sbin/start-history-server.sh
14. cd /home/abhi/eclipse-welcome/sparkscalaapplication/target/scala-2.12
15. spark_submit --class com.abhi.sparkscala.ExampleJob --driver-java-options "-Dlog4j.configuration=file:/home/abhi/eclipse-welcome/sparkscalaapplication/driver_log4j.properties" --master yarn --deploy-mode cluster --driver-memory 1g --executor-memory 1g --executor-cores 1 sparkscalaapplication_2.11-0.1.0-SNAPSHOT.jar hdfs://localhost:9000/input/transactions.txt hdfs://localhost:9000/input/users.txt hdfs://localhost:9000/output 

#Spark submission command in LOCAL mode ->
spark_submit --class com.abhi.sparkscala.ExampleJob --master local sparkscalaapplication_2.11-0.1.0-SNAPSHOT.jar hdfs://localhost:9000/input/transactions.txt hdfs://localhost:9000/input/users.txt hdfs://localhost:9000/output

#Spark submission command in YARN mode (Please check the resources in the command)->
spark_submit --class com.abhi.sparkscala.ExampleJob --driver-java-options "-Dlog4j.configuration=file:/home/abhi/eclipse-welcome/sparkscalaapplication/driver_log4j.properties" --master yarn --deploy-mode cluster --driver-memory 1g --executor-memory 1g --executor-cores 1 sparkscalaapplication_2.11-0.1.0-SNAPSHOT.jar hdfs://localhost:9000/input/transactions.txt hdfs://localhost:9000/input/users.txt hdfs://localhost:9000/output

## REMOVE THE OUTPUT DIRECTORY for the next job submission  - otherwise will get FileAlreadyExistsException
hadoop fs -rm -r hdfs://localhost:9000/output

##YARN Resource Manager is avaliable at
http://localhost:8088/cluster/apps

##History server enabled
http://localhost:18080
Steps:
1. changes in spark-defaults.conf (/home/abhi/Downloads/spark-2.3.0-bin-hadoop2.7/conf): 
spark.eventLog.enabled           true
spark.eventLog.dir               file:/tmp/spark-events
spark.history.fs.logDirectory    file:/tmp/spark-events

2. added in log4j.properties: (/home/abhi/Downloads/spark-2.3.0-bin-hadoop2.7/conf):
log4j.logger.org.apache.spark.deploy.history=INFO
3. Start the history server from path /home/abhi/Downloads/spark-2.3.0-bin-hadoop2.7:
./sbin/start-history-server.sh

Can check the history server status logs from logs under /home/abhi/Downloads/spark-2.3.0-bin-hadoop2.7/logs directory 
)

