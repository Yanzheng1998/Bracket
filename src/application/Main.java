package application;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {

    public static String[] teamsName;
    public static int numOfTeam;
    public static Bracket bracket;

    @Override
    public void start(Stage primaryStage) {

            if (numOfTeam == 0) {

                GraphGenerator.setupNoTeam(primaryStage);

            } else if (numOfTeam == 1) {

                GraphGenerator.setupOneTeam(primaryStage, teamsName[0]);

            } else {

                GraphGenerator.setupMultipleTeams(primaryStage, teamsName, bracket);

            }

    }

    public static void main(String[] args) {
    
    	try {
            teamsName = Files.lines(Paths.get("teams"))
                            .map(String::trim)
                            .filter(x->x!=null && !x.equals(""))
                            .toArray(String[]::new);
            numOfTeam = teamsName.length;
            bracket = new Bracket(teamsName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch(args);
    }

}
