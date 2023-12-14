package com.course.client.controllers.inventory;

import com.course.client.domain.InventoryObject;
import com.course.client.service.ModelContext;
import com.course.client.service.UiContext;
import com.course.client.ui.NotificationDialog;
import com.course.client.ui.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.sql.Timestamp;
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

    @FXML
    private void onAddButtonClicked()
    {
        goToSceneWithResource("Inventory/CreateObjectView.fxml");
    }

    @FXML
    private void onViewButtonClicked()
    {
        if (listsView.getSelectionModel().getSelectedItem() == null)
        {
            NotificationDialog.showWarningDialog("Необходимо выбрать объект для просмотра!");
            modelContext.getLogger().error("View attempt with empty selection!");
            return;
        }

        String selectedListId = listsView.getSelectionModel().getSelectedItem().getInventoryNumber();

        if (selectedListId != null)
        {
            modelContext.setCurrentObjectId(selectedListId);
            goToSceneWithResource("Inventory/ObjectDataView.fxml");
        }
    }

    @FXML
    private void onEditButtonClicked()
    {
        if (listsView.getSelectionModel().getSelectedItem() == null)
        {
            NotificationDialog.showWarningDialog("Необходимо выбрать объект для редактирования!");
            modelContext.getLogger().error("Edit attempt with empty selection!");
            return;
        }

        String selectedListId = listsView.getSelectionModel().getSelectedItem().getInventoryNumber();

        if (selectedListId != null)
        {
            modelContext.setCurrentObjectId(selectedListId);
            goToSceneWithResource("Inventory/EditObjectView.fxml");
        }
    }

    @FXML
    private void onDecommissionButtonClicked()
    {
        if (listsView.getSelectionModel().getSelectedItem() == null)
        {
            NotificationDialog.showWarningDialog("Необходимо выбрать объект для списания!");
            modelContext.getLogger().error("Decommission attempt with empty selection!");
            return;
        }

        String selectedListId = listsView.getSelectionModel().getSelectedItem().getInventoryNumber();

        if (selectedListId != null)
        {
            InventoryObject object = modelContext.getRequestHandler().getObject(selectedListId, modelContext.getCurrentUser().getId());
            object.setDecommissioned(true);
            object.setDecommissionedById(modelContext.getCurrentUser().getId());
            object.setDecommissionDate(new Timestamp(System.currentTimeMillis()));
            modelContext.getRequestHandler().updateObject(object, modelContext.getCurrentUser().getId());
            modelContext.getLogger().info("Object " + object.getInventoryNumber() + " decommissioned");
        }
    }

    @FXML
    private void onDeleteButtonClicked()
    {
        if (listsView.getSelectionModel().getSelectedItem() == null)
        {
            NotificationDialog.showWarningDialog("Необходимо выбрать объект для удаления!");
            modelContext.getLogger().error("Delete attempt with empty selection!");
            return;
        }

        String selectedObjectInventoryNumber = listsView.getSelectionModel().getSelectedItem().getInventoryNumber();
        modelContext.getRequestHandler().deleteObject(selectedObjectInventoryNumber, modelContext.getCurrentUser().getId());
        modelContext.getLogger().info("Object " + selectedObjectInventoryNumber + " deleted");
        updateList();
    }

    @FXML
    private void onMoveButtonClicked()
    {
        if (listsView.getSelectionModel().getSelectedItem() == null)
        {
            NotificationDialog.showWarningDialog("Необходимо выбрать объект для изменения списка!");
            modelContext.getLogger().error("Edit attempt with empty selection!");
            return;
        }

        String selectedListId = listsView.getSelectionModel().getSelectedItem().getInventoryNumber();

        if (selectedListId != null)
        {
            modelContext.setCurrentObjectId(selectedListId);
            goToSceneWithResource("Inventory/MoveObjectView.fxml");
        }
    }
}
