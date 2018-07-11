package factory;

import model.Company;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileData {

    List<Company> getDataContent() throws IOException;

}
