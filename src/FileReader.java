import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class FileNotEqualException extends Exception {
    public FileNotEqualException(String errorMessage) {
        super(errorMessage);
    }
}

public class FileReader{

    public Scanner createScanner(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        return new Scanner(file);
    }

    public void readToQueue(String fileName, Queue queue) throws FileNotFoundException, InterruptedException {

        Scanner scanner = createScanner(fileName);

        // with el with keep count which element we are reading
        int el = 0;
        int coefficient = 0;
        int exponent = 0;
        while (scanner.hasNext()) {
            String data = scanner.next();

            // reading coeff
            if (el == 0) {
                coefficient = Integer.parseInt(data);
            }
            // reading exponent
            else {
                exponent = Integer.parseInt(data);
                Node node = new Node(coefficient, exponent, null);
                queue.push(node);
                el = 0;
            }
            el++;
        }

    }

    public void readToList(String fileName, LinkedList linkedList) throws FileNotFoundException, InterruptedException {

        Scanner scanner = createScanner(fileName);

        // with el with keep count which element we are reading
        int el = 0;
        int coefficient = 0;
        int exponent = 0;
        while (scanner.hasNext()) {
            String data = scanner.next();
            // reading coeff
            if (el == 0) {
                coefficient = Integer.parseInt(data);
            }
            else {
                // reading exponent
                exponent = Integer.parseInt(data);
                linkedList.addNode(coefficient, exponent);
                el = 0;
            }
            el++;
        }

    }

    public static void areFilesEqual(String fileName1, String fileName2) throws FileNotFoundException, FileNotEqualException {

        Scanner scanner1 = new Scanner(new File(fileName1));
        Scanner scanner2 = new Scanner(new File(fileName2));
        if (scanner1.nextLine().equals(scanner2.nextLine())) {
            System.out.println("fisierele sunt egale");
            return ;
        }
        throw new FileNotEqualException("Files are not equal!");
    }



}
