import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import scr.model.Customer;
import scr.model.Order;
import scr.model.Product;
import scr.model.ProductLoader;
import scr.model.MetodoDePago.*;

public class main {
    public static void main(String[] args) {
         
        List<Product> catalogo = ProductLoader.loadProducts("product.txt");
        if (catalogo == null || catalogo.isEmpty()) {
            System.out.println("El catálogo está vacío o no se pudo cargar. No se puede continuar.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer("Andres Vargas", "andrescamilo.vargas@uptc.edu.co");
     

        System.out.println("Bienvenido, " + customer.getName() + ".");
        System.out.println("Aquí está nuestro catálogo de productos:");
        System.out.println("0. Finalizar compra");
        
        for (int i = 0; i < catalogo.size(); i++) {
            System.out.println((i + 1) + ". " + catalogo.get(i).getName() + " - $" + catalogo.get(i).getPrice());
        }

        Order order = new Order(101, null);

        int selectedOption;
        do {
            System.out.print("Ingrese el número del producto que desea agregar al carrito (0 para finalizar): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Opción inválida. Intente de nuevo.");
                System.out.print("Ingrese el número del producto que desea agregar (0 para finalizar): ");
                scanner.next();
            }
            selectedOption = scanner.nextInt();

            if (selectedOption > 0 && selectedOption <= catalogo.size()) {
                Product selectedProduct = catalogo.get(selectedOption - 1);
                order.addProduct(selectedProduct);
                System.out.println(selectedProduct.getName() + " ha sido agregado al carrito.");
            } else if (selectedOption != 0) {
                System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (selectedOption != 0);

  if (order.getProducts().isEmpty()) {
            System.out.println("No agregaste productos. La orden no se puede procesar.");
            scanner.close();
            return;
        }

        System.out.println("\nResumen del Pedido:");
        order.showOrder();

        // === Elegir método de pago ===
        System.out.println("\nSeleccione el método de pago:");
        System.out.println("1. Tarjeta");
        System.out.println("2. Transferencia bancaria");
        System.out.println("3. Billetera digital");

        int paymentOption;
        do {
            System.out.print("Opción: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Opción inválida. Intente de nuevo.");
                scanner.next();
            }
            paymentOption = scanner.nextInt();
        } while (paymentOption < 1 || paymentOption > 3);

        scanner.nextLine(); // limpiar buffer
        PaymentMethod paymentMethod = null;
        double amount = order.TotalCost(); // Monto total

        switch (paymentOption) {
            case 1:
                System.out.print("Ingrese número de tarjeta: ");
                int cardNumber = scanner.nextInt();
                System.out.print("Ingrese CVV: ");
                int cvv = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Ingrese fecha de expiración (MM/AA): ");
                String expiration = scanner.nextLine();
                paymentMethod = new Card(customer.getName(), amount, cvv, cardNumber, expiration);
                break;

            case 2:
                System.out.print("Ingrese nombre de la cuenta: ");
                String accName = scanner.nextLine();
                System.out.print("Ingrese nombre del banco: ");
                String bankName = scanner.nextLine();
                paymentMethod = new BankTransfer(customer.getName(), amount, accName, bankName);
                break;

            case 3:
                System.out.print("Ingrese ID de la billetera digital: ");
                int walletID = scanner.nextInt();
                paymentMethod = new DigitalWallet(customer.getName(), amount, walletID);
                break;
        }

        // Asignar método de pago a la orden sin perder productos
        order.setPaymentMethod(paymentMethod);
        System.out.println("\nProcesando pago...");
        order.processOrderPayment();
        System.out.println("Pago realizado con éxito. ¡Gracias por su compra!");
        scanner.close();
    }
}