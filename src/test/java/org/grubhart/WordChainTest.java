package org.grubhart;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import static org.junit.Assert.assertEquals;

public class WordChainTest {

    private WordChain wordChain;

    public WordChainTest() {
        wordChain = new WordChain();
    }

    @Test
    public void testWordcontainAnotherWordOriginlesserThanDestiny() throws IOException {

        String origin = "hat";
        String destiny = "hate";

        String expectedChain = "hat:hate";

        ArrayList<String> diccionario = null;
        String obtainedChain = wordChain.getchain(origin,destiny, diccionario);

        assertEquals(expectedChain,obtainedChain);

    }

    @Test
    public void testWordcontainAnotherWordOriginGreaterThanDestiny() throws IOException {

        String origin = "hate";
        String destiny = "hat";

        String expectedChain = "hate:hat";

        ArrayList<String> diccionario = null;
        String obtainedChain = wordChain.getchain(origin,destiny, diccionario);

        assertEquals(expectedChain,obtainedChain);

    }

    @Test
    public void testDestinyDifferentOriginSameSizeOneWordDistance() throws IOException {

        ArrayList<String> diccionario = new ArrayList<String>();
        diccionario.add("cat");
        diccionario.add("hat");
        diccionario.add("hot");
        diccionario.add("verb");

        String obtainedChain = wordChain.getchain("cat","hot",diccionario);
        String expectedChain = "cat:hat:hot";

        assertEquals(expectedChain,obtainedChain);


    }

    @Test
    public void testDestinyDifferentOriginDIfferentSizeSizeNoChain() throws IOException {

        ArrayList<String> diccionario = new ArrayList<String>();
        diccionario.add("car");
        diccionario.add("cat");
        diccionario.add("hat");
        diccionario.add("hot");
        diccionario.add("verb");

        String obtainedChain = wordChain.getchain("cat","verb",diccionario);
        String expectedChain = "error: no se pudo completar una cadena= cat:car";

        assertEquals(expectedChain,obtainedChain);


    }

    @Test
    public void testDestinyDifferentOriginDIfferentSize() throws IOException {

        ArrayList<String> diccionario = new ArrayList<String>();

        diccionario.add("bard");
        diccionario.add("bird");
        diccionario.add("cat");
        diccionario.add("car");
        diccionario.add("carb");
        diccionario.add("card");
        diccionario.add("cart");
        diccionario.add("cerb");
        diccionario.add("hat");
        diccionario.add("hot");
        diccionario.add("var");
        diccionario.add("ver");
        diccionario.add("varb");
        diccionario.add("verb");

        String obtainedChain = wordChain.getchain("cat","verb",diccionario);
        String expectedChain = "cat:car:carb:cerb:verb";

        assertEquals(expectedChain,obtainedChain);


    }

    @Test
    public void testDestinyDifferentOriginDIfferentSizeBird() throws IOException {

        ArrayList<String> diccionario = new ArrayList<String>();

        diccionario.add("bard");
        diccionario.add("bird");
        diccionario.add("cat");
        diccionario.add("car");
        diccionario.add("carb");
        diccionario.add("card");
        diccionario.add("cart");
        diccionario.add("cerb");
        diccionario.add("hat");
        diccionario.add("hot");
        diccionario.add("var");
        diccionario.add("ver");
        diccionario.add("varb");
        diccionario.add("verb");

        String obtainedChain = wordChain.getchain("cat","bird",diccionario);
        String expectedChain = "cat:car:card:bard:bird";

        assertEquals(expectedChain,obtainedChain);


    }

    @Test
    public void testDestinyDifferentOriginDIfferentSizeBirdFromFile() throws IOException {


        String obtainedChain = wordChain.getchain("lead","gold",null);
        String expectedChain = "lead:load:goad:gold";

        assertEquals(expectedChain,obtainedChain);

    }

    @Test
    public void testDestinyDifferentOriginDIfferentSizeBirdFromFile1() throws IOException {


        String obtainedChain = wordChain.getchain("car","bird",null);
        String expectedChain = "lead:load:goad:gold";

        assertEquals(expectedChain,obtainedChain);

    }


    @Test
    public void testDifferenceBeetween(){

        assertEquals((Integer)3,wordChain.differenceBeetween("dog","cat"));
        assertEquals((Integer)1,wordChain.differenceBeetween("hot","hat"));
        assertEquals((Integer)2,wordChain.differenceBeetween("cap","hat"));
        assertEquals((Integer)5,wordChain.differenceBeetween("cereal","comida"));
        assertEquals((Integer)1,wordChain.differenceBeetween("hat","hate"));
        assertEquals((Integer)2,wordChain.differenceBeetween("hat","hater"));
        assertEquals((Integer)3,wordChain.differenceBeetween("hat","chater"));
        assertEquals((Integer)8,wordChain.differenceBeetween("are","mercedes"));
        assertEquals((Integer)8,wordChain.differenceBeetween("mercedes","aria"));
        assertEquals((Integer)4,wordChain.differenceBeetween("cat","bird"));
        assertEquals((Integer)3,wordChain.differenceBeetween("car","bird"));


    }


}
