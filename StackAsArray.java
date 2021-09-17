/**
 * Class describing stack with array implementation.
 */
public class StackAsArray implements Stack {

    //stack is based on dynamic array with default length 5
    private Object[] stack = new Object[5];

    //currentLength indicates number of objects in the stack
    private int currentLength=0;

    /** Push the element to the top of the stack */
    @Override
    public void push(Object o) {
        if(currentLength!=stack.length) //if array is not full we can add objects
        {
            stack[currentLength]=o;     //adding the object
            currentLength++;            //increasing the length of the stack
        }
        else {
            Object[]tempStack = new Object[currentLength+5];    //creating new array with 5 more elements
            for (int i = 0; i < currentLength; i++) {           //rewriting old array to the bigger new
                tempStack[i]=stack[i];
            }
            tempStack[currentLength]=o;                         //adding the new item
            currentLength++;                                    //increasing the length of the stack
            stack=tempStack;                                    //making stack pointer pointing to new array
        }
    }

    /** Pop the element from the top of the stack (returns the popped object), (throws RuntimeException if stack is empty)*/
    @Override
    public Object pop() {
        if(!this.isEmpty()) {
            currentLength--;                     //reducing the length of stack
            Object pop = stack[currentLength];   //creating temp object for popped element
            stack[currentLength] = null;         //deleting the last element
            return pop;                          //returning the popped element
        }
        throw new RuntimeException("The stack is empty"); //throwing if stack  is empty
    }

    /** isEmpty returns true if stack is empty */
    @Override
    public boolean isEmpty() {
        return currentLength==0;
    }

}
