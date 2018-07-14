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
11. 
#Spark submission command in LOCAL mode ->
spark_submit --class com.abhi.sparkscala.ExampleJob --master local sparkscalaapplication_2.11-0.1.0-SNAPSHOT.jar hdfs://localhost:9000/input/transactions.txt hdfs://localhost:9000/input/users.txt hdfs://localhost:9000/output

#Spark submission command in YARN mode (Please check the resources in the command)->
spark_submit --class com.abhi.sparkscala.ExampleJob --master yarn --deploy-mode cluster --driver-memory 1g --executor-memory 1g --executor-cores 1 sparkscalaapplication_2.11-0.1.0-SNAPSHOT.jar hdfs://localhost:9000/input/transactions.txt hdfs://localhost:9000/input/users.txt hdfs://localhost:9000/output

## REMOVE THE OUTPUT DIRECTORY for the next job submission  - otherwise will get FileAlreadyExistsException
hadoop fs -rm -r hdfs://localhost:9000/output

##YARN Resource Manager is avaliable at
http://localhost:8088/cluster/apps


