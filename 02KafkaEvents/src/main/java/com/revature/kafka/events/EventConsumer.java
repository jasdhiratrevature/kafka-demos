package com.revature.kafka.events;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class EventConsumer {
    public static void main(String[] args) {
        // Setup Kafka Consumer properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "my-event-consumer-group");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        props.put("auto.offset.reset", "earliest");

        // Create KafkaConsumer instance
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // Subscribe to the event topic
        consumer.subscribe(Collections.singletonList("my-event-topic"));

        try {
            while (true) {
                // Poll for new events (messages)
                var records = consumer.poll(1000);
                records.forEach(record -> {
                    System.out.println("Consumed event: " + record.value());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close consumer
            consumer.close();
        }
    }
}