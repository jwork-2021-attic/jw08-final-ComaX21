package world;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class World {

    private Tile[][] tiles;
    private int height;
    private int width;
    private ArrayList<Creature> creatures;
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;


    //WorldBuilder.build()调用
    public World(Tile[][] tiles) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
        this.creatures = new ArrayList<>();
        this.players = new ArrayList<>();
        this.monsters = new ArrayList<>();
    }
    public Tile tile(int x, int y) {
        return tiles[x][y];
    }

    public char glyph(int x, int y) {
        return tiles[x][y].glyph();
    }

    public Color color(int x, int y) {
        return tiles[x][y].color();
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public void dig(int x, int y) {
        if (this.tile(x, y).isDiggable()) {
            this.tiles[x][y] = Tile.FLOOR;
        }
    }

    public void addPlayer(Player player) {
        this.players.add(player);
        this.addAtEmptyLocation(player);
    }

    public void addMonster(Monster monster) {
        this.monsters.add(monster);
        this.addAtEmptyLocation(monster);
    }

    private void addAtEmptyLocation(Creature creature) {
        int x;
        int y;
        //寻找一个没有生物的FLOOR
        do {
            x = (int) (Math.random() * this.width);
            y = (int) (Math.random() * this.height);
        } while (!tile(x, y).isGround() || this.creature(x, y) != null);
        creature.setX(x);
        creature.setY(y);
        this.creatures.add(creature);
    }

    //(x,y)位置上的生物，没有返回null
    public Creature creature(int x, int y) {
        for (Creature c : this.creatures) {
            if (c.x() == x && c.y() == y) {
                return c;
            }
        }
        return null;
    }

    public boolean haveCreature()
    {
        return !this.creatures.isEmpty();
    }
    public boolean haveAlivePlayer()
    {
        return this.players.get(0).hp() > 0;
    }
    public boolean haveMonster()
    {
        return !this.monsters.isEmpty();
    }
    
    public List<Creature> getCreatures() {
        return this.creatures;
    }

    public Player getPlayer()
    {   
        return this.players.get(0);
    }
    
    public void remove(Creature target) {
        this.creatures.remove(target);
        this.monsters.remove(target);
    }

}
