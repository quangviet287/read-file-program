package factory;

import model.Company;
import model.File;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVFactory implements FileAbstractFactory {
    @Override
    public List<Company> getContentFile(Path file) {
        List<Company> companyList = null;
        try (InputStream in = Files.newInputStream(file)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            List<String> list = new ArrayList<>();
            while ((line = reader.readLine())!=null){
                list.add(line);
            }
            list.remove(0);
            for (String itemOfList : list){
                Company company = new Company();
                String[] items = itemOfList.split("\\,");
                company.setId(items[0]);
                company.setName(items[1]);
                company.setFoundationDate(items[2]);
                company.setCapital(Integer.parseInt(items[3]));
                company.setCountry(items[4]);
                if (items.length == 6){
                    company.setHeadquarterId(items[5]);
                }else{
                    company.setHeadquarterId(null);
                }
                companyList.add(company);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return companyList;
    }

    private List<?> getListElementsByCountry(Path file, final String country) {
        List<Company> list = this.getContentFile(file);
        return list.stream().filter(p->p.getCountry().equals(country)).collect(Collectors.toList());
    }

    private int getTotalCapitalByCountry(Path file, String country) {
        List<Company> list = this.getContentFile(file);
        return list.stream().filter(p->p.getCountry().equals(country)).mapToInt(Company::getCapital).sum();
    }
}
