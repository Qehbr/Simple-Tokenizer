/**
 * Class describing variable that stores the boolean value.
 */
public class VariableToken extends ValueToken {

    //variable stores the boolean value
    private char variable;

    //constructor
    VariableToken(String var, boolean[] assigner) {
        super(assigner[((int)var.toCharArray()[0]-96)-1]);  //assigning the appropriate value to variable (according to assigner table)
        variable=var.toCharArray()[0];                      //casting String variable to char
    }

    //returns the variable of the token
    char getVariable(){
        return variable;
    }

    public String toString(){
        return String.valueOf(variable);
    }
}
