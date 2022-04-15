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
    
    public List<Point> findPath(int[][] map, Point start, Point end) {
        boolean finished = false;
        List<Point> used = new ArrayList<>();
        
        used.add(start);
        
        while(!finished) {
            List<Point> newOpen = new ArrayList<>();
            
            for(int i = 0; i < used.size(); ++i) {
                Point point = used.get(i);
                
                for(Point neighbor : findNeighbors(map, point)) {
                    if(!used.contains(neighbor) && !newOpen.contains(neighbor)) {
                        newOpen.add(neighbor);
                    }
                }
            }
            
            for(Point point : newOpen) {
                used.add(point);
                if(end.equals(point)) {
                    finished = true;
                    break;
                }
            }
            
            if(!finished && newOpen.isEmpty()) return null;
        }
        
        List<Point> path = new ArrayList<>();
        Point point      = used.get(used.size() - 1);
        
        while(point.prev != null) {
            path.add(0, point);
            point = point.prev;
        }
        
        return path;
    }
    
}