package player;


import units.Unit;
import units.UnitEnum;
import utils.AnimationState;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.raylib.Colors;
import com.raylib.Raylib.Color;
import static com.raylib.Raylib.DrawRectangle;
import com.raylib.Raylib;
import com.raylib.Raylib.Vector2;
import static com.raylib.Raylib.LoadTexture;

public class Player {
    private List<Unit> units = new ArrayList<>();
    private final Color color;

    private static final Map<String, Unit> UNIT_MAP = Map.of(
    "Soldier", new Unit(0,UnitEnum.SOLDIER,0,0,0)
    );

    public Player(Color color) {
        loadUnits();
        int i = 1;
        for (Unit unit : units) {
            unit.setLife(i);
            i++;
        }
        this.color = color;
    }

    public List<Unit> getUnits() {
        return units;
    }
    public Color getColor() {
        return color;
    }

    /*public void drawUnitsBase(List<Unit> units) {
        for (Unit unit : units) {
            unit.updateAnimation();
            Raylib.DrawTexture(unit.getTextureBase(), (int)unit.getPosition().x(), (int)unit.getPosition().y(), Colors.WHITE);
        }
    }

    public void drawUnitsColor(List<Unit> units) {
        for (Unit unit : units) {
            unit.updateAnimation();
            Raylib.DrawTexture(unit.getTextureColor(), (int)unit.getPosition().x(), (int)unit.getPosition().y(), getColor());
        }
    }*/

    public void drawUnits(List<Unit> units) {
        for (Unit unit : units) {
            unit.drawUnitBase();
            unit.drawUnitColor(getColor());
        }
    }


    public void loadUnits() {
        for(int i = 5; i<7;i++){
            Unit unit = new Unit(i+1, UnitEnum.SOLDIER, 100, i*32, i*32);
            loadTexturesBase(unit);
            loadTexturesBase(unit);
            unit.setCurrentState(AnimationState.IDLE_RIGHT); // por defecto
            units.add(unit);
        }

        /*Texture[] soldierFrames = new Texture[3];
        for (int i = 0; i < 3; i++) {
            soldierFrames[i] = LoadTexture("src/assets/sprites/soldier/soldier" + i + ".png");
        }

        Unit u1 = new Unit(1, UnitEnum.SOLDIER, 0, 64, 64);
        u1.setFrames(soldierFrames);
        units.add(u1);*/
        //units.add(new Unit(1,UnitEnum.SOLDIER,0,64,64));
        //units.add(new Unit(2,UnitEnum.SOLDIER,0,32,32));
    }

    public void loadTexturesBase(Unit unit) {
        for (AnimationState state : AnimationState.values()) {
            Raylib.Texture[] framesBase = new Raylib.Texture[3];
            Raylib.Texture[] framesColor = new Raylib.Texture[3];
            for (int i = 0; i < 3; i++) {
                framesBase[i] = LoadTexture("src/assets/sprites/" + unit.getUnitEnum().getName()
                        + "/" + state.name().toLowerCase()
                        + "/" + state.name().toLowerCase() +  "_" + i + ".png");
                framesColor[i] = LoadTexture("src/assets/sprites/" + unit.getUnitEnum().getName()
                        + "/" + state.name().toLowerCase()
                        + "/" + state.name().toLowerCase() +  "_" + i + "_G.png");
            }
            unit.setAnimationBase(state, framesBase);
            unit.setAnimationColor(state, framesColor);
        }
    }

    public Unit searchUnit(int x, int y) {
        for (Unit unit : units) {

            Raylib.Vector2 pos = new Vector2();
            pos.x(x);
            pos.y(y);

            if (unit.getPosition().x() == pos.x() && unit.getPosition().y() == pos.y()) {
                return unit;
            }
        }
        return null;
    }
}
