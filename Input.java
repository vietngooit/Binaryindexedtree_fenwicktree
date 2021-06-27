
public class Input {
    public static String check_input(String s){
        String res = "";
        String a = "";
      
        int cnt = 0;
        char []ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++){
            if(ch[i] == ',' && ch[i + 1] == ','){
                ch[i] = ch[i + 1];
            }
            else a += ch[i];
        }
        s = a;
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) >= 48  && s.charAt(i) <= 57){
                res += s.charAt(i);
                cnt++;
            }
            else if(s.charAt(i) == ',') res += s.charAt(i);
        }
        s = res;
        if(res == "" || cnt == 0) return "FALSE";
        else return res;
    }
}
