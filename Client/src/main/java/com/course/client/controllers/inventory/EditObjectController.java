package com.course.client.controllers.inventory;

import com.course.client.domain.Role;
import com.course.client.service.ModelContext;
import com.course.client.service.UiContext;
import com.course.client.ui.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.util.UUID;

public class EditObjectController extends SceneController
{
    @FXML
    private Label currentUserLabel;

//    @FXML
//    private ChoiceBox<Role> selectRoleChoice;

    @Override
    public void setContext(ModelContext modelContext, UiContext uiContext)
    {
        this.modelContext = modelContext;
        this.uiContext = uiContext;
        updateList();
    }

    private void updateList()
    {
        String objectId = modelContext.getCurrentObjectId();
        currentUserLabel.setText("Объект: " + objectId.toString());

//        ObservableList<Role> roles = FXCollections.observableArrayList(Role.values());
//        selectRoleChoice.setItems(roles);
    }

    @FXML
    private void onBackButtonClicked()
    {
        modelContext.setCurrentObjectId(null);
        goToSceneWithResource("Inventory/ObjectsInListView.fxml");
    }

    @FXML
    private void onApplyButtonClicked()
    {
        modelContext.setCurrentObjectId(null);
        goToSceneWithResource("Inventory/ObjectsInListView.fxml");
    }
}
