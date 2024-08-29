package game.entities.sportsman;

import game.arena.IArena;
import game.competition.Competitor;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import utilities.Point;

public class WinterSportsman extends Sportsman implements Competitor, Cloneable {
    private final Discipline discipline;
    private Point finish;
    private IArena arena;
    private static int idCounter = 1; // Static counter for unique IDs
    private int number;
    private String color; // New attribute to support color customization

    public WinterSportsman(String name, double age, Gender gender, double acceleration, double maxSpeed, Discipline discipline) {
        super(name, age, gender, acceleration, maxSpeed);
        this.discipline = discipline;
        this.number = generateUniqueNumber(); // Assign unique number upon creation
    }

    // Static method to generate unique numbers for competitors
    public static int generateUniqueNumber() {
        return idCounter++;
    }

    @Override
    public void initRace() {
        this.setLocation(new Point(0, this.getLocation().getY()));
    }

    @Override
    public void initRace(Point p, Point f, IArena arena) {
        this.setLocation(p);
        this.finish = f;
        this.arena = arena;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + getName() + " (Number: " + number + ")";
    }

    //region Getters & setters
    public Discipline getDiscipline() {
        return discipline;
    }

    @Override
    protected double getAcceleration() {
        return super.getAcceleration() + League.calcAccelerationBonus(this.getAge());
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }
    //endregion

    // Method to check if the competition is still in progress
    private boolean competitionInProgress() {
        boolean res = getLocation().getX() < finish.getX();
        Point p = getLocation();
        if (!res) setLocation(new Point(finish.getX(), p.getY()));
        return res;
    }

    @Override
    public void run() {
        while (competitionInProgress()) {
            move(arena.getFriction());
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        setChanged();
        notifyObservers();
    }

    @Override
    public WinterSportsman clone() throws CloneNotSupportedException {
        WinterSportsman cloned = (WinterSportsman) super.clone();
        cloned.setNumber(generateUniqueNumber()); // Assign a new unique number to the clone
        return cloned;
    }
}
