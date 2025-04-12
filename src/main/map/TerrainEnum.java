package map;

public enum TerrainEnum {
    GRASS("Grass", 0, 1),
    FOREST("Forest", 1, 2),
    MOUNTAIN("Mountain", 2, 3);

    private final String name;
    private boolean isOccupied;
    private final String path = "src/assets/tiles/";
    private final int defenseLevel;
    private final int movementCost;

    TerrainEnum(String name, int movementCost, int defenseBonus) {
        this.name = name;
        this.isOccupied = false;
        this.defenseLevel = defenseBonus;
        this.movementCost = movementCost;
    }

    public String getName() { return name; }
    public boolean getIsOccupied() { return isOccupied; }
    public String getPath() { return path; }
    public int getDefenseLevel() { return defenseLevel; }
    public int getMovementCost() { return movementCost; }

    @Override
    public String toString() {
        return name +
                "(isOccupied: " + isOccupied + ")" +
                "(path: " + path + ")" +
                "(defenseLevel: " + defenseLevel + ")" +
                "(mobilityLevel: " + movementCost + ")";
    }
}
