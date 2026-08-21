package vn.edu.eaut.lab8.repository;

import vn.edu.eaut.lab8.model.Product;
import java.util.*;

public class ProductRepository {

    private static final List<Product> data = new ArrayList<>();
    private static int autoId = 3;

    static {
        data.add(new Product(1, "Bàn phím cơ", 590000, 20));
        data.add(new Product(2, "Chuột không dây", 250000, 35));
    }

    public List<Product> findAll() {
        return data;
    }

    public Product findById(int id) {
        return data.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public void add(Product p) {
        p.setId(autoId++);
        data.add(p);
    }

    public void update(Product p) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == p.getId()) {
                data.set(i, p);
                break;
            }
        }
    }

    public void delete(int id) {
        data.removeIf(x -> x.getId() == id);
    }
}
