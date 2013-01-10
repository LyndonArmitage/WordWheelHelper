package com.lyndonarmitage.WordWheel.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Created By: Lyndon Armitage
 * Date: 09/01/13
 */
public class TextNodeCreator {

	public ArrayList<String> words = new ArrayList<String>();
	private char mustHave;
	private String canHave;
	private TextNode root;

	private static TextNodeCreator instance = null;

	private TextNodeCreator() {
	}

	public static TextNodeCreator instanceOf() {
		if (instance == null) {
			instance = new TextNodeCreator();
		}
		return instance;
	}

	public TextNode createNewNode(char mustHave, String canHave) {
		this.mustHave = mustHave;
		this.canHave = canHave;
		TextNode root = new TextNode(mustHave, canHave, makeCharMap(mustHave, canHave));
		return root;
	}

	public void loadWords(String filename) {
		int maxLength = canHave.length() + 1;
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

	/**
	 * This function will find the words that can be made from a tree of potential letter combinations.<br />
	 *
	 * @param root            Is the root node to use
	 * @param textStringsList This is an ArrayList used to keep a record of the text of the nodes visited, that way we can ignore duplicate branches.
	 * @return
	 */
	public LinkedHashSet<String> findWords(TextNode root, ArrayList<String> textStringsList) {
		LinkedHashSet<String> found = new LinkedHashSet<String>();
		for (TextNode N : root.children) {
			if (textStringsList.contains(N.getTextString())) continue;
			else {
				textStringsList.add(N.getTextString());
			}
			found.addAll(findWords(N, textStringsList));
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

	/**
	 * Gets a map of the characters for checking if they have all been used
	 *
	 * @param mustHave
	 * @param canHave
	 * @return
	 */
	private HashMap<Character, Integer> makeCharMap(char mustHave, String canHave) {
		HashMap<Character, Integer> charMap = new HashMap<Character, Integer>(); // should make this a static variable?
		charMap.put(mustHave, 1);
		for (char C : canHave.toCharArray()) {
			if (charMap.containsKey(C)) {
				charMap.put(C, charMap.get(C) + 1);
			} else {
				charMap.put(C, 1);
			}
		}

//		for (char C : canHave.toCharArray()) {
//			System.out.println(C + "=" + charMap.get(C));
//		}
		return charMap;
	}
}
