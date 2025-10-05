package com.example.rc.chess.pieces;

import com.example.rc.chess.ChessPiece;
import com.example.rc.chess.ChessBoard;
import com.example.rc.chess.pieces.Rook;
import com.example.rc.chess.pieces.Bishop;

public class Queen extends ChessPiece {
    public Queen(boolean isWhite, int row, int col) {
        super(isWhite, row, col, PieceType.QUEEN);
    }

    @Override
    public boolean isValidMove(int toRow, int toCol, ChessBoard board) {
        Rook rook = new Rook(isWhite, row, col);
        Bishop bishop = new Bishop(isWhite, row, col);

        return rook.isValidMove(toRow, toCol, board) ||
                bishop.isValidMove(toRow, toCol, board);
    }
}