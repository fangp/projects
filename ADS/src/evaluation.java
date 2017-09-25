

import java.io.IOException;

public class evaluation {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		double sum = 0;
		/*for(int i=0;i<10;i++){
			encoder e2 = new encoder(1);
			e2.readData();
			long a = System.currentTimeMillis();
			e2.buildTree();
			long b = System.currentTimeMillis();
			System.out.println(b-a);
			sum+=(b-a);
		}
		System.out.println("p"+sum/10);
		sum = 0;
		for(int i=0;i<10;i++){
			encoder e2 = new encoder(0);
			e2.readData();
			long a = System.currentTimeMillis();
			e2.buildTree();
			long b = System.currentTimeMillis();
			System.out.println(b-a);
			sum+=(b-a);
		}
		System.out.println("b"+sum/10);
	    sum = 0;*/
		for(int i=0;i<10;i++){
			encoder e2 = new encoder(2);
			e2.readData();
			long a = System.currentTimeMillis();
			e2.buildTree();
			long b = System.currentTimeMillis();
			System.out.println(b-a);
			sum+=(b-a);
		}
		System.out.println("f"+sum/10);
		/*encoder e2 = new encoder(1);
		System.out.println("Pairing: "+ e2.eval());
		encoder e1 = new encoder(0);
		System.out.println("Binary: " + e1.eval());
		encoder e3 = new encoder(2);
		System.out.println("4Way: "+ e3.eval());*/
		
		
	}

}
