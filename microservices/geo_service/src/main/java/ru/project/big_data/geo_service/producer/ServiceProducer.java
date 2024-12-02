package ru.project.big_data.geo_service.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.project.big_data.geo_service.data.DataGenerator;

import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;


//TODO
@Service
public class ServiceProducer {
    @Autowired
    public ServiceProducer(DataGenerator dataGenerator) {
        this.dataGenerator = dataGenerator;
    }
//    @Value("${kafka.brokers}")
//    private String kafkaBrokers;
//    @Value("${kafka.group.id.config}")
//    private String kafkaGroupIdConfig;
//    @Value("${kafka.topic}")
//    private String kafkaTopic;
//    @Value("${kafka.partition}")
//    private int kafkaPartition;

    private DataGenerator dataGenerator;
    private static KafkaProducer<String, String> producer;
    private static Thread threadProducer;

//    private KafkaProducer<String, String> getProducer() {
//        Properties producerProperties = new Properties();
//        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokers);
//        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        KafkaProducer<String, String> result = new KafkaProducer(producerProperties);
//        return result;
//    }

    public String sendData(boolean isSendData) {

//        if(producer == null) {
//            producer = getProducer();
//        }

        if(!isSendData && threadProducer != null) {
            threadProducer.interrupt();
            producer.close();
            producer = null;
            threadProducer = null;
            return "producer_geo is stoped!";
        }

        if(!isSendData && threadProducer == null) {
            return "producer_geo was stoped!";
        }

        dataGenerator.getData();

//        if (isSendData && threadProducer == null) {
//            threadProducer = new Thread(() -> {
//                while(!Thread.currentThread().isInterrupted()) {
//                    ProducerRecord<String, String> producerRecord =
//                            new ProducerRecord<>(
//                                    kafkaTopic, kafkaPartition, kafkaGroupIdConfig, dataGenerator.getData()
//                            );
//                    producer.send(producerRecord);
//                    try {
//                        Thread.sleep(ThreadLocalRandom.current().nextInt(50, 100));
//                    } catch (InterruptedException e) {
//                        break;
//                    }
//                }
//            });
//            threadProducer.start();
//            return "producer_geo is start!";
//        }

        return "producer was started!";
    }

}
