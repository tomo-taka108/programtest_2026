package test01;

import java.util.Scanner;

/**
 * 【課題1】
 * すごろくの全長 A マスが標準入力で与えられるので、
 * ゴールに到達できる最小のサイコロ回数を標準出力に出力する。
 */
public class Test01Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int A = scanner.nextInt();

    // スタートは1マス目にあるため、実際に進む必要があるのは A-1 マス
    // 1回のサイコロで最大6マス進めるので、必要最小回数は ceil((A-1) / 6)
    int minRolls = (int) Math.ceil((A - 1) / 6.0);

    System.out.println(minRolls);
  }

}