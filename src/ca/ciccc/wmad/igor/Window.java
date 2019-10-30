package ca.ciccc.wmad.igor;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ca.ciccc.wmad.igor.Config.WIDTH;

public class Window implements Runnable {

    JFrame frame;
    Box[][] boxes;

    @Override
    public void run() {
        initFrame();
        initBoxes();
        initTimer();
    }

    void initFrame() {
        frame = new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setSize(Config.SIZE * WIDTH + 25, Config.SIZE * Config.HEIGHT + 55 );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Game of Life");
    }

    void initBoxes() {
        boxes = new Box[WIDTH][Config.HEIGHT];
        for (int x = 0; x < WIDTH; x++)
            for (int y = 0; y < Config.HEIGHT; y++) {
                boxes[x][y] = new Box(x, y);
                frame.add(boxes[x][y]);
            }

        for (int x = 0; x < WIDTH; x++)
            for (int y = 0; y < Config.HEIGHT; y++)
                for (int sx = -1; sx <= +1; sx++)
                    for (int sy = -1; sy <= +1; sy++)
                        if (!(sx == 0 && sy == 0))
                            boxes[x][y].cell.addNear(
                                    boxes[(x + sx + WIDTH) % WIDTH]
                                            [(y + sy + Config.HEIGHT) % Config.HEIGHT].cell);

        for (int x = 10; x <= 15; x++) {
            boxes[x][10].cell.status = Status.LIVE;
            boxes[x][10].setColor();

        }
    }

    private void initTimer()
    {
        TimerListener tl = new TimerListener();
        Timer timer = new Timer(Config.SLEEPMS, tl);
        timer.start();
    }

    private class TimerListener implements ActionListener {
         boolean flop = false;
        @Override
        public void actionPerformed(ActionEvent e) {
            flop = !flop;
            for (int x = 0; x < WIDTH; x++)
                for (int y = 0; y < Config.HEIGHT; y++)
                {
                    if (flop)
                    boxes[x][y].step1();
                    else
                    boxes[x][y].step2();
                }
        }
    }
}
