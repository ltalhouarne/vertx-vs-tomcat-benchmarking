package com.ltalhouarne.service;

import com.ltalhouarne.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.SuccessCallback;

@Service
public class KafkaService {
    
    @Autowired
    private KafkaTemplate kafkaTemplate;
    
    public void sendToKafka(Transaction transactions, SuccessCallback successCallback, FailureCallback failureCallback) {
        transactions.getTransactions().stream()
                .map(tran -> kafkaTemplate.sendDefault(null, tran.toString()))
                .forEach(future -> future.addCallback(successCallback, failureCallback));
    
        kafkaTemplate.flush();
    }
}
