package com.example.rc.chess;

import com.example.rc.chess.pieces.Pawn;
import com.example.rc.chess.pieces.Rook;
import com.example.rc.chess.pieces.Knight;
import com.example.rc.chess.pieces.Bishop;
import com.example.rc.chess.pieces.Queen;
import com.example.rc.chess.pieces.King;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private ChessPiece[][] board;
    private boolean isWhiteTurn;
    private ChessPiece selectedPiece;
    private List<int[]> possibleMoves;

    public ChessBoard() {
        board = new ChessPiece[8][8];
        isWhiteTurn = true;
        possibleMoves = new ArrayList<>();
        initializeBoard();
    }

    private void initializeBoard() {
        // Черные фигуры
        board[0][0] = new Rook(false, 0, 0);
        board[0][1] = new Knight(false, 0, 1);
        board[0][2] = new Bishop(false, 0, 2);
        board[0][3] = new Queen(false, 0, 3);
        board[0][4] = new King(false, 0, 4);
        board[0][5] = new Bishop(false, 0, 5);
        board[0][6] = new Knight(false, 0, 6);
        board[0][7] = new Rook(false, 0, 7);

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(false, 1, i);
        }

        // Белые фигуры
        board[7][0] = new Rook(true, 7, 0);
        board[7][1] = new Knight(true, 7, 1);
        board[7][2] = new Bishop(true, 7, 2);
        board[7][3] = new Queen(true, 7, 3);
        board[7][4] = new King(true, 7, 4);
        board[7][5] = new Bishop(true, 7, 5);
        board[7][6] = new Knight(true, 7, 6);
        board[7][7] = new Rook(true, 7, 7);

        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(true, 6, i);
        }
    }

    // Остальные методы без изменений...
    public ChessPiece getPiece(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 8) {
            return null;
        }
        return board[row][col];
    }

    public boolean selectPiece(int row, int col) {
        ChessPiece piece = getPiece(row, col);
        if (piece != null && piece.isWhite() == isWhiteTurn) {
            selectedPiece = piece;
            calculatePossibleMoves();
            return true;
        }
        return false;
    }

    public boolean movePiece(int toRow, int toCol) {
        if (selectedPiece == null || !isValidMove(toRow, toCol)) {
            return false;
        }

        board[selectedPiece.getRow()][selectedPiece.getCol()] = null;
        board[toRow][toCol] = selectedPiece;
        selectedPiece.setPosition(toRow, toCol);

        isWhiteTurn = !isWhiteTurn;
        selectedPiece = null;
        possibleMoves.clear();

        return true;
    }

    private void calculatePossibleMoves() {
        possibleMoves.clear();
        if (selectedPiece == null) return;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (selectedPiece.isValidMove(row, col, this)) {
                    possibleMoves.add(new int[]{row, col});
                }
            }
        }
    }

    private boolean isValidMove(int toRow, int toCol) {
        for (int[] move : possibleMoves) {
            if (move[0] == toRow && move[1] == toCol) {
                return true;
            }
        }
        return false;
    }

    public ChessPiece getSelectedPiece() { return selectedPiece; }
    public List<int[]> getPossibleMoves() { return possibleMoves; }
    public boolean isWhiteTurn() { return isWhiteTurn; }
}