
public class splayTree {
	private int size;
	private treeNode root;
	
	public splayTree(){
		this.root = new treeNode();
		size = 1;
	}
	
	public splayTree(Object key, Object value){
		this.root = new treeNode(key, value);
		size = 1;
	}
	
	/*public treeNode find(int key){
		if(root == null) return null;
		treeNode temp = root;
		while(temp != null){
			if(temp.ComparaTo(key) > 0 && temp.leftChild != null) {
				temp = temp.leftChild;
			}else if(temp.ComparaTo(key) < 0 && temp.rightChild != null) {
				temp = temp.rightChild;
			}else break;
		}
		
		
		return temp;
	}*/
	
	public treeNode find(Object Key){
		if(size == 0) return null;
		int key = (Integer) Key;
		if(root.ComparaTo(key) == 0) return root;
		treeNode node = root;
		while(node != null){
			if(node.ComparaTo(key) > 0 && node.leftChild != null) {
				node = node.leftChild;
			}else if(node.ComparaTo(key) < 0 && node.rightChild != null) {
				node = node.rightChild;
			}else break;
		}
		
		while(node != root){
			if(node.parent != root){
				if(node.rlChild() == node.parent.rlChild()){
					if(node.parent.parent != root){
						if(key == 7) System.out.println("inserting");
						node.zigZig();	
					}else if(node.parent.parent == root){
						if(key == 7) System.out.println("inserting1");
						node.zigZig();
						root = node;
					}
					
				}else if(node.rlChild() != node.parent.rlChild()){
					if( node.parent.parent != root){
						node.zigZag();
					}else if( node.parent.parent == root){
						node.zigZag();
						root = node;
					}
				}
			}
			if(node.parent == root){
				if(key == 7) System.out.println("inserting2");
				node.zig();
				root = node;
			}
		}
		
		return node;	
	}
	
	public treeNode insert(Object Key, Object value){
		treeNode node = null;
		if(root == null) {
			node = new treeNode(Key, value);
			root = node;
			size ++;
			return root;
		}
		treeNode temp = root;
		
		int key = (Integer) Key;
		while(temp!=null){
			if(temp.ComparaTo(key) > 0){
				if(temp.leftChild == null){
					node = new treeNode(Key, value, temp);
					temp.leftChild = node;
					size ++;
					break;
				}else{
					temp = temp.leftChild;
				}
			}else if(temp.ComparaTo(key) < 0){
				if(temp.rightChild == null){
					node = new treeNode(Key, value, temp);
					temp.rightChild = node;
					size ++;
					break;
				}{
					temp = temp.rightChild;
				}
			}else{
				return null;
			}
		}
		
		if(node.parent == null) return root;
		
		while(node != root){
			if(node.parent != root){
				if(node.rlChild() == node.parent.rlChild()){
					if(node.parent.parent != root){
						node.zigZig();	
					}else if(node.parent.parent == root){
						node.zigZig();
						root = node;
					}
					
				}else if(node.rlChild() != node.parent.rlChild()){
					if( node.parent.parent != root){
						node.zigZag();
					}else if( node.parent.parent == root){
						node.zigZag();
						root = node;
					}
				}
			}
			if(node.parent == root){
				if(key == 7) System.out.println("inserting2");
				node.zig();
				root = node;
			}
		}
		
		return root;
	}
	
	public String toString(){
		String s ="";
		s = s + root.toString();
		return s;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		splayTree tree = new splayTree(8, 3);
		tree.insert(4, 2);
		tree.insert(12, 5);
		tree.insert(2, 5);
		tree.insert(6, 9);
		tree.insert(1, 1);
		tree.insert(3, 9);
		tree.insert(5, 5);
		tree.insert(10, 15);
		tree.insert(7, 10);
		tree.insert(14, 9);
		tree.insert(9, 17);
		tree.insert(11, 8);
		tree.insert(13, 25);
		tree.insert(15, 30);
		System.out.println(tree);
		System.out.println(tree.root.getEntry());
		System.out.println(tree.find(7));
		System.out.println(tree.root.getEntry());
		
		/*treeNode gp = new treeNode(8, 3);
		treeNode gl = new treeNode(4, 2);
		treeNode gr = new treeNode(12, 5);
		gl.parent = gp;
		gr.parent = gp;
		gp.leftChild = gl;
		gp.rightChild = gr;
		treeNode P1l = new treeNode(2 , 5);
		treeNode P1r = new treeNode(6,9);
		treeNode c1 = new treeNode(1, 1);
		treeNode c2 = new treeNode(3, 9);
		treeNode c3 = new treeNode(5, 5);
		treeNode c4 = new treeNode(7, 10);
		P1l.parent = gl;
		gl.leftChild = P1l;
		P1r.parent = gl;
		gl.rightChild = P1r;
		c1.parent = P1l;
		P1l.leftChild = c1;
		c2.parent = P1l;
		P1l.rightChild = c2;
		c3.parent = P1r;
		P1r.leftChild = c3;
		c4.parent = P1r;
		P1r.rightChild = c4;
		treeNode P2l = new treeNode(10, 15);
		treeNode P2r = new treeNode(14, 9);
		treeNode c5 = new treeNode(9, 17);
		treeNode c6 = new treeNode(11, 8);
		treeNode c7 = new treeNode(13, 25);
		treeNode c8 = new treeNode(15, 30);
		P2l.parent = gr;
		gr.leftChild = P2l;
		P2r.parent = gr;
		gr.rightChild = P2r;
		c5.parent = P2l;
		P2l.leftChild = c5;
		c6.parent = P2l;
		P2l.rightChild = c6;
		c7.parent = P2r;
		P2r.leftChild = c7;
		c8.parent = P2r;
		P2r.rightChild = c8;
		P1r.zigZag();
		System.out.println(P1r);*/
	}

}
