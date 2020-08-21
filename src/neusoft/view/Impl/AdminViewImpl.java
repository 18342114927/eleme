package neusoft.view.Impl;

import neusoft.dao.AdminDao;
import neusoft.dao.Impl.AdminDaoImpl;
import neusoft.domain.Admin;
import neusoft.view.AdminView;

import java.util.Scanner;

public class AdminViewImpl implements AdminView {
    private Scanner input = new Scanner(System.in);
    @Override
    public Admin login() {
        System.out.println("请输入管理员的用户名: ");
        String adminName = input.next();
        System.out.println("请输入管理员的密码: ");
        String password = input.next();
        AdminDao dao = new AdminDaoImpl();
        return  dao.getAdminByNameBypass(adminName,password);

    }
}
