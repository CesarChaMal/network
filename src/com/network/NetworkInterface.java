package com.network;

public interface NetworkInterface {
    void connect(Element firstElement, Element secondElement) throws ElementNotFoundException;
    boolean query(Element firstElement, Element secondElement) throws ElementNotFoundException;
}
