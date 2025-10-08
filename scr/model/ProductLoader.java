package scr.model;

import java.io.*;
import java.util.List;
import java.util.ArrayList;


public class ProductLoader {
    
    public static List<Product> loadProducts(String pathfile) {
        List<Product> products = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(pathfile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                double price = Double.parseDouble(values[2]);
                products.add(new Product(id, name, price));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
      
        } catch (NumberFormatException e) {
            System.err.println("Error en el formato del archivo: " + e.getMessage());
  
        }
        catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
      
        }finally {
            System.out.println("Cierre del programa.");
        }
        return products;
    }
}