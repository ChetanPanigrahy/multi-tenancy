package com.chetan.service.client;

import com.chetan.service.model.TenantValue2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.chetan.service.model.TenantValue;

@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendTenantMessage(TenantValue msg) {
        SendMessage(msg);
    }

    public void sendTenantMessage2(TenantValue2 msg) {
        SendMessage(msg);
    }

    private void SendMessage(Object msg) {
        ProducerRecord<String, Object> record = new ProducerRecord<>("multiType", msg);
        record.headers().add("foo", "bar".getBytes());
        kafkaTemplate.send(record);
    }
}
