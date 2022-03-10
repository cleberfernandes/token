/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package token;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author cleber.fernandes
 */
public class FileNames {
    ArrayList<String> list = new ArrayList<String>();
    public String initialDir;
    public String ext;
    
    public void find() {
        File file = new File(initialDir);
        String[] l = file.list();
        
        for (String fn:l) {
            String d = initialDir;
            d = d + "\\" + fn;
            File f = new File(d);
            
            if (f.isDirectory()) {
                FileNames recursiveFileNames = new FileNames();
                recursiveFileNames.ext = this.ext;
                recursiveFileNames.initialDir = d;
                recursiveFileNames.find();
                
                if (recursiveFileNames.list != null)
                {
                    String temp[] = new String[recursiveFileNames.list.size()];
                    temp = recursiveFileNames.list.toArray(temp);
                    for (String s: temp) {
                        this.list.add(s);
                    }
                }
            } else if ((f != null) && (d.toUpperCase().endsWith("." + ext))) {
                this.list.add(d);
            }
        }
    }
}
