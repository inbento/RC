package com.example.rc.chess.pieces;

import com.example.rc.chess.ChessPiece;
import com.example.rc.chess.ChessBoard;

public class Pawn extends ChessPiece {
    public Pawn(boolean isWhite, int row, int col) {
        super(isWhite, row, col, PieceType.PAWN);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, ChessBoard board) {
        int direction = isWhite ? -1 : 1;
        int startRow = isWhite ? 6 : 1;

        // Ход вперед
        if (col == toCol) {
            // Обычный ход
            if (toRow == row + direction && board.getPiece(toRow, toCol) == null) {
                return true;
            }
            // Двойной ход в начале
            if (row == startRow && toRow == row + 2 * direction &&
                    board.getPiece(toRow, toCol) == null &&
                    board.getPiece(row + direction, col) == null) {
                return true;
            }
        }

        // Взятие по диагонали
        if (Math.abs(toCol - col) == 1 && toRow == row + direction) {
            ChessPiece target = board.getPiece(toRow, toCol);
            return target != null && target.isWhite() != isWhite;
        }

        return false;
    }
}