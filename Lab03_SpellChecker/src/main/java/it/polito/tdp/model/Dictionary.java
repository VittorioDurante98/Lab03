package it.polito.tdp.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	List<String> dizionarioInglese = new LinkedList<>();
	List<String> dizionarioItaliano = new LinkedList<>();
	//List<String> dizionario = new LinkedList<>();
	List<String> dizionario = new ArrayList<>();
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
		Collections.sort(dizionario);
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
	
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList){
		
		//List<RichWord> listaErr = new LinkedList<>();
		List<RichWord> listaErr = new ArrayList<>();
		for(String s: inputTextList) {
			if(!searchM(s)) {
				listaErr.add(new RichWord(s, false));
			}
				
		}
		return listaErr;
	}
	
	public boolean searchM(String sTemp){
		int inizio=0;
		int fine = dizionario.size();
		
		while(inizio != fine) {
			int medio = inizio+(fine-inizio)/2;
			if(sTemp.compareToIgnoreCase(dizionario.get(medio))==0)
				return true;
			else if(sTemp.compareToIgnoreCase(dizionario.get(medio))>0)
				inizio=medio+1;
			else
				fine=medio;
		}
		return false;
	}

}
