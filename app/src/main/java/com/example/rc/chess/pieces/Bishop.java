package com.example.rc.chess.pieces;

import com.example.rc.chess.ChessPiece;
import com.example.rc.chess.ChessBoard;

public class Bishop extends ChessPiece {
    public Bishop(boolean isWhite, int row, int col) {
        super(isWhite, row, col, PieceType.BISHOP);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, ChessBoard board) {
        if (Math.abs(toRow - row) != Math.abs(toCol - col)) {
            return false;
        }

        int rowStep = Integer.compare(toRow, row);
        int colStep = Integer.compare(toCol, col);

        int currentRow = row + rowStep;
        int currentCol = col + colStep;

        while (currentRow != toRow || currentCol != toCol) {
            if (board.getPiece(currentRow, currentCol) != null) {
                return false;
            }
            currentRow += rowStep;
            currentCol += colStep;
        }

        ChessPiece target = board.getPiece(toRow, toCol);
        return target == null || target.isWhite() != isWhite;
    }
}