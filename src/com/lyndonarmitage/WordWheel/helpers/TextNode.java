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
