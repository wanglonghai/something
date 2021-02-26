package com.secret.attendancesummary.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页工具类
 */
@Data
public class PageUtils<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 总记录数
	 */
	//("总记录数")
	private int totalCount;
	/**
	 * 每页记录数
	 */
	//("每页记录数")
	private int pageSize;
	/**
	 * 总页数
	 */
	//("总页数")
	private int totalPage;
	/**
	 * 当前页数
	 */
	//("当前页数")
	private int currPage;
	/**
	 * 列表数据
	 */
	//("列表数据")
	private List<T> list;
	
	/**
	 * 分页
	 * @param list        列表数据
	 * @param totalCount  总记录数
	 * @param pageSize    每页记录数
	 * @param currPage    当前页数
	 */
	public PageUtils(List<T> list, int totalCount, int pageSize, int currPage) {
		this.list = list;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currPage = currPage;
		this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
	}
	// 带限制的分页构造方法limitCount限制多少行，返回数据最大就到多少行，主要针对es 深度分页10000行限制
	public PageUtils(List<T> list, int totalCount, int pageSize, int currPage, int limitCount) {
		this.list = list;
		this.totalCount = totalCount;
		if(totalCount>limitCount){
			this.totalCount = limitCount;
		}
		this.pageSize = pageSize;
		this.currPage = currPage;
		this.totalPage = (int)Math.ceil((double)this.totalCount/pageSize);
	}
	/**
	 * 分页
	 */
	public PageUtils(IPage<T> page) {
		this.list = page.getRecords();
		this.totalCount = (int)page.getTotal();
		this.pageSize = (int)page.getSize();
		this.currPage = (int)page.getCurrent();
		this.totalPage = (int)page.getPages();
	}

	public PageUtils() {
		this.list = new ArrayList<>();
		this.totalCount = 0;
		this.pageSize = 0;
		this.currPage = 0;
		this.totalPage = 0;
	}

	public PageUtils(List<T> list) {
		this.list = list;
	}

}
