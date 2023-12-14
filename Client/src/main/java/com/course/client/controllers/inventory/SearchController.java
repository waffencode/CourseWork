package com.course.client.controllers.inventory;

import com.course.client.domain.InventoryObject;
import com.course.client.service.ModelContext;
import com.course.client.service.UiContext;
import com.course.client.ui.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.util.List;
import java.util.UUID;

public class SearchController extends SceneController
{
    @FXML
    private RadioButton nameSearchButton, idSearchButton;

    @FXML
    private TextField criteriaField;

    @FXML
    private ListView<InventoryObject> listsView;

    @Override
    public void setContext(ModelContext modelContext, UiContext uiContext)
    {
        this.modelContext = modelContext;
        this.uiContext = uiContext;
        init();
    }

    @FXML
    private void onBackButtonClicked()
    {
        goToSceneWithResource("Inventory/MainListsView.fxml");
    }

    @FXML
    private void onSearchButtonClicked()
    {
        String criteria = criteriaField.getText();

        if (idSearchButton.isSelected())
        {
            List<InventoryObject> list = modelContext.getRequestHandler().searchObjectsById(criteria, modelContext.getCurrentUser().getId());
            updateListWith(list);
        }
        else
        {
            List<InventoryObject> list = modelContext.getRequestHandler().searchObjectsByName(criteria, modelContext.getCurrentUser().getId());
            updateListWith(list);
        }
    }

    private void init()
    {
        ToggleGroup toggleGroup = new ToggleGroup();
        idSearchButton.setToggleGroup(toggleGroup);
        nameSearchButton.setToggleGroup(toggleGroup);
        idSearchButton.setSelected(true);
    }

    private void updateListWith(List<InventoryObject> list)
    {
        ObservableList<InventoryObject> inventoryObjectsLists = FXCollections.observableArrayList(list);
        listsView.setItems(inventoryObjectsLists);
    }
}
