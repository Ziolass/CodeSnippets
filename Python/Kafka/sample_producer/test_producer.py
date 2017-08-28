import time
import json
from pykafka import KafkaClient

KAFKA_HOSTS = 'kafka:9092'
KAFKA_TOPIC = 'Topic1'

sample_data = {
    "some_id": 1,
    "eventId": "dse6-asd3f-3943836b3edc",
    "timestamp": "Jun 6, 2016 6:51:22 PM +0200",
    "ipAddress": "10.134.116.184",
    }

print("Preparing mockup producer...")
client = KafkaClient(hosts=KAFKA_HOSTS)
print ('Available topics:', client.topics)
topic = client.topics[KAFKA_TOPIC.encode()]
print("Mockup producer creation succeeded!")

with topic.get_sync_producer() as producer:
    print("Beginning to send messages...")
    while True:
        producer.produce(json.dumps(sample_data).encode())
        sample_data['sample_id'] += 1
        print("Ping " + str(sample_data['sample_id']))
        #time.sleep(5)
