package com.course.client.service.network;

import com.course.client.domain.*;
import com.course.client.service.config.Config;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TcpRequestHandler
{
    private final String host;
    private final int port;

    public TcpRequestHandler(Config config)
    {
        host = config.getHostname();
        port = config.getPort();
    }

    public boolean isConnectionAvailable()
    {
        try (Socket ignored = new Socket(host, port))
        {
            return true;
        }
        catch (IOException ex)
        {
            return false;
        }
    }

    public void createUser(User user)
    {
        String path = "/inventory/user";
        String query = "by=" + user.getId().toString();
        String data = new JsonStream().writeAsString(user);
        sendPostWithQuery(path, query, data);
    }

    public User getUser(UUID id, UUID by)
    {
        String path = "/inventory/user";
        String query = "id=" + id.toString() + "&by=" + by.toString();
        String response = sendGet(path, query);
        return new JsonStream().readUser(getJsonFromResponse(response));
    }

    public User getUserByLogin(String login)
    {
        String path = "/inventory/user";
        String query = "login=" + login;
        String response = sendGet(path, query);
        return new JsonStream().readUser(getJsonFromResponse(response));
    }

    public List<User> getAllUsers(UUID by)
    {
        String path = "/inventory/user";
        String query = "by=" + by.toString();
        String response = sendGet(path, query);
        return new JsonStream().readUserArray(getJsonFromResponse(response));
    }

    public UUID tryAuthorizeLoginData(String login, String passwordHash)
    {
        String path = "/inventory/user/login";
        String query = "login=" + login + "&password_hash=" + passwordHash;
        String response = sendGet(path, query);

        return getUuidFromResponse(response);
    }

    public void updateUser(User user, UUID by)
    {
        String path = "/inventory/user";
        String query = "id=" + user.getId().toString() + "&by=" + by.toString();
        String data = new JsonStream().writeAsString(user);
        sendPut(path, query, data);
    }

    public void deleteUser(UUID id, UUID by)
    {
        String path = "/inventory/user";
        String query = "id=" + id.toString() + "&by=" + by.toString();
        sendDelete(path, query);
    }

    public void createObject(InventoryObject object, UUID by)
    {
        String path = "/inventory/object";
        String query = "by=" + by.toString();
        String data = new JsonStream().writeAsString(object);
        sendPostWithQuery(path, query, data);
    }

    public InventoryObject getObject(String inventoryNumber, UUID by)
    {
        String path = "/inventory/object";
        String query = "id=" + inventoryNumber + "&by=" + by.toString();
        String response = sendGet(path, query);
        return new JsonStream().readObject(getJsonFromResponse(response));
    }

    public List<InventoryObject> getAllObjectsFromList(UUID listId, UUID by)
    {
        String path = "/inventory/object/view_list";
        String query = "list_id=" + listId.toString() + "&by=" + by.toString();
        String response = sendGet(path, query);
        String json = getJsonFromResponse(response);

        return new JsonStream().readObjectArray(json);
    }

    public List<InventoryObject> getAllObjects(UUID by)
    {
        String path = "/inventory/object";
        String query = "by=" + by.toString();
        String response = sendGet(path, query);
        String json = getJsonFromResponse(response);

        return new JsonStream().readObjectArray(json);
    }

    public void updateObject(InventoryObject object, UUID by)
    {
        String path = "/inventory/object";
        String query = "id=" + object.getInventoryNumber() + "&by=" + by.toString();
        String data = new JsonStream().writeAsString(object);
        sendPut(path, query, data);
    }

    public void deleteObject(String inventoryNumber, UUID by)
    {
        String path = "/inventory/object";
        String query = "id=" + inventoryNumber + "&by=" + by.toString();
        sendDelete(path, query);
    }

    public void createList(InventoryObjectsList list, UUID by)
    {
        String path = "/inventory/list";
        String query = "by=" + by.toString();
        String data = new JsonStream().writeAsString(list);
        sendPostWithQuery(path, query, data);
    }

    public InventoryObjectsList getList(UUID id, UUID by)
    {
        String path = "/inventory/list";
        String query = "id=" + id.toString() + "&by=" + by.toString();
        String response = sendGet(path, query);
        String json = getJsonFromResponse(response);

        return new JsonStream().readList(json);
    }

    public List<InventoryObjectsList> getAllLists(UUID by)
    {
        String path = "/inventory/list";
        String query = "by=" + by.toString();
        String response = sendGet(path, query);
        String json = getJsonFromResponse(response);

        return new JsonStream().readListArray(json);
    }

    public void updateList(InventoryObjectsList list, UUID by)
    {
        String path = "/inventory/list";
        String query = "id=" + list.getId().toString() + "&by=" + by.toString();
        String data = new JsonStream().writeAsString(list);
        sendPut(path, query, data);
    }

    public void archiveList(UUID listId, UUID userId)
    {
        String path = "/inventory/list/archive";
        String query = "id=" + listId.toString() + "&action=0&by=" + userId;
        sendGet(path, query);
    }

    public void deleteList(UUID id, UUID by)
    {
        String path = "/inventory/list";
        String query = "id=" + id.toString() + "&by=" + by.toString();
        sendDelete(path, query);
    }

    public List<InventoryObject> searchObjectsById(String id, UUID by)
    {
        String path = "/inventory/object/search";
        String query = "id=" + id + "&by=" + by.toString();
        String response = sendGet(path, query);
        String json = getJsonFromResponse(response);

        return new JsonStream().readObjectArray(json);
    }

    public List<InventoryObject> searchObjectsByName(String name, UUID by)
    {
        String path = "/inventory/object/search";
        String query = "name=" + name + "&by=" + by.toString();
        String response = sendGet(path, query);
        String json = getJsonFromResponse(response);

        return new JsonStream().readObjectArray(json);
    }

    private String sendGet(String path, String query)
    {
        String request = "GET " + path + "?" + query + " HTTP/1.1\r\n" + "Host: " + host + "\r\n" + "Connection: close\r\n\r\n";

        try {
            return getRequestResult(request);
        }
        catch (ConnectException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    private String sendPostWithQuery(String path, String query, String body)
    {
        String request = "POST " + path + "?" + query + " HTTP/1.1\r\n" + "Host: " + host + "\r\n" + "Content-Type: application/json\r\n" + "Content-Length: " + body.length() + "\r\n" + "Connection: close\r\n\r\n" + body;

        try {
            return getRequestResult(request);
        }
        catch (ConnectException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }


    private String sendPut(String path, String query, String body)
    {
        String request = "PUT " + path + "?" + query + " HTTP/1.1\r\n" + "Host: " + host + "\r\n" + "Content-Type: application/json\r\n" + "Content-Length: " + body.length() + "\r\n" + "Connection: close\r\n\r\n" + body;

        try {
            return getRequestResult(request);
        }
        catch (ConnectException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    private String sendDelete(String path, String query)
    {
        String request = "DELETE " + path + "?" + query + " HTTP/1.1\r\n" + "Host: " + host + "\r\n" + "Connection: close\r\n\r\n";

        try {
            return getRequestResult(request);
        }
        catch (ConnectException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    private String getRequestResult(String request) throws ConnectException
    {
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

            return response.toString();
        }
        catch (ConnectException ex)
        {
            throw ex;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private static UUID getUuidFromResponse(String string)
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

    private static String getJsonFromResponse(String response)
    {
        String[] parts = response.split("\n\n");
        return parts[1];
    }
}
