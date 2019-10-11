package com.network;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class NetworkTest {

    private Network network;
    private static Map<Integer, Element> elements;

    @Before
    public void setUp() throws Exception {
        Map<Integer, Element> map = prepareElementsMap();
        network = new Network(map);
        network.connect(new Element(1), new Element(2));
        network.connect(new Element(6), new Element(2));
        network.connect(new Element(2), new Element(4));
        network.connect(new Element(5), new Element(8));
    }

    private static Map<Integer, Element> prepareElementsMap() {
        elements = new HashMap<>();
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

    @Test(expected = ElementNotFoundException.class)
    public void whenIsInvalidElementInConnect() throws ElementNotFoundException {
        network.connect(new Element(5), new Element(9));
    }

    @Test(expected = ElementNotFoundException.class)
    public void whenIsInvalidElementInQuery() throws ElementNotFoundException {
        network.query(new Element(5), new Element(9));
    }

    @Test
    public void whenElementsAreNotConnected() throws ElementNotFoundException {
        assertFalse(new Element(1).getValue() + " and " + new Element(8).getValue() + " are connected", network.query(new Element(1), new Element(8)));
        assertFalse(new Element(3).getValue() + " and " + new Element(7).getValue() + " are connected", network.query(new Element(3), new Element(7)));
        assertFalse(new Element(2).getValue() + " and " + new Element(5).getValue() + " are connected", network.query(new Element(2), new Element(5)));
    }

    @Test
    public void whenElementsAreConnected() throws ElementNotFoundException {
        assertTrue(new Element(1).getValue() + " and " + new Element(2).getValue() + " are connected", network.query(new Element(1), new Element(2)));
        assertTrue(new Element(6).getValue() + " and " + new Element(2).getValue() + " are connected", network.query(new Element(6), new Element(2)));
        assertTrue(new Element(2).getValue() + " and " + new Element(4).getValue() + " are connected", network.query(new Element(2), new Element(4)));
        assertTrue(new Element(5).getValue() + " and " + new Element(8).getValue() + " are connected", network.query(new Element(5), new Element(8)));
    }
}