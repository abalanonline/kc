package ab;

//import ab.tns.soundbank.TheInstrument;
//import ab.tns.soundbank.TheSoundbank;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Patch;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class KcTest {

//  static TheInstrument makeInstrument(String wav) throws IOException, UnsupportedAudioFileException {
//    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
//        new BufferedInputStream(Files.newInputStream(Paths.get(wav))));
//    byte[] pcm16 = audioInputStream.readAllBytes();
//    TnsSound.Font soundFont = new TnsSound.Font(1, pcm16, (int) audioInputStream.getFormat().getSampleRate(), "");
//    TnsSound.Instrument[] ins = soundFont.getInstruments();
//    ins[0].setSample(0, pcm16.length);
//    return TheSoundbank.fromBytes(soundFont.toByteArray()).getInstruments()[0];
//  }

  @Test
  void test() throws Exception {
//    TheSoundbank soundbank = new TheSoundbank();
//    soundbank.putInstrument(new Patch(0, 86 - 1), makeInstrument("assets/86.wav"));
//    soundbank.putInstrument(new Patch(0, 124 - 1), makeInstrument("assets/boing.wav"));
//    soundbank.putInstrument(new Patch(0, 125 - 1), makeInstrument("assets/crash.wav"));
//    Files.write(Paths.get("assets/music.sf2"), soundbank.toBytes());
  }

}
