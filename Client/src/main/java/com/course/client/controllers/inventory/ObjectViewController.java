package com.course.client.controllers.inventory;

import com.course.client.domain.Category;
import com.course.client.domain.InventoryObject;
import com.course.client.service.ModelContext;
import com.course.client.service.UiContext;
import com.course.client.ui.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ObjectViewController extends SceneController
{
    @FXML
    private TextField nameField;

    @FXML
    private ChoiceBox<Category> selectCategoryChoice;

    @FXML
    private CheckBox isInPlaceCheckbox;

    @FXML
    Label currentObjectLabel;

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
        currentObjectLabel.setText("Объект: " + objectId);

        InventoryObject object = modelContext.getRequestHandler().getObject(objectId, modelContext.getCurrentUser().getId());

        nameField.setText(object.getName());
        isInPlaceCheckbox.setSelected(object.getInPlace());
        selectCategoryChoice.setValue(object.getCategory());

        ObservableList<Category> categories = FXCollections.observableArrayList(Category.values());
        selectCategoryChoice.setItems(categories);
    }

    @FXML
    private void onBackButtonClicked()
    {
        modelContext.setCurrentObjectId(null);
        goToSceneWithResource("Inventory/ObjectsInListView.fxml");
    }
}
