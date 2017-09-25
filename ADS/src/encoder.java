

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class encoder {
	public static Map<Integer, Integer> fre_table;
	public static Map<String, String> code_table;
	private static Heap heap;
	private static int heapkind;
	public static String filename;
	encoder(int heapkind){
		this.heapkind = heapkind;
		//this.filename = "sample_input_large.txt";
	}
	public void readData() throws IOException{
		fre_table = new HashMap<>();
		File file = new File(filename);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String temp = "";
		while((temp = br.readLine())!=null){
			//System.out.println(temp);
			int num = 0;
			try{
				num = Integer.parseInt(temp);
			}catch(NumberFormatException e){
				break;
			}
			
			if(fre_table.containsKey(num)){
				fre_table.put(num, fre_table.get(Integer.parseInt(temp))+1);
			}
			else
				fre_table.put(Integer.parseInt(temp), 1);
		}
		br.close();
	}
	public void setHeap(int heapkind){
		switch(heapkind){
		case 0:
			//fre_table.size()
			heap = new BinaryHeap(fre_table.size());
			break;
		case 1:
			heap = new PairingHeap(fre_table.size());
			break;
		case 2:
			heap = new FourwayHeap(fre_table.size());
			break;
		}
	}
	public node buildTree(){
		//BinaryHeap bh = new BinaryHeap();
		//PairingHeap ph = new PairingHeap();
		//long a = System.currentTimeMillis();
		setHeap(heapkind);
		for(Map.Entry<Integer, Integer> e:fre_table.entrySet()){
			heap.add(new node(e.getValue(), e.getKey()));
			//System.out.println(e.getValue()+" "+ e.getKey());
		}
		//long b = System.currentTimeMillis();
		//System.out.println("build heap: "+(b-a));
		//ph.removeMin();
		//System.out.println(ph.root.leftchild.left);
		node root=null;
		//a = System.currentTimeMillis();
		while(heap.getSize()>1){
			//System.out.println(heap.getSize());
			root = new node(0, 1);
			node left = heap.removeMin();
			node right = heap.removeMin();
			//System.out.println(ph.root.nd.text);
			//System.out.println(left.text+" "+right.text);
			root.left = left;
			root.right = right;
			root.freq = left.freq+right.freq;
			//root.text = left.text+right.text;
			heap.add(root);
		}
		//b = System.currentTimeMillis();
		//System.out.println("build Huffman Tree: "+(b-a));
		return heap.peek();
	}
	public void encode(node root) throws IOException{
		code_table = new HashMap<>();
		long a = System.currentTimeMillis();
		System.out.println("build code_table: ");
		dfs(root, "");
		FileWriter fw = new FileWriter("code_table.txt");
		//BufferedWriter fw1 = new BufferedWriter(new FileWriter(outputname + "_encoded.bin"));
		BufferedOutputStream fw1 = new BufferedOutputStream(new FileOutputStream("encoded.bin"));
		BufferedReader br = new BufferedReader(new FileReader(filename));
		for(Map.Entry<String, String> e: code_table.entrySet()){
			fw.write(e.getKey()+" "+e.getValue()+"\n");
		}
		long b = System.currentTimeMillis();
		System.out.println("time for build code_table: "+(b-a));
		fw.close();
		StringBuffer result = new StringBuffer();
		String temp = null;
		String res = "";
		int count = 0;
		a = System.currentTimeMillis();
		System.out.println("encoding: ");
		while((temp=br.readLine())!=null){
			int num = 0;
			try{
				num = Integer.parseInt(temp);
			}catch(NumberFormatException e){
				break;
			}
			/*try{
				result.append(code_table.get(temp));
			}catch(NullPointerException e){
				System.out.println(code_table.get(temp));
			}*/
			res = res + code_table.get(temp);
			//fw1.write(code_table.get(temp).getBytes());
			while(res.length()>=8){
				fw1.write((byte)Integer.parseInt(res.substring(0,8), 2));
				res = res.substring(8, res.length());
			}
			
			/*while(count+8<=res.length()){
				fw1.write((byte)Integer.parseInt(res.substring(count,count+8), 2));
				count = count+8;
			}*/
		}
		/*res = result.toString();
		int count = 0
		while(res.length()>=8){
			fw1.write((byte)Integer.parseInt(res.substring(count, count+8), 2));
			count+=8;
			//result = result.substring(8, result.length());
		}*/
		//System.out.println(result.length());
		//fw1.write(result.toString().getBytes());
		b = System.currentTimeMillis();
		System.out.println("time for encoding: "+(b-a));
		br.close();
		fw1.close();
	}
	public static void dfs(node root, String huff){
		if(root!=null){
			if(root.left==null&&root.right==null){
				code_table.put(String.valueOf(root.text), huff);
			}
			dfs(root.left, new String(huff+"0"));
			dfs(root.right, new String(huff+"1"));
		}
	}
	public double eval() throws IOException{
		filename = "sample_input_large.txt";
		readData();
		double sum = 0;
		for(int i=0;i<10;i++){
			double a = System.currentTimeMillis();
			node temp = buildTree();
			double b = System.currentTimeMillis();
			//heap.clear();
			System.out.println(b-a);
			sum+=(b-a);
		}
		
		return sum/10;
	}
	public static void main(String[] args) throws IOException{
		// filename = args[0];
		filename = "sample_input_large.txt";
		encoder e = new encoder(2);
		long s = System.currentTimeMillis();
		System.out.println("building fre_table:");
		long a = System.currentTimeMillis();
		e.readData();
		long b = System.currentTimeMillis();
		System.out.println("time for building fre_table:"+ (b-a));
		System.out.println("building tree:");
		a = System.currentTimeMillis();
		node root = e.buildTree();
		b = System.currentTimeMillis();
		System.out.println("time for building tree:"+(b-a));
		e.encode(root);
		long end = System.currentTimeMillis();
		System.out.println("total time: "+ (end-s));
	}
}


