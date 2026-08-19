package vn.edu.eaut.lab7.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.time.LocalDateTime;

/**
 * Listener ghi log khi ứng dụng khởi động/dừng
 * và khi session được tạo/hủy (Bài 12).
 */
@WebListener
public class AppLifecycleListener implements ServletContextListener, HttpSessionListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log("Ứng dụng Lab07-CRUD-MVC đã KHỞI ĐỘNG.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log("Ứng dụng Lab07-CRUD-MVC đã DỪNG.");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log("Session được TẠO, id = " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log("Session bị HỦY, id = " + se.getSession().getId());
    }

    private void log(String message) {
        System.out.println("[" + LocalDateTime.now() + "] [Lab07-Listener] " + message);
    }
}
