package apocalypse.mechanics.readers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;

public class TileMapReader {

    public int[][] readMap(){
        int[][]map = null;
        try {
            List<String> nums = Files.readAllLines(Paths.get("map.txt"));
            map = new int[nums.size()][nums.get(0).length()];

            for (int i = 0;i<nums.size();i++){
                for (int k = 0;k<nums.get(0).length();k++){
                    map[i][k] = Integer.parseInt(String.valueOf(nums.get(i).charAt(k)));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return map;
    }

}
