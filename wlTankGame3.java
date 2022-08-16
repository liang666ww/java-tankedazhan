package wlTankGame3;

import javax.swing.*;

public class wlTankGame03 extends JFrame {
    //定义一个MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {
        wlTankGame03 wwlTankGame01 = new wlTankGame03();

    }

    public wlTankGame03(){
        mp = new MyPanel();
        this.add(mp);
        Thread thread = new Thread(mp);
        thread.start();
        this.setSize(1000,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }
}
