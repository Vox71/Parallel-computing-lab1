import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class CreateChart {
    public static void chart(float oneThread, float twoThread, float threeThread, float fourThread, String name){
    double[] xData = new double[] { 1.0, 2.0, 3.0, 4.0 };
    double[] yData = new double[] { oneThread, twoThread, threeThread, fourThread };

    // Create Chart
    XYChart chart = QuickChart.getChart(name, "Потоки", "Y", "y(x)", xData, yData);

    // Show it
        new SwingWrapper(chart).displayChart();
}
}
