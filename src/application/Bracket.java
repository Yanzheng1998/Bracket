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
    
    private String[] reorder (String[] teams) {
    	int[] order = reorder(teams.length);
    	String[] reordered = new String[teams.length];
    	for (int i = 0; i <= teams.length - 1; i++) {
    		reordered[order[i]] = teams[i];
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
