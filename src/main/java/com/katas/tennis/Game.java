package com.katas.tennis;

import static com.katas.tennis.Model.TENNIS_POINTS;
import static com.katas.tennis.Model.WINNING_THRESHOLD;

public class Game implements TennisGame {
    private final Player player1;
    private final Player player2;

    public Game(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
    }

    @Override
    public void wonPoint(String playerName) {
        if (player1.getName().equals(playerName)) {
            player1.addPoint();
        } else {
            player2.addPoint();
        }
    }

    @Override
    public String getScore() {
        if (playerAboveWinningThreshold()) {
            if (isGameOver()) {
                return "Win for " + getPlayerNameForScore();
            }

            if (isAdvantage()) {
                return "Advantage " + getPlayerNameForScore();
            }
        }

        if (isEqualScore()) {
            if (playerCanWinNext()) {
                return "Deuce";
            }
            return TENNIS_POINTS.get(player1.getPoints()) + "-All";
        }

        return TENNIS_POINTS.get(player1.getPoints()) + "-" + TENNIS_POINTS.get(player2.getPoints());
    }

    private boolean isEqualScore() {
        return player1.getPoints() == player2.getPoints();
    }

    private int calculateDifference() {
        return Math.abs(player1.getPoints() - player2.getPoints());
    }

    private boolean playerCanWinNext() {
        return player1.getPoints() >= 3 || player2.getPoints() >= 3;
    }

    private boolean playerAboveWinningThreshold() {
        return player1.getPoints() >= WINNING_THRESHOLD || player2.getPoints() >= WINNING_THRESHOLD;
    }

    private boolean isAdvantage() {
        return playerAboveWinningThreshold() && calculateDifference() == 1;
    }

    private boolean isGameOver() {
        return playerAboveWinningThreshold() && calculateDifference() >= 2;
    }

    private String getPlayerNameForScore() {
        return player1.getPoints() > player2.getPoints() ? player1.getName() : player2.getName();
    }
}
