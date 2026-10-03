package css123proj.salamatminumultoangpapelko.Panels;

import css123proj.salamatminumultoangpapelko.Panels.Levels.Instructions;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TitleScreen extends JPanel {

    private final Consumer<String> goTo;
    private Image bgImg;

    private static final double BASE_WIDTH = 1920.0;
    private static final double BASE_HEIGHT = 1080.0;

    private final JLabel titleLabel = new JLabel("Salamat, Minumulto Ang Papel Ko!", SwingConstants.CENTER);
    private final JButton playBtn = new JButton("Start Checking!");
    private final JButton instBtn = new JButton("Instructions");
    private final JButton credBtn = new JButton("Credits");
    private final JButton setBtn  = new JButton("Settings");
    private final JButton opeBtn  = new JButton("Rewatch Opening");

    public TitleScreen(Consumer<String> goTo) {
        this.goTo = goTo;
        setLayout(null);

        try {
            bgImg = ImageIO.read(getClass().getResource("/Art/Backgrounds/TitleImg.png"));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
        add(titleLabel);

        configureButton(playBtn, e -> {
            AudioSettings.playSfx("Confirm.wav");
            goTo.accept("Calendar");
        });

        configureButton(instBtn, e -> {
            AudioSettings.playSfx("Confirm.wav");
            Instructions.showInstructions();
        });

        configureButton(credBtn, e -> {
            AudioSettings.playSfx("Confirm.wav");
            goTo.accept("Credits");
        });

        configureButton(setBtn, e -> {
            AudioSettings.playSfx("Confirm.wav");
            goTo.accept("Settings");
        });

        configureButton(opeBtn, e -> {
            AudioSettings.playSfx("Confirm.wav");
            goTo.accept("Opening Cutscene");
        });

        // Dynamic responsive positioning on window resize
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repositionElements();
            }
        });
    }

    private void configureButton(JButton btn, java.awt.event.ActionListener action) {
        btn.setFont(new Font("SansSerif", Font.BOLD, 18));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(action);
        add(btn);
    }

    private void repositionElements() {
        int w = getWidth();
        int h = getHeight();
        if (w == 0 || h == 0) return;

        double sx = w / BASE_WIDTH;
        double sy = h / BASE_HEIGHT;

        titleLabel.setBounds((int)(460 * sx), (int)(120 * sy), (int)(1000 * sx), (int)(80 * sy));

        // Right-hand vertical button column
        int btnX = (int)(810 * sx);
        int btnW = (int)(320 * sx);
        int btnH = (int)(55 * sy);

        playBtn.setBounds(btnX, (int)(420 * sy), btnW, btnH);
        instBtn.setBounds(btnX, (int)(495 * sy), btnW, btnH);
        credBtn.setBounds(btnX, (int)(570 * sy), btnW, btnH);
        setBtn.setBounds(btnX,  (int)(645 * sy), btnW, btnH);
        opeBtn.setBounds(btnX,  (int)(720 * sy), btnW, btnH);

        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImg != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(bgImg, 0, 0, getWidth(), getHeight(), this);
            g2.dispose();
        }
    }
       

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
