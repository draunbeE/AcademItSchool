package ru.academits.khrushchev.model;

public class Model {
    final private static double kelvinCoefficient = 273.15;
    final private static double fahrenheitCoefficient = 5 / 9.;

    public double convertTemperature(ScaleToConvert stc, ConversionScale cs, double enteredNumber) {
        if (stc == ScaleToConvert.CELSIUS) {
            if (cs == ConversionScale.CELSIUS) {
                return enteredNumber;
            }

            if (cs == ConversionScale.FAHRENHEIT) {
                return enteredNumber * fahrenheitCoefficient + 32;
            }

            if (cs == ConversionScale.KELVIN) {
                return enteredNumber + kelvinCoefficient;
            }
        }

        if (stc == ScaleToConvert.FAHRENHEIT) {
            if (cs == ConversionScale.CELSIUS) {
                return (enteredNumber - 32) * fahrenheitCoefficient;
            }

            if (cs == ConversionScale.FAHRENHEIT) {
                return enteredNumber;
            }

            if (cs == ConversionScale.KELVIN) {
                return (enteredNumber - 32) * fahrenheitCoefficient + 273.15;
            }
        }

        if (stc == ScaleToConvert.KELVIN) {
            if (cs == ConversionScale.CELSIUS) {
                return enteredNumber - kelvinCoefficient;
            }

            if (cs == ConversionScale.FAHRENHEIT) {
                return (enteredNumber - kelvinCoefficient) * Math.pow(fahrenheitCoefficient, -1) + 32;
            }

            if (cs == ConversionScale.KELVIN) {
                return enteredNumber;
            }
        }

        throw new RuntimeException("No temperature scales selected");
    }

    public String buttonClickOccurred(String text) {
        if (text.contains("(selected)")) {
            return text;
        }

        return text + " (selected)";
    }

    public double convertEnteredTemperatureToDouble(String enteredText) {
        if (enteredText.length() == 0) {
            throw new UnsupportedOperationException("Text length is 0");
        }

        int commaIndex = enteredText.indexOf(",");

        if (commaIndex != -1) {
            commaIndex++;
            commaIndex = enteredText.indexOf(",", commaIndex);

            if (commaIndex != -1) {
                throw new IllegalArgumentException("More than 1 comma in the entered text");
            }

            enteredText = enteredText.replace(",", ".");
        }

        return Double.parseDouble(enteredText);

    }
}
