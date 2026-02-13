package com.katas.tennis;

import java.util.Map;

public class Model {
    public static final int WINNING_THRESHOLD = 4;

    public static final Map<Integer, String> TENNIS_POINTS = Map.of(
            0, "Love",
            1, "Fifteen",
            2, "Thirty",
            3, "Forty"
    );
}
