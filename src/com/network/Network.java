package com.network;

import java.util.HashMap;
import java.util.Map;

public class Network implements NetworkInterface {

    private Map<Integer, Element> valuesMap = new HashMap<>();

    private Map<Integer, Tree<Element>> binarySearchTreesMap = new HashMap<>();

    private Tree<Element> binarySearchTree = new BinarySearchTree<>();
    private int cont;

    public Network(Map<Integer, Element> elements) {
        this.valuesMap = elements;
    }

    @Override
    public void connect(Element firstElement, Element secondElement) throws ElementNotFoundException {
        checkInvalidValue(firstElement);
        checkInvalidValue(secondElement);

        verifyPreviousElementsConnected(firstElement, secondElement);

        binarySearchTree.insert(firstElement);
        binarySearchTree.insert(secondElement);
        binarySearchTreesMap.put(cont, binarySearchTree);
    }

    private void verifyPreviousElementsConnected(Element firstElement, Element secondElement) {
        int i = 0;
        int temp = 0;
        for (Map.Entry<Integer, Tree<Element>> item : binarySearchTreesMap.entrySet()) {
            Tree<Element> bst = item.getValue();
            if (isNotAPreviousElement(firstElement, secondElement, bst)){
                binarySearchTree = new BinarySearchTree<>();
                cont++;
            } else {
                Node<Element> nodeFirstElement = null;
                Node<Element> nodeSecondElement = null;
                if (isAPreviousElement(firstElement, secondElement, binarySearchTree)) {
                    if (binarySearchTree.containsNode(firstElement))
                        nodeFirstElement = binarySearchTree.returnNodeifExists(firstElement);
                    if (binarySearchTree.containsNode(secondElement))
                        nodeSecondElement = binarySearchTree.returnNodeifExists(secondElement);
                }
                binarySearchTree = bst;
                if (nodeFirstElement != null)
                    binarySearchTree.insertToRoot(nodeFirstElement);
                if (nodeSecondElement != null)
                    binarySearchTree.insertToRoot(nodeSecondElement);
                temp=cont;
                cont=i;
                break;
            }
            i++;
        }
    }

    public void getTraversal() {
        for (Map.Entry<Integer, Tree<Element>> item : binarySearchTreesMap.entrySet()) {
            Tree<Element> bst = item.getValue();
            bst.traversal();
        }
    }

    private boolean isNotAPreviousElement(Element firstElement, Element secondElement, Tree<Element> binarySearchTree) {
        return !(binarySearchTree.containsNode(firstElement) || binarySearchTree.containsNode(secondElement));
    }

    private boolean isAPreviousElement(Element firstElement, Element secondElement, Tree<Element> binarySearchTree) {
        return binarySearchTree.containsNode(firstElement) || binarySearchTree.containsNode(secondElement);
    }

    private void checkInvalidValue(Element element) throws ElementNotFoundException {
        if (!valuesMap.containsKey(element.getValue()))
            throw new ElementNotFoundException("Invalid value = " + element);
    }

    public void cleanBinarySearchTree() {
        for (Map.Entry<Integer, Tree<Element>> item : binarySearchTreesMap.entrySet()) {
            Integer key = item.getKey();
            Tree<Element> bst = item.getValue();
            bst.eliminateDuplicates();
        }
    }

    @Override
    public boolean query(Element firstElement, Element secondElement) throws ElementNotFoundException {
        checkInvalidValue(firstElement);
        checkInvalidValue(secondElement);
        for (Map.Entry<Integer, Tree<Element>> item : binarySearchTreesMap.entrySet()) {
            Tree<Element> bst = item.getValue();
            final boolean connected = bst.containsNode(firstElement) && bst.containsNode(secondElement);
            if (!connected)
                continue;
            return connected;
        }
        return false;
    }
}
