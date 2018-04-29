package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class VSButton {

    public Button vsButton;
    public HBox buttonBox;

    public VSButton(Bracket bracket, Team[][] teams, int seriesNumber, int teamNum, int region) {
        vsButton = new Button("VS");
        buttonBox = new HBox();
        // set action
        vsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String name1 = teams[seriesNumber - 1][teamNum * 2 - 1].name.getText();
                String name2 = teams[seriesNumber - 1][teamNum * 2].name.getText();
                String score1 = teams[seriesNumber - 1][teamNum * 2 - 1].score.getText();
                String score2 = teams[seriesNumber - 1][teamNum * 2].score.getText();
                try {
                    Integer scoreNum1 = Integer.valueOf(score1);
                    Integer scoreNum2 = Integer.valueOf(score2);
                    if(scoreNum1 < 0 || scoreNum2 < 2)
                        throw new ArithmeticException();
                    
                    if (score1 != null && score2 != null) {
                        if (scoreNum1 > scoreNum2) {
                            teams[seriesNumber][teamNum].name.setText(name1);
                            teams[seriesNumber][teamNum].score.setVisible(true);
                            // is this button is the semi-final game button
                            if(seriesNumber==bracket.roundNum) {
                                bracket.thirdPlaceName[region] = name2;
                                bracket.thirdPlaceScore[region] = scoreNum2;
                            }
                        } else {
                            teams[seriesNumber][teamNum].name.setText(name2);
                            teams[seriesNumber][teamNum].score.setVisible(true);
                            // is this button is the semi-final game button
                            if(seriesNumber==bracket.roundNum) {
                                bracket.thirdPlaceName[region] = name1;
                                bracket.thirdPlaceScore[region] = scoreNum1;
                            }
                        }
                    }
                } catch (NumberFormatException r) {
                    if (score1.isEmpty() || score2.isEmpty())
                        AlertBox.display("WARNING", "Score entered is empty");
                    else
                        AlertBox.display("WARNING", "Please enter integers as team score");
                } catch (ArithmeticException r) {
                    AlertBox.display("WARNING", "Please enter positive integer as team score");
                }
            }
        });
        buttonBox.getChildren().add(vsButton);
        buttonBox.setAlignment(Pos.CENTER);

    }



}
