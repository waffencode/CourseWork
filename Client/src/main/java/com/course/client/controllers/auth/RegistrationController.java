package com.course.client.controllers.auth;

import com.course.client.domain.Role;
import com.course.client.domain.User;
import com.course.client.service.HashProvider;
import com.course.client.ui.NotificationDialog;
import com.course.client.ui.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegistrationController extends SceneController
{
    @FXML
    private TextField loginField, passwordField;

    @FXML
    private void onRegisterButtonClicked()
    {
        if (loginField.getText().isBlank() || passwordField.getText().isBlank())
        {
            NotificationDialog.showInformationDialog("Введите имя пользователя и пароль!");
            return;
        }

        if (modelContext.getRequestHandler().getUserByLogin(loginField.getText()) != null)
        {
            NotificationDialog.showWarningDialog("Пользователь с таким логином уже существует!");
            return;
        }

        if (!containsOnlyAllowedCharacters(loginField.getText()))
        {
            NotificationDialog.showWarningDialog("Логин может содержать только символы латинского алфавита, цифры и знаки препинания!");
            return;
        }

        User user = new User();
        user.setLogin(loginField.getText());
        String passwordHash = HashProvider.getStringHash(passwordField.getText());
        user.setPasswordHash(passwordHash);
        user.setRole(Role.USER);
        modelContext.getRequestHandler().createUser(user);

        NotificationDialog.showInformationDialog("Аккаунт успешно создан!");
        goToSceneWithResource("Auth/LoginView.fxml");
    }

    @FXML
    private void onBackButtonClicked()
    {
        goToSceneWithResource("Auth/LoginView.fxml");
    }

    private static boolean containsOnlyAllowedCharacters(String str)
    {
        String regex = "^[a-zA-Z0-9\\p{Punct} ]+$";
        return str.matches(regex);
    }
}
