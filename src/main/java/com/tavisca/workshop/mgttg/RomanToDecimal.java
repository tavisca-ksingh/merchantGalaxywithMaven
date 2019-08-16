package com.tavisca.workshop.mgttg;

import java.util.HashMap;

public class RomanToDecimal {

    private  String roman;


     private static HashMap<Character, Integer> Conversion= new HashMap<Character, Integer>(){
         {
             put('I',1);
             put('V',5);
             put('X',10);
             put('L',50);
             put('C',100);
             put('D',500);
             put('M',1000);
         }
     };


    public int romanIntoDigits(String roman ) {
        this.roman = roman.toUpperCase() ;
        int sum = 0;
        for (int i = 0; i < roman.length(); i++) {
                int currentValue = Conversion.get(roman.charAt(i));
                if(i+1 < roman.length())
                {
                    int nextValue = Conversion.get(roman.charAt(i+1));
                    if(currentValue < nextValue)
                    {
                        sum += nextValue-currentValue;
                        i++;
                    }
                    else{
                        sum += currentValue;
                    }
                }
                else {
                sum = sum + currentValue;
            }
        }
        return sum;
    }
}
