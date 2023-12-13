package com.course.client.controllers.inventory;

import com.course.client.ui.SceneController;
import javafx.fxml.FXML;

public class EditListController extends SceneController
{
    @FXML
    private void onBackButtonClicked()
    {
        goToSceneWithResource("Inventory/MainListsView.fxml");
    }
}
