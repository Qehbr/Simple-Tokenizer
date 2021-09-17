/**
 * Class describing binary Boolean operation Xor.
 */
public class XorOp extends BinaryOp {
    //constructor
    public XorOp() {
        super('^');
    }

    @Override
    public boolean operate(boolean left, boolean right) {
        return left^right;
    }

    @Override
    public double getPrecedence() {
        return 1;
    }
}
