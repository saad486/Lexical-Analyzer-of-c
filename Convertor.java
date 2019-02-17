import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.regex.*;
import java.util.HashMap;
import java.util.Map;

public class Convertor {

    public static Map intializeMap(){

        Map<String,String> wordsMap = new HashMap<>();
        wordsMap.put("main","keyword");
        wordsMap.put("void","keyword");
        wordsMap.put("int","keyword");
        wordsMap.put("char","keyword");
        wordsMap.put("double","keyword");
        wordsMap.put("{","Open Brace");
        wordsMap.put("(","Left parenthesis");
        wordsMap.put("}","Close Brace");
        wordsMap.put(")","Right parenthesis");
        wordsMap.put(",","Comma");
        wordsMap.put(";","SemiColon");
        wordsMap.put("printf","Keyword");



        return wordsMap;
    }


    public static void main(String[] args) throws IOException{

        int c;
        String temp = "";

        FileReader fr = null;
        try {

            fr = new FileReader("c code");//File path of the c code

        }
        catch (FileNotFoundException fe){
            System.out.println("File not found");
        }

        while((c = fr.read())!=-1) {
            temp = temp + (char)c;
        }

       // System.out.println(temp);
        fr.close();

       // String[] split = temp.split("[\\r\\n]+");
        Map<String,String> wordsMap = intializeMap();

        StringTokenizer st = new StringTokenizer(temp,"[\r?\n?]+");

        String pattern = "#|[A-z][A-z0-9_]*|\\s?void\\s|\".*\"|\\s?char\\s|\\s?int\\s|\\s+main\\b|\\s?double\\s|\\s?float\\s|\\{|\\}|\\(|\\)|\\|scanf\\b|printf\\b|\\;";
        Pattern p = Pattern.compile(pattern);
        while (st.hasMoreTokens()) {
            String line = st.nextToken();
            Matcher m = p.matcher(line);
            while(m.find()) {

                String matchWords = m.group();
                matchWords = matchWords.trim();
               // System.out.println(matchWords);
                if(matchWords.equals("#")) {
                    System.out.println(line);
                    break;
                }
                else {
                    if(wordsMap.containsKey(matchWords)) {
                        System.out.println(matchWords + " " + wordsMap.get(matchWords));
                    }
                    else if(matchWords.startsWith("\"")) {
                        System.out.println(matchWords + " String literal");
                    }
                    else {
                        System.out.println(matchWords+" Identifier");
                    }
                }


            }


        }

    }






}

