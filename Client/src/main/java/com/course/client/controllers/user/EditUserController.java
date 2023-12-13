package com.course.client.controllers.user;

import com.course.client.domain.Category;
import com.course.client.domain.Role;
import com.course.client.service.ModelContext;
import com.course.client.service.UiContext;
import com.course.client.ui.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.util.UUID;

public class EditUserController extends SceneController
{
    @FXML
    private Label currentUserLabel;

    @FXML
    private ChoiceBox<Role> selectRoleChoice;

    @Override
    public void setContext(ModelContext modelContext, UiContext uiContext)
    {
        this.modelContext = modelContext;
        this.uiContext = uiContext;
        updateList();
    }

    @FXML
    private void onBackButtonClicked()
    {
        modelContext.setCurrentUserOnEditId(null);
        goToSceneWithResource("User/UserListView.fxml");
    }

    private void updateList()
    {
        UUID userId = modelContext.getCurrentUserOnEditId();
        currentUserLabel.setText("Пользователь: " + userId.toString());

        ObservableList<Role> roles = FXCollections.observableArrayList(Role.values());
        selectRoleChoice.setItems(roles);
    }

    @FXML
    private void onApplyButtonClicked()
    {
        modelContext.setCurrentUserOnEditId(null);
        goToSceneWithResource("User/UserListView.fxml");
    }
}
