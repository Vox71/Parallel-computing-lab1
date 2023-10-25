import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.Math.random;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int N = 99999999;
        List<Double> vector = new ArrayList<>();
        for(int i = 0; i < N; i++){
            vector.add(random());
        }
        NumberFormat formatter = new DecimalFormat("#0.00000000");

        ExecutorService sequentallyExecuter = Executors.newFixedThreadPool(1);
        long start = System.currentTimeMillis();
        Future<Integer> future1 = sequentallyExecuter.submit(new UnordoredSequentially(N, vector));
        System.out.println(future1.get());
        sequentallyExecuter.shutdown();
        long end = System.currentTimeMillis();
        float sequentiallyTime = (float) (end - start);
        System.out.println("Время выполнения программы: " + formatter.format((end - start) / 1000d) + " секунд");


        float acceleration_2thread = RunThreads.threads(2, N, vector, formatter, sequentiallyTime);
        float efficiency_2thread = acceleration_2thread/2;
        System.out.println("Эффективность алгоритма при 2 потоках: " + efficiency_2thread);
        float acceleration_3thread = RunThreads.threads(3, N, vector, formatter, sequentiallyTime);
        float efficiency_3thread = acceleration_3thread/3;
        System.out.println("Эффективность алгоритма при 3 потоках: " + efficiency_3thread);
        float acceleration_4thread = RunThreads.threads(4, N, vector, formatter, sequentiallyTime);
        float efficiency_4thread = acceleration_4thread/4;
        System.out.println("Эффективность алгоритма при 4 потоках: " + efficiency_4thread);

        float acceleration_10thread = RunThreads.threads(10, N, vector, formatter, sequentiallyTime);
        float efficiency_10thread = acceleration_10thread/10;
        System.out.println("Эффективность алгоритма при 10 потоках: " + efficiency_10thread);



        CreateChart.chart(1, acceleration_2thread, acceleration_3thread, acceleration_4thread, "Acceleration");
        CreateChart.chart(1, efficiency_2thread, efficiency_3thread, efficiency_4thread, "Efficiency");

    }
}