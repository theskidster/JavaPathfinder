package dev.theskidster.pathfinder.main;

import java.util.Objects;

/**
 * Apr 15, 2022
 */

/**
 * @author J Hoffman
 * @since  
 */
class Point {
    
    int x;
    int y;
    Point prev;

    Point(int x, int y, Point prev) {
        this.x    = x;
        this.y    = y;
        this.prev = prev;
    }
    
    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        
        final Point other = (Point) obj;
        return (this.x == other.x && this.y == other.y);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
    Point offset(int objX, int objY) {
        return new Point(x + objX, y + objY, this);
    }
    
}