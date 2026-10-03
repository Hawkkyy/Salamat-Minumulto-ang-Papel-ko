package css123proj.salamatminumultoangpapelko.Panels;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Mixer;

public class AudioSettings {
    
    // part of the sound card's name, taken from the "Mixer:" lines (Generic = onboard audio)
private static final String PREFERRED_MIXER = "Generic";

private static Mixer.Info pickMixer(DataLine.Info info) {
    for (Mixer.Info mi : AudioSystem.getMixerInfo()) {
        if (mi.getName().contains(PREFERRED_MIXER)
                && !mi.getName().startsWith("Port")
                && AudioSystem.getMixer(mi).isLineSupported(info)) {
            return mi;
        }
    }
    return null;   // nothing matched: use Java's default
}

    private static volatile float musicVolume = 0.4f;   // 0.0 = silent, 1.0 = full volume

public static void setMusicVolume(float v) { musicVolume = Math.max(0f, Math.min(1f, v)); }
public static float getMusicVolume()       { return musicVolume; }

/** Scales 16-bit PCM samples in place. */
private static void applyVolume(byte[] buf, int len, boolean bigEndian, float vol) {
    if (vol >= 1f) return;
    for (int i = 0; i + 1 < len; i += 2) {
        short s = bigEndian ? (short) ((buf[i] << 8) | (buf[i + 1] & 0xFF))
                            : (short) ((buf[i + 1] << 8) | (buf[i] & 0xFF));
        s = (short) (s * vol);
        if (bigEndian) { buf[i] = (byte) (s >> 8); buf[i + 1] = (byte) s; }
        else           { buf[i] = (byte) s;        buf[i + 1] = (byte) (s >> 8); }
    }
}

    private static boolean musicOn = true;
    private static boolean sfxOn = true;
    private static String currentMusic;    // the song that SHOULD be playing
    private static String playingMusic;    // the song actually playing (music thread only)
    private static class Track { volatile boolean stop; }
    private static Track musicTrack;      // music thread only

    // all audio work happens on these threads, never on the window thread
    private static final ExecutorService MUSIC = Executors.newSingleThreadExecutor(r -> daemon(r, "music"));
    private static final ExecutorService SFX   = Executors.newSingleThreadExecutor(r -> daemon(r, "sfx"));

    private static Thread daemon(Runnable r, String name) {
        Thread t = new Thread(r, name);
        t.setDaemon(true);   // never keeps the game open after the window closes
        return t;
    }

    public static boolean isMusicOn() { return musicOn; }
    public static boolean isSfxOn()   { return sfxOn; }
    public static void setSfxOn(boolean on) { sfxOn = on; }

    public static void setMusicOn(boolean on) {
        musicOn = on;
        MUSIC.submit(() -> {
            if (!on) stopClip();
            else if (currentMusic != null) startMusic(currentMusic);
        });
    }

    public static void playMusic(String path) {
        currentMusic = path;
        MUSIC.submit(() -> {
            if (musicOn && path.equals(currentMusic)) startMusic(path);   // skip if the screen already changed
        });
    }

    public static void stopMusic() {
        currentMusic = null;
        MUSIC.submit(AudioSettings::stopClip);
    }

    public static void playSfx(String path) {
        if (!sfxOn) return;
        SFX.submit(() -> {
            try (AudioInputStream in = AudioSystem.getAudioInputStream(AudioSettings.class.getResource(path))) {
                Mixer.Info mi = pickMixer(new DataLine.Info(Clip.class, in.getFormat()));
                Clip clip = (mi != null) ? AudioSystem.getClip(mi) : AudioSystem.getClip();
                clip.open(in);
                clip.addLineListener(ev -> {
                    if (ev.getType() == LineEvent.Type.STOP) clip.close();
                });
                clip.start();
            } catch (Exception e) {
                // sound file missing: stay silent
            }
        });
    }

    // ---- music thread only ----

    private static void startMusic(String path) {
    if (path.equals(playingMusic) && musicTrack != null) return;   // already playing this song
    stopClip();
    URL url = AudioSettings.class.getResource(path);
    if (url == null) {
        System.out.println("Music file not found: " + path);
        return;
    }
    Track track = new Track();
    musicTrack = track;
    playingMusic = path;
    Thread t = new Thread(() -> stream(url, path, track), "music-stream");
    t.setDaemon(true);
    t.start();
}

/** Reads the file in small pieces and feeds the speakers; loops until told to stop. */
private static void stream(URL url, String path, Track track) {
    SourceDataLine line = null;
    try {
        AudioFormat fmt;
        try (AudioInputStream probe = AudioSystem.getAudioInputStream(url)) {
            fmt = probe.getFormat();
        }
        Mixer.Info mi = pickMixer(new DataLine.Info(SourceDataLine.class, fmt));
try {
    line = (mi != null) ? AudioSystem.getSourceDataLine(fmt, mi) : AudioSystem.getSourceDataLine(fmt);
    line.open(fmt);
} catch (Exception e) {                 // that device is busy or refused: use the default
    line = AudioSystem.getSourceDataLine(fmt);
    line.open(fmt);
    mi = null;
}
System.out.println("Using mixer: " + (mi != null ? mi.getName() : "default"));
        line.start();
        
        
final SourceDataLine dbg = line;
new Thread(() -> {
    try { Thread.sleep(3000); } catch (InterruptedException ignored) {}
    System.out.println("Audio frames played after 3s: " + dbg.getLongFramePosition());
}).start();
        
        System.out.println("Music started: " + path);

        byte[] buf = new byte[8192];
        while (!track.stop) {                    // when the song ends, open it again (loop)
            try (AudioInputStream in = AudioSystem.getAudioInputStream(url)) {
                int n;
                while (!track.stop && (n = in.read(buf, 0, buf.length)) != -1) {
                    if (fmt.getSampleSizeInBits() == 16) applyVolume(buf, n, fmt.isBigEndian(), musicVolume);
                    line.write(buf, 0, n);
                }
            }
        }
    } catch (Exception e) {
        System.out.println("Can't play music " + path + ": " + e);
    } finally {
        if (line != null) {
            line.stop();
            line.flush();
            line.close();
        }
    }
}

private static void stopClip() {
    playingMusic = null;
    if (musicTrack != null) {
        musicTrack.stop = true;
        musicTrack = null;
    }
}
}