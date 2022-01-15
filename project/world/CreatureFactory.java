
package world;

import java.util.List;

import asciiPanel.AsciiPanel;

public class CreatureFactory {

    private World world;

    public CreatureFactory(World world) {
        this.world = world;
    }

    public Player newPlayer(List<String> messager) {
        Player player = new Player(this.world, (char)2, AsciiPanel.brightWhite, 20, 20, 5, 5, messager);
        world.addPlayer(player);
        return player;
    }

    
    public Monster newMonster() {
        Monster monster = new Monster(this.world, (char)2, AsciiPanel.red, 20, 20, 5, 5);
        world.addMonster(monster);
        new Thread(monster).start();
        return monster;
    }

}
