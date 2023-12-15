package com.course.client.controllers.external;

import com.course.client.domain.InventoryObject;
import com.course.client.domain.InventoryObjectsList;
import com.course.client.service.file.CsvFile;
import com.course.client.service.file.JsonFile;
import com.course.client.service.context.ModelContext;
import com.course.client.service.context.UiContext;
import com.course.client.ui.NotificationDialog;
import com.course.client.ui.SceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class ExportController extends SceneController
{
    @FXML
    private ListView<InventoryObjectsList> listsView;

    @FXML
    private RadioButton csvFormatButton, jsonFormatButton;

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
    private void onExportButtonClicked()
    {
        if (csvFormatButton.isSelected())
        {
            FileChooser chooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
            chooser.getExtensionFilters().add(extFilter);
            File file = chooser.showSaveDialog(uiContext.getStage());

            if (file != null)
            {
                if (listsView.getSelectionModel().getSelectedItem() != null)
                {
                    UUID listId = listsView.getSelectionModel().getSelectedItem().getId();
                    List<InventoryObject> list = modelContext.getRequestHandler().getAllObjectsFromList(listId, modelContext.getCurrentUser().getId());
                    CsvFile csvFile = new CsvFile(file);
                    csvFile.writeList(list);
                }
                else
                {
                    List<InventoryObject> list = modelContext.getRequestHandler().getAllObjects(modelContext.getCurrentUser().getId());
                    CsvFile csvFile = new CsvFile(file);
                    csvFile.writeList(list);
                }
            }
        }
        else
        {
            FileChooser chooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            chooser.getExtensionFilters().add(extFilter);
            File file = chooser.showSaveDialog(uiContext.getStage());

            if (file != null)
            {
                if (listsView.getSelectionModel().getSelectedItem() != null)
                {
                    UUID listId = listsView.getSelectionModel().getSelectedItem().getId();
                    List<InventoryObject> list = modelContext.getRequestHandler().getAllObjectsFromList(listId, modelContext.getCurrentUser().getId());
                    JsonFile jsonFile = new JsonFile(file);
                    jsonFile.writeList(list);
                }
                else
                {
                    List<InventoryObject> list = modelContext.getRequestHandler().getAllObjects(modelContext.getCurrentUser().getId());
                    JsonFile jsonFile = new JsonFile(file);
                    jsonFile.writeList(list);
                }
            }
        }

        NotificationDialog.showInformationDialog("Экспорт произведён успешно!");
        modelContext.getLogger().info("Export success");
        goToSceneWithResource("Main/MainMenuView.fxml");
    }

    private void updateList()
    {
        ToggleGroup group = new ToggleGroup();
        csvFormatButton.setToggleGroup(group);
        jsonFormatButton.setToggleGroup(group);
        jsonFormatButton.setSelected(true);

        UUID currentUserId = modelContext.getCurrentUser().getId();
        List<InventoryObjectsList> list = modelContext.getRequestHandler().getAllLists(currentUserId);
        ObservableList<InventoryObjectsList> inventoryObjectsLists = FXCollections.observableArrayList(list);
        listsView.setItems(inventoryObjectsLists);
    }
}
