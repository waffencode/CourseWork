package com.course.client.controllers.auth;

import com.course.client.domain.Role;
import com.course.client.domain.User;
import com.course.client.service.HashProvider;
import com.course.client.ui.SceneController;
import com.course.client.ui.SceneProvider;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegistrationController extends SceneController
{
    @FXML
    private TextField loginField, passwordField;

    @FXML
    private void onRegisterButtonClicked()
    {
        User user = new User();
        user.setLogin(loginField.getText());
        String passwordHash = HashProvider.getStringHash(passwordField.getText());
        user.setPasswordHash(passwordHash);
        user.setRole(Role.USER);
        modelContext.getRequestHandler().createUser(user);

        goToSceneWithResource("Auth/LoginView.fxml");
    }
}
