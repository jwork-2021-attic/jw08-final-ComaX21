
package world;

import java.awt.Color;

public class Creature {
    protected World world;
    protected char glyph;
    protected Color color;
    protected int maxHP;
    protected int hp;
    protected int attackValue;
    protected int defenseValue;
    protected int visionRadius;

    public Creature(World world, char glyph, Color color, int maxHP, int attack, int defense, int visionRadius) {
        this.world = world;
        this.glyph = glyph;
        this.color = color;
        this.maxHP = maxHP;
        this.hp = maxHP;
        this.attackValue = attack;
        this.defenseValue = defense;
        this.visionRadius = visionRadius;
    }
    
    protected int x;
    protected int y;

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }



    public void modifyHP(int amount) {
        this.hp += amount;

        if (this.hp < 1) {
            world.remove(this);
        }
    }

    public void attack(Creature other) {
        int damage = Math.max(0, this.attackValue() - other.defenseValue());
        damage = (int) (Math.random() * damage) + 1;

        other.modifyHP(-damage);

        this.notify("You attack the '%s' for %d damage.", other.glyph, damage);
        other.notify("The '%s' attacks you for %d damage.", glyph, damage);
    }

    public void notify(String message, Object... params) {
    }


    
    
    public boolean canSee(int wx, int wy) {
        if ((this.x() - wx) * (this.x() - wx) + (this.y() - wy) * (this.y() - wy) > this.visionRadius()
                * this.visionRadius()) {
            return false;
        }
        
        return true;
    }

    public Tile tile(int wx, int wy) {
        return world.tile(wx, wy);
    }

    public void dig(int wx, int wy) {
        world.dig(wx, wy);
    }

    public void move(int mx, int my) {
        if(this.x + mx < 0 || this.x + mx >= this.world.width() || this.y + my < 0 || this.y + my >= this.world.height())
        {
            this.notify("Reach the end of the world!");
            return;
        }

        Creature other = world.creature(this.x + mx, this.y + my);
        if (other == null) {
            this.Enter(x + mx, y + my, world.tile(x + mx, y + my));
        } else {
            attack(other);
        }
    }
    public void Enter(int x, int y, Tile tile) {
        if (tile.isGround()) {
            this.setX(x);
            this.setY(y);
        } else if (tile.isDiggable()) {
            this.dig(x, y);
        }
    }
    

    public int x() {
        return x;
    }
    public int y() {
        return y;
    }
    public char glyph() {
        return this.glyph;
    }
    public Color color() {
        return this.color;
    }
    public int maxHP() {
        return this.maxHP;
    }
    public int hp() {
        return this.hp;
    }
    public int attackValue() {
        return this.attackValue;
    }
    public int defenseValue() {
        return this.defenseValue;
    }
    public int visionRadius() {
        return this.visionRadius;
    }
    
}
