package neusoft.dao.Impl;

import neusoft.dao.BusinessDao;
import neusoft.domain.Business;
import neusoft.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessDaoImpl implements BusinessDao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;


    @Override
    public List<Business> listBusiness(String businessName, String businessAddress) {
        List<Business> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select * from business where 1=1");
        if (businessName != null && !businessName.equals("")) {
            // 传入了商家名
            sql.append(" and businessName like '%").append(businessName).append("%' ");
            System.out.println(sql);
        }
        if (businessAddress != null && !businessAddress.equals("")) {
            // 传入了商家名
            sql.append(" and businessAddress like '%").append(businessAddress).append("%' ");
            System.out.println(sql);
        }
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Business business = new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setPassword(rs.getString("password"));
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStarPrice(rs.getDouble("starPrice"));
                business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
                list.add(business);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, pstmt, conn);
        }
        return list;
    }

    @Override
    public int saveBusiness(String businessName) {
        int businessId = 0;
        // 附带一个初始密码
        String sql = "insert into business(businessName, password)values(?, '123456')";
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // 可以在prepareStatement中设置返回自增长列的值
            pstmt.setString(1, businessName);
            pstmt.executeUpdate();//更新数据
            rs = pstmt.getGeneratedKeys();// 获取自增长的列
            if (rs.next()){
                businessId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return businessId;
    }

    @Override
    public int deleteBusiness(int businessId) {
        int result=0;
        String sql = "DELETE FROM business WHERE business.businessId = ?";
        try {
            conn = JDBCUtils.getConnection();
            //手动开启事务
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, businessId);
            result=pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            //进入了异常的代码区要给result置为零
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt,conn);
        }
        return result;
    }

    @Override
    public Business getBusinessByNameBypass(Integer businessId, String password) {
        Business business=null;
        String sql="select * from business where businessId = ? and password = ?";
        try{
            conn= JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,businessId);
            pstmt.setString(2,password);
            rs= pstmt.executeQuery();
            while (rs.next()){
                business = new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return business;
    }

    @Override
    public Business getBusinessByBusinessId(Integer businessId) {

        Business business=null;
        String sql="select * from business where businessId = ?";
        try{
            conn= JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,businessId);
            rs= pstmt.executeQuery();
            while (rs.next()){
                business = new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setPassword(rs.getString("password"));
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStarPrice(rs.getDouble("starPrice"));
                business.setDeliveryPrice(rs.getDouble("deliveryPrice"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return business;
    }

    @Override

    public int updateBusiness(Business business) {
        int result = 0;
        String sql = "update business set businessName = ?,businessAddress =?,businessExplain=?,starPrice=?,deliveryPrice=? where businessId = ? ";
        try{
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, business.getBusinessName());
            pstmt.setString(2, business.getBusinessAddress());
            pstmt.setString(3, business.getBusinessExplain());
            pstmt.setDouble(4, business.getStarPrice());
            pstmt.setDouble(5, business.getDeliveryPrice());
            pstmt.setInt(6, business.getBusinessId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs, pstmt, conn);
        }
        return result;
    }

    @Override
    public int updateBusinessPassword(Business business) {
        int result = 0;
        String sql = "update business set password=? where businessId = ? ";
        try{
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, business.getPassword());
            pstmt.setInt(2, business.getBusinessId());
            result = pstmt.executeUpdate();
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs, pstmt, conn);
        }
        return result;
    }
}