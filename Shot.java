package wlTankGame3;

public class Shot implements Runnable{
    int x;
    int y;
    int direct;
    int speed = 2;
    boolean isLive = true;

    //构造器


    public Shot(int x, int y, int direct, int speed) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.speed = speed;
    }


    @Override
    public void run() {//射击行为
        while (true){

            //线程休眠
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //跟据方向改变x,y坐标
            switch (direct){
                case 0://上
                    y -= speed;
                    break;
                case  1://右
                    x += speed;
                    break;
                case  2://下
                    y += speed;
                    break;
                case  3://左
                    x -= speed;
                    break;
            }
            if (!(x>=0&&x<=1000&&y>=0&&y<=750)){
                isLive = false;
                break;
            }
        }


    }
}
