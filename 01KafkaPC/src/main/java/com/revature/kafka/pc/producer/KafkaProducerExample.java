package com.revature.kafka.pc.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerExample {
    public static void main(String[] args) {
        // Configure Kafka Producer
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092"); // Kafka broker address
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

        // Create KafkaProducer instance
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        try {
            // Produce messages
            for (int i = 0; i < 10; i++) {
                String message = "Message-" + i;
                ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", "key-" + i, message);
                producer.send(record);
                System.out.println("Sent message: " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the producer
            producer.close();
        }
    }
}