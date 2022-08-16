package wlTankGame3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//为了监听键盘事件，实现KeyListener
public class MyPanel extends JPanel implements KeyListener,Runnable{
    //定义我的坦克
    Hero hero = null;
    //定义敌人坦克
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 3;
    public MyPanel(){
        hero = new Hero(100,100);//初始化自己的坦克
        hero.setSpeed(4);
        //初始化敌人坦克
        for(int i=0;i<enemyTankSize;i++){
            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
            enemyTank.setDirect(2);


            Shot shot = new Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect(),enemyTank.getSpeed());
            enemyTank.shots.add(shot);
            //启动
            new Thread(shot).start();
            enemyTanks.add(enemyTank);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形，默认黑色

        //画出自己的坦克-封装到方法
        drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),0);

        //画出敌人的坦克
        for(int i=0;i<enemyTanks.size();i++){
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),1);
            //画出所有子弹
            for(int j=0;j<enemyTank.shots.size();j++)
            {
                Shot shot = enemyTank.shots.get(j);
                if(shot.isLive){
                    g.draw3DRect(shot.x,shot.y,10,10,false);
                }
                else {
                    enemyTank.shots.remove(shot);
                }
            }
        }
        //画出hero子弹
        if (hero.shot!=null&&hero.shot.isLive!=false){
          //  g.fill3DRect(hero.shot.x,hero.shot.y,1,1,false);
              g.draw3DRect(hero.shot.x,hero.shot.y,1,1,false);
        }

}
    public void drawTank(int x,int y,Graphics g,int direct,int type){
        switch (type){
            case 0://我们的坦克
                g.setColor(Color.orange);
                break;
            case 1://敌人的坦克
                g.setColor(Color.WHITE);
                break;
        }
        //访问坦克方向，来绘制坦克
        switch (direct){
            case 0://上
                g.fill3DRect(x,y,10,60,false);//画出坦克的左边轮子
                g.fill3DRect(x +30,y,10,60,false);//画出坦克的右边轮子
                g.fill3DRect(x +10,y + 10,20,40,false);//画出坦克盖子
                g.fillOval(x +10,y+20,20,20);//画出圆形盖子
                g.drawLine(x +20,y+30,x+20,y);
                break;
            case 1://右
                g.fill3DRect(x,y,60,10,false);//画出坦克的上边轮子
                g.fill3DRect(x ,y+30,60,10,false);//画出坦克的下边轮子
                g.fill3DRect(x +10,y + 10,40,20,false);//画出坦克盖子
                g.fillOval(x +20,y+10,20,20);//画出圆形盖子
                g.drawLine(x +30,y+20,x+60,y+20);
                break;
            case 2://下
                g.fill3DRect(x,y,10,60,false);//画出坦克的左边轮子
                g.fill3DRect(x +30,y,10,60,false);//画出坦克的右边轮子
                g.fill3DRect(x +10,y + 10,20,40,false);//画出坦克盖子
                g.fillOval(x +10,y+20,20,20);//画出圆形盖子
                g.drawLine(x +20,y+30,x+20,y+60);
                break;
            case 3://左
                g.fill3DRect(x,y,60,10,false);//画出坦克的上边轮子
                g.fill3DRect(x ,y+30,60,10,false);//画出坦克的下边轮子
                g.fill3DRect(x +10,y + 10,40,20,false);//画出坦克盖子
                g.fillOval(x +20,y+10,20,20);//画出圆形盖子
                g.drawLine(x +30,y+20,x,y+20);
                break;

            default:
                System.out.println("暂时没有处理");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理wsda键
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){
            hero.setDirect(0);
            hero.moveUp();
        } else if(e.getKeyCode()==KeyEvent.VK_D){
            hero.setDirect(1);
            hero.moveRight();
        } else if(e.getKeyCode()==KeyEvent.VK_S){
            hero.setDirect(2);
            hero.moveDown();
        }else if(e.getKeyCode()==KeyEvent.VK_A){
            hero.setDirect(3);
            hero.moveLeft();
        }
        this.repaint();

        if(e.getKeyCode() == KeyEvent.VK_J){
            hero.shotEnemyTank();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
    }
}
