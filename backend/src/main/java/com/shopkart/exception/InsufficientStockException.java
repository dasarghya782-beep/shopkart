package com.shopkart.exception;

public class InsufficientStockException extends RuntimeException{

    public InsufficientStockException(Long productid,int requestedQuantity){
        super("Insufficient stock for product: " + productid +
                " Requested quantity: " + requestedQuantity);
    }
}
