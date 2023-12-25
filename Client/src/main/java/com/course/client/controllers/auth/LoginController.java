package com.course.client.controllers.auth;

import com.course.client.service.security.HashProvider;
import com.course.client.ui.NotificationDialog;
import com.course.client.ui.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.UUID;

public class LoginController extends SceneController
{
    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void onSignInButtonClicked()
    {
        modelContext.getLogger().info("Sign in attempt");

        if (loginField.getText().isBlank() || passwordField.getText().isBlank())
        {
            modelContext.getLogger().error("Sign in attempt failed: incorrect input");
            NotificationDialog.showInformationDialog("Введите имя пользователя и пароль!");
            return;
        }

        if (!modelContext.getRequestHandler().isConnectionAvailable())
        {
            modelContext.getLogger().error("Sign in error: no connection");
            NotificationDialog.showErrorDialog("Ошибка: отсутствует подключение к серверу!");
            return;
        }

        String login = loginField.getText();
        String password = passwordField.getText();
        String passwordHash = HashProvider.getStringHash(password);

        UUID authorizedUserId = modelContext.getRequestHandler().tryAuthorizeLoginData(login, passwordHash);

        if (authorizedUserId != null)
        {
            modelContext.setCurrentUser(modelContext.getRequestHandler().getUser(authorizedUserId, authorizedUserId));
            modelContext.getLogger().info("User with ID {} signed in", authorizedUserId);
            goToSceneWithResource("Main/MainMenuView.fxml");
        }
        else
        {
            modelContext.getLogger().error("Sign in attempt failed: invalid login or password");
            NotificationDialog.showWarningDialog("Неверное имя пользователя или пароль!");
        }
    }

    @FXML
    private void onSignUpButtonClicked()
    {
        goToSceneWithResource("Auth/RegisterView.fxml");
    }

    @FXML
    private void onExitButtonClicked()
    {
        modelContext.getApplication().exit();
    }
}
