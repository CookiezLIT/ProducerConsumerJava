import java.io.File;
import java.io.IOException;

public class GenerateFile{
    public static void main(String[] args) throws IOException {
        generate(10, 1000, 50);
    }

    private static void generate(int nrFiles, int gradeMax, int nrMon) throws IOException {
        String currentPath = new java.io.File(".").getCanonicalPath();
        //currentPath is before the src module
        for (int i = 0; i < nrFiles; ++i) {
            String filename = currentPath + "/src/data/file"+ + i;
            FileWriter fileWriter = new FileWriter(nrMon, 1, gradeMax);
            File file = fileWriter.createFile(filename);
            fileWriter.writeToFileRandomNumbers(file);
        }
    }
}
