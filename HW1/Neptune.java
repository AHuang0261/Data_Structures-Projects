
public class Neptune extends Currency implements Exchangeable{
	public Neptune() {
		currencyName = "Neptune Nuggets";
		totalFunds = 100.0;	}
	
	public Neptune(double start) {
		currencyName = "Neptune Nuggets";
		totalFunds = start;
	}
	
	public double toEarthDollars(double amount) {
		return amount / Exchangeable.NN;
	}
	public double fromEarthDollars(double EarthDollars) {
		return EarthDollars * Exchangeable.NN;
	}
	
	@Override
	public void exchange(Exchangeable other, double amount) {
		if(! (other instanceof Saturn || other instanceof Mars)) {
			System.out.println("We don't service that planet.");
			System.out.println();
			return;
		}
		//checking if there is enough money
		if(amount > totalFunds) {
			System.out.println("Uh oh - Neptune only has an available balance of " + totalFunds + ", which is less than" + amount);
			System.out.println();
			return;
		}
		Currency o;
		//Saturn
		if(other instanceof Saturn) {
			o = (Saturn) other;
			
		}
		else{
			o = (Mars) other;
		}
		System.out.println("Converting from " + this.currencyName +" to " + o.currencyName +" and initiating transfer...");
		//Conversion
		double eAmount = this.toEarthDollars(amount);
		double oAmount = o.fromEarthDollars(eAmount);
		System.out.println("" + String.format("%.2f",amount) + " " +this.currencyName +" = " + String.format("%.2f",eAmount) + " EarthDollars = " + String.format("%.2f",oAmount) + " " +o.currencyName);
		//fee calculation
		double fee = 5;
		System.out.println("Neptune exchange fee is " + fee);
		//Funds transfer
		this.totalFunds = this.totalFunds - amount - fee;
		o.totalFunds = o.totalFunds + oAmount;
		System.out.println(this.currencyName + " has a total of " + String.format("%.2f",this.totalFunds));
		System.out.println(o.currencyName + " has a total of " + String.format("%.2f",o.totalFunds));
		System.out.println();
		
	}
}
