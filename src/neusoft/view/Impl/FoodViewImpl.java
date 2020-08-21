package neusoft.view.Impl;

import neusoft.dao.FoodDao;
import neusoft.dao.Impl.FoodDaoImpl;
import neusoft.domain.Food;
import neusoft.view.FoodView;

import java.util.List;
import java.util.Scanner;

public class FoodViewImpl implements FoodView {
    Scanner input = new Scanner(System.in);

    @Override
    public void showFoodList(Integer businessId) {
        FoodDaoImpl dao = new FoodDaoImpl();
        List<Food> foodList = dao.findAll(businessId);
        System.out.println("食品编号" + "\t" + "食品名称"+"\t"+"食品介绍"+"\t"+"食品价格"+"\t"+"商家编号");
        for (Food food : foodList) {
            System.out.println(food.getFoodId() + "\t" + food.getFoodName()+"\t"+food.getFoodExplain()+"\t"+food.getFoodPrice()+"\t"+food.getBusinessId());
        }
    }

    @Override
    public void saveFood(Integer businessId) {
        System.out.println("请输入食品名字");
        String foodName = input.next();
        FoodDao dao = new FoodDaoImpl();
        int foodId = dao.saveFood(foodName);
        if (foodId > 0) {
            System.out.println("新建食品成功！ 食品编号为" + foodId);
        } else {
            System.out.println("新建食品失败！");
        }
    }


    @Override
    public void updateFood(Integer businessId) {
        String inputStr = "";
        int foodId=0;
        FoodDao dao = new FoodDaoImpl();
        List<Food> list = dao.findAll(businessId);
        if(list.size()==0){
            System.out.println("没有任何食品");
        }else {
            System.out.println("请输入要修改的食品编号");
            foodId=input.nextInt();
            Food food = dao.getFoodById(foodId);
            System.out.println(food);// 先显示一遍食品信息,方便用户查看修改
            System.out.println("是否修改食品名称(y/n)");
            inputStr = input.next();
            if (inputStr.equals("y")) {
                System.out.println("请输入新的食品名称");
                food.setFoodName(input.next());
            }
            int res = dao.updateFood(food);
            if (res > 0)
                System.out.println("修改食品信息成功");
            else
                System.out.println("修改食信息失败");
        }
    }

    @Override
    public void removeFood(Integer businessId) {
        System.out.println("请输入要删除的食物编号");
        int foodId = input.nextInt();
        System.out.println("确定要删除吗(y/n)");
        String s = input.next();
        if (s.equals("y")) {
            FoodDao foodDao = new FoodDaoImpl();
            int res = foodDao.removeFood(foodId);
            if (res > 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        } else {
            System.out.println("取消删除");
        }
    }
}
