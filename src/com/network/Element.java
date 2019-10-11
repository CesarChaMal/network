package com.network;

public class Element implements Comparable<Element> {

	private int value;

	public Element(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(Element element) {
		return value - element.getValue();
	}

	@Override
//	public String toString() {
//		return "Element - " + this.value;
//	}
	public String toString() {
		return String.valueOf(this.value);
	}
}
