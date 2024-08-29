package game.competition;

import java.util.Observer;

import game.arena.IArena;
import game.entities.IMobileEntity;
import utilities.Point;

public interface Competitor extends IMobileEntity, Runnable, Cloneable {
    void initRace();
    void initRace(Point p, Point f, IArena arena);
    void addObserver(Observer o);

    // Clone method to be implemented by all competitors
}
