
/**
 * Created by HYC on 2016/9/13.
 */

import java.util.Scanner;



/**
   * set default mock parameter.闁挎稑鐗婇弻鐔封枖閺団槅鍤涢柡鍕嚱缁憋拷
   * @throws Exception if has error(鐎殿喖鍊搁悥鍓佹嫚鐎涙ɑ顫�)
   */
public class PolynomialDer {
  /**
* set default mock parameter.闁挎稑鐗婇弻鐔封枖閺団槅鍤涢柡鍕嚱缁憋拷
* @throws Exception if has error(鐎殿喖鍊搁悥鍓佹嫚鐎涙ɑ顫�)
*/
  public static void main(String[] args) {
    Poly myPoly = new Poly();
    Scanner input = new Scanner(System.in);
    System.out.println("闂佸搫瀚烽崹浼村箚娓氾拷閺屽矂宕堕埡鍌涙闂佸湱顣介弲娑㈡儓瀹ュ鏅柣鎺戝�块弫宥咁吋閸℃锟�  N闂佹寧绋戠粔鎾箚閿燂拷");
    String extendOrder = input.nextLine();
    if (extendOrder.equals("Y") || extendOrder.equals("y")) {
      myPoly.boo = true;
    }
    boolean setflag = true;
    while (setflag) {
      System.out.println("Enter a polynomial:");
      String str = input.nextLine();
      setflag = !myPoly.set(str);
    }
    boolean flag = true;
    while (flag) {
      System.out.println("Please input instruction:");
      String instruction = input.nextLine();
      int command = Poly.oeder(instruction);
      long startTime = System.currentTimeMillis();
      switch (command) {
        case 1:// 濠殿喚鎳撻崐鎼侇敋閿燂拷
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
        case 2:// 濠殿喚鎳撻崐鎼佹晸閺傘倖瀚�
          String[] var = instruction.split(" ");
          for (int i = 1; i < var.length; i++) {
            if (var[i].indexOf("=") != -1) {
              String[] temp = var[i].split("=");
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
      long endTime = System.currentTimeMillis();
      System.out.println("闁哄鏅滈崝姗�銆侀幋锕�绫嶉柛顐ｆ礃閿涳拷" + (endTime - startTime) + "ms");
      System.out.println("continue or give up?(Y/N):");
      String temp = input.nextLine();
      if (temp.equals("n") || temp.equals("N")) {
        break;
      }
    }
    input.close();
    System.out.println("end");
  }
}

