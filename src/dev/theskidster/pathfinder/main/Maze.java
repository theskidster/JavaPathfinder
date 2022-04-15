package dev.theskidster.pathfinder.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static javax.swing.Spring.width;

/**
 * Apr 15, 2022
 */

/**
 * @author J Hoffman
 * @since  
 */
class Maze {
    
    Point start;
    Point end;
    
    int width;
    int height;
    int[][] cells;

    Maze(String filename) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/dev/theskidster/pathfinder/mazes/" + filename)));
            
            width  = Integer.parseInt(br.readLine());
            height = Integer.parseInt(br.readLine());
            
            cells = new int[width][height];
            
            for(int y = 0; y < height; y++) {
                String sLine = br.readLine();
                String[] sTokens = sLine.split(" ");
                
                for(int x = 0; x < width; x++) {
                    cells[x][y] = Integer.parseInt(sTokens[x]);
                    
                    if(cells[x][y] == 2)      start = new Point(x, y, null);
                    else if(cells[x][y] == 3) end = new Point(x, y, null);
                }
            }
        } catch(IOException | NumberFormatException e) {
            System.err.println(e);
        }
    }
    
}