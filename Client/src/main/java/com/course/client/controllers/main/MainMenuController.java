package com.course.client.controllers.main;

import com.course.client.domain.Role;
import com.course.client.domain.User;
import com.course.client.service.context.ModelContext;
import com.course.client.service.context.UiContext;
import com.course.client.ui.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainMenuController extends SceneController
{
    @FXML
    private Button usersMenuButton, inventoryMenuButton, settingsMenuButton, exitButton;

    @FXML
    private Label userLoginLabel;

    @Override
    public void initController(ModelContext modelContext, UiContext uiContext)
    {
        this.modelContext = modelContext;
        this.uiContext = uiContext;
        updateLabels();

        Role userRole = modelContext.getCurrentUser().getRole();
        usersMenuButton.setVisible(userRole == Role.ADMINISTRATOR);
    }

    private void updateLabels()
    {
        User currentUser = modelContext.getCurrentUser();
        userLoginLabel.setText("Авторизованный пользователь: " + currentUser.getLogin());
    }

    @FXML
    private void onExitButtonClicked()
    {
        modelContext.getApplication().exit();
    }

    @FXML
    private void onUsersButtonClicked()
    {
        goToSceneWithResource("User/UserListView.fxml");
    }

    @FXML
    private void onInventoryButtonClicked()
    {
        goToSceneWithResource("Inventory/MainListsView.fxml");
    }

    @FXML
    private void onLogoutButtonClicked()
    {
        modelContext.setCurrentUser(null);
        goToSceneWithResource("Auth/LoginView.fxml");
    }

    @FXML
    private void onExportButtonClicked()
    {
        goToSceneWithResource("External/ExportView.fxml");
    }

    @FXML
    private void onImportButtonClicked()
    {
        goToSceneWithResource("External/ImportView.fxml");
    }
}
