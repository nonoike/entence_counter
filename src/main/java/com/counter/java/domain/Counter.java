package com.counter.java.domain;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Counter {
    private final List<C> sortedCs;

    private Counter(List<C> sortedCs) {
        this.sortedCs = sortedCs;
    }

    public static Counter factory(List<String> sentence) {
        Map<String, Long> sentenceAndCount = sentence.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        List<C> cs = sentenceAndCount.entrySet()
                .stream()
                .map(e -> new C(e.getValue(), e.getKey()))
                .sorted(Comparator.comparing(C::getCount).reversed()
                        .thenComparing(Comparator.comparing(C::getSentence)))
                .collect(Collectors.toList());

        return new Counter(cs);
    }

    public List<String> toOutputFormatStrings() {
        return sortedCs.stream()
                .map(C::toOutputFormatString)
                .collect(Collectors.toList());
    }

    private static class C {
        private final long count;
        private final String sentence;

        private C(long count, String sentence) {
            this.count = count;
            this.sentence = sentence;
        }

        private long getCount() {
            return count;
        }

        private String getSentence() {
            return sentence;
        }

        private String toOutputFormatString() {
            return count + ":" + sentence;
        }
    }
}
