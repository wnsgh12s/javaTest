import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException {
        Solution solution = new Solution();
        solution.soulution("ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"});
    }

    public static class Solution {
        public String soulution(String m ,String[] musicinfos) throws ParseException {
            int time = Integer.MIN_VALUE;
            String answer = "";
//           기억한 멜로디를 재생시간과 악보를 비교
            LinkedHashMap<String,Integer> map = new LinkedHashMap<>();
            for(String s : musicinfos){
                String[] music = s.split(",");
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date sT = sdf.parse(music[0]);
                Date eT = sdf.parse(music[1]);
                long timeMil1 = sT.getTime();
                long timeMil2 = eT.getTime();
                long diff = Math.abs(timeMil1 - timeMil2);
                long diffMin = diff/(1000*60);
                String mN = music[2];
                String mI = music[3];
                mI = change(mI);
                StringBuilder str  = new StringBuilder();
                for(int i =0; i < diffMin; i++){
                    if(i > mI.length()-1){
                        str.append(mI.charAt(i - mI.length()));
                        continue;
                    }
                    str.append(mI.charAt(i));
                }
                m = change(m);
                if(String.valueOf(str).contains(m) && diffMin > time){
                    answer = mN;
                    time = (int) diffMin;
                }
            }
            return answer;
        }
        public static String change(String words){
            StringBuilder temp = new StringBuilder();
            for(int i = 0; i<words.length()-1;i++){
                if(words.charAt(i+1) == '#'){
                    temp.append(Character.toLowerCase(words.charAt(i)));
                }else {
                    temp.append(words.charAt(i));
                }
            }
            return temp.toString();
        }
    }
}
