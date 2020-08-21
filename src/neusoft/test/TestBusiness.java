package neusoft.test;

import neusoft.dao.Impl.BusinessDaoImpl;

public class TestBusiness {
    public static void main(String[] args) {
        BusinessDaoImpl dao = new BusinessDaoImpl();
        dao.listBusiness("饺子",null);
        dao.saveBusiness("喜家德");
        dao.deleteBusiness(10008);
    }
}

