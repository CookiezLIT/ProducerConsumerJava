import java.io.FileNotFoundException;
import java.io.IOException;

public class ThreadFile extends Thread{
    private final Queue queue;
    private final int nrFiles;

    public ThreadFile(Queue queue, int nrFiles) {
        this.queue = queue;
        this.nrFiles = nrFiles;
    }

    public void run() {
        try {
            String currentPath = new java.io.File(".").getCanonicalPath();
            for (int i = 0; i < nrFiles; ++i) {
                String filename = currentPath + "/src/data/file" + i;
                FileReader fileReader = new FileReader();
                fileReader.readToQueue(filename, queue);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            queue.done.set(true);
            System.out.println("file terminated");
            try {
                queue.push(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
