package service;

import exception.TypeNotSupportedException;
import factory.FileFactory;
import model.Company;
import model.FileData;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CSVFileServiceImpl {


    public CSVFileServiceImpl(Path file) throws TypeNotSupportedException {
            FileData fileData = FileFactory.getFile(file);
//            fileData.getNameOfCompanyByCountry(file);
//            fileData.getTotalCapitalByCountry(file);
    }

    /*public void getTotalCapitalByCountry(List<Company> companyList){
        System.out.println("Start get total capital by country: ");
        try {
            List<Company> companyList = this.getDataContent(file);
            int total = companyList.stream().filter("CH".equals(p->p.getCountry()))).mapToInt(Company::getCapital).sum();
            System.out.println("Total capital of “CH” headquarters is: " + total);
            System.out.println("The list is sorted descending by capital “CH”: ");
            companyList.stream().filter(p->p.getCountry().equals("CH")).sorted(Comparator.comparing(Company::getCapital).reversed()).forEach(e-> System.out.println(e));
        } catch (IOException e) {
            System.err.println("File not found. "+e.getMessage());
        }
    }


    public void getNameOfCompanyByCountry(List<Company> companyList){
//        System.out.println("Start get name of company by country: ");
        try {
            List<Company> companyList = this.getDataContent(file);
            List<Company> listNames = companyList.stream().filter(p->p.getCountry().equals("CH")).collect(Collectors.toList());
//            System.out.println("List Name of company of “CH” headquarters is: " + listNames);
        } catch (IOException e) {
//            System.err.println("File not found. "+e.getMessage());
        }
    }*/
}
