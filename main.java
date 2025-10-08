import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import scr.model.Customer;
import scr.model.Order;
import scr.model.Product;
import scr.model.ProductLoader;

public class main {
    public static void main(String[] args) {
         
        List<Product> catalogo = ProductLoader.loadProducts("product.txt");
        if (catalogo == null || catalogo.isEmpty()) {
            System.out.println("El catálogo está vacío o no se pudo cargar. No se puede continuar.");
            return;
        }
        

        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer("Andres Vargas", "andrescamilo.vargas@uptc.edu.co");
        Order order = new Order(101); 



        System.out.println("Bienvenido, " + customer.getName() + ".");
        System.out.println("Aquí está nuestro catálogo de productos:");
        System.out.println("0. Finalizar compra");
        
        for (int i = 0; i < catalogo.size(); i++) {
            System.out.println((i + 1) + ". " + catalogo.get(i).getName() + " - $" + catalogo.get(i).getPrice());
        }

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

        System.out.println("\nResumen del Pedido:");
        order.showOrder();
        scanner.close(); 
    }
}