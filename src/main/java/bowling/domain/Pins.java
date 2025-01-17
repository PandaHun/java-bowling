package bowling.domain;

import bowling.exception.PinsSizeException;

import java.util.ArrayList;
import java.util.List;

public class Pins {

    private static final int FIRST_TRY = 0;
    private static final int SECOND_TRY = 1;
    private static final int BONUS_TRY = 2;
    private static final int PIN_SIZE_BOUND = 3;
    private static final int STRIKE_COUNT = 10;
    private static final int CONVERT_CORRECTION = 1;
    private static final String PIN_SIZE_EXCEPTION_MESSAGE = String.format("최대 %d회의 시도만 가능합니다", PIN_SIZE_BOUND);

    private final List<Pin> pins;

    public Pins() {
        this.pins = new ArrayList<>();
    }

    public int totalCount() {
        return pins.stream()
                .mapToInt(Pin::count)
                .sum();
    }

    public int toSecondCount() {
        return pins.stream()
                .mapToInt(Pin::count)
                .limit(SECOND_TRY + 1)
                .sum();
    }

    public int firstTryCount() {
        return pins.get(FIRST_TRY).count();
    }

    public int secondTryCount() {
        return pins.get(SECOND_TRY).count();
    }

    public int bonusTryCount() {
        return pins.get(BONUS_TRY).count();
    }

    public void add(Pin pin) {
        validatePinSize();
        pins.add(pin);
    }

    private void validatePinSize() {
        if (pins.size() >= PIN_SIZE_BOUND) {
            throw new PinsSizeException(PIN_SIZE_EXCEPTION_MESSAGE);
        }
    }

    public int tryCount() {
        return pins.size();
    }

    public Pin firstPin() {
        return pins.get(FIRST_TRY);
    }

    public Pin bonusPin() {
        return pins.get(BONUS_TRY);
    }

    public boolean isStrike() {
        return totalCount() == STRIKE_COUNT;
    }
}
