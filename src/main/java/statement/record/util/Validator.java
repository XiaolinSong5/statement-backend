package statement.record.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static boolean validateCurrency (String endbalance) {
        String money="23.234,00";
        Pattern p=Pattern.compile("^(?:0|[1-9]\\d{0,2}(?:\\.\\d{3})*).\\d{2}$");
        Matcher m=p.matcher(endbalance);
        if (m.matches()) {
            return true;
        }
        else {
            return false;
        }
    }
}
