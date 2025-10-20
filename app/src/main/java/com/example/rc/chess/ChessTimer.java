package com.example.rc.chess;

import android.os.CountDownTimer;
import android.widget.TextView;
import android.view.View;

public class ChessTimer {
    private CountDownTimer whiteTimer;
    private CountDownTimer blackTimer;
    private long whiteTimeLeft;
    private long blackTimeLeft;
    private boolean isWhiteTurn;
    private TextView tvWhiteTimer, tvBlackTimer;
    private View indicatorWhite, indicatorBlack;
    private TimerListener listener;
    private boolean isRunning = false;
    private boolean isPaused = false;

    public interface TimerListener {
        void onTimeOut(boolean isWhite);
        void onTimeUpdate(long whiteTime, long blackTime);
    }

    public ChessTimer(long initialTimeMillis, TextView tvWhiteTimer, TextView tvBlackTimer,
                      View indicatorWhite, View indicatorBlack, TimerListener listener) {
        this.whiteTimeLeft = initialTimeMillis;
        this.blackTimeLeft = initialTimeMillis;
        this.tvWhiteTimer = tvWhiteTimer;
        this.tvBlackTimer = tvBlackTimer;
        this.indicatorWhite = indicatorWhite;
        this.indicatorBlack = indicatorBlack;
        this.listener = listener;
        this.isWhiteTurn = true;

        updateTimerDisplays();
        updateIndicators();
    }

    public void start() {
        if (isRunning) return;

        isRunning = true;
        isPaused = false;

        if (isWhiteTurn) {
            startWhiteTimer();
        } else {
            startBlackTimer();
        }
        updateIndicators();
    }

    public void switchTurn() {
        if (!isRunning) {
            start();
            return;
        }

        // Останавливаем текущий таймер
        if (whiteTimer != null) {
            whiteTimer.cancel();
        }
        if (blackTimer != null) {
            blackTimer.cancel();
        }

        isWhiteTurn = !isWhiteTurn;

        // Запускаем таймер для нового игрока
        if (isWhiteTurn) {
            startWhiteTimer();
        } else {
            startBlackTimer();
        }

        updateIndicators();
    }

    private void startWhiteTimer() {
        if (whiteTimeLeft <= 0) {
            if (listener != null) {
                listener.onTimeOut(true);
            }
            return;
        }

        whiteTimer = new CountDownTimer(whiteTimeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                whiteTimeLeft = millisUntilFinished;
                updateTimerDisplays();
                if (listener != null) {
                    listener.onTimeUpdate(whiteTimeLeft, blackTimeLeft);
                }
            }

            @Override
            public void onFinish() {
                whiteTimeLeft = 0;
                updateTimerDisplays();
                isRunning = false;
                if (listener != null) {
                    listener.onTimeOut(true);
                }
            }
        }.start();
    }

    private void startBlackTimer() {
        if (blackTimeLeft <= 0) {
            if (listener != null) {
                listener.onTimeOut(false);
            }
            return;
        }

        blackTimer = new CountDownTimer(blackTimeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                blackTimeLeft = millisUntilFinished;
                updateTimerDisplays();
                if (listener != null) {
                    listener.onTimeUpdate(whiteTimeLeft, blackTimeLeft);
                }
            }

            @Override
            public void onFinish() {
                blackTimeLeft = 0;
                updateTimerDisplays();
                isRunning = false;
                if (listener != null) {
                    listener.onTimeOut(false);
                }
            }
        }.start();
    }

    public void pause() {
        if (!isRunning) return;

        isPaused = true;
        if (whiteTimer != null) {
            whiteTimer.cancel();
        }
        if (blackTimer != null) {
            blackTimer.cancel();
        }
    }

    public void resume() {
        if (!isPaused || !isRunning) return;

        isPaused = false;
        if (isWhiteTurn) {
            startWhiteTimer();
        } else {
            startBlackTimer();
        }
    }

    public void stop() {
        isRunning = false;
        isPaused = false;
        if (whiteTimer != null) {
            whiteTimer.cancel();
            whiteTimer = null;
        }
        if (blackTimer != null) {
            blackTimer.cancel();
            blackTimer = null;
        }
    }

    public void reset(long initialTimeMillis) {
        stop();
        whiteTimeLeft = initialTimeMillis;
        blackTimeLeft = initialTimeMillis;
        isWhiteTurn = true;
        isRunning = false;
        isPaused = false;
        updateTimerDisplays();
        updateIndicators();
    }

    private void updateTimerDisplays() {
        if (tvWhiteTimer != null) {
            tvWhiteTimer.setText(formatTime(whiteTimeLeft));
        }
        if (tvBlackTimer != null) {
            tvBlackTimer.setText(formatTime(blackTimeLeft));
        }
    }

    private void updateIndicators() {
        if (indicatorWhite != null) {
            indicatorWhite.setVisibility(isWhiteTurn ? View.VISIBLE : View.INVISIBLE);
        }
        if (indicatorBlack != null) {
            indicatorBlack.setVisibility(!isWhiteTurn ? View.VISIBLE : View.INVISIBLE);
        }
    }

    private String formatTime(long millis) {
        if (millis <= 0) return "0:00";

        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    // Геттеры для состояния
    public boolean isRunning() {
        return isRunning;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    public long getWhiteTimeLeft() {
        return whiteTimeLeft;
    }

    public long getBlackTimeLeft() {
        return blackTimeLeft;
    }
}