package neusoft.view.Impl;

import neusoft.dao.BusinessDao;
import neusoft.dao.Impl.BusinessDaoImpl;
import neusoft.domain.Business;
import neusoft.view.BusinessView;

import java.util.List;
import java.util.Scanner;

public class BusinessViewImpl implements BusinessView {
    Scanner input = new Scanner(System.in);

    /**
     * 显示所有商家信息
     */
    @Override
    public void listBusinessAll() {
        BusinessDaoImpl dao = new BusinessDaoImpl();
        List<Business> list = dao.listBusiness(null, null);
        System.out.println("商家编号\t商家名称\t商家地址\t商家介绍\t起送费\t配送费");
        for (Business b : list) {
            System.out.println(
                    b.getBusinessId() + "\t" +
                            b.getBusinessName() + "\t" +
                            b.getBusinessAddress() + "\t" +
                            b.getBusinessExplain() + "\t" +
                            b.getStarPrice() + "\t" +
                            b.getDeliveryPrice() + "\t");
        }
    }

    /**
     * 搜索商家
     */
    @Override
    public void listBusinessBySearch() {
        String businessName = "";
        String busineessAddress = "";
        String inputStr = "";
        System.out.println("是否需要输入商家名称关键字(y/n)");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入商家名称关键字");
            businessName = input.next();
        }
        System.out.println("是否需要输入商家地址关键字(y/n)");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入商家地址关键字");
            busineessAddress = input.next();
        }
        BusinessDao dao = new BusinessDaoImpl();
        List<Business> list = dao.listBusiness(businessName, busineessAddress);
        System.out.println("商家编号\t商家名称\t商家地址\t商家介绍\t起送费\t配送费");
        for (Business b : list) {
            System.out.println(
                    b.getBusinessId() + "\t" +
                            b.getBusinessName() + "\t" +
                            b.getBusinessAddress() + "\t" +
                            b.getBusinessExplain() + "\t" +
                            b.getStarPrice() + "\t" +
                            b.getDeliveryPrice() + "\t");
        }
    }

    /**
     * 保存商家
     */
    @Override
    public void saveBusiness() {
        System.out.println("请输入商家名字");
        String businessName = input.next();
        BusinessDao dao = new BusinessDaoImpl();
        int businessId = dao.saveBusiness(businessName);
        if (businessId > 0) {
            System.out.println("新建商家成功！ 商家编号为" + businessId);
        } else {
            System.out.println("新建商家失败！");
        }
    }

    /**
     * 删除的时候注意要开启事务
     */
    @Override

    public void deleteBusiness() {
        System.out.println("请输入商家编号：");
        int businessId = input.nextInt();
        BusinessDao dao = new BusinessDaoImpl();
        System.out.println("确认要删除吗(y/n)：");
        if (input.next().equals("y")) {
            int result = dao.deleteBusiness(businessId);
            if (result == 1) {
                System.out.println("删除商家成功！");
            } else {
                System.out.println("删除商家失败！");
            }
        }
    }

    @Override
    public Business login() {
        System.out.println("请输入商家的编号: ");
        Integer businessId = input.nextInt();
        System.out.println("请输入密码: ");
        String password = input.next();
        BusinessDaoImpl dao = new BusinessDaoImpl();
        return dao.getBusinessByNameBypass(businessId, password);
    }

    @Override
    public void showBusinessInfo(Integer businessId) {
        //调用dao
        BusinessDaoImpl dao = new BusinessDaoImpl();
        Business business = dao.getBusinessByBusinessId(businessId);
        System.out.println(business);
    }

    @Override
    public void updateBusinessInfo(Integer businessId) {
        BusinessDao dao = new BusinessDaoImpl();
        Business business = dao.getBusinessByBusinessId(businessId);
        String inputStr = "";
        System.out.println(business);// 先显示一遍商家信息， 方便用户查看修改
        System.out.println("是否修改商家名称(y/n)");
        inputStr = input.next();
        if (inputStr.equals("y")){
            System.out.println("请输入新的商家名称");
            business.setBusinessName(input.next());
        }
        System.out.println("是否修改商家地址(y/n)");
        inputStr = input.next();
        if (inputStr.equals("y")){
            System.out.println("请输入新的商家地址");
            business.setBusinessAddress(input.next());
        }
        System.out.println("是否修改商家介绍(y/n)");
        inputStr = input.next();
        if (inputStr.equals("y")){
            System.out.println("请输入新的商家介绍");
            business.setBusinessExplain(input.next());
        }
        System.out.println("是否修改商家起送费(y/n)");
        inputStr = input.next();
        if (inputStr.equals("y")){
            System.out.println("请输入新的起送费");
            business.setStarPrice(input.nextDouble());
        }
        System.out.println("是否修改商家配送费(y/n)");
        inputStr = input.next();
        if (inputStr.equals("y")){
            System.out.println("请输入新的配送费");
            business.setDeliveryPrice(input.nextDouble());
        }
        int res = dao.updateBusiness(business);
        if(res > 0)
            System.out.println("修改商家信息成功");
        else
            System.out.println("修改商家信息失败");
    }

    @Override
    public void updateBusinessPasswordInfo(Integer businessId) {
        BusinessDao dao = new BusinessDaoImpl();
        Business business = dao.getBusinessByBusinessId(businessId);
        String inputStr1 = "";
        String inputStr2 = "";
        String inputStr3 = "";
        System.out.println(business);// 先显示一遍商家信息， 方便用户查看修改
        System.out.println("请输入商家原密码:");
        inputStr1 = input.next();
        if (inputStr1.equals(business.getPassword())){
            System.out.println("请输入新的商家新密码");
            inputStr2=input.next();
            System.out.println("请再次输入商家新密码");
            inputStr3=input.next();
            if(inputStr2.equals(inputStr3)){
                business.setPassword(inputStr2);
            }
            else {
                System.out.println("两次密码输入不一致");
            }

        int res = dao.updateBusinessPassword(business);
        if(res > 0)
            System.out.println("修改商家信息成功");
        else
            System.out.println("修改商家信息失败");
        }
    }
}
