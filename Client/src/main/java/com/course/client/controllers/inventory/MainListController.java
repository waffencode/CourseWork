package com.course.client.controllers.inventory;

import com.course.client.ui.SceneController;
import javafx.fxml.FXML;

public class MainListController extends SceneController
{
    @FXML
    public void onBackButtonClicked()
    {
        goToSceneWithResource("Main/MainMenuView.fxml");
    }
}
