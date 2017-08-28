from pyspark import SparkContext, SparkConf
from pyspark.sql import HiveContext

param = ""
spark_conf = SparkConf().setAppName("job_name_%s" % param)
spark_context = SparkContext(conf=spark_conf)
hive_context = HiveContext(spark_context)

# DO NOT PUT SEMICOLON AT THE END OF THE HQL QUERY HERE!
query = """
        some unspecified query {param}
""".format(param="sth")

# do not do so, if your dataset is moderately huge
results = hive_context.sql(query).collect()

for line in results:
    print(line)
