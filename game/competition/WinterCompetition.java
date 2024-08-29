package game.competition;

import game.arena.WinterArena;
import game.entities.sportsman.WinterSportsman;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

public class WinterCompetition extends Competition {
    private final Discipline discipline;
    private final League league;
    private final Gender gender;

    public WinterCompetition(WinterArena arena, int maxCompetitors, Discipline discipline, League league, Gender gender) {
        super(arena, maxCompetitors);
        this.discipline = discipline;
        this.league = league;
        this.gender = gender;
    }

    protected boolean isValidCompetitor(Competitor competitor) {
        if (competitor instanceof WinterSportsman) {
            WinterSportsman winterSportsman = (WinterSportsman) competitor;
            return discipline.equals(winterSportsman.getDiscipline()) &&
                   league.isInLeague(winterSportsman.getAge()) &&
                   gender.equals(winterSportsman.getGender());
        }
        return false;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public League getLeague() {
        return league;
    }

    public Gender getGender() {
        return gender;
    }

    // Prototype method to clone and register a new competitor
    public WinterSportsman cloneAndRegisterCompetitor(WinterSportsman original, String newColor) throws CloneNotSupportedException {
        // Clone the original competitor
        WinterSportsman clonedCompetitor = original.clone();
        // Modify the clone's attributes
        clonedCompetitor.setColor(newColor);
        // Assign a new unique number to the cloned competitor
        clonedCompetitor.setNumber(WinterSportsman.generateUniqueNumber());
        // Register the new competitor in the competition
        addCompetitor(clonedCompetitor);
        return clonedCompetitor;
    }
}
