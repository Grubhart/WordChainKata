package org.grubhart;

public class WordChain {

    public String getchain(String origin, String destiny) {

        StringBuilder chain = new StringBuilder(origin);

        if (( destiny.contains(origin) || origin.contains(destiny)) && ( Math.abs(destiny.length() - origin.length()) == 1 )) {
            chain.append(",").append(destiny);
        }


        return chain.toString();
    }

}
