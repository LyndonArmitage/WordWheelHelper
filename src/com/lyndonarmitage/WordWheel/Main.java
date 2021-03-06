package com.lyndonarmitage.WordWheel;

import com.lyndonarmitage.WordWheel.helpers.TextNode;
import com.lyndonarmitage.WordWheel.helpers.TextNodeCreator;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * WordWheel Solver
 * <p/>
 * Created By: Lyndon Armitage
 * Date: 09/01/13
 * <p/>
 * Currently it is very slow and needs to be optimised.
 */
public class Main {

	public char mustHave = 'c';
	public String canHave = "tliolesa";
	//	public char mustHave = 'i';
//	public String canHave = "enrdigna";
	// This example should find the word ingrained as the biggest word

	public static void main(String args[]) {
		Main M = new Main();
		TextNodeCreator T = TextNodeCreator.instanceOf();
		TextNode root = T.createNewNode(M.mustHave, M.canHave);
		T.loadWords("wordlist.txt");
		ArrayList<String> textStringsList = new ArrayList<String>();
		LinkedHashSet<String> foundWords = T.findWords(root, textStringsList);
		for (String S : foundWords) {
			System.out.println(S);
		}
	}

}
