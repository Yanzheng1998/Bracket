///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Tournament Bracket
// Files:            Main.java, AlertBox.java, Bracket.java, GraphGenerateor.java, Team.java, VSButton.java, application.css, style.css, background2.jpg
// Semester:         CS400 Spring 2018
//
// Author:           Weiheng Wang wwang522@wisc.edu
//                   Haoran Li hli584@wisc.edu
//                   Yuming Ma  ma226@wisc.edu
//                   Yanzheng Li li694@wisc.edu
//                   Taijing Chen tchen284@wisc.edu
//
//
//                   
// Lecturer's Name:  Deb Deppeler
//
//////////////////////////// 80 columns wide //////////////////////////////////


/**
 * Team class contains info of one team and the box that contain the info which will be
 * displayed in the UI.
 */

package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

public class Team {
    
    public Label name; // name of team
    public TextField score; // input score of team
    public HBox teamBox; // the box that contains the team
    
    /**
     * Constructor of Team
     * Set the style of team box
     */
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
        
        name.setMinWidth(40);


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
