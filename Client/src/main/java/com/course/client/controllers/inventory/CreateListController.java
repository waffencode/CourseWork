package com.course.client.controllers.inventory;

import com.course.client.domain.InventoryObjectsList;
import com.course.client.ui.NotificationDialog;
import com.course.client.ui.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateListController extends SceneController
{
    @FXML
    private TextField listName;

    @FXML
    private void onBackButtonClicked()
    {
        goToSceneWithResource("Inventory/MainListsView.fxml");
    }

    @FXML
    private void onCreateButtonClicked()
    {
        if (listName.getText().isBlank())
        {
            NotificationDialog.showInformationDialog("Введите имя списка!");
            return;
        }

        InventoryObjectsList list = new InventoryObjectsList();
        list.setName(listName.getText());
        list.setArchived(false);
        list.setCreatedById(modelContext.getCurrentUser().getId());
        modelContext.getRequestHandler().createList(list, modelContext.getCurrentUser().getId());

        goToSceneWithResource("Inventory/MainListsView.fxml");
    }
}
