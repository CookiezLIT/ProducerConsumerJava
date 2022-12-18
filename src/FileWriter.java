import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class FileWriter {
    private final int size, min, max;

    public FileWriter(int size, int min, int max) {
        this.size = size;
        this.min = min;
        this.max = max;
    }

    public void writeToFileRandomNumbers(File file) throws FileNotFoundException {
        Random random = new Random();
        PrintWriter writer = new PrintWriter(file);
        for (int i = 0; i < size; ++i) {
            int randomNumber = random.nextInt(max + 1 - min) + min;
            int randomNumber2 = random.nextInt(max + 1 - min) + min;
            writer.write(randomNumber + " " + randomNumber2 + "\n");
        }
        writer.close();
    }

    public static void writeResultToFile(File file, LinkedList linkedList) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file);
        writer.write(String.valueOf(linkedList));
        writer.close();
    }

    public File createFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.createNewFile()) {
            // if file already exists, we clear it's content
            clearFIle(file);
        }
        return file;
    }

    public void clearFIle(File file) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file);
        writer.write("");
        writer.close();
    }
}
