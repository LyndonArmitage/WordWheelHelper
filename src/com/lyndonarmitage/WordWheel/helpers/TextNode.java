package com.lyndonarmitage.WordWheel.helpers;

import java.util.ArrayList;

/**
 * Created By: Lyndon Armitage
 * Date: 09/01/13
 */
public class TextNode {

	private char addedCharacter = '\0';
	private String textString = "";
	private TextNode parent = null;
	private ArrayList<TextNode> children = new ArrayList<TextNode>();

	public TextNode() {
	}

	public TextNode(char mustHave, String canHave) {
		makeRootNode(mustHave, canHave);
	}

	public TextNode(char character, TextNode parent) {
		this.parent = parent;
		addedCharacter = character;
		textString = parent.getTextString() + character;
	}

	public void makeRootNode(char mustHave, String canHave) {
		this.addedCharacter = mustHave;
		this.textString = String.valueOf(mustHave);
		generateChildren();
	}

	private void generateChildren() {

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
