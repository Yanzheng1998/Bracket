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


    public static void setupMultipleTeams(Stage primaryStage, String[] teamsNamePass, Bracket bracketPass) {
        bracket = bracketPass;
        teamsName = teamsNamePass;
        numOfTeam = teamsName.length;

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        int teamsInSeries = numOfTeam/2;
        int seriesNumber = 1;

        setupInstruction();
        // create left part 
        while (teamsInSeries != 0) {
            // TODO setup one column (set team slot by ID (button is associated with those IDs,
            // champion should be special))
            setupLeftSeries(primaryStage, teamsInSeries, seriesNumber);
            teamsInSeries /= 2;
            seriesNumber++;
        }
        
        // set final game button
        HBox buttonBox = new HBox(10);
        Button VSbutton = new Button("Final");

        // set action
        VSbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
//                (HBox) grid.lookup("#1.1");
                // TODO
            }
        });
        buttonBox.getChildren().add(VSbutton);
        buttonBox.setAlignment(Pos.CENTER);
        grid.add(buttonBox, bracket.roundNum,(int) ((Math.pow(2, (bracket.roundNum - 1)) - 1)));
        
        
        
        teamsInSeries = numOfTeam/2;
        seriesNumber = 1;
        // create right part 
        while (teamsInSeries != 0) {
            // TODO setup one column (set team slot by ID (button is associated with those IDs,
            // champion should be special))
            setupRightSeries(primaryStage, teamsInSeries, seriesNumber);
            teamsInSeries /= 2;
            seriesNumber++;
        }

        // TODO setup the champion slot

        root.getChildren().add(grid);
        // grid.setGridLinesVisible(true);
        AnchorPane.setLeftAnchor(grid, 0.0);
        AnchorPane.setRightAnchor(grid, 0.0);
        AnchorPane.setTopAnchor(grid, 0.0);
        AnchorPane.setBottomAnchor(grid, 0.0);
        // root.setStyle("-fx-background-color: yellow");
        Scene scene = new Scene(root);

        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private static void setupLeftSeries(Stage primaryStage, int teamsInSeries, int seriesNumber) {

        // TODO setup the first column: should ordered by the seed, set team slot by ID (button is
        // associated with those IDs)

        // If this is the first round, then setup the total rows we need to in this bracket and set the instruction 
        if (seriesNumber == 1) {
            int numRows = numOfTeam -1;
            for (int i = 0; i < numRows; i++) {
                RowConstraints rc = new RowConstraints();
                rc.setPercentHeight(100.0 / numRows);
                grid.getRowConstraints().add(rc);
            }
//            Label labelInstruction = new Label("Enter the scores and click the button!");
//            grid.add(labelInstruction, 3, numRows);
        }

        for (int i = 1; i <= teamsInSeries; i++) {

            StackPane stack = new StackPane();
            HBox teamBox = new HBox(10);

            // show the boundary of teamBox
            teamBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                            + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                            + "-fx-border-radius: 5;" + "-fx-border-color: blue;");



            // Label teamName = new Label(teamsName[i]);
            Label teamName = new Label();
            TextField score = new TextField();
            score.setVisible(false);
            score.setPrefWidth(80);
            // If according challenger exist
            if(bracket.leftChallengerLists[seriesNumber][i]!=null) {
                teamName.setText(bracket.leftChallengerLists[seriesNumber][i]);
                score.setVisible(true);
            }

            // set id for every teamBox
//            teamBox.setId(new String(seriesNumber + "." + i));

            teamName.setAlignment(Pos.CENTER_LEFT);
            score.setAlignment(Pos.CENTER_RIGHT);
            teamBox.getChildren().add(teamName);
            stack.getChildren().add(score);
            stack.setAlignment(Pos.CENTER_RIGHT);
            teamBox.getChildren().add(stack);
            HBox.setHgrow(stack, Priority.ALWAYS);

            // set button and action
            if (numOfTeam/2 != teamsInSeries) {
                HBox buttonBox = new HBox(10);
                Button VSbutton = new Button("VS");

                // set action
                VSbutton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
//                        (HBox) grid.lookup("#1.1");
                        // TODO
                    }
                });
                buttonBox.getChildren().add(VSbutton);
                buttonBox.setAlignment(Pos.CENTER);
                grid.add(buttonBox, seriesNumber - 2, (int) ((Math.pow(2, (seriesNumber - 1)) - 1)
                                + (i-1) * Math.pow(2, seriesNumber)));
            }
            grid.add(teamBox, seriesNumber - 1, (int) ((Math.pow(2, (seriesNumber - 1)) - 1)
                            + (i-1) * Math.pow(2, seriesNumber)));



        }


    }
    
    
    private static void setupRightSeries(Stage primaryStage, int teamsInSeries, int seriesNumber) {


        for (int i = 1; i <= teamsInSeries; i++) {

            StackPane stack = new StackPane();
            HBox teamBox = new HBox(10);

            // show the boundary of teamBox
            teamBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                            + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                            + "-fx-border-radius: 5;" + "-fx-border-color: blue;");



            // Label teamName = new Label(teamsName[i]);
            Label teamName = new Label();
            TextField score = new TextField();
            score.setVisible(false);
            score.setPrefWidth(80);
            // If according challenger exist
            if(bracket.rightChallengerLists[seriesNumber][i]!=null) {
                teamName.setText(bracket.rightChallengerLists[seriesNumber][i]);
                score.setVisible(true);
            }

            // set id for every teamBox
//            teamBox.setId(new String(seriesNumber + "." + i));

            teamName.setAlignment(Pos.CENTER_LEFT);
            score.setAlignment(Pos.CENTER_RIGHT);
            stack.setAlignment(Pos.CENTER_RIGHT);
            teamBox.getChildren().add(stack);
            teamBox.getChildren().add(teamName);
            stack.getChildren().add(score);
            HBox.setHgrow(stack, Priority.ALWAYS);

            // set button and action
            if (numOfTeam/2 != teamsInSeries) {
                HBox buttonBox = new HBox(10);
                Button VSbutton = new Button("VS");

                // set action
                VSbutton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
//                        (HBox) grid.lookup("#1.1");
                        // TODO
                    }
                });
                buttonBox.getChildren().add(VSbutton);
                buttonBox.setAlignment(Pos.CENTER);
                grid.add(buttonBox, bracket.roundNum*2+2-seriesNumber+1, (int) ((Math.pow(2, (seriesNumber - 1)) - 1)
                                + (i-1) * Math.pow(2, seriesNumber)));
            }
            grid.add(teamBox, bracket.roundNum*2+2-seriesNumber, (int) ((Math.pow(2, (seriesNumber - 1)) - 1)
                            + (i-1) * Math.pow(2, seriesNumber)));



        }


    }
    
    
    
    private static void setupInstruction() {
        // TODO setup instruction
    }
    
    


}
