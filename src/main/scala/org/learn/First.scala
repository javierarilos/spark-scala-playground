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

    val lines = sc.textFile(fPath)

    val nLines = lines.count()
    val nChars = lines.map(_.length).reduce(_ + _)
    val wordCount = lines.flatMap(_.split(" "))
                          .map(_.replace(".", ""))
                          .map(_.replace(",", ""))
                          .map(_.replace("\n", ""))
                          .map(_.toLowerCase())
                          .map(w => (w, 1))
                          .reduceByKey(_+_)
                          .sortBy[Int](_._2, ascending=false)
                          .collect()
    println("=========")
    println(s"======== File $fPath has:")
    println(s"======== $nLines lines")
    println(s"======== $nChars characters")
    println(s"======== wordCount: ")
    wordCount.foreach[Unit](t => println(s"   ${t._1} => ${t._2}"))
    println("=========")




    sc.stop()
  }

}
