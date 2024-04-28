# CS 3345 - Project 1

Jacob Kulcak
JAK210005
Section 002

## ArrayStack
ArrayStack works by having a top value, an array, and an initial capacity. The top value is set as -1 by default to indicate an empty stack/array. 
The push function first checks that the array is not at full capacity. If the array is full, resize to double the array. If not, assign value at top of the array stack.
The pop function first checks that the array is not empty. If it is empty, throw exception. If not, return the top value and then decrement the value of top.
Peek works the same as pop, but does not decrement top.
The isEmpty function simply returns true if top is -1, indicating the array is empty.
The count function returns the size of the stack by returning the top value and adding 1 to compensate for the array subscript.
Finally, resize creates a new array of twice the initial array size, copies all the old values over, and then sets the old array as equal to the new one, effectively doubling the size of the original array.

### Upper-bound O(N) complexity
The complexity of every method in ArrayStack is O(1), except for resize(), which is O(N). resize() is O(N) because the for loop used to copy the array values must run for N cycles to complete the resizing process. Push, pop, and peek are all O(1) because they only manipulate one item, being the top of the stack. Count has complexity O(1) because the size of the stack is already saved with the 'top' variable.

## ListStack
ListStack works by having a top node and stack size. The push function takes a double as an argument, creates a node that holds the data, increments size and connects the new node to the top. Pop checks throws an exception if the stack is empty. If not empty, it saves the top value as a temporary value, removes the node, and returns the value. Peek works the same without removing the node. isEmpty() and count() are identical to the method in ArrayStack.

### Upper-bound O(N) complexity
The complexity for every method in ListStack is O(1). Push, pop, and peek only access the last node, and there is no looping in either of the methods, so they are all O(1). isEmpty and count are both only one instruction each, so they are also O(1).

## How to run
Your machine needs to have JDK installed and Java added to the Path.
Open terminal at the location of the .java files.

```
javac BackMasking.java

java BackMasking <stack type: list/array> <input file> <output file>
```