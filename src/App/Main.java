package App;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    public int i;
    public Button startButton;
    public TextField windowSizeTextField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override

    public void start(Stage primaryStage) throws Exception {
        /**
         * Main Component Properties
         */
        HBox root = new HBox();
        BorderPane timeLine = new BorderPane();
        BorderPane viewer = new BorderPane();
        viewer.setPrefWidth(700);
        timeLine.setPrefWidth(400);

        /**
         * Time Line Component
         */

        /**
         * Leftline
         */
        Line leftLine = new Line();
        leftLine.setStartX(0.0f);
        leftLine.setStartY(0.0f);
        leftLine.setEndX(0.0f);
        leftLine.setEndY(550.0f);

        /**
         * RightLine
         */
        Line rightLine = new Line();
        rightLine.setStartX(0.0f);
        rightLine.setStartY(0.0f);
        rightLine.setEndX(0.0f);
        rightLine.setEndY(550.0f);

        /**
         * LeftLine Container
         */
        Label Source = new Label("Source");
        HBox leftLineContainer = new HBox();
        leftLineContainer.setPadding(new Insets(0, 0, 0, 15));
        leftLineContainer.getChildren().add(leftLine);

        /**
         * Left Container
         */
        VBox leftContainer = new VBox();
        leftContainer.setAlignment(Pos.TOP_CENTER);
        leftContainer.setAlignment(Pos.TOP_CENTER);
        leftContainer.setSpacing(10);
        leftContainer.setPadding(new Insets(10, 30, 10, 30));
        leftContainer.getChildren().add(Source);
        leftContainer.getChildren().add(leftLineContainer);

        /**
         * Rightline Container
         */
        Label Destination = new Label("Destination");
        HBox rightLineContainer = new HBox();
        rightLineContainer.setPadding(new Insets(0, 0, 0, 30));
        rightLineContainer.getChildren().add(rightLine);

        /**
         * Right Container
         */
        VBox rightContainer = new VBox();
        rightContainer.setPadding(new Insets(10, 30, 10, 30));
        rightContainer.setAlignment(Pos.TOP_CENTER);
        rightContainer.setSpacing(10);
        rightContainer.getChildren().add(Destination);
        rightContainer.getChildren().add(rightLineContainer);

        /**
         * Labels Container
         */
        VBox labelsContainer = new VBox();
        labelsContainer.setPadding(new Insets(40, 0, 0, 0));
        labelsContainer.setSpacing(10);
        Label packetLabel = new Label("------------------> Packet 1");
        Label ackLabel = new Label("Ack 1 <----------------------");
        Label packet2Label = new Label("-------------------------------X");
        Label packet3Label = new Label("X-------------------------------");


        /**
         * timeline Component Set
         */
        timeLine.setLeft(leftContainer);
        timeLine.setRight(rightContainer);
        timeLine.setCenter(labelsContainer);

        /**
         * Viewer Component
         */

        /**
         * Upper area (Input of simulation)
         */
        Label windowSizeLabel = new Label("Window Size : ");
        windowSizeTextField = new TextField();
        startButton = new Button("Start");
        HBox upperArea = new HBox();
        upperArea.setAlignment(Pos.TOP_CENTER);
        upperArea.setPadding(new Insets(10, 0, 0, 0));
        upperArea.setSpacing(30);
        upperArea.getChildren().addAll(windowSizeLabel, windowSizeTextField, startButton);

        /**
         * Set Packets View
         */

        GridPane packetsContainer = new GridPane();
        packetsContainer.setHgap(10);

        /**
         * Packet Path and Component
         */

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int count = Integer.parseInt(windowSizeTextField.getText());
                ArrayList<Packet> packets = new ArrayList<Packet>();
                ArrayList<Acknowledgement> acknowledgements = new ArrayList<Acknowledgement>();
//                for (i = 0; i < count;i++ ) {
//                    packets.add(new Packet());
//                    packetsContainer.add(packets.get(i).getContainer(), i, 0);
//                    packets.get(i).getPt().play();
//
//                    packets.get(i).getPt().setOnFinished(new EventHandler<ActionEvent>() {
//                        @Override
//                        public void handle(ActionEvent event) {
//
//                            System.out.println("Packet Received !");
//                            labelsContainer.getChildren().add(new Label("Packet " + i));
//                            System.out.println(i);
////                            acknowledgements.add(new Acknowledgement());
////                            packetsContainer.add(acknowledgements.get(i).getContainer(), i, 0);
////                            acknowledgements.get(i).getPt().play();
////                            acknowledgements.get(i).getPt().setOnFinished(new EventHandler<ActionEvent>() {
////                                @Override
////                                public void handle(ActionEvent event) {
//
////                                    labelsContainer.getChildren().add(new Label("Ack " + i));
////                                }
////                            });
//                        }
//                    });
//                }

                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    int counter = 0;
                    @Override
                    public void run() {
                        System.out.println("Second passed !");
                        counter++;
                        if(counter == 6) {
                            timer.cancel();
                        }
                    }

                };
                timer.scheduleAtFixedRate(timerTask, 1000, 1000);

//                timer.cancel();

//                packets.add(new Packet());
//                    packetsContainer.add(packets.get(i).getContainer(), i, 0);
//                    packets.get(i).getPt().play();
//
//                Timeline animation = new Timeline(
//                        new KeyFrame(Duration.millis(1000),));
//                animation.setCycleCount(Timeline.INDEFINITE);
            }
        });


        /**
         * Viewer Component Set
         */
        viewer.setTop(upperArea);
        viewer.setCenter(packetsContainer);

        /**
         * Set the scene
         */
        root.getChildren().add(timeLine);
        root.getChildren().add(viewer);
        primaryStage.setTitle("Selective Repeat");
        primaryStage.setScene(new Scene(root, 1100, 600));
        primaryStage.show();
    }


}
