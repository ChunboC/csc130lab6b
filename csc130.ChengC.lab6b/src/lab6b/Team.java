package lab6b;

import java.util.Comparator;

/**
 * <p>
 * Title: Team.java
 * </p>
 *
 * <p>
 * Description: Represents a football team with attributes such as name, wins,
 * losses, draws, goals scored, goals conceded, and points. Provides methods to
 * manipulate and retrieve team statistics. Implements the Comparable interface
 * to enable comparison based on points.
 * </p>
 *
 * @author Chunbo Cheng
 */
public class Team implements Comparable<Team> {
	private String name;
	// private int wins;
	// private int loses;
	// private int draws;
	private int goalsFor;
	private int goalsAgainst;
	// private int points;

	private int homeWins;
	private int awayWins;
	private int homeDraws;
	private int awayDraws;
	private int homeLosses;
	private int awayLosses;

	/**
	 * Constructs a new Team object with the given name.
	 *
	 * @param name The name of the team.
	 */
	public Team(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param homeGame True if home game, false otherwise
	 * @param gf       Number of goals scored
	 * @param ga       Number of goals for opposing team
	 */
	public void score(boolean homeGame, int gf, int ga) {
		goalsFor += gf;
		goalsAgainst += ga;
		if (homeGame) {
			if (gf > ga)
				homeWins++;
			else if (gf < ga)
				homeLosses++;
			else
				homeDraws++;
		} else {
			if (gf > ga)
				awayWins++;
			else if (gf < ga)
				awayLosses++;
			else
				awayDraws++;
		}
	}

	public String getName() {
		return name;
	}

	public int getWins() {
		return homeWins + awayWins;
	}

	public int getLosses() {
		return homeLosses + awayLosses;
	}

	public int getDraws() {
		return homeDraws + awayDraws;
	}

	public int getPoints() {
		return getHomePoints() + getAwayPoints();
	}

	public int getGoalsFor() {
		return goalsFor;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public int goalDifference() {
		return goalsFor - goalsAgainst;
	}

	public int getHomeWins() {
		return homeWins;
	}

	public int getHomeDraws() {
		return homeDraws;
	}

	public int getHomeLosses() {
		return homeLosses;
	}

	public int getHomePoints() {
		return homeWins * 3 + homeDraws;
	}

	public int getAwayWins() {
		return awayWins;
	}

	public int getAwayDraws() {
		return awayDraws;
	}

	public int getAwayLosses() {
		return awayLosses;
	}

	public int getAwayPoints() {
		return awayWins * 3 + awayDraws;
	}

	public int getGamesPlayed() {
		return homeWins + homeDraws + homeLosses + awayWins + awayDraws + awayLosses;
	}

	public String toString() {
		return name + " wins: " + getWins() + " draws: " + getDraws() + " losses: " + getLosses() + " points: "
				+ getPoints();
	}

	public boolean equals(Object ot) {
		if (ot instanceof Team)
			return this.equals((Team) ot);
		return false;
	}

	public boolean equals(Team ot) {
		return name.equals(ot.getName());
	}

	@Override
	public int compareTo(Team ot) {
		return getPoints() - ot.getPoints();
	}
}

// complete this based on information in PDF file
class PointsComparator implements Comparator<Team> {
	public int compare(Team thisTeam, Team otherTeam) {
		if (thisTeam.getPoints() == otherTeam.getPoints())
			return thisTeam.goalDifference() - otherTeam.goalDifference();
		return thisTeam.getPoints() - otherTeam.getPoints();
	}
}

class HomeWinsComparator implements Comparator<Team> {
	public int compare(Team thisTeam, Team otherTeam) {
		if (thisTeam.getHomeWins() == otherTeam.getHomeWins())
			return thisTeam.getHomePoints() - otherTeam.getHomePoints();
		return thisTeam.getHomeWins() - otherTeam.getHomeWins();
	}
}

class AwayWinsComparator implements Comparator<Team> {
	public int compare(Team thisTeam, Team otherTeam) {
		if (thisTeam.getAwayWins() == otherTeam.getAwayWins())
			return thisTeam.getAwayPoints() - otherTeam.getAwayPoints();
		return thisTeam.getAwayWins() - otherTeam.getAwayWins();
	}
}

class NamesComparator implements Comparator<Team> {
	public int compare(Team thisTeam, Team otherTeam) {
		return thisTeam.getName().compareTo(otherTeam.getName());
	}
}