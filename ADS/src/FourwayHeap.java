

public class FourwayHeap extends Heap{

	public node[] nodes;
	FourwayHeap(int size){
		super(size);
		//count = 3;
		count = 3;
		nodes = new node[size+3];
	}
	public int getSize(){
		return count-3;
	}
	public node peek(){
		return count-3==0?null:nodes[3];
	}
	public void add(node newnode){
		//System.out.println(newnode);
		if(newnode==null)
			throw new NullPointerException();
		if(count==3)
			nodes[3] = newnode;
		else{
			nodes[count]=newnode;
			shiftup(count);
		}
		count++;
	}
	public node removeMin(){
		if(count<3)
			return null;
		node result = nodes[3];
		swap(3, count-1);
		nodes[count-1]=null;
		count--;
		//System.out.println(nodes[0].freq);
		shiftdown(3);
		return result;
	}
	private void shiftdown(int k) {
		// TODO Auto-generated method stub
		while(k-3<count/4){
			int val = nodes[k].freq;
			int min = Integer.MAX_VALUE;
			int minchild = 0;
			for(int i=(k-3)*4+1;i<(k-3)*4+5;i++){
				if(i+3>=count)
					break;
				if(min>nodes[i+3].freq){
					min = nodes[i+3].freq;
					minchild = i+3;
				}
			}
			if(min<val){
				swap(minchild, k);
				k = minchild;
			}
			else
				return;
		}
	}
	private void shiftup(int k) {
		// TODO Auto-generated method stub
		int parent = 0;
		while(k>3){
			parent = (k-4)/4+3;
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
		count = 3;
		nodes = new node[size+3];
	}
}

