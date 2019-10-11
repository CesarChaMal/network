package com.network;

import java.util.HashSet;
import java.util.Set;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

	private Node<T> root;

	@Override
	public void traversal() {
		if (root != null) {
			inOrderTraversal(root);
		}
		System.out.println();
	}

	private void inOrderTraversal(Node<T> node) {
		if (node.getLeftChild() != null)
			inOrderTraversal(node.getLeftChild());
		System.out.print(node + "  -->  ");
		if (node.getRightChild() != null)
			inOrderTraversal(node.getRightChild());
	}

	@Override
	public void insert(T value) {
		if (root == null) {
			root = new Node<T>(value);
		} else {
			insertNode(value, root);
		}
	}

	private void insertNode(T value, Node<T> node) {
		if (value.compareTo(node.getData()) < 0) {
			if (node.getLeftChild() != null) {
				insertNode(value, node.getLeftChild());
			} else {
				Node<T> newNode = new Node<T>(value);
				node.setLeftChild(newNode);
			}
		} else if (value.compareTo(node.getData()) > 0){
			if (node.getRightChild() != null) {
				insertNode(value, node.getRightChild());
			} else {
				Node<T> newNode = new Node<T>(value);
				node.setRightChild(newNode);
			}
		}
	}

	@Override
	public void insertToRoot(Node<T> NodeToCopy) {
		if (root == null) {
			return;
		} else {
			insertNodeToRoot(NodeToCopy, root);
		}
	}

	private void insertNodeToRoot(Node<T> NodeToCopy, Node<T> node) {
		T value = NodeToCopy.getData();
		if (value.compareTo(node.getData()) < 0) {
			if (node.getLeftChild() != null) {
				insertNodeToRoot(NodeToCopy, node.getLeftChild());
			} else {
				node.setLeftChild(NodeToCopy);
			}
		} else if (value.compareTo(node.getData()) > 0){
			if (node.getRightChild() != null) {
				insertNodeToRoot(NodeToCopy, node.getRightChild());
			} else {
				node.setRightChild(NodeToCopy);
			}
		}
	}

	@Override
	public boolean containsNode(T value) {
		return containsNodeRecursive(root, value);
	}

	private boolean containsNodeRecursive(Node<T> node, T value) {
		if (node == null) {
			return false;
		}

		if (value.compareTo(node.getData()) == 0) {
//			System.out.println("Founded = " + value);
				return true;
		}

		if (value.compareTo(node.getData()) < 0)
			return containsNodeRecursive(node.getLeftChild(), value);
		else
			return containsNodeRecursive(node.getRightChild(), value);
	}

	public Node<T> returnNodeifExists(T value) {
		return returnNodeRecursive(root, value);
	}

	private Node<T> returnNodeRecursive(Node<T> node, T value) {
		if (node == null) {
			return node;
		}

		if (value.compareTo(node.getData()) == 0) {
//			System.out.println("Founded = " + value);
				return root;
		}

		if (value.compareTo(node.getData()) < 0)
			return returnNodeRecursive(node.getLeftChild(), value);
		else
			return returnNodeRecursive(node.getRightChild(), value);
	}

	public void eliminateDuplicates() {
		if (root != null) {
			Set<Integer> hashSet = new HashSet<>();
			checkDuplicates(root, hashSet);
//			hashMap.entrySet().stream().forEach(e-> System.out.println(e));
		}
	}

	private boolean checkDuplicates(Node<T> node, Set<Integer> hashSet)
	{
		if (node == null)
			return false;

//		Element element = (Element) node.getData();
		T element = node.getData();

//		if (hashSet.contains(element.getValue())){
		if (hashSet.contains(Integer.parseInt(String.valueOf(element)))){
			System.out.println(node.getData());
			delete(node.getData());
		}

//		hashSet.add(element.getValue());
		hashSet.add(Integer.parseInt(String.valueOf(element)));

		return checkDuplicates(node.getLeftChild(), hashSet) || checkDuplicates(node.getRightChild(), hashSet);
	}

	@Override
	public void delete(T value) {

		if (root != null)
			root = delete(root, value);
	}

	private Node<T> delete(Node<T> node, T value) {
		if( node == null ) return node;

		if( value.compareTo(node.getData()) < 0 ) {
			node.setLeftChild( delete(node.getLeftChild(), value) );
		} else if ( value.compareTo(node.getData()) > 0 ) {
			node.setRightChild( delete(node.getRightChild(), value) );
		} else {

			if( node.getLeftChild() == null && node.getRightChild() == null ) {
				System.out.println("Removing a leaf node...");
				return null;
			}

			if( node.getLeftChild() == null ) {
				System.out.println("Removing the right child...");
				Node<T> tempNode = node.getRightChild();
				node = null;
				return tempNode;
			} else if( node.getRightChild() == null ) {
				System.out.println("Removing the left child...");
				Node<T> tempNode = node.getLeftChild();
				node = null;
				return tempNode;
			}

			System.out.println("Removing item with two children...");
			Node<T> tempNode = getPredecessor(node.getLeftChild());
			node.setData(tempNode.getData());
			node.setLeftChild( delete(node.getLeftChild(), tempNode.getData()) );
		}
		return node;
	}

	private Node<T> getPredecessor(Node<T> node) {
		if( node.getRightChild() != null )
			return getPredecessor(node.getRightChild());
		return node;
	}
}
