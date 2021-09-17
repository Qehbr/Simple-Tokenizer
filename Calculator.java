/**
 * Class describing binary Boolean operation And.
 */
public abstract class Calculator {

    //fields
    protected boolean[] assigner;

    //constructor
    Calculator(boolean[] assigner)
    {
        this.assigner=assigner;
    }

    /**Evaluate function gets a valid postfix expression and return it's value */
    abstract boolean evaluate(String expr);

}
