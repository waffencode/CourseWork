package com.course.client.service;

public class ModelContext
{
    private TcpRequestHandler requestHandler = new TcpRequestHandler();

    public TcpRequestHandler getRequestHandler()
    {
        return requestHandler;
    }

    public void setRequestHandler(TcpRequestHandler requestHandler)
    {
        this.requestHandler = requestHandler;
    }
}
