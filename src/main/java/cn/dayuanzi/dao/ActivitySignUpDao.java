/**
 * 
 */
package cn.dayuanzi.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;



import cn.dayuanzi.model.ActivitySignUp;


/** 
 * @ClassName: ActivitySignUpDao 
 * @Description: TODO
 * @author qiuyingxiang
 * @date 2015年5月28日 下午3:40:37 
 *  
 */
@Repository
public class ActivitySignUpDao extends BaseDao<Long, ActivitySignUp>{

	
	/**
	 * 判断该用户是否参加该活动
	 */
	public boolean getUserjoin (long userId,long activityId){
		return  this.findUnique(Restrictions.eq("user_id", userId),Restrictions.eq("activity_id", activityId))!=null;
	}
}
