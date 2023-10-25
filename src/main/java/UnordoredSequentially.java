import java.util.List;
import java.util.concurrent.Callable;

public class UnordoredSequentially implements Callable<Integer> {
    int n;
    List<Double> vector;

    public UnordoredSequentially(int n, List<Double> vector) {
        this.n = n;
        this.vector = vector;
    }

    public Integer call() throws Exception {
        int count = 0;
        for(int i = 1; i < n; i++){
            if(vector.get(i-1) > vector.get(i)){
                count = count + 1;
            }
        }
        return count;
    }
}
