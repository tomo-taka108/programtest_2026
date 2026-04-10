package test02;

import java.util.Scanner;
import java.util.ArrayDeque;

/**
 * 【課題2】
 * 一次元リバーシの棋譜 S が標準入力で与えられるので、
 * 最終的な黒石と白石の個数をスペース区切りで標準出力に出力する
 *
 * ＜設計の考え方＞
 * 盤面を「連続する同色ブロック」単位で管理する。
 *
 * 例: B B B W W B → [(B,3), (W,2), (B,1)]
 *
 * ※ブロック列は必ず「色が交互に並ぶ」
 * ※石は左端・右端にしか打てないため、1手で影響を考慮するのは常に「端のブロック」だけ。
 *
 */
public class Test02Main {

  static final int BLACK = 0;
  static final int WHITE = 1;

  /** 連続する同色ブロックを表す内部クラス */
  static class Block {
    int color; // BLACK or WHITE
    int count; // このブロックに含まれる石の個数

    Block(int color, int count) {
      this.color = color;
      this.count = count;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String S = scanner.nextLine();

    // ブロック列: 先頭=左端、末尾=右端
    ArrayDeque<Block> blocks = new ArrayDeque<>();
    // 初期配置: 左端から黒1個, 白1個
    blocks.addLast(new Block(BLACK, 1));
    blocks.addLast(new Block(WHITE, 1));

    // 標準入力 S を左から1文字ずつ読んで、石を置く処理を繰り返す
    for (int i = 0; i < S.length(); i++) {
      char move = S.charAt(i);

      // 奇数手なら黒、偶数手なら白を定義
      int color;
      if (i % 2 == 0) {
        color = BLACK;
      } else {
        color = WHITE;
      }

      // Lなら左端に置く、Rなら右端に置く処理を呼び出す
      if (move == 'L') {
        placeLeft(blocks, color);
      } else if (move == 'R') {
        placeRight(blocks, color);
      }
    }

    // 黒・白の総数をブロック単位で集計
    long black = 0, white = 0;
    for (Block b : blocks) {
      if (b.color == BLACK) black += b.count;
      else               white += b.count;
    }

    System.out.println(black + " " + white);
  }

  /**
   * 左端に color の石を1個置く。
   *
   * ケース A: 先頭ブロックが同色
   *   → 先頭ブロックのカウントを +1。
   * ケース B: 先頭ブロックが異色 かつ ブロックが2個以上ある
   *   → ブロック列は色が交互なので 2個目は必ず同色。
   *     先頭ブロックをひっくり返し、2個目の count に (ひっくり返したcount + 1) を加算。
   * ケース C: 先頭ブロックが異色 かつ ブロックが1個しかない
   *   → 反対側に同色なし。ひっくり返さず、新ブロック(color,1)を先頭に追加。
   */
  static void placeLeft(ArrayDeque<Block> blocks, int color) {
    Block head = blocks.peekFirst();

    if (head.color == color) {
      // ケース A
      head.count++;
    } else if (blocks.size() >= 2) {
      // ケース B
      int flipped = blocks.pollFirst().count; // 先頭の異色ブロック（ひっくり返す分）の個数を取得
      blocks.peekFirst().count += flipped + 1; // 新石1個 + ひっくり返した分を加算
    } else {
      // ケース C
      blocks.addFirst(new Block(color, 1));
    }
  }

  /**
   * 右端に color の石を1個置く。placeLeft の左右対称版。
   *
   * ケース A: 末尾ブロックが同色 → 末尾カウント +1。
   * ケース B: 末尾が異色かつ2個以上 → 末尾をひっくり返し、その前のブロックに加算。
   * ケース C: 末尾が異色かつ1個のみ → 新ブロックを末尾に追加。
   */
  static void placeRight(ArrayDeque<Block> blocks, int color) {
    Block tail = blocks.peekLast();

    if (tail.color == color) {
      // ケース A
      tail.count++;
    } else if (blocks.size() >= 2) {
      // ケース B
      int flipped = blocks.pollLast().count;
      blocks.peekLast().count += flipped + 1;
    } else {
      // ケース C
      blocks.addLast(new Block(color, 1));
    }
  }
}
