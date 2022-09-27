import java.util.Scanner;

public class Main {
    public static final Scanner in = new Scanner(System.in);

    public static String[] welcomeMessage = {""};

    public static final String[][][] menu = new String[50][50][50];

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                for (int k = 0; k < 50; k++) { //
                    if (k == 0) {
                        menu[i][j][k] = "";
                    } else {
                        menu[i][j][k] = "0";
                    }
                }
            }
        }

        greet();
    }

    public static void greet() {
        System.out.println("\nWelcome to the Admin Panel of \"Salam Bro\" Fast Food Station!");
        System.out.println("Here you can do the following activities:");

        mainMenu();
    }

    public static void mainMenu() {
        for (int i = 0; i < welcomeMessage.length; i++) {
            welcomeMessage[i] = welcomeMessage[i].trim();
        }

        for (int i = 0; i < 50; i++) {
            menu[i][0][0] = menu[i][0][0].trim();

            for (int j = 1; j < 50; j++) {
                menu[i][j][0] = menu[i][j][0].trim();
            }
        }

        System.out.println("\n0. Exit Menu.");
        System.out.println("1. Change Welcome Message.");
        System.out.println("2. Change Menu.");
        System.out.println("3. Change SubMenu.");
        System.out.println("4. Change Prices.");
        System.out.println("5. Display Welcome Message.");
        System.out.println("6. Display Menu.");
        System.out.println("7. Display SubMenu.");
        System.out.println("8. Display Prices.");
        System.out.println("9. Switch to Client Mode.");
        System.out.println("10. Change PIN code.");
        System.out.print("\nChoose an activity: ");

        int n = in.nextInt();
        System.out.println();

        switch (n) {
            case (0):
                System.exit(0);
            case (1):
                changeWelcomeMsg();
                mainMenu();
            case (2):
                changeMenu();
                mainMenu();
            case (3):
                changeSubMenu();
                mainMenu();
            case (4):
                changePrices();
                mainMenu();
            case (5):
                displayWelcomeMsg();
                mainMenu();
            case (6):
                displayMenu();
                mainMenu();
            case (7):
                displaySubMenu();
                mainMenu();
            case (8):
                displayPrices();
                mainMenu();
            case (9):
                MenuClient.countNumberOfMenu();
                break;
            case (10):
                System.out.printf("Your current PIN code is %s%n%n", Switch.currentPIN);
                System.out.println("Do you want to change your PIN code?");

                Switch.askForPIN();
                break;
            default:
                System.out.println("There is no such number here. Please, try again.");

                mainMenu();
        }
    }

    public static void changeWelcomeMsg() {
        System.out.println("Please, type the message that clients will see when they enter the menu:");
        System.out.print("Tip: use underscore (_) as a splitter\n> ");

        in.nextLine();
        welcomeMessage = in.nextLine().split("_");
    }

    public static void changeMenu() {
        System.out.println("Here you can do the following activities:");
        System.out.println("0. Go back.");
        System.out.println("1. Add categories.");
        System.out.println("2. Remove categories.");
        System.out.print("\nYour choice: ");

        int n = in.nextInt();

        if (n == 0) {
            mainMenu();
        } else if (n == 1) {
            System.out.println("\nWrite the names of categories separated by comma (c1, c2, ...):");
            System.out.print("> ");

            in.nextLine();
            String[] tmp = in.nextLine().split(",");

            int count = 0;

            for (int i = 0; i < 50; i++) {
                if (!menu[i][0][0].equals("")) {
                    count++;
                } else {
                    break;
                }
            }

            for (String s : tmp) {
                menu[count][0][0] += s;

                count++;
            }
        } else if (n == 2) {
            if (!menu[0][0][0].equals("")) {
                System.out.println("\nChoose categories you want to remove by its index shown below:");

                displayMenu();

                System.out.println("\nTip: write the indexes separated by comma (i1, i2, ...):");
                System.out.print("> ");

                in.nextLine();
                String[] tmp1 = in.nextLine().split(","); // 1, 2, 5

                int[] tmp = new int[tmp1.length];

                for (int i = 0; i < tmp.length; i++) {
                    tmp[i] = Integer.parseInt(tmp1[i].trim()) - 1 - i; // 0, 0, 2
                }

                for (int value : tmp) {
                    for (int j = value; j < 49; j++) {
                        menu[j][0][0] = menu[j + 1][0][0];

                        for (int k = 1; k < 50; k++) {
                            menu[j][k][0] = "";

                            menu[j][k][k] = "0";
                        }

                        for (int k = 1; k < 50; k++) {
                            menu[j][k][0] = menu[j + 1][k][0];

                            menu[j][k][k] = menu[j + 1][k][k];
                        }
                    }
                }
            } else {
                System.out.println("\nYou don't have a menu yet.");
            }
        }
    }

    public static void changeSubMenu() {
        if (!menu[0][0][0].equals("")) {
            System.out.println("Here you can do the following activities:");
            System.out.println("0. Go back.");
            System.out.println("1. Add subcategories.");
            System.out.println("2. Remove subcategories.");
            System.out.print("\nYour choice: ");

            int n = in.nextInt();

            if (n == 0) {
                mainMenu();
            } else if (n == 1) {
                System.out.println("\nChoose the category for which you want to add subcategories:");

                displayMenu();

                System.out.print("\n> ");

                n = in.nextInt() - 1;

                System.out.println("\nWrite the names of subcategories separated by comma (c1, c2, ...):");
                System.out.print("> ");

                in.nextLine();
                String[] tmp = in.nextLine().split(",");

                int count = 1;

                for (int i = 1; i < 50; i++) {
                    if (!menu[n][i][0].equals("")) {
                        count++;
                    } else {
                        break;
                    }
                }

                for (String s : tmp) {
                    menu[n][count][0] = s.trim();

                    count++;
                }
            } else if (n == 2) {
                System.out.println("\nChoose the category for which you want to remove subcategories:");

                displayMenu();

                System.out.print("\n> ");

                n = in.nextInt() - 1;

                if (!menu[n][1][0].equals("")) {
                    System.out.println("\nChoose subcategories you want to remove by its index shown below:\n");

                    for (int i = 1; i < 50; i++) {
                        if (!menu[n][i][0].equals("")) {
                            System.out.printf("%d. %s.%n", i, menu[n][i][0]);
                        } else {
                            break;
                        }
                    }

                    System.out.println("\nTip: write the indexes separated by comma (i1, i2, ...):");
                    System.out.print("> ");

                    in.nextLine();
                    String[] tmp1 = in.nextLine().split(",");

                    int[] tmp = new int[tmp1.length];

                    for (int i = 0; i < tmp.length; i++) {
                        tmp[i] = Integer.parseInt(tmp1[i].trim()) - i;
                    }

                    for (int k : tmp) { // a
                        for (int i = k; i < 49; i++) { // q e r
                            menu[n][i][0] = menu[n][i + 1][0];

                            menu[n][i][i] = menu[n][i + 1][i + 1];
                        }

                        menu[n][menu[n].length - 1][0] = "";

                        menu[n][menu[n].length - 1][menu[n].length - 1] = "0";
                    }
                } else {
                    System.out.println("\nYou don't have subcategories in this category.");
                }
            }
        } else {
            System.out.println("You don't have a menu yet.");
        }
    }

    public static void changePrices() {
        if (!menu[0][0][0].equals("")) {
            System.out.println("Choose a category for which you want to change prices of subcategories:");

            displayMenu();

            System.out.print("\n> ");

            int n = in.nextInt() - 1;

            if (!menu[n][1][0].equals("")) {
                System.out.println("\nChoose subcategories for which you want to change prices:");

                for (int i = 1; i < 50; i++) {
                    if (!menu[n][i][0].equals("")) {
                        System.out.printf("%d. %s - %s kzt.%n", i, menu[n][i][0], menu[n][i][i]);
                    } else {
                        break;
                    }
                }

                System.out.println("\nWrite the indexes separated by comma (i1, i2, ...):");
                System.out.print("> ");

                in.nextLine();
                String[] tmp1 = in.nextLine().split(",");
                System.out.println();

                int[] tmp = new int[tmp1.length];

                for (int i = 0; i < tmp.length; i++) {
                    tmp[i] = Integer.parseInt(tmp1[i].trim());
                }

                for (int j : tmp) {
                    if (!menu[n][j][0].equals("")) {
                        System.out.printf("Enter a new price for %s: ", menu[n][j][0]);

                        menu[n][j][j] = in.nextLine();
                    } else {
                        break;
                    }
                }
            } else {
                System.out.println("\nYou don't have subcategories in this category.");
            }
        } else {
            System.out.println("You don't have a menu yet.");
        }
    }

    public static void displayWelcomeMsg() {
        if (!welcomeMessage[0].equals("")) {
            for (String s : welcomeMessage) {
                System.out.println(s);
            }
        } else {
            System.out.println("You don't have a welcome message yet.");
        }
    }

    public static void displayMenu() {
        if (!menu[0][0][0].equals("")) {
            for (int i = 0; i < 50; i++) {
                if (!menu[i][0][0].equals("")) {
                    System.out.printf("%d. %s.%n", i + 1, menu[i][0][0]);
                } else {
                    break;
                }
            }
        } else {
            System.out.println("You don't have a menu yet.");
        }
    }

    public static void displaySubMenu() {
        if (!menu[0][0][0].equals("")) {
            System.out.println("Choose the category for which you want to see its subcategories:");

            displayMenu();

            System.out.print("\n> ");

            int n = in.nextInt() - 1;
            System.out.println();

            if (!menu[n][1][0].equals("")) {
                for (int i = 1; i < 50; i++) {
                    if (!menu[n][i][0].equals("")) {
                        System.out.printf("%d. %s.%n", i, menu[n][i][0]);
                    } else {
                        break;
                    }
                }
            } else {
                System.out.println("You don't have subcategories in this category.");
            }
        } else {
            System.out.println("You don't have a menu yet.");
        }
    }

    public static void displayPrices() {
        if (!menu[0][0][0].equals("")) {
            System.out.println("Choose the category for which you want to see its prices:");

            displayMenu();

            System.out.print("\n> ");

            int n = in.nextInt() - 1;
            System.out.println();

            if (!menu[n][1][0].equals("")) {
                for (int i = 1; i < 50; i++) {
                    if (!menu[n][i][0].equals("")) {
                        System.out.printf("%d. %s - %s kzt.%n", i, menu[n][i][0], menu[n][i][i]);
                    } else {
                        break;
                    }
                }
            } else {
                System.out.println("You don't have subcategories in this category.");
            }
        } else {
            System.out.println("You don't have a menu yet.");
        }
    }
}