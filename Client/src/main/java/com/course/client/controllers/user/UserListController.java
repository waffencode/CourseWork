package com.course.client.controllers.user;

import com.course.client.domain.User;
import com.course.client.service.context.ModelContext;
import com.course.client.service.context.UiContext;
import com.course.client.ui.NotificationDialog;
import com.course.client.ui.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;
import java.util.UUID;

public class UserListController extends SceneController
{
    @FXML
    private ListView<User> usersList;

    @FXML
    public void onBackButtonClicked()
    {
        goToSceneWithResource("Main/MainMenuView.fxml");
    }

    @Override
    public void initController(ModelContext modelContext, UiContext uiContext)
    {
        this.modelContext = modelContext;
        this.uiContext = uiContext;
        updateList();
    }

    private void updateList()
    {
        UUID currentUserId = modelContext.getCurrentUser().getId();
        List<User> list = modelContext.getRequestHandler().getAllUsers(currentUserId);
        ObservableList<User> users = FXCollections.observableArrayList(list);
        usersList.setItems(users);
    }

    @FXML
    private void onEditButtonClicked()
    {
        if (usersList.getSelectionModel().getSelectedItem() == null)
        {
            NotificationDialog.showWarningDialog("Необходимо выбрать пользователя для редактирования!");
            return;
        }

        UUID selectedUserId = usersList.getSelectionModel().getSelectedItem().getId();

        if (selectedUserId != null)
        {
            modelContext.setCurrentUserOnEditId(selectedUserId);
            goToSceneWithResource("User/EditUserView.fxml");
        }
    }

    @FXML
    private void onDeleteButtonClicked()
    {
        if (usersList.getSelectionModel().getSelectedItem() == null)
        {
            NotificationDialog.showWarningDialog("Необходимо выбрать пользователя для удаления!");
            return;
        }

        UUID selectedUserId = usersList.getSelectionModel().getSelectedItem().getId();

        if (selectedUserId != null)
        {
            modelContext.getLogger().info("User " + selectedUserId + " deleted");
            modelContext.getRequestHandler().deleteUser(selectedUserId, modelContext.getCurrentUser().getId());
            updateList();
        }
    }
}
