package sniffer2;
import java.io.IOException;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;
 
public class PieChartSample extends Application {
 
    @Override public void start(Stage stage) throws PcapNativeException, IOException, NotOpenException, InterruptedException {
        
        int[] result = new int[6];
        NetworkSniffing.run();
        result =NetworkSniffing.getCounters();
        
        
        Scene scene = new Scene(new Group());
        stage.setTitle("Sniffing Result");
        stage.setWidth(500);
        stage.setHeight(500);
 
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("TCP", result[0]),
                new PieChart.Data("UDP", result[1]),
                new PieChart.Data("ipv4", result[2]),
                new PieChart.Data("ipv6", result[3]),
                new PieChart.Data("ethernet", result[4]),
                new PieChart.Data("other", result[5]));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Sniffing Result");
        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " ", data.pieValueProperty()
                        )
                )
        );

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args){
        launch(args);
    }
}