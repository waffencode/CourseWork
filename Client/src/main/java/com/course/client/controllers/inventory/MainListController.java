package com.course.client.controllers.inventory;

import com.course.client.domain.InventoryObjectsList;
import com.course.client.domain.Role;
import com.course.client.service.context.ModelContext;
import com.course.client.service.context.UiContext;
import com.course.client.ui.NotificationDialog;
import com.course.client.ui.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.List;
import java.util.UUID;

public class MainListController extends SceneController
{
    @FXML
    ListView<InventoryObjectsList> listsView;

    @FXML
    Button viewButton, deleteButton, archiveButton, editButton, searchButton, mergeButton, createButton;

    @Override
    public void initController(ModelContext modelContext, UiContext uiContext)
    {
        this.modelContext = modelContext;
        this.uiContext = uiContext;
        updateList();

        Role userRole = modelContext.getCurrentUser().getRole();
        deleteButton.setVisible(userRole == Role.ADMINISTRATOR);
        archiveButton.setVisible(userRole.compareTo(Role.INVENTORY_OFFICER) >= 0);
        editButton.setVisible(userRole.compareTo(Role.INVENTORY_OFFICER) >= 0);
        mergeButton.setVisible(userRole.compareTo(Role.INVENTORY_OFFICER) >= 0);
        createButton.setVisible(userRole.compareTo(Role.INVENTORY_OFFICER) >= 0);
    }

    @FXML
    private void onBackButtonClicked()
    {
        goToSceneWithResource("Main/MainMenuView.fxml");
    }

    @FXML
    private void onViewButtonClicked()
    {
        if (listsView.getSelectionModel().getSelectedItem() == null)
        {
            NotificationDialog.showWarningDialog("Необходимо выбрать список для просмотра!");
            modelContext.getLogger().error("List view attempt with empty selection!");
            return;
        }

        UUID selectedListId = listsView.getSelectionModel().getSelectedItem().getId();

        if (selectedListId != null)
        {
            modelContext.setCurrentListId(selectedListId);
            goToSceneWithResource("Inventory/ObjectsInListView.fxml");
        }
    }

    @FXML
    private void onEditButtonClicked()
    {
        if (listsView.getSelectionModel().getSelectedItem() == null)
        {
            NotificationDialog.showWarningDialog("Необходимо выбрать список для редактирования!");
            modelContext.getLogger().error("List edit attempt with empty selection!");
            return;
        }

        UUID selectedListId = listsView.getSelectionModel().getSelectedItem().getId();

        if (selectedListId != null)
        {
            modelContext.setCurrentListId(selectedListId);
            goToSceneWithResource("Inventory/EditListView.fxml");
        }
    }

    @FXML
    private void onCreateButtonClicked()
    {
        goToSceneWithResource("Inventory/CreateListView.fxml");
    }

    private void updateList()
    {
        UUID currentUserId = modelContext.getCurrentUser().getId();
        List<InventoryObjectsList> list = modelContext.getRequestHandler().getAllLists(currentUserId);
        ObservableList<InventoryObjectsList> inventoryObjectsLists = FXCollections.observableArrayList(list);
        listsView.setItems(inventoryObjectsLists);
    }

    @FXML
    private void onSearchButtonClicked()
    {
        goToSceneWithResource("Inventory/SearchView.fxml");
    }

    @FXML
    private void onMergeButtonClicked()
    {
        goToSceneWithResource("Inventory/MergeListView.fxml");
    }

    @FXML
    private void onDeleteButtonClicked()
    {
        if (listsView.getSelectionModel().getSelectedItem() == null)
        {
            NotificationDialog.showWarningDialog("Необходимо выбрать список для удаления!");
            modelContext.getLogger().error("List delete attempt with empty selection!");
            return;
        }

        UUID selectedListId = listsView.getSelectionModel().getSelectedItem().getId();

        if (!modelContext.getRequestHandler().getAllObjectsFromList(selectedListId, modelContext.getCurrentUser().getId()).isEmpty())
        {
            NotificationDialog.showWarningDialog("Невозможно удалить список! В списке находятся объекты!");
            return;
        }

        modelContext.getRequestHandler().deleteList(selectedListId, modelContext.getCurrentUser().getId());
        NotificationDialog.showInformationDialog("Список удалён!");
        modelContext.getLogger().info("List " + selectedListId + " deleted");
        updateList();
    }

    @FXML
    private void onArchiveButtonClicked()
    {
        if (listsView.getSelectionModel().getSelectedItem() == null)
        {
            NotificationDialog.showWarningDialog("Необходимо выбрать список для архивации!");
            modelContext.getLogger().error("List archivation attempt with empty selection!");
            return;
        }

        UUID selectedListId = listsView.getSelectionModel().getSelectedItem().getId();

        if (selectedListId != null)
        {
            modelContext.getRequestHandler().archiveList(selectedListId, modelContext.getCurrentUser().getId());
            NotificationDialog.showInformationDialog("Список архивирован!");
            modelContext.getLogger().info("List " + selectedListId + " archived");
            updateList();
        }
    }
}
