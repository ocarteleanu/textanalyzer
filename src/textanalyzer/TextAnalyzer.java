package textanalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TextAnalyzer {
	//create the lists that will store all the words in the poem, unique words and the pairs of one word and its frequency
	private static ArrayList<String> listOfWords = new ArrayList<>();
	private static ArrayList<String> listOfUniqueWords = new ArrayList<>();
	private static ArrayList<PairOfWordsAndOccurrences> listOfPairs = new ArrayList<>();
	private static Scanner wordScanner;
	public static int occurrenceCounter;
	
	public static ArrayList<String> fileManager(){			
		try {
			//create a file object that will read the initial document
			File myFile = new File("1065-h.htm");
			wordScanner = new Scanner(myFile);
			boolean controlOfStart = false; 
			while(wordScanner.hasNextLine()){
				String newWord = wordScanner.next();
				//set the point where the reading will actually start
				if(newWord.compareTo("<H1") == 0){
					controlOfStart = true;
				}
				//set the point where the reading will stop
				if(newWord.compareTo("<BR><BR><BR><BR>") == 0){
					break;
				}
				//ignore HTML tags
				if(controlOfStart && (! newWord.contains("STYLE")) && (newWord.charAt(0) != '<')
						&& !(newWord.contains("<") ^ newWord.contains(">"))){
					if(newWord.contains("&mdash;")){
						removeAmpersand(newWord);
						if(removeAmpersand(newWord)[0].compareTo("<BR>") != 0){																				
							listOfWords.add(removeAmpersand(newWord)[0]);						
						}
						if(removeAmpersand(newWord)[1].compareTo("<BR>") != 0){	
							;														
							listOfWords.add(removeAmpersand(newWord)[1]);
						}
					}
					else{
						if(newWord.compareTo("<BR>") != 0){													
							listOfWords.add(newWord);
						}
					}
				}
			}
			
			wordScanner.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println(listOfWords.get(listOfWords.size() - 1));
		}
		
		return listOfWords;
	}
	//this method will remove the dashes and create two words from the part before and after the dash
	public static String[] removeAmpersand (String initialStr){
		String[] theTwoStrings = {"", ""};		
		theTwoStrings[0] = initialStr.substring(0, initialStr.indexOf('&')) ;
		theTwoStrings[1] = initialStr.substring(initialStr.indexOf(';') + 1);
		return theTwoStrings;
	}
	//this method will remove any remaining HTML tags not removed in the previous steps
	public static ArrayList<String> cleanArrayNoTags(ArrayList<String> someArray){
		ArrayList<String> finalArray = new ArrayList<>();
		for(String someString : someArray){
			if(someString.contains("<")){
				finalArray.add(someString.substring(0, someString.indexOf('<')));
			}
			else 
			{
				finalArray.add(someString);
			}
		}
		return finalArray;
	}
	//this method will remove punctuation such as commas and quotation marks
	public static ArrayList<String> trimPunctuation(ArrayList<String> beginingArray){
		ArrayList<String> finalArray = new ArrayList<>();
		for(String someString : beginingArray){
			if(someString.length() > 2){
			if(!Character.isLetter(someString.charAt(someString.length() -1))){
				if(!Character.isLetter(someString.charAt(someString.length() -2 ))){
					if(!Character.isLetter(someString.charAt(0))){
						finalArray.add(someString.substring(1, someString.length() - 2));
					}
					else
					{
						finalArray.add(someString.substring(0, someString.length() - 2));
					}
				}
				else if(!Character.isLetter(someString.charAt(0)))
				{
					finalArray.add(someString.substring(1, someString.length() - 1));
				}
				else 
				{
					finalArray.add(someString.substring(0, someString.length() - 1));
				}
			}
			else if(!Character.isLetter(someString.charAt(0)))
			{
				finalArray.add(someString.substring(1));
			}
			else
			{
				finalArray.add(someString);
			}
		}
		
		else {
			finalArray.add(someString);
		}
		}
		return finalArray;
		
	}
		
	public static void main(String[] args) {		
		//populate the array list of all words
		ArrayList <String> allWords = trimPunctuation(cleanArrayNoTags(fileManager()));
		//populate the array list with unique words
		for(String eachWord : allWords){
			if(!listOfUniqueWords.contains(eachWord)){
				listOfUniqueWords.add(eachWord);
			}
		}
		
		//Count the occurrences of all the words and populate the array list of pairs word-frequency
		
		int[] occurrenceCounter = new int[allWords.size() - 1];
		for(int j = 0; j < allWords.size() - 1; j++){
			occurrenceCounter[j] = 0;
		}
		for(String uniqueWord : listOfUniqueWords){
			for(int i = 0; i < allWords.size() - 1; i++){
				if(uniqueWord.compareTo(allWords.get(i)) == 0){
					occurrenceCounter[listOfUniqueWords.indexOf(uniqueWord)] ++;
				}
			}
			PairOfWordsAndOccurrences newPair = new PairOfWordsAndOccurrences(uniqueWord, 
					occurrenceCounter[listOfUniqueWords.indexOf(uniqueWord)]);
			listOfPairs.add(newPair);			
		}
		//sort the pairs array list in descending order of the word frequency
		Collections.sort(listOfPairs, new SortingTool());
		System.out.println("These are the top 20 words, by frequency, in the poem:");
		for(int k = 0; k < 20; k++){
		System.out.printf("%-17s%d%n", listOfPairs.get(k).word ,listOfPairs.get(k).occurrence);
		}		
	}

}
