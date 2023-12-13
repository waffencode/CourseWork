package com.course.client.controllers.external;

import com.course.client.domain.InventoryObject;
import com.course.client.domain.InventoryObjectsList;
import com.course.client.service.JsonFile;
import com.course.client.ui.NotificationDialog;
import com.course.client.ui.SceneController;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class ImportController extends SceneController
{
    @FXML
    private void onBackButtonClicked()
    {
        goToSceneWithResource("Main/MainMenuView.fxml");
    }

    @FXML
    private void onImportButtonClicked()
    {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showOpenDialog(uiContext.getStage());

        if (file != null)
        {
            JsonFile jsonFile = new JsonFile(file);
            List<InventoryObject> list = jsonFile.readList();
            List<InventoryObject> database = modelContext.getRequestHandler().getAllObjects(modelContext.getCurrentUser().getId());
            List<InventoryObjectsList> databaseLists = modelContext.getRequestHandler().getAllLists(modelContext.getCurrentUser().getId());

            for (InventoryObject listObject : list)
            {
                for (InventoryObject databaseObject : database)
                {
                    if (listObject.getInventoryNumber().equals(databaseObject.getInventoryNumber()))
                    {
                        NotificationDialog.showErrorDialog("В импортируемом списке есть объекты с номерами, уже содержащимися в БД!");
                        return;
                    }
                }
            }

            for (InventoryObject listObject : list)
            {
                for (InventoryObjectsList databaseObject : databaseLists)
                {
                    if (listObject.getListId().equals(databaseObject.getId()))
                    {
                        NotificationDialog.showErrorDialog("В импортируемом списке есть списки с ID, уже содержащимися в БД!");
                        return;
                    }
                }
            }

            HashSet<UUID> ids = new HashSet<>();
            for (InventoryObjectsList databaseObject : databaseLists)
            {
                ids.add(databaseObject.getId());
            }

            for (InventoryObject listObject : list)
            {
                if (!ids.contains(listObject.getListId()))
                {
                    InventoryObjectsList inventoryObjectsList = new InventoryObjectsList();
                    inventoryObjectsList.setId(listObject.getListId());
                    inventoryObjectsList.setName("Imported");
                    inventoryObjectsList.setCreatedById(modelContext.getCurrentUser().getId());
                    modelContext.getRequestHandler().createList(inventoryObjectsList, modelContext.getCurrentUser().getId());
                }

                modelContext.getRequestHandler().createObject(listObject, modelContext.getCurrentUser().getId());
            }

            NotificationDialog.showInformationDialog("Успешный импорт!");
        }

        goToSceneWithResource("Main/MainMenuView.fxml");
    }
}
