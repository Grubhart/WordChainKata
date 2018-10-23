package org.grubhart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.HashSet;
import java.util.List;

public class WordChain {

    private HashSet<String> dictionary;

    public void loadDictionary() throws IOException {

        System.out.println("loading dictionary");
        dictionary = new LinkedHashSet();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("dictionary.txt").getFile());
        BufferedReader r = new BufferedReader(new FileReader(file));
        System.out.println("File open successful!");
        		try {
        			String line;
        			while ((line = r.readLine()) != null) {
        				    dictionary.add(line.toLowerCase());
        			}
        		} finally {
        			r.close();
        		}

    }

    public String getchain(String origin, String destiny, ArrayList<String> diccionario) throws IOException {

        loadDictionary();

        System.out.println("starting chain search");
        StringBuilder chain = new StringBuilder(origin);

        if (( destiny.contains(origin) || origin.contains(destiny)) && ( Math.abs(destiny.length() - origin.length()) == 1 )) {
            chain.append(":").append(destiny);
            return chain.toString();
        }else{

            List<String> workDiccionary;
            if(null != diccionario){
                workDiccionary= new ArrayList<String>(diccionario);
            }
            else {
                workDiccionary = new ArrayList<String>(dictionary);
            }

            workDiccionary.remove(origin.toLowerCase());
            workDiccionary.remove(destiny.toLowerCase());

            List<List<String>> chainListGroup = new ArrayList<List<String>>();
            List<String> chainList = new ArrayList<String>();
            chainList.add(origin);
            chainListGroup.add(chainList);

            int i=0;
            int count = 0;
            while ( notChainCompleted(destiny, chainList) && count<20 ) {

                    while (i<workDiccionary.size()) {
                        String chainElement = workDiccionary.get(i);
                        List<List<String>> listTsoAdd = new ArrayList<List<String>>();
                        for (List<String> candidateList: chainListGroup) {
                            if( isValidChainElement(candidateList.get(candidateList.size()-1),chainElement,destiny,candidateList)   ){
                                if(differenceBeetween(candidateList.get(candidateList.size()-1),destiny)==differenceBeetween(chainElement,destiny)){
                                    System.out.println("created candidate list");
                                    listTsoAdd.add(new ArrayList<String>(candidateList));
                                }
                                candidateList.add(chainElement);
                                System.out.println("added: "+chainElement);
                            }
                        }
                        chainListGroup.addAll(listTsoAdd);
                        i++;
                    }
                i=0;
                count++;
                System.out.println("cycle: "+count);

            }

            List<String> minSizeChain = new ArrayList<String>();
            for(List<String> candidateList:chainListGroup){
                if( isPenultimate(candidateList.get(candidateList.size()-1), destiny)) {
                    candidateList.add(destiny);
                    minSizeChain= candidateList;
                }
            }

            if( !minSizeChain.isEmpty()){
                chain = buildChain(chainList);
                return chain.toString();
            }else{
                chain = buildChain(chainList);
                return "error: no se pudo completar una cadena= "+chain.toString();
            }

        }

    }

    private boolean notChainCompleted(String destiny, List<String> chainList) {
        return !chainList.get(chainList.size()-1).equals(destiny);
    }

    private StringBuilder buildChain(List<String> chainList) {
        StringBuilder chain = new StringBuilder();

        for (int i = 0; i <chainList.size() ; i++) {

            chain.append(chainList.get(i));
            if(i!= chainList.size()-1){
                chain.append(":");
            }
        }
        return chain;
    }

    private boolean isPenultimate(String word, String destiny) {
        return differenceBeetween(word,destiny).equals(1);
    }

    private boolean isValidChainElement(String lastValidCandidate, String candidate, String destiny, List<String> chainList) {
        Integer differenceBeetweenLastValidCandidateAndDestiny = differenceBeetween(lastValidCandidate,destiny);
        Integer differenceBeetweenLastValidCandidateAndCandidate = differenceBeetween(lastValidCandidate,candidate);
        Integer differenceBeetweenCandidateAndDestiny = differenceBeetween(candidate,destiny);


        boolean valid = false;

        if(candidate.length() < destiny.length())
                valid =differenceBeetweenLastValidCandidateAndCandidate.equals(1)
                && differenceBeetweenLastValidCandidateAndDestiny >= differenceBeetweenCandidateAndDestiny && !chainList.contains(candidate);
        else if (candidate.length()==destiny.length()){
            valid =differenceBeetweenLastValidCandidateAndCandidate.equals(1)
                    && differenceBeetweenLastValidCandidateAndDestiny > differenceBeetweenCandidateAndDestiny && !chainList.contains(candidate);
        }

        return valid;

    }

    protected Integer differenceBeetween(String word, String destiny) {

        String workWord = word.toLowerCase();
        String workDestiny = destiny.toLowerCase();

        Integer difference = 0;
        boolean differentSize = workWord.length()!=workDestiny.length();

        if(differentSize){
            String longest;
            String shortest;
            if(workDestiny.length()>workWord.length()){
                longest=workDestiny;
                shortest=workWord;
            }else{
                longest=workWord;
                shortest=workDestiny;
            }
            if(longest.contains(shortest)){
                String[] fragments = longest.split(shortest);
                for (int i = 0; i < fragments.length; i++) {
                    difference += fragments[i].length();
                }
            }else{
                for (int i = 0; i < shortest.length(); i++) {
                    if (shortest.charAt(i) != longest.charAt(i)) {
                        difference++;
                    }
                }
                difference+=longest.length()-shortest.length();
            }
        }else{
            for (int i = 0; i < workWord.length(); i++) {
                if (workWord.charAt(i) != workDestiny.charAt(i)) {
                    difference++;
                }
            }
        }

        return difference;
    }


}
