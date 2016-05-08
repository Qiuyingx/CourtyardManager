package cn.dayuanzi.resp;

import java.util.List;

/**
 * 
 * @author : leihc
 * @date : 2015年5月19日
 * @version : 1.0
 */
public class DataResp extends Resp {

	

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
