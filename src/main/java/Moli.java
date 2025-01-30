import java.util.Scanner;

public class Moli {

    public static void main(String[] args) {
        final String DIVIDER = "____________________________________________________________";
        final String LOGO = "\n"
                + "                ███╗   ███╗ ██████╗ ██╗     ██╗\n"
                + "                ████╗ ████║██╔═══██╗██║     ██║\n"
                + "                ██╔████╔██║██║   ██║██║     ██║\n"
                + "                ██║╚██╔╝██║██║   ██║██║     ██║\n"
                + "                ██║ ╚═╝ ██║╚██████╔╝███████╗██║\n"
                + "                ╚═╝     ╚═╝ ╚═════╝ ╚══════╝╚═╝";

        System.out.println(LOGO);
        System.out.println(DIVIDER);
        System.out.println("Hello, dear one. I'm Moli.\nI'm here for you💙");
        System.out.println(DIVIDER);

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            String input = scanner.nextLine().trim();

            if ("bye".equalsIgnoreCase(input)) {
                System.out.println(DIVIDER);
                System.out.println("Take care of yourself💖\nCome back anytime you need a listening ear.");
                System.out.println(DIVIDER);
                quit = true;
            }
            else{
                System.out.println(DIVIDER);
                System.out.println(" You said: " + input);
                System.out.println(" " + "I'm listening💖, tell me more.");
                System.out.println(DIVIDER);
            }
        }
        scanner.close();
    }
}
