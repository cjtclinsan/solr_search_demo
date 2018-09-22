/** 
 * Copyright(C) 2010-2011 xmall WuXi LTD. All Rights Reserved.                               
 * Project: <FileUpload>
 * Module ID: <0001>
 * Comments: <类描述>                          
 * JDK version used: <JDK1.6.0_25>                              
 * Author: baieqiuzhu        
 * Create Date：Sep 26, 2013 5:37:23 PM
 * Modified By: baieqiuzhu                                
 * Modified Date: Sep 26, 2013 5:37:23 PM                                   
 * Why & What is modified: <修改原因描述>    
 * Version: V1.0                  
 */

package com.cableex.core.util.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: Page
 * @Description: Page工具类
 * @author baeqiuzhu
 * @date Oct 7, 2013 11:07:19 AM
 * 
 */

public class Page implements Serializable {
	public final static String ORDER_DIRECTION_ASC = "ASC";
	public final static String ORDER_DIRECTION_DESC = "DESC";

	/**
	 * 默认每页记录数
	 */
	public static final int DEFAULT_PAGE_SIZE = 15;

	/**
	 * 原始页码
	 */
	private int plainPageNum = 1;

	/**
	 * 当前页码
	 */
	protected int pageNum = 1;
	protected int numPerPage = DEFAULT_PAGE_SIZE;

	// 默认按照id倒序排列
	private String orderField = "";
	private String orderDirection = "";

	/**
	 * 总页数
	 */
	private int totalPage = 1;

	/**
	 * 前一页
	 */
	private int prePage = 1;

	/**
	 * 下一页
	 */
	private int nextPage = 1;
	
	/**
	 * 最后一页
	 */
	private int lastPage = 1;

	/**
	 * 总记录数
	 */
	protected long totalCount = 0;
	
	/**
	 * 分页panel显示的页码数字，默认最多显示五条
	 */
	private List<PagePanelNum> pagePanelList;
	
	/**
	 * 分页url
	 */
	private String pageUrl;
	
	/**
	 * 其它查询条件
	 */
	private String param;

	/**
	 * 返回 pageNum 的值
	 * 
	 * @return pageNum
	 */
	public int getPageNum() {
		if (pageNum > totalPage) {
			pageNum = totalPage;
		}
		return pageNum;
	}

	/**
	 * 设置 pageNum 的值
	 * 
	 * @param pageNum
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum > 0 ? pageNum : 1;
		this.plainPageNum = this.pageNum;
	}

	/**
	 * 返回 numPerPage 的值
	 * 
	 * @return numPerPage
	 */
	public int getNumPerPage() {
		return numPerPage;
	}

	/**
	 * 设置 numPerPage 的值
	 * 
	 * @param numPerPage
	 */
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage > 0 ? numPerPage : 10;
	}

	/**
	 * 返回 orderField 的值
	 * 
	 * @return orderField
	 */
	public String getOrderField() {
		return orderField;
	}

	/**
	 * 设置 orderField 的值
	 * 
	 * @param orderField
	 */
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	/**
	 * 返回 orderDirection 的值
	 * 
	 * @return orderDirection
	 */
	public String getOrderDirection() {
		return orderDirection;
	}

	/**
	 * 设置 orderDirection 的值
	 * 
	 * @param orderDirection
	 */
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	/**
	 * 返回 totalPage 的值
	 * 
	 * @return totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * 设置 totalPage 的值
	 * 
	 * @param totalPage
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * 返回 prePage 的值
	 * 
	 * @return prePage
	 */
	public int getPrePage() {
		prePage = pageNum - 1;
		if (prePage < 1) {
			prePage = 1;
		}
		return prePage;
	}

	/**
	 * 设置 prePage 的值
	 * 
	 * @param prePage
	 */
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	/**
	 * 返回 nextPage 的值
	 * 
	 * @return nextPage
	 */
	public int getNextPage() {
		nextPage = pageNum + 1;
		if (nextPage > totalPage) {
			nextPage = totalPage;
		}

		return nextPage;
	}

	/**
	 * 设置 nextPage 的值
	 * 
	 * @param nextPage
	 */
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getLastPage() {
		this.lastPage = this.totalPage;
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	/**
	 * 返回 totalCount 的值
	 * 
	 * @return totalCount
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置 totalCount 的值
	 * 
	 * @param totalCount
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		totalPage = (int) (totalCount - 1) / this.numPerPage + 1;
	}

	/**
	 * 返回 plainPageNum 的值
	 * 
	 * @return plainPageNum
	 */
	public int getPlainPageNum() {
		return plainPageNum;
	}

	/**
	 * 设置 plainPageNum 的值
	 * 
	 * @param plainPageNum
	 */
	public void setPlainPageNum(int plainPageNum) {
		this.plainPageNum = plainPageNum;
	}

	public int getBegin() {
		return (pageNum - 1) * numPerPage;
	}

	public int getEnd() {
		return (pageNum * numPerPage > Integer.parseInt(String.valueOf(totalCount)))? Integer.parseInt(String.valueOf(totalCount)):pageNum * numPerPage;
	}

	public List<PagePanelNum> getPagePanelList() {
		pagePanelList = new ArrayList<PagePanelNum>();
		if(totalPage > 0 && totalPage <= 5) {
			for(int i = 0; i < totalPage; i++){
				PagePanelNum panelNum = new PagePanelNum(i+1);
				pagePanelList.add(panelNum);
			}
		} else if(totalPage > 5 && pageNum + 5 <= totalPage && pageNum >=3){
			pagePanelList.add(new PagePanelNum(pageNum-2));
			pagePanelList.add(new PagePanelNum(pageNum-1));
			pagePanelList.add(new PagePanelNum(pageNum));
			pagePanelList.add(new PagePanelNum(pageNum+1));
			pagePanelList.add(new PagePanelNum(pageNum+2));
		} else if(totalPage > 5 && pageNum + 5 > totalPage){
			pagePanelList.add(new PagePanelNum(totalPage-4));
			pagePanelList.add(new PagePanelNum(totalPage-3));
			pagePanelList.add(new PagePanelNum(totalPage-2));
			pagePanelList.add(new PagePanelNum(totalPage-1));
			pagePanelList.add(new PagePanelNum(totalPage));
		} else if(totalPage > 5 && pageNum + 5 <= totalPage && pageNum < 3){
			pagePanelList.add(new PagePanelNum(1));
			pagePanelList.add(new PagePanelNum(2));
			pagePanelList.add(new PagePanelNum(3));
			pagePanelList.add(new PagePanelNum(4));
			pagePanelList.add(new PagePanelNum(5));
		} 
		return pagePanelList;
	}

	public void setPagePanelList(List<PagePanelNum> pagePanelList) {
		this.pagePanelList = pagePanelList;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
}
