package ru.academit.khrushchev.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return this.from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return this.to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getRange() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getCrossingRange(Range range) {
        return this.from < range.to && this.to > range.to || range.from < this.to && range.to > this.to
                ? new Range(Math.max(range.from, this.from), Math.min(range.to, this.to)) : null;
    }

    public Range[] getRangesUnion(Range range) {
        if (this.to < range.from || this.from > range.to) {
            return new Range[]{range, new Range(this.from, this.to)};
        }

        return new Range[]{new Range((Math.min(this.from, range.from)), (Math.max(this.to, range.to)))};
    }

    public Range[] subtractRanges(Range range) {
        if(this.from == range.from && this.to == range.to) {
            return null;
        }

    return null;    }
}
