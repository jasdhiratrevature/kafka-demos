package com.revature.kafka.events;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class EventProducer {
    public static void main(String[] args) {
        // Setup Kafka Producer properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

        // Create KafkaProducer instance
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        try {
            // Produce event
            for (int i = 0; i < 5; i++) {
                String key = "event-key-" + i;
                String value = "Event-Data-" + i;
                ProducerRecord<String, String> record = new ProducerRecord<>("my-event-topic", key, value);
                producer.send(record);
                System.out.println("Produced event: " + value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close producer
            producer.close();
        }
    }
}
