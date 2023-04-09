/*
 * Copyright 2023 Aleksei Balan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ab.kc;

import ab.jnc2.GraphicsMode;
import ab.jnc2.Screen;
import ab.jnc2.TextFont;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class App implements Runnable, KeyListener {

  public static final String[] TAC9 = {
      "000000000111111111111111110000000", "000000001333333333333333331000000",
      "000000013222322233222322233100000", "000000013222322233222322233100000",
      "000000013222322233222322233100000", "000000013333333333311333333101100",
      "000000013222322233122122233112210", "000000013222322233122212233122210",
      "011110013222322233122221111222210", "122211113333333333121122222221210",
      "112222113222322231222211222112221", "001111213222322231222312222231221",
      "000001113222322231222112221211221", "000000013333333331222222222222221",
      "000000013222322231222211111112221", "000000113222322233122212212212210",
      "000001211333333333312222222222100", "000012221111111111111111111111000",
      "000012210122100000012210122100000", "000011100011100000001110011100000",
  };
  public static final int FPS = 50;

  Screen screen;
  TextFont textFont;
  int[] cm;
  private final int ground;
  boolean running;
  int velocity;
  int height;
  int distance;
  int phase;
  private final Graphics2D graphics;
  int[] treadmill = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, -1};
  int hitBox;
  int score;
  int hiScore;

  public App(Screen screen) {
    this.screen = screen;
    screen.keyListener = this;
    graphics = this.screen.image.createGraphics();
    this.textFont = new TextFont(6, 6);
    cm = GraphicsMode.COLOR_MAP_CGA;
    ground = screen.mode.resolution.height * 3 / 4;
    hitBox = TAC9[0].length();
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();
    switch (keyCode) {
      case KeyEvent.VK_UP:
      case KeyEvent.VK_Z:
        if (!running && score > 0) {
          restart();
          break;
        }
        running = true;
        if (velocity == 0) velocity = 40;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  void drawTac(int x, int y, int p) {
    int[] rgb = {cm[8], cm[0], cm[7], cm[15]};
    for (int y1 = 0, y2 = y; y1 < TAC9.length; y1++, y2++) {
      if (y2 < 0 || y2 >= screen.mode.resolution.height) continue;
      String s = TAC9[y1];
      if (y1 == 18) x += p;
      for (int x1 = 0, x2 = x; x1 < s.length(); x1++, x2++) {
        if (x2 < 0 || x2 >= screen.mode.resolution.width) continue;
        int c = s.charAt(x1) - '0';
        if (c == 0) continue;
        screen.image.setRGB(x2, y2, rgb[c]);
      }
    }
  }

  void drawBlock(int x, int y) {
    graphics.setBackground(new Color(cm[0]));
    graphics.clearRect(x, y, 16, 16);
    graphics.setBackground(new Color(cm[15]));
    graphics.clearRect(x, y, 15, 15);
    graphics.setBackground(new Color(cm[7]));
    graphics.clearRect(x + 1, y + 1, 14, 14);
  }

  void restart() {
    distance = 0;
    phase = 0;
    velocity = 0;
    height = 0;
    score = 0;
    running = true;
  }

  @Override
  public void run() {
    if (running) {
      distance++;
      phase++;
      score = distance / 16;
      if (hiScore < score) hiScore = score;
    }
    velocity--;
    height += velocity;
    if (height <= 0) {
      height = 0;
      velocity = 0;
    }
    int pixHeight = (int) (Math.log(height / 20 + 1) * 15);

    graphics.setBackground(new Color(cm[8]));
    graphics.clearRect(0, 0, screen.mode.resolution.width, screen.mode.resolution.height);
    for (int i = 0; i < screen.mode.resolution.width + 16; i += 16) {
      int i1 = i - 60 + distance - hitBox + 1;
      while (i1 < 0) i1 += treadmill.length * 16;
      int r = i1 % 16;
      i1 = i1 / 16 % treadmill.length;
      i1 = treadmill[i1];
      do {
        drawBlock(i - r, ground - i1 * 16);
        i1--;
      } while (i1 >= 0);
    }
    drawTac(60, ground - 20 - pixHeight, phase / 8 % 2);
    if (treadmill[distance / 16 % treadmill.length] * 16 > pixHeight) running = false;
    textFont.printCentered(screen.image, "Tac Nayn sluoS fo retaE", screen.mode.resolution.width / 2, 20, cm[7]);
    textFont.print(screen.image, String.format("EROCS %04d", score), 120, 30, cm[7]);
    textFont.print(screen.image, String.format("CS IH %04d", hiScore), 120, 40, cm[7]);
  }

  public static void main(String[] args) {
    GraphicsMode graphicsMode = new GraphicsMode(240, 135); // FHD/8
    graphicsMode.aspectRatio = new Dimension(16, 9);
    Screen screen = new Screen(graphicsMode);
    screen.flicker(FPS, new App(screen));
  }

}
