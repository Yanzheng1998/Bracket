package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GraphGenerator {

    static AnchorPane root = new AnchorPane();
    static GridPane grid = new GridPane();
    public static String[] teamsName;
    public static int numOfTeam;
    public static Bracket bracket;


    public static void setupNoTeam(Stage primaryStage) {
        primaryStage.setTitle("Label");
        StackPane sPane = new StackPane();
        Scene scene = new Scene(sPane, 1600, 900, Color.DARKGRAY);
        Label label = new Label();
        label.setText("There is no team there!");
        label.setFont(new Font("Arial", 50));
        sPane.getChildren().add(label);
        StackPane.setAlignment(label, Pos.CENTER);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void setupOneTeam(Stage primaryStage, String teamName) {
        primaryStage.setTitle("Label");
        StackPane sPane = new StackPane();
        Scene scene = new Scene(sPane, 1600, 900, Color.DARKGRAY);
        Label label = new Label();
        label.setText(teamName + " is Championship!");
        label.setFont(new Font("Arial", 50));
        sPane.getChildren().add(label);
        StackPane.setAlignment(label, Pos.CENTER);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void setupMultipleTeams(Stage primaryStage, String[] teamsNamePass,
                    Bracket bracketPass) {
        bracket = bracketPass;
        teamsName = teamsNamePass;
        numOfTeam = teamsName.length;

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        int teamsInSeries = numOfTeam / 2;
        int seriesNumber = 1;

        // create left part
        while (teamsInSeries != 0) {
            setupLeftSeries(primaryStage, teamsInSeries, seriesNumber);
            teamsInSeries /= 2;
            seriesNumber++;
        }


        // set final game button
        HBox finalButtonBox = new HBox();
        Button finalButton = new Button("Final");
        finalButton.setMinWidth(60);
        finalButton.setId("finalButton");
        finalButton.setShape(new Circle(1.5));
        // set action
        finalButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String name1 = bracket.leftChallengerLists[bracket.roundNum][1].name.getText();
                String name2 = bracket.rightChallengerLists[bracket.roundNum][1].name.getText();
                String score1 = bracket.leftChallengerLists[bracket.roundNum][1].score.getText();
                String score2 = bracket.rightChallengerLists[bracket.roundNum][1].score.getText();
                Integer scoreNum1 = Integer.valueOf(score1);
                Integer scoreNum2 = Integer.valueOf(score2);
                if (score1 != null && score2 != null) {

                    // if we have third place
                    String thirdPalce = new String();
                    if (bracket.thirdPlaceName[0] != null) {
                        if (bracket.thirdPlaceScore[0] > bracket.thirdPlaceScore[1]) {
                            thirdPalce = new String(bracket.thirdPlaceName[0]);
                        } else {
                            thirdPalce = new String(bracket.thirdPlaceName[1]);
                        }
                    }
                    if (scoreNum1 > scoreNum2) {
                        // name1 is the first place
                        // name2 is the second place
                        AlertBox.displayPlacement(name1, name2, thirdPalce);
                        if (thirdPalce != null) {
                            // thirdPalce is the third place 
                        }
                        // alert box
                    } else {
                        // name2 is first place
                        // name1 is second place
                        AlertBox.displayPlacement(name2, name1, thirdPalce);
                        if (thirdPalce != null) {
                            // thirdPalce is the third place 
                        }
                        // alert box
                    }
                }
            }
        });
        finalButtonBox.getChildren().add(finalButton);
        finalButtonBox.setAlignment(Pos.CENTER);
        grid.add(finalButtonBox, bracket.roundNum,
                        (int) ((Math.pow(2, (bracket.roundNum - 1)) - 1)));



        teamsInSeries = numOfTeam / 2;
        seriesNumber = 1;
        // create right part
        while (teamsInSeries != 0) {
            setupRightSeries(primaryStage, teamsInSeries, seriesNumber);
            teamsInSeries /= 2;
            seriesNumber++;
        }


        // show in the stage
        root.getChildren().add(grid);
         grid.setGridLinesVisible(true);
        AnchorPane.setLeftAnchor(grid, 0.0);
        AnchorPane.setRightAnchor(grid, 0.0);
        AnchorPane.setTopAnchor(grid, 0.0);
        AnchorPane.setBottomAnchor(grid, 0.0);
        // root.setStyle("-fx-background-color: yellow");
        Scene scene = new Scene(root, 1600, 900);

        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private static void setupLeftSeries(Stage primaryStage, int teamsInSeries, int seriesNumber) {


        // If this is the first round, then setup the total rows we need to in this bracket and set
        // the instruction
        if (seriesNumber == 1) {
            int numRows = numOfTeam;
            for (int i = 0; i < numRows; i++) {
                RowConstraints rc = new RowConstraints();
                rc.setPercentHeight(100.0 / numRows);
                grid.getRowConstraints().add(rc);
            }
            // set up the instruction
            HBox instructionBox = new HBox();
            Label labelInstruction = new Label("Enter the scores and click the button!");
            instructionBox.getChildren().add(labelInstruction);
            instructionBox.setAlignment(Pos.CENTER);
            grid.add(instructionBox, bracket.roundNum-1, numRows - 1, 3, 1);
        }

        for (int i = 1; i <= teamsInSeries; i++) {

            if (seriesNumber != 1) {
                bracket.leftChallengerLists[seriesNumber][i] = new Team();
            }


            // set button and action
            if (numOfTeam / 2 != teamsInSeries) {
                VSButton vsButton = new VSButton(bracket, bracket.leftChallengerLists, seriesNumber,
                                i, 0);

                grid.add(vsButton.buttonBox, seriesNumber - 2,
                                (int) ((Math.pow(2, (seriesNumber - 1)) - 1)
                                                + (i - 1) * Math.pow(2, seriesNumber)));
            }
            grid.add(bracket.leftChallengerLists[seriesNumber][i].teamBox, seriesNumber - 1,
                            (int) ((Math.pow(2, (seriesNumber - 1)) - 1)
                                            + (i - 1) * Math.pow(2, seriesNumber)));



        }


    }


    private static void setupRightSeries(Stage primaryStage, int teamsInSeries, int seriesNumber) {


        for (int i = 1; i <= teamsInSeries; i++) {

            if (seriesNumber != 1) {
                bracket.rightChallengerLists[seriesNumber][i] = new Team();
            }

            // set button and action
            if (numOfTeam / 2 != teamsInSeries) {
                VSButton vsButton = new VSButton(bracket, bracket.rightChallengerLists,
                                seriesNumber, i, 1);
                grid.add(vsButton.buttonBox, bracket.roundNum * 2 + 2 - seriesNumber + 1 -1,
                                (int) ((Math.pow(2, (seriesNumber - 1)) - 1)
                                                + (i - 1) * Math.pow(2, seriesNumber)));
            }
            grid.add(bracket.rightChallengerLists[seriesNumber][i].teamBox,
                            bracket.roundNum * 2 + 2 - seriesNumber-1,
                            (int) ((Math.pow(2, (seriesNumber - 1)) - 1)
                                            + (i - 1) * Math.pow(2, seriesNumber)));



        }


    }



}