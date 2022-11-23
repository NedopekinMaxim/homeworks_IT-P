import java.util.Collection;
import java.util.HashMap;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState {
    /**
     * This is a reference to the map that the A* algorithm is navigating.
     **/
    private Map2D map;

    private HashMap<Location, Waypoint> openWaypoints = new HashMap<Location, Waypoint>(); // hashmap открытых точек
    private HashMap<Location, Waypoint> closeWaypoints = new HashMap<Location, Waypoint>(); // hashmap закрытых точек


    // hashmap - это класс, которых хранит набор каких-то элементов в виде пар ключ-значение
    // location - waypoint

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map) {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /**
     * Returns the map that the A* pathfinder is navigating.
     **/
    public Map2D getMap() {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint() {
        // TODO:  Implement.
        if (this.numOpenWaypoints() != 0) { // если открытые точки не пустые
            double minCost = Double.MAX_VALUE; // минимальная стоимость пути
            Waypoint minWaypoint = null; // вершина с минимальной стоимостью пути
            Collection<Waypoint> arrayList = openWaypoints.values(); // все открытые вершины
            for (Waypoint waypoint : arrayList) { // перебираем вершины
                if (waypoint.getTotalCost() < minCost) { // если стоимость пути меньше минимальной
                    minCost = waypoint.getTotalCost();
                    minWaypoint = waypoint;
                }
            }
            return minWaypoint;
        }
        return null;
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP) {
        // TODO:  Implement.
        Location location = newWP.getLocation(); //сохраняем локацию
        if (openWaypoints.containsKey(location)) { // если точка с такой локацией есть в  hashmap
            Waypoint waypoint = openWaypoints.get(location); //  берем локации этой точки
            if (newWP.getPreviousCost() < waypoint.getPreviousCost()) { // если стоимость меньше
                openWaypoints.put(location, newWP); // то меняем
                return true;
            }
            return false; // или не меняем
        }
        openWaypoints.put(location, newWP); // если такой точки нет то создаем новую
        return true;
    }


    /**
     * Returns the current number of open waypoints.
     **/
    public int numOpenWaypoints() {
        // TODO:  Implement.
        return openWaypoints.size();
        // возвращает размер hashmap открытых точек
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc) {
        // TODO:  Implement.
        closeWaypoints.put(loc, openWaypoints.get(loc)); // точку с такой же локацией в "открытых" добавляем в "закрытые"
        openWaypoints.remove(loc); // удаляем точку из закрытых
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc) {
        // TODO:  Implement.
        return closeWaypoints.containsKey(loc); //проверка на содержание в hashmap
    }
}
