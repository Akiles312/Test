package units;

import com.raylib.Colors;
import com.raylib.Raylib;
import com.raylib.Raylib.*;
import utils.AnimationEntity;
import utils.AnimationState;

import java.util.ArrayList;
import java.util.List;

import static com.raylib.Raylib.DrawRectangle;
import static com.raylib.Raylib.LoadTexture;

public class Unit extends AnimationEntity {
    private final int id;
    private final UnitEnum unitEnum;
    private int life;
    private Vector2 position;
    private Vector2 destination;
    private List<Vector2> posibleMovement = new ArrayList<>();
    private boolean isLastDirectionRight;


    public Unit(int id, UnitEnum unitEnum, int life, int x, int y) {
        this.id = id;
        this.unitEnum = unitEnum;
        this.life = life;
        this.position = new Vector2();
        this.position.x(x);
        this.position.y(y);
        this.destination = new Vector2();
        this.destination.x(x);
        this.destination.y(y);
        //this.texture = LoadTexture(unit.getTexturePath());
    }

    public int getId() {return id;}
    public UnitEnum getUnitEnum() {return unitEnum;}
    public int getLife() {return life;}
    public void setLife(int life) {
        this.life = life;
    }

    public Vector2 getPosition() {return position;}
    public void setPositionX(int x) {
        this.position.x(x);
    }
    public void setPositionY(int y) {
        this.position.y(y);
    }
    public void setPosition(int x, int y) {
        setPositionX(x);
        setPositionY(y);
    }

    public Vector2 getDestination() {return destination;}
    public void setDestinationX(int x) {
        this.destination.x(x);
    }
    public void setDestinationY(int y) {
        this.destination.y(y);
    }
    public void setDestination(int x, int y) {
        setDestinationX(x);
        setDestinationY(y);
    }

    public List<Vector2> getPosibleMovement() {
        return posibleMovement;
    }
    public void setPosibleMovement(List<Vector2> posibleMovement) {
        this.posibleMovement = posibleMovement;
    }

    //public int getY() {return y;}
    //public void setY(int y) {this.y = y;}
    //public int getDesX() {return desX;}
    //public void setDesX(int desX) {this.desX = desX;}
    //public int getDesY() {return desY;}
    //public void setDesY(int desY) {this.desY = desY;}
    public void drawUnitBase() {
        updateAnimation();
        Raylib.DrawTexture(getTextureBase(), (int)getPosition().x(), (int)getPosition().y(), Colors.WHITE);
    }

    public void drawUnitColor(Color color) {
        updateAnimation();
        Raylib.DrawTexture(getTextureColor(), (int)getPosition().x(), (int)getPosition().y(), color);
    }

    public boolean move(int x, int y) {
        boolean finish = false;
        setDestination(x,y);

        if (getPosition().x() < getDestination().x()) {
            setPositionX( (int)getPosition().x() + 1 );
            setCurrentState(AnimationState.RIGHT);
            this.isLastDirectionRight = true;
            return finish;
        }

        if (getPosition().y() < getDestination().y()) {
            setPositionY( (int)getPosition().y() + 1 );
            setCurrentState(AnimationState.DOWN);
            return finish;
        }

        if (getPosition().x() > getDestination().x()) {
            setPositionX( (int)getPosition().x() - 1 );
            setCurrentState(AnimationState.LEFT);
            this.isLastDirectionRight = false;
            return finish;
        }

        if (getPosition().y() > getDestination().y()) {
            setPositionY( (int)getPosition().y() - 1 );
            setCurrentState(AnimationState.UP);
            return finish;
        }

        if(getPosition().x() == getDestination().x() && getPosition().y() == getDestination().y()) {
            finish = true;
            //checkArea();
            if(getCurrentState() == AnimationState.RIGHT){
                setCurrentState(AnimationState.IDLE_RIGHT);
            }
            if(getCurrentState() == AnimationState.LEFT){
                setCurrentState(AnimationState.IDLE_LEFT);
            }
            if(getCurrentState() == AnimationState.UP || getCurrentState() == AnimationState.DOWN){
                setCurrentState(isLastDirectionRight ? AnimationState.IDLE_RIGHT : AnimationState.IDLE_LEFT);
            }
        }
        return finish;

        /*if (getX() > getDesX()) {
            setX(getX() - 1 );
            return finish;
        }*/
    }

    public void attack(int x, int y) {}
    public void destroy() {}

    @Override
    public String toString() {
        return "Unit [ id: "+ id + " life: " + life + " " + unitEnum.toString() + "]";
    }

    public void drawArea(Color color) {
        for(Vector2 v : posibleMovement) {
            if(v != null) {
                //DrawRectangle((int)v.x(),(int)v.y(),32,32,color);
            }
        }
    }

    public void checkArea() {

        int centroX = (int)getPosition().x();
        int centroY = (int)getPosition().y();
        int radio = unitEnum.getMobility();
        //posibleMovement.clear();
        //List<Vector2> todosLosVectores = new ArrayList<>();
        // Recorremos un cuadrado de tamaño 2*radio centrado en (centroX, centroY)
        /*
        for (int i = centroX - radio; i <= centroX + radio; i++) {
            for (int j = centroY - radio; j <= centroY + radio; j++) {
                double distancia = Math.sqrt(Math.pow(i - centroX, 2) + Math.pow(j - centroY, 2));
                if (distancia <= radio) {
                    Vector2 point = new Vector2();
                    point.x(i);
                    point.y(j);
                    todosLosVectores.add(point);
                }
            }
        }

        for (int i = 0; i < todosLosVectores.size(); i += 32) {
            int fin = Math.min(i + 32, todosLosVectores.size());
            posibleMovement.add(todosLosVectores.subList(i, fin));
        }

        for(int i=-radio;i<radio+radio;i++){
            for(int j=-radio;j<radio+radio;j++){
                double magnitud = Math.sqrt(Math.pow(i,2.0)+Math.pow(j,2.0));
                if((magnitud < radio || magnitud == radio) && magnitud != 0){

                    int x = i*32+centroX;
                    int y = j*32+centroY;
                    DrawRectangle(x,y,32,32,Colors.RED);
                    Vector2 v = new Vector2();
                    v.x(x);
                    v.y(y);
                    todosLosVectores.add(v);
                }
            }
        }
        */

        for(int i=-radio;i<radio+radio;i++){
            for(int j=-radio;j<radio+radio;j++){
                int magnitud = Math.abs(i) + Math.abs(j);
                if((magnitud < radio || magnitud == radio) && magnitud != 0){
                    //Vector2 v = new Vector2();
                    //int x = i*32+centroX;
                    //int y = j*32+centroY;
                    //v.x(x);
                    //v.y(y);
                    //posibleMovement.add(v);
                }
            }
        }
    }
}
