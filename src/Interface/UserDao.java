package Interface;

import java.sql.Connection;

/**
 * �û����ܽӿ�
 */
public interface UserDao {
	/**
     * �����û���¼����
     * 
     * @param username �û���
     * @param password ����
     * @return ��¼�Ƿ�ɹ�
     */
	public abstract boolean login(String username,String password);
	
	/**
     * �����û�ע�Ṧ��
     *
     * @param user ��ע����û���Ϣ
     */
	public abstract void regist(User user);
	/**
	 * �����Ƿ��û����ظ�
	 * @param username
	 * @return
	 */
	public abstract boolean check(String username);
	/**
	 * �����޸����빦��
	 * 
	 * @param user �����µ��û���Ϣ
	 * @return
	 */
	boolean update(User user) throws Exception;

	public abstract void insert(Connection co);

}
