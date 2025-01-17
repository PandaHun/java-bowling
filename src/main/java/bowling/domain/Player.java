package bowling.domain;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import bowling.exception.NameEmptyException;
import bowling.exception.NameFormatException;
import bowling.exception.NameLengthException;

import java.util.Objects;
import java.util.regex.Pattern;

public class Player {

    private static final int NAME_MAX_LENGTH = 3;
    private static final String NAME_LANGUAGE_PATTERN = "^[a-zA-Z]*$";
    private static final String NAME_LANGUAGE_EXCEPTION_MESSAGE = "이름은 영문만 허용합니다";
    private static final String NAME_LENGTH_EXCEPTION_MESSAGE = "이름은 3글자 이하 입니다";
    private static final String NAME_EMPTY_EXCEPTION_MESSAGE = "이름은 1글자 이상 입니다.";

    private final String name;
    private final Scores scores;

    private Player(final String name) {
        this.name = name;
        this.scores = Scores.init();
    }

    public static Player from(final String name) {
        validateNameFormat(name);
        validateNameLength(name);
        return new Player(name);
    }

    private static void validateNameFormat(String name) {
        if (!Pattern.matches(NAME_LANGUAGE_PATTERN, name)) {
            throw new NameFormatException(NAME_LANGUAGE_EXCEPTION_MESSAGE);
        }
    }

    private static void validateNameLength(String name) {
        if (name.length() > NAME_MAX_LENGTH) {
            throw new NameLengthException(NAME_LENGTH_EXCEPTION_MESSAGE);
        }
        if (name.trim().isEmpty()) {
            throw new NameEmptyException(NAME_EMPTY_EXCEPTION_MESSAGE);
        }
    }

    public String name() {
        return this.name;
    }

    public void addScore(Score score) {
        scores.addScore(score);
    }

    public Scores scores() {
        return scores;
    }

    public int scoreRound() {
        return scores.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
