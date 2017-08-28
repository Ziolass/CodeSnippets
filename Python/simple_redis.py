import time 

from rediscluster import StrictRedisCluster

# ###################################################################################################################
REDIS_HOST = "X.X.X.X"
REDIS_PORT = 6379
redis_connection = StrictRedisCluster(REDIS_HOST, REDIS_PORT)
STR_TO_MATCH = ""
# cluster mode rules out the transactional pipeline
pipeline = redis_connection.pipeline(transaction=False)

# create an generator to efficiently iterate the keys
generator = redis_connection.scan_iter(match=STR_TO_MATCH+"*")
for event in generator:
    event_key = event[4:]
    if 1 == 1:
        pipeline.delete(event)

pipeline.execute()
