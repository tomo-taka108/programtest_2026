package test03;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 【課題3】
 * 10進数で表記された n 桁の整数 x が標準入力で与えられるので、
 * x の各桁の数字を入れ替えた整数のうち、先頭が0にならないものの中で最小の値を標準出力に出力する。
 *
 * ＜設計の考え方＞
 * まず各桁を昇順にソートすれば、最小に近い並びになる。
 * ただし、先頭が0だと条件違反になるため、
 * ソート後の先頭が0の場合は「最初に現れる非0の数字」を先頭に移動する。
 *
 * 例:
 * 201 → ソート後 012 → 先頭が0なので 102 に並べ替える
 * 12212122212222 → ソート後 11112222222222 → そのまま最小
 */
public class Test03Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String x = scanner.nextLine();

    // 入力された整数を1文字ずつの配列に変換する
    char[] digits = x.toCharArray();

    // 各桁を昇順に並べる
    Arrays.sort(digits);

    // 先頭が0なら、そのままでは「先頭が0でない」という条件を満たせない
    if (digits[0] == '0') {
      // 2文字目以降から、最初に現れる非0の数字を探す
      for (int i = 1; i < digits.length; i++) {
        // 最初の非0の数字を見つけたら、先頭の0と入れ替える
        if (digits[i] != '0') {
          char temp = digits[0];
          digits[0] = digits[i];
          digits[i] = temp;
          break;
        }
      }
      System.out.println(new String(digits));
    } else {
      System.out.println(new String(digits));
    }
  }
}
