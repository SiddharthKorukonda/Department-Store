/* Siddharth Korukonda
 * 115607752
 * CSE 214.30
 */

package Homework2;

/**
 * Represents a node in the doubly linked list
 * Each node contains a reference to the ItemInfo object, and the previous and next nodes in the doubly linked list
 */
public class ItemInfoNode {
    private ItemInfo info;
    private ItemInfoNode prev;
    private ItemInfoNode next;

    /**
     * ItemInfoNode constructor with null references
     */
    public ItemInfoNode() {
        this.info = null;
        this.prev = null;
        this.next = null;
    }

    /**
     * Gets the ItemInfo object
     * @return ItemInfo object
     */
    public ItemInfo getInfo() {
        return info;
    }

    /**
     * Gets the previous node
     * @return previous node
     */
    public ItemInfoNode getPrev() {
        return prev;
    }

    /**
     * Gets the next node
     * @return next node
     */
    public ItemInfoNode getNext() {
        return next;
    }

    /**
     * Sets the ItemInfo object
     * @param info from the ItemInfo object
     */
    public void setInfo(ItemInfo info) {
        this.info = info;
    }

    /**
     * Sets the previous node
     * @param node (previous)
     */
    public void setPrev(ItemInfoNode node) {
        this.prev = node;
    }

    /**
     * Sets the next node
     * @param node (next)
     */
    public void setNext(ItemInfoNode node) {
        this.next = node;
    }
}
