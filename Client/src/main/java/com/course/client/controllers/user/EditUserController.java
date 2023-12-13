package com.course.client.controllers.user;

import com.course.client.domain.InventoryObject;
import com.course.client.service.ModelContext;
import com.course.client.service.UiContext;
import com.course.client.ui.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.UUID;

public class EditUserController extends SceneController
{
    @FXML
    private Label currentUserLabel;

    @FXML
    private ListView<InventoryObject> listsView;

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
    }
}
