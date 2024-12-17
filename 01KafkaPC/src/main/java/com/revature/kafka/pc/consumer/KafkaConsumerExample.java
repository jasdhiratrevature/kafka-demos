package com.revature.kafka.pc.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerExample {
    public static void main(String[] args) {
        // Configure Kafka Consumer
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");  // Kafka broker address
        props.put("group.id", "my-group");  // Consumer group ID
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        props.put("auto.offset.reset", "earliest");  // Start reading from the beginning if no offset

        // Create KafkaConsumer instance
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // Subscribe to the topic
        consumer.subscribe(Collections.singletonList("my-topic"));

        try {
            while (true) {
                // Poll for new messages
                var records = consumer.poll(1000);  // 1000ms timeout
                records.forEach(record -> {
                    System.out.println("Consumed message: " + record.value());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the consumer
            consumer.close();
        }
    }
}
