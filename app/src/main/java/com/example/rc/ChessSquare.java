package com.example.rc;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import android.view.ViewGroup;

import com.example.rc.chess.ChessPiece;

public class ChessSquare extends AppCompatTextView {
    private int row;
    private int col;
    private ChessPiece piece;
    private boolean isSelected = false;

    public ChessSquare(Context context, int row, int col) {
        super(context);
        this.row = row;
        this.col = col;

        setTextSize(24);
        setGravity(android.view.Gravity.CENTER);
        setText("");

        // Устанавливаем базовый цвет клетки
        updateBaseColor();
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
        if (piece != null) {
            setText(getPieceSymbol(piece));
            // Делаем текст более контрастным для лучшей видимости на фоне
            setTextColor(piece.isWhite() ? Color.WHITE : Color.BLACK);
            setShadowLayer(2, 1, 1, Color.GRAY); // Добавляем тень для лучшей читаемости
        } else {
            setText("");
            setShadowLayer(0, 0, 0, Color.TRANSPARENT);
        }

        // Обновляем фон с учетом выбора
        updateBackground();
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
        updateBackground();
    }

    private void updateBaseColor() {
        // Более насыщенные цвета для лучшей видимости на фоне
        if ((row + col) % 2 == 0) {
            setBackgroundColor(Color.parseColor("#E8D5B7")); // Более насыщенный светлый
        } else {
            setBackgroundColor(Color.parseColor("#B58763")); // Более насыщенный темный
        }
    }

    private void updateBackground() {
        if (isSelected) {
            // Зеленая обводка для выбранной клетки
            if ((row + col) % 2 == 0) {
                setBackgroundResource(R.drawable.chess_square_light_selected);
            } else {
                setBackgroundResource(R.drawable.chess_square_dark_selected);
            }
        } else {
            // Обычный цвет без обводки
            updateBaseColor();
        }
    }

    private String getPieceSymbol(ChessPiece piece) {
        switch (piece.getType()) {
            case KING:
                return piece.isWhite() ? "♔" : "♚";
            case QUEEN:
                return piece.isWhite() ? "♕" : "♛";
            case ROOK:
                return piece.isWhite() ? "♖" : "♜";
            case BISHOP:
                return piece.isWhite() ? "♗" : "♝";
            case KNIGHT:
                return piece.isWhite() ? "♘" : "♞";
            case PAWN:
                return piece.isWhite() ? "♙" : "♟";
            default:
                return "";
        }
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public ChessPiece getPiece() { return piece; }
}