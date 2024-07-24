/* Siddharth Korukonda
 * 115607752
 * CSE 214.30
 */

package Homework2;

/**
 * Exception thrown when there are no nodes in the doubly linked list
 */
public class LinkedListEmptyException extends Exception {
    public LinkedListEmptyException(String message) {
        super(message);
    }
}
