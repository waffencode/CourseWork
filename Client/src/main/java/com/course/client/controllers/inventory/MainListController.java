package com.course.client.controllers.inventory;

import com.course.client.domain.InventoryObjectsList;
import com.course.client.service.ModelContext;
import com.course.client.service.UiContext;
import com.course.client.ui.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;
import java.util.UUID;

public class MainListController extends SceneController
{
    @FXML
    ListView<InventoryObjectsList> listsView;

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
        goToSceneWithResource("Main/MainMenuView.fxml");
    }

    @FXML
    public void onViewButtonClicked()
    {
        UUID selectedListId = listsView.getSelectionModel().getSelectedItem().getId();
        if (selectedListId != null)
        {
            modelContext.setCurrentListId(selectedListId);
            goToSceneWithResource("Inventory/ObjectsInListView.fxml");
        }

    }

    private void updateList()
    {
        UUID currentUserId = modelContext.getCurrentUser().getId();
        List<InventoryObjectsList> list = modelContext.getRequestHandler().getAllLists(currentUserId);
        ObservableList<InventoryObjectsList> inventoryObjectsLists = FXCollections.observableArrayList(list);
        listsView.setItems(inventoryObjectsLists);
    }
}
