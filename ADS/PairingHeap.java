

class heapNode{
	node nd;
	heapNode left;
	heapNode leftchild;
	heapNode rightsibling;
	heapNode(node nd){
		this.nd = nd;
		this.left = null;
		this.leftchild = null;
		this.rightsibling = null;
	}
}
public class PairingHeap extends Heap {
	public heapNode root;
	
	PairingHeap(int size){
		super(size);
		root = null;
	}
	public node peek(){
		return root.nd;
	}
	public void add(node newnode){
		if(newnode==null)
			throw new NullPointerException();
		heapNode temp = new heapNode(newnode);
		root = combine2(root, temp);
		count++;
	}
	public node removeMin(){
		if(root==null)
			return null;
		node result = root.nd;
		//System.out.println(result.text);
		if(root.leftchild==null){
			root = null;
			count--;
			return result;
		}
		root.leftchild.left = null;
		root = meld(root.leftchild);
		count--;
		return result;
	}
	public heapNode meld(heapNode silbing){  //two pass
		heapNode first = silbing;
		heapNode second = first==null?null:silbing.rightsibling;
		heapNode head = first;
		//System.out.println(head.nd.freq);
		while(first!=null&&second!=null){
			head = combine2(first, second);
			first = head.rightsibling;
			if(first!=null){
				second = first.rightsibling;
			}
		}
		// second pass
		if(first!=null)
			head = first;
		while(head.left!=null){
			head = combine2(head.left, head);
			//System.out.println(head.left.nd.text+" "+head.nd.text);
		}
		return head;
		
	}
	public heapNode combine2(heapNode a, heapNode b){
		if(a==null)
			return b;
		if(b==null)
			return a;
		//System.out.println(a.nd.text+" "+b.nd.text);
		if(a.nd.freq<b.nd.freq){
			//System.out.println("!");
			b.left = a;
			a.rightsibling = b.rightsibling;
			if(a.rightsibling!=null)
				a.rightsibling.left = a;
			b.rightsibling = a.leftchild;
			if(b.rightsibling!=null)
				b.rightsibling.left = b;
			a.leftchild = b;
			return a;
		}
		else{
			//System.out.println("?");
			b.left = a.left;
			a.left = b;
			a.rightsibling = b.leftchild;
			if(a.rightsibling!=null){
				a.rightsibling.left = a;
			}
			b.leftchild = a;
			return b;
		}
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		count = 0;
		root = null;
	}
}
