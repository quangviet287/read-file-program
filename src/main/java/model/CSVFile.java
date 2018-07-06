package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVFile extends FileData {

    private Path src;

    public CSVFile(Path src) {
        this.src = src;
    }

    @Override
    public List<Company> getDataContent(Path file) throws IOException {
        List<Company> companyList = new ArrayList<>();
        InputStream in = Files.newInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        List<String> list = new ArrayList<>();
        while ((line = reader.readLine())!=null){
            if (line.equals("ID,Name,Foundation Date,Capital,Country,Headquarter ID")) {
                continue;
            }
            list.add(line);
        }
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
        return companyList;
    }

    @Override
    public void getTotalCapitalByCountry(Path file){
        System.out.println("Start get total capital by country: ");
        try {
            List<Company> companyList = this.getDataContent(file);
            int total = companyList.stream().filter(p->p.getCountry().equals("CH")).mapToInt(Company::getCapital).sum();
            System.out.println("Total capital of “CH” headquarters is: " + total);
            System.out.println("The list is sorted descending by capital “CH”: ");
            companyList.stream().filter(p->p.getCountry().equals("CH")).sorted(Comparator.comparing(Company::getCapital).reversed()).forEach(e-> System.out.println(e));
        } catch (IOException e) {
            System.err.println("File not found. "+e.getMessage());
        }
    }

    @Override
    public void getNameOfCompanyByCountry(Path file){
        System.out.println("Start get name of company by country: ");
        try {
            List<Company> companyList = this.getDataContent(file);
            List<Company> listNames = companyList.stream().filter(p->p.getCountry().equals("CH")).collect(Collectors.toList());
            System.out.println("List Name of company of “CH” headquarters is: " + listNames);
        } catch (IOException e) {
            System.err.println("File not found. "+e.getMessage());
        }
    }
}
