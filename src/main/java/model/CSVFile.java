package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVFile extends FileData {

    private Path src;

    public CSVFile(Path src) {
        this.src = src;
    }

    @Override
    public List<Company> getDataContent(Path file) {
        List<Company> companyList = new ArrayList<>();
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

    @Override
    public void getTotalCapitalByCountry(Path file){
        List<Company> companyList = this.getDataContent(file);
        int total = companyList.stream().filter(p->p.getCountry().equals("CH")).mapToInt(Company::getCapital).sum();
        System.out.println("Total capital of “CH” headquarters is: " + total);
    }

    @Override
    public void getNameOfCompanyByCountry(Path file){
        List<Company> companyList = this.getDataContent(file);
        List<Company> listNames = companyList.stream().filter(p->p.getCountry().equals("CH")).collect(Collectors.toList());
        System.out.println("List Name of company of “CH” headquarters is: " + listNames);
    }

    @Override
    public String toString() {
        return "CSVFile{" +
                "src=" + src +
                '}';
    }
}
