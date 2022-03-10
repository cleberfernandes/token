/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package token;

import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author cleber.fernandes
 */
public class TokenList {
    final static int MIN_TOKEN_LENGTH = 2;
    
    public ArrayList<TokenInfo> list = new ArrayList<>();
    
    public void addWord(String s) {
        try {
            Double foo = Double.parseDouble(s);
            return;
        } catch(Exception e) {
            // abafa
        }
        
        if (s.length() < MIN_TOKEN_LENGTH)
            return;
        
        TokenInfo t = find(s);
        if (t == null) {
            t = new TokenInfo();
            t.text = s;
            t.count = 1;
            if (list != null)
                list.add(t);
        } else {
            t.inc();
        }
        //System.out.println(s + ": " + t.count);
    }
    
    public void addLine(String s) {
        String ss = s.toLowerCase();
        
        if (ss.trim().startsWith("//") || ss.trim().startsWith("/*") || ss.trim().endsWith("*/"))
            return;
            
        ss = filterChars(ss);
        
        String aStrings[] = ss.split(" ");
        for (String word: aStrings) {
            addWord(word);
        }
    }
    
    public TokenInfo find(String s) {
        if ((list == null) || (list.size() < 1))
            return null;
        
        for (TokenInfo t: list) {
            String tokenText = t.text;
            String lowerS = s.toLowerCase();
            
            if (tokenText.equalsIgnoreCase(lowerS)) {
                return t;
            }
        }
        
        return null;
    }

    static String filterChars(String s) {
        if (s.isEmpty())
            return s;
        
        String r = "";
        try {
            for (int i=0; i < s.length(); i++) {
                String ss = s.substring(i, i+1);
                if ("abcdefghijklmnopqrstuvwxyz1234567890".contains(ss)) {
                    r += ss;
                } else {
                    r += " "; // qq outro símbolo, concatena 1 space
                }
            }
        } finally {
            return r;
        }
    }
}
