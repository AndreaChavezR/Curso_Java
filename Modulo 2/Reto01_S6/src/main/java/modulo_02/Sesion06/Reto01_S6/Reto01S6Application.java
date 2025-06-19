package modulo_02.Sesion06.Reto01_S6;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication
public class Reto01S6Application {

	public static void main(String[] args) {
		SpringApplication.run(Reto01S6Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductoRepository productoRepository) {
		return args -> {
			// Crear y guardar productos
			productoRepository.saveAll(List.of(
					new Producto("Laptop Lenovo", "Portátil de 14 pulgadas", 12500.00),
					new Producto("Mouse Logitech", "Mouse inalámbrico", 350.00),
					new Producto("Teclado Mecánico", "Teclado RGB", 950.00),
					new Producto("Monitor Samsung", "Monitor curvo 24\"", 3200.00)
			));

			// Consultas y visualización
			System.out.println("\n📦 Productos con precio mayor a 500:");
			productoRepository.findByPrecioGreaterThan(500).forEach(System.out::println);

			System.out.println("\n🔍 Productos que contienen 'lap':");
			productoRepository.findByNombreContainingIgnoreCase("lap").forEach(System.out::println);

			System.out.println("\n🎯 Productos con precio entre 400 y 1000:");
			productoRepository.findByPrecioBetween(400, 1000).forEach(System.out::println);

			System.out.println("\n📘 Productos cuyo nombre empieza con 'm':");
			productoRepository.findByNombreStartingWithIgnoreCase("m").forEach(System.out::println);
		};
	}
}
