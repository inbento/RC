package com.example.rc.chess.pieces;

import com.example.rc.chess.ChessPiece;
import com.example.rc.chess.ChessBoard;

public class Rook extends ChessPiece {
    public Rook(boolean isWhite, int row, int col) {
        super(isWhite, row, col, PieceType.ROOK);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, ChessBoard board) {
        if (row != toRow && col != toCol) {
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