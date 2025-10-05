package com.example.rc.chess.pieces;

import com.example.rc.chess.ChessPiece;
import com.example.rc.chess.ChessBoard;

public class Knight extends ChessPiece {
    public Knight(boolean isWhite, int row, int col) {
        super(isWhite, row, col, PieceType.KNIGHT);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, ChessBoard board) {
        int rowDiff = Math.abs(toRow - row);
        int colDiff = Math.abs(toCol - col);

        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            ChessPiece target = board.getPiece(toRow, toCol);
            return target == null || target.isWhite() != isWhite;
        }
        return false;
    }
}