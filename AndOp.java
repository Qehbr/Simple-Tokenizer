/**
 * Class describing binary Boolean operation And.
 */
public class AndOp extends BinaryOp {

    //constructor
    public AndOp() {
        super('&');
    }

    @Override
    public boolean operate(boolean left, boolean right) {
        return left&right;
    }

    @Override
    public double getPrecedence() {
        return 2;
    }
}
