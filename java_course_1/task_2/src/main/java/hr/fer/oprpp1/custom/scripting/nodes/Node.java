package hr.fer.oprpp1.custom.scripting.nodes;

import java.util.Objects;

import hr.fer.oprpp1.custom.collections.ArrayIndexedCollection;

/**	Base class which is a top class for all other node related classes.
 *  
 * 	@author adrian
 *
 */
public class Node {
	private ArrayIndexedCollection array;
	private int numberOfChildren;
	
	public Node() {
		numberOfChildren = 0;
	}
	
	public void addChildNode(Node child) {
		if(numberOfChildren == 0) this.array = new ArrayIndexedCollection();
		array.add(child);
		numberOfChildren++;
	}
	
	public int numberOfChildren() {
		return numberOfChildren;
	}
	
	public Node getChild(int index) {
		if(index >= array.size() || index < 0) throw new IllegalArgumentException("Given index is invalid!");
		return (Node)array.get(index);
	}

	@Override
	public int hashCode() {
		return Objects.hash(array, numberOfChildren);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		return Objects.equals(array, other.array) && numberOfChildren == other.numberOfChildren;
	}
	
	
}
