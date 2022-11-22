package com.owem.experiment04;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CalculatorController {
    @FXML
    private Label numberText;
    double perNumber = 0;
    String operation = "";

    @FXML
    protected void OnBackspaceButton() {
        String str = numberText.getText();
        if (str.charAt(0) == '=') {
            str = "0";
        } else {
            str = str.substring(0, str.length() - 1);
            if (str.length() == 0) {
                str = "0";
            } else if (str.charAt(str.length() - 1) == '.') {
                str = str.substring(0, str.length() - 1);
            }
        }
        numberText.setText(str);
    }

    @FXML
    protected void OnCEButton() {
        numberText.setText("0");
        perNumber = 0;
    }

    @FXML
    protected void OnCButton() {
        numberText.setText("0");
    }

    @FXML
    protected void OnSevenButton() {
        String str = numberText.getText();
        if (str.equals("0") || (!str.equals("") && str.charAt(0) == '=')) {
            str = "";
        }
        str = str + "7";
        numberText.setText(str);
    }

    @FXML
    protected void OnEightButton() {
        String str = numberText.getText();
        if (str.equals("0") || (!str.equals("") && str.charAt(0) == '=')) {
            str = "";
        }
        str = str + "8";
        numberText.setText(str);
    }

    @FXML
    protected void OnNineButton() {
        String str = numberText.getText();
        if (str.equals("0") || (!str.equals("") && str.charAt(0) == '=')) {
            str = "";
        }
        str = str + "9";
        numberText.setText(str);
    }

    @FXML
    protected void OnDivideButton() {
        perNumber = Double.parseDouble(numberText.getText());
        operation = "/";
        numberText.setText("");
    }

    @FXML
    protected void OnSqrtButton() {
        double number = Double.parseDouble(numberText.getText());
        numberText.setText("=" + Math.sqrt(number));
    }

    @FXML
    protected void OnFourButton() {
        String str = numberText.getText();
        if (str.equals("0") || (!str.equals("") && str.charAt(0) == '=')) {
            str = "";
        }
        str = str + "4";
        numberText.setText(str);
    }

    @FXML
    protected void OnFiveButton() {
        String str = numberText.getText();
        if (str.equals("0") || (!str.equals("") && str.charAt(0) == '=')) {
            str = "";
        }
        str = str + "5";
        numberText.setText(str);
    }

    @FXML
    protected void OnSixButton() {
        String str = numberText.getText();
        if (str.equals("0") || (!str.equals("") && str.charAt(0) == '=')) {
            str = "";
        }
        str = str + "6";
        numberText.setText(str);
    }

    @FXML
    protected void OnMultiplicationButton() {
        perNumber = Double.parseDouble(numberText.getText());
        operation = "*";
        numberText.setText("");
    }

    @FXML
    protected void OnPercentButton() {
        double number = Double.parseDouble(numberText.getText());
        if (number != 0) {
            numberText.setText(String.valueOf(number / 100));
        }
    }

    @FXML
    protected void OnThreeButton() {
        String str = numberText.getText();
        if (str.equals("0") || (!str.equals("") && str.charAt(0) == '=')) {
            str = "";
        }
        str = str + "3";
        numberText.setText(str);
    }

    @FXML
    protected void OnTwoButton() {
        String str = numberText.getText();
        if (str.equals("0") || (!str.equals("") && str.charAt(0) == '=')) {
            str = "";
        }
        str = str + "2";
        numberText.setText(str);
    }

    @FXML
    protected void OnOneButton() {
        String str = numberText.getText();
        if (str.equals("0") || (!str.equals("") && str.charAt(0) == '=')) {
            str = "";
        }
        str = str + "1";
        numberText.setText(str);
    }

    @FXML
    protected void OnSubtractButton() {
        perNumber = Double.parseDouble(numberText.getText());
        operation = "-";
        numberText.setText("");
    }

    @FXML
    protected void OnOneOfButton() {
        if (numberText.getText().charAt(0) != '=') {
            double number = Double.parseDouble(numberText.getText());
            if (number != 0) {
                numberText.setText("=" + 1 / number);
            }
        }
    }

    @FXML
    protected void OnZeroButton() {
        String str = numberText.getText();
        if (str.equals("0") || (!str.equals("") && str.charAt(0) == '=')) {
            str = "";
        }
        str = str + "0";
        numberText.setText(str);
    }

    @FXML
    protected void OnPositiveOrNegativeButton() {
        String str = numberText.getText();
        if (!str.equals("0")) {
            if (numberText.getText().contains("-")) {
                numberText.setText(str.substring(1));
            } else {
                numberText.setText("-" + str);
            }
        }
    }

    @FXML
    protected void OnPointButton() {
        if (!numberText.getText().contains(".")) {
            numberText.setText(numberText.getText() + ".");
        }
    }

    @FXML
    protected void OnPlusButton() {
        perNumber = Double.parseDouble(numberText.getText());
        operation = "+";
        numberText.setText("");
    }

    @FXML
    protected void OnEqualButton() {
        double number = Double.parseDouble(numberText.getText());
        switch (operation) {
            case "+" -> number = perNumber + number;
            case "-" -> number = perNumber - number;
            case "*" -> number = perNumber * number;
            case "/" -> {
                if (number != 0) {
                    number = perNumber / number;
                }
            }
        }
        numberText.setText("=" + number);
    }
}