import java.io.File;
import java.io.IOException;

public class Parallel{

    public static void main(String[] args) throws IOException, FileNotEqualException {
        int threadNum = 8;
        int fileNum = 5;
        Thread[] threads = new Thread[threadNum];
        LinkedList linkedList = new LinkedList();
        Queue queue = new Queue();

        //create producer thread 0 and add to array
        Thread threadFile = new Thread(new ThreadFile(queue, fileNum));
        threads[0] = threadFile;

        //generate the next consumer threads
        for (int i = 1; i < threadNum; i++){
            Thread threadConsumer = new Thread(new ThreadConsumer(queue, linkedList));
            threads[i] = threadConsumer;
        }


        long start = System.nanoTime();
        for (int i = 0; i < threadNum; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threadNum; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.nanoTime();
        FileWriter.writeResultToFile(new File("rezultat_thread.txt"), linkedList);
        FileReader.areFilesEqual("rezultat_thread.txt", "rezultat_secvential.txt");
        System.out.println((double)(end - start) * 1E-6);
    }

}
