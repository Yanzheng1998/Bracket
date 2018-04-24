package application;




public class Bracket {
    
    public String[][] leftChallengerLists;
    public String[][] rightChallengerLists;
    public int roundNum;

    public Bracket(String[] teamsName) {
        int numOfTeam = teamsName.length;
        int teamInSeries = numOfTeam;
        teamsName = reorder(teamsName);
        roundNum = 0;
        while(teamInSeries!=1) {
            teamInSeries/=2;
            roundNum++;
        }
        leftChallengerLists = new String[roundNum+1][numOfTeam/2+1];
        rightChallengerLists = new String[roundNum+1][numOfTeam/2+1];
        for(int i=0; i<numOfTeam/2; i++) {
            leftChallengerLists[1][i+1] = teamsName[i];
            rightChallengerLists[1][i+1] = teamsName[numOfTeam-1-i];
        }
    }
    
    
    private int[] reorder(int teamNum) {
        int[] array = new int[teamNum];
//    	int[] prev;
//        int i;
//        
//        if (teamNum <= 2)
//        {
//        	array[0] = 0;
//        	array[1] = 1;
//        	return array
//        }
//        
//        prev = reorder()
//    	return teamsName;
        // TODO reorder teams
//        String[] orderedTeams = new String[teamsName.length];
//        for(int i=1; i<=teamsName.length; i++) {
//            
//        }
        return array;
    }

    
    
}
