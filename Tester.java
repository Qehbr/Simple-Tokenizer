/**
 * This is a testing framework. Use it extensively to verify that your code is working
 * properly.
 */
public class Tester {

	private static boolean testPassed = true;
	private static int testNum = 0;

	/**
	 * This entry function will test all classes created in this assignment.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {

		// Each function here should test a different class.

		//testing operators
		testAndOp();
		testOrOp();
		testNotOp();
		testXorOp();
		testPrecedence();

		//testing rest of the tokens
		testValueToken();
		testVariableToken();


		//testing classes using tokens
		testExpTokenizer();
		testStackAsArray();

		//testing calculator
    	testStackCalculator();


		// Notifying the user that the code have passed all tests.
		if (testPassed) {
			System.out.println("All " + testNum + " tests passed!");
		}
	}

	/**
	 * This utility function will count the number of times it was invoked.
	 * In addition, if a test fails the function will print the error message.
	 * @param exp The actual test condition
	 * @param msg An error message, will be printed to the screen in case the test fails.
	 */
	private static void test(boolean exp, String msg) {
		testNum++;

		if (!exp) {
			testPassed = false;
			System.out.println("Test " + testNum + " failed: "  + msg);
		}
	}



	/** Testing operators **/
	//checks AndOp class
	private static void testAndOp() {
		AndOp op = new AndOp();

		test(op.toString().equals("&"), "The string & should be printed.");

		test(op.operate(true, true), "true & true = true");
		test(!op.operate(true, false), "true & false = false");
		test(!op.operate(false, true), "false & true = false");
		test(!op.operate(false, false), "false & false = false");
	}

	//checks NotOp class
	private static void testNotOp() {
		NotOp op = new NotOp();

		test(op.toString().equals("!"), "The string ! should be printed.");
		test(!op.operate(true), "!true = false");
		test(op.operate( false), "!false = true");
	}

	//checks OrOp class
	private static void testOrOp() {
		OrOp op = new OrOp();

		test(op.toString().equals("|"), "The string | should be printed.");

		test(op.operate(true, true), "true | true = true");
		test(op.operate(true, false), "true | false = true");
		test(op.operate(false, true), "false | true = true");
		test(!op.operate(false, false), "false | false = false");
	}

	//checks XorOp class
	private static void testXorOp() {
		XorOp op = new XorOp();

		test(op.toString().equals("^"), "The string ^ should be printed.");

		test(!op.operate(true, true), "true ^ true = false");
		test(op.operate(true, false), "true ^ false= true");
		test(op.operate(false, true), "false ^ true = true");
		test(!op.operate(false, false), "false ^ false = false");
	}

	private static void testPrecedence(){
		AndOp and = new AndOp();
		NotOp not = new NotOp();
		XorOp xor = new XorOp();
		OrOp or = new OrOp();

		test(not.getPrecedence()<xor.getPrecedence()&&xor.getPrecedence()<and.getPrecedence()&&and.getPrecedence()<or.getPrecedence(), "1.Not, 2.Xor, 3.And, 4.Or");
	}



	/** Testing rest of the tokens **/
	//Checks the ValueToken class.
	private static void testValueToken() {
		ValueToken t = new ValueToken(true);
		ValueToken f = new ValueToken(false);

		test(t.getValue(), "Value of 't' should be true");
		test(!f.getValue(), "Value of 'f' should be false");

		test(t.toString().equals("T"), "The string T should be printed.");
		test(f.toString().equals("F"), "The string F should be printed.");

	}

	//Checks the VariableToken class.
	private static void testVariableToken() {
		boolean[] assigner = {true, false, false, true, true, false, true, false, false};

		ValueToken t1 = new VariableToken("a", assigner);
		ValueToken t2 = new VariableToken("b", assigner);
		ValueToken t3 = new VariableToken("g", assigner);

		test(t1.getValue(), "Value of 'a' should be true");
		test(!t2.getValue(), "Value of 'b' should be false");
		test(t3.getValue(), "Value of 'g' should be true");

		test(t1.toString().equals("a"), "The string a should be printed.");
		test(t2.toString().equals("b"), "The string b should be printed.");
		test(t3.toString().equals("g"), "The string g should be printed.");


	}



    /** Testing classes using tokens **/
	//Checks the ExpTokenizer class.
	private static void testExpTokenizer() {
        //checks tokenizer working with value and variable tokens
		boolean[] assigner = {true, false, false, true, true, false, true, false, false};
		ExpTokenizer tokenizerValue = new ExpTokenizer("a b g i F", assigner);

		test(tokenizerValue.countTokens()==5, "Tokenizer should have 5 elements");

        boolean testAns = true;
		while (tokenizerValue.hasMoreElements()){
			if (!(tokenizerValue.nextElement() instanceof ValueToken))
				testAns=false;
		}
		test(testAns, "All Tokens should be of type ValueToken");
		test(!tokenizerValue.hasMoreElements(), "All Tokens should be of type ValueToken");

        //checks tokenizer working with operator tokens
        ExpTokenizer tokenizerOperators = new ExpTokenizer("& ^ | !", assigner);
        test(tokenizerOperators.countTokens()==4, "Tokenizer should have 4 elements");

        testAns = true;
        while (tokenizerOperators.hasMoreElements()){
            if (!(tokenizerOperators.nextElement() instanceof Op))
                testAns=false;
        }
        test(testAns, "All Tokens should be operators");
        test(!tokenizerOperators.hasMoreElements(), "All Tokens should be operators");
    }

    //Checks the StackAsArray class.
    private static void testStackAsArray() {
        StackAsArray stack = new StackAsArray();
        boolean[] assigner = {true, false, false, true, true, false, true, false, false};
        ExpTokenizer tokenizer = new ExpTokenizer("a b g i F & ^ | !", assigner);
        test(stack.isEmpty(), "Stack should be empty");
        while (tokenizer.hasMoreElements())
        {
            stack.push(tokenizer.nextElement());
        }
        test(!stack.isEmpty(), "Stack should have elements");
        test((stack.pop().toString().equals("!")),"Popped element should be operator !");
        while(!stack.isEmpty())
        {
            stack.pop();
        }
        test(stack.isEmpty(), "Stack should be empty");
    }


    /** Testing Calculator
	 * Helper function to check the StackCalculator class
	 * @param pc a StackCalculator instance
	 * @param infix a string representing an infix expression
	 * @param expectedPost the expected post expression of infix
	 * @param expectedValue the expected boolean value of the expression
	 */
	private static void testPostAndEvaluate(StackCalculator pc, String infix, String expectedPost, boolean expectedValue) {
		String postExp = pc.infixToPostfix(infix);
		String msg = String.format("Postfix of \"%s\" is \"%s\" (got: \"%s\")", infix, expectedPost, postExp);
		test(postExp.equals(expectedPost), msg);
		msg = String.format("The output of \"%s\" should be %s", expectedPost, expectedValue);
		test(pc.evaluate(postExp) == expectedValue, msg);
	}

	//checks StackCalculator class
	private static void testStackCalculator() {
		boolean[] assigner = {true, false, false, true, true, false, true, false, false};
		StackCalculator pc = new StackCalculator(assigner);

		//testing only infixToPostfix function
        test(pc.infixToPostfix("( T & F ) | ( F ^ F )").equals("T F & F F ^ |"), "The Postfix form of '( T & F ) | ( F ^ F )' should be 'T F & F F ^ |' ");
        test(pc.infixToPostfix("a ^ F & T | g").equals("a F ^ T & g |"), "The Postfix form of 'a ^ F & T | g' should be 'a F ^ T & g |' ");
        test(pc.infixToPostfix("( a & b ^ g ) | T").equals("a b g ^ & T |"), "The Postfix form of '( a & b ^ g ) | T' should be 'a b g ^ & T |' ");
        test(pc.infixToPostfix("! T | T & a").equals("T ! T a & |"), "The Postfix form of '! T | T & a' should be 'T ! T a & |' ");
        test(pc.infixToPostfix("! ( a & h ) & ( T | b ) & ( d & e )").equals("a h & ! T b | d e & & &"), "The Postfix form of '! ( a & h ) & ( T | b ) & ( d & e )' should be 'a h & ! T b | d e & & &' ");


        //testing only evaluate function
        test(!pc.evaluate("T F & F F ^ |"), "The result of 'T F & F F ^ |' should be false");
        test(pc.evaluate("a F ^ T & g |"), "The result of 'a F ^ T & g |' should be true");
        test(pc.evaluate("a b g ^ & T |"), "The result of 'a b g ^ & T |' should be true");
        test(pc.evaluate("T ! T a & |"), "The result of 'T ! T a & |' should be true");
        test(pc.evaluate("a h & ! T b | d e & & &"), "The result of 'a h & ! T b | d e & & &' should be true");


        //testing infixToPostfix and evaluate functions
        testPostAndEvaluate(pc, "a & F", "a F &", false);
        testPostAndEvaluate(pc, "a | b & c | d", "a b c & d | |", true);
        testPostAndEvaluate(pc, "a & ! b & ! c & d & e & ! f & g & ! h & ! i", "a b ! c ! d e f ! g h ! i ! & & & & & & & &", true);
        testPostAndEvaluate(pc, "a & T | ! F ^ F & T","a T & F ! F ^ T & |", true);
        testPostAndEvaluate(pc, "( a & F & T ) | ( ( T ^ F ) & ( a ^ i ) )", "a F T & & T F ^ a i ^ & |", true);
        testPostAndEvaluate(pc, "( a ^ F & T ) & ( ( F ^ F ) & ( b ^ i ) ) & ! ( a & T )", "a F ^ T & F F ^ b i ^ & a T & ! & &", false);
	}


}