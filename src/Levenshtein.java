import java.util.Map;

public class Levenshtein {

    public static int distance(String str1, String str2) {
        int[][] values = new int[str1.length()+1][str2.length()+1];
        values[0][0] = 0;
        values[0][1] = 1; values[1][0] = 1;
        for(int j=1; j<=str2.length(); j++) {
            values[0][j]=j;
            for(int i=1; i<=str1.length(); i++) {
                values[i][0]=i;
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    values[i][j] = values[i-1][j-1];
                } else {
                    values[i][j] = 1 + Math.min(Math.min(values[i-1][j],values[i][j-1]),values[i-1][j-1]);  //Effacement,Insertion,Substitution
                    //values[i][j] = 1 + Math.min(Math.min(Math.min(values[i-1][j],values[i][j-1]),values[i-1][j-1]),transposition(str1,str2,i,j,values));  //Effacement,Insertion,Substitution,Transposition
                }
            }
        }
        return values[str1.length()][str2.length()];
    }
    
    private static int transposition(String str1,String str2,int i, int j,int [][] values){   // Ajout de la transposition
        return i>1 && j>1 && str1.substring(i - 1, i).equals(str2.substring(j - 2, j - 1)) && str1.substring(i - 2,i-1).equals(str2.substring(j - 1,j)) ? values[i - 2][j- 2] : 40;
    }
    
    public static String levenMin(String word, Map<String, Integer> map) {
        int min = 100;
        String myword = "";
        for(String key : map.keySet()) {
            if(key.length()<=1) continue;
            if(Levenshtein.distance(key,word) < min) {
                min = Levenshtein.distance(key,word);
                myword = key;
            }
        }
        return myword;
    }
     public static Set<String> levenMin(String word, Map<String,Integer> tri) {
        Map<String, Integer> words = new HashMap<>();
        int lev;
        for (String key : tri.keySet()) {
            if (key.length() <= 1) continue;
            lev=Levenshtein.distance(key, word);
            if (words.size() < 5) words.put(key, lev);
            for (String i : words.keySet()) {
                if (lev < words.get(i)) {
                    words.remove(i);
                    words.put(key, lev);
                    break;
                }
            }
        }
        return words.keySet();
    }
}
