package com.course.client.controllers.external;

import com.course.client.ui.SceneController;
import javafx.fxml.FXML;

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

    }
}
