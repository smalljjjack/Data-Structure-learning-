package tree;
public class tree {
	protected treeNode root;
	private int size;
	
	public tree(){
		root = new treeNode();
		size = 0;
	}
	
	public tree(Object key, Object value){
		root = new treeNode(key, value);
		size = 1;
	}
	
	public int size(){
		return size;
	}
	
	public treeNode root(){
		if(this.isEmpty()) return null;
		if(root.isValidNode()){
			return root;
		}else{
			return null;
		}
	}
	
	
	private boolean isEmpty() {
		if(size == 0) return true;
		else return false;
	}
	
	public treeNode find(int key){
		if(this.isEmpty()) return null;
		treeNode temp = root;
		while(temp!=null){
			if(temp.compareTo(key) == 0){
				return temp;
			}else if(temp.compareTo(key) > 0) {
				temp = temp.leftChild;
			}else if(temp.compareTo(key) < 0){
				temp = temp.rightChild;
			}
		}
		return null;
	}
	
	public treeNode remove(int key){
		if(this.isEmpty()) return null;
		treeNode temp = root;
		size--;
		return temp.remove(key);
	}
	
	public void insert(Object key, Object value){
		if(this.isEmpty()) return;
		treeNode temp = root;
		treeNode node = new treeNode(key, value);

		while(temp.isValidNode()){
	
			if(node.compareTo(temp)<=0 && temp.leftChild!=null){
				temp = temp.leftChild;
			}else if(node.compareTo(temp)>0 && temp.rightChild!= null){
				temp = temp.rightChild;
			}else if(node.compareTo(temp)<=0 && temp.leftChild==null){
				temp.leftChild = node;
				node.parent = temp;
				size++;
				return;
			}else if(node.compareTo(temp)>0 && temp.rightChild==null){
				temp.rightChild  = node;
				node.parent = temp;
				size++;
				return;
			}
		}
	}
	
	public String toString(){
		if(size == 0) return null;
		return root.toString();
	}

	public static void main(String[] args) {
		tree tree1 = new tree(1, 3);
		System.out.println(tree1);
		tree1.insert(2, 4);
		System.out.println(tree1);
		tree1.insert(3, 5);
		System.out.println(tree1);
		tree1.insert(4, 2);
		System.out.println(tree1);
		tree1.insert(5, 2);
		System.out.println(tree1);
		System.out.println(tree1.find(3));
		tree1.insert(7, 3);
		System.out.println(tree1);
		tree1.insert(6, 15);
		System.out.println(tree1);
		System.out.println(tree1.remove(3));
		System.out.println(tree1);
		tree1.insert(3, 5);
		System.out.println(tree1);
		tree tree2 = new tree(5,3);
		tree2.insert(4, 2);
		tree2.insert(6, 3);
		tree2.insert(2, 7);
		tree2.insert(3, 1);
		tree2.insert(5, 9);
		System.out.println(tree2);
		
		
		
	}
}
