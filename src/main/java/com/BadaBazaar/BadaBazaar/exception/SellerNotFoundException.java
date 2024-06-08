package com.BadaBazaar.BadaBazaar.exception;

public class SellerNotFoundException extends RuntimeException{

    public SellerNotFoundException(){
        super();
    }

    public SellerNotFoundException(String message){
        super(message);
    }

    public SellerNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public SellerNotFoundException(Throwable cause){
        super(cause);
    }







}
