package com.assignment.tictactoe.controller;

import com.assignment.tictactoe.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements BoardUI {
    private BoardImpl board;
    private Player humanPlayer;
    private Player aiPlayer;


    @FXML
    private GridPane grid;

    @FXML
    private Button but1;

    @FXML
    private Button but2;

    @FXML
    private Button but3;

    @FXML
    private Button but4;

    @FXML
    private Button but5;

    @FXML
    private Button but6;

    @FXML
    private Button but7;

    @FXML
    private Button but8;

    @FXML
    private Button but9;

    public BoardController() {
        board = new BoardImpl(this);
        humanPlayer = new HumanPlayer(board);
        aiPlayer = new AIPlayer(board);
    }

    @FXML
    void btOnAction(ActionEvent event) {
        Button but = (Button) event.getSource();

        if(!but.getText().isEmpty()){
            // new Alert(Alert.AlertType.INFORMATION, "Already Selected this button").showAndWait();
            return;
        }

        String id = but.getId();

        int cell = Integer.parseInt(id.substring(3));

        int count = 1;
        L1:for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (count == cell) {
                    humanPlayer.move(i, j);
                    update(i, j, Piece.X);
                    aiPlayer.move(-1, -1);
                    board.printBoard();
                    if(board.checkWinner() != null){
                        notifyWinner(board.checkWinner().getWinnerPiece());
                    }else if(board.isBoardFull()){
                        showAlert("Tie");
                    }
                    break L1;
                }
                count++;
            }
        }

    }


    public void update(int row, int col, Piece piece) {
        Button[][] buttons = {{but1, but2, but3}, {but4, but5, but6}, {but7, but8, but9}};

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (i == row && j == col) {
                    if (piece == Piece.X) {
                        buttons[row][col].setText("X");
                    } else if (piece == Piece.O) {
                        buttons[row][col].setText("O");
                    } else {
                        buttons[row][col].setText("");
                    }
                }
            }
        }
    }


    @Override
    public void notifyWinner(Piece winner) {
        if (Piece.X == board.checkWinner().getWinnerPiece()) {
            System.out.println("X is winner");
            colorUpdate(board.checkWinner());
            showAlert("X is winner");
        } else if (Piece.O == board.checkWinner().getWinnerPiece()) {
            System.out.println("O is winner");
            colorUpdate(board.checkWinner());
            showAlert("O is winner");
        }

    }

    private void colorUpdate(Winner winner) {
        Button[][] button = {{but1, but2, but3}, {but4, but5, but6}, {but7, but8, but9}};

        int[] rows = {winner.getRow1(), winner.getRow2(), winner.getRow3()};
        int[] col = {winner.getCol1(), winner.getCol2(), winner.getCol3()};

        for (int i = 0; i < rows.length; i++) {
            button[rows[i]][col[i]].setStyle("-fx-background-color: red;");
        }
    }

    private void resetButtonStyles() {
        Button[][] button = {{but1, but2, but3}, {but4, but5, but6}, {but7, but8, but9}};

        for (int i = 0; i < button.length; i++) {
            for (int j = 0; j < button[i].length; j++) {
                button[i][j].setStyle("-fx-background-color:  #1B4242;");
            }
        }
    }

    public void updateUi(){
        Button[][] button = {{but1, but2, but3}, {but4, but5, but6}, {but7, but8, but9}};

        for (int i = 0; i < button.length; i++) {
            for (int j = 0; j < button[i].length; j++) {
                button[i][j].setText("");
            }
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setOnCloseRequest((DialogEvent event) -> {
            board.initializeBoard();
            resetButtonStyles();
            updateUi();

        });
        alert.showAndWait();
    }

}
