package top.builbu.common.util.page;

import java.io.Serializable;
import java.util.List;


/**
 * @ClassName: PageVO
 * @Description: TODO 管理后台分页返回对象
 * @date 2016年3月7日 下午12:59:39
 * 
 * @param <T>
 */

public class PageVO<T> implements Serializable {
	
	private static final long serialVersionUID = -8269591232053080855L;
	private List<T> list;  //结果list
	private Pagination pagination;  // 处理分页组件
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	
}
