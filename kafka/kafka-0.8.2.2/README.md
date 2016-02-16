# kafka-0.8.2.2

* OpenJDK 7
* Kafka 0.8.2.2, port 9092
* Zookeeper (from kafka-0.8.2.2 distribution), port 2181

Home directory for Kafka + Zookeeper: /usr/local/kafka/ 

## Logs:

See `/usr/local/kafka/logs/`.

## Some examples:

Create `test` topic.
```
./kafka-topics.sh --zookeeper localhost:2181 --create --replication-factor 1 --partitions 1 --topic test
```

List topics.
```
./kafka-topics.sh --zookeeper localhost:2181 --list
```

Send some messages.
```
./kafka-console-producer.sh --broker-list localhost:9092 --topic test
```

Start a consumer and receive messages.
```
./kafka-console-consumer.sh --zookeeper localhost:2181 --topic test --from-beginning
```