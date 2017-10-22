package assignments2017.a1posted;



import java.util.LinkedList;

public class NaturalNumber  {

	
	
	private int	base;       

	private LinkedList<Integer>  coefficients;

	//  Constructors

	NaturalNumber(int base){
		
		
		this.base = base;
		coefficients = new LinkedList<Integer>();
	}

	ing only represents a base 10 number when the base is given to be 10.
	 */
	NaturalNumber(String sBase,  int base) throws Exception{
		int i;
		this.base = base;
		coefficients = new LinkedList<Integer>();
		if ((base <2) || (base > 10)){
			System.out.println("constructor error:  base must be between 2 and 10");
			throw new Exception();
		}

		
			
		int l = sBase.length();
		for (int indx = 0; indx < l; indx++){  
			i = sBase.charAt(indx);
			if ( (i >= 48) && (i - 48 < base))                                                    				
				coefficients.addFirst( new Integer(i-48) );
			else{
				System.out.println("constructor error:  all coefficients should be non-negative and less than base");
				throw new Exception();
			}			
		}
	}
	
	

	NaturalNumber(int i,  int base) throws Exception{
		this.base = base;
		coefficients = new LinkedList<Integer>();
		
		if ((i >= 0) && (i < base))
			coefficients.addFirst( new Integer(i) );
		else {
			System.out.println("constructor error: all coefficients should be non-negative and less than base");
			throw new Exception();
		}
	}

	
	
	
	
	public int getBase()
	{
		return base;
	}
	public LinkedList<Integer>  getCoefficients()
	{
		return coefficients;
	}
	
	
	
	
	
	public NaturalNumber plus( NaturalNumber  second) throws Exception{
				
		//  initialize the sum as an empty list of coefficients
		
		NaturalNumber sum = new NaturalNumber( this.base );

		if (this.base != second.base){
			System.out.println("ERROR: bases must be the same in an addition");
			throw new Exception();
		}

		

		NaturalNumber  firstClone  = this.clone();
		NaturalNumber  secondClone = second.clone();

		
		
		int   diff = firstClone.coefficients.size() - second.coefficients.size();
		while (diff < 0){  // second is bigger
                                                     		
			firstClone.coefficients.addLast(0);			        
			diff++;
		}
		while (diff > 0){  //  this is bigger
			secondClone.coefficients.addLast(0);
			diff--;
		}

		
		//   one more term with the carry.
		int i; // counter
		int carry=0;

		for ( i=0 ; i< firstClone.coefficients.size() ; i++) {
			sum.coefficients.addLast( (firstClone.coefficients.get(i) + secondClone.coefficients.get(i) + carry) % firstClone.base    ) ; //implement the sum value

			carry = ((firstClone.coefficients.get(i) + secondClone.coefficients.get(i)+ carry)/ firstClone.base ); //revalue carry for the second loop
		}
		sum.coefficients.addLast(carry); // addding the carry at the end

		for(int z=0 ; z< firstClone.coefficients.size() ; z++){    //removing the zeros at the begining
			if (sum.coefficients.getLast()== 0) {
				sum.coefficients.removeLast();
			}
			else {
				continue;
			}
		}

		
		
		return sum;		
	}
	

	
	
	public NaturalNumber slowTimes( NaturalNumber  multiplier) throws Exception{

		NaturalNumber prod  = new NaturalNumber(0, this.base);
		NaturalNumber one   = new NaturalNumber(1, this.base);
		for (NaturalNumber counter = new NaturalNumber(0, this.base);  counter.compareTo(multiplier) < 0;  counter = counter.plus(one) ){
			prod = prod.plus(this);
		}
		return prod;
	}
	
	

	
	
	public NaturalNumber times( NaturalNumber multiplicand) throws Exception{
		
		//  initialize product as an empty list of coefficients
		
		NaturalNumber product	= new NaturalNumber( this.base );

		if (this.base != multiplicand.base){
			System.out.println("ERROR: bases must be the same in a multiplication");
			throw new Exception();
		}
		
		



		NaturalNumber tempProduct = new NaturalNumber(this.base);


		for (int i = 0; i < this.getCoefficients().size(); i++) {


			tempProduct = multiplicand.timesSingleDigit(this.coefficients.get(i), i ); //implememnt the helper function before the sum

			product = tempProduct.plus(product);
		}

		
		return product;
	}




	public NaturalNumber timesSingleDigit(int digit, int index){
			int carry = 0 ;
			int prod=0 ;
			int tempDigit=0;
			int c= 0;
			NaturalNumber product1 = new NaturalNumber(this.base);
			for(int i=0 ; i < this.coefficients.size() ; i++ ){
				prod = this.coefficients.get(i) * digit;
				tempDigit = prod % base;      //implementing the single digit and carry out for the single digit
				product1.coefficients.addLast(tempDigit+carry);
				carry = prod / this.base;
			}
		product1.coefficients.addLast(carry); // addding the carry at the end


		for (int j = 0; j<index; j++){

			product1.coefficients.addFirst(c);

		}

		while ((product1.coefficients.size() > 1) &
				(product1.coefficients.getLast().intValue() == 0)){
			product1.coefficients.removeLast();
		}



		return product1;
	}



	
	public NaturalNumber  minus(NaturalNumber second) throws Exception{

		//  initialize the result (difference) as an empty list of coefficients
		
		NaturalNumber  difference = new NaturalNumber(this.base);

		if (this.base != second.base){
			System.out.println("ERROR: bases must be the same in a subtraction");
			throw new Exception();
		}
		

		NaturalNumber  first = this.clone();

		
		 
		if (this.compareTo(second) < 0){
			System.out.println("Error: the subtraction a-b requires that a > b");
			throw new Exception();
		}

		
		int i;
		int borrow = 0;
		int tempborrow = 0;  //initializing the varibles
		for (i =  0; i < this.coefficients.size() ; i++){
			if (!(this.coefficients.get(i)-borrow >= second.coefficients.get(i))){
				borrow = 1;    //always barrow or continue would be used
			}else{
				borrow = 0;
			}
			difference.coefficients.addLast( (this.coefficients.get(i)+ this.base * borrow - second.coefficients.get(i)-tempborrow ) % this.base );
			tempborrow = borrow;
		}
		


		
		
		
		while ((difference.coefficients.size() > 1) & 
				(difference.coefficients.getLast().intValue() == 0)){
			difference.coefficients.removeLast();
		}
		return difference;	
	}

	

	
	public NaturalNumber slowDivide( NaturalNumber  divisor) throws Exception{

		NaturalNumber quotient = new NaturalNumber(0,base);
		NaturalNumber one = new NaturalNumber(1,base);
		NaturalNumber remainder = this.clone();
		while ( remainder.compareTo(divisor) >= 0 ){
			remainder = remainder.minus(divisor);
			quotient = quotient.plus(one);
		}
		return quotient;
	}


	
	
	
	public NaturalNumber divide( NaturalNumber  divisor ) throws Exception{
		
		
		
		NaturalNumber  quotient = new NaturalNumber(this.base);
		
		if (this.base != divisor.base){
			System.out.println("ERROR: bases must be the same in an division");
			throw new Exception();
		}
		
		if(divisor.compareTo(new NaturalNumber(0, this.base))==0){
			System.out.println("ERROR: division by zero not possible");
			throw new Exception();
		}
		
		NaturalNumber  remainder = this.clone();

		

		
		Iterator<Integer> reversedLisResult = this.coefficients.descendingIterator(); //used iterator as couldn't figure out to do it with the reverse numbering.
		int z = 0;
		NaturalNumber tempQ;
		int count = 0;

		NaturalNumber digitNumber = new NaturalNumber(this.base);
		NaturalNumber tempRes = new NaturalNumber(this.base);

		LinkedList<Integer> ListResult = new LinkedList<Integer>();    //implemented a different list for the coefficients of the result


		while (reversedLisResult.hasNext()){     //.hasNext() -> is probably illegal, sorry
			z = (int)reversedLisResult.next();
			digitNumber =new NaturalNumber(this.base,new int[] {bi}).add(digitNumber);
			tempQ = new NaturalNumber(this.base, new int []{0});
			while (divisor.multiply(q).compareTo(digitNumber)<=0){           // loop to start from the left to right checking and multiplying backwards
				tempQ=tempQ.add(new NaturalNumber(this.base,new int[] {1}));
				if ((divisor.multiply(tempQ).compareTo(digitNumber))>0){
					tempQ=tempQ.subtract(new NaturalNumber(this.base,new int[]{1}));
					break;
				}
			}
			tempRes = divisor.multiply(tempQ);
			remainder = digitNumber.subtract(tempRes);     // setting up the output variables, and also updating the list
			digitNumber = remainder.timesBaseToThePower(1);
			ListResult.addFirst(tempQ.coefficients.getFirst());
		}

		for( int h=0 ; h< ListResult.size() ; h++) {	     //transfer the values from the List to the quotient.coef linked list
			quotient.coefficients.set(i,ListResult.get(i));
		}



		return quotient;		
	}
	
	

	
	
	
	public NaturalNumber  clone(){

		

		NaturalNumber copy = new NaturalNumber(this.base);
		for (int i=0; i < this.coefficients.size(); i++){
			copy.coefficients.addLast( new Integer( this.coefficients.get(i) ) );
		}
		return copy;
	}
	
	
	
	private int 	compareTo(NaturalNumber second){


		int diff = this.coefficients.size() - second.coefficients.size();
		if (diff < 0)
			return -1;
		else if (diff > 0)
			return 1;
		else { 
			
			
			
			boolean done = false;
			int i = this.coefficients.size() - 1;
			while (i >=0 && !done){
				diff = this.coefficients.get(i) - second.coefficients.get(i); 
				if (diff < 0){
					return -1;
				}
				else if (diff > 0)
					return 1;
				else{
					i--;
				}
			}
			return 0;  
		}
	}

	
	private NaturalNumber timesBaseToThePower(int n){
		for (int i=0; i< n; i++){
			this.coefficients.addFirst(new Integer(0));
		}
		return this;
	}

	
	
	@Override
	public String toString(){	
		String s = new String(); 
		for (Integer coef : coefficients)     
			s = coef.toString() + s ;        
		return "(" + s + ")_" + base;		
	}
	
}

