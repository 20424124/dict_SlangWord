/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dic_slangword;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author macintoshhd
 */
public class dao_SW {
    private String ORIGINAL_SLANG = "slang.txt";
    private String NEW_SLANG = "new_Slang.txt";
    private String HISTORY_SLANG = "history_Slang.txt";
    
    private int sizeMap;
    
    public HashMap<String, List<String>> listSlangWord = new HashMap<String, List<String>>();
    private int find = 0;
    
    // Đọc dữ liệu từ File text
    void readFile() throws FileNotFoundException{
        listSlangWord.clear();
		String slag = null;
		Scanner scanner = new Scanner(new File(NEW_SLANG));
		scanner.useDelimiter("`");
		scanner.next();
		String temp = scanner.next();
		String[] part = temp.split("\n");
		int i = 0;
		int flag = 0;
		sizeMap = 0;
		while (scanner.hasNext()) {
			List<String> meaning = new ArrayList<String>();
			slag = part[1].trim();
			temp = scanner.next();
			part = temp.split("\n");
			if (listSlangWord.containsKey(slag)) {
				meaning = listSlangWord.get(slag);
			}
			if (part[0].contains("|")) {
//				System.out.println(part[0]);
				String[] d = (part[0]).split("\\|");
				for (int ii = 0; ii < d.length; ii++)
//					System.out.println(d[ii]);
				Collections.addAll(meaning, d);
				sizeMap += d.length - 1;
			} else {
				meaning.add(part[0]);
			}
			// map.put(slag.trim(), meaning);
			listSlangWord.put(slag, meaning);
			i++;
			sizeMap++;
		}
		scanner.close();
    }
    
    // Ghi dữ liệu từ File text
    public void fileWriter(HashMap<String, List<String>> listSlangWord){
        try{
            PrintWriter writer = new PrintWriter(NEW_SLANG);
            writer.print("");
            writer.close();
            
            FileWriter fw = new FileWriter(NEW_SLANG, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Slag`Meaning");
            bw.newLine();
            Map<String, List<String>> sortedMap = new TreeMap<>(listSlangWord);
            Set<String> keySet = sortedMap.keySet();
            
            for (String key : keySet) {
                StringBuilder tKey = new StringBuilder();
                StringBuilder tValue = new StringBuilder();
                
                tKey.append(key + "`");
                //item.getValue().forEach((key, value)-> tValue.append("|" + value));
                Set<String> set = new HashSet<String>(sortedMap.get(key));
                List<String> list = new ArrayList<String>(set);
                
                for(String value: list){
                    tValue.append("| " + value);
                }
                bw.write(tKey.toString() + tValue.substring(1).toString());
                
                bw.newLine();
                //item.getValue().forEach((key, value)-> System.out.println(" - " + value));
            }
                               
            bw.close();
            fw.close();
            System.out.println("Success !!!! ");
            
        }catch(Exception e){
            
        }
        
        
}
}
