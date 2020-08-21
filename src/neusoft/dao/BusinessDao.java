package neusoft.dao;

import neusoft.domain.Business;

import java.util.List;

public interface BusinessDao {
    //显示所有商家列表  可选输入businessName和businessAddress
    public List<Business> listBusiness(String businessName, String businessAddress);
    public int saveBusiness(String businessName);
    public int deleteBusiness(int businessId);
    public Business getBusinessByNameBypass(Integer businessId, String password);
    public Business getBusinessByBusinessId(Integer businessId);
    public int updateBusiness( Business business);
    public int updateBusinessPassword( Business business);
}
