package com.course.client.controllers.user;

import com.course.client.ui.SceneController;
import javafx.fxml.FXML;

public class UserListController extends SceneController
{
    @FXML
    public void onBackButtonClicked()
    {
        goToSceneWithResource("Main/MainMenuView.fxml");
    }
}
