package com.course.client.controllers;

import com.course.client.InventoryApplication;
import com.course.client.ui.SceneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.Objects;

public class LoginController extends SceneController
{
    Parent root;

    public LoginController(InventoryApplication application)
    {
        super(application);

//        try
//        {
//            root = FXMLLoader.load(Objects.requireNonNull(application.getClass().getClassLoader().getResource("LoginView.fxml")));
//            scene = new Scene(root);
//        } catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
    }

    @Override
    public void update()
    {

    }
}
