package com.bik.smalldata.visualization;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class BarChartDemo extends Application {

    // from https://www.census.gov/popclock/print.php?component=counter
    private static final Map<String, Long> countriesAndPopulation =
            new HashMap<>(){{
                put("China", 1_407_098_834L);
                put("India", 1_380_721_926L);
                put("United States", 331_893_745L);
                put("Indonesia", 275_122_131L);
                put("Pakistan", 238_181_034L);
                put("Nigeria", 219_463_862L);
                put("Brazil", 215_903_281L);
                put("Nepal", 29_994_733L); //https://www.worldometers.info/world-population/nepal-population/
            }};

    public void draw() { launch(null);
    }


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        XYChart.Series series = new XYChart.Series();

        for (Map.Entry entry : countriesAndPopulation.entrySet()) {
            series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }

        CategoryAxis xAxis = new CategoryAxis(); xAxis.setLabel("x");
        NumberAxis yAxis = new NumberAxis(); yAxis.setLabel("y");

        BarChart<String,Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.getData().add(series);

        Scene scene = new Scene(barChart, 800, 600);

        stage.setScene(scene);
        stage.show();
    }
}
