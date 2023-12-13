package com.course.client.controllers.user;

import com.course.client.domain.Category;
import com.course.client.domain.Role;
import com.course.client.domain.User;
import com.course.client.service.ModelContext;
import com.course.client.service.UiContext;
import com.course.client.ui.NotificationDialog;
import com.course.client.ui.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.UUID;

public class EditUserController extends SceneController
{
    @FXML
    private Label currentUserLabel;

    @FXML
    private ChoiceBox<Role> selectRoleChoice;

    @FXML
    private TextField userLoginField;

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

        User user = modelContext.getRequestHandler().getUser(userId, modelContext.getCurrentUser().getId());
        selectRoleChoice.setValue(user.getRole());
        userLoginField.setText(user.getLogin());
    }

    @FXML
    private void onApplyButtonClicked()
    {
        if (userLoginField.getText().isBlank() || selectRoleChoice.getValue() == null)
        {
            NotificationDialog.showInformationDialog("Заполните требуемые поля!");
            return;
        }

        if (!containsOnlyAllowedCharacters(userLoginField.getText()))
        {
            NotificationDialog.showWarningDialog("Логин может содержать только символы латинского алфавита, цифры и знаки препинания!");
            return;
        }

        UUID userId = modelContext.getCurrentUserOnEditId();
        User user = modelContext.getRequestHandler().getUser(userId, modelContext.getCurrentUser().getId());

        user.setLogin(userLoginField.getText());
        user.setRole(selectRoleChoice.getValue());

        modelContext.getRequestHandler().updateUser(user, modelContext.getCurrentUser().getId());

        modelContext.setCurrentUserOnEditId(null);
        goToSceneWithResource("User/UserListView.fxml");
    }

    private static boolean containsOnlyAllowedCharacters(String str)
    {
        String regex = "^[a-zA-Z0-9\\p{Punct} ]+$";
        return str.matches(regex);
    }
}
