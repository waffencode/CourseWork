package com.course.client.ui;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class NotificationDialog
{
    public static void showInformationDialog(String message)
    {
        showDialog(Alert.AlertType.INFORMATION, "Информация", message);
    }

    public static void showWarningDialog(String message)
    {
        showDialog(Alert.AlertType.WARNING, "Предупреждение", message);
    }

    public static void showErrorDialog(String message)
    {
        showDialog(Alert.AlertType.ERROR, "Ошибка", message);
    }

    private static void showDialog(Alert.AlertType type, String header, String message)
    {
        Alert alert = new Alert(type);

        Stage stage = (Stage)alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("Images/clipboard.png"));
        alert.setTitle(header);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
