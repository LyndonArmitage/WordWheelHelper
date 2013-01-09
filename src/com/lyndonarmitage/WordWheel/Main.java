package com.lyndonarmitage.WordWheel;

import com.lyndonarmitage.WordWheel.helpers.TextNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Created By: Lyndon Armitage
 * Date: 09/01/13
 */
public class Main {

	public char mustHave = 'i';
	public String canHave = "enrdigna";
	// This example should find the word ingrained
	// It doesn't at the moment but finds the rest of the words
	public ArrayList<String> words = new ArrayList<String>();

	public static void main(String args[]) {
		Main M = new Main();
		TextNode root = M.constructTextComboTree();
//		root.printTree();
		M.loadWords("wordlist.txt", M.canHave.length() + 1);

		LinkedHashSet<String> foundWords = M.findWords(root);
		System.out.println("\nFound Words:");
		for (String S : foundWords) {
			System.out.println(S);
		}
	}

	public TextNode constructTextComboTree() {
		TextNode root = new TextNode();
		root.makeRootNode(mustHave, canHave);
		return root;
	}

	public void loadWords(String filename, int maxLength) {
		Scanner in;
		try {
			in = new Scanner(new BufferedReader(new FileReader(filename)));
			while (in.hasNext()) {
				String S = in.next();
				if (S.length() > maxLength || S.indexOf(mustHave) == -1) continue;
				//System.out.println(S);
				words.add(S);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LinkedHashSet<String> findWords(TextNode root) {
		LinkedHashSet<String> found = new LinkedHashSet<String>();
		for (TextNode N : root.children) {
			found.addAll(findWords(N));
			for (String S : words) {
				if (N.getTextString().length() != S.length()) continue;
				String orderedWord = TextNode.reorderString(S);
				if (orderedWord.equals(N.getTextString())) {
					found.add(S);
				}
			}

		}
		return found;
	}
}
