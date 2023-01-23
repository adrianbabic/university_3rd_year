package hr.fer.oprpp1.custom.scripting.parser;


import hr.fer.oprpp1.custom.scripting.elems.Element;
import hr.fer.oprpp1.custom.scripting.elems.ElementVariable;
import hr.fer.oprpp1.custom.scripting.lexer.Lexer;
import hr.fer.oprpp1.custom.scripting.lexer.TokenType;
import hr.fer.oprpp1.custom.scripting.nodes.DocumentNode;
import hr.fer.oprpp1.custom.scripting.nodes.EchoNode;
import hr.fer.oprpp1.custom.scripting.nodes.ForLoopNode;
import hr.fer.oprpp1.custom.scripting.nodes.Node;
import hr.fer.oprpp1.custom.scripting.nodes.TextNode;
import hr.fer.oprpp1.custom.scripting.parser.collections.ArrayIndexedCollection;
import hr.fer.oprpp1.custom.scripting.parser.collections.ObjectStack;

public class SmartScriptParser {
	private Lexer lexer;
	private ObjectStack tree;
	private DocumentNode docNode;
	
	public SmartScriptParser(String input) {
		this.lexer = new Lexer(input);
		this.tree = new ObjectStack();
		parse();
	}
	
	public DocumentNode getDocumentNode() {
		return docNode;
	}

	private void parse() {
		docNode = new DocumentNode();
		tree.push(docNode);
		Node lastOne = docNode;
		
		ArrayIndexedCollection createdNodes = generateNodes();
		for(int i = 0; i < createdNodes.size(); i++) {
			Node current = (Node)createdNodes.get(i);
			if(current instanceof EchoNode) {
				EchoNode echoNode = (EchoNode) current;
				if(echoNode.getElements().length == 1) {
					Element tmp = echoNode.getElements()[0];
					if(tmp.asText().equals("end")) {
						lastOne.addChildNode(current);
						tree.pop();
						lastOne = (Node)tree.peek();
						continue;
					}
				} else {
					lastOne.addChildNode(current);
				}
			} else if (current instanceof TextNode){
				lastOne.addChildNode(current);
			} else {
				lastOne.addChildNode(current);
				tree.push(current);
				lastOne = current;
			}
		}
		if(tree.size() != 1)
			throw new SmartScriptParserException("The number of opened non-empty tags is not equal to number of <code>{$END$}</code>"
					+ " closing empty tags.");
	}
	
	private ArrayIndexedCollection generateNodes() {
		ArrayIndexedCollection createdNodes = new ArrayIndexedCollection();
		lexer.nextToken();
		while(true) {
			if(isTokenType(TokenType.EOF)) break;
			
			if(isTokenType(TokenType.TEXT)){
				TextNode textNode = new TextNode(getElement().asText());
				createdNodes.add(textNode);
				lexer.nextToken();
				continue;
			} 
			
			if(isTokenType(TokenType.TAG_START)) {
				lexer.nextToken();
				if(!isTokenType(TokenType.VARIABLE)) 
					throw new SmartScriptParserException("Appropriate tag name is missing!");
				ElementVariable variable = (ElementVariable) getElement();
				if(getElement().asText().equals("for")) {
					Element element1 = isAllowedInForLoop();
					Element element2 = isAllowedInForLoop();
					Element element3 = isAllowedInForLoop();
					if(element1 == null || element2 == null) 
						throw new SmartScriptParserException("For loop should contain minimum three elements!");
					if(element3 != null) {
						lexer.nextToken();
						if(!isTokenType(TokenType.TAG_END))
							throw new SmartScriptParserException("For loop should contain maximum four elements!");
					}
					createdNodes.add(new ForLoopNode(variable, element1, element2, element3));
				} else {
					ArrayIndexedCollection elementsArray = new ArrayIndexedCollection();
					elementsArray.add(variable);
					lexer.nextToken();
					while(!isTokenType(TokenType.TAG_END)) {
						if(isTokenType(TokenType.EOF) || isTokenType(TokenType.TEXT) || isTokenType(TokenType.TAG_START)) 
							throw new SmartScriptParserException("Empty tag was opened but never closed!");
						elementsArray.add(getElement());
						lexer.nextToken();
					}
					int size = elementsArray.size();
					Element[] elements = new Element[size];
					for(int i = 0; i < size; i++) {
						elements[i] = (Element)elementsArray.get(i);
					}
					createdNodes.add(new EchoNode(elements));
				}
				lexer.nextToken();
			} else {
				throw new SmartScriptParserException("Parser found an element which should be part of"
						+ "an empty or non-empty tag, but tag was never opened!");
			}
		}
		return createdNodes;
	}

	private boolean isTokenType(TokenType type) {
		return lexer.getToken().getTokenType() == type; 
	}
	
	private Element getElement() {
		return lexer.getToken().getElement();
	}
	
	private Element isAllowedInForLoop() {
		lexer.nextToken();
		if(isTokenType(TokenType.VARIABLE) || isTokenType(TokenType.NUMBER) ||
				isTokenType(TokenType.STRING) ) {
			return getElement();
		} else if(isTokenType(TokenType.TAG_END)) {
			return null;
		} else {
			throw new SmartScriptParserException("For loop should only contain elements of type variable, number or string!");
		}
	}

	
	
}
