package com.course.client.controllers.settings;

import com.course.client.ui.SceneController;
import javafx.fxml.FXML;

public class SettingsController extends SceneController
{
    @FXML
    public void onBackButtonClicked()
    {
        goToSceneWithResource("Main/MainMenuView.fxml");
    }
}
