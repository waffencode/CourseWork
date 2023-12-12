package com.course.client.controllers;

import com.course.client.ui.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController extends SceneController
{
    @FXML
    private Button signInButton, signUpButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void onSignInButtonClicked()
    {
        System.out.println("signInButton clicked with data:\nLogin: " + loginField.getText() + "\nPassword: " + passwordField.getText());
    }

    @FXML
    private void onSignUpButtonClicked()
    {
        System.out.println("signUpButton clicked with data:\nLogin: " + loginField.getText() + "\nPassword: " + passwordField.getText());
    }
}
