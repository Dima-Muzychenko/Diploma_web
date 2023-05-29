package Check;

public class InputDataCheck {

    // Helper method to check if a string value is a number within the specified range
    public boolean isValidNumber(String value, double minValue, double maxValue) {
        try {
            double number = Double.parseDouble(value);
            return number >= minValue && number <= maxValue;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
