package modulo_02.Sesion06.Reto02_S6;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Mantenemos los métodos del reto anterior
    List<Producto> findByPrecioGreaterThan(double precio);
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByPrecioBetween(double min, double max);
    List<Producto> findByNombreStartingWithIgnoreCase(String prefijo);

    // Método básico para buscar por marca
    List<Producto> findByMarca(Marca marca);
}
