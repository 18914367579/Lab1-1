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
    System.out.println("闂備礁鎼�氱兘宕规导鏉戠畾濞撴熬鎷烽柡灞界焸瀹曞爼鍩￠崒娑欘吂闂備礁婀遍。浠嬪疾濞戙垺鍎撶�广儱顦伴弲顒勬煟閹烘垵锟藉潡寮鍜佸悑闁糕剝顭勯敓锟�  N闂備焦瀵х粙鎴犵矓閹绢喖绠氶柨鐕傛嫹");
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
        case 1:// 婵犳鍠氶幊鎾诲磹閹间緡鏁嬮柨鐕傛嫹
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
        case 2:// 婵犳鍠氶幊鎾诲磹閹间焦鏅搁柡鍌樺�栫�氾拷
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
      System.out.println("闂佸搫顦弲婊堝礉濮楋拷閵嗕線骞嬮敃锟界猾宥夋煕椤愶絾绀冮柨娑虫嫹" + (endTime - startTime) + "ms");
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

