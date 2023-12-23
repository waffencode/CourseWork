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
        modelContext.getLogger().info("SignIn attempt");

        if (!modelContext.getRequestHandler().isConnectionAvailable())
        {
            modelContext.getLogger().error("SignIn error: no connection");
            NotificationDialog.showErrorDialog("Ошибка: отсутствует подключение к серверу!");
            return;
        }

        if (loginField.getText().isBlank() || passwordField.getText().isBlank())
        {
            modelContext.getLogger().error("SignIn attempt with invalid data");
            NotificationDialog.showInformationDialog("Введите имя пользователя и пароль!");
            return;
        }

        String login = loginField.getText();
        String password = passwordField.getText();
        String passwordHash = HashProvider.getStringHash(password);

        UUID authorizedUserId = modelContext.getRequestHandler().tryAuthorizeLoginData(login, passwordHash);

        if (authorizedUserId != null)
        {
            modelContext.getLogger().info("SignIn success");
            modelContext.setCurrentUser(modelContext.getRequestHandler().getUser(authorizedUserId, authorizedUserId));
            goToSceneWithResource("Main/MainMenuView.fxml");
        }
        else
        {
            modelContext.getLogger().error("SignIn attempt failed: invalid login or password");
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
