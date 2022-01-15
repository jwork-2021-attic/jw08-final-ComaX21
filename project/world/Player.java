package world;
import java.awt.Color;
import java.util.List;

public class Player extends Creature{
    public Player(World world, char glyph, Color color, int maxHP, int attack, int defense, int visionRadius,List<String> messager)
    {
        super(world, glyph, color, maxHP, attack, defense, visionRadius);
        this.messager = messager;
    }

    private List<String> messager;
    @Override 
    public void notify(String message, Object... params) {
        this.messager.add(String.format(message, params));
    }

}
