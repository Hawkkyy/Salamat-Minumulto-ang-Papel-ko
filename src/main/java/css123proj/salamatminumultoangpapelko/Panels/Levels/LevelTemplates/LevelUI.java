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

    private static final double BASE_WIDTH = 1920.0;
    private static final double BASE_HEIGHT = 1080.0;

    public LevelUI(int level) {
        setLayout(null); 

        try {
            levelImg = ImageIO.read(getClass().getResource("/Art/Backgrounds/LevelImg.jpg"));
            if (levelImg != null) {
                inst1 = new ImageIcon(levelImg);
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        testPaper = new TestPaperUI(level);
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

       //(x * sx, y * sy, w * sx, h * sy)
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
    String t = String.valueOf(evt.getSelectedTool()).toLowerCase();
    System.out.println("Switched to " + t);

    if (t.contains("pen")) {
        QuestionUI.currentTool = QuestionUI.Tool.PEN;
        setCursor(makeCursor(new Color(0, 160, 0)));      // green dot
    } else if (t.contains("tape")) {
        QuestionUI.currentTool = QuestionUI.Tool.TAPE;
        setCursor(makeCursor(new Color(240, 240, 230)));  // pale dot
    } else {
        QuestionUI.currentTool = QuestionUI.Tool.NONE;
        setCursor(Cursor.getDefaultCursor());
    }
}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (levelImg != null) {
            g.drawImage(levelImg, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    private Cursor makeCursor(Color color) {
        int size = 32;
        java.awt.image.BufferedImage img =
            new java.awt.image.BufferedImage(size, size, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.fillOval(4, 4, size - 8, size - 8);
        g2.setColor(Color.BLACK);
        g2.drawOval(4, 4, size - 8, size - 8);
        g2.dispose();
        
        return Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(size / 2, size / 2), "tool");
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
