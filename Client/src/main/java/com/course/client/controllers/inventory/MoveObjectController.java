package com.course.client.controllers.inventory;

import com.course.client.domain.Category;
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
import javafx.scene.control.Label;

import java.util.List;

public class MoveObjectController extends SceneController
{
    @FXML
    private Label currentObjectLabel;

    @FXML
    private ChoiceBox<InventoryObjectsList> selectListChoice;

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
        InventoryObject object = modelContext.getRequestHandler().getObject(objectId, modelContext.getCurrentUser().getId());
        currentObjectLabel.setText("Объект: " + object.toString());

        List<InventoryObjectsList> allLists = modelContext.getRequestHandler().getAllLists(modelContext.getCurrentUser().getId());
        ObservableList<InventoryObjectsList> lists = FXCollections.observableArrayList(allLists);
        selectListChoice.setItems(lists);
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
        InventoryObjectsList list = selectListChoice.getSelectionModel().getSelectedItem();

        if (list != null)
        {
            String objectId = modelContext.getCurrentObjectId();
            InventoryObject object = modelContext.getRequestHandler().getObject(objectId, modelContext.getCurrentUser().getId());
            object.setListId(list.getId());
            modelContext.getRequestHandler().updateObject(object, modelContext.getCurrentUser().getId());
            modelContext.setCurrentObjectId(null);

            NotificationDialog.showInformationDialog("Список объекта успешно изменён!");
            modelContext.getLogger().info("Object " + objectId + " list changed to " + list.getId());
            goToSceneWithResource("Inventory/ObjectsInListView.fxml");
        }
        else
        {
            NotificationDialog.showWarningDialog("Необходимо выбрать список для перемещения объекта!");
            modelContext.getLogger().error("Object list change error");
        }
    }
}
