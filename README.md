# spark-scala-playground
Playing around with Spark 2.1 and Scala 2.12

## Getting started

### Software requirements
First you will need to have SBT, Scala and Spark. There are many ways to do it, this is the one I used, should work in any Linux distribution.

```bash
echo "... everything is done inside one directory"
cd software_my
echo "... preparing SBT"
curl -vL -O https://dl.bintray.com/sbt/native-packages/sbt/0.13.13/sbt-0.13.13.tgz
tar -zxvf sbt-0.13.13.tgz
rm sbt-0.13.13.tgz
mv sbt-launcher-packaging-0.13.13/ sbt-0.13.13/

echo "... preparing Scala"
curl -O http://downloads.lightbend.com/scala/2.11.8/scala-2.11.8.tgz
tar -xzvf scala-2.11.8.tgz
rm scala-2.11.8.tgz

echo "... preparing Spark"
curl -O http://d3kbcqa49mib13.cloudfront.net/spark-2.1.0-bin-hadoop2.7.tgz
tar -zxvf spark-2.1.0-bin-hadoop2.7.tgz
rm spark-2.1.0-bin-hadoop2.7.tgz

echo "... preparing the environment PATH"
echo 'MYDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
PATH=$MYDIR/sbt-0.13.13/bin:$MYDIR/scala-2.11.8/bin/:$MYDIR/spark-2.1.0-bin-hadoop2.7/bin:$PATH' > spark.env

echo "... now we load the PATH env var to have all the binaries available"
source spark.env
```

### Build and execute the Spark application
```bash
sbt package

export FILE=$(pwd)/README.md
spark-submit --class "org.learn.First" \
        --master "local[4]" \
        target/scala-2.11/first-self-contained-spark-app_2.11-0.1.jar
```
