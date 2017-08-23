
public class treeNode {
	private entry entry;
	treeNode parent;
	treeNode rightChild;
	treeNode leftChild;
	
	public treeNode(Object Key, Object value, treeNode parent, treeNode rightChild, treeNode leftChild){
		this.entry = new entry(Key, value);
		this.parent = parent;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	

	public treeNode(Object Key, Object value, treeNode parent){
		this.entry = new entry(Key, value);
		this.parent = parent;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	public treeNode(){
		this.entry = null;
		this.parent = null;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	public treeNode(Object Key, Object value){
		this.entry = new entry(Key, value);
		this.parent = null;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	public int ComparaTo(treeNode temp){
		Comparable item1 = (Comparable) this.entry.Key;
		Comparable item2 = (Comparable) temp.entry.Key;
		if(item1.compareTo(item2) > 0 ) return 1;
		else if(item1.compareTo(item2) == 0 ) return 0;
		else return -1;
	}
	
	public int ComparaTo(int temp){
		Comparable item1 = (Comparable) this.entry.Key;
		Comparable item2 = (Comparable) temp;
		if(item1.compareTo(item2) > 0 ) return 1;
		else if(item1.compareTo(item2) == 0 ) return 0;
		else return -1;
	}
	
	public String toString(){
		String s = "";
		if(this.leftChild != null) s = s + this.leftChild.toString();
		s = s +'\n' + "Key:" +this.entry.Key + " "+"Value:"+ this.entry.value;
		if(this.rightChild != null) s = s + this.rightChild.toString();
		
		return s;
	}
	
	public void zig(){
		if(this.parent == null) return;
		if(this.parent.parent != null) return;
		if(this.rlChild() == 1){
			treeNode p = this.parent;
			p.parent = this;
			if(this.leftChild != null){
				p.rightChild = this.leftChild;
				this.leftChild.parent = p;
			} else p.rightChild = null;
			this.leftChild = p;
			this.parent = null;	
		}else if(this.rlChild() == -1){
			treeNode p = this.parent;
			p.parent = this;
			if(this.rightChild != null) {
				p.leftChild = this.rightChild;
				this.rightChild.parent = p;
			} else p.leftChild = null;
			this.rightChild = p;
			this.parent = null;
		}
	}
	
	public void zigZig(){
		if(this.parent==null) return;
		if(this.rlChild() != this.parent.rlChild()) return;
		
		if(this.rlChild() == -1){
			this.parent.leftChild = this.rightChild;
			if(this.rightChild != null) this.rightChild.parent = this.parent;
			if(this.parent.parent.parent != null && this.parent.parent.rlChild() == -1){
				this.rightChild = this.parent.parent;
				this.parent.parent.parent.leftChild = this;
				treeNode gp = this.parent.parent.parent;
				this.parent.parent.parent = this;
				this.parent = gp;				
			}else if(this.parent.parent.parent != null && this.parent.parent.rlChild() == 1){
				this.rightChild = this.parent.parent;
				this.parent.parent.parent.rightChild = this;
				treeNode gp = this.parent.parent.parent;
				this.parent.parent.parent = this;
				this.parent = gp;	
			}else{
				this.rightChild = this.parent.parent;
				this.parent.parent.parent = this; 
				this.parent = null;
			}
			
		}
		
		if(this.rlChild() == 1){
			this.parent.rightChild = this.leftChild;
			if(this.leftChild!=null) this.leftChild.parent = this.parent;

			if(this.parent.parent.parent != null && this.parent.parent.rlChild() == -1){
				this.leftChild = this.parent.parent;
				this.parent.parent.parent.leftChild = this;
				treeNode gp = this.parent.parent.parent;
				this.parent.parent.parent = this;
				this.parent = gp;				
			}else if(this.parent.parent.parent != null && this.parent.parent.rlChild() == 1){
				this.leftChild = this.parent.parent;
				this.parent.parent.parent.rightChild = this;
				treeNode gp = this.parent.parent.parent;
				this.parent.parent.parent = this;
				this.parent = gp;		
			}else{
				this.leftChild = this.parent.parent;
				this.parent.parent.parent = this; 
				this.parent = null;
			}
		}
	}
	
	public void zigZag(){
		if(this.parent == null) return;
		if(this.rlChild() == this.parent.rlChild() || this.parent.parent == null) return;
		if(this.rlChild() == 1 && this.parent.rlChild() == -1){
			this.parent.rightChild = this.leftChild;
			if(this.leftChild != null) this.leftChild.parent = this.parent;
			this.parent.parent.leftChild = this.rightChild;
			if(this.rightChild != null) this.rightChild.parent = this.parent.parent;
			if(this.parent.parent.parent != null && this.parent.parent.rlChild() == -1){
				this.leftChild = this.parent;
				this.rightChild = this.parent.parent;
				this.parent.parent.parent.leftChild = this;
				treeNode gp = this.parent.parent.parent;
				this.parent.parent.parent = this;
				this.parent.parent = this;
				this.parent = gp;
			}else if(this.parent.parent.parent != null && this.parent.parent.rlChild() == 1){
				this.leftChild = this.parent;
				this.rightChild = this.parent.parent;
				this.parent.parent.parent.rightChild = this;
				treeNode gp = this.parent.parent.parent;
				this.parent.parent.parent = this;
				this.parent.parent = this;
				this.parent = gp;		
			}else{
				this.leftChild = this.parent;
				this.rightChild = this.parent.parent;
				this.parent.parent.parent = this;
				this.parent.parent = this;
				this.parent = null;	
			}
		}
		
		if(this.rlChild() == -1 && this.parent.rlChild() == 1){
			this.parent.leftChild = this.rightChild;
			if(this.rightChild != null)this.rightChild.parent = this.parent;
			this.parent.parent.rightChild = this.leftChild;
			if(this.leftChild != null)this.leftChild.parent = this.parent.parent;
			
			if(this.parent.parent.parent != null && this.parent.parent.rlChild() == -1){
				this.rightChild = this.parent;
				this.leftChild = this.parent.parent;
				this.parent.parent.parent.leftChild = this;
				treeNode gp = this.parent.parent.parent;
				this.parent.parent.parent = this;
				this.parent.parent = this;
				this.parent = gp;
			}else if(this.parent.parent.parent != null && this.parent.parent.rlChild() == 1){
				this.rightChild = this.parent;
				this.leftChild = this.parent.parent;
				this.parent.parent.parent.rightChild = this;
				treeNode gp = this.parent.parent.parent;
				this.parent.parent.parent = this;
				this.parent.parent = this;
				this.parent = gp;		
			}else {
				this.rightChild = this.parent;
				this.leftChild = this.parent.parent;
				this.parent.parent.parent = this;
				this.parent.parent = this;
				this.parent = null;
			}
		}
	}
	
	public entry getEntry(){
		return entry;
	}
	
	
	public int rlChild(){
		if(this.parent == null) return 0;
		if(this.parent.rightChild == this) {
			return 1;
		}else {
			return -1;
		}
	}
	
	class entry{
		public Object Key;
		public Object value;
		
		public entry(){
			
		}
		public entry(Object Key, Object value){
			this.Key = Key;
			this.value = value;
		}
		
		public String toString(){
			String s = "";
			s = "key:" + Key + "value:" + value;
			return s;
		}
	}
	
	public static void main(String[] args){
		treeNode gp = new treeNode(8, 3);
		treeNode gl = new treeNode(4, 2);
		treeNode gr = new treeNode(12, 5);
		gl.parent = gp;
		gr.parent = gp;
		gp.leftChild = gl;
		gp.rightChild = gr;
		gl.zig();
		System.out.println(gl);
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
		System.out.println(P1r);
	}
}
