package statement.record.util;

import statement.record.Record;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader {
    public static void main(String argv[]) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(
                "/Users/xiaolinsong/dev/statementbackend/src/main/resources/records.csv"));
        // read file line by line
        String line = null;
        Scanner scanner = null;
        int index = 0;
        List<Record> empList = new ArrayList<Record>();
        try {
            while ((line = reader.readLine()) != null) {
                Record emp = new Record();
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    String data = scanner.next();
                    if (index == 0)
                        emp.setReference(Long.valueOf(data));
                    else if (index == 1)
                        emp.setAccountNumber(data);
                    else if (index == 2)
                        emp.setDescription(data);
                    else if (index == 3)
                        emp.setStartBalance(data);
                    else if (index == 4)
                        emp.setMutation(data);
                    else if (index == 5)
                        emp.setEndBalance(data);
                    else
                        System.out.println("invalid data::" + data);
                    index++;
                }
                index = 0;
                empList.add(emp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //close reader
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(empList);

    }


}

