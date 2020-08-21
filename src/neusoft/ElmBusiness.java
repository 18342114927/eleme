package neusoft;

import neusoft.domain.Business;
import neusoft.view.FoodView;
import neusoft.view.Impl.BusinessViewImpl;
import neusoft.view.Impl.FoodViewImpl;

import java.util.Scanner;

public class ElmBusiness {
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        work();
    }
    public static void work(){
        System.out.println("--------------------------------------------------");
        System.out.println("|\t\t\t\t饿了么控制台版后台管理系统 v1.0\t\t\t\t|");
        System.out.println("--------------------------------------------------");
        //调用商家登录
        BusinessViewImpl businessView = new BusinessViewImpl();
        Business business = businessView.login();

        if(business!=null){
            int menu=0;
            System.out.println("欢迎来到饿了么商家管理系统");
            //创建一个菜单
            while (menu!=5){
                System.out.println("========= 一级菜单1.查看商家信息=2.修改商家信息=3.更改密码=4.所属商品管理=5.退出系统 =========");
                System.out.println("请选择相应的菜单编号");
                menu = input.nextInt();

                switch (menu){
                    case 1:
                        businessView.showBusinessInfo(business.getBusinessId());
                        break;
                    case 2:
                        businessView.updateBusinessInfo(business.getBusinessId());
                        break;
                    case 3:
                        businessView.updateBusinessPasswordInfo(business.getBusinessId());
                        break;
                    case 4:
                        foodManager(business.getBusinessId());
                        break;
                    case 5:System.out.println("=========欢迎下次光临饿了么系统========");
                        break;
                    default:
                        System.out.println("没有这个菜单项");
                        break;
                }
            }

        }else {
            System.out.println("账号或密码有误请从新输入");
        }
    }
    private static void foodManager(int businessId){
        FoodView foodView = new FoodViewImpl();
        int menu=0;
        while (menu!=5){
            System.out.println("========= 二级菜单1.查看食品列表=2.新增食品=3.修改食品=4.删除食品=5.返回一级菜单=========");
            System.out.println("请选择相应的菜单编号");
            menu = input.nextInt();

            switch (menu){
                case 1:
                    foodView.showFoodList(businessId);
                    break;
                case 2:
                    foodView.saveFood(businessId);
                    break;
                case 3:
                    foodView.updateFood(businessId);
                    break;
                case 4:
                    foodView.removeFood(businessId);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("没有这个菜单项");
                    break;
            }
        }
    }
}
