package com.example.rc.chess;

public abstract class ChessPiece {
    protected boolean isWhite;
    protected int row;
    protected int col;
    protected PieceType type;

    public ChessPiece(boolean isWhite, int row, int col, PieceType type) {
        this.isWhite = isWhite;
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public abstract boolean isValidMove(int toRow, int toCol, ChessBoard board);

    public boolean isWhite() { return isWhite; }
    public int getRow() { return row; }
    public int getCol() { return col; }
    public PieceType getType() { return type; }
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public enum PieceType {
        PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING
    }
}