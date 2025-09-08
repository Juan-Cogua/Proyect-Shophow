
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Product> catalogo = ProductLoader.loadProducts("src/products.txt");
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer("Andres Vargas", "andrescamilo.vargas@uptc.edu.co");
        Order order = new Order(101); 

        // Catalogo de productos
        List<Product> catalog = new ArrayList<>();
        catalog.add(new Product("Laptop HP Pavilion", 2500.0));
        catalog.add(new Product("Mouse Logitech G502", 50.0));
        catalog.add(new Product("Teclado Mecánico Razer", 120.0));
        catalog.add(new Product("Monitor 27'' Samsung", 350.0));
        catalog.add(new Product("Webcam Full HD", 75.0));
        catalog.add(new Product("Disco Duro Externo 1TB", 60.0));
        catalog.add(new Product("Auriculares HyperX Cloud II", 99.0));
        catalog.add(new Product("Tarjeta Gráfica NVIDIA RTX 3060", 400.0));
        catalog.add(new Product("Impresora Multifuncional", 150.0));
        catalog.add(new Product("Router Wi-Fi 6", 80.0));

        System.out.println("Bienvenido, " + customer.getName() + ".");
        System.out.println("Aquí está nuestro catálogo de productos:");
        System.out.println("0. Finalizar compra");
        
        for (int i = 0; i < catalog.size(); i++) {
            System.out.println((i + 1) + ". " + catalog.get(i).getName() + " - $" + catalog.get(i).getPrice());
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

            if (selectedOption > 0 && selectedOption <= catalog.size()) {
                Product selectedProduct = catalog.get(selectedOption - 1);
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