package com.course.client.controllers.inventory;

import com.course.client.ui.SceneController;
import javafx.fxml.FXML;

public class CreateObjectController extends SceneController
{
    @FXML
    private void onBackButtonClicked()
    {
        goToSceneWithResource("Inventory/ObjectsInListView.fxml");
    }

    @FXML
    private void onCreateButtonClicked()
    {

    }
}
