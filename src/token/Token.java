/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package token;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cleber.fernandes
 */
public class Token {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Nothing to do!");
            return;
        }
        
        FileNames fileNames = new FileNames();
        fileNames.initialDir = args[0];
        fileNames.ext = args[1];
        fileNames.find();
        
        TokenList tokenList = new TokenList();
                    
        if (fileNames.list != null) {
            String temp[] = new String[fileNames.list.size()];
            temp = fileNames.list.toArray(temp);
            for (String fn:temp) {
                System.out.println(fn);
                
                try {
                    FileReader fr = new FileReader(new File(fn));
                    BufferedReader br = new BufferedReader(fr);
                    
                    String ln;
                    while ((ln = br.readLine()) != null) {
                        String line = ln.trim();
                        if (line.length() == 0)
                            continue;
                        
                        //!!!System.out.println(line);
                        tokenList.addLine(line);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Token.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
        }
                    
        for (TokenInfo tokenInfo: tokenList.list) {
            System.out.println("\"" + tokenInfo.text + "\"; " + tokenInfo.count);
        }
    }
}
