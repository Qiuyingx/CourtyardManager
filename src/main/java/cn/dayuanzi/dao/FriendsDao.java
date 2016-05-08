package cn.dayuanzi.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.dayuanzi.model.Friends;

/**
 * 
 * @author : leihc
 * @date : 2015年4月22日 下午9:11:42
 * @version : 1.0
 */
@Repository
public class FriendsDao extends BaseDao<Long, Friends> {

	/**
	 * 查找好友
	 * 
	 * @param usre_id
	 * @param courtyard_id
	 * @return
	 */
	public List<Friends> findFriends(long user_id, long courtyard_id){
		return this.find(Restrictions.eq("courtyard_id", courtyard_id),Restrictions.eq("user_id", user_id),Restrictions.eq("accept", 1));
	}
	
	/**
	 * 统计未读的好友请求条数
	 * 
	 * @param courtyard_id
	 * @param user_id
	 * @param last_readed_request
	 * @return
	 */
	public long countNotReadFriendRequest(long courtyard_id, long user_id, long last_readed_request){
		return this.count(Restrictions.eq("courtyard_id", courtyard_id),Restrictions.eq("user_id", user_id),Restrictions.eq("accept", 0),Restrictions.gt("id", last_readed_request));
	}
}
