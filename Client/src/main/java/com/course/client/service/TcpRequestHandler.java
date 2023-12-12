package com.course.client.service;

import com.course.client.domain.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TcpRequestHandler
{
    public void createUser(User user)
    {

    }

    public User getUser(UUID id)
    {
        return new User();
    }

    public User getUserByLogin(String login)
    {
        return new User();
    }

    public List<User> getAllUsers()
    {
        return new ArrayList<User>();
    }

    public UUID tryAuthorizeLoginData(String login, String passwordHash)
    {
        try
        {
            String host = "localhost";
            int port = 8080;

            String path = "/user/login";
            String query = "login=" + login + "&password_hash=" + passwordHash;

            String request = "GET " + path + "?" + query + " HTTP/1.1\r\n" +
                    "Host: " + host + "\r\n" +
                    "Connection: close\r\n\r\n";

            System.out.println("Request: " + request);

            try (Socket socket = new Socket(host, port);
                 OutputStream outputStream = socket.getOutputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)))
            {
                outputStream.write(request.getBytes(StandardCharsets.UTF_8));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null)
                {
                    response.append(line).append("\n");
                }

                String responseBody = response.toString();
                System.out.println("Response: " + responseBody);
                UUID authId = getUuidFromResponse(responseBody);

                if (authId != null)
                {
                    return authId;
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private UUID getUuidFromResponse(String string)
    {
        String regex = "\\b[0-9a-f]{8}-([0-9a-f]{4}-){3}[0-9a-f]{12}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);

        if (matcher.find())
        {
            String match = matcher.group();

            if (match != null)
            {
                return UUID.fromString(matcher.group());
            }
        }
        return null;
    }

    public void updateUser(User user)
    {

    }

    public void deleteUser(UUID id)
    {

    }

    public void createObject(InventoryObject object)
    {

    }

    public InventoryObject getObject(String inventoryNumber)
    {
        return new InventoryObject();
    }

    public List<InventoryObject> getAllObjectsFromList(UUID listId)
    {
        return new ArrayList<InventoryObject>();
    }

    public void updateObject(InventoryObject object)
    {

    }

    public void deleteObject(String inventoryNumber)
    {

    }

    public void createList(InventoryObjectsList list)
    {

    }

    public InventoryObjectsList getList(UUID id)
    {
        return new InventoryObjectsList();
    }

    public List<InventoryObjectsList> getAllLists()
    {
        return new ArrayList<InventoryObjectsList>();
    }

    public void updateList(InventoryObjectsList list)
    {

    }

    public void archiveList(UUID listId, UUID userId)
    {

    }

    public void restoreList(UUID listId)
    {

    }

    public void deleteList(UUID id)
    {

    }
}
