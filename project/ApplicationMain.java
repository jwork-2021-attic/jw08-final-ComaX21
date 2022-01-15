
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;
import screen.Screen;


public class ApplicationMain extends JFrame {

    private AsciiPanel terminal;
    private Screen screen;

    public ApplicationMain() {
        super();

        //新建终端窗口
        terminal = new AsciiPanel(70, 45, AsciiFont.TALRYTH_15_15);
        this.add(terminal);
        this.pack();

        screen = new Screen();
        this.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e) {
                //按键更新
                screen = screen.respondToUserInput(e);
                //终端显示
                repaint();
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //终端显示
    @Override
    public void repaint() {
        terminal.clear();
        screen.displayOn(terminal);
        super.repaint();
    }


    public static void main(String[] args) throws InterruptedException {
        ApplicationMain app = new ApplicationMain();
        while(true)
        {
            app.repaint();
            Thread.sleep(1000);
        }
    }

}
