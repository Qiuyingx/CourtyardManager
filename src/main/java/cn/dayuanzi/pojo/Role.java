package cn.dayuanzi.pojo;

/**
 * 管理员角色
 * @author : leihc
 * @date : 2015年5月11日
 * @version : 1.0
 */
public enum Role {

	ROOT {
		@Override
		public String getDescription() {
			return "管理员";
		}
	},// 管理员
	COURTYARD_ROOT {
		@Override
		public String getDescription() {
			return "社区管理员";
		}
	}, // 社区管理员
	COURTYARD_CEO {
		@Override
		public String getDescription() {
			return "社区CEO";
		}
	};// 社区CEO
	
	public abstract String getDescription();
}
