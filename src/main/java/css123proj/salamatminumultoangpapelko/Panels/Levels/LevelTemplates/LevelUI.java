package css123proj.salamatminumultoangpapelko.Panels.Levels.LevelTemplates;

import css123proj.salamatminumultoangpapelko.Models.*;
import css123proj.salamatminumultoangpapelko.Panels.Levels.Instructions;
import css123proj.salamatminumultoangpapelko.Models.Menu.MenuButton;
import css123proj.salamatminumultoangpapelko.Models.TestPaperTemplates.*;
import css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents.SwitchTool;
import css123proj.salamatminumultoangpapelko.Panels.Levels.CustomEvents.SwitchToolListener;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import javax.swing.*;

public class LevelUI extends JPanel implements SwitchToolListener {

    private Image levelImg;
    TestPaperUI testPaper;
    AnswerSheet ansKey;
    Clock clock;
    CorrectionTape corTape;
    GreenBallpen ballpen;
    TestPaperStack papStack;
    MenuButton menu;

    JPopupMenu popMenu;
    JMenuItem resume, inst, cal, title, restart, quit;

    private static final double BASE_WIDTH = 1920.0;
    private static final double BASE_HEIGHT = 1080.0;
    
    private static final int LEVEL_SECONDS = 300;   // 5 minutes per level

private boolean ansExpanded = false;   // answer sheet is enlarged in the middle of the screen
private boolean levelStarted = false;  // true once the player pressed Confirm and the clock is running
private final JButton confirmBtn = new JButton("Confirm");
private final JPanel dim = new JPanel() {
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0, 170));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
};

    private final int level;
    private final Consumer<String> goTo;

    public LevelUI(int level, Consumer<String> goTo) {
        setLayout(null);
        
        this.level = level;
        this.goTo = goTo;

        try {
            levelImg = ImageIO.read(getClass().getResource("/Art/Backgrounds/LevelImg.jpg"));
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
        popMenu.addSeparator();
        popMenu.add(inst);
        popMenu.addSeparator();
        popMenu.add(cal);
        popMenu.addSeparator();
        popMenu.add(title);
        popMenu.addSeparator();
        popMenu.add(restart);
        popMenu.addSeparator();
        popMenu.add(quit);

        menu.addActionListener(e -> {
            popMenu.show(menu, -(popMenu.getPreferredSize().width - menu.getWidth()), menu.getHeight());
        });
        
        popMenu.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
    public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) { clock.pause(); }
    public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) { clock.resume(); }
    public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) { clock.resume(); }
});

resume.addActionListener(e -> { });
inst.addActionListener(e -> {
    clock.pause();
    Instructions.showInstructions();
    clock.resume();
});
cal.addActionListener(e -> goTo.accept("Calendar"));
title.addActionListener(e -> goTo.accept("Title Screen"));
restart.addActionListener(e -> resetLevel());
quit.addActionListener(e -> {
    clock.pause();
    int ans = JOptionPane.showConfirmDialog(this, "Quit the game?", "Quit",
            JOptionPane.YES_NO_OPTION);
    if (ans == JOptionPane.YES_OPTION) System.exit(0);
    clock.resume();
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
        
        dim.setOpaque(false);
dim.setVisible(false);
dim.addMouseListener(new MouseAdapter() {      // swallows clicks so tools underneath can't be used
    @Override
    public void mouseClicked(MouseEvent e) {
        if (ansExpanded && levelStarted) setAnswerSheetOpen(false);   // click outside = close
    }
});
confirmBtn.setVisible(false);
confirmBtn.addActionListener(e -> confirmAnswerSheet());
add(dim);
add(confirmBtn);

// stacking order (top to bottom): button, answer sheet, dim, everything else
setComponentZOrder(dim, 0);
setComponentZOrder(ansKey, 0);
setComponentZOrder(confirmBtn, 0);

ansKey.addMouseListener(new MouseAdapter() {   // click the small sheet on the left to enlarge it
    @Override
    public void mouseClicked(MouseEvent e) {
        if (levelStarted && !ansExpanded) setAnswerSheetOpen(true);
    }
});

        ansKey.setKey(testPaper.getSet(), testPaper.getAnswerKey());

        papStack.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!levelStarted || ansExpanded) return;
        if (testPaper.nextPaper()) {          // does nothing while a paper is still on the desk
            ansKey.setKey(testPaper.getSet(), testPaper.getAnswerKey());
            updateStack();
        }
    }
});

        testPaper.setOnFinished((total, levelOver) -> {
    if (!levelOver) {
    updateStack();
    return;
}
    
                
    clock.stop();
    GameResult.level = level;
    GameResult.score = total;
    GameResult.maxScore = testPaper.getMaxScore();
    GameResult.timedOut = false;
    goTo.accept("Score");
});

clock.setOnTimeUp(() -> {
    GameResult.level = level;
    GameResult.score = testPaper.getTotalScore();
    GameResult.maxScore = testPaper.getMaxScore();
    GameResult.timedOut = true;
    goTo.accept("Score");
});

addHierarchyListener(e -> {
    if ((e.getChangeFlags() & java.awt.event.HierarchyEvent.SHOWING_CHANGED) != 0) {
        if (isShowing()) resetLevel(); else clock.stop();
    }
});

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repositionElements();
            }
        });
    }
    
    private void resetLevel() {
    testPaper.reset();
    ansKey.setKey(testPaper.getSet(), testPaper.getAnswerKey());
    setHeldTool(QuestionUI.Tool.NONE);
    updateStack();

    levelStarted = false;
    clock.prepare(LEVEL_SECONDS);   // sits at 11:00 PM until Confirm
    setAnswerSheetOpen(true);
}
    
    private void updateStack() {
    papStack.setCount(testPaper.getPapersLeftInStack(), testPaper.getPapersPerLevel());
}

private void setAnswerSheetOpen(boolean open) {
    ansExpanded = open;
    ansKey.setExpanded(open);
    dim.setVisible(open);
    confirmBtn.setVisible(open);
    confirmBtn.setText(levelStarted ? "Close" : "Confirm");
    repositionElements();
}

private void confirmAnswerSheet() {
    if (!levelStarted) {
        levelStarted = true;
        clock.begin();          // timer only starts after the player has read the key
    }
    setAnswerSheetOpen(false);
}
    

    private void repositionElements() {
        int w = getWidth();
        int h = getHeight();
        if (w == 0 || h == 0) return;

        double sx = w / BASE_WIDTH;
        double sy = h / BASE_HEIGHT;

        testPaper.setBounds((int)(550 * sx), (int)(100 * sy), (int)(650 * sx), (int)(900 * sy));
        dim.setBounds(0, 0, w, h);
if (ansExpanded) {
    int eh = (int) (h * 0.9);
    int ew = (int) (eh * 650.0 / 900.0);
    ansKey.setBounds((w - ew) / 2, (h - eh) / 2, ew, eh);
    confirmBtn.setBounds(w / 2 - 90, (h + eh) / 2 - 70, 180, 44);
} else {
    ansKey.setBounds((int)(0 * sx), (int)(100 * sy), (int)(550 * sx), (int)(950 * sy));
}
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
    QuestionUI.Tool clicked = t.contains("tape") ? QuestionUI.Tool.TAPE
                            : t.contains("pen")  ? QuestionUI.Tool.PEN
                            : QuestionUI.Tool.NONE;
    if (clicked == QuestionUI.Tool.NONE) return;

    if (QuestionUI.currentTool == clicked) {            // already holding it -> put it back
        setHeldTool(QuestionUI.Tool.NONE);
    } else if (QuestionUI.currentTool != QuestionUI.Tool.NONE) {   // holding the other one
        clock.pause();
        JOptionPane.showMessageDialog(this, "I can only hold one item at a time!\n- Ghost",
                "Ghost", JOptionPane.WARNING_MESSAGE);
        clock.resume();
    } else {
        setHeldTool(clicked);
    }
}

private void setHeldTool(QuestionUI.Tool tool) {
    QuestionUI.currentTool = tool;
    ballpen.setHeld(tool == QuestionUI.Tool.PEN);
    corTape.setHeld(tool == QuestionUI.Tool.TAPE);

    switch (tool) {
        case PEN:  setCursor(makeToolCursor(ballpen.getImage(), new Color(0, 160, 0))); break;
        case TAPE: setCursor(makeToolCursor(corTape.getImage(), new Color(240, 240, 230))); break;
        default:   setCursor(Cursor.getDefaultCursor());
    }
}

/** Builds a cursor from the tool's own image, falling back to a colored dot if the image is missing. */
private Cursor makeToolCursor(Image toolImg, Color fallback) {
    Dimension d = Toolkit.getDefaultToolkit().getBestCursorSize(48, 48);
    int size = Math.max(32, Math.min(d.width, d.height));
    java.awt.image.BufferedImage img =
        new java.awt.image.BufferedImage(size, size, java.awt.image.BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = img.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    if (toolImg != null) {
        int iw = toolImg.getWidth(null), ih = toolImg.getHeight(null);
        double scale = Math.min((double) size / iw, (double) size / ih);
        int w = (int) (iw * scale), h = (int) (ih * scale);
        g2.drawImage(toolImg, (size - w) / 2, (size - h) / 2, w, h, null);
    } else {
        g2.setColor(fallback);
        g2.fillOval(4, 4, size - 8, size - 8);
        g2.setColor(Color.BLACK);
        g2.drawOval(4, 4, size - 8, size - 8);
    }
    g2.dispose();
    // hotspot = the pixel that actually clicks; adjust if the tip of your art is elsewhere
    return Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(size / 2, size / 2), "tool");
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
