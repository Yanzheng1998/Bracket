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

package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class implements static methods to display the final result in a pop-up window.
 * 
 * @author Taijing Chen
 *
 */
public class AlertBox {
    
    /**
     * Pop up a window with given title and display the contents in the message
     * Has to click "Confirm" button to close the window
     * 
     * @param title - tile of the pop-up window
     * @param message - message want to display
     */
    public static void display(String title, String message) {
        // set up the new window
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinHeight(150);
        window.setMinWidth(450);
        
        // display information
        Label messageLabel = new Label();
        messageLabel.setText(message);
        messageLabel.setFont(Font.font("Arial", 14));
        // button to close the window
        Button closeButton = new Button("Confirm");
        closeButton.setOnAction(e -> window.close());
        closeButton.setStyle("-fx-font: 14 arial");
        
        // design layout
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(messageLabel, closeButton);
        vBox.setAlignment(Pos.CENTER);
        
        // display the stage
        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();
    }
    
    /**
     * Pop up window with a tile named as "Final Result", and display the given
     * placements information. Has to click "Confirm" Button to close the window.
     * 
     * @param first - name of champion team
     * @param second - name of 2nd team
     * @param third - name of 3rd team
     */
    public static void displayPlacement(String first, String second, String third, Stage stageToClose) {
        // set up stage
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Final Result");
        
        // set up a listView of placements
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(
                "THE CHAMPION: " + first,
                "THE 2ND PLACE: " + second,
                "THE 3RD PLACE: " + third);
        listView.setMaxSize(500, 100);
        listView.setStyle("-fx-font: 14 arial");
        
        // set up confirm button
        Button button = new Button("Confirm");
        button.setOnAction( e -> {
            window.close();
            AlertBox.closeConfirmBox(stageToClose, "Do you want to quit the program?");
        } );
        button.setStyle("-fx-font: 14 arial");
        
        // set up layout
        VBox vBox = new VBox();
        vBox.getChildren().addAll(listView, button);
        vBox.setAlignment(Pos.CENTER);
        
        // display the stage
        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();
    }
    
    /**
     * This private method helps promt a confirm box when the program ends. 
     * If the user clicked yes, then he/she will quit the program
     * If the user clicked no, then he/she will return to previous stage
     * 
     * @param stageToClose - the stage to close
     * @param message - the message to display
     */
    private static void closeConfirmBox(Stage stageToClose, String message) {
        // set up stage
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Close Program");
        window.setMinHeight(150);
        window.setMinWidth(450);
        
        // set up buttons
        StackPane layout = new StackPane();
        Button yesButton = new Button("YES");
        Button noButton = new Button("NO");
        noButton.setOnAction(e -> window.close());
        yesButton.setOnAction(e -> {
            window.close();
            stageToClose.close();
        });
        yesButton.setStyle("-fx-font: 14 arial");
        noButton.setStyle("-fx-font: 14 arial");
        Label messageLabel = new Label(message);
        messageLabel.setFont(Font.font("Arial", 14));
        
        // group buttons into boxes
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(yesButton, noButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(30);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(messageLabel, buttonBox);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        
        // set up layout and show the current stage
        layout.getChildren().add(vBox);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
        
    }

}