package ru.academit.khrushchev.range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(4.2, 25.3);
        Range range2 = new Range(25.3, 30.1);

        // Range task first part
        System.out.println("Range beginning = " + range1.getFrom());
        System.out.println("Range ending = " + range1.getTo());
        System.out.println("Range length is " + range1.getRange());

        double number = 5.0;

        System.out.println("Number " + number + " is " + (range1.isInside(number) ? "inside" : "outside") + " the range");

        range1.setFrom(13.2);
        range1.setTo(24);

        System.out.println();
        System.out.println("Updated range beginning = " + range1.getFrom());
        System.out.println("Updated range ending = " + range1.getTo());
        System.out.println("Updated range length is = " + range1.getRange());

        System.out.println("Now number " + number + " is " + (range1.isInside(number) ? "inside" : "outside") + " the updated range");

        //Range task second part
        Range crossingRange = range1.getCrossingRange(range2);

        if (crossingRange == null) {
            System.out.println("Ranges are not crossed");
        } else {
            System.out.println("Crossing range beginning = " + crossingRange.getFrom());
            System.out.println("Crossing range ending = " + crossingRange.getTo());
        }

        Range range3 = new Range(1, 3);

        Range[] rangesUnion1 = range1.getRangesUnion(range3);
        Range[] rangesUnion2 = range3.getRangesUnion(range2);
        Range[] rangesUnion3 = range2.getRangesUnion(range1);

        printUnitedRanges(rangesUnion1);
        printUnitedRanges(rangesUnion2);
        printUnitedRanges(rangesUnion3);
    }

    public static void printUnitedRanges(Range[] rangesUnion) {
        for (Range range : rangesUnion) {
            System.out.print(range.getFrom() + " ");
            System.out.print(range.getTo());

            System.out.print("  ");
        }

        System.out.println();
    }
}
