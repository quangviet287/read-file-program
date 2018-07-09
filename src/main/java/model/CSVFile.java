package model;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVFile implements  FileData {

    final static Logger logger = Logger.getLogger(CSVFile.class);

    private String CSVFile;

    public CSVFile(String CSVFile) {
        this.CSVFile = CSVFile;
    }

    @Override
    public List<Company> getDataContent(Path file){
        List<Company> companyList = new ArrayList<>();
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            List<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (line.equals("ID,Name,Foundation Date,Capital,Country,Headquarter ID")) {
                    continue;
                }
                list.add(line);

                Company company = new Company();
                String[] items = line.split("\\,");
                company.setId(items[0]);
                company.setName(items[1]);
                company.setFoundationDate(items[2]);
                company.setCapital(Integer.parseInt(items[3]));
                company.setCountry(items[4]);
                if (items.length == 6) {
                    company.setHeadquarterId(items[5]);
                }
                companyList.add(company);
            }
        } catch (IOException e) {
            logger.error("Error when read data content", e);
        }
        return companyList;
    }
}
