/**
 *
 * @author Ege Yay
 *
 */
/*
 * ID Number: 260679652
 */
public class MakingChange {
	
	public static void main(String[] args) {

		String moneyToChange = args[0];
		String realMoney = moneyToChange.replace(".", "");
		int money = Integer.parseInt(realMoney);
		getChange(money);

	}

	public static void getChange(int money) {

		System.out.println("there are " + money / 200 + " toonies");
		money = money % 200;

		System.out.println("there are " + money / 100 + " loonies");
		money = money % 100;

		System.out.println("there are " + money / 25 + " quarters");
		money = money % 25;

		System.out.println("there are " + money / 10 + " dimes");
		money = money % 10;

		System.out.println("there are " + money / 5 + " nickels");
		money = money % 5;

		System.out.println("there are " + money / 1 + " pennies");

	}

}
