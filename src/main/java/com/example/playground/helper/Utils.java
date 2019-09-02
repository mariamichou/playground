package com.example.playground.helper;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public final class Utils {

    private Utils() {
    }

    public static <T, U extends Comparable<U>> List<T> toSorted(Collection<T> list, Function<T, U> comparingFunction) {
        return list.stream()
                .sorted(Comparator.comparing(comparingFunction))
                .collect(Collectors.toList());
    }

    public static boolean isNumeric(String strNum) {
        boolean isNumeric;

        try {
            double d = Double.parseDouble(strNum);
            log.debug("{} is numeric", d);
            isNumeric = true;
        } catch (NumberFormatException e) {
            isNumeric = false;
        }

        return isNumeric;
    }

    public static <T> boolean areEqual(Map<String, T> first, Map<String, T> second, Predicate<Map.Entry<String, T>> matcher) {
        return first.size() == second.size() &&
                first.entrySet().stream()
                        .allMatch(matcher);
    }
}
