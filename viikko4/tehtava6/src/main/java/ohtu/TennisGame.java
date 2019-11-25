package ohtu;

public class TennisGame {
    private static final int EARLY_MAX_SCORE = 3;
    private static final int LEAD_REQUIRED_FOR_WIN = 2;
    private static final int SCORE_AWARDED_PER_POINT = 1;
    private static final String SCORE_0 = "Love";
    private static final String SCORE_1 = "Fifteen";
    private static final String SCORE_2 = "Thirty";
    private static final String SCORE_3 = "Forty";
    private static final String SCORE_EQUAL = "All";
    private static final String SCORE_DEUCE = "Deuce";
    private static final String WIN_PREFIX = "Win for";
    private static final String ADVANTAGE_PREFIX = "Advantage";

    private final String player1Name;
    private final String player2Name;
    private int player1Score = 0;
    private int player2Score = 0;

    public TennisGame(String player1Name, String player2Name) {
        if (player1Name.equals(player2Name)) {
            throw new IllegalArgumentException("Players names cannot be equal!");
        }

        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (isPlayerOne(playerName)) {
            player1Score += SCORE_AWARDED_PER_POINT;
        } else if (isPlayerTwo(playerName)) {
            player2Score += SCORE_AWARDED_PER_POINT;
        } else {
            throw new IllegalArgumentException("Provided player name did not match any of the players!");
        }
    }

    private boolean isPlayerOne(String playerName) {
        return playerName.equals(player1Name);
    }

    private boolean isPlayerTwo(String playerName) {
        return playerName.equals(player2Name);
    }

    public String getScore() {
        return gameIsAtLateStage() ? getLateStageScore() : getEarlyStageScore();
    }

    private String getLateStageScore() {
        return scoresAreEqual()
                ? SCORE_DEUCE
                : String.format("%s %s",
                                getGameStatus(),
                                getLeadingPlayerName());
    }

    private String getEarlyStageScore() {
        return String.format("%s-%s",
                             scoreAsString(player1Score),
                             scoresAreEqual()
                                     ? SCORE_EQUAL
                                     : scoreAsString(player2Score));
    }

    private String getGameStatus() {
        int scoreLead = Math.abs(player1Score - player2Score);

        return scoreLead >= LEAD_REQUIRED_FOR_WIN
                ? WIN_PREFIX
                : ADVANTAGE_PREFIX;
    }

    private String getLeadingPlayerName() {
        assert player1Score != player2Score;
        return player1Score > player2Score
                ? player1Name
                : player2Name;
    }

    private boolean scoresAreEqual() {
        return player1Score == player2Score;
    }

    private boolean gameIsAtLateStage() {
        return player1Score > EARLY_MAX_SCORE || player2Score > EARLY_MAX_SCORE;
    }

    private String scoreAsString(int score) {
        assert score <= 3;
        switch (score) {
            case 0:
                return SCORE_0;
            case 1:
                return SCORE_1;
            case 2:
                return SCORE_2;
            case 3:
                return SCORE_3;
            default:
                throw new AssertionError("Score must be an integer in range from 0 to 3");
        }
    }
}