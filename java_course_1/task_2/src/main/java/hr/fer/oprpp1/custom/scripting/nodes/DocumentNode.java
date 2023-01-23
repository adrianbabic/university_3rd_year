package hr.fer.oprpp1.custom.scripting.nodes;

public class DocumentNode extends Node {

	public DocumentNode() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < this.numberOfChildren(); i++) {
			Node current = this.getChild(i);
			sb.append(current.toString());
		}
		return sb.toString();
	}
	
	
	
}
