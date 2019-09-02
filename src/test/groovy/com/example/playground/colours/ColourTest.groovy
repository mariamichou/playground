package com.example.playground.colours

import spock.lang.Specification
import spock.lang.Unroll

import static com.example.playground.colours.Colour.*
import static com.example.playground.helper.Utils.toSorted

class ColourTest extends Specification {

    @Unroll
    void "'#name' should return a supported rainbow colour"() {
        when:
        Colour colour = fromName(name)

        then:
        colour == expectedColour

        where:
        name     | expectedColour
        "blue"   | BLUE
        "BluE"   | BLUE
        "green"  | GREEN
        "purple" | null
        ""       | null
        null     | null
    }

    @Unroll
    void "'#order' should match a supported rainbow colour"() {
        when:
        Colour colour = fromOrder(order as Integer)

        then:
        colour == expectedColour

        where:
        order | expectedColour
        1     | ORANGE
        2     | YELLOW
        -1    | null
        null  | null
    }

    def "get all rainbow colours, sorted by order ASC"() {
        when:
        List<Colour> rainbowColours = getRainbowColours()

        then:
        rainbowColours == [BLUE, GREEN, INDIGO, RED, ORANGE, VIOLET, YELLOW]

        when:
        List<Colour> orderedColours = toSorted(rainbowColours, { Colour c -> c.getOrder() })

        then:
        orderedColours == [RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET]
    }

    @Unroll
    def "populate colour order map, ordering existing colours by order, then ordering the rest by name ASC and incrementing index"() {
        when:
        Map<String, Integer> colourOrderMap = populateColourOrder(colourList)

        then:
        colourOrderMap == expectedColourOrderedMap

        where:
        colourList                                                                                              | expectedColourOrderedMap
        ["red", "yellow", "green", "blue", "indigo", "violet", "orange", "pink", "black", "burgundy", "purple"] | ["red": 0, "orange": 1, "yellow": 2, "green": 3, "blue": 4, "indigo": 5, "violet": 6, "black": 7, "burgundy": 8, "pink": 9, "purple": 10]
        ["blue", "green", "purple", "pink", "black"]                                                            | ["green": 3, "blue": 4, "black": 7, "pink": 8, "purple": 9]
        ["purple", "pink", "black"]                                                                             | ["black": 7, "pink": 8, "purple": 9]
        ["pink", "black"]                                                                                       | ["black": 7, "pink": 8]
        ["black"]                                                                                               | ["black": 7]
    }
}
