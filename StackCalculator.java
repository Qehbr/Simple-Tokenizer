/**
 * Class describing calculator for logical expressions.
 */
public class StackCalculator extends Calculator {

    //constructor
    StackCalculator(boolean[] assigner) {
        super(assigner);
    }

    /**
     * Evaluate function gets a valid postfix expression and return it's value
     */
    @Override
    public boolean evaluate(String expr) {

        ExpTokenizer tokenizer = new ExpTokenizer(expr, assigner);  //Tokenizer for iterating through tokens
        StackAsArray tokenStack = new StackAsArray();               //Stack for storing tokens
        int numOfTokens = tokenizer.countTokens();                  //Variable numOfTokens is number of tokens in expression

        for (int i = 0; i < numOfTokens; i++) {                     //Looping through tokens
            CalcToken token = tokenizer.nextElement();              //Taking the next token
            if (token instanceof Op) {
                ValueToken result;                                  //Variable result stores the result of operation
                if (token instanceof BinaryOp)                      //If operation is BinaryOperation
                {
                    ValueToken op1 = (ValueToken) tokenStack.pop();  //Taking top 2 elements from the stack and assign them to op1, op2
                    ValueToken op2 = (ValueToken) tokenStack.pop();
                    result = new ValueToken(((BinaryOp) token).operate(op1.getValue(), op2.getValue())); //Performing the appropriate operation on operands
                } else {                                              //If operation is unary operation
                    ValueToken op = (ValueToken) tokenStack.pop();   //Taking the top element
                    result = new ValueToken(((NotOp) token).operate(op.getValue())); //Performing the appropriate operation on operand
                }
                tokenStack.push(result);                            //Push the result to the stack
            } else {                                                   //If token is not operation
                tokenStack.push(token);                             //Push the token to the stack
            }
        }
        return ((ValueToken) tokenStack.pop()).getValue();           //Return the top element of the stack representing the actual result of the expression
    }

    /**
     * infixToPostfix function gets a valid infix expression and transforms it to valid postfix expression
     */
    public String infixToPostfix(String expr) {

        StringBuilder postFix = new StringBuilder();                //String variable postFix representing the postfix form of given infix expression (expr)
        ExpTokenizer tokenizer = new ExpTokenizer(expr, assigner);  //Tokenizer for iterating through tokens
        StackAsArray tokenStack = new StackAsArray();               //Stack for storing tokens
        int numOfTokens = tokenizer.countTokens();                  //Variable numOfTokens is number of tokens in expression

        for (int i = 0; i < numOfTokens; i++) {                     //Looping through tokens
            CalcToken token = tokenizer.nextElement();              //Taking the next token
            if (token instanceof OpenBracket) {                     //If token is opening bracket
                tokenStack.push(token);                             //Pushing opening bracket to the stack
            } else if (token instanceof CloseBracket) {             //If token is closing bracket
                while (!tokenStack.isEmpty()) {                       //Loop until stack will be empty or break
                    Object tempToken = tokenStack.pop();            //Taking the top element of the stack
                    if (tempToken instanceof OpenBracket)            //If token is opening bracket
                    {
                        break;                                      //Stop looping through tokens in stack
                    } else                                            //If token is not opening bracket
                    {
                        postFix.append(tempToken.toString());       //Concatenate it to the postfix expression
                    }
                }
            } else if (token instanceof Op) {                       //if token is operation
                while (!tokenStack.isEmpty()) {                           //Loop until stack will be empty or break
                    Object tempToken = tokenStack.pop();                //Taking the top element of the stack
                    if (!(tempToken instanceof Op)) {                     //If that element in the stack is not operation
                        tokenStack.push(tempToken);                         //Return it to the top of the stack and stop looping through the tokens in the stack
                        break;
                    } else {                                              //If that element in the stack os operation
                        if (((Op) token).getPrecedence() <= ((Op) tempToken).getPrecedence())    //Check if our token has lower precedence than token from the stack
                        {
                            tokenStack.push(tempToken);                                         //If so return it to the top of the stack and stop looping through the tokens in the stack
                            break;
                        } else {
                            postFix.append(tempToken.toString());                               //If not concatenate it to the postfix expression
                        }
                    }
                }
                tokenStack.push(token);     //If stack was empty just push the operation to the stack
            } else if (token instanceof ValueToken) {   //If token is ValueToken (or VariableToken)
                postFix.append(token.toString());       //Concatenate it to the postfix expression
            }
        }
        //Now loop through tokens in expression is ended

        while (!tokenStack.isEmpty()) {                     //If stack is still is not empty
            postFix.append(tokenStack.pop().toString());    //Concatenate each element from the stack to the postfix expression until stack will be empty
        }
        return postFix.toString().replace("", " ").trim();  //Add space between each two element in the postfixExpression (to make it valid) and return the result

    }
}
