/*
 * @author Ege Yay
 */
/*
 * ID NUMBER:260679652
 */
public class dnaUtalities {
	public static void main(String[] args) {

		System.out.println(watsonCrickTripletComplement("AGC"));
	}

	public static boolean isValidBase(char base) {

		if (base == 'A' || base == 'T' || base == 'G' || base == 'C') {
			return true;

		}
		return false;
	}

	public static char watsonCrickComplement(char base) {

		if (base == 'A') {
			return 'T';
		}
		if (base == 'T') {
			return 'A';
		}
		if (base == 'G') {
			return 'C';
		}
		if (base == 'C') {
			return 'G';
		}

		return base;

	}

	public static String watsonCrickTripletComplement(String dnaSequence) {

		if (dnaSequence.length() != 3) {
			return "";
		}
		if (!(isValidBase(dnaSequence.charAt(0)) && isValidBase(dnaSequence.charAt(1))
				&& (isValidBase(dnaSequence.charAt(2))))) {
			return "";
		}
		String a = "";
		a += watsonCrickComplement(dnaSequence.charAt(0));
		a += watsonCrickComplement((dnaSequence.charAt(1)));
		a += watsonCrickComplement((dnaSequence.charAt(2)));

		return a;

	}
}
