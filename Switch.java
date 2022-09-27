public class Switch extends Main {

	public static String currentPIN = "1234";

	public static void askForPIN() {
		System.out.print("\n\"Y\" for YES, \"N\" for NO.\n> ");

		in.nextLine();
		String answer = in.next().toUpperCase().trim();

		if (answer.equals("Y")) {
			changePIN();
		} else if (answer.equals("N")) {
			mainMenu();
		} else {
			askForPIN();
		}
	}

	public static void changePIN() {
		System.out.print("\nWrite your new PIN code (4 digits):\n> ");

		String newPass = in.next();

		if (newPass.equals(currentPIN)) {
			System.out.println("Your new PIN code must not match the current one.");

			changePIN();
		}

		try {
			Integer.parseInt(newPass);

			if (newPass.length() == 4) {
				System.out.print("Confirm your new PIN code:\n> ");

				String conNewPass = in.next();

				if (newPass.equals(conNewPass)) {
					currentPIN = newPass;

					System.out.println("\nPassword changed.");
				} else {
					System.out.println("\nWrong confirmation... Password not changed.");
				}

				mainMenu();
			} else {
				System.out.println("\nPlease, try again... (4 digits)");

				changePIN();
			}
		} catch (NumberFormatException e) {
			System.out.println("\nPlease, try again... (4 digits)");

			changePIN();
		}
	}

	public static void switchToAdminMode() {
		System.out.print("\nPlease, write your PIN code (4 digits):\n> ");

		in.nextLine();
		String answer = in.next();

		if (currentPIN.equals(answer)) {
			MenuClient.clearBasket();
			greet();
		} else {
			System.out.println("\nWrong PIN code...");

			MenuClient.menu();
		}
	}
}