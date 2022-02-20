package com.bik.smalldata.visualization;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;


// https://www.jetbrains.com/help/idea/javafx.html#create-project
public class ScatterPlotDemo extends Application{

    public static final double C = 2.5;
    public static final double M = 1.75;

    public void draw() {
        launch(null);
    }


    public static void main(String[] args) {
        launch(args);
    }

    // y = mx  + c
    public double[] getY(final double... xs) {
        final int length = xs.length;
        double[] ys = new double[length];
        for (int i = 0; i < length; i ++) {
            ys[i] = (xs[i] * M) + C;
        }
        return ys;
    }



    @Override
    public void start(Stage stage) throws Exception {
        double[] xData = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double[] yData = getY(xData);
        /* Adding data to series */
        XYChart.Series series = new XYChart.Series();
        for (int i = 0; i < xData.length; i++) {
            series.getData().add(new XYChart.Data(xData[i], yData[i]));
        }

        /* Creating axis */
        NumberAxis xAxis = new NumberAxis(); xAxis.setLabel("x");
        NumberAxis yAxis = new NumberAxis(); yAxis.setLabel("y");

        /* Initializing the scatter chart */
        ScatterChart<Number,Number> scatterChart = new ScatterChart<>(xAxis, yAxis);
        scatterChart.setAnimated(false);
        scatterChart.getData().add(series);

        /* Initializing  a scene using the chart */
        Scene scene = new Scene(scatterChart, 800, 600);

        /* Setting scene to the stage to render */
        stage.setScene(scene);

        /* save the chart to a file */
        WritableImage image = scatterChart.snapshot(new SnapshotParameters(), null);
        File file = new File("ScatterChart.png");
        ImageIO.write(javafx.embed.swing.SwingFXUtils.fromFXImage(image, null), "png", file);
    }

}
