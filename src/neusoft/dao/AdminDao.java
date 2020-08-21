package neusoft.dao;
import neusoft.domain.Admin;

public interface AdminDao{
    public Admin getAdminByNameBypass(String adminName, String password);
}
