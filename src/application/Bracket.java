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


public class Bracket {
    
    public Team[][] leftChallengerLists;
    public Team[][] rightChallengerLists;
    public String[] thirdPlaceName = new String[2];
    public Integer[] thirdPlaceScore = new Integer[2];
    public int roundNum;

    /**
    * set up the bracket
    * @param teamsName - all names of the teams in the file
    */
    public Bracket(String[] teamsName) {
        int numOfTeam = teamsName.length;
        int teamInSeries = numOfTeam;
        teamsName = reorder(teamsName);
        roundNum = 0;
        while(teamInSeries!=1) {
            teamInSeries/=2;
            roundNum++;
        }
        leftChallengerLists = new Team[roundNum+1][numOfTeam/2+1];
        rightChallengerLists = new Team[roundNum+1][numOfTeam/2+1];
        for(int i=0; i<numOfTeam/2; i++) {
            leftChallengerLists[1][i+1] = new Team();
            leftChallengerLists[1][i+1].name.setText(teamsName[i]);
            leftChallengerLists[1][i+1].score.setVisible(true);
            rightChallengerLists[1][i+1] = new Team();
            rightChallengerLists[1][i+1].name.setText(teamsName[numOfTeam-1-i]);
            rightChallengerLists[1][i+1].score.setVisible(true);
        }
    }
    
    // teams are seeded by the challenger's rank order
    private String[] reorder (String[] teams) {
        int[] order = reorder(teams.length);
        String[] reordered = new String[teams.length];
        for (int i = 0; i <= teams.length - 1; i++) {
            reordered[i] = teams[order[i]];
        }
        return reordered;
    }
    
    private int[] reorder(int teamNum) {
        int[] array = new int[teamNum];
        int[] prev;
        int i;
        
        if (teamNum <= 2)
        {
            array[0] = 0;
            array[1] = 1;
            return array;
        }
        
        prev = reorder(teamNum/2);
        for(i=0;i< teamNum; i+=2)
        {
            array[i] = prev[i/2];
            array[i + 1] = teamNum -1 - array[i];
        }
         return array;
        
    }

    
    
}
