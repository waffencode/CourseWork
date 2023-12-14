package com.course.client.controllers.inventory;

import com.course.client.domain.InventoryObject;
import com.course.client.domain.InventoryObjectsList;
import com.course.client.service.ModelContext;
import com.course.client.service.UiContext;
import com.course.client.ui.NotificationDialog;
import com.course.client.ui.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.UUID;

public class MergeListController extends SceneController
{
    @FXML
    private ChoiceBox<InventoryObjectsList> selectTargetList, selectAuxList;

    @FXML
    private TextField newListName;

    @Override
    public void setContext(ModelContext modelContext, UiContext uiContext)
    {
        this.modelContext = modelContext;
        this.uiContext = uiContext;
        updateList();
    }

    private void updateList()
    {
        UUID currentUserId = modelContext.getCurrentUser().getId();
        List<InventoryObjectsList> list = modelContext.getRequestHandler().getAllLists(currentUserId);
        ObservableList<InventoryObjectsList> inventoryObjectsLists = FXCollections.observableArrayList(list);
        selectTargetList.setItems(inventoryObjectsLists);
        selectAuxList.setItems(inventoryObjectsLists);
    }

    @FXML
    private void onBackButtonClicked()
    {
        goToSceneWithResource("Inventory/MainListsView.fxml");
    }

    @FXML
    private void onApplyButtonClicked()
    {
        InventoryObjectsList target = selectTargetList.getSelectionModel().getSelectedItem();
        InventoryObjectsList aux = selectAuxList.getSelectionModel().getSelectedItem();

        if (target == null || aux == null || newListName.getText().isBlank())
        {
            NotificationDialog.showWarningDialog("Выберите списки для объединения и введите название нового списка!");
            modelContext.getLogger().error("List merge error");
            return;
        }

        if (target.getId() == aux.getId())
        {
            NotificationDialog.showWarningDialog("Невозможно объединить одинаковые списки!");
            modelContext.getLogger().error("List merge error");
            return;
        }

        InventoryObjectsList mergedList = new InventoryObjectsList();
        mergedList.setName(newListName.getText());
        mergedList.setCreatedById(modelContext.getCurrentUser().getId());
        modelContext.getRequestHandler().createList(mergedList, modelContext.getCurrentUser().getId());

        List<InventoryObject> targetListObjects = modelContext.getRequestHandler().getAllObjectsFromList(target.getId(), modelContext.getCurrentUser().getId());
        changeListIdForObjects(targetListObjects, mergedList.getId());
        List<InventoryObject> auxListObjects = modelContext.getRequestHandler().getAllObjectsFromList(aux.getId(), modelContext.getCurrentUser().getId());
        changeListIdForObjects(auxListObjects, mergedList.getId());

        modelContext.getRequestHandler().deleteList(target.getId(), modelContext.getCurrentUser().getId());
        modelContext.getRequestHandler().deleteList(aux.getId(), modelContext.getCurrentUser().getId());

        NotificationDialog.showInformationDialog("Списки успешно объединены!");
        modelContext.getLogger().info("List merge success");
        goToSceneWithResource("Inventory/MainListsView.fxml");
    }

    private void changeListIdForObjects(List<InventoryObject> objects, UUID listID)
    {
        for (InventoryObject object: objects)
        {
            object.setListId(listID);
            modelContext.getRequestHandler().updateObject(object, modelContext.getCurrentUser().getId());
        }
    }
}
