/*
 *@author Ege Yay
 */
/*
 * ID NUMBER:260679652
 */

package leapYearCalculator;

public class leapYearCalculator {
	public static void main(String[] args) {

		String year1 = args[0];
		int year = Integer.parseInt(year1);
		printIsLeapYear(year);
		System.out.println(isLeapYear(year));
		System.out.println(subsequentLeapYear(year));
	}

	public static void printIsLeapYear(int year) {

		if (year % 400 == 0) {
			System.out.println(year + " is a leap year");
		} else if ((year % 4 == 0) && (year % 100 != 0)) {
			System.out.println(year + " is a leap year");
		} else if (year % 100 == 0) {
			System.out.println(year + " is not a leap year");
		} else {
			System.out.println(year + " is not a leap year");
		}
	}

	public static boolean isLeapYear(int year) {
		boolean year0 = false;

		if (year % 400 == 0) {
			year0 = true;
		} else if ((year % 4 == 0) && (year % 100 != 0)) {
			year0 = true;
		} else if (year % 100 == 0) {
			year0 = false;
		}
		return year0;
	}

	public static int subsequentLeapYear(int year) {
		for (int i = 0; i < 8; i++) {
			year++;
			if (isLeapYear(year)) {
				break;
			}
		}
		return year;

	}
}
