/* Tree234.java */

package dict;

/**
 *  A Tree234 implements an ordered integer dictionary ADT using a 2-3-4 tree.
 *  Only int keys are stored; no object is associated with each key.  Duplicate
 *  keys are not stored in the tree.
 *
 *  @author Jonathan Shewchuk
 **/
public class Tree234 extends IntDictionary {

  /**
   *  You may add fields if you wish, but don't change anything that
   *  would prevent toString() or find() from working correctly.
   *
   *  (inherited)  size is the number of keys in the dictionary.
   *  root is the root of the 2-3-4 tree.
   **/
  Tree234Node root;

  /**
   *  Tree234() constructs an empty 2-3-4 tree.
   *
   *  You may change this constructor, but you may not change the fact that
   *  an empty Tree234 contains no nodes.
   */
  public Tree234() {
    root = null;
    size = 0;
  }

  /**
   *  toString() prints this Tree234 as a String.  Each node is printed
   *  in the form such as (for a 3-key node)
   *
   *      (child1)key1(child2)key2(child3)key3(child4)
   *
   *  where each child is a recursive call to toString, and null children
   *  are printed as a space with no parentheses.  Here's an example.
   *      ((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))
   *
   *  DO NOT CHANGE THIS METHOD.  The test code depends on it.
   *
   *  @return a String representation of the 2-3-4 tree.
   **/
  public String toString() {
    if (root == null) {
      return "";
    } else {
      /* Most of the work is done by Tree234Node.toString(). */
      return root.toString();
    }
  }

  /**
   *  printTree() prints this Tree234 as a tree, albeit sideways.
   *
   *  You're welcome to change this method if you like.  It won't be tested.
   **/
  public void printTree() {
    if (root != null) {
      /* Most of the work is done by Tree234Node.printSubtree(). */
      root.printSubtree(0);
    }
  }

  /**
   *  find() prints true if "key" is in this 2-3-4 tree; false otherwise.
   *
   *  @param key is the key sought.
   *  @return true if "key" is in the tree; false otherwise.
   **/
  public boolean find(int key) {
    Tree234Node node = root;
    while (node != null) {
      if (key < node.key1) {
        node = node.child1;
      } else if (key == node.key1) {
        return true;
      } else if ((node.keys == 1) || (key < node.key2)) {
        node = node.child2;
      } else if (key == node.key2) {
        return true;
      } else if ((node.keys == 2) || (key < node.key3)) {
        node = node.child3;
      } else if (key == node.key3) {
        return true;
      } else {
        node = node.child4;
      }
    }
    return false;
  }

  /**
   *  insert() inserts the key "key" into this 2-3-4 tree.  If "key" is
   *  already present, a duplicate copy is NOT inserted.
   *
   *  @param key is the key sought.
   **/
  public void insert(int key) {
    // Fill in your solution here.
	  if(root == null){
		  root = new Tree234Node(null,key);
		  size++;
		  return;
	  }
	  if(find(key)){
		  System.out.println("This key already exists in the Tree");
		  return;
	  }
	  Tree234Node temp = root;
	  while(temp != null){
		  if(temp.keys == 1 && temp.parent == null && temp.child1 == null && temp.child2 == null){
			  if(key > temp.key1){
				  temp.key2 = key;
				  temp.keys++;
			  }if(key < temp.key1){
				  temp.key2 = temp.key1;
				  temp.key1 = key;
				  temp.keys++;
			  }
			  return;
		  }
		  temp = this.dealWith3keysNode(temp, key);
		  if(temp.keys == 2 && temp.parent == null && temp.child1 == null && temp.child2 == null && temp.child3 == null){
			  if(temp.key1>key){
				  temp.key3 = temp.key2;
				  temp.key2 = temp.key1;
				  temp.key1 = key;
				  temp.keys++;
			  }if(temp.key1 < key && temp.key2 > key){
				  temp.key3 = temp.key2;
				  temp.key2 = key;
				  temp.keys++;
			  }if(temp.key2 < key){
				  temp.key3 = key;
				  temp.keys++;
			  }
			  return;
		  }

		  if(temp.keys == 1 && key < temp.key1 && temp.child1 != null){
			  temp = temp.child1;
		  }
		  if(temp.keys == 1 && key > temp.key1 && temp.child2 != null){
			  temp = temp.child2;
		  }
		  if(temp.keys == 2 && key < temp.key1 && temp.child1 != null){
			  temp = temp.child1;
		  }
		  if(temp.keys == 2 && key > temp.key1 && key < temp.key2&& temp.child2 != null){
			  temp = temp.child2;
		  }
		  if(temp.keys == 2 && key > temp.key2 && temp.child3 != null){
			  temp = temp.child3;
		  }
		  if(temp.keys == 1 && key < temp.key1 && temp.child1 == null){
			  temp.keys++;
			  temp.key2 = temp.key1;
			  temp.key1 = key;
			  return;
		  }
		  if(temp.keys == 1 && key > temp.key1 && temp.child2 == null){
			  temp.keys++;
			  temp.key2 = key;
			  return;
		  }
		  if(temp.keys == 2 && key < temp.key1 && temp.child1 == null){
			  temp.keys++;
			  temp.key3 = temp.key2;
			  temp.key2 = temp.key1;
			  temp.key1 = key;
			  return;
		  }if(temp.keys == 2 && key > temp.key1 && key < temp.key2&& temp.child2 == null){
			  temp.keys++;
			  temp.key3 = temp.key2;
			  temp.key2 = key;
			  return;
		  }if(temp.keys == 2 && key > temp.key2 && temp.child3 == null){
			  temp.keys++;
			  temp.key3 = key;
			  return;
		  }
	  }  
  }
  
  public Tree234Node dealWith3keysNode(Tree234Node temp, int key){
	  if(temp.keys == 3 && temp.parent == null){
		  Tree234Node newRoot = new Tree234Node(null, temp.key2);
		  Tree234Node firstKey = new Tree234Node(newRoot, temp.key1);
		  Tree234Node secondKey = new Tree234Node(newRoot, temp.key3);
		  if(temp.child1 != null){
			  firstKey.child1 = temp.child1;
			  temp.child1.parent = firstKey;
		  }
		  if(temp.child2 != null){
			  firstKey.child2 = temp.child2;
			  temp.child2.parent = firstKey;
		  }
		  if(temp.child3 != null){
			  secondKey.child1 = temp.child3;
			  temp.child3.parent = secondKey;
		  }
		  if(temp.child4 != null){
			  secondKey.child2 = temp.child4;
			  temp.child4.parent = secondKey;
		  }
		  if(temp.child1 == null && temp.child2 == null && temp.child3 == null && temp.child4 == null){
			  newRoot.child1 = firstKey;
			  newRoot.child2 = secondKey;
		  }
		  size++;
		  this.root = newRoot;
		  temp = newRoot;
	  }
	  if(temp.keys == 3 && temp.parent != null && temp.parent.keys == 1){
		  
		  Tree234Node firstKey = new Tree234Node(temp.parent, temp.key1);
		  Tree234Node secondKey = new Tree234Node(temp.parent, temp.key3);
		  if(temp.child1 != null){
			  firstKey.child1 = temp.child1;
			  temp.child1.parent = firstKey;
		  }
		  if(temp.child2 != null){
			  firstKey.child2 = temp.child2;
			  temp.child2.parent = firstKey;
		  }
		  if(temp.child3 != null){
			  secondKey.child1 = temp.child3;
			  temp.child3.parent = firstKey;
		  }
		  if(temp.child4 != null){
			  secondKey.child2 = temp.child4;
			  temp.child4.parent = firstKey;
		  }
		  
		  if(temp.key2 > temp.parent.key1){
			  temp.parent.key2 = temp.key2;
			  temp.parent.keys++;
			  temp.parent.child2 = firstKey;
			  temp.parent.child3 = secondKey;
		  }if(temp.key2 < temp.parent.key1){
			  temp.parent.key2 = temp.parent.key1;
			  temp.parent.key1 = temp.key2;
			  temp.parent.keys++;
			  temp.parent.child3 = temp.parent.child2;
			  temp.parent.child1 = firstKey;
			  temp.parent.child3 = secondKey;
		  }
		  temp = temp.parent;
	  }
	  if(temp.keys == 3 && temp.parent != null && temp.parent.keys == 2){
		  Tree234Node firstKey = new Tree234Node(temp.parent, temp.key1);
		  Tree234Node secondKey = new Tree234Node(temp.parent, temp.key3);
		  if(temp.child1 != null){
			  firstKey.child1 = temp.child1;
			  temp.child1.parent = firstKey;
		  }
		  if(temp.child2 != null){
			  firstKey.child2 = temp.child2;
			  temp.child2.parent = firstKey;
		  }
		  if(temp.child3 != null){
			  secondKey.child1 = temp.child3;
			  temp.child3.parent = firstKey;
		  }
		  if(temp.child4 != null){
			  secondKey.child2 = temp.child4;
			  temp.child4.parent = firstKey;
		  }
		  if(temp.parent.key1 > temp.key2){
			  temp.parent.keys++;
			  temp.parent.key3 = temp.parent.key2;
			  temp.parent.key2 = temp.parent.key1;
			  temp.parent.key1 = temp.key2;
			  temp.child4 = temp.child3;
			  temp.child3 = temp.child2;
			  temp.child1 = firstKey;
			  temp.child2 = secondKey;
			  
		  }
		  if(temp.parent.key1 < temp.key2 && temp.key2 < temp.parent.key3){
			  temp.parent.keys++;
			  temp.parent.key3 = temp.parent.key2;
			  temp.parent.key2 = temp.key2;
			  temp.parent.child4 = temp.parent.child3;
			  temp.parent.child2 = firstKey;
			  temp.parent.child3 = secondKey;
			  temp = temp.parent;
			  if(key < temp.key1){
				  temp = temp.child1;
			  }else if(key > temp.key1 && key < temp.key2){
				  temp = temp.child2;
			  }else if(key > temp.key2 && key < temp.key3){
				  temp = temp.child3;
			  }else if(key > temp.key3){
				  temp = temp.child4;
			  }
		  }
	  }
	  return temp;
  }
  
  public void remove(int key){
	  if(!find(key)) {
		  System.out.println("The object you want to remove does not exist");
		  return;
	  }
	  Tree234Node temp = root;
	  while(temp!=null){
		  if(temp.parent != null 
			&& temp.child1 == null 
			&&temp.child2 == null
			&&temp.child3 == null
			&&temp.child4 == null
			){
			  if(temp.key1 == key && temp.keys == 1 && temp.key1 == key){
				  this.root = null;
				  this.size--;
				  return;
			  }else if(temp.keys == 2 && temp.key1 == key){
				  this.root = new Tree234Node(null, temp.key2);
				  return;
			  }else if(temp.keys == 2 && temp.key2 == key){
				  this.root = new Tree234Node(null, temp.key1);
				  root.keys = 1;
				  root.key1 = temp.key1;
				  return;
			  }else if(temp.keys == 3 && temp.key1 == key){
				  this.root = new Tree234Node(null, temp.key2);
				  root.keys = 2;
				  root.key2 = temp.key3;
				  return;
			  }else if(temp.keys == 3 && temp.key2 == key){
				  this.root = new Tree234Node(null, temp.key1);
				  root.keys = 2;
				  root.key2 = temp.key3;
				  return;
			  }else if(temp.keys == 3 && temp.key3 == key){
				  this.root = new Tree234Node(null, temp.key1);
				  root.keys = 2;
				  root.key2 = temp.key2;
				  return;
			  }  
		  }
		  if(temp.keys == 1 && temp.key1 == key &&temp.parent != null){
			  if(temp.parent.keys == 2 
				 && temp.parent.child1 == temp 
				 && temp.parent.child2.keys>1
				 ){
				  Tree234Node temp2 = new Tree234Node(temp.parent, temp.key1);
				  temp2.key2 = temp.parent.key1;
				  temp2.keys++;
				  temp2.child1 = temp.child1;
				  temp2.child2 = temp.child2;
				  temp2.child3 = temp.parent.child2.child1; 
				  temp.parent.key1 = temp.parent.child2.key1;
				  temp.parent.child2.child1 = temp.parent.child2.child2;
				  temp.parent.child2.child2  = temp.parent.child2.child3;
				  temp.parent.child2.child3 = temp.parent.child2.child4;
				  temp.parent.child2.key1 = temp.parent.child2.key2;
				  temp.parent.child2.key2 = temp.parent.child2.key3;
				  temp.parent.child2.keys--;
				  temp.parent.child1 = temp2;
				  temp = temp.parent;
			  }
			  if(temp.parent.keys == 2 
		   		 && temp.parent.child2 == temp 
				 && temp.parent.child3.keys>1){
				  Tree234Node temp2 = new Tree234Node(temp.parent, temp.key1);
				  temp2.key2 = temp.parent.key2;
				  temp2.keys++;
				  temp2.child1 = temp.child1;
				  temp2.child2 = temp.child2;
				  temp2.child3 = temp.parent.child3.child1;
				  temp.parent.key2 = temp.parent.child3.key1;
				  temp.parent.child3.child1 = temp.parent.child3.child2;
				  temp.parent.child3.child2  = temp.parent.child3.child3;
				  temp.parent.child3.child3 = temp.parent.child3.child4;
				  temp.parent.child3.key1 = temp.parent.child3.key2;
				  temp.parent.child3.key2 = temp.parent.child3.key3;
				  temp.parent.child3.keys--;
				  temp.parent.child2 = temp2;
				  temp = temp.parent;
			  }
			  if(temp.parent.keys == 2 
				 && temp.parent.child2 == temp 
				 && temp.parent.child3.keys==1
				 && temp.parent.child1.keys > 1){
				  Tree234Node temp2 = new Tree234Node(temp.parent, temp.key1);
				  temp2.key2 = temp.key1;
				  temp2.key2 = temp.parent.key1;
				  temp2.keys++;
				  temp2.child2 = temp.child1;
				  temp2.child3 = temp.child2;
				  if(temp.parent.child1.child4 != null){
					  temp2.child1 = temp.parent.child1.child4;
					  temp.parent.child1.child4 = null;
				  }
				  else if(temp.parent.child1.child4 == null
						  && temp.parent.child1.child3 != null){
					  temp2.child1 = temp.parent.child1.child3;
					  temp.parent.child1.child3 = null;
				  }
				  temp.parent.key1 = temp.parent.child1.key2;
				  if(temp.parent.child1.keys > 2) temp.parent.key1 = temp.parent.child1.key3;
				  temp.parent.child3.keys--;
				  temp.parent.child2 = temp2;
				  temp = temp.parent;
					  }
			  if(temp.parent.keys == 2 
				 && temp.keys == 1 
				 && temp.parent.child2.keys == 1
				 &&	temp.parent.child3.keys == 1){
				if(temp == temp.parent.child1
					&& temp.parent.child2 != null
					&& temp.parent.child3 != null){
					Tree234Node parent2 = new Tree234Node(temp.parent.parent, temp.parent.key2);
					if(temp.parent == temp.parent.parent.child1) temp.parent.parent.child1 = parent2;
					if(temp.parent == temp.parent.parent.child2) temp.parent.parent.child2 = parent2;
					if(temp.parent == temp.parent.parent.child3) temp.parent.parent.child3 = parent2;
					Tree234Node temp2 = new Tree234Node(parent2, temp.key1);
					temp2.key2 = temp.parent.key1;
					temp2.child1 = temp.child1;
					temp2.child2 = temp.child2;
					temp2.child3 = temp.parent.child2.child1;
					temp2.child4 = temp.parent.child2.child2;
					parent2.child2 = temp.parent.child3;
					temp.parent.child3.parent = parent2;
					temp = parent2;
				}
			  }  
		  }  
		  if(temp.parent!=null
			&&!temp.hasChild()){
			  if(temp.key1 == key && temp.keys == 1){
				  temp.nodeReplace(null);
				  this.size--;
				  return;
			  }else if(temp.keys == 2 && temp.key1 == key){
				  Tree234Node newNode = new Tree234Node(temp.parent, temp.key2);
				  temp.nodeReplace(newNode);
				  return;
			  }else if(temp.keys == 2 && temp.key2 == key){
				  Tree234Node newNode = new Tree234Node(temp.parent, temp.key1);
				  root.keys = 1;
				  root.key1 = temp.key1;
				  temp.nodeReplace(newNode);
				  return;
			  }else if(temp.keys == 3 && temp.key1 == key){
				  Tree234Node newNode = new Tree234Node(temp.parent, temp.key2);
				  root.keys = 2;
				  root.key2 = temp.key3;
				  temp.nodeReplace(newNode);
				  return;
			  }else if(temp.keys == 3 && temp.key2 == key){
				  Tree234Node newNode = new Tree234Node(temp.parent, temp.key1);
				  root.keys = 2;
				  root.key2 = temp.key3;
				  temp.nodeReplace(newNode);
				  return;
			  }else if(temp.keys == 3 && temp.key3 == key){
				  Tree234Node newNode = new Tree234Node(temp.parent, temp.key1);
				  root.keys = 2;
				  root.key2 = temp.key2;
				  temp.nodeReplace(newNode);
				  return;
			  }
		  }
		  if(key < temp.key1 && temp.child1 != null){
			  temp = temp.child1;
		  }else if(key > temp.key1 && key < temp.key2 && temp.child2 != null){
			  temp = temp.child2;
		  }else if(key > temp.key2 && key < temp.key3 && temp.child3 != null){
			  temp = temp.child3;
		  }else if(key > temp.key3 && temp.child4 != null){
			  temp = temp.child4;
		  }
	  }
  }
  

  /**
   *  testHelper() prints the String representation of this tree, then
   *  compares it with the expected String, and prints an error message if
   *  the two are not equal.
   *
   *  @param correctString is what the tree should look like.
   **/
  public void testHelper(String correctString) {
    String treeString = toString();
    System.out.println(treeString);
    if (!treeString.equals(correctString)) {
      System.out.println("ERROR:  Should be " + correctString);
    }
  }

  /**
   *  main() is a bunch of test code.  Feel free to add test code of your own;
   *  this code won't be tested or graded.
   **/
  public static void main(String[] args) {
    Tree234 t = new Tree234();

    System.out.println("\nInserting 84.");
    t.insert(84);
    t.testHelper("84");

    System.out.println("\nInserting 7.");
    t.insert(7);
    t.testHelper("7 84");

    System.out.println("\nInserting 22.");
    t.insert(22);
    t.testHelper("7 22 84");

    System.out.println("\nInserting 95.");
    t.insert(95);
    t.testHelper("(7)22(84 95)");

    System.out.println("\nInserting 50.");
    t.insert(50);
    t.testHelper("(7)22(50 84 95)");

    System.out.println("\nInserting 11.");
    t.insert(11);
    t.testHelper("(7 11)22(50 84 95)");

    System.out.println("\nInserting 37.");
    t.insert(37);
    t.testHelper("(7 11)22(37 50)84(95)");

    System.out.println("\nInserting 60.");
    t.insert(60);
    t.testHelper("(7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 1.");
    t.insert(1);
    t.testHelper("(1 7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 23.");
    t.insert(23);
    t.testHelper("(1 7 11)22(23 37)50(60)84(95)");

    System.out.println("\nInserting 16.");
    t.insert(16);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95))");

    System.out.println("\nInserting 100.");
    t.insert(100);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95 100))");

    System.out.println("\nInserting 28.");
    t.insert(28);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(95 100))");

    System.out.println("\nInserting 86.");
    t.insert(86);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(86 95 100))");

    System.out.println("\nInserting 49.");
    t.insert(49);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))");

    System.out.println("\nInserting 81.");
    t.insert(81);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60 81)84(86 95 100))");

    System.out.println("\nInserting 51.");
    t.insert(51);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86 95 100))");

    System.out.println("\nInserting 99.");
    t.insert(99);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86)95(99 100))");

    System.out.println("\nInserting 75.");
    t.insert(75);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(75 81)84(86)95" +
                 "(99 100))");

    System.out.println("\nInserting 66.");
    t.insert(66);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(66 75 81))84((86)95" +
                 "(99 100))");

    System.out.println("\nInserting 4.");
    t.insert(4);
    t.testHelper("((1 4)7(11 16))22((23)28(37 49))50((51)60(66 75 81))84" +
                 "((86)95(99 100))");

    System.out.println("\nInserting 80.");
    t.insert(80);
    t.testHelper("(((1 4)7(11 16))22((23)28(37 49)))50(((51)60(66)75" +
                 "(80 81))84((86)95(99 100)))");

    System.out.println("\nFinal tree:");
    t.printTree();
  }
}