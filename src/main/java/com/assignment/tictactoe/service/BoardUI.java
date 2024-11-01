package com.assignment.tictactoe.service;

public interface BoardUI {
    void update(int row, int col, Piece piece);
    void notifyWinner(Piece winner);
}
