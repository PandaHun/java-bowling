package bowling.domain.score;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreState;
import bowling.domain.score.Scores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoresTest {

    @DisplayName("점수들을 초기화한다")
    @Test
    void scoresInitTest() {
        Scores scores = Scores.init();
        assertThat(scores.size()).isEqualTo(0);
    }

    @DisplayName("점수는 이전 점수와 현재 라운드 점수의 합이다")
    @Test
    void scoresAddTest() {
        Scores scores = Scores.init();
        scores.addScore(Score.of(9, ScoreState.ofNone()));
        scores.addScore(Score.of(9, ScoreState.ofNone()));
        assertThat(scores.roundScore(2).calculateScore()).isEqualTo(18);
    }
}
