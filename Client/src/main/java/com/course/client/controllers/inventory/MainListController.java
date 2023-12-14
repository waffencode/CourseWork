package com.course.client.controllers.inventory;

import com.course.client.domain.InventoryObjectsList;
import com.course.client.service.ModelContext;
import com.course.client.service.UiContext;
import com.course.client.ui.NotificationDialog;
import com.course.client.ui.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;
import java.util.UUID;

public class MainListController extends SceneController
{
    @FXML
    ListView<InventoryObjectsList> listsView;

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
        goToSceneWithResource("Main/MainMenuView.fxml");
    }

    @FXML
    private void onViewButtonClicked()
    {
        if (listsView.getSelectionModel().getSelectedItem() == null)
        {
            NotificationDialog.showWarningDialog("Необходимо выбрать список для просмотра!");
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
            return;
        }

        UUID selectedListId = listsView.getSelectionModel().getSelectedItem().getId();
        if (!modelContext.getRequestHandler().getAllObjectsFromList(selectedListId, modelContext.getCurrentUser().getId()).isEmpty())
        {
            NotificationDialog.showWarningDialog("Невозможно удалить список! В списке находятся объекты!");
            return;
        }

        if (selectedListId != null)
        {
            modelContext.getRequestHandler().deleteList(selectedListId, modelContext.getCurrentUser().getId());
            updateList();
        }
    }

    @FXML
    private void onArchiveButtonClicked()
    {
        if (listsView.getSelectionModel().getSelectedItem() == null)
        {
            NotificationDialog.showWarningDialog("Необходимо выбрать список для архивации!");
            return;
        }

        UUID selectedListId = listsView.getSelectionModel().getSelectedItem().getId();

        if (selectedListId != null)
        {
            modelContext.getRequestHandler().archiveList(selectedListId, modelContext.getCurrentUser().getId());
            updateList();
        }
    }
}
