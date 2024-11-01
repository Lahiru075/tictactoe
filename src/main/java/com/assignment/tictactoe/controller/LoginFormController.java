package com.assignment.tictactoe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private AnchorPane MainAnchorPane;

    @FXML
    private Button btGoNext;

    @FXML
    void btGoOnAction(ActionEvent event) throws IOException {
        MainAnchorPane.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/PlayForm.fxml"));
        MainAnchorPane.getChildren().add(anchorPane);
    }

}
