package edu.poly.duan1.hibernateConfig;

import edu.poly.duan1.model.ChucVu;
import edu.poly.duan1.model.HoaDon;
import edu.poly.duan1.model.HoaDonCT;
import edu.poly.duan1.model.KhachHang;
import edu.poly.duan1.model.NCC;
import edu.poly.duan1.model.NXB;
import edu.poly.duan1.model.NguoiDung;
import edu.poly.duan1.model.Sach;
import edu.poly.duan1.model.SachCT;
import edu.poly.duan1.model.TheLoai;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateConfig {

    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=DUAN_1_GROUP1_");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123");
//        properties.put(Environment.SHOW_SQL, "true");

        conf.setProperties(properties);
        conf.addAnnotatedClass(Sach.class);
        conf.addAnnotatedClass(NguoiDung.class);
        conf.addAnnotatedClass(ChucVu.class);
        conf.addAnnotatedClass(TheLoai.class);
        conf.addAnnotatedClass(NXB.class);
        conf.addAnnotatedClass(NCC.class);
        conf.addAnnotatedClass(SachCT.class);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(HoaDonCT.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        getFACTORY();
    }

}
