package com.chetan.management.kafkalistener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import com.chetan.management.service.TenantManagementService;
import com.chetan.service.model.TenantValue;
import com.chetan.service.model.TenantValue2;

@Slf4j
@Component
@KafkaListener(topics = "multiType", clientIdPrefix = "json",
        containerFactory = "kafkaListenerContainerFactory")
public class MultiTypeKafkaListener {
    @Autowired
    private TenantManagementService tenantManagementService;

    @KafkaHandler
    public void listenTenant(ConsumerRecord<String, TenantValue> cr,
                             @Payload TenantValue message) {
        this.tenantManagementService.createTenant(message.getTenantId(), message.getSchema(), message.getOrgId(), message.getModule());
    }

    @KafkaHandler
    public void listenTenant2(ConsumerRecord<String, TenantValue2> cr,
                              @Payload TenantValue2 message,
                              @Header("foo") String foo) {
        log.info(foo);
        log.info(message.toString());
    }
}
