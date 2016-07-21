import java.util.Iterator;
import java.util.LinkedList;

public class primtal {
	private LinkedList<Integer> prim = new LinkedList<Integer>();
	private double P = 1;
	
	
	public double getP(){
		return P;
	}
	public LinkedList<Integer> getPrim(){
		return new LinkedList<Integer>(prim);
	}
	
	public boolean isPrim(int tal){
		Iterator<Integer> itr = prim.iterator();
		boolean isPrim = prim(tal, itr);
		if (isPrim){
			prim.add(tal);
			P += tal/1000;
		}
		return isPrim;
	}
	
	private boolean prim(int tal, Iterator<Integer> itr){
		while (itr.hasNext()){
			if (tal % itr.next() == 0){
				return false;
			}
		}
		return true;
//		if (!itr.hasNext()){
//			return true;
//		}
//		if (tal % itr.next() == 0){
//			return false;
//		} else{
//			return prim(tal, itr);
//		}
	}
	
	public int QTal(int tal){
		int Q = 0;
		for (int i = 0; i <= tal; i+=23){
			Q += i;
		}
		return Q;
	}
	public static void main(String[] args){
		primtal pt = new primtal();
		LinkedList<Integer> lst = new LinkedList<Integer>();
		lst.add(2);
		for (int i = 2; i < 1000000; i++){
			pt.isPrim(i);
//			System.out.println(pt.prim.iterator().next());
		}
		double P = pt.getP();
		System.out.println(pt.QTal(10000)*P);
//		Iterator<Integer> itr = pt.prim.iterator();
//		while (itr.hasNext()){
//			System.out.println(itr.next());			
//		}
	}
}
