package hr.fer.oprpp1.custom.scripting.nodes;

import java.util.Objects;

public class TextNode extends Node {
	private String text;
	
	public TextNode(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(text);
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
		TextNode other = (TextNode) obj;
		return Objects.equals(text, other.text);
	}
	
	
}
