package com.course.client.controllers.inventory;

import com.course.client.domain.InventoryObjectsList;
import com.course.client.service.context.ModelContext;
import com.course.client.service.context.UiContext;
import com.course.client.ui.NotificationDialog;
import com.course.client.ui.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.UUID;

public class EditListController extends SceneController
{
    @FXML
    private Label listIdLabel;

    @FXML
    private TextField listNameField;

    @Override
    public void setContext(ModelContext modelContext, UiContext uiContext)
    {
        this.modelContext = modelContext;
        this.uiContext = uiContext;
        updateList();
    }

    private void updateList()
    {
        UUID listId = modelContext.getCurrentListId();
        listIdLabel.setText("Список: " + listId.toString());
        InventoryObjectsList list = modelContext.getRequestHandler().getList(listId, modelContext.getCurrentUser().getId());

        listNameField.setText(list.getName());
    }

    @FXML
    private void onApplyButtonClicked()
    {
        if (listNameField.getText().isBlank())
        {
            NotificationDialog.showInformationDialog("Введите имя списка!");
            modelContext.getLogger().error("List edit error");
            return;
        }

        if (!containsOnlyAllowedCharacters(listNameField.getText()))
        {
            NotificationDialog.showWarningDialog("Имя может содержать только символы латинского алфавита, цифры и знаки препинания!");
            modelContext.getLogger().error("List edit error");
            return;
        }

        UUID listId = modelContext.getCurrentListId();
        InventoryObjectsList list = modelContext.getRequestHandler().getList(listId, modelContext.getCurrentUser().getId());
        list.setName(listNameField.getText());
        modelContext.getRequestHandler().updateList(list, modelContext.getCurrentUser().getId());
        NotificationDialog.showInformationDialog("Список успешно изменён!");
        modelContext.getLogger().info("List edit success");
        goToSceneWithResource("Inventory/MainListsView.fxml");
    }

    @FXML
    private void onBackButtonClicked()
    {
        modelContext.setCurrentListId(null);
        goToSceneWithResource("Inventory/MainListsView.fxml");
    }

    private static boolean containsOnlyAllowedCharacters(String str)
    {
        String regex = "^[a-zA-Z0-9\\p{Punct} ]+$";
        return str.matches(regex);
    }
}
