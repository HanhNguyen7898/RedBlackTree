/* Name: Hanh Tran Dieu Nguyen 
 * RedID: 826051324
 * Account number: cssc0242
 * Course: CS210-02_CS496-03-CX-Spring2022
 * Prof. Louie Lu 
 * Date: 04-18-2022
 */

package data_structures;

public class RedBlackTree implements RedBlackTreeInterface {
	private Node root;
	public class Node implements NodeInterface{
		private int data; //holds the key 
		private Node left; //pointer to the left child
		private Node right; //pointer to the right child 
		private Node parent; //pointer to the parent 
		private int color; //1-RED, 0-BLACK

		//Constructor for the node in the tree
		public Node() {
			this.data = 0; 
			this.left = null;
			this.right = null;
			this.parent = null;
			this.color = 1; 
		}

		public Node(int value) {
			this.data = value; 
			this.left = null;
			this.right = null;
			this.parent = null;
			this.color = 1; 
		}

		//Return the node's color
		@Override
		public int getColor() {
			return color;
		}

		//Set the node's color
		@Override
		public void setColor(int color) {
			this.color = color; 
		}

		@Override
		//Returns true if the node or one of it's children contain the value
		public boolean contains(int value) {
			return left.contains(value)||right.contains(value)||parent.contains(value)||this.getValue()==value;
		}

		//Inserts a value into the correct position
		@Override
		public void add(int value) {
			Node node = new Node();
			node.data = value;
			node.setParent(null);
			node.setLeftChild(totalNull);
			node.setRightChild(totalNull);
			if (node.parent != null) {
				node.setColor(1);
				Node a = null;
				Node b = root;		
				while (b != totalNull) {
					a = b;
					if (node.data >= b.data) {
						b = b.right;
					}
					else {
						b = b.left;
					}
				}		
				//x parent
				node.setParent(a);
				if (a == null ) {
					root = node;
				}
				else if (node.data >= a.data) {
					a.setRightChild(node);
				}
				else {
					a.setLeftChild(node);
				}

			}
			else if (node.parent == null){
				node.setColor(0);
				return;
			}
			else if (node.parent.parent == null) {
				return;
			}
			fixAfterAdding(node);
		}

		private void fixAfterAdding(Node k) {
			Node u;
			while (k.parent.color == 1) {
				if (k.parent == k.parent.parent.right) {
					u = k.parent.parent.left; // uncle
					if (u.color == 1) {
						// case 3.1
						u.color = 0;
						k.parent.color = 0;
						k.parent.parent.color = 1;
						k = k.parent.parent;
					} else {
						if (k == k.parent.left) {
							// case 3.2.2
							k = k.parent;
							rightRotate(k);
						}
						// case 3.2.1
						k.parent.color = 0;
						k.parent.parent.color = 1;
						leftRotate(k.parent.parent);
					}
				} else {
					u = k.parent.parent.right; // uncle

					if (u.color == 1) {
						// mirror case 3.1
						u.color = 0;
						k.parent.color = 0;
						k.parent.parent.color = 1;
						k = k.parent.parent;	
					} else {
						if (k == k.parent.right) {
							// mirror case 3.2.2
							k = k.parent;
							leftRotate(k);
						}
						// mirror case 3.2.1
						k.parent.color = 0;
						k.parent.parent.color = 1;
						rightRotate(k.parent.parent);
					}
				}
				if (k == root) {
					break;
				}
			}
			root.color = 0;
		}
		//Returns the node's value
		@Override
		public int getValue() {
			return data;
		}
		//Set the node's left child
		@Override
		public void setLeftChild(NodeInterface n) {
			this.left = (Node) n;
		}

		//Returns the node's left child
		@Override
		public NodeInterface getLeftChild() {
			return left;
		}
		//Set the node's right child
		@Override
		public void setRightChild(NodeInterface n) {
			this.right = (Node) n;
		}
		//Returns the node's right child
		@Override
		public NodeInterface getRightChild() {
			return right;
		}

		//set the node's parent
		@Override
		public void setParent(NodeInterface n) {
			this.parent = (Node) n;
		}	

		//Returns the node's parent
		@Override
		public NodeInterface getParent() {
			return parent;
		}

	}

	private Node totalNull;

	public RedBlackTree() {
		totalNull = new Node();
		totalNull.color = 0;
		totalNull.data = 0;
		totalNull.left = null;
		totalNull.right = null;
		root = totalNull;
	}

	//Inserts a value into the tree and performs the necessary balancing
	public void add(int value) {
		NodeInterface oPrevTracker = null;          // pointer to keep track of the previous node
		NodeInterface oCurrNode = root;            // pointer that keeps track of our current node
		NodeInterface oNewNode = new Node();        // declare and init the new node
		// this portion finds the correct spot to insert the node
		while(oCurrNode != null){
			oPrevTracker = oCurrNode;
			if(value < oCurrNode.getValue())
				oCurrNode = oCurrNode.getLeftChild();
			else
				oCurrNode = oCurrNode.getRightChild();
		}
		oNewNode.add(value);                        // set the value of the new node
		oNewNode.setParent(oPrevTracker);           // set the parent of the new node
		// this portion of code puts our new node in the right position
		if(oPrevTracker == null)
			root = (Node) oNewNode;                       // if the tree is empty
		else if(value < oPrevTracker.getValue())
			oPrevTracker.setLeftChild(oNewNode);
		else
			oPrevTracker.setRightChild(oNewNode);
	}

	
	//Removes a value from the tree if present and performs the necessary balancing
	public void remove(int value) {
		deleteNodeHelper(root, value);
	}

	private void deleteNodeHelper(Node node, int key) {
		// find the node containing key
		Node z = totalNull;
		Node x, y;
		while (node != totalNull){
			if (node.data == key) {
				z = node;
			}

			if (node.data <= key) {
				node = node.right;
			} else {
				node = node.left;
			}
		}

		if (z == totalNull) {
			return;
		} 

		y = z;
		int yOriginalColor = y.color;
		if (z.left == totalNull) {
			x = z.right;
			rbTransplant(z, z.right);
		} else if (z.right == totalNull) {
			x = z.left;
			rbTransplant(z, z.left);
		} else {
			y = minimum(z.right);
			yOriginalColor = y.color;
			x = y.right;
			if (y.parent == z) {
				x.parent = y;
			} else {
				rbTransplant(y, y.right);
				y.right = z.right;
				y.right.parent = y;
			}

			rbTransplant(z, y);
			y.left = z.left;
			y.left.parent = y;
			y.color = z.color;
		}
		if (yOriginalColor == 0){
			fixRemove(x);
		}
	}

	//corrects red and black
	private void rbTransplant(Node n, Node m) {
		if (n.parent == null) {
			root = m;
		}
		else if (n == n.parent.left) {
			n.parent.setLeftChild(m);
		}
		else {
			n.parent.setRightChild(m);
		}
		m.setParent(n.parent);
	}

	//checks for smallest
	private Node minimum(Node n) {
		while (n.left != totalNull) {
			n = n.left;
		}
		return n;
	}

	//corrects nodes in remove
	private void fixRemove(Node x) {
		Node s;
		while (x != root && x.color == 0) {
			if (x == x.parent.left) {
				s = x.parent.right;
				if (s.color == 1) {
					// case 3.1
					s.color = 0;
					x.parent.color = 1;
					leftRotate(x.parent);
					s = x.parent.right;
				}

				if (s.left.color == 0 && s.right.color == 0) {
					// case 3.2
					s.color = 1;
					x = x.parent;
				} else {
					if (s.right.color == 0) {
						// case 3.3
						s.left.color = 0;
						s.color = 1;
						rightRotate(s);
						s = x.parent.right;
					} 

					// case 3.4
					s.color = x.parent.color;
					x.parent.color = 0;
					s.right.color = 0;
					leftRotate(x.parent);
					x = root;
				}
			} else {
				s = x.parent.left;
				if (s.color == 1) {
					// case 3.1
					s.color = 0;
					x.parent.color = 1;
					rightRotate(x.parent);
					s = x.parent.left;
				}

				if (s.right.color == 0 && s.right.color == 0) {
					// case 3.2
					s.color = 1;
					x = x.parent;
				} else {
					if (s.left.color == 0) {
						// case 3.3
						s.right.color = 0;
						s.color = 1;
						leftRotate(s);
						s = x.parent.left;
					} 

					// case 3.4
					s.color = x.parent.color;
					x.parent.color = 0;
					s.left.color = 0;
					rightRotate(x.parent);
					x = root;
				}
			} 
		}
		x.color = 0;
	}

	//rotates right
	private void rightRotate(Node n) {
		Node temp = n.left;
		n.setLeftChild(n.right);
		if (temp.right != totalNull) {
			temp.right.setParent(n);
		}
		temp.setParent(n.parent);
		if (n.parent == null) {
			root = temp;
		}
		else if (n == n.parent.right) {
			n.parent.setRightChild(temp);
		}
		else {
			n.parent.setLeftChild(temp);
		}
		temp.setRightChild(n);
		n.setParent(temp);
	}

	//rotates left
	private void leftRotate(Node n) {
		Node temp = n.right;
		n.setRightChild(n.left);
		if (temp.left != totalNull) {
			temp.left.setParent(n);
		}
		temp.setParent(n.parent);
		if (n.parent == null) {
			root = temp;
		}
		else if (n == n.parent.left) {
			n.parent.setLeftChild(temp);
		}
		else {
			n.parent.setRightChild(temp);
		}
		temp.setLeftChild(n);
		n.setParent(temp);
	}

	//Returns true if the tree contains the specified value
	@Override
	public boolean contains(int value) {
		boolean flag = false;
		if(root != null)
			flag = contains(value, root);
		return flag;
	}
	/**
	 * This is the recursive Binary Search algorithm to find
	 * the correct node.
	 *
	 * @param value : value we are searching for
	 * @param oNode : current node
	 * @return : true if found, false if not found
	 */
	private boolean contains(int value, NodeInterface oNode){
		if(oNode == null)
			return false;
		if(value == oNode.getValue())
			return true;
		if(value < oNode.getValue())
			return contains(value, oNode.getLeftChild());
		return contains(value,oNode.getRightChild());
	}
	/**
	 * This function uses an iterative Binary Search algorithm
	 * to find the correct node.
	 * @param value : value we are searching for
	 * @return : the node we are searching for or null if not found
	 */
	@Override
	public NodeInterface get(int value) {
		NodeInterface temp = root;
		while(temp != null){
			if(temp.getValue() == value) {
				return temp;
			}
			else if(value < temp.getValue()) {
				temp = temp.getLeftChild();
			}
			else {
				temp = temp.getRightChild();
			}
		}
		return null; // if we don't find the value
	}

}

