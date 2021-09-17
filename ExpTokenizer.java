public class ExpTokenizer {
	
	//fields
	private String [] result;
	private int index;
	private boolean[] assigner;
	
	//constructor
	public ExpTokenizer(String exp, boolean[] assigner) {
		this.result = exp.split(" ");
		this.index = 0;
		this.assigner = assigner;
	}
	
	public CalcToken nextElement() { //we create for each token the suitable object.
		CalcToken resultToken = null;
		String token =  nextToken();

		if(token.equals("&")){resultToken = new AndOp();}
		else if(token.equals("!")){resultToken = new NotOp();}
		else if(token.equals("|")){resultToken = new OrOp();}
		else if(token.equals("^")){resultToken = new XorOp();}
		else if(token.equals("(")){resultToken = new OpenBracket();}
		else if(token.equals(")")){resultToken = new CloseBracket();}
		else if ("TF".contains(token)){resultToken =  new ValueToken("T".equals(token));}
		// if the token is none of the above, it has to be a variable. therefore, we would create variableToken.
		else{
			resultToken = new VariableToken(token, assigner);}
		return resultToken;	
	}
	
	public boolean hasMoreElements() {
		return (this.index != this.result.length);
	}
	
	public String nextToken() {
		return this.result[index++];
	}


	public int countTokens() {
		return (this.result.length - this.index);
	}
	

}