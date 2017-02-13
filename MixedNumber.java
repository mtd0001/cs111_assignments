package edu.wvu.cs111.assignment5;

public class MixedNumber {

	private int whole;
	private Fraction fractionPart;
	
	public MixedNumber(){
		whole = 1;
		fractionPart = new Fraction(0, 1);
	}
	
	public MixedNumber(int wholes, Fraction fract){
		
		whole = wholes;
		fractionPart = fract; 
		
		if(whole < 0 && fract.getNumerator() < 0){
			fract.setNumerator(fract.getNumerator() * (-1));
			setWhole((whole * (-1)));
		}
		
		else if(fract.getNumerator() < 0){
				setWhole(whole * (-1));
				fract.setNumerator(fract.getNumerator() * (-1));
		}
				
		}
			
			
	
	// you receive a fraction, you make it into a whole number
	public MixedNumber( Fraction fract){
		
		if(fract.getNumerator() > fract.getDenominator()){
			whole = fract.getNumerator() / fract.getDenominator();
			fract.setNumerator((fract.getNumerator() % fract.getDenominator()));	
		}
		
		else if(fract.getNumerator() == fract.getDenominator()){
			whole = whole + 1;
			fract.setNumerator(0);
			fract.setDenominator(1);
		}
		
		else 
			// if the fraction is not improper, then this will reduce it and set the whole number to the 
			// default value of one
			setWhole(1);
			setFraction(fract);
			this.fractionPart.reduce();
	}
	
	private MixedNumber reduceMixed(){
		
		if(fractionPart.getNumerator() > fractionPart.getDenominator()){
			whole = whole + (fractionPart.getNumerator() / fractionPart.getDenominator());
			fractionPart.setNumerator((fractionPart.getNumerator() % fractionPart.getDenominator()));	
		}
		
		else if(fractionPart.getNumerator() == fractionPart.getDenominator()){
			whole = whole + 1;
			fractionPart.setNumerator(0);
			fractionPart.setDenominator(1);
		}
		
		else 
			fractionPart.reduce();
		
		return this;
		
	}
	
	public void setWhole(int num){
		whole = num;
	}
	
	public int getWhole(){
		return whole;
	}
	
	public void setFraction(Fraction newFract){
		fractionPart = newFract;
	}
	
	public Fraction getFraction(){
		return fractionPart;
	}
	
	// how do I know it calls the toString method from fraction
	public String toString(){
		
		String fraction = fractionPart.toString();
		String num = Integer.toString(whole) + " " + fraction;
	
		return num;
	}
	
	public MixedNumber add(MixedNumber B){
		
		whole = this.whole + (B.getWhole());
		fractionPart = Fraction.add(this.getFraction(), B.getFraction()); 
		
		 
		
		return this.reduceMixed();
	}
	
	// USE THE CONVER TO IMPROPER FRACTION METHOD TO DO THESE METHODS
	
	//////////////////////////////
	public MixedNumber add(Fraction B){
		
		
		this.getFraction().setNumerator(this.getFraction().getNumerator() * B.getDenominator());;
		B.setNumerator(B.getNumerator() * this.getFraction().getDenominator());
		
		this.getFraction().setDenominator(this.getFraction().getDenominator() * B.getDenominator());
		this.getFraction().setNumerator(B.getNumerator() + this.getFraction().getNumerator());
		
		
		
		
		return this.reduceMixed();
		
		
		
		
		
		
	}

	
	public MixedNumber subtract(MixedNumber B){
		
		whole = this.whole - B.getWhole();
		fractionPart = fractionPart.subtract(B.getFraction());
		
		if(whole < 0 && fractionPart.getNumerator() < 0){
			fractionPart.setNumerator(fractionPart.getNumerator() * (-1));
			setWhole((whole * (-1)));
		}
		
		else if(fractionPart.getNumerator() < 0){
				setWhole(whole * (-1));
				fractionPart.setNumerator(fractionPart.getNumerator() * (-1));
		}
		
		return this;
	
	}
	
	public MixedNumber subtract(Fraction B){
		
		this.getFraction().setNumerator(this.getFraction().getNumerator() * B.getDenominator());;
		B.setNumerator(B.getNumerator() * this.getFraction().getDenominator());
		
		this.getFraction().setDenominator(this.getFraction().getDenominator() * B.getDenominator());
		this.getFraction().setNumerator(B.getNumerator() - this.getFraction().getNumerator());
		
		
		if(whole < 0 && fractionPart.getNumerator() < 0){
			fractionPart.setNumerator(fractionPart.getNumerator() * (-1));
			setWhole((whole * (-1)));
		}
		
		else if(fractionPart.getNumerator() < 0){
				setWhole(whole * (-1));
				fractionPart.setNumerator(fractionPart.getNumerator() * (-1));
		}
		
		return this.reduceMixed();
		
	}
	
	public MixedNumber multiply(MixedNumber B){
		
		whole = B.getWhole() * whole;
		fractionPart.setNumerator(fractionPart.getNumerator() * B.getFraction().getNumerator());
		fractionPart.setDenominator(fractionPart.getDenominator() * B.getFraction().getDenominator());
		
		MixedNumber A = new MixedNumber(0,this.convertToImproperFraction());
		
		

		return this.reduceMixed();
	}
	

	public MixedNumber multiply(Fraction B){
		
		fractionPart.setNumerator(this.fractionPart.getNumerator() * B.getNumerator());
		fractionPart.setDenominator(this.fractionPart.getDenominator() * B.getDenominator());
		
		
		
		// call cinvert to improper fraction for when you can simplify your fraction
		MixedNumber A = new MixedNumber(whole, fractionPart);
		
		return A.reduceMixed();
	}
	
	// still needs work
	public MixedNumber divide(MixedNumber B){
		
		fractionPart.setNumerator(this.fractionPart.getNumerator() * B.fractionPart.getDenominator());
		fractionPart.setDenominator(this.fractionPart.getDenominator() * B.fractionPart.getNumerator());
		
		Fraction wholes = new Fraction(this.getWhole(), B.getWhole());
		
		wholes.setNumerator(wholes.getNumerator() * this.getFraction().getNumerator());
		wholes.setDenominator(wholes.getDenominator() * this.getFraction().getDenominator());
		
		MixedNumber A = new MixedNumber(0, wholes);
		
		return A.reduceMixed();
	}
	
	public MixedNumber divide(Fraction B){
		
		fractionPart.setNumerator(this.fractionPart.getNumerator() * B.getDenominator());
		fractionPart.setDenominator(this.fractionPart.getDenominator() * B.getNumerator());
		
		
		return this.reduceMixed();
		
	}
	
	// still have to convert a fraction such as 2/5 to a mixed number, improper is covered
	public Fraction convertToImproperFraction(){
		
		fractionPart.setNumerator(whole * fractionPart.getDenominator() + fractionPart.getNumerator());
		
		whole = 0;
		
		return fractionPart;
		
	}
	
}
