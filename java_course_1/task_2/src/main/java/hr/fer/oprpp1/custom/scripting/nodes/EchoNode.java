package hr.fer.oprpp1.custom.scripting.nodes;

import java.util.Arrays;

import hr.fer.oprpp1.custom.scripting.elems.Element;

public class EchoNode extends Node {
	private Element[] elements;
	
	public EchoNode(Element[] elements) {
		this.elements = elements;
	}

	public Element[] getElements() {
		return elements;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{$ ");
		for(int i = 0; i < elements.length; i++) {
			sb.append(elements[i].asText());
			sb.append(" ");
		}
		sb.append("$}");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(elements);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EchoNode other = (EchoNode) obj;
		return Arrays.equals(elements, other.elements);
	}
	
	
	
	
}
