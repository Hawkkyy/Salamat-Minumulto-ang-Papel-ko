package css123proj.salamatminumultoangpapelko.Panels.Levels.LevelTemplates;

import css123proj.salamatminumultoangpapelko.Models.*;
import css123proj.salamatminumultoangpapelko.Models.Menu.MenuButton;
import css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates.*;
import css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents.SwitchTool;
import css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents.SwitchToolListener;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class LevelUI extends JPanel implements SwitchToolListener {

    private Image levelImg;
    Icon inst1;
    TestPaperUI testPaper;
    AnswerSheet ansKey;
    Clock clock;
    CorrectionTape corTape;
    GreenBallpen ballpen;
    TestPaperStack papStack;
    MenuButton menu;

    JPopupMenu popMenu;
    JMenuItem resume, inst, cal, title, restart, quit;
    JSeparator sep1, sep2, sep3, sep4, sep5;

    // Base resolution you originally designed for (e.g., 1920 x 1080)
    private static final double BASE_WIDTH = 1920.0;
    private static final double BASE_HEIGHT = 1080.0;

    public LevelUI() {
        setLayout(null); // Keep absolute layout for free desk placement

        try {
            levelImg = ImageIO.read(getClass().getResource("/Art/Backgrounds/LevelImg.jpg"));
            if (levelImg != null) {
                inst1 = new ImageIcon(levelImg);
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        testPaper = new TestPaperUI();
        ansKey = new AnswerSheet();
        clock = new Clock();
        corTape = new CorrectionTape();
        ballpen = new GreenBallpen();
        papStack = new TestPaperStack();
        menu = new MenuButton();

        popMenu = new JPopupMenu("In-Game Menu");
        resume = new JMenuItem("Resume");
        inst = new JMenuItem("Instructions");
        cal = new JMenuItem("Calendar");
        title = new JMenuItem("Return to Title Screen");
        restart = new JMenuItem("Restart");
        quit = new JMenuItem("Quit");

        popMenu.add(resume);
        popMenu.add(new JSeparator());
        popMenu.add(inst);
        popMenu.add(new JSeparator());
        popMenu.add(cal);
        popMenu.add(new JSeparator());
        popMenu.add(title);
        popMenu.add(new JSeparator());
        popMenu.add(restart);
        popMenu.add(new JSeparator());
        popMenu.add(quit);

        menu.addActionListener(e -> {
            popMenu.show(menu, -(popMenu.getPreferredSize().width - menu.getWidth()), menu.getHeight());
        });

        ballpen.addSwitchToolListener(this);
        corTape.addSwitchToolListener(this);

        // Add them to the panel
        add(testPaper);
        add(ansKey);
        add(corTape);
        add(ballpen);
        add(papStack);
        add(menu);
        add(clock);

        // Recalculate bounds whenever the window resizes or runs on another laptop
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repositionElements();
            }
        });
    }

    private void repositionElements() {
        int w = getWidth();
        int h = getHeight();
        if (w == 0 || h == 0) return;

        double sx = w / BASE_WIDTH;
        double sy = h / BASE_HEIGHT;

        // Automatically scales your original coordinates: (x * sx, y * sy, w * sx, h * sy)
        testPaper.setBounds((int)(550 * sx), (int)(100 * sy), (int)(650 * sx), (int)(900 * sy));
        ansKey.setBounds((int)(0 * sx), (int)(100 * sy), (int)(550 * sx), (int)(1000 * sy));
        clock.setBounds((int)(1530 * sx), (int)(30 * sy), (int)(350 * sx), (int)(160 * sy));
        ballpen.setBounds((int)(1300 * sx), (int)(700 * sy), (int)(100 * sx), (int)(200 * sy));
        corTape.setBounds((int)(1400 * sx), (int)(700 * sy), (int)(100 * sx), (int)(200 * sy));
        papStack.setBounds((int)(1500 * sx), (int)(500 * sy), (int)(400 * sx), (int)(550 * sy));
        menu.setBounds((int)(1625 * sx), (int)(30 * sy), (int)(200 * sx), (int)(50 * sy));

        revalidate();
        repaint();
    }

    @Override
    public void onToolSelected(SwitchTool evt) {
        System.out.println("Switched to " + evt.getSelectedTool());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (levelImg != null) {
            g.drawImage(levelImg, 0, 0, getWidth(), getHeight(), this);
        }
    }



    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1093, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 827, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
