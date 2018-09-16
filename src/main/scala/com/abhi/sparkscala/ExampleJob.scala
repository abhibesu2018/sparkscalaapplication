package com.abhi.sparkscala

import scala.collection.Map

import org.apache.log4j.Level
import org.apache.log4j.LogManager
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions


class ExampleJob(sc: SparkContext) {
  def run(t: String, u: String): RDD[(String, String)] = {
    val transactions = sc.textFile(t)
    val newTransactionsPair = transactions.map { t =>
      val p = t.split("\t")
      val logger = LogManager.getRootLogger
      logger.setLevel(Level.INFO)
      logger.info("Inside map:")
      println("Print transaction pairs.....")
      println("print :"+p(2).toInt)
      println("print :"+p(1).toInt)
      (p(2).toInt, p(1).toInt)
    }

    val users = sc.textFile(u)
    val newUsersPair = users.map { t =>
      val p = t.split("\t")
      println("New users pairs.....")
      println(p(0).toInt)
      println(p(3))
      (p(0).toInt, p(3))
    }
    val result = processData(newTransactionsPair, newUsersPair)
    return sc.parallelize(result.toSeq).map(t => (t._1.toString, t._2.toString))
  }

  def processData(t: RDD[(Int, Int)], u: RDD[(Int, String)]): Map[Int, Long] = {
    val logger = LogManager.getRootLogger
    logger.setLevel(Level.INFO)
    println("Left outer join values:")
    var jn1 = t.leftOuterJoin(u).values
    jn1.collect().foreach(println)
    var jn = t.leftOuterJoin(u).values.distinct
    logger.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^")
    logger.info(jn)
    jn.collect().foreach(println)
    logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
    return jn.countByKey
  }
}

object ExampleJob {
  
  def main(args: Array[String]) {
    val transactionsIn = args(0)
    val usersIn = args(1)
    val conf = new SparkConf().setAppName("SparkJoins").set("spark.hadoop.validateOutputSpecs", "false")
    val context = new SparkContext(conf)
    val job = new ExampleJob(context)
    val results = job.run(transactionsIn, usersIn)
    val output = args(2)
    results.saveAsTextFile(output)
    context.stop()
  }
}