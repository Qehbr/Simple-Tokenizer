/**
 * Class describing binary Boolean operation Or.
 */
public class OrOp extends BinaryOp {

    //constructor
    public OrOp() {
        super('|');
    }

    @Override
    public boolean operate(boolean left, boolean right) {
        return left|right;
    }

    @Override
    public double getPrecedence() {
        return 3;
    }
}
