package cn.dayuanzi.pojo;

import cn.dayuanzi.config.ExpInfo;
import cn.dayuanzi.config.GainExpConfig;
import cn.dayuanzi.config.GainLindouConfig;
import cn.dayuanzi.config.LindouInfo;

/**
 * 
 * @author : leihc
 * @date : 2015年6月7日
 * @version : 1.0
 */
public enum ThingsAdder {

	注册{
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getExpInfo()
		 */
		@Override
		public ExpInfo getExpInfo() {
			return GainExpConfig.getDatas().get(1);
		}
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getLindouInfo()
		 */
		@Override
		public LindouInfo getLindouInfo() {
			return GainLindouConfig.getDatas().get(1);
		}
		/* (non-Javadoc)
		 * @see cn.dayuanzi.pojo.ThingsAdder#getTaskId()
		 */
		@Override
		public int getTaskId() {
			
			return 1;
		}
	},社区认证{
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getExpInfo()
		 */
		@Override
		public ExpInfo getExpInfo() {
			return GainExpConfig.getDatas().get(2);
		}
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getLindouInfo()
		 */
		@Override
		public LindouInfo getLindouInfo() {
			return GainLindouConfig.getDatas().get(2);
		}
		@Override
		public int getTaskId() {
			
			return 2;
		}
		
	},资料完善{
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getExpInfo()
		 */
		@Override
		public ExpInfo getExpInfo() {
			return GainExpConfig.getDatas().get(3);
		}
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getLindouInfo()
		 */
		@Override
		public LindouInfo getLindouInfo() {
			return GainLindouConfig.getDatas().get(3);
		}
		@Override
		public int getTaskId() {
			
			return 3;
		}
	},每日签到{
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getExpInfo()
		 */
		@Override
		public ExpInfo getExpInfo() {
			return GainExpConfig.getDatas().get(4);
		}
		@Override
		public int getTaskId() {
			
			return 4;
		}
	},连续七日签到{
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getExpInfo()
		 */
		@Override
		public ExpInfo getExpInfo() {
			return GainExpConfig.getDatas().get(5);
		}
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getLindouInfo()
		 */
		@Override
		public LindouInfo getLindouInfo() {
			return GainLindouConfig.getDatas().get(4);
		}
		
		@Override
		public int getTaskId() {
			
			return 1;
		}
	},发话题{
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getExpInfo()
		 */
		@Override
		public ExpInfo getExpInfo() {
			return GainExpConfig.getDatas().get(6);
		}
		@Override
		public int getTaskId() {
			
			return 6;
		}
	},发邀约{
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getExpInfo()
		 */
		@Override
		public ExpInfo getExpInfo() {
			return GainExpConfig.getDatas().get(7);
		}
		@Override
		public int getTaskId() {
			
			return 7;
		}
	},发帮帮{
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getExpInfo()
		 */
		@Override
		public ExpInfo getExpInfo() {
			return GainExpConfig.getDatas().get(8);
		}
		@Override
		public int getTaskId() {
			
			return 8;
		}
	},意见被采纳{
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getExpInfo()
		 */
		@Override
		public ExpInfo getExpInfo() {
			return GainExpConfig.getDatas().get(9);
		}
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getLindouInfo()
		 */
		@Override
		public LindouInfo getLindouInfo() {
			return GainLindouConfig.getDatas().get(5);
		}
		@Override
		public int getTaskId() {
			
			return 9;
		}
	},帮帮评论{
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getExpInfo()
		 * 目前只有帮帮的一级评论可以获得经验
		 */
		@Override
		public ExpInfo getExpInfo() {
			return GainExpConfig.getDatas().get(10);
		}
		@Override
		public int getTaskId() {
			
			return 10;
		}
	},被感谢{
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getExpInfo()
		 */
		@Override
		public ExpInfo getExpInfo() {
			return GainExpConfig.getDatas().get(11);
		}
		@Override
		public int getTaskId() {
			
			return 11;
		}
	},邀请邻居{
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getExpInfo()
		 */
		@Override
		public ExpInfo getExpInfo() {
			return GainExpConfig.getDatas().get(12);
		}
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getLindouInfo()
		 */
		@Override
		public LindouInfo getLindouInfo() {
			return GainLindouConfig.getDatas().get(6);
		}
		@Override
		public int getTaskId() {
			
			return 12;
		}
	},邀请非邻居{
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getExpInfo()
		 */
		@Override
		public ExpInfo getExpInfo() {
			return GainExpConfig.getDatas().get(13);
		}
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getLindouInfo()
		 */
		@Override
		public LindouInfo getLindouInfo() {
			return GainLindouConfig.getDatas().get(7);
		}
		@Override
		public int getTaskId() {
			
			return 13;
		}
	},官方活动报名并签到{
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getExpInfo()
		 */
		@Override
		public ExpInfo getExpInfo() {
			return GainExpConfig.getDatas().get(14);
		}
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getLindouInfo()
		 */
		@Override
		public LindouInfo getLindouInfo() {
			return GainLindouConfig.getDatas().get(8);
		}
		@Override
		public int getTaskId() {
			
			return 14;
		}
	},介绍自己{
		@Override
		public ExpInfo getExpInfo() {
			return GainExpConfig.getDatas().get(15);
		}
		/**
		 * @see cn.dayuanzi.pojo.ThingsAdder#getLindouInfo()
		 */
		@Override
		public LindouInfo getLindouInfo() {
			return GainLindouConfig.getDatas().get(9);
		}
		@Override
		public int getTaskId() {
			
			return 5;
		}
	};
	
	
	public abstract ExpInfo getExpInfo();
	
	public int getTaskId(){
		return 0;
	}
	
	public LindouInfo getLindouInfo(){
		return null;
	}
}
