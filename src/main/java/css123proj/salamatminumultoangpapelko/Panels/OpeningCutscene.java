/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package css123proj.salamatminumultoangpapelko.Panels;


    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.net.URL;
    import java.util.function.Consumer;

public class OpeningCutscene extends javax.swing.JPanel {

        private Consumer<String> goTo;
        private int i = 1;
    
    public OpeningCutscene(Consumer<String> goTo) {

    this.goTo = goTo;
    setLayout(new BorderLayout());
    setBackground(Color.BLACK);

    JButton prev = new JButton("<");
    JButton next = new JButton(">");
    JButton skip = new JButton("Skip");
    JButton confirm = new JButton("Confirm");

    // big side arrows, easy to hit
    Font arrowFont = new Font("SansSerif", Font.BOLD, 36);
    Dimension arrowSize = new Dimension(80, 180);
    prev.setFont(arrowFont);
    next.setFont(arrowFont);
    prev.setPreferredSize(arrowSize);
    next.setPreferredSize(arrowSize);
    confirm.setFont(new Font("SansSerif", Font.BOLD, 24));
    confirm.setPreferredSize(new Dimension(220, 50));

    CardLayout showScene = new CardLayout();
    JPanel sceneBox = new JPanel(showScene);
    sceneBox.add(new SceneImage("/Art/Cutscenes/Opening/Cutscene1.png"), "SCENE_1");
    sceneBox.add(new SceneImage("/Art/Cutscenes/Opening/Cutscene2.png"), "SCENE_2");

    // top-left: Skip
    JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
    topBar.setOpaque(false);
    topBar.setPreferredSize(new Dimension(0, 70));
    topBar.add(skip);

    // bottom-center: Confirm (only visible on the last scene; the bar keeps its height so the image doesn't jump)
    JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
    bottomBar.setOpaque(false);
    bottomBar.setPreferredSize(new Dimension(0, 70));
    bottomBar.add(confirm);

    // left and right: arrows, vertically centered
    JPanel leftBar = new JPanel(new GridBagLayout());
    leftBar.setOpaque(false);
    leftBar.add(prev);
    JPanel rightBar = new JPanel(new GridBagLayout());
    rightBar.setOpaque(false);
    rightBar.add(next);

    add(topBar, BorderLayout.NORTH);
    add(bottomBar, BorderLayout.SOUTH);
    add(leftBar, BorderLayout.WEST);
    add(rightBar, BorderLayout.EAST);
    add(sceneBox, BorderLayout.CENTER);

    final int lastScene = 2;
    Runnable refresh = () -> {
        prev.setEnabled(i > 1);
        next.setEnabled(i < lastScene);
        confirm.setVisible(i == lastScene);
    };
    refresh.run();

    next.addActionListener(e -> {
        if (i < lastScene) {
            i++;
            showScene.show(sceneBox, "SCENE_" + i);
            refresh.run();
        }
    });
    prev.addActionListener(e -> {
        if (i > 1) {
            i--;
            showScene.show(sceneBox, "SCENE_" + i);
            refresh.run();
        }
    });
    confirm.addActionListener(e -> goTo.accept("Title Screen"));
    skip.addActionListener(e -> goTo.accept("Title Screen"));

    // restart from scene 1 every time the cutscene is shown (e.g. "Rewatch Opening")
    addHierarchyListener(e -> {
        if ((e.getChangeFlags() & java.awt.event.HierarchyEvent.SHOWING_CHANGED) != 0 && isShowing()) {
            i = 1;
            showScene.show(sceneBox, "SCENE_1");
            refresh.run();
        }
    });
}
    
    /** Draws an image as large as possible without stretching it, centered in the panel. */
private static class SceneImage extends JPanel {

    private Image img;

    SceneImage(String resourcePath) {
        setBackground(Color.BLACK);
        URL url = OpeningCutscene.class.getResource(resourcePath);
        if (url != null) img = new ImageIcon(url).getImage();
        else System.err.println("Missing image: " + resourcePath);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img == null) return;
        int iw = img.getWidth(this), ih = img.getHeight(this);
        if (iw <= 0 || ih <= 0) return;
        double scale = Math.min(getWidth() / (double) iw, getHeight() / (double) ih);
        int w = (int) (iw * scale), h = (int) (ih * scale);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(img, (getWidth() - w) / 2, (getHeight() - h) / 2, w, h, this);
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
