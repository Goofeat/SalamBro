public class MenuClient extends Main {
    public static final String[] orderList = new String[50];

    public static int $total = 0;

    public static int numberOfSubMenu = 0;

    public static int numberOfMenu = 0;

    public static int countOrder = 0;

    public static void countNumberOfMenu() {
        int tmp = 0;
        int tmp1 = 0;

        for (int i = 0; i < 50; i++) {
            for (int j = 1; j < 50; j++) {
                if (!menu[i][j][0].equals("")) {
                    tmp1++;
                } else {
                    break;
                }
            }

            if (!menu[i][0][0].equals("")) {
                tmp++;
            } else {
                break;
            }
        }

        numberOfSubMenu = tmp1;
        numberOfMenu = tmp;

        checkForValid();
    }

    public static void checkForValid() {
        if (!welcomeMessage[0].equals("")) {
            if (!menu[0][0][0].equals("")) {
                int tmp = 0;

                for (int i = 0; i < numberOfMenu; i++) {
                    if (!menu[i][1][0].equals("")) {
                        tmp++;
                    }
                }

                if (tmp == numberOfMenu) {
                    int tmp1 = 0;

                    for (int i = 0; i < numberOfMenu; i++) {
                        for (int j = 1; j < numberOfSubMenu + 1; j++) {
                            if (!menu[i][j][0].equals("") && !menu[i][j][0].equals("0")) {
                                tmp1++;
                            }
                        }
                    }

                    if (tmp1 == numberOfSubMenu) {
                        start();
                    } else {
                        System.out.println("You don't have prices in these subcategories:");

                        int q = 1;

                        for (int i = 0; i < numberOfMenu; i++) {
                            for (int j = 1; j < numberOfSubMenu + 1; j++) {
                                if (!menu[i][j][0].equals("") && menu[i][j][j].equals("0")) {
                                    System.out.printf("%d. %s.%n", q, menu[i][j][0]);
                                    q++;
                                }
                            }
                        }

                        mainMenu();
                    }
                } else {
                    System.out.println("You don't have subcategories in these categories:");

                    int q = 1;

                    for (int i = 0; i < numberOfMenu; i++) {
                        if (!menu[i][0][0].equals("") && !menu[i][1][0].equals("")) {
                            System.out.printf("%d. %s.%n", q, menu[i][0][0]);
                            q++;
                        }
                    }

                    mainMenu();
                }
            } else {
                System.out.println("You don't have a menu yet.");

                mainMenu();
            }
        } else {
            System.out.println("You don't have a welcome message yet.");

            mainMenu();
        }
    }

    public static void start() {
        for (int i = 0; i < 50; i++) {
            if (orderList[i].equals("")) break;
            orderList[i] = "";
        }

        for (String s : welcomeMessage) {
            System.out.println(s);
        }

        menu();
    }

    public static void menu() {
        System.out.println("\n0. Exit Menu.");

        int q = 1;

        for (int i = 0; i < 50; i++) {
            if (!menu[i][0][0].equals("")) {
                System.out.printf("%d. %s.%n", q, menu[i][0][0]);
                q++;
            } else {
                break;
            }
        }

        System.out.printf("%d. Clear Basket.%n", q);
        System.out.printf("%d. Check Basket.%n", q + 1);
        System.out.printf("%d. Switch to Admin Mode.%n", q + 2);

        System.out.print("\nChoose one: ");

        int choiceInMenu = in.nextInt();

        if (choiceInMenu == 0) {
            System.exit(0);
        } else if (choiceInMenu == q) {
            System.out.println("\nYour basket has been successfully emptied.");
            clearBasket(); menu();
        } else if (choiceInMenu == q + 1) {
            checkBasket();
        } else if (choiceInMenu == q + 2) {
            Switch.switchToAdminMode();
        } else if (choiceInMenu < 0) {
            System.out.println("There is no such number here. Please, try again.");

            menu();
        } else if (choiceInMenu < q) {
            subMenu(choiceInMenu - 1);
        }
    }

    public static void subMenu(int n) {
        System.out.println("\n0. Back to menu.");

        int tmp = 1;

        for (int i = 1; i < 50; i++) {
            if (!menu[n][i][0].equals("")) {
                System.out.printf("%d. %s - %s kzt.%n", i, menu[n][i][0], menu[n][i][i]);
                tmp++;
            } else {
                break;
            }
        }

        System.out.print("\nChoose one: ");

        in.nextLine();
        int choiceInSubMenu = in.nextInt();

        if (choiceInSubMenu == 0) {
            menu();
        } else if (choiceInSubMenu <= tmp && choiceInSubMenu > 0) {
            order(n, choiceInSubMenu);
        } else {
            System.out.println("There is no such number here. Please, try again.");
            subMenu(n);
        }
    }

    public static void order(int n, int m) {
        System.out.printf("%nHow many %s would you like to order? (\"0\" to go back)%n> ", menu[n][m][0]);

        in.nextLine();
        int x = in.nextInt();

        if (x < 0) {
            System.out.println("Error... Please, try again.");
            subMenu(n);
        } else if (x == 0) {
            subMenu(n);
        } else if (x == 1) {
            orderList[countOrder] = String.format("%s - %s kzt.%n", menu[n][m][0], menu[n][m][m]);

            $total += Integer.parseInt(menu[n][m][m]);

            countOrder++;
        } else if (x < 20) {
            orderList[countOrder] = String.format("%s (x%s) - %s kzt.%n", menu[n][m][0], x, menu[n][m][m]);

            $total += Integer.parseInt(menu[n][m][m]) * x;

            countOrder++;
        } else {
            System.out.printf("You can't order more than 10 %s.%n", menu[n][m][0]);
            subMenu(n);
        }

        menu();
    }

    public static void clearBasket() {
        for (int i = 0; i < 50; i++) {
            if (orderList[i].equals("")) break;
            orderList[i] = "";
        }

        $total = 0;
    }

    public static void checkBasket() {
        if ($total == 0) {
            System.out.println("\nBasket is empty.\nTotal price: 0 kzt.");
        } else {
            System.out.println("\nBasket:");

            for (int i = 0; i < countOrder + 1; i++) {
                if (orderList[i].equals("")) break;
                System.out.print(orderList[i]);
            }

            System.out.println("Total price: " + $total + " kzt.");
        }

        menu();
    }
}