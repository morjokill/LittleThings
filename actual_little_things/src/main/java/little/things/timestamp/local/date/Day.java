package little.things.timestamp.local.date;

import com.google.common.collect.Sets;

import java.time.LocalDate;
import java.util.Set;

public enum Day {
    TODAY(Sets.newHashSet("сегодня", "today")) {
        @Override
        LocalDate getDate() {
            return LocalDate.now();
        }
    },
    TOMORROW(Sets.newHashSet("завтра", "tomorrow")) {
        @Override
        LocalDate getDate() {
            return LocalDate.now().plusDays(1);
        }
    },
    AFTER_TOMORROW(Sets.newHashSet("послезавтра")) {
        @Override
        LocalDate getDate() {
            return LocalDate.now().plusDays(2);
        }
    };

    private Set<String> values;

    Day(Set<String> values) {
        this.values = values;
    }

    abstract LocalDate getDate();

    public static Day getDay(String dayLine) {
        dayLine = dayLine.toLowerCase();
        Day[] values = values();

        for (Day value : values) {
            if (value.values.contains(dayLine)) {
                return value;
            }
        }
        return null;
    }
}
