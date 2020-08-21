package neusoft.test;

import neusoft.dao.AdminDao;
import neusoft.dao.Impl.AdminDaoImpl;
import neusoft.domain.Admin;

public class TestAdmin {
    public static void main(String[] args) {
        AdminDao adminDao = new AdminDaoImpl();
        Admin admin = adminDao.getAdminByNameBypass("王磊", "123");
        System.out.println(admin);
    }
}