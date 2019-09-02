package com.example.playground.colours;

import com.example.playground.helper.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Getter
@AllArgsConstructor
public enum Colour {

    BLUE("blue", 4),
    GREEN("green", 3),
    INDIGO("indigo", 5),
    RED("red", 0),
    ORANGE("orange", 1),
    VIOLET("violet", 6),
    YELLOW("yellow", 2);

    private String name;
    private int order;

    private static final Map<String, Colour> colourMap = Collections.unmodifiableMap(initialiseColourMap());
    private static final Map<Integer, Colour> orderMap = Collections.unmodifiableMap(initialiseOrderMap());


    private static Map<String, Colour> initialiseColourMap() {
        return Arrays.stream(Colour.values())
                .map(value -> new AbstractMap.SimpleEntry<>(value.name, value))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    private static Map<Integer, Colour> initialiseOrderMap() {
        return Arrays.stream(Colour.values())
                .map(value -> new AbstractMap.SimpleEntry<>(value.order, value))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    public static Colour fromName(String name) {
        if (name != null) {
            return Optional.ofNullable(colourMap.get(name.toLowerCase()))
                    .orElseGet(() -> {
                        log.warn("{} is not a supported rainbow colour", name);
                        return null;
                    });
        }
        log.debug("Name should not be null!");
        return null;
    }

    public static Colour fromOrder(Integer order) {
        if (order != null && Utils.isNumeric(order.toString())) {
            return Optional.ofNullable(orderMap.get(order))
                    .orElseGet(() -> {
                        log.warn("{} does not match a supported colour in rainbow", order);
                        return null;
                    });
        }
        log.error("Order has to be a number!");
        return null;
    }

    public static List<Colour> getRainbowColours() {
        return Arrays.asList(values());
    }

    public static Map<String, Integer> populateColourOrder(List<String> colourList) {
        Map<String, Integer> map = new HashMap<>();
        AtomicInteger size = new AtomicInteger(values().length);

        Utils.toSorted(colourList, String::intern).forEach(colourName -> {
                    Colour colour = Colour.fromName(colourName);
                    if (colour != null) {
                        map.put(colour.getName(), colour.getOrder());
                    } else {
                        map.put(colourName.toLowerCase(), size.getAndIncrement());
                    }
                }
        );

        return map;
    }
}