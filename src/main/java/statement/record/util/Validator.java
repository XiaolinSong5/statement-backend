package statement.record.util;

import javax.validation.constraints.NotNull;
import java.text.NumberFormat;
import java.util.Locale;

public class Validator {
    public static boolean valdateFormat(@NotNull String amount) {
        double value;
        try {
            value = Double.parseDouble(amount);

        } catch (NumberFormatException e) {
            return false;
        }
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("nl", "NL"));
        return formatter.format(value) != null;
    }
}
