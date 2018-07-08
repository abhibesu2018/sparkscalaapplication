package com.abhi.sparkscala

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.scalatest.junit.AssertionsForJUnit

class SparkJoinsScalaTest extends AssertionsForJUnit {

  var sc: SparkContext = _

  @Before
  def initialize() {
    val conf = new SparkConf().setAppName("SparkJoins").setMaster("local")
    sc = new SparkContext(conf)
  }

  @After
  def tearDown() {
    sc.stop()
  }

  @Test
  def testExamleJobCode() {
    val job = new ExampleJob(sc)
    val result = job.run("./src/test/scala/com/abhi/sparkscala/transactions.txt", "./src/test/scala/com/abhi/sparkscala/users.txt")
    assert(result.collect()(0)._1 === "1")
    assert(result.collect()(0)._2 === "3")
    assert(result.collect()(1)._1 === "2")
    assert(result.collect()(1)._2 === "1")
  }
}