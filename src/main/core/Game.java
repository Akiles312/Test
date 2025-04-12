package core;

import com.raylib.Colors;
import map.Board;
import player.Player;
import units.Unit;
import utils.ColorAux;


import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;
import static com.raylib.Raylib.GetKeyPressed;


public class Game{
    private final int screenWidth = 800;
    private final int screenHeight = 480;
    private final String gameTitle = "Advance Strategy";
    private final int fps = 60;
    private final String mapPath = "src\\assets\\Map.CSV";
    private final ColorAux areaColor = new ColorAux(102,191,225,10);


    public void main() {
        /*int screenWidth = 800;
        int screenHeight = 450;

        InitWindow(screenWidth, screenHeight, "Advance Strategy");
        SetTargetFPS(60);

        Texture scarfy = LoadTexture("src\\assets\\sprites\\Grass32x32.png");

        int frameWidth = scarfy.width()/6;
        int frameHeight = scarfy.height();

        System.out.println("Frame Width: " + frameWidth);
        System.out.println("Frame Height: " + frameHeight);

        // Source rectangle (part of the texture to use for drawing)
        Rectangle sourceRec = new Rectangle();
        sourceRec.x(0);
        sourceRec.y(0);
        sourceRec.width(frameWidth);
        sourceRec.height(frameHeight);

        // Destination rectangle (screen rectangle where drawing part of texture)
        Rectangle destRec = new Rectangle();
        sourceRec.x((float) (screenWidth/2.0));
        sourceRec.y((float) (screenHeight/2.0));
        sourceRec.width((float) (frameWidth*2.0));
        sourceRec.height((float) (frameHeight*2.0));

        // Origin of the texture (rotation/scale point), it's relative to destination rectangle size
        Vector2 origin = new Vector2();
        origin.x(frameWidth);
        origin.y(frameHeight);

        System.out.println("Origin: " + origin.x() +" "+ origin.y());


        while (!WindowShouldClose()) {
            //Update
            BeginDrawing();
            ClearBackground(RAYWHITE);
            //DrawText("Ventana de Configuraciones", 250, 150, 20, BLACK);


            //DrawTexturePro(scarfy, sourceRec, destRec, origin, 0, BLACK);
            //DrawTexture(scarfy,0,0,WHITE);


            //DrawRectangle(0,0,frameWidth,frameHeight,BLACK);
            //DrawRectangle((int) (screenWidth/2.0), (int) (screenHeight/2.0), (int) (frameWidth*2.0), (int) (frameHeight*2.0), BLACK);

            DrawLine((int)destRec.x(), 0, (int)destRec.x(), screenHeight, GRAY);
            DrawLine(0, (int)destRec.y(), screenWidth, (int)destRec.y(), GRAY);

            EndDrawing();
        }

        CloseWindow();*/
        CreateWindow();

        startGame();
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }
    public int getScreenHeight() {
        return this.screenHeight;
    }
    public int getFps() {
        return this.fps;
    }
    public String getMapPath() {
        return this.mapPath;
    }

    private void CreateWindow() {
        InitWindow(screenWidth, screenHeight, gameTitle);
        //SetWindowIcon(Image image);  //añadir icono a la aplicación
        SetConfigFlags(FLAG_MSAA_4X_HINT);
        InitAudioDevice();

        SetTargetFPS(60);
    }

    private void InitGame(){

    }

    private void startGame(){

        int framesCounter = 0;

        Board board = new Board(this);
        Player player = new Player(BLUE);
        Player enemigo = new Player(RED);


        boolean pause = false;

        boolean mouse0 = false;
        boolean mouse1 = false;
        Vector2 positionMouse0 = new Vector2();
        Vector2 positionMouse1 = new Vector2();
        Unit unit0 = player.getUnits().getFirst();
        Unit unit1 = player.getUnits().getLast();
        Unit unitSelect = null;

        BeginDrawing();
        Music music = LoadMusicStream("src\\assets\\music.mp3");
        PlayAudioStream(music.stream());



        while (!WindowShouldClose()){

            int key = GetKeyPressed();
            if(key != 0){System.out.println(key);}
            if (key == 80){
                pause = !pause;
                key = 0;
            }

            ClearBackground(RAYWHITE);
            UpdateMusicStream(music);

            if(!pause) {

                if (IsMouseButtonPressed(0)) {
                    mouse0 = true;
                    positionMouse0 = GetMousePosition();
                    unitSelect = player.searchUnit(normaliceCord((int)positionMouse0.x()),normaliceCord((int)positionMouse0.y()));
                    /*System.out.println("Position0: "+unit0.getPosition().x() + " " + unit0.getPosition().y());
                    System.out.println("Destination0: "+unit0.getDestination().x() + " " + unit0.getDestination().y());
                    System.out.println("Position1: "+unit1.getPosition().x() + " " + unit1.getPosition().y());
                    System.out.println("Destination1: "+unit1.getDestination().x() + " " + unit1.getDestination().y());
                    System.out.println("positionMouse0: "+positionMouse0.x() + " " + positionMouse0.y());
                    System.out.println("positionMouse1: "+positionMouse1.x() + " " + positionMouse1.y());
                     */
                }

                if (IsMouseButtonPressed(1)) {

                    unitSelect = null;
                    /*System.out.println("Position0: "+unit0.getPosition().x() + " " + unit0.getPosition().y());
                    System.out.println("Destination0: "+unit0.getDestination().x() + " " + unit0.getDestination().y());
                    System.out.println("Position1: "+unit1.getPosition().x() + " " + unit1.getPosition().y());
                    System.out.println("Destination1: "+unit1.getDestination().x() + " " + unit1.getDestination().y());
                    System.out.println("positionMouse0: "+positionMouse0.x() + " " + positionMouse0.y());
                    System.out.println("positionMouse1: "+positionMouse1.x() + " " + positionMouse1.y());
                     */
                }


                /*
                if (mouse0 && framesCounter % 60 == 0) {
                    mouse0 = !unit0.move(normaliceCord((int) positionMouse0.x()), normaliceCord((int) positionMouse0.y()));
                }

                if (IsMouseButtonPressed(1)) {
                    mouse1 = true;
                    positionMouse1 = GetMousePosition();
                }
                if (mouse1 && framesCounter % 60 == 0) {
                    mouse1 = !unit1.move(normaliceCord((int) positionMouse1.x()), normaliceCord((int) positionMouse1.y()));
                }
                */

                //resto
                    //Vector2 position = GetMousePosition();
                    //player.getUnits().getFirst().move((int)position.y(),(int)position.x());
                    //System.out.println(board.getMap()[(int)position.y()/32][(int)position.x()/32].toString());
                    //System.out.println("x: " + (int) position.y() + " y: " + (int) position.x());
                    //System.out.println("x: " + normaliceCord((int) position.y()) + " y: " + normaliceCord((int) position.x()));
                    //System.out.println(player.getUnits().getFirst().getPosition());
                    //System.out.println("x: "+ (int)position.y()/32 + " y: " + (int)position.x()/32);
                    //System.out.println("x: "+ ((int)position.y()/32)*32 + " y: " + ((int)position.x()/32)*32);
                    //System.out.println(player.searchUnit(((int)position.y()/32)*32, ((int)position.x()/32)*32));
                    //board.setMap(TerrainEnum.FOREST.getName(), (int)position.y()/32,(int)position.x()/32);


                framesCounter++;
            }
            DrawFrame(board, player, enemigo, unitSelect);
        }
        UnloadMusicStream(music);
        CloseAudioDevice();
        CloseWindow();
    }

    private void UpdateFrame(){

    }

    private void DrawFrame(Board board, Player player, Player enemigo, Unit unitSelect){
        board.drawMap();
        player.drawUnits(player.getUnits());

        if (unitSelect != null){
            unitSelect.drawArea(areaColor.toRaylibColor());
        }

        EndDrawing();
    }

    private int normaliceCord(int cord){
        return (cord/32)*32;
    }
}
