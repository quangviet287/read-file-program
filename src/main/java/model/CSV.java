package model;

import java.nio.file.Path;
import java.util.List;

public class CSV extends File {

    private Path src;

    private String fileName;

    private String fileType;

    private List<Company> fileContent;

    @Override
    public List<Company> getFileContent() {
        return this.fileContent;
    }
}
