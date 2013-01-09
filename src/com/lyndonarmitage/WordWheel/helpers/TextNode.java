package com.lyndonarmitage.WordWheel.helpers;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created By: Lyndon Armitage
 * Date: 09/01/13
 */
public class TextNode {

	private char addedCharacter = '\0';
	private String textString = "";
	private TextNode parent = null;
	public ArrayList<TextNode> children = new ArrayList<TextNode>();

	public TextNode() {
	}

	public TextNode(char mustHave, String canHave, HashMap<Character, Integer> charMap) {
		makeRootNode(mustHave, canHave, charMap);
	}

	public TextNode(char character, TextNode parent) {
		this.parent = parent;
		addedCharacter = character;
		textString = parent.getTextString() + character;
		textString = reorderString(textString);
		System.out.println("New node: " + addedCharacter + " = " + textString);
	}

	public void makeRootNode(char mustHave, String canHave, HashMap<Character, Integer> charMap) {
		this.addedCharacter = mustHave;
		this.textString = String.valueOf(mustHave);
		generateChildren(canHave, charMap);
	}

	//static int DD = 0;
	private void generateChildren(String canHave, HashMap<Character, Integer> charMap) {
		for (int i = 0; i < canHave.length(); i++) {
			// only make children with characters we haven't used yet
			boolean characterPresent = charCount(canHave.charAt(i)) >= charMap.get(canHave.charAt(i));

//			if (canHave.charAt(i) == addedCharacter || textString.contains(String.valueOf(canHave.charAt(i)))) continue;
			if (characterPresent) continue;
			TextNode node = new TextNode(canHave.charAt(i), this);
			if (addChild(node)) {
				//System.out.println(++DD);
				node.generateChildren(canHave, charMap);
			}
		}
	}

	private boolean addChild(TextNode N) {
		boolean addIt = true;
		for (TextNode T : children) {
			if (T.getTextString().equals(N.getTextString())) {
				addIt = false;
				break;
			}
		}
		if (addIt) {
			children.add(N);
			return true;
		} else {
			return false;
		}
	}

	private int charCount(char c) {
		int count = 0;
		for (char C : textString.toCharArray()) {
			if (C == c) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Orders the string
	 */
	public static String reorderString(String S) {
		char content[] = S.toCharArray();
		java.util.Arrays.sort(content);
		return String.valueOf(content);
	}

	public void printChildren() {
		for (TextNode N : children) {
			System.out.println(N.getTextString());
		}
	}

	public void printTree() {
		for (TextNode N : children) {
			System.out.println(N.getTextString());
			N.printTree();
		}
	}

	public char getAddedCharacter() {
		return addedCharacter;
	}

	public void setAddedCharacter(char addedCharacter) {
		this.addedCharacter = addedCharacter;
	}

	public String getTextString() {
		return textString;
	}

}
