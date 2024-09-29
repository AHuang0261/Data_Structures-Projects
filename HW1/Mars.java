
public class Mars extends Currency implements Exchangeable{
	
	public Mars() {
		currencyName = "Mars Money";
		totalFunds = 100.0;	}
	
	public Mars(double start) {
		currencyName = "Mars Money";
		totalFunds = start;
	}
	
	public double toEarthDollars(double amount) {
		return amount / Exchangeable.MM;
	}
	public double fromEarthDollars(double EarthDollars) {
		return EarthDollars * Exchangeable.MM;
	}
	
	
	public void exchange(Exchangeable other, double amount) {
		//Checking if other is Saturn or Neptune
		if(! (other instanceof Saturn || other instanceof Neptune)) {
			System.out.println("We don't service that planet.");
			System.out.println();
			return;
		}
		//checking if there is enough money
		if(amount > totalFunds) {
			System.out.println("Uh oh - Mars only has an available balance of " + totalFunds + ", which is less than" + amount);
			System.out.println();
			return;
		}
		Currency o;
		//Saturn
		if(other instanceof Saturn) {
			o = (Saturn) other;
			
		}
		else{
			o = (Neptune) other;
		}
		System.out.println("Converting from " + this.currencyName +" to " + o.currencyName +" and initiating transfer...");
		//Conversion
		double eAmount = this.toEarthDollars(amount);
		double oAmount = o.fromEarthDollars(eAmount);
		System.out.println("" + String.format("%.2f",amount) + " " +this.currencyName +" = " + String.format("%.2f",eAmount) + " EarthDollars = " + String.format("%.2f",oAmount) + " " +o.currencyName);
		//fee calculation
		double fee = 0.1 * amount;
		System.out.println("Mars exchange fee is " + fee);
		//Funds transfer
		this.totalFunds = this.totalFunds - amount - fee;
		o.totalFunds = o.totalFunds + oAmount;
		System.out.println(this.currencyName + " has a total of " + String.format("%.2f",this.totalFunds));
		System.out.println(o.currencyName + " has a total of " + String.format("%.2f",o.totalFunds));
		System.out.println();
		
		
	}
	
	
	
}
