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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditObjectController extends SceneController
{
    @FXML
    private TextField nameField;

    @FXML
    private ChoiceBox<Category> selectCategoryChoice;

    @FXML
    private CheckBox isInPlaceCheckbox;

    @FXML Label currentObjectLabel;

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
        currentObjectLabel.setText("Объект: " + objectId.toString());

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

    @FXML
    private void onApplyButtonClicked()
    {
        if (nameField.getText().isBlank() || selectCategoryChoice.getValue() == null)
        {
            NotificationDialog.showInformationDialog("Заполните все требуемые поля!");
            return;
        }

        if (!containsOnlyAllowedCharacters(nameField.getText()))
        {
            NotificationDialog.showWarningDialog("Имя может содержать только символы латинского алфавита, цифры и знаки препинания!");
            return;
        }

        String objectId = modelContext.getCurrentObjectId();
        InventoryObject object = modelContext.getRequestHandler().getObject(objectId, modelContext.getCurrentUser().getId());
        object.setName(nameField.getText());
        object.setInPlace(isInPlaceCheckbox.isSelected());
        object.setCategory(selectCategoryChoice.getValue());
        modelContext.getRequestHandler().updateObject(object, modelContext.getCurrentUser().getId());

        modelContext.setCurrentObjectId(null);
        NotificationDialog.showInformationDialog("Объект успешно изменён!");
        goToSceneWithResource("Inventory/ObjectsInListView.fxml");
    }

    private static boolean containsOnlyAllowedCharacters(String str)
    {
        String regex = "^[a-zA-Z0-9\\p{Punct} ]+$";
        return str.matches(regex);
    }
}
