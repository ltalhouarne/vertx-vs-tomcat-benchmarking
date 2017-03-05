package com.ltalhouarne.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Transaction {
    
    @Valid
    @NotNull
    @NotEmpty
    @JsonProperty("transactions")
    private List<Message> transactions;
    
    public List<Message> getTransactions() {
        return transactions;
    }
    
    public static class Message {
        
        @NotNull
        @JsonProperty("id")
        private String trackingId;
    
        @NotNull
        @JsonProperty("message")
        private String reasonType;
    
        public String getTrackingId() {
            return trackingId;
        }
    
        public String getReasonType() {
            return reasonType;
        }
    
        @Override
        public String toString() {
            return "Message{" +
                    "trackingId='" + trackingId + '\'' +
                    ", reasonType='" + reasonType + '\'' +
                    '}';
        }
    }
    
    @Override
    public String toString() {
        return "Transaction{" +
                "transactions=" + transactions +
                '}';
    }
}
