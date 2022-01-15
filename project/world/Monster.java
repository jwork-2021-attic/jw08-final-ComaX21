package world;
import java.awt.Color;



public class Monster extends Creature implements Runnable{

    public Monster(World world, char glyph, Color color, int maxHP, int attack, int defense, int visionRadius)
    {
        super(world, glyph, color, maxHP, attack, defense, visionRadius);
    }
    
    private void moveToPlayer()
    {
        Player player = this.world.getPlayer();
        int px = player.x();
        int py = player.y();
        if(this.canSee(px, py)){
            if(this.x - px < 0)
                this.move(1, 0);
            else if(this.x - px > 0)
                this.move(-1, 0);
            if(this.y - py < 0)
                this.move(0, 1);
            else if(this.y - py > 0)
                this.move(0, -1);
        }
    }

    public void run() {
        while(true)
        {
            
            try {
                this.moveToPlayer();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
            
        }
    }
}
