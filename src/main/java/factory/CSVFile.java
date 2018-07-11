package factory;

import model.Company;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVFile implements  FileData {

    private static final int ID_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int FOUNDATION_DATE_INDEX = 2;
    private static final int CAPITAL_INDEX = 3;
    private static final int COUNTRY_INDEX = 4;
    private static final int HEADQUARTER_ID_INDEX = 5;

    private static final String TITLE_LINE_ID = "ID";
    private static final String TITLE_LINE_NAME = "Name";
    private static final String TITLE_LINE_FOUNDATION_DATE = "Foundation Date";
    private static final String TITLE_LINE_CAPITAL = "Capital";
    private static final String TITLE_LINE_COUNTRY = "Country";
    private static final String TITLE_LINE_HEADQUARTER_ID = "Headquarter ID";

    private static final Logger LOGGER = Logger.getLogger(CSVFile.class);

    private String csvFile;

    public CSVFile(String csvFile) {
        this.csvFile = csvFile;
    }

    @Override
    public List<Company> getDataContent(){
        Path file = Paths.get(csvFile);
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            List<Company> companyList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {

                Company company = new Company();
                String[] items = Arrays.stream(line.split(",")).map(String::trim).toArray(String[]::new);
                if (isTitleLine(items)){
                    continue;
                }
                company.setId(items[ID_INDEX]);
                company.setName(items[NAME_INDEX]);
                company.setFoundationDate(items[FOUNDATION_DATE_INDEX]);
                company.setCapital(Integer.parseInt(items[CAPITAL_INDEX]));
                company.setCountry(items[COUNTRY_INDEX]);
                if (hasHeadQuarter(items)) {
                    company.setHeadquarterId(items[HEADQUARTER_ID_INDEX]);
                }
                companyList.add(company);
            }
            return companyList;
        } catch (IOException e) {
            LOGGER.error("Error when read data content", e);
        }

        return new ArrayList<>();
    }

    private boolean hasHeadQuarter(String[] data) {
        return data!= null && (data.length-1) >= HEADQUARTER_ID_INDEX;
    }

    private boolean isTitleLine(String[] items){
        return TITLE_LINE_ID.equals(items[ID_INDEX])
                && TITLE_LINE_NAME.equals(items[NAME_INDEX])
                && TITLE_LINE_FOUNDATION_DATE.equals(items[FOUNDATION_DATE_INDEX])
                && TITLE_LINE_CAPITAL.equals(items[CAPITAL_INDEX])
                && TITLE_LINE_COUNTRY.equals(items[COUNTRY_INDEX])
                && TITLE_LINE_HEADQUARTER_ID.equals(items[HEADQUARTER_ID_INDEX]);
    }
}
