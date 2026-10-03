package css123proj.salamatminumultoangpapelko.Panels;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.HierarchyEvent;
import java.util.function.Consumer;
import javax.swing.*;

public class Settings extends JPanel {

    private static final double BASE_WIDTH = 1920.0;
    private static final double BASE_HEIGHT = 1080.0;

    private final Consumer<String> goTo;

    private final JLabel titleLbl = new JLabel("Settings", SwingConstants.CENTER);
    private final JToggleButton musicBtn = new JToggleButton();
    private final JToggleButton sfxBtn = new JToggleButton();
    private final JLabel volLbl = new JLabel("Music Volume", SwingConstants.CENTER);
    private final JSlider volumeSlider = new JSlider(0, 100, 40);
    private final JButton saveBtn = new JButton("Save");
    private final JButton backBtn = new JButton("Return to Title");

    public Settings(Consumer<String> goTo) {
        this.goTo = goTo;
        setLayout(null);

        titleLbl.setForeground(Color.WHITE);
        volLbl.setForeground(Color.WHITE);
        volumeSlider.setOpaque(false);

        add(titleLbl);
        add(musicBtn);
        add(sfxBtn);
        add(volLbl);
        add(volumeSlider);
        add(saveBtn);
        add(backBtn);

        musicBtn.addActionListener(e -> updateToggleText());
        sfxBtn.addActionListener(e -> updateToggleText());
        volumeSlider.addChangeListener(e -> AudioSettings.setMusicVolume(volumeSlider.getValue() / 100f));
        saveBtn.addActionListener(e -> saveSettings());
        backBtn.addActionListener(e -> goTo.accept("Title Screen"));

        // every time the screen is shown, show the saved values (unsaved changes are discarded)
        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && isShowing()) {
                syncFromSaved();
            }
        });
        syncFromSaved();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repositionElements();
            }
        });
    }

    private void syncFromSaved() {
        musicBtn.setSelected(AudioSettings.isMusicOn());
        sfxBtn.setSelected(AudioSettings.isSfxOn());
        volumeSlider.setValue((int) (AudioSettings.getMusicVolume() * 100));
        updateToggleText();
    }

    private void updateToggleText() {
        musicBtn.setText("Music: " + (musicBtn.isSelected() ? "ON" : "OFF"));
        sfxBtn.setText("SFX: " + (sfxBtn.isSelected() ? "ON" : "OFF"));
    }

    private void saveSettings() {
        AudioSettings.setMusicOn(musicBtn.isSelected());
        AudioSettings.setSfxOn(sfxBtn.isSelected());
        JOptionPane.showMessageDialog(this, "Settings saved.");
    }

    private void place(JComponent c, int x, int y, int w, int h, double sx, double sy) {
        c.setBounds((int) (x * sx), (int) (y * sy), (int) (w * sx), (int) (h * sy));
    }

    private void repositionElements() {
        double sx = getWidth() / BASE_WIDTH;
        double sy = getHeight() / BASE_HEIGHT;
        if (sx == 0 || sy == 0) return;
        double s = Math.min(sx, sy);

        // positions are in the 1920 x 1080 design space: x, y, width, height
        place(titleLbl,     560, 100, 800, 110, sx, sy);
        place(musicBtn,     660, 300, 600,  80, sx, sy);
        place(sfxBtn,       660, 410, 600,  80, sx, sy);
        place(volLbl,       660, 540, 600,  50, sx, sy);
        place(volumeSlider, 660, 595, 600,  60, sx, sy);
        place(saveBtn,      660, 760, 290,  80, sx, sy);
        place(backBtn,      970, 760, 290,  80, sx, sy);

        titleLbl.setFont(titleLbl.getFont().deriveFont(Font.BOLD, (float) (72 * s)));
        volLbl.setFont(volLbl.getFont().deriveFont(Font.BOLD, (float) (30 * s)));
        for (AbstractButton b : new AbstractButton[]{musicBtn, sfxBtn, saveBtn, backBtn}) {
            b.setFont(b.getFont().deriveFont(Font.BOLD, (float) (32 * s)));
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(0, 0, new Color(15, 20, 45), 0, getHeight(), new Color(45, 30, 70)));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
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
