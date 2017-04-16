package top.builbu.common.util.page;

import java.io.Serializable;

/**
 * @ClassName: Pagination
 * @Description: TODO 分页
 * @date 2016年3月7日 下午1:00:18
 * 
 */
public class Pagination implements Serializable {
	private static final long serialVersionUID = 1052319221780926400L;

	public static final int DEFAULT_PAGE_SIZE = 30;
	public static final int DEFAULT_CURRENT_SKIP = 1;

	private int pageSize = DEFAULT_PAGE_SIZE; // 每页显示记录数

	private int pageCurrent = DEFAULT_CURRENT_SKIP; // 当前页

	private int currentResult; // 当前记录起始索引

	private String orderField; //排序字段

	private String orderDirection; //排序方式

	public Pagination() {

	}

	public Pagination(int rows) {
		this(rows, DEFAULT_CURRENT_SKIP);
	}

	public Pagination(int pageSize, int pageCurrent) {
		this.pageSize = pageSize;
		this.pageCurrent = pageCurrent;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}



	public int getCurrentResult() {
		this.currentResult = (this.getPageCurrent() - 1) * this.getPageSize();
		return currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public int getPageCurrent() {
		if (pageCurrent <= 1) {
			return 1;
		}
		return pageCurrent;
	}

	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
		this.currentResult = (this.getPageCurrent() - 1) * this.getPageSize();
	}



	@Override
	public String toString() {
		return "Pagination [rows=" + this.pageSize + ", page="
				+ this.pageCurrent + ", currentResult=" + this.currentResult + ", orderBy=" + this.orderField + "]";
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

}
