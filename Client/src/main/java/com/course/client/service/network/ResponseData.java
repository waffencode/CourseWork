package com.course.client.service.network;

public class ResponseData
{
    public ResponseData(String response)
    {
        parseResponse(response);
    }

    private void parseResponse(String response)
    {
        String[] parts = response.split("\n\n");
        String headers = parts[0];
        parseHeaders(headers);
        String data = parts[1];
    }

    private void parseHeaders(String headers)
    {

    }
}
