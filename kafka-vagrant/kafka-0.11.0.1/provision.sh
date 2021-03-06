#!/bin/bash
export DEBIAN_FRONTEND=noninteractive

sudo add-apt-repository ppa:openjdk-r/ppa
sudo apt-get update > /dev/null
sudo apt-get install -q -y openjdk-8-jdk

SCALA_VERSION="2.11"
KAFKA_VERSION="0.11.0.1"
KAFKA_VERSIONED_NAME="kafka_${SCALA_VERSION}-${KAFKA_VERSION}"
KAFKA_HOME=/usr/local/kafka

if ! [ -f "$KAFKA_VERSIONED_NAME.tgz" ]; then
    echo "Downloading $KAFKA_VERSIONED_NAME.tgz"
    wget --quiet http://ftp.fau.de/apache/kafka/$KAFKA_VERSION/$KAFKA_VERSIONED_NAME.tgz
fi

echo "Extracting $KAFKA_VERSIONED_NAME.tgz"
tar -zxf $KAFKA_VERSIONED_NAME.tgz -C /usr/local/

if [ -d "/usr/local/kafka" ]; then
    echo "Removing old symlink"
    rm /usr/local/kafka
fi

echo "Creating symlink"
ln -s /usr/local/$KAFKA_VERSIONED_NAME/ /usr/local/kafka

if ! [ -d "$KAFKA_HOME/logs" ]; then
    mkdir $KAFKA_HOME/logs
fi

echo "Starting Zookeeper"
nohup $KAFKA_HOME/bin/zookeeper-server-start.sh $KAFKA_HOME/config/zookeeper.properties > $KAFKA_HOME/logs/zookeeper.stdout.log 2>&1 &

echo "Starting Kafka"
nohup $KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/config/server.properties > $KAFKA_HOME/logs/kafka.stdout.log 2>&1 &

echo "Done."