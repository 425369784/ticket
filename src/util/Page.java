/**
 * All rights Reserved, Designed By Zhengyanbo
 * @Title:  Page.java
 * @Package com.hpe.online.util
 * @Description:    TODO
 * @author: Zhengyanbo
 * @date:   2018�?1�?9�? 下午4:21:21
 * @version V1.0
 */
package util;

import java.util.List;

/**
 * <p>Description: </p>
 * <p>TODO</p>
 * @author	Zhengyanbo
 * @time	2018�?1�?9�? 下午4:21:21
 * @version V1.0
 */
public class Page {
	/**
	 * 当前页码
	 */
	private int pageNum;
	/**
	 * 每页记录�?
	 */
	private int rowCount = 5;
	
	/**
	 * 总页�?
	 */
	private int pageCount;
	
	/**
	 * 数据
	 */
	private List list;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
}
