package com.network;

public interface Tree<I> {
	void traversal();
	void insert(I value);
	void insertToRoot(Node<I> NodeToCopy);
	boolean containsNode(I value);
	Node<I> returnNodeifExists(I value);
	void delete(I value);
	void eliminateDuplicates();
}
