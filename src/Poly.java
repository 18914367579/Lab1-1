import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Poly {
  public boolean boo = false; // 閹碘晛鐫嶉弽鍥х箶
  public String str; // 鐎涙ê鍋嶆径姘躲�嶅锟�
  public String simStr; // 閸栨牜鐣濋崥搴ｆ畱婢舵岸銆嶅锟�
  String[] arr; // 鐎涙ê鍋嶆径姘躲�嶅蹇曟畱濮ｅ繋绔存い锟�
  public String result;
  /**
   * set default mock parameter.锛堟柟娉曡鏄庯級
   * @throws Exception if has error(寮傚父璇存槑)
   */
  
  public boolean set(String str) {
    // 閺嶇厧绱￠崠褰掑帳閺冭泛鐨㈡径姘躲�嶅蹇撶摠閸屻劏鎹ｉ弶锟�
    if (boo) {
      this.arr = str.split("[+-]");
      for (int i = 0; i < arr.length; i++) {
        arr[i] = arr[i].trim();
        arr[i] = arr[i].replaceAll("\\s", "*");
        Pattern pat = Pattern.compile(
            "\\w+([\\^]\\d+)?(([*]\\w+)|([*]\\w+[\\^]\\d+))*");
        Matcher mat = pat.matcher(arr[i]);
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
        // 閺嶇厧绱℃稉宥呭爱闁板秵妞傛潏鎾冲毉闁挎瑨顕ゆ穱鈩冧紖
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
  //婢跺嫮鎮婄悰銊ㄦ彧瀵骏绱濊ぐ銏″灇閼奉亜鐣炬稊澶嬫殶閹诡喚绮ㄩ弸锟�
  public boolean expression() {
    Pattern pat;
    if (!boo) {
      pat = Pattern.compile("\\w+([*]\\w)*([+]\\w+([*]\\w)*)*");
    } else {
      pat = Pattern.compile("\\w+([\\^]\\d+)?(([*]\\w+)"
        + "|([*]\\w+[\\^]\\d+))*([+-]\\w+([\\^]\\d+)?(([*]\\w+)"
        + "|([*]\\w+[\\^]\\d+))*)*");
    }
    Matcher mat = pat.matcher(str);
    // 鏉╂柨娲栭弰顖氭儊閸栧綊鍘ら弽鍥唶
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
  //濮瑰倸顕遍弫锟�,var閹稿洤顦挎い鐟扮础娑擃厾娈戦崣姗�鍣�
  public boolean derivative(String var) {
    if (str.indexOf(var) == -1) {
      System.out.println("error");
      return false;
    }
    for (int i = 0; i < arr.length; i++) {
      // 缂佺喕顓哥拠銉┿�嶆稉顓犳畱閸欐﹢鍣洪惃鍕嚋閺侊拷
      int count = countOfCh(arr[i], var);
      switch (count) {
        case 0: // 鐠囥儵銆嶆稉顓濈瑝閸氼偅婀侀崣姗�鍣洪敍灞剧湴鐎电厧鎮楁稉锟�0
          arr[i] = null;
          break;
        case 1: // 鐠囥儵銆嶆稉顓炲瘶閸氼偂绔存稉顏勫綁闁插骏绱濆Ч鍌氼嚤閸氬骸褰夐柌蹇曟暏1娴狅絾娴�
          arr[i] = arr[i].replaceAll(var, "1");
          break;
        default: // 閸栧懎鎯�2娑擃亙浜掓稉濠勬畱閸欐﹢鍣洪敍灞惧Ω缁楊兛绔存稉顏勫綁闁插繐褰夐幑銏″灇count
          String cstring = Integer.toString(count);
          arr[i] = arr[i].replaceFirst(var, cstring);
      }
    }
    return true;
  }

  /**
   * set default mock parameter.閿涘牊鏌熷▔鏇☆嚛閺勫函绱�
   * @throws Exception if has error(瀵倸鐖剁拠瀛樻)
   */
  //缂佺喕顓竚yStr娑撶捒ar閸戣櫣骞囬惃鍕偧閺侊拷
  public static int countOfCh(String myStr, String var) {
    if (myStr == null || myStr.equals("")) {
      return 0;
    }
    int count = 0;
    // 瑜版挷绗夋潻娑滎攽閹碘晛鐫嶉弮鑸电槨娑擄拷妞ら�涜厬閸氬嫪閲滈崣姗�鍣洪幋鏍ㄦ殶鐎涙ぞ绠ｉ梻瀵告暏*閸掑棗澹�
    String[] varArr = myStr.split("\\*");

    for (String temp : varArr) {
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
  // 閸栨牜鐣濇径姘躲�嶅锟�
  public String simplify(String[] arr) {
    String myStr;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != null) {
        arr[i] = simPoly(arr[i]);
      }
    }
    int sum = 0;
    // System.out.println(Arrays.toString(arr));
    for (String temp : arr) {
      if (isNum(temp)) {
        sum = Integer.valueOf(temp).intValue() + sum;
      }
    }
    myStr = sum == 0 ? "" : String.valueOf(sum);
    for (String temp : arr) {
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
    ArrayList<Integer> list = new ArrayList<Integer>(); // 鐠佹澘缍嶉幍锟介張澶嬵劀鐠愮喎褰块幍锟介崷銊ф畱娴ｅ秶鐤�
    int index = str.indexOf("-");
    int min = -1;
    int pcount = 0;
    int scount = 0;
    if (index != 0) {
      list.add(1);
      pcount++;
    }
    do {
      int aaa = str.indexOf("+", ++min);
      int bbb = str.indexOf("-", ++min);
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
  // 闁俺绻冩导鐘插弳娑擄拷娑擃亜鍙挎担鎾舵畱閸婄彻ntvalue鐏忓棜銆冩潏鎯х础娑擃厾娈憊ar閺囨寧宕查幋鎰徔娴ｆ挾娈戦崐锟�
  public void specific(String var, String intvalue) {
    if (intvalue != null && intvalue != "") {
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
  // 閸栨牜鐣濇径姘躲�嶅蹇曟畱娑擄拷妞ょ櫢绱濇笟瀣洤閿涙艾鐨*a閸欐ü璐焌^2閿涘苯鐨�1*2閸欐ü璐�2
  public static String simPoly(String myStr) {
    // 绾喖鐣剧拠銉┿�嶆稉顓炴儓閺堝顦跨亸鎴濆綁闁插繑鍨ㄩ弫鏉跨摟
    String[] arrlocal = myStr.split("\\*");
    Arrays.sort(arrlocal);
    int sum = 1; // 鐠囥儵銆嶆稉顓熷閺堝鏆熺�涙娈戞稊妯夹�
    String strTemp = arrlocal[0];
    String strReturn = new String();
    int count = 0; // 鐎涙顑侀崙铏瑰箛閻ㄥ嫭顐奸弫锟�
    for (String temp : arrlocal) {
      // 鐠侊紕鐣婚幍锟介張澶嬫殶鐎涙娈戞稊妯夹�
      if (isNum(temp)) {
        int aaa = Integer.valueOf(temp).intValue();
        if (aaa == 0) {
          return null;
        }
        sum = sum * aaa;
        strTemp = temp;
      } else {
        // 缂佺喕顓稿В蹇曨潚鐎涙顑侀崙铏瑰箛閻ㄥ嫭顐奸弫锟�
        if (strTemp.equals(temp)) {
          count++;
        } else {
          if (count == 1) {
            strReturn = strReturn + "*" + strTemp;
          } else if (count > 1) {
            strReturn = strReturn + "*" + strTemp 
              + "^" + String.valueOf(count);
          }
          strTemp = temp;
          count = 1;
        }
      }
    }
    if (count == 1) {
      strReturn = strReturn + "*" + strTemp;
    } else if (count > 1) {
      strReturn = strReturn + "*" + strTemp + "^" + String.valueOf(count);
    }
    if (strReturn.isEmpty()) {
      return String.valueOf(sum);
    } else {
      if (sum == 1) {
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
    // 鐏忓棜顕氭い鐟板瀻閸撳弶鍨氶崡鏇氶嚋閻ㄥ嫬鐡х粭锔藉灗閼帮拷 var ^ d閻ㄥ嫬鑸板锟�
    myStr = myStr.trim();
    String[] temp = myStr.split("\\*|\\s");
    for (int i = 0; i < temp.length; i++) {
      int index = temp[i].indexOf("^");
      if (index != -1) {
        // 閸戣櫣骞囨禍鍞檃r^d閻ㄥ嫬鑸板锟�
        String[] astring = temp[i].split("\\^");
        int numOfVar = Integer.parseInt(astring[1]);
        temp[i] = astring[0];
        for (int j = 1; j < numOfVar; j++) {
          temp[i] = temp[i] + "*" + astring[0];
        }
      }
    }
    // 閸氬牆鑻熷妤�鍩屾稉宥呮儓閺堝。閻ㄥ嫰銆�
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
  // 閸掋倖鏌囨稉锟芥稉顏勭摟缁楋缚瑕嗛弰顖氭儊閸欘垯浜掓潪顒�瀵叉担宥勮礋閺佹澘鐡ч敍宀勫櫚閻€劍顒滈崚娆掋�冩潏鎯х础閻ㄥ嫭鏌熷▔锟�
  public static boolean isNum(String string) {
    if (string == null) {
      return false;
    }
    Pattern pat = Pattern.compile("[0-9]*");
    Matcher mat = pat.matcher(string);
    return mat.matches();
  }

  /**
   * set default mock parameter.閿涘牊鏌熷▔鏇☆嚛閺勫函绱�
   * @throws Exception if has error(瀵倸鐖剁拠瀛樻)
   */
  public static int oeder(String order) {
    // 濮瑰倸顕遍幐鍥︽姢
    Pattern der = Pattern.compile("!d/d \\w+");
    // 閸栨牜鐣濋幐鍥︽姢
    Pattern sim = Pattern.compile("!simplify( \\w+(=\\d+)?)+");
    // 濮瑰倸顕遍幐鍥︽姢鏉╂柨娲�1閿涘苯瀵茬粻锟介幐鍥︽姢鏉╂柨娲�2閿涘苯鎯侀崚娆掔箲閸ワ拷0
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
