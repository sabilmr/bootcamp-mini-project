package com.bootcamp.northwind.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRequest {
    private String id;
    private String orderId;
    private OrderRequest order;
    private String productId;
    private Double unitPrice;
    private Double quantity;
    private String discount;
}
