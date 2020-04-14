package it.polito.tdp.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	List<String> dizionarioInglese = new LinkedList<>();
	List<String> dizionarioItaliano = new LinkedList<>();
	List<String> dizionario = new LinkedList<>();
	
	public void loadDictionary() {
		
		
		try {
			FileReader fr = new FileReader("src/main/resources/English.txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			
			while ((word = br.readLine()) != null) {
			// Aggiungere parola alla struttura dati
				dizionario.add(word.toLowerCase());
			}
			br.close();
			
		} catch (IOException e){
			System.out.println("Errore nella lettura del file");
		}
		
		try {
			FileReader fr = new FileReader("src/main/resources/Italian.txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			
			while ((word = br.readLine()) != null) {
			// Aggiungere parola alla struttura dati
				dizionario.add(word.toLowerCase());
			}
			br.close();
			
		} catch (IOException e){
			System.out.println("Errore nella lettura del file");
		}
		
		//dizionario.addAll(dizionarioInglese);
		//dizionario.addAll(dizionarioItaliano);
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		List<RichWord> e = new LinkedList<>();
		for(String s: inputTextList) {
			RichWord r;
			if(!dizionario.contains(s)) {
				r = new RichWord(s, false);
				e.add(r);
			}
			
		}
		
		return e;
	}

}
