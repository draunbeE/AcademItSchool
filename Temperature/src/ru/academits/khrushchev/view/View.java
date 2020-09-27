package ru.academits.khrushchev.view;

import ru.academits.khrushchev.model.ConversionScale;
import ru.academits.khrushchev.model.Model;
import ru.academits.khrushchev.model.ScaleToConvert;

import javax.swing.*;
import java.awt.*;

public class View {
    Model model = new Model();
    ScaleToConvert scaleToConvert;
    ConversionScale conversionScale;

    public void start() {
        SwingUtilities.invokeLater(() -> {
            // Main window
            JFrame mainWindow = new JFrame("Temperature convertor");
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = (int) (screenSize.width * 0.3);
            int height = (int) (screenSize.height * 0.25);
            mainWindow.setSize(width, height);
            mainWindow.setMinimumSize(new Dimension(width + 200, height));
            mainWindow.setResizable(false);
            mainWindow.setVisible(true);
            mainWindow.setLocationRelativeTo(null);
            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainWindow.add(mainPanel);

            //Temperature to convert scale selection panel
            JPanel scaleToConvertSelectionPanel = new JPanel();
            mainPanel.add(scaleToConvertSelectionPanel);

            JLabel temperatureToConvertScaleSelectionText = new JLabel("Select temperature scale to convert: ");
            scaleToConvertSelectionPanel.add(temperatureToConvertScaleSelectionText);

            JButton celsiusButton1 = new JButton("Celsius");

            scaleToConvertSelectionPanel.add(celsiusButton1);

            JButton kelvinButton1 = new JButton("Kelvin");
            scaleToConvertSelectionPanel.add(kelvinButton1);

            JButton fahrenheitButton1 = new JButton("Fahrenheit");
            scaleToConvertSelectionPanel.add(fahrenheitButton1);

            //Temperature converted scale selection panel
            JPanel resultScaleSelectionPanel = new JPanel();
            mainPanel.add(resultScaleSelectionPanel);

            JLabel temperatureResultScaleSelectionText = new JLabel("Select temperature result scale: ");
            resultScaleSelectionPanel.add(temperatureResultScaleSelectionText);

            JButton celsiusButton2 = new JButton("Celsius");
            resultScaleSelectionPanel.add(celsiusButton2);

            JButton kelvinButton2 = new JButton("Kelvin");
            resultScaleSelectionPanel.add(kelvinButton2);

            JButton fahrenheitButton2 = new JButton("Fahrenheit");
            resultScaleSelectionPanel.add(fahrenheitButton2);

            celsiusButton1.addActionListener(event -> {
                scaleToConvert = ScaleToConvert.CELSIUS;
                celsiusButton1.setText(model.buttonClickOccurred(celsiusButton1.getText()));
                kelvinButton1.setText("Kelvin");
                fahrenheitButton1.setText("Fahrenheit");
            });

            kelvinButton1.addActionListener(event -> {
                scaleToConvert = ScaleToConvert.KELVIN;
                celsiusButton1.setText("Celsius");
                kelvinButton1.setText(model.buttonClickOccurred(kelvinButton1.getText()));
                fahrenheitButton1.setText("Fahrenheit");
            });

            fahrenheitButton1.addActionListener(event -> {
                scaleToConvert = ScaleToConvert.FAHRENHEIT;
                celsiusButton1.setText("Celsius");
                kelvinButton1.setText("Kelvin");
                fahrenheitButton1.setText(model.buttonClickOccurred(fahrenheitButton1.getText()));
            });

            //Conversion type selection
            celsiusButton2.addActionListener(event -> {
                conversionScale = ConversionScale.CELSIUS;
                celsiusButton2.setText(model.buttonClickOccurred(celsiusButton2.getText()));
                kelvinButton2.setText("Kelvin");
                fahrenheitButton2.setText("Fahrenheit");
            });

            kelvinButton2.addActionListener(event -> {
                conversionScale = ConversionScale.KELVIN;
                celsiusButton2.setText("Celsius");
                kelvinButton2.setText(model.buttonClickOccurred(kelvinButton2.getText()));
                fahrenheitButton2.setText("Fahrenheit");
            });

            fahrenheitButton2.addActionListener(event -> {
                conversionScale = ConversionScale.FAHRENHEIT;
                celsiusButton2.setText("Celsius");
                kelvinButton2.setText("Kelvin");
                fahrenheitButton2.setText(model.buttonClickOccurred(fahrenheitButton2.getText()));
            });

            //Work panel
            JPanel conversionPanel = new JPanel();
            mainPanel.add(conversionPanel);

            JLabel infoTextForEntranceField = new JLabel("Type temperature here: ");
            conversionPanel.add(infoTextForEntranceField);

            JTextField temperatureEntranceField = new JTextField(10);
            conversionPanel.add(temperatureEntranceField);

            JButton convertButton = new JButton("Convert");
            conversionPanel.add(convertButton);

            //Conversion logic
            convertButton.addActionListener(event -> {
                String enteredText = temperatureEntranceField.getText();

                try {
                    double enteredNumber = model.convertEnteredTemperatureToDouble(enteredText);
                    String resultText = "Result is: " + model.convertTemperature(scaleToConvert, conversionScale, enteredNumber);

                    JOptionPane.showMessageDialog(null, resultText, "Result window", JOptionPane.INFORMATION_MESSAGE);
                } catch (UnsupportedOperationException unsupportedOperationException) {
                    JOptionPane.showMessageDialog(null, "Type a number.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(null, "Entered text must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException illegalArgumentException) {
                    JOptionPane.showMessageDialog(null, "Entered text cannot contain more than 1 comma.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (RuntimeException runtimeException) {
                    JOptionPane.showMessageDialog(null, "Both scales must be selected.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        });
    }
}
