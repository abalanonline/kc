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

import java.util.Arrays;

public class App {

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

  public static void main(String[] args) {
    Arrays.stream(TAC9).forEach(System.out::println);
  }

}
