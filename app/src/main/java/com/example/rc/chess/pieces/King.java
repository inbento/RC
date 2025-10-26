package com.example.rc.chess.pieces;

import com.example.rc.chess.ChessPiece;
import com.example.rc.chess.ChessBoard;

public class King extends ChessPiece {
    private boolean hasMoved = false;

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

        if (rowDiff == 0 && colDiff == 2 && !hasMoved) {
            return isValidCastling(toRow, toCol, board);
        }

        return false;
    }

    private boolean isValidCastling(int toRow, int toCol, ChessBoard board) {
        int direction = toCol > col ? 1 : -1; // 1 - короткая, -1 - длинная
        int rookCol = direction == 1 ? 7 : 0;

        ChessPiece rook = board.getPiece(row, rookCol);
        if (rook == null || rook.getType() != ChessPiece.PieceType.ROOK || ((Rook) rook).hasMoved()) {
            return false;
        }

        int startCol = Math.min(col, rookCol) + 1;
        int endCol = Math.max(col, rookCol) - 1;
        for (int c = startCol; c <= endCol; c++) {
            if (board.getPiece(row, c) != null) {
                return false;
            }
        }

        return true;
    }

    public void setMoved() {
        hasMoved = true;
    }

    public boolean hasMoved() {
        return hasMoved;
    }
}