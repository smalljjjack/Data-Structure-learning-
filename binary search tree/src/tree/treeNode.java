package tree;

public class treeNode {
	protected entry item;
	protected treeNode parent;
	protected treeNode leftChild;
	protected treeNode rightChild;
	
	public treeNode(){
	}
	
	public treeNode(entry item){
		this.item = item;
		this.parent = null;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	public treeNode(Object key, Object value){
		this.item = new entry(key, value);
		this.parent = null;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	public treeNode(entry item, treeNode parent){
		this.item = item;
		this.parent = parent;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	public treeNode(entry item, treeNode parent, treeNode leftChild, treeNode rightChild){
		this.item = item;
		this.parent = parent;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public boolean isValidNode(){
		if(this != null) return true;
		else return false;
	}
	
	public int compareTo(treeNode node){
		Comparable item1 = (Comparable) this.item.key;
		Comparable item2 = (Comparable) node.item.key;
		if(item1.compareTo(item2) > 0) {
			return 1;
		}else if(item1.compareTo(item2) < 0) {
			return -1;
		}else return 0;
	}
	
	public int compareTo(Object key){
		Comparable item1 = (Comparable) this.item.key;
		Comparable item2 = (Comparable) key;
		if(item1.compareTo(item2) > 0) {
			return 1;
		}else if(item1.compareTo(item2) < 0) {
			return -1;
		}else return 0;
	}
	
	
	public treeNode smallestNode(){
		treeNode[] temps = null;
		this.getAllLeft(temps);
		if(temps == null) return null; 
		if(temps != null){
			for(int i = 0; i < temps.length; i++){
				for(int j =i ; j < temps.length; j++){
					if(temps[i].compareTo(temps[j]) < 0){
						treeNode temp = temps[i];
						temps[i] = temps[j];
						temps[j] = temp;
					}
				}
			}
		}
		return temps[0];
	}
	
	
	public void getAllLeft(treeNode[] temps){
		if(this == null) return;
		int count = temps.length;
		treeNode temp = this;
		while(temp != null){
			if(temp.leftChild != null){
			temps[count] = temp.leftChild;
			temp = temp.leftChild;
			temp.getAllLeft(temps);
			}else if(temp.rightChild!= null){
				temp = temp.rightChild;
				temp.getAllLeft(temps);
			}
		}
	}

	
	public treeNode remove(int key){
		if(this == null) return null;
		boolean lr = true;
		treeNode temp = this;
		treeNode[] temps= null;
		while(temp != null){
			if(temp.compareTo(key) == 0){
				if(temp.leftChild == null && temp.rightChild == null){
					if(lr){
						temp.parent.leftChild = null;
					}else{
						temp.parent.rightChild = null;
					}
					return temp;
				}else if(temp.leftChild != null && temp.rightChild != null){
					treeNode temp2 = temp.rightChild;
					while(temp2 != null){
						temp2 = temp.leftChild;
					}
					if(lr){
						temp.parent.leftChild = temp2;
						temp2.parent = temp.parent;
					}else{
						temp.parent.rightChild = temp2;
						temp2.parent = temp.parent;
					}
					return temp;
				}else if(temp.leftChild != null && temp.rightChild == null){
					if(lr){
						temp.parent.leftChild = temp.leftChild;
						temp.leftChild.parent = temp.parent;
					}else{
						temp.parent.rightChild = temp.leftChild;
						temp.leftChild.parent = temp.parent;
					}
					return temp;
				}else if (temp.leftChild == null && temp.rightChild != null){
					if(lr){
						temp.parent.leftChild = temp.rightChild;
						temp.rightChild.parent = temp.parent;
					}else{
						temp.parent.rightChild = temp.rightChild;
						temp.rightChild.parent = temp.parent;
					}
					return temp;
				}
				/*if(temp.leftChild != null) {
					treeNode temp2 = temp.leftChild;
					int n = 0;
					while(temp2!= null){
						temps[n] = temp2;
						temp2 =temp2.leftChild;
						n++;
					}
					for(int i = 0; i < temps.length; i++){
						for(int j = i;j < temps.length; j--){
							if(temps[i].compareTo(temps[j])>0){
								treeNode temp3 = temps[i];
								temps[i] = temps[j];
								temps[j] = temp3;
							}
						}
						temp2 = temps[0];
						temp2.parent = temp.parent;
						temp2.leftChild = temp.leftChild;
						temp2.rightChild = temp.rightChild;
						if(temp.leftChild != null) temp.leftChild.parent = temp2;
						if(temp.rightChild != null) temp.rightChild.parent = temp2;
						temp = null;
						return temp2;
					}
				}*/
			}
			if(temp.compareTo(key) > 0 && temp.leftChild != null){
				temp = temp.leftChild;
				lr = true;
			}if(temp.compareTo(key) < 0 && temp.rightChild != null){
				temp = temp.rightChild;
				lr = false;
			}
		}
		return null;
	}
	
	public String toString(){
		if(this == null) return null;
		String str = "";
		if(this.rightChild != null) str = rightChild.toString()+str;
		str = this.item.toString()+str;
		if(this.leftChild != null) str = leftChild.toString()+str;
		return  str;
	}
	
	class entry{
		protected Object key;
		protected Object value;
		
		public entry(Object key, Object value){
			this.key = key;
			this.value = value;
		}
		
		public String toString(){
			return "("+key.toString()+")"+"["+value.toString()+"]";
		}
	}
}
