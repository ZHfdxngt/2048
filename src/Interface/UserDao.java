package Interface;

import java.sql.Connection;

/**
 * 用户功能接口
 */
public interface UserDao {
	/**
     * 这是用户登录功能
     * 
     * @param username 用户名
     * @param password 密码
     * @return 登录是否成功
     */
	public abstract boolean login(String username,String password);
	
	/**
     * 这是用户注册功能
     *
     * @param user 被注册的用户信息
     */
	public abstract void regist(User user);
	/**
	 * 查找是否用户名重复
	 * @param username
	 * @return
	 */
	public abstract boolean check(String username);
	/**
	 * 这是修改密码功能
	 * 
	 * @param user 被更新的用户信息
	 * @return
	 */
	boolean update(User user) throws Exception;

	public abstract void insert(Connection co);

}
