
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class decoder {
	private node root;
	decoder(){
		root = new node(0, 0);
	}
	public void buildLeaf(String str){
		String[] str1 = str.split(" ");
		char[] ch = str1[1].toCharArray();
		node temp = root;
		for(int i=0;i<ch.length;i++){
			if(ch[i]=='0'){
				if(temp.left!=null)
					temp = temp.left;
				else{
					temp.left = new node(0, 0);
					temp = temp.left;
				}
			}
			else{
				if(temp.right!=null)
					temp = temp.right;
				else{
					temp.right = new node(0, 0);
					temp = temp.right;
				}
			}
		}
		temp.text = Integer.parseInt(str1[0]);
	}
	public boolean isLeaf(node root){
		if(root.left==null&&root.right==null)
			return true;
		return false;
	}
	public void buildTree(String filename) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String str = "";
		while((str = br.readLine())!=null){
			buildLeaf(str);
		}
		br.close();
	}
	public void decode(String filename) throws IOException{
		node temp = root;
		BufferedInputStream fis = new BufferedInputStream(new FileInputStream(filename));
		FileWriter fw = new FileWriter("decoded.txt");
		int b = 0;
		int count = 1;
		while((b=fis.read())!=-1){
			count++;
			for(int i=7;i>=0;i--){
				int mask = 1<<i;
				temp = (b&mask)==0?temp.left:temp.right;
				if(isLeaf(temp)){
					fw.write(temp.text+"\n");
					temp = root;
				}
				else
					continue;
			}
		}
		//System.out.println(count);
		fw.close();
		fis.close();
	}
	public static void main(String[] args) throws IOException{
		decoder d = new decoder();
		long a = System.currentTimeMillis();
		d.buildTree(args[1]);
		long b = System.currentTimeMillis();
		System.out.println("Finish building decoder tree: "+(b-a));
		d.decode(args[0]);
		b = System.currentTimeMillis();
		System.out.println("Finish decoding: "+(b-a));
	}
}
