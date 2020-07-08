package ru.academit.khrushchev.range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(4.2, 25.3);
        Range range2 = new Range(25.3, 30.1);

        // Range task first part
        System.out.println("The first range beginning = " + range1.getFrom());
        System.out.println("The first range ending = " + range1.getTo());
        System.out.println("The first range length is " + range1.getRange());

        double number = 5.0;

        System.out.println("Number " + number + " is " + (range1.isInside(number) ? "inside" : "outside") + " the first range");

        range1.setFrom(13.2);
        range1.setTo(32);

        System.out.println();
        System.out.println("The first updated range beginning = " + range1.getFrom());
        System.out.println("The first updated range ending = " + range1.getTo());
        System.out.println("The first updated range length is = " + range1.getRange());

        System.out.println("Now number " + number + " is " + (range1.isInside(number) ? "inside" : "outside") + " the first updated range");
        System.out.println();

        //Range task second part
        Range crossingRange = range2.getCrossingRange(range1);

        if (crossingRange == null) {
            System.out.println("Ranges are not crossed");
        } else {
            System.out.println("Crossing range beginning = " + crossingRange.getFrom());
            System.out.println("Crossing range ending = " + crossingRange.getTo());
        }

        System.out.println();

        Range range3 = new Range(1, 3);

        Range[] rangesUnion1 = range1.getRangesUnion(range3);
        Range[] rangesUnion2 = range3.getRangesUnion(range2);
        Range[] rangesUnion3 = range2.getRangesUnion(range1);

        System.out.println("United ranges: ");

        System.out.println("First range united with the third");
        printRangesArray(rangesUnion1);
        System.out.println();

        System.out.println("Third range united with the second");
        printRangesArray(rangesUnion2);
        System.out.println();

        System.out.println("Second range united with the first");
        printRangesArray(rangesUnion3);
        System.out.println();

        Range[] subtractedRanges1 = range1.subtractRanges(range2);
        Range[] subtractedRanges2 = range2.subtractRanges(range1);

        Range[] subtractedRanges3 = range1.subtractRanges(range3);
        Range[] subtractedRanges4 = range3.subtractRanges(range1);

        System.out.println("Subtracted ranges: ");
        System.out.println("The second range subtracted from the first: ");
        printRangesArray(subtractedRanges1);
        System.out.println();

        System.out.println("The first range subtracted from the second: ");
        printRangesArray(subtractedRanges2);
        System.out.println();

        System.out.println("The third range subtracted from the first: ");
        printRangesArray(subtractedRanges3);
        System.out.println();

        System.out.println("The first range subtracted from the third: ");
        printRangesArray(subtractedRanges4);
        System.out.println();
    }

    public static void printRangesArray(Range[] rangesUnion) {
        if (rangesUnion.length == 0) {
            System.out.println("There is no objects in array.");

            return;
        }

        System.out.println("The array of range/-s: ");

        for (Range range : rangesUnion) {
            System.out.print("Beginning " + range.getFrom() + " ");
            System.out.print("Ending " + range.getTo());

            System.out.print("  ");
        }

        System.out.println();
    }
}
