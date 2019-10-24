package com.example.demo.Exception;

public class MainException extends Exception
{

    private int status;

    private String errorMessage;

    public MainException(int status, String errorMessage)
    {
        super(errorMessage);
        this.status = status;
    }

    public MainException(String errorMessage)
    {
        super(errorMessage);
        this.status = 500;
    }

    public int getStatus()
    {
        return status;
    }
}
