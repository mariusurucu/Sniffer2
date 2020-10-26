package sniffer2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;

public class Sniffer2 extends Application {

    Stage window;
    Scene scene1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        //Button 1
        Label label1 = new Label("Welcome to the first scene!");
        Button button1 = new Button("Start ");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                PieChartSample pc = new PieChartSample();
                try {
                    pc.start(window);
                } catch (PcapNativeException ex) {
                    Logger.getLogger(Sniffer2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Sniffer2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NotOpenException ex) {
                    Logger.getLogger(Sniffer2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sniffer2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //Layout 1 - children laid out in vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 200, 200);

        //Display scene 1 at first
        window.setScene(scene1);
        window.setTitle("Title Here");
        window.show();
    }

}