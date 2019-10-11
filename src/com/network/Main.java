package com.network;

/*
Task
Write a class NetworkAbstract. The constructor should take a positive integer value indicating the
number of elements in the set. Passing in an invalid value should throw an exception. The
class should also provide two public methods, connect and query. The first method,
connect will take two integers indicating the elements to connect. This method should throw
exceptions as appropriate. The second method, query will also take two integers and should
also throw an exception as appropriate. It should return true if the elements are connected,
directly or indirectly, and false if the elements are not connected. The class can have as
many private or protected members as needed for a good implementation.
*/

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Integer, Element> map = prepareElementsMap();
        Network network = new Network(map);
        processElementsNetwork(network);
        network.getTraversal();
    }

    private static void processElementsNetwork(Network network) {
        try {
            network.connect(new Element(1), new Element(2));
            network.connect(new Element(6), new Element(2));
            network.connect(new Element(2), new Element(4));
            network.connect(new Element(5), new Element(8));
//            network.connect(new Element(8), new Element(1));
            network.connect(new Element(3), new Element(7));
//            network.connect(new Element(7), new Element(1));
//            network.connect(new Element(5), new Element(9));

            network.cleanBinarySearchTree();

            final boolean query1 = network.query(new Element(1), new Element(2));
            final boolean query2 = network.query(new Element(6), new Element(2));
            final boolean query3 = network.query(new Element(2), new Element(4));
            final boolean query4 = network.query(new Element(2), new Element(8));
            final boolean query5 = network.query(new Element(5), new Element(8));
            final boolean query6 = network.query(new Element(3), new Element(7));
            final boolean query7 = network.query(new Element(6), new Element(4));
            final boolean query8 = network.query(new Element(1), new Element(3));
            final boolean query9 = network.query(new Element(2), new Element(1));
            final boolean query10 = network.query(new Element(2), new Element(6));
            final boolean query11 = network.query(new Element(8), new Element(5));
            final boolean query12 = network.query(new Element(8), new Element(1));
//            final boolean query12 = network.query(new Element(5), new Element(9));

            printConnected(query1, new Element(1), new Element(2));
            printConnected(query2, new Element(6), new Element(2));
            printConnected(query3, new Element(2), new Element(4));
            printConnected(query4, new Element(2), new Element(8));
            printConnected(query5, new Element(5), new Element(8));
            printConnected(query6, new Element(3), new Element(7));
            printConnected(query7, new Element(6), new Element(4));
            printConnected(query8, new Element(1), new Element(3));
            printConnected(query9, new Element(2), new Element(1));
            printConnected(query10, new Element(2), new Element(6));
            printConnected(query11, new Element(8), new Element(5));
            printConnected(query12, new Element(8), new Element(1));

//            network.query(new Element(5), new Element(9));
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printConnected(boolean connected, Element firstElement, Element secondElement) {
        if (connected)
            System.out.println(firstElement.getValue() + " and " + secondElement.getValue() + " are connected." );
        else
            System.out.println(firstElement.getValue() + " and " + secondElement.getValue() + " are not connected." );
    }

    private static Map<Integer, Element> prepareElementsMap() {
        Map<Integer, Element> elements = new HashMap<>();
        elements.put(1, new Element(1));
        elements.put(2, new Element(2));
        elements.put(3, new Element(3));
        elements.put(4, new Element(4));
        elements.put(5, new Element(5));
        elements.put(6, new Element(6));
        elements.put(7, new Element(7));
        elements.put(8, new Element(8));
        return elements;
    }
}
