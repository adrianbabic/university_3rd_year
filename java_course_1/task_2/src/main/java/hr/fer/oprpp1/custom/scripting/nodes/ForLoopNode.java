package hr.fer.oprpp1.custom.scripting.nodes;

import java.util.Objects;

import hr.fer.oprpp1.custom.scripting.elems.*;


public class ForLoopNode extends Node {
	private ElementVariable variable;
	private Element startExpression;
	private Element endExpression;
	private Element stepExpression;
	
	public ForLoopNode(ElementVariable variable, Element startExpression, Element endExpression, Element stepExpression) {
		this.variable = variable;
		this.startExpression = startExpression;
		this.endExpression = endExpression;
		this.stepExpression = stepExpression;
	}
	
	public ForLoopNode(ElementVariable variable, Element startExpression, Element endExpression) {
		this(variable, startExpression, endExpression, null);
	}

	public ElementVariable getVariable() {
		return variable;
	}

	public Element getStartExpression() {
		return startExpression;
	}

	public Element getEndExpression() {
		return endExpression;
	}

	public Element getStepExpression() {
		return stepExpression;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{$ " + variable.asText() + " " + startExpression.asText() + " " + endExpression.asText() + " ");
		if(stepExpression != null)
			sb.append(stepExpression.asText() + " ");
		sb.append("$}");
		for(int i = 0; i < this.numberOfChildren(); i++) {
			Node current = this.getChild(i);
			sb.append(current.toString());
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(endExpression, startExpression, stepExpression, variable);
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
		ForLoopNode other = (ForLoopNode) obj;
		return Objects.equals(endExpression, other.endExpression)
				&& Objects.equals(startExpression, other.startExpression)
				&& Objects.equals(stepExpression, other.stepExpression) && Objects.equals(variable, other.variable);
	}
	
	

}
