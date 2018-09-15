#!/bin/bash
sbt package
startnamenode
startyarn
formatnamenode
startnamenode
hadoop fs -mkdir /input
hadoop fs -put /home/abhi/eclipse-welcome/sparkscalaapplication/src/main/resources/transactions.txt /input
hadoop fs -put /home/abhi/eclipse-welcome/sparkscalaapplication/src/main/resources/users.txt /input
cd /home/abhi/eclipse-welcome/sparkscalaapplication/target/scala-2.11
mkdir -p /tmp/spark-events
cd /home/abhi/Downloads/spark-2.3.0-bin-hadoop2.7
./sbin/start-history-server.sh
cd /home/abhi/eclipse-welcome/sparkscalaapplication/target/scala-2.12
spark_submit --class com.abhi.sparkscala.ExampleJob --driver-java-options "-Dlog4j.configuration=file:/home/abhi/eclipse-welcome/sparkscalaapplication/driver_log4j.properties" --master yarn --deploy-mode cluster --driver-memory 1g --executor-memory 1g --executor-cores 1 sparkscalaapplication_2.11-0.1.0-SNAPSHOT.jar hdfs://localhost:9000/input/transactions.txt hdfs://localhost:9000/input/users.txt hdfs://localhost:9000/output
