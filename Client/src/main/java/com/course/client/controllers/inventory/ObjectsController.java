package com.course.client.controllers.inventory;

import com.course.client.domain.InventoryObject;
import com.course.client.service.ModelContext;
import com.course.client.service.UiContext;
import com.course.client.ui.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;
import java.util.UUID;

public class ObjectsController extends SceneController
{
    @FXML
    private Label currentListLabel;

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
        modelContext.setCurrentListId(null);
        goToSceneWithResource("Inventory/MainListsView.fxml");
    }

    private void updateList()
    {

        UUID listId = modelContext.getCurrentListId();
        currentListLabel.setText("Список: " + listId.toString());

        UUID currentUserId = modelContext.getCurrentUser().getId();
        List<InventoryObject> list = modelContext.getRequestHandler().getAllObjectsFromList(listId, currentUserId);
        ObservableList<InventoryObject> inventoryObjectsLists = FXCollections.observableArrayList(list);
        listsView.setItems(inventoryObjectsLists);
    }
}
