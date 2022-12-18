import java.io.File;
import java.io.IOException;

public class Secvential extends FileReader {
    public static void main(String[] args) throws IOException, InterruptedException {
        int fileNum = 5;
        LinkedList linkedList = new LinkedList();
        long start = System.nanoTime();
        readFromFile(fileNum, linkedList);
        long end = System.nanoTime();
        FileWriter.writeResultToFile(new File("rezultat_secvential.txt"), linkedList);
        System.out.println((double)(end - start) * 1E-6);
    }

    private static void readFromFile(int nrFiles, LinkedList linkedList) throws IOException, InterruptedException {
        System.out.println("CURRENT PATH");
        String currentPath = new java.io.File(".").getCanonicalPath();
        System.out.println(currentPath);
        for (int i = 0; i < nrFiles; ++i) {
            String filename = currentPath + "/src/data/file" + i;
            FileReader fileReader = new FileReader();
            fileReader.readToList(filename, linkedList);
        }
    }
}
