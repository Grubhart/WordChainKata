package org.grubhart;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WordChainTest {

    private WordChain wordChain;

    public WordChainTest() {
        wordChain = new WordChain();
    }

    @Test
    public void testWordcontainAnotherWordOriginlesserThanDestiny(){

        String origin = "hat";
        String destiny = "hate";

        String expectedChain = "hat,hate";

        String obtainedChain = wordChain.getchain(origin,destiny);

        assertEquals(expectedChain,obtainedChain);

    }

    @Test
    public void testWordcontainAnotherWordOriginGreaterThanDestiny(){

        String origin = "hate";
        String destiny = "hat";

        String expectedChain = "hate,hat";

        String obtainedChain = wordChain.getchain(origin,destiny);

        assertEquals(expectedChain,obtainedChain);

    }


}
