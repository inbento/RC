package com.example.rc.chess.pieces;

import com.example.rc.chess.ChessPiece;
import com.example.rc.chess.ChessBoard;

public class King extends ChessPiece {
    public King(boolean isWhite, int row, int col) {
        super(isWhite, row, col, PieceType.KING);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, ChessBoard board) {
        int rowDiff = Math.abs(toRow - row);
        int colDiff = Math.abs(toCol - col);

        if (rowDiff <= 1 && colDiff <= 1) {
            ChessPiece target = board.getPiece(toRow, toCol);
            return target == null || target.isWhite() != isWhite;
        }

        return false;
    }
}