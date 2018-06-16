package statement.record.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Validator {
    public static boolean format(String amount) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);
        try {
            numberFormat.parse(amount);
            return true;
        } catch (ParseException e) {
            return false;
        }

    }
}
