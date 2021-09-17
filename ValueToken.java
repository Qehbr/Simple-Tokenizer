/**
 * Class describing token with boolean value.
 */
public class ValueToken implements CalcToken {

    //boolean variable representing the value of the token
    private boolean value;

    //constructor
    ValueToken(boolean val){
        value=val;
    }

    //returns the boolean value of the token
    boolean getValue(){
        return value;
    }

    //prints appropriate representation of the value
    @Override
    public String toString(){
        return value? "T" : "F";
    }
}
