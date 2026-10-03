package css123proj.salamatminumultoangpapelko.Panels;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;

public class AudioSettings {

    private static boolean musicOn = true;
    private static boolean sfxOn = true;
    private static Clip musicClip;
    private static String currentMusic;   // remembered so turning Music back ON resumes it

    private AudioSettings() {}

    public static boolean isMusicOn() { return musicOn; }
    public static boolean isSfxOn()   { return sfxOn; }

    public static void setMusicOn(boolean on) {
        musicOn = on;
        if (!on) {
            stopClip();
        } else if (currentMusic != null) {
            playMusic(currentMusic);
        }
    }

    public static void setSfxOn(boolean on) { sfxOn = on; }

    /** Loops background music, e.g. playMusic("/Music/title.wav"). Replaces whatever was playing. */
    public static void playMusic(String resourcePath) {
        if (resourcePath.equals(currentMusic) && musicClip != null && musicClip.isRunning()) return;
    currentMusic = resourcePath;
        currentMusic = resourcePath;
        if (!musicOn) return;
        stopClip();
        URL url = AudioSettings.class.getResource(resourcePath);
        if (url == null) return;                         // file not added yet: stay silent
        try (AudioInputStream ais = AudioSystem.getAudioInputStream(url)) {
            musicClip = AudioSystem.getClip();
            musicClip.open(ais);
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println("Could not play music " + resourcePath + ": " + e.getMessage());
        }
    }

    public static void stopMusic() {
        currentMusic = null;
        stopClip();
    }

    /** Plays a short one-shot sound, e.g. playSfx("/SFX/check.wav"). */
    public static void playSfx(String resourcePath) {
        if (!sfxOn) return;
        URL url = AudioSettings.class.getResource(resourcePath);
        if (url == null) return;
        try (AudioInputStream ais = AudioSystem.getAudioInputStream(url)) {
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.addLineListener(ev -> {
                if (ev.getType() == LineEvent.Type.STOP) clip.close();
            });
            clip.start();
        } catch (Exception e) {
            System.err.println("Could not play sfx " + resourcePath + ": " + e.getMessage());
        }
    }

    private static void stopClip() {
        if (musicClip != null) {
            musicClip.stop();
            musicClip.close();
            musicClip = null;
        }
    }
}