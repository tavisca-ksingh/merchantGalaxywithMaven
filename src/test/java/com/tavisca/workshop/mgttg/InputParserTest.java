package com.tavisca.workshop.mgttg;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InputParserTest {
    WordToRomanParser wordToRomanParser= new WordToRomanParser();
    @Test
   public void canParseWordToRomanNumeralStatement(){

        assertArrayEquals(new String[]{"glob","I"},
                wordToRomanParser.parse("glob is I"));

        assertArrayEquals(new String[]{"prok","V"},
                wordToRomanParser.parse("prok is V"));
    }

    @Test
    public void canParseMultipleWordsToRomanNumeralStatement(){

        assertArrayEquals(new String[]{"glob","prok","Gold","57800"},
                wordToRomanParser.parse("glob prok Gold is 57800 Credits"));
        assertArrayEquals(new String[]{"glob","glob","Silver","34"},
                wordToRomanParser.parse("glob glob Silver is 34 Credits"));
    }

    @Test
    public void checkInitialConditonToVerifyQuestion(){
        String [] question = {"how much is pish tegj glob glob ?","how many Credits is glob prok Silver ?"};

        assertEquals("how much is ",
                wordToRomanParser.checkConditionToVerifyQuestion(question[0].split(" ")));
        assertEquals("how many Credits ",
                        wordToRomanParser.checkConditionToVerifyQuestion(question[1].split(" ")));
    }

    @Test
    public void canParsetheValueWeNeededToConvert(){
        assertArrayEquals(new String[]{"pish","tegj","glob","glob"},
                wordToRomanParser.parse("how much is pish tegj glob glob ?"));
        assertArrayEquals(new String[]{"glob","prok","Silver"},
                wordToRomanParser.parse("how many Credits is glob prok Silver ?"));
        assertArrayEquals(new String[]{"glob","prok","Iron"},
                wordToRomanParser.parse("how many Credits is glob prok Iron ?"));
       assertArrayEquals(new String[]{"I have no idea what you are talking about"},
                wordToRomanParser.parse("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"));
    }

    @Test
    public void CheckTheValueInMap()
    {

        wordToRomanParser.getTheValueFromParseWords("glob is I");
        wordToRomanParser.getTheValueFromParseWords("prok is V");
        wordToRomanParser.getTheValueFromParseWords("pish is X");
        wordToRomanParser.getTheValueFromParseWords("tegj is L");

       assertEquals("V",  wordToRomanParser.getValueFromWordToRomanMap("prok"));
       assertEquals("X",  wordToRomanParser.getValueFromWordToRomanMap("pish"));
       assertEquals("L",  wordToRomanParser.getValueFromWordToRomanMap("tegj"));
    }

    @Test
    public void CalculateTheMissingValue(){
        wordToRomanParser.getTheValueFromParseWords("glob is I");
        wordToRomanParser.getTheValueFromParseWords("prok is V");
        wordToRomanParser.getTheValueFromParseWords("pish is X");
        wordToRomanParser.getTheValueFromParseWords("tegj is L");
        assertEquals(17,wordToRomanParser.getTheValueFromParseWords("glob glob Silver is 34 Credits"),1);

        assertEquals(14450,wordToRomanParser.getTheValueFromParseWords("glob prok Gold is 57800 Credits"),1);
        assertEquals(195.5,wordToRomanParser.getTheValueFromParseWords("pish pish Iron is 3910 Credits"),1);

        //checking weather missing value is added in he map or not
        assertEquals("17.0",  wordToRomanParser.getValueFromWordToRomanMap("Silver"));
        assertEquals("14450.0",  wordToRomanParser.getValueFromWordToRomanMap("Gold"));
        assertEquals("195.5",  wordToRomanParser.getValueFromWordToRomanMap("Iron"));
    }


    @Test
    public void WordsCalculator(){
        wordToRomanParser.getTheValueFromParseWords("glob is I");
        wordToRomanParser.getTheValueFromParseWords("prok is V");
        wordToRomanParser.getTheValueFromParseWords("pish is X");
        wordToRomanParser.getTheValueFromParseWords("tegj is L");
        wordToRomanParser.getTheValueFromParseWords("glob glob Silver is 34 Credits");
        wordToRomanParser.getTheValueFromParseWords("glob prok Gold is 57800 Credits");
        wordToRomanParser.getTheValueFromParseWords("pish pish Iron is 3910 Credits");
        assertEquals(42,wordToRomanParser.getResults("how much is pish tegj glob glob ?"),1);
        assertEquals(68,wordToRomanParser.getResults("how many Credits is glob prok Silver ?"),1);
        assertEquals(57800,wordToRomanParser.getResults("how many Credits is glob prok Gold ?"),1);
        assertEquals(782 ,wordToRomanParser.getResults("how many Credits is glob prok Iron ?"),1);
        assertEquals("I have no idea what you are talking about",  wordToRomanParser.getValueFromWordToRomanMap("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"));

    }


}
