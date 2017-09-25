

public abstract class Heap {
	protected int count;
	protected int size;
	Heap(int size){
		count = 0;
		this.size = size;
	}
	public int getSize(){
		return count;
	}
	public abstract node peek();
	public abstract node removeMin();
	public abstract void add(node newnode);
	public abstract void clear();
}
