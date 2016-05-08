package cn.dayuanzi.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.dayuanzi.model.Notice;

/**
 * 
 * @author : leihc
 * @date : 2015年4月22日 下午5:53:14
 * @version : 1.0
 */
@Repository
public class NoticeDao extends BaseDao<Long, Notice> {


	/**
	 * 根据通知类型和院子ID查询通知列表
	 */
	public List<Notice> findNotices(int notice_type, long yard_id){
		// 不是系统通知还跟那个院子有关
		if(notice_type == 0){
			return this.find(Restrictions.eq("notice_type", notice_type));
		}else{
			return this.find(Restrictions.eq("notice_type", notice_type),Restrictions.eq("courtyard_id", yard_id));
		}
	}
	
	/**
	 * 未读通知条数
	 * 
	 * @param yard_id
	 * @param last_readed_notice
	 * @return
	 */
	public long countNotReadNotice(long yard_id, long last_readed_notice){
		// where id>last_readed_notice and (notice_type=0 or yard_id=yard_id)
		Criterion lastReaded = Restrictions.gt("id", last_readed_notice);
		
		Criterion sysNotice = Restrictions.eq("notice_type", 0);
		Criterion sameYard = Restrictions.eq("courtyard_id", yard_id);
		Criterion or = Restrictions.or(sysNotice, sameYard);
		
		return this.count(lastReaded, or);
	}
	
	
}
