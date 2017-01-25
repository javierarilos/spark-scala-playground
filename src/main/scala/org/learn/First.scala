package org.learn

/**
  * First self contained Spark application
  * how to submit:
  *     spark-submit  --class "org.learn.First" \
  *                   --master "local[2]" \
  *                   target/scala-2.11/first-self-contained-spark-app_2.11-1.0.jar
  */

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import sys.env

object First {
  def main(args: Array[String]): Unit = {
    val fPath = env.getOrElse("FILE", "/home/javi/mywork/ml-100k/u.data")

    val conf = new SparkConf().setAppName("First-self-contained")
    val sc = new SparkContext(conf)

    val data = sc.textFile(fPath)
    val nLines = data.count()
    println("=========")
    println(s"======== File $fPath has $nLines lines")
    println("=========")
    sc.stop()
  }

}
