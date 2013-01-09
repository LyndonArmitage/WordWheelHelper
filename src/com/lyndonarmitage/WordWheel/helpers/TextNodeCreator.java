package com.lyndonarmitage.WordWheel.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
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
		TextNode root = new TextNode(mustHave, canHave);
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
