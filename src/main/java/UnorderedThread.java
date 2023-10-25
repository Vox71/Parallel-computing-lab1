import java.util.List;
import java.util.concurrent.Callable;

public class UnorderedThread implements Callable<Integer> {
    int n;
    List<Double> vector;
    int margin;
    int threadNum;

    public UnorderedThread(int n, List<Double> vector, int margin, int threadNum) {
        this.n = n;
        this.vector = vector;
        this.margin = margin;
        this.threadNum = threadNum;
    }
    public Integer call() throws Exception {
        int count = 0;
        for(int i = margin + 1; i < n; i = i + threadNum){
            if(vector.get(i-1) > vector.get(i)){
                count = count + 1;
            }
        }
        return count;
    }
}
