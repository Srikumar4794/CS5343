package Assignments;

import java.util.LinkedList;

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree. Note that all "matching" is
 * based on the compareTo method.
 * 
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
	/**
	 * Construct the tree.
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * Insert into the tree; duplicates are ignored.
	 * 
	 * @param x
	 *            the item to insert.
	 */
	public void insert(AnyType x) {
		root = insert(x, root);
	}

	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 * 
	 * @param x
	 *            the item to remove.
	 */
	public void remove(AnyType x) {
		root = remove(x, root);
	}

	/**
	 * Find the smallest item in the tree.
	 * 
	 * @return smallest item or null if empty.
	 */
	public AnyType findMin() {
		// if( isEmpty( ) )
		// throw new UnderflowException("Stack Underflow." );
		return findMin(root).element;
	}

	/**
	 * Find the largest item in the tree.
	 * 
	 * @return the largest item of null if empty.
	 */
	public AnyType findMax() {
		// if( isEmpty( ) )
		// throw new UnderflowException( );
		return findMax(root).element;
	}

	/**
	 * Find an item in the tree.
	 * 
	 * @param x
	 *            the item to search for.
	 * @return true if not found.
	 */
	public boolean contains(AnyType x) {
		return contains(x, root);
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty() {
		root = null;
	}

	/**
	 * Test if the tree is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Print the tree contents in sorted order.
	 */
	public void printTree() {
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}

	/**
	 * Internal method to insert into a subtree.
	 * 
	 * @param x
	 *            the item to insert.
	 * @param t
	 *            the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
		if (t == null)
			return new BinaryNode<>(x, null, null);

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			t.left = insert(x, t.left);
		else if (compareResult > 0)
			t.right = insert(x, t.right);
		else
			; // Duplicate; do nothing
		return t;
	}

	/**
	 * Internal method to remove from a subtree.
	 * 
	 * @param x
	 *            the item to remove.
	 * @param t
	 *            the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
		if (t == null)
			return t; // Item not found; do nothing

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			t.left = remove(x, t.left);
		else if (compareResult > 0)
			t.right = remove(x, t.right);
		else if (t.left != null && t.right != null) // Two children
		{
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		} else
			t = (t.left != null) ? t.left : t.right;
		return t;
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the smallest item.
	 */
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
		if (t == null)
			return null;
		else if (t.left == null)
			return t;
		return findMin(t.left);
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the largest item.
	 */
	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
		if (t != null)
			while (t.right != null)
				t = t.right;

		return t;
	}

	/**
	 * Internal method to find an item in a subtree.
	 * 
	 * @param x
	 *            is item to search for.
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the matched item.
	 */
	private boolean contains(AnyType x, BinaryNode<AnyType> t) {
		if (t == null)
			return false;

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			return contains(x, t.left);
		else if (compareResult > 0)
			return contains(x, t.right);
		else
			return true; // Match
	}

	/**
	 * Internal method to print a subtree in sorted order.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 */
	private void printTree(BinaryNode<AnyType> t) {
		if (t != null) {
			printTree(t.left);
			System.out.println(t.element + " ");
			printTree(t.right);
		}
	}

	/**
	 * Internal method to compute height of a subtree.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 */
	private int height(BinaryNode<AnyType> t) {
		if (t == null)
			return -1;
		else
			return 1 + Math.max(height(t.left), height(t.right));
	}

	// Basic node stored in unbalanced binary search trees
	public static class BinaryNode<AnyType> {
		// Constructors
		BinaryNode(AnyType theElement) {
			this(theElement, null, null);
		}

		BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
			element = theElement;
			left = lt;
			right = rt;
		}

		AnyType element; // The data in the node
		BinaryNode<AnyType> left; // Left child
		BinaryNode<AnyType> right; // Right child
	}

	private BinaryNode<AnyType> root;
	/** The tree root. */

	int count = 0;

	public int getNodeCount() 
	{
		return nodeCount(root);
	}

	private int nodeCount(BinaryNode<AnyType> t) 
	{
		if (t == null)											  // Base case.
			return (0);
		else
			return (1 + nodeCount(t.left) + nodeCount(t.right));  // Recursive call.
	}

	private boolean isFull() 
	{
		return (isFull(root));
	}

	private boolean isFull(BinaryNode<AnyType> t)
	{

		// base case
		if (t == null)
			return true;
		// leaf
		if (t.left == null && t.right == null)
		{
			return true;
		}
		// both the child are present
		if (t.left != null && t.right != null)
		{
			return isFull(t.left) && isFull(t.right);
		}
		// only if one child is present
		else 
		{
			return false;
		}
	}

	public boolean compareStructure(BinarySearchTree<AnyType> t2)
	{
		return compareStructure(this.root, t2.root);
	}
	
	public boolean compareStructure(BinaryNode<AnyType> node1, BinaryNode<AnyType> node2) 
	{
		if ((node1 == null && node2 != null) || (node1 != null && node2 == null))                      //base case.
			return false;
		if (node1 == null && node2 == null)
			return true;
		return compareStructure(node1.left, node2.left) && compareStructure(node1.right, node2.right); //recursive call.
	}

	public boolean equals(BinarySearchTree<AnyType> t2)
	{
		return equals(this.root, t2.root);
	}

	public boolean equals(BinaryNode<AnyType> node1, BinaryNode<AnyType> node2)
	{
		if ((node1 == null && node2 != null) || (node1 != null && node2 == null))
			return false;
		if (node1 == null && node2 == null)
			return true;

		if (node1.element.compareTo(node2.element) != 0)
			return false;
		else
			return (equals(node1.left, node2.left) && equals(node1.right, node2.right));
	}

	

	public BinarySearchTree<AnyType> copy()
	{
		BinarySearchTree<AnyType> t2 = new BinarySearchTree<>();
		Create(this.root, t2);
		return t2;
	}

	private void Create(BinaryNode<AnyType> node, BinarySearchTree<AnyType> newTree)
	{
		if (node != null)
		{
			newTree.insert(node.element);
			Create(node.left, newTree);
			Create(node.right, newTree);
		}
	}

	public BinarySearchTree<AnyType> mirror()
	{
		BinarySearchTree<AnyType> newTree = new BinarySearchTree<>();
		if (this.root != null)
		{
			newTree.root = new BinaryNode<AnyType>(this.root.element);
			mirror(this.root, newTree.root);
		}
		return newTree;
	}

	private void mirror(BinaryNode<AnyType> oldNode, BinaryNode<AnyType> newNode) 
	{
		if (oldNode != null)
		{
			if (oldNode.left != null)
				newNode.right = new BinaryNode<AnyType>(oldNode.left.element);
			if (oldNode.right != null)
				newNode.left = new BinaryNode<AnyType>(oldNode.right.element);
		}
		if (oldNode.left != null)
			mirror(oldNode.left, newNode.right);
		if (oldNode.right != null)
			mirror(oldNode.right, newNode.left);
	}

	public boolean isMirror(BinarySearchTree<AnyType> t2) 
	{
		if (t2.equals(this.mirror()))
			return true;
		else
			return false;
	}

	public void rotateRight(AnyType x)
	{
		if(root.element.compareTo(x) == 0)
		{
			System.out.println("Rotating at root: ");
			BinaryNode<AnyType> node;
			node = root.left;
            root.left = node.right;
			node.right = root;
			root = node;
			System.out.println("New root  value set to: "+ root.element);
		}
		else
		{
			BinaryNode<AnyType> parentNode;
			parentNode = findParent(root,x);
			BinaryNode<AnyType> child;
			BinaryNode<AnyType> node;
			if(parentNode.right != null && parentNode.right.element.compareTo(x) == 0)
			{
				child = parentNode.right;
				node = child.left;
				if(node != null)
				{
					child.left = node.right;
					node.right = child;
					parentNode.right = node;
				}
			}
			if (parentNode.left != null && parentNode.left.element.compareTo(x) == 0)
			{
				child = parentNode.left;
				node = child.left;
				if(node != null)
				{
					child.left = node.right;
					node.right = child;
					parentNode.left = node;
				}
			}
					
		}
	}
	
	public void rotateLeft(AnyType x)
	{
		if(root.element.compareTo(x) == 0)
		{
			System.out.println("Rotating at root:");
			BinaryNode<AnyType> node;
			node = root.right;
			root.right = node.left;
			node.left = root;
			root = node;
			System.out.println("New root  value set to: "+ root.element);
		}
		else
		{
			BinaryNode<AnyType> parentNode;
			parentNode = findParent(root,x);
			BinaryNode<AnyType> child;
			BinaryNode<AnyType> node;
			if(parentNode.right != null && parentNode.right.element.compareTo(x) == 0)
			{
				child = parentNode.right;
				node = child.right;
				if(node != null)
				{
					child.right = node.left;
					node.left = child;
					parentNode.right = node;
				}
			}
			if (parentNode.left != null && parentNode.left.element.compareTo(x) == 0)
			{
				child = parentNode.left;
				node = child.right;
				if(node != null)
				{
					child.right = node.left;
					node.left = child;
					parentNode.left = node;
				}
			}
					
		}
	}
	
	private BinaryNode<AnyType> findParent(BinaryNode<AnyType> node,AnyType x)
	{
		if(node.left != null && node.left.element.compareTo(x) == 0 )
			return node;
		if(node.right != null && node.right.element.compareTo(x) == 0)
			return node;
		if(node.element.compareTo(x) < 0)
			return findParent(node.right,x);
		else
			return findParent(node.left,x);
			
	}

	public void printLevels() {
		LinkedList<BinaryNode<AnyType>> list = new LinkedList<>();
		list.add(root);
		while (!list.isEmpty()) {
			BinaryNode<AnyType> iterator = list.remove();
			System.out.println(iterator.element);
			if (iterator.left != null) {
				list.add(iterator.left);
			}
			if (iterator.right != null) {
				list.add(iterator.right);
			}
		}

	}

	// Test program
	public static void main(String[] args)
	{
		//Creating tree1 object.
		BinarySearchTree<Integer> tree1 = new BinarySearchTree<>();
		
		//Inserting nodes into tree1.
		tree1.insert(100);
		tree1.insert(50);
		tree1.insert(150);
		tree1.insert(40);
		tree1.insert(45);

		System.out.println("Printing the tree 1 with in-order traversal:");
		tree1.printTree();
		System.out.println();

		// Printing the number of nodes
		System.out.println("----Demonstrating the method to count nodes---- ");
		System.out.println("The number of nodes in the tree1 is: " + tree1.getNodeCount());
		System.out.println();

		System.out.println("----Demonstrating the method to check if tree is full---- ");
		System.out.println("Checking if the tree1 is full? " + tree1.isFull());
		System.out.println();

		//Creating tree2 object. 
		BinarySearchTree<Integer> tree2 = new BinarySearchTree<>();
		
		//Inserting nodes into Tree2
		tree2.insert(80);
		tree2.insert(70);
		tree2.insert(35);
		tree2.insert(40);
		tree2.insert(100);
		tree2.insert(14);
		tree2.insert(160);

		System.out.print("Tree 1:\n");
		tree1.printTree();
		System.out.print("Tree 2:\n");
		tree2.printTree();
		System.out.println("----Demonstrating the method to compare tree structures----");
		System.out.println("Checking if the above 2 tree structures match: " + tree1.compareStructure(tree2));
		System.out.println();
		
		System.out.println("----Demonstrating the method to check if trees are equal----");
		System.out.println("Checking if trees are equal: " + tree1.equals(tree2));
		System.out.println();
		
		System.out.println("----Demonstrating the method to create the copy of a tree----");
		System.out.println("Creating copy of tree and printing: ");
		BinarySearchTree<Integer> copyTree = tree1.copy();
		copyTree.printTree();
		System.out.println();
		
		System.out.println("----Demonstrating the method to mirror a tree----");
		System.out.println("Creating mirror image of tree and printing: ");
		BinarySearchTree<Integer> mirrorTree = tree1.mirror();
		mirrorTree.printTree();
		System.out.println();

		System.out.println("----Demonstrating the isMirror() method----");
		System.out.println("Checking whether the tree1 is a mirror of tree2: " + tree1.isMirror(tree2));
		System.out.println();

		System.out.println("----Demonstrating rotateLeft method----");
		System.out.println("Printing levels of tree before rotation:");
		tree1.printLevels();
		tree1.rotateLeft(100);
		System.out.println("Printing levels of tree after rotating left:");
		tree1.printLevels();
		System.out.println();
		
		System.out.println("----Demonstrating rotateRight method----");
		System.out.println("Printing levels of tree before rotation:");
		tree1.printLevels();
		tree1.rotateRight(150);
		System.out.println("Printing levels of tree after rotating right:");
		tree1.printLevels();
		System.out.println();
		
		System.out.println("----Demonstrating printLevels method----");
		System.out.println("Printing levels of tree2: " );
		tree2.printLevels();
	}
}
