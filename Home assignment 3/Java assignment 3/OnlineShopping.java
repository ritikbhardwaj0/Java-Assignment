import java.util.Scanner;

class MinimumAmountException extends Exception {

    public MinimumAmountException(String message) {
        super(message);
    }
}


public class OnlineShopping {


    private static final int MINIMUM_CART_AMOUNT = 500;
    public void placeOrder(int cartAmount) throws MinimumAmountException {

        if (cartAmount < MINIMUM_CART_AMOUNT) {
            throw new MinimumAmountException(
                    "Minimum cart value must be ₹500.");
        }
        System.out.println(" Order Placed Successfully");
        System.out.println(" Cart Amount : ₹" + cartAmount);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        OnlineShopping onlineShopping = new OnlineShopping();

        try {

            System.out.print("Enter Cart Amount: ₹");
            int cartAmount = scanner.nextInt();

            onlineShopping.placeOrder(cartAmount);

        } catch (MinimumAmountException exception) {

            System.out.println("\nException: " + exception.getMessage());

        } catch (Exception exception) {

            System.out.println("\nInvalid Input! Please enter a numeric value.");

        } finally {

            System.out.println("\nThank you for shopping with us.");

            scanner.close();
        }
    }
}