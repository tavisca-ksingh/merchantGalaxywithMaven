package com.tavisca.workshop.mgttg;


import java.util.Arrays;

public class WordToRomanParser {

    public String [] parse(String str) {
        String [] val = str.split(" ");
        if(val.length==3){
            return Singleparse(val);
        }
        else if(val[0].equalsIgnoreCase("how"))
        {
        return parseTheQuestion(val);
        }
        else
        {
            return Multipleparse(val);
        }
    }
     public String checkConditionToVerifyQuestion(String [] val)
     {

         String initialCondition = "";
         for(int i= 0; i<3;i++)
         {
             initialCondition += val[i] + " ";
         }
         return initialCondition;
     }

    private String[] parseTheQuestion(String[] val) {

        if( checkConditionToVerifyQuestion(val).equalsIgnoreCase("how many credits " ) || checkConditionToVerifyQuestion(val).equalsIgnoreCase("how much is " )) {
            int n = Arrays.asList(val).indexOf("is");
            String[] newVal = new String[val.length - (n + 2)];
            int k = 0;
            for (int i = n + 1; i < val.length; i++) {
                if (val[i].equals("?")) continue;
                newVal[k++] = val[i];
            }
            return newVal;
        }
        else{
             String[] strings = {"I have no idea what you are talking about"};
            return strings;
        }
    }

    private String [] Singleparse(String [] val) {
        return  new String [] {val[0] , val[2]};
    }

    private String[]  Multipleparse(String [] val) {
        int n=Arrays.asList(val).indexOf("is");
        String[] newVal =new String[n+1];
        int i=0;
        for(String str:val)
        {
            if(str.equals("is")){
                break;
            }

            newVal[i++] = str;
        }
        if(val[val.length-1].equalsIgnoreCase("credits")) {
            newVal[i] = val[val.length-2];
        } else
            throw new RuntimeException(val[val.length-2] + "credits is missing");
        return newVal;
    }
     MapWordToRomanValue mapWordToRomanValue=new MapWordToRomanValue();


        public double getTheValueFromParseWords(String s)
        {
            String [] strArray = parse(s);
            double results = (double)0;
            if(strArray.length==2)
            {
                mapWordToRomanValue.wordToRoman.put(strArray[0],strArray[1]);
            }
            else if(strArray.length>2){

               results=  mapWordToRomanValue.calculateMissingValue(strArray);
        }
            return  results;
        }


        public double  getResults(String s)
        {
            String [] valueToCalculate = parse(s);
            int len =valueToCalculate.length;
            double credits,results=0;

            if(mapWordToRomanValue.wordsCredits.containsKey(valueToCalculate[len-1])) {
                credits = mapWordToRomanValue.wordsCredits.get(valueToCalculate[len - 1]);
                String [] multipleKeys = Arrays.copyOf(valueToCalculate,len-1);
                int decimalValue = mapWordToRomanValue.getDecimalFromWords(multipleKeys);
                results = decimalValue * credits;
                return  results;
            }
            else if(mapWordToRomanValue.wordToRoman.containsKey(valueToCalculate[len-1]))
            {
                    results = mapWordToRomanValue.getDecimalFromWords(valueToCalculate);
                    return results;
            }
            else
                throw  new RuntimeException("I have no idea what you are talking about");
        }


    public String getValueFromWordToRomanMap(String key) {
            if(mapWordToRomanValue.wordToRoman.containsKey(key))
                return mapWordToRomanValue.wordToRoman.get(key);
            else if(mapWordToRomanValue.wordsCredits.containsKey(key)) {
                double value= mapWordToRomanValue.wordsCredits.get(key);
                return Double.toString(value);
            }
            else{
        return "I have no idea what you are talking about";
            }
    }
}
