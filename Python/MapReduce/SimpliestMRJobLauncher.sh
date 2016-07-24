yarn jar /usr/hdp/current/hadoop-mapreduce-client/hadoop-streaming.jar \ 
-file mapper.py -mapper 'python mapper.py' \
-file reducer.py -reducer 'python reducer.py' \ 
-input /user/stankiewicz/SalesJan2009_final.csv  \
-output /user/stankiewicz/productCount/