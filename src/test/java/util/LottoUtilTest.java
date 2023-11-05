package util;

import java.util.Arrays;
import java.util.List;

import VO.UserLottoVO;
import domain.Lotto;
import domain.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static util.LottoUtil.*;

public class LottoUtilTest {

    @DisplayName("매입 금액에 따른 로또수 반환")
    @Test
    void countByPayment() {
        final int pay = 5000;
        assertThat(countLotto(pay)).isEqualTo(5);
    }

    @DisplayName("사용자의 로또와 당첨번호를 비교해 등수로 분류한 배열로 반환")
    @Test
    void countWinLottoByRank() {
        final WinningLotto winningLotto = WinningLotto.getInstance();
        final UserLottoVO userLottoVO = new UserLottoVO();
        winningLotto.setNumbers(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        winningLotto.setBonusNumber(7);
        userLottoVO.setLottoList(List.of(
                new Lotto(List.of(1, 2, 3, 7, 8 ,9)),
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7))));

        assertThat(Arrays.toString(countWinLotto(winningLotto, userLottoVO)))
                .isEqualTo("[0, 0, 0, 1, 0, 0, 1, 1]");
    }
}
