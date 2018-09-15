package com.abhi.sparkscala

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import scala.collection.Map
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.Serializable
import org.apache.log4j.LogManager
import org.apache.log4j.Level

class ExampleJob(sc: SparkContext) {
  def run(t: String, u: String): RDD[(String, String)] = {
    val transactions = sc.textFile(t)
    val newTransactionsPair = transactions.map { t =>
      val p = t.split("\t")
      /*logger.info("Print transaction pairs.....")
      logger.info("print :"+p(2).toInt)
      logger.info("print :"+p(1).toInt)*/
      (p(2).toInt, p(1).toInt)
    }

    val users = sc.textFile(u)
    val newUsersPair = users.map { t =>
      val p = t.split("\t")
//      logger.info("Print users pairs.....")
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
    var jn = t.leftOuterJoin(u).values.distinct
    logger.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^")
    logger.info(jn)
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