package pol;

/**
 * Created by HYC on 2016/9/13.
 */

import java.util.Scanner;



/**
   * set default mock parameter.闂佹寧绋戦悧濠囧蓟閻斿皝鏋栭柡鍥ｆ閸ゆ盯鏌￠崟顐㈠毐缂佹唻鎷�
   * @throws Exception if has error(閻庢鍠栭崐鎼佹偉閸撲焦瀚氶悗娑櫳戦～锟�)
   */
public class PolynomialDer {
  /**
* set default mock parameter.闂佹寧绋戦悧濠囧蓟閻斿皝鏋栭柡鍥ｆ閸ゆ盯鏌￠崟顐㈠毐缂佹唻鎷�
* @throws Exception if has error(閻庢鍠栭崐鎼佹偉閸撲焦瀚氶悗娑櫳戦～锟�)
*/
  public static void main(final String[] args) {
    final Poly myPoly = new Poly();
    final Scanner input = new Scanner(System.in);
    System.out.println("是否采用扩展？Y－是  N－否");
    String extendOrder = input.nextLine();
    String isequalY = "Y";
    String isequaly = "y";
    if (extendOrder.equals(isequaly) || extendOrder.equals(isequalY)) {
      myPoly.boo = true;
    }
    boolean setflag = true;
    while (setflag) {
      System.out.println("Enter a polynomial:");
      final String str = input.nextLine();
      setflag = !myPoly.set(str);
    }
    boolean flag = true;
    while (flag) {
      System.out.println("Please input instruction:");
      final String instruction = input.nextLine();
      final int command = Poly.oeder(instruction);
      final long startTime = System.currentTimeMillis();
      switch (command) {
        case 1:// 求导
          myPoly.partition();
          if (!myPoly.derivative(instruction.substring(5))) {
            break;
          }
          if (myPoly.boo && myPoly.str.indexOf("-") != -1) {
            myPoly.result = myPoly.extendSim();
          } else {
            myPoly.result = myPoly.simplify(myPoly.arr);
          }
          myPoly.getStr();
          break;
        case 2:// 求值
          final String[] var = instruction.split(" ");
          for (int i = 1; i < var.length; i++) {
            if (var[i].indexOf('=') != -1) {
              final String[] temp = var[i].split("=");
              myPoly.specific(temp[0], temp[1]);
            }
          }
          if (myPoly.boo && myPoly.simStr.indexOf("-") != -1) {
            myPoly.result = myPoly.extendSim();
          } else {
            myPoly.result = myPoly.simplify(myPoly.arr);
          }
          myPoly.getStr();
          break;
        default:
          System.out.println("error");
      }
      final long endTime = System.currentTimeMillis();
      System.out.println("运行时间" + (endTime - startTime) + "ms");
      System.out.println("continue or give up?(Y/N):");
      final String temp = input.nextLine();
      String isequalN = "N";
      String isequaln = "n";
      if (temp.equals(isequalN) || temp.equals(isequaln)) {
        break;
      }
    }
    input.close();
    System.out.println("end");
  }
}

