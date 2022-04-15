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
    
    int width;
    int height;
    int[][] cells;

    Maze(String filename) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/dev/theskidster/pathfinder/mazes/" + filename)));
            
            width  = Integer.parseInt(br.readLine());
            height = Integer.parseInt(br.readLine());
            
            cells = new int[height][width];
            
            for(int row = 0; row < height; row++) {
                String sLine = br.readLine();
                String[] sTokens = sLine.split(" ");
                
                for(int col = 0; col < width; col++) {                    
                    cells[row][col] = Integer.parseInt(sTokens[col]);
                }
            }
        } catch(IOException | NumberFormatException e) {
            System.err.println(e);
        }
    }
    
}