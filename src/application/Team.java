package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

public class Team {
    
    public Label name;
    public TextField score;
    public HBox teamBox;

    public Team() {
        StackPane scoreStack = new StackPane();
        StackPane nameStack = new StackPane();
        teamBox = new HBox(10);
        name = new Label();
        score = new TextField();

        // show the boundary of teamBox
        teamBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                        + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                        + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
        
        teamBox.setMaxHeight(0);



        score.setVisible(false);
        score.setPrefWidth(80);
        score.setPromptText("Enter score here");


        name.setAlignment(Pos.CENTER_LEFT);
        score.setAlignment(Pos.CENTER_RIGHT);
        scoreStack.getChildren().add(score);
        scoreStack.setAlignment(Pos.CENTER_RIGHT);
        nameStack.getChildren().add(name);
        nameStack.setAlignment(Pos.CENTER_LEFT);
        teamBox.getChildren().add(scoreStack);
        teamBox.getChildren().add(nameStack);
        HBox.setHgrow(nameStack, Priority.ALWAYS);
    }
    
    

}
