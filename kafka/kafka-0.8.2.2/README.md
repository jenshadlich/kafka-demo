# kafka-0.8.2.2

Quickstart examples:

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