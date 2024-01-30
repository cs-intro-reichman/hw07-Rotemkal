
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	} 

	public static int levenshtein(String word1, String word2) {
		if (word1.length()==0){
			return word2.length();
		}
		if (word2.length()==0){
			return word1.length();
		}
		if (word1.charAt(0)==word2.charAt(0)){
			return levenshtein(tail(word1), tail(word2));
		}
		else {
			return 1 + Math.min(Math.min((levenshtein(tail(word1),word2)),(levenshtein(tail(word2),word1))),(levenshtein(tail(word1),tail(word2))));
		}
	}

	public static String[] readDictionary(String fileName) {
			String[] dictionary = new String[3000];
			In in = new In(fileName);
			for (int i = 0 ; i < 3000 ; i++){
				String word = in.readLine();
				dictionary[i] = word;
			}
			return dictionary;
		}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String similar = "";
		int min = threshold + 1, diffrence ; 
		for (int i = 0 ; i < 3000 ; i++)
		{
			diffrence = levenshtein(dictionary[i],word);
			if (diffrence < min)
			{
				similar = dictionary[i];
				min = diffrence;
			}
		}
		if (min <= threshold)
		{
		return similar;
		}
		return word;
	}

}
