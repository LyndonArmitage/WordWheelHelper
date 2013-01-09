package com.lyndonarmitage.WordWheel;

import com.lyndonarmitage.WordWheel.helpers.TextNode;

/**
 * Created By: Lyndon Armitage
 * Date: 09/01/13
 */
public class Main {

	public char mustHave = 'a';
	public String canHave = "ct";

	public static void main(String args[]) {
		Main M = new Main();
		TextNode root = M.constructTextComboTree();
		root.printTree();

	}

	public TextNode constructTextComboTree() {
		TextNode root = new TextNode();
		root.makeRootNode(mustHave, canHave);
		return root;
	}
}
