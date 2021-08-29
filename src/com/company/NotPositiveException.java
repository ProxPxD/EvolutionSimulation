package com.company;

public class NotPositiveException extends IllegalArgumentException{

    public NotPositiveException(String argument){
        super(argument + " cannot be not positive");
    }
}
