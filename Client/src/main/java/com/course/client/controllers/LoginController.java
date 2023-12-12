package com.course.client.controllers;

import com.course.client.service.HashProvider;
import com.course.client.ui.NotificationDialog;
import com.course.client.ui.SceneController;
import com.course.client.ui.SceneProvider;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.UUID;

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
        String login = loginField.getText();
        String password = passwordField.getText();
        String passwordHash = HashProvider.getStringHash(password);

        UUID authorizedUserId = modelContext.getRequestHandler().tryAuthorizeLoginData(login, passwordHash);

        if (authorizedUserId != null)
        {
            uiContext.getStage().setScene(new SceneProvider().getPreparedScene("MainMenuView.fxml", modelContext, uiContext));
        }
        else
        {
            NotificationDialog.showWarningDialog("Неверное имя пользователя или пароль!");
        }
    }

    @FXML
    private void onSignUpButtonClicked()
    {
        System.out.println("signUpButton clicked with data:\nLogin: " + loginField.getText() + "\nPassword: " + passwordField.getText());
    }
}
