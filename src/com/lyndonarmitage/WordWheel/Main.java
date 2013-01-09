package com.lyndonarmitage.WordWheel;

import com.lyndonarmitage.WordWheel.helpers.TextNode;
import com.lyndonarmitage.WordWheel.helpers.TextNodeCreator;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Created By: Lyndon Armitage
 * Date: 09/01/13
 */
public class Main {

	public char mustHave = 'i';
	public String canHave = "enrdigna";
	// This example should find the word ingrained
	public ArrayList<String> words = new ArrayList<String>();

	public static void main(String args[]) {
		Main M = new Main();
		TextNodeCreator T = TextNodeCreator.instanceOf();
		TextNode root = T.createNewNode(M.mustHave, M.canHave);
		T.loadWords("wordlist.txt");
		LinkedHashSet<String> foundWords = T.findWords(root);
		for (String S : foundWords) {
			System.out.println(S);
		}
//		TextNode.makeMap(M.mustHave, M.canHave);
	}

}
