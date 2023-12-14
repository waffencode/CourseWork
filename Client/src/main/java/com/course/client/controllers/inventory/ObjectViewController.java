package com.course.client.controllers.inventory;

import com.course.client.domain.InventoryObject;
import com.course.client.domain.InventoryObjectsList;
import com.course.client.domain.User;
import com.course.client.service.ModelContext;
import com.course.client.service.UiContext;
import com.course.client.ui.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ObjectViewController extends SceneController
{
    @FXML
    private Label inventoryNumber, name, category, listId, addedById, additionDate, decommissionedById, decommissionDate;

    @FXML
    private Label isInPlace, isDecommissioned;

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

        inventoryNumber.setText(object.getInventoryNumber());
        name.setText(object.getName());
        category.setText(object.getCategory().toString());

        InventoryObjectsList list =  modelContext.getRequestHandler().getList(object.getListId(), modelContext.getCurrentUser().getId());
        listId.setText(list.toString());

        User adder = modelContext.getRequestHandler().getUser(object.getAddedById(), modelContext.getCurrentUser().getId());
        addedById.setText(adder.getLogin());
        additionDate.setText(object.getAdditionDate().toString());

        if (object.getDecommissioned())
        {
            isDecommissioned.setText("Списан");
            User decommissioner = modelContext.getRequestHandler().getUser(object.getDecommissionedById(), modelContext.getCurrentUser().getId());
            decommissionedById.setText(decommissioner.getLogin());
            decommissionDate.setText(object.getDecommissionDate().toString());
        }
        else
        {
            isDecommissioned.setText("Актуален");
            decommissionedById.setText("-");
            decommissionDate.setText("-");
        }

        if (object.getInPlace())
        {
            isInPlace.setText("В наличии");
        }
        else
        {
            isInPlace.setText("Нет в наличии");
        }
    }

    @FXML
    private void onBackButtonClicked()
    {
        modelContext.setCurrentObjectId(null);
        goToSceneWithResource("Inventory/ObjectsInListView.fxml");
    }
}
