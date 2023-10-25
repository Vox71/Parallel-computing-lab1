import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunThreads {
    public static float threads(int threads, int N, List<Double> vector, NumberFormat formatter, float sequentiallyTime) throws ExecutionException, InterruptedException {
        ExecutorService threadsExecutor = Executors.newFixedThreadPool(threads);
        long start = System.currentTimeMillis();
        List<Future<Integer>> threadsFutures = new ArrayList<>();
        int result = 0;
        for(int i = 0; i < threads; i++){
            threadsFutures.add(threadsExecutor.submit(new UnorderedThread(N, vector, i, threads)));
        }
        for(int i = 0; i < threads; i++) {
            result = result + threadsFutures.get(i).get();
        }
        System.out.println(result);
        threadsExecutor.shutdown();
        long end = System.currentTimeMillis();
        float parallelTime = (float) (end - start);
        System.out.println("Время выполнения многопоточной программы c " + threads + " потоками: " + formatter.format((end - start) / 1000d) + " секунд");
        float acceleration = sequentiallyTime / parallelTime;
        System.out.println("Ускорение алгоритма при " + threads + " потоках: " + acceleration);
        return acceleration;
    }
}
