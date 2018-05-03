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
 * VSButton class contains styles and functions of general VSbuttons.
 */
package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
* The constructor for VSbutton
* 
* @param bracket the bracket class containing info of all team
* @param teams the info of right half teams or left half teams
* @param seriesNumber the current round number
* @param teamNum the placement number of a team in a row
* @param region could be 0 - left half or 1 - right
*/
public class VSButton {
	public Button vsButton; // button node
	public HBox buttonBox; // Hbox node contains button

	public VSButton(Bracket bracket, Team[][] teams, int seriesNumber, int teamNum, int region) {
		vsButton = new Button("VS");
		vsButton.setMinWidth(60);
		buttonBox = new HBox();
		
		// set action
		vsButton.setOnAction(new EventHandler<ActionEvent>() {
			/**
			 * action method of button's inner class - contains the action need to complete when 
			 * user click on general VS buttons.
			 * The system will compare the score entered by user and handle exceptions that could 
			 * occur. Then the input box, button and label of the winner team would appear at next
			 * team box.
			 */
			@Override
			public void handle(ActionEvent e) {
				if (!teams[seriesNumber - 1][teamNum * 2].score.isVisible() || !teams[seriesNumber - 1][teamNum * 2].score.isVisible())
                    			return;
				
				String name1 = teams[seriesNumber - 1][teamNum * 2 - 1].name.getText();
				String name2 = teams[seriesNumber - 1][teamNum * 2].name.getText();
				String score1 = teams[seriesNumber - 1][teamNum * 2 - 1].score.getText();
				String score2 = teams[seriesNumber - 1][teamNum * 2].score.getText();
				try {
					Integer scoreNum1 = Integer.valueOf(score1);
					Integer scoreNum2 = Integer.valueOf(score2);
					if(scoreNum1 < 0 || scoreNum2 < 0)
                        			throw new ArithmeticException();
                    			if(scoreNum1 == scoreNum2)
                        			throw new NullPointerException();
					
					if (scoreNum1 > scoreNum2) {
						teams[seriesNumber][teamNum].name.setText(name1);
						teams[seriesNumber][teamNum].score.setVisible(true);
						
						teams[seriesNumber - 1][teamNum * 2].score.setVisible(false);
						teams[seriesNumber - 1][teamNum * 2].score.setMaxWidth(1);
						teams[seriesNumber - 1][teamNum * 2 - 1].score.setVisible(false);
						teams[seriesNumber - 1][teamNum * 2 - 1].score.setMaxWidth(1);
						teams[seriesNumber - 1][teamNum * 2].name.setText(name2 + ": " + score2);
						teams[seriesNumber - 1][teamNum * 2 - 1].name.setText(name1 + ": " + score1);

						teams[seriesNumber - 1][teamNum * 2 - 1].name.setTextFill(Color.web("#FF3030"));
						
						// is this button is the semi-final game button
						if (seriesNumber == bracket.roundNum) {
							bracket.thirdPlaceName[region] = name2;
							bracket.thirdPlaceScore[region] = scoreNum2;
						}
					} else {
						teams[seriesNumber][teamNum].name.setText(name2);
						teams[seriesNumber][teamNum].score.setVisible(true);
						
						teams[seriesNumber - 1][teamNum * 2].score.setVisible(false);
						teams[seriesNumber - 1][teamNum * 2].score.setMaxWidth(1);
						teams[seriesNumber - 1][teamNum * 2 - 1].score.setVisible(false);
						teams[seriesNumber - 1][teamNum * 2 - 1].score.setMaxWidth(1);
						teams[seriesNumber - 1][teamNum * 2].name.setText(name2 + ": " + score2);
						teams[seriesNumber - 1][teamNum * 2 - 1].name.setText(name1 + ": " + score1);

						teams[seriesNumber - 1][teamNum * 2].name.setTextFill(Color.web("#FF3030"));
						
						// is this button is the semi-final game button
						if (seriesNumber == bracket.roundNum) {
							bracket.thirdPlaceName[region] = name1;
							bracket.thirdPlaceScore[region] = scoreNum1;
						}
					}
				} catch (NumberFormatException r) {
				    if (score1.isEmpty() || score2.isEmpty())
					AlertBox.display("WARNING", "Score entered is empty");
				    else
					AlertBox.display("WARNING", "Please enter integers as team score");
				} catch (ArithmeticException r) {
				    AlertBox.display("WARNING", "Please enter non-negative integers as team score");
				} catch (NullPointerException r) {
				    AlertBox.display("Result Unclear", "The two teams competing have same scores, thus no team is winning");
				}
			}
		});
		buttonBox.getChildren().add(vsButton);
		buttonBox.setAlignment(Pos.CENTER);

	}

}
