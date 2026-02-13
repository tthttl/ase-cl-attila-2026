package com.katas.tennis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("Game")
class GameTest {

    private void checkScore(TennisGame game, int player1Score, int player2Score, String expectedScore) {
        int highestScore = Math.max(player1Score, player2Score);

        for (int i = 0; i < highestScore; i++) {
            if (i < player1Score) {
                game.wonPoint("player1");
            }
            if (i < player2Score) {
                game.wonPoint("player2");
            }
        }

        assertThat(game.getScore()).isEqualTo(expectedScore);
    }

    @ParameterizedTest(name = "Game: scores {0}:{1} as {2}")
    @MethodSource("getAllScores")
    @DisplayName("All Score Combinations")
    void game(int player1Score, int player2Score, String expectedScore) {
        TennisGame game = new Game("player1", "player2");
        checkScore(game, player1Score, player2Score, expectedScore);
    }

    static Stream<Arguments> getAllScores() {
        return Stream.of(
                // Early game scores
                arguments(0, 0, "Love-All"),
                arguments(1, 0, "Fifteen-Love"),
                arguments(2, 0, "Thirty-Love"),
                arguments(3, 0, "Forty-Love"),
                arguments(4, 0, "Win for player1"),

                arguments(0, 1, "Love-Fifteen"),
                arguments(1, 1, "Fifteen-All"),
                arguments(2, 1, "Thirty-Fifteen"),
                arguments(3, 1, "Forty-Fifteen"),
                arguments(4, 1, "Win for player1"),

                arguments(0, 2, "Love-Thirty"),
                arguments(1, 2, "Fifteen-Thirty"),
                arguments(2, 2, "Thirty-All"),
                arguments(3, 2, "Forty-Thirty"),
                arguments(4, 2, "Win for player1"),

                arguments(0, 3, "Love-Forty"),
                arguments(1, 3, "Fifteen-Forty"),
                arguments(2, 3, "Thirty-Forty"),
                arguments(3, 3, "Deuce"),
                arguments(4, 3, "Advantage player1"),

                arguments(0, 4, "Win for player2"),
                arguments(1, 4, "Win for player2"),
                arguments(2, 4, "Win for player2"),
                arguments(3, 4, "Advantage player2"),
                arguments(4, 4, "Deuce"),

                // Extended deuce scenarios
                arguments(5, 4, "Advantage player1"),
                arguments(6, 4, "Win for player1"),
                arguments(4, 5, "Advantage player2"),
                arguments(5, 5, "Deuce"),
                arguments(6, 5, "Advantage player1"),
                arguments(7, 5, "Win for player1"),

                arguments(4, 6, "Win for player2"),
                arguments(5, 6, "Advantage player2"),
                arguments(6, 6, "Deuce"),
                arguments(7, 6, "Advantage player1"),
                arguments(8, 6, "Win for player1"),

                arguments(6, 7, "Advantage player2"),
                arguments(7, 7, "Deuce"),
                arguments(8, 7, "Advantage player1"),
                arguments(9, 7, "Win for player1"),

                arguments(7, 8, "Advantage player2"),
                arguments(8, 8, "Deuce"),
                arguments(9, 8, "Advantage player1"),
                arguments(10, 8, "Win for player1")
        );
    }

}