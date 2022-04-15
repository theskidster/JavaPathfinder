package dev.theskidster.pathfinder.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Apr 15, 2022
 */

/**
 * @author J Hoffman
 * @since  
 */
class PathFinder {

    int[][] map = {
        {0, 0, 0, 0, 0},
        {0, 0, 1, 0, 1},
        {1, 0, 0, 1, 1},
        {0, 0, 0, 1, 0},
        {1, 1, 0, 0, 1},
    };
    
    Point start = new Point(0, 0, null);
    Point end   = new Point(3, 4, null);
    
    private boolean isWalkable(int[][] map, Point point) {
        if(point.y < 0 || point.y > map.length - 1) return false;
        if(point.x < 0 || point.x > map[0].length - 1) return false;
        return map[point.y][point.x] == 0;
    }
    
    private List<Point> findNeighbors(int[][] map, Point point) {
        List<Point> neighbors = new ArrayList<>();
        
        Point up    = point.offset( 0,  1);
        Point down  = point.offset( 0, -1);
        Point left  = point.offset(-1,  0);
        Point right = point.offset( 1,  0);
        
        if(isWalkable(map, up))    neighbors.add(up);
        if(isWalkable(map, down))  neighbors.add(down);
        if(isWalkable(map, left))  neighbors.add(left);
        if(isWalkable(map, right)) neighbors.add(right);
        
        return neighbors;
    }
    
    public static List<Point> findPath(int[][] map, Point start, Point end) {
        return null;
    }
    
}