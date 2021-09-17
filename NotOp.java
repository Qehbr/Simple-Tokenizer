/**
 * Class describing unary Boolean operation Not.
 */
public class NotOp extends Op {

    //constructor
    public NotOp() {
        super('!');
    }

    /** Return the result of this operation on its operand */
    public boolean operate(boolean operand) {
        return !operand;
    }

    @Override
    public double getPrecedence() {
        return 0;
    }
}

