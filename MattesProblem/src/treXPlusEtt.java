
public class treXPlusEtt {
	private int P;
	
	public int calculate(int tal){
		P += 1;
		if (tal == 1){
			return 1;
		}
		if (tal % 2 == 0){
			return calculate(tal/2);
		} else {
			return calculate(tal*3 + 1);
		}
	}
	
	public int getP(){
		return P;
	}
	
	public static void main(String[] args){
		treXPlusEtt tpe = new treXPlusEtt();
		tpe.calculate(329);
		System.out.println(tpe.getP());
	}
}
