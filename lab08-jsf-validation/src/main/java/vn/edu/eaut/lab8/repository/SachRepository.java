package vn.edu.eaut.lab8.repository;

import vn.edu.eaut.lab8.model.Sach;
import java.util.*;

public class SachRepository {

    private static final List<Sach> data = new ArrayList<>();
    private static int autoId = 3;

    static {
        data.add(new Sach(1, "Lập trình Java cơ bản", "Nguyễn Văn A", 2020));
        data.add(new Sach(2, "Jakarta EE thực chiến", "Trần Thị B", 2023));
    }

    public List<Sach> findAll() {
        return data;
    }

    public Sach findById(int id) {
        return data.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    public void add(Sach s) {
        s.setId(autoId++);
        data.add(s);
    }

    public void update(Sach s) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == s.getId()) {
                data.set(i, s);
                break;
            }
        }
    }

    public void delete(int id) {
        data.removeIf(x -> x.getId() == id);
    }
}
