package com.course.client.controllers.main;

import com.course.client.domain.User;
import com.course.client.service.ModelContext;
import com.course.client.service.UiContext;
import com.course.client.ui.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainMenuController extends SceneController
{
    @FXML
    private Button usersMenuButton, inventoryMenuButton, settingsMenuButton, exitButton;

    @FXML
    private Label userLoginLabel, userIdLabel, userRoleLabel;

    @Override
    public void setContext(ModelContext modelContext, UiContext uiContext)
    {
        this.modelContext = modelContext;
        this.uiContext = uiContext;
        updateLabels();
    }

    private void updateLabels()
    {
        User currentUser = modelContext.getCurrentUser();
        userLoginLabel.setText("Авторизованный пользователь: " + currentUser.getLogin());
        userIdLabel.setText("ID: " + currentUser.getId().toString());
        userRoleLabel.setText("Уровень доступа: " + currentUser.getRole().name());
    }

    @FXML
    private void onExitButtonClicked()
    {
        modelContext.getApplication().exit();
    }

    @FXML
    private void onUsersButtonClicked()
    {

    }

    @FXML
    private void onInventoryButtonClicked()
    {

    }

    @FXML
    private void onSettingsButtonClicked()
    {

    }
}
