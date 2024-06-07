package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvReader {
    public static Object[][] readCsvFile(String fileName) throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            List<String[]> csvData = csvReader.readAll();
            // Direct conversion to Object[][] type, leveraging the List size for the first dimension.
            Object[][] csvDataObject = new Object[csvData.size()][];
            for (int i = 0; i < csvData.size(); i++) {
                // Directly assign each String[] to an index in Object[][].
                // This approach automatically accommodates the length of each String[].
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;
        }
    }
}