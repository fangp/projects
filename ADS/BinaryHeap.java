

public class BinaryHeap extends Heap {
	
	public node[] nodes;
	BinaryHeap(int size){
		super(size);
		nodes = new node[size];
	}
	public node peek(){
		return count==0?null:nodes[0];
	}
	public void add(node newnode){
		//System.out.println(newnode);
		if(newnode==null)
			throw new NullPointerException();
		if(count==0)
			nodes[0] = newnode;
		else{
			nodes[count]=newnode;
			shiftup(count);
		}
		count++;
	}
	public node removeMin(){
		if(nodes.length==0)
			return null;
		node result = nodes[0];
		swap(0, count-1);
		nodes[count-1]=null;
		count--;
		//System.out.println(nodes[0].freq);
		shiftdown(0);
		return result;
	}
	private void shiftdown(int k) {
		// TODO Auto-generated method stub
		while(k<count/2){
			int left = k*2+1;
			int right = left+1;
			if(right>=count&&left>=count)
				return;
			else if(right>=count){
				if(nodes[left].freq>nodes[k].freq)
					return;
				else{
					swap(left, k);
					k = left;
				}
			}
			else if(left>=count){
				if(nodes[right].freq>nodes[k].freq)
					return;
				else{
					swap(right, k);
					k = right;
				}
			}
			else{
				int minchild = nodes[left].freq>nodes[right].freq?right:left;
				//System.out.println(minchild);
				if(nodes[k].freq>nodes[minchild].freq){
					swap(k, minchild);
					k = minchild;
				}
				else{
					return;
				}
			}
		}
	}
	private void shiftup(int k) {
		// TODO Auto-generated method stub
		int parent = 0;
		while(k>0){
			parent = (k-1)/2;
			if(nodes[k].freq>nodes[parent].freq)
				break;
			swap(parent, k);
			k = parent;
		}
	}
	private void swap(int a, int b){
		node temp = nodes[a];
		nodes[a] = nodes[b];
		nodes[b] = temp;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		count = 0;
		nodes = new node[size];
	}
}


