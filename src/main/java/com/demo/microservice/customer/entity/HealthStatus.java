package com.demo.microservice.customer.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum HealthStatus {
    UP("UP"),
    DOWN("DOWN");
    private final String status;

    HealthStatus(String status) {
        this.status = status;
    }
}
