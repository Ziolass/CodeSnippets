# Valid for spark < 2.0
from pyspark import SparkContext, SparkConf
from pyspark.sql import HiveContext

param = ""
spark_conf = SparkConf().setAppName("job_name_%s" % param)
spark_context = SparkContext(conf=spark_conf)
hive_context = HiveContext(spark_context)
