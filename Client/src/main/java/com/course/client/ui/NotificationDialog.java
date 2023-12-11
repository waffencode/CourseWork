package com.course.client.ui;

import javafx.scene.control.Alert;

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
        alert.setTitle(header);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
