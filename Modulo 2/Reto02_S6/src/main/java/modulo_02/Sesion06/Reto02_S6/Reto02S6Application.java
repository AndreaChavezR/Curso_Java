package modulo_02.Sesion06.Reto02_S6;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Reto02S6Application {

	public static void main(String[] args) {
		SpringApplication.run(Reto02S6Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductoRepository productoRepository,
								  MarcaRepository marcaRepository) {
		return args -> {
			// Crear marcas
			Marca lenovo = new Marca("Lenovo");
			Marca samsung = new Marca("Samsung");
			Marca apple = new Marca("Apple");

			marcaRepository.saveAll(List.of(lenovo, samsung, apple));

			// Crear y guardar productos asociados a marcas
			productoRepository.saveAll(List.of(
					new Producto("Laptop ThinkPad", "Portátil empresarial", 18500.00, lenovo),
					new Producto("Tablet Yoga", "Tablet convertible", 7500.00, lenovo),
					new Producto("Galaxy S23", "Smartphone flagship", 21000.00, samsung),
					new Producto("Smart TV QLED", "TV 55\" 4K", 15000.00, samsung),
					new Producto("iPhone 15 Pro", "Smartphone premium", 28000.00, apple),
					new Producto("MacBook Air", "Portátil ultraligero", 25000.00, apple)
			));

			// Consultas y visualización
			System.out.println("\n📚 Productos por marca:");
			marcaRepository.findAll().forEach(marca -> {
				System.out.println("🏷️ " + marca.getNombre() + ":");
				productoRepository.findAll().stream()
						.filter(p -> p.getMarca() != null && p.getMarca().getId().equals(marca.getId()))
						.forEach(p -> System.out.println("   - " + p.getNombre()));
			});

			// Consultas adicionales del reto anterior
			System.out.println("\n📦 Productos con precio mayor a 15000:");
			productoRepository.findByPrecioGreaterThan(15000).forEach(System.out::println);

			System.out.println("\n🔍 Productos que contienen 'Phone':");
			productoRepository.findByNombreContainingIgnoreCase("Phone").forEach(System.out::println);
		};
	}
}
