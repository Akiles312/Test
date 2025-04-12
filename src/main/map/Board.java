package map;

import core.Game;
import java.io.*;
import java.util.*;

import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.DrawTexture;

public class Board {
    private Terrain[][] map;
    private final Game game;
    private static final Map<String, Terrain> TERRAIN_MAP = Map.of(
        "Grass", new Terrain(TerrainEnum.GRASS),
        "Forest", new Terrain(TerrainEnum.FOREST),
        "Mountain", new Terrain(TerrainEnum.MOUNTAIN)
    );

    public Board(Game game) {
        this.game = game;
        loadMap();
    }

    public Terrain[][] getMap() {
        return map;
    }

    public void setMap(String terrain,int x,int y) {
        map[x][y] = TERRAIN_MAP.get(terrain);
    }

    public void loadMap(){
        List<String[]> data = leerCSV(game.getMapPath());
        map = dumpMap(data);
    }

    public Terrain[][] dumpMap(List<String[]> csv){

        if(csv.isEmpty()) return new Terrain[0][0];

        int rows = csv.size();
        int cols = csv.getFirst().length;
        Terrain[][] data = new Terrain[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = TERRAIN_MAP.getOrDefault(csv.get(i)[j], new Terrain(TerrainEnum.GRASS));
            }
        }


        return data;
    }

    public List<String[]> leerCSV(String filePath) {
        List<String[]> dataList  = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t"); // Separador por comas
                dataList .add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }

    public void drawMap(){

        for(int j = 0; j <= map.length-1; j++) {
            for(int i = 0; i <= map[j].length-1; i++) {
                DrawTexture(map[j][i].getTexture(), i*32, j*32, WHITE);
            }
        }

    }


}
