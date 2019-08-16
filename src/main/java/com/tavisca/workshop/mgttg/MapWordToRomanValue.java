package com.tavisca.workshop.mgttg;

import java.util.HashMap;

public class MapWordToRomanValue {

    protected HashMap<String, String> wordToRoman = new HashMap<String, String>();
    protected HashMap<String, Double> wordsCredits = new HashMap<String, Double>();
    RomanToDecimal romanToDecimal=new RomanToDecimal();

    public double calculateMissingValue(String[] strArray) {
        int len =strArray.length;
        int val = Integer.parseInt(strArray[len-1]);
        String key = strArray[len-2];
        double result;
        String romanValue="";
        for(int i=0;i<len-1;i++)
        {
            if(wordToRoman.containsKey(strArray[i]))
            {
                romanValue += wordToRoman.get(strArray[i]);
            }
        }

        int divisor= romanToDecimal.romanIntoDigits(romanValue);
        result= (double)val / divisor;
        wordsCredits.put(key,result);
        return result;

    }

    public int getDecimalFromWords(String [] keys)
    {
        int len = keys.length;

        String romanValue="";
        for(int i=0;i<len;i++)
        {
            if(wordToRoman.containsKey(keys[i]))
            {
                romanValue += wordToRoman.get(keys[i]);
            }
        }
        int results= romanToDecimal.romanIntoDigits(romanValue);
        return results;
    }

    public String getRomanFromWords(String [] keys)
    {
        int len = keys.length;

        String romanValue="";
        for(int i=0;i<len;i++)
        {
            if(wordToRoman.containsKey(keys[i]))
            {
                romanValue += wordToRoman.get(keys[i]);
            }
        }
        return romanValue;
    }


}
