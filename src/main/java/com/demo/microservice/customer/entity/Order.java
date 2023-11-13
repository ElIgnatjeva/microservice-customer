package com.demo.microservice.customer.entity;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@ToString
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotBlank
    private String id;

    @NotBlank
    private String customerId;
}
