package pol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Poly {
  public boolean boo = false; //扩展标志
  public String str; // 存储多项式
  public String simStr; // 化简后的多项式
  public String[] arr; // 存储多项式的每一项
  public String result;
  /**
   * set default mock parameter.锛堟柟娉曡鏄庯級
   * @throws Exception if has error(寮傚父璇存槑)
   */
  
  public final boolean set(String str) {
    // 格式匹配时将多项式存储起来
    if (boo) {
      this.arr = str.split("[+-]");
      for (int i = 0; i < arr.length; i++) {
        arr[i] = arr[i].trim();
        arr[i] = arr[i].replaceAll("\\s", "*");
        final Pattern pat = Pattern.compile(
            "\\w+([\\^]\\d+)?(([*]\\w+)|([*]\\w+[\\^]\\d+))*");
        final Matcher mat = pat.matcher(arr[i]);
        if (!mat.matches()) {
          return false;
        }
        arr[i] = changeStrFormat(arr[i]);
      }
      this.simStr = str;
      this.str = str;
      return true;
    } else {
      this.str = str;
      this.simStr = str;
      if (!this.expression()) {
        // 格式不匹配时输出错误信息
        System.out.println("error");
        return false;
      }
      return true;
    }
  }

  public void getStr() {
    // System.out.println(str);
    System.out.println(result);
  }

  // public void getSimStr()
  // {
  // System.out.println(simStr);
  // }
  /**
  * set default mock parameter.閿涘牊鏌熷▔鏇☆嚛閺勫函绱�
  * @throws Exception if has error(瀵倸鐖剁拠瀛樻)
  */
  //处理表达式，形成自定义数据结构
  public boolean expression() {
    Pattern pat;
    if (boo) {
      pat = Pattern.compile("\\w+([\\^]\\d+)?(([*]\\w+)"
                + "|([*]\\w+[\\^]\\d+))*([+-]\\w+([\\^]\\d+)?(([*]\\w+)"
                + "|([*]\\w+[\\^]\\d+))*)*");
    } else {
      pat = Pattern.compile("\\w+([*]\\w)*([+]\\w+([*]\\w)*)*");
    }
    final Matcher mat = pat.matcher(str);
    // 返回是否匹配标记
    return mat.matches();
  }

  /**
   * set default mock parameter.閿涘牊鏌熷▔鏇☆嚛閺勫函绱�
   * @throws Exception if has error(瀵倸鐖剁拠瀛樻)
   */
  public void partition() {
    this.arr = this.str.split("[+-]");
    for (int i = 0; i < arr.length; i++) {
      this.arr[i] = changeStrFormat(this.arr[i]);
    }
  }
  
  /**
   * set default mock parameter.閿涘牊鏌熷▔鏇☆嚛閺勫函绱�
   * @throws Exception if has error(瀵倸鐖剁拠瀛樻)
   */
  //求导数,var指多项式中的变量
  public final boolean derivative(String var) {
    if (str.indexOf(var) == -1) {
      System.out.println("error");
      return false;
    }
    for (int i = 0; i < arr.length; i++) {
      // 统计该项中的变量的个数
      final int count = countOfCh(arr[i], var);
      switch (count) {
        case 0: // 该项中不含有变量，求导后为0
          arr[i] = null;
          break;
        case 1: // 该项中包含一个变量，求导后变量用1代替
          arr[i] = arr[i].replaceAll(var, "1");
          break;
        default: // 包含2个以上的变量，把第一个变量变换成count
          final String cstring = Integer.toString(count);
          arr[i] = arr[i].replaceFirst(var, cstring);
          break;
      }
    }
    return true;
  }

  /**
   * set default mock parameter.閿涘牊鏌熷▔鏇☆嚛閺勫函绱�
   * @throws Exception if has error(瀵倸鐖剁拠瀛樻)
   */
  //统计myStr中var出现的次数
  public static final int countOfCh(String myStr, String var) {
    if (myStr == null || myStr.equals("")) {
      return 0;
    }
    int count = 0;
    // 当不进行扩展时每一项中各个变量或数字之间用*分割
    final String[] varArr = myStr.split("\\*");

    for (final String temp : varArr) {
      if (temp.equals(var)) {
        count++;
      }
    }
    return count;
  }

  /**
   * set default mock parameter.閿涘牊鏌熷▔鏇☆嚛閺勫函绱�
   * @throws Exception if has error(瀵倸鐖剁拠瀛樻)
   */
  // 化简多项式
  public String simplify(String[] arr) {
    String myStr;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != null) {
        arr[i] = simPoly(arr[i]);
      }
    }
    int sum = 0;
    // System.out.println(Arrays.toString(arr));
    for (final String temp : arr) {
      if (isNum(temp)) {
        sum = Integer.valueOf(temp).intValue() + sum;
      }
    }
    myStr = sum == 0 ? "" : String.valueOf(sum);
    for (final String temp : arr) {
      if (temp != null && !isNum(temp)) {
        myStr = myStr + "+" + temp;
      }
    }
    myStr = sum == 0 ? myStr.substring(1) : myStr;
    // System.out.println(myStr);
    return myStr;
  }

  /**
   * set default mock parameter.閿涘牊鏌熷▔鏇☆嚛閺勫函绱�
   * @throws Exception if has error(瀵倸鐖剁拠瀛樻)
   */
  public String extendSim() {
    final ArrayList<Integer> list = new ArrayList<Integer>(); // 记录所有正负号所在的位置
    final int index = str.indexOf('-');
    int min = -1;
    int pcount = 0;
    int scount = 0;
    if (index != 0) {
      list.add(1);
      pcount++;
    }
    do {
      final int aaa = str.indexOf('+', ++min);
      final int bbb = str.indexOf('-', ++min);
      if (aaa == -1 && bbb == -1) {
        break;
      } else if (aaa == -1) {
        min = bbb;
      } else if (bbb == -1) {
        min = aaa;
      } else {
        min = aaa < bbb ? aaa : bbb;
      }
      if (aaa == min) {
        list.add(1);
        pcount++;
      } else {
        list.add(0);
      }
    } while (min > -1 && min < str.length());
    scount = list.size() - pcount;
    String[] parr = new String[pcount];
    String[] sarr = new String[scount];
    int mmm = 0;
    int nnn = 0;
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i) == 0) {
        sarr[nnn] = this.arr[i];
        nnn++;
      } else {
        parr[mmm] = this.arr[i];
        mmm++;
      }
    }
    // System.out.println(Arrays.toString(parr));
    // System.out.println(Arrays.toString(sarr));
    // return simplify(parr) + "-(" + simplify(sarr) + ")";
    return simplify(parr) + "-" + simplify(sarr).replaceAll("\\+", "-");
  }

  /**
   * set default mock parameter.閿涘牊鏌熷▔鏇☆嚛閺勫函绱�
   * @throws Exception if has error(瀵倸鐖剁拠瀛樻)
   */
  // 通过传入一个具体的值intvalue将表达式中的var替换成具体的值
  public final void specific(String var, String intvalue) {
    if (intvalue != null && intvalue.equals("") == false) {
      simStr = simStr.replace(var, intvalue);
    }
    arr = simStr.split("[+-]");
    for (int i = 0; i < arr.length; i++) {
      arr[i] = arr[i].trim();
      arr[i] = arr[i].replaceAll("\\s", "*");
      arr[i] = changeStrFormat(arr[i]);
    }
    // arr = simStr.split("\\-");
  }

  /**
   * set default mock parameter.閿涘牊鏌熷▔鏇☆嚛閺勫函绱�
   * @throws Exception if has error(瀵倸鐖剁拠瀛樻)
   */
  // 化简多项式的一项，例如：将a*a变为a^2，将1*2变为2
  public static final String simPoly(String myStr) {
    final int judge = 1;
    // 确定该项中含有多少变量或数字
    final String[] arrlocal = myStr.split("\\*");
    Arrays.sort(arrlocal);
    int sum = 1; // 该项中所有数字的乘积
    String strTemp = arrlocal[0];
    String strReturn = new String();
    int count = 0; // 字符出现的次数
    for (final String temp : arrlocal) {
      // 计算所有数字的乘积
      if (isNum(temp)) {
        final int aaa = Integer.valueOf(temp).intValue();
        if (aaa == 0) {
          return null;
        }
        sum = sum * aaa;
        strTemp = temp;
      } else {
        // 统计每种字符出现的次数
        if (strTemp.equals(temp)) {
          count++;
        } else {
          if (count == judge) {
            strReturn = strReturn + "*" + strTemp;
          } else if (count > judge) {
            strReturn = strReturn + "*" + strTemp 
              + "^" + String.valueOf(count);
          }
          strTemp = temp;
          count = 1;
        }
      }
    }
    if (count == judge) {
      strReturn = strReturn + "*" + strTemp;
    } else if (count > judge) {
      strReturn = strReturn + "*" + strTemp + "^" + String.valueOf(count);
    }
    if (strReturn.isEmpty()) {
      return String.valueOf(sum);
    } else {
      if (sum == judge) {
        return strReturn.substring(1);
      } else {
        return String.valueOf(sum) + strReturn;
      }
    }
  }

  /**
   * set default mock parameter.閿涘牊鏌熷▔鏇☆嚛閺勫函绱�
   * @throws Exception if has error(瀵倸鐖剁拠瀛樻)
   */
  public static String changeStrFormat(String myStr) {
    // 将该项分割成单个的字符或者 var ^ d的形式
    myStr = myStr.trim();
    String[] temp = myStr.split("\\*|\\s");
    for (int i = 0; i < temp.length; i++) {
      final int index = temp[i].indexOf('^');
      if (index != -1) {
        // 出现了var^d的形式
        final String[] astring = temp[i].split("\\^");
        final int numOfVar = Integer.parseInt(astring[1]);
        temp[i] = astring[0];
        for (int j = 1; j < numOfVar; j++) {
          temp[i] = temp[i] + "*" + astring[0];
        }
      }
    }
    // 合并得到不含有^的项
    myStr = temp[0];
    for (int i = 1; i < temp.length; i++) {
      myStr = myStr + "*" + temp[i];
    }
    return myStr;
  }

  /**
   * set default mock parameter.閿涘牊鏌熷▔鏇☆嚛閺勫函绱�
   * @throws Exception if has error(瀵倸鐖剁拠瀛樻)
   */
  // 判断一个字符串是否可以转化位为数字，采用正则表达式的方法
  public static final boolean isNum(String string) {
    if (string == null) {
      return false;
    }
    char []jud = string.toCharArray();
    for(int i=0;i<string.length();i++)
    {
    	if(jud[i]<96 || jud[i]>105)
    	{
    		return false;
    	}
    }
    return true;
  }

  /**
   * set default mock parameter.閿涘牊鏌熷▔鏇☆嚛閺勫函绱�
   * @throws Exception if has error(瀵倸鐖剁拠瀛樻)
   */
  public static final int oeder(String order) {
    // 求导指令
    final Pattern der = Pattern.compile("!d/d \\w+");
    // 化简指令
    final Pattern sim = Pattern.compile("!simplify( \\w+(=\\d+)?)+");
    // 求导指令返回1，化简指令返回2，否则返回0
    if (der.matcher(order).matches()) {
      return 1;
    } else if (sim.matcher(order).matches()) {
      return 2;
    } else {
      return 0;
    }
  }
}
//add a new line at the end of the file
