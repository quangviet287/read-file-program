package service;

import exception.TypeNotSupportedException;
import factory.FileFactory;
import model.FileData;

import java.nio.file.Path;

public class CSVFileServiceImpl {

    public CSVFileServiceImpl(Path file) throws TypeNotSupportedException {
            FileData fileData = FileFactory.getFile(file);
            fileData.getNameOfCompanyByCountry(file);
            fileData.getTotalCapitalByCountry(file);
    }
}
