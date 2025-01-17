package bowling.domain.score;

import bowling.exception.CannotCalculateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    @DisplayName("Miss, Gutter의 경우 바로 값을 계산 할 수 있다")
    @Test
    void calculateScoreWhenMissOrGutterTest() {
        Score missScore = Score.of(2, ScoreState.ofNone());
        assertThat(missScore.calculateScore()).isEqualTo(2);
    }

    @DisplayName("Spare의 경우 다음 투구의 값도 더한다")
    @Test
    void calculateScoreWhenSpareTest() {
        Score spareScore = Score.of(10, ScoreState.ofSpare());
        spareScore.addAdditionalScore(9);
        assertThat(spareScore.calculateScore()).isEqualTo(19);
    }

    @DisplayName("Strike의 경우 다음 2 투구의 값도 더한다")
    @Test
    void calculateScoreWhenStrikeTest() {
        Score strikeScore = Score.of(10, ScoreState.ofStrike());
        strikeScore.addAdditionalScore(4);
        strikeScore.addAdditionalScore(5);
        assertThat(strikeScore.calculateScore()).isEqualTo(19);
    }

    @DisplayName("Spare, Strike는 바로 계산 할 수 없다")
    @Test
    void calculateScoreExceptionTest() {
        Score strikeScore = Score.of(10, ScoreState.ofStrike());
        Score spareScore = Score.of(10, ScoreState.ofSpare());
        assertThatThrownBy(() -> {
            strikeScore.calculateScore();
            spareScore.calculateScore();
        }).isInstanceOf(CannotCalculateException.class);
    }
}
