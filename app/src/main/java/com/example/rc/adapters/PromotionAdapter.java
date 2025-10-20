package com.example.rc.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rc.R;

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.PromotionViewHolder> {

    private String[] promotionPieces;
    private OnPromotionPieceSelected listener;
    private boolean isWhite;

    public interface OnPromotionPieceSelected {
        void onPieceSelected(int pieceType);
    }

    public PromotionAdapter(OnPromotionPieceSelected listener, boolean isWhite) {
        this.listener = listener;
        this.isWhite = isWhite;

        // Определяем символы в зависимости от цвета
        if (isWhite) {
            promotionPieces = new String[]{"♕", "♖", "♗", "♘"}; // Белые фигуры
        } else {
            promotionPieces = new String[]{"♛", "♜", "♝", "♞"}; // Черные фигуры
        }
    }

    @NonNull
    @Override
    public PromotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_promotion, parent, false);
        return new PromotionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromotionViewHolder holder, int position) {
        holder.tvPiece.setText(promotionPieces[position]);
        holder.tvPiece.setTextColor(isWhite ? 0xFF000000 : 0xFFFFFFFF);

        // Устанавливаем названия фигур
        String[] pieceNames = {"Ферзь", "Ладья", "Слон", "Конь"};
        holder.tvPieceName.setText(pieceNames[position]);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                // 1-Ферзь, 2-Ладья, 3-Слон, 4-Конь
                listener.onPieceSelected(position + 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return promotionPieces.length;
    }

    static class PromotionViewHolder extends RecyclerView.ViewHolder {
        TextView tvPiece;
        TextView tvPieceName;

        public PromotionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPiece = itemView.findViewById(R.id.tvPiece);
            tvPieceName = itemView.findViewById(R.id.tvPieceName);
        }
    }
}