package com.course.client.controllers.inventory;

import com.course.client.domain.Category;
import com.course.client.domain.InventoryObject;
import com.course.client.service.ModelContext;
import com.course.client.service.UiContext;
import com.course.client.ui.NotificationDialog;
import com.course.client.ui.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class CreateObjectController extends SceneController
{
    @FXML
    private TextField inventoryNumberField, nameField;

    @FXML
    private ChoiceBox<Category> selectCategoryChoice;

    @FXML
    private CheckBox isInPlaceCheckbox;

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
        goToSceneWithResource("Inventory/ObjectsInListView.fxml");
    }

    @FXML
    private void onCreateButtonClicked()
    {
        if (inventoryNumberField.getText().isBlank() || nameField.getText().isBlank() || selectCategoryChoice.getValue() == null)
        {
            NotificationDialog.showInformationDialog("Заполните все требуемые поля!");
            return;
        }

        if (!containsOnlyDigits(inventoryNumberField.getText()) || inventoryNumberField.getText().length() > 15)
        {
            NotificationDialog.showWarningDialog("Инвентарный номер должен содержать только цифры и иметь длину не более 15 цифр!");
            return;
        }

        if (modelContext.getRequestHandler().getObject(inventoryNumberField.getText(), modelContext.getCurrentUser().getId()) != null)
        {
            NotificationDialog.showWarningDialog("Объект с таким инвентарным номером уже существует!");
            return;
        }

        InventoryObject object = new InventoryObject();
        object.setInventoryNumber(inventoryNumberField.getText());
        object.setName(nameField.getText());
        object.setInPlace(isInPlaceCheckbox.isSelected());
        object.setCategory(selectCategoryChoice.getValue());
        object.setListId(modelContext.getCurrentListId());
        object.setAddedById(modelContext.getCurrentUser().getId());

        modelContext.getRequestHandler().createObject(object, modelContext.getCurrentUser().getId());

        goToSceneWithResource("Inventory/ObjectsInListView.fxml");
    }

    private void updateList()
    {
        ObservableList<Category> categories = FXCollections.observableArrayList(Category.values());
        selectCategoryChoice.setItems(categories);
    }

    private static boolean containsOnlyDigits(String str)
    {
        String regex = "\\d+";
        return str.matches(regex);
    }
}
