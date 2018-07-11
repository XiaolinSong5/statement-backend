package nl.rabo.statement.record;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class Greeting {
 private String someDataForTheFrontend;

    public String getSomeDataForTheFrontend() {
        return someDataForTheFrontend;
    }

    public void setSomeDataForTheFrontend(String someDataForTheFrontend) {
        this.someDataForTheFrontend = someDataForTheFrontend;
    }
}
