package com.inkyi.common.util;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	private int total;
	private int currentPage;
	private int pages;
	private int pernum;
	private int start;
	private int end;

	private int nextpage;
	private int previouspage;
	private List<? extends Object> recordList;
	public static int ROWS = 20;

	public Page() {
		super();
	}
	@SuppressWarnings("unchecked")
	public List<T> getRecordList() {
		return recordList == null ? new ArrayList<T>(0) : (List<T>) recordList;
	}

	public void setRecordList(List<T> recordList) {
		this.recordList = recordList;
	}
	public Page(int total, int currentPage) {
		this(total, currentPage, ROWS);
	}

	public Page(int total, int currentPage, int pernum) {
		super();
		this.total = total;
		this.currentPage = currentPage == 0 ? 1 : currentPage;
		this.pernum = pernum;
		this.pages = (int) Math.ceil((total + 0.0) / pernum);
		if(currentPage>=pages){
			this.currentPage = pages;
		}
		count();
		nextAndprevious(this.currentPage);
	}

	private void nextAndprevious(int currentPage) {
		nextpage = currentPage + 1;
		previouspage = currentPage - 1;
		if (!hasPreview())
			previouspage = currentPage;
		if (!hasNext())
			nextpage = currentPage;
	}

	public int getNextpage() {
		return nextpage;
	}

	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}

	public int getPreviouspage() {
		return previouspage;
	}

	public void setPreviouspage(int previouspage) {
		this.previouspage = previouspage;
	}

	private void count() {
		this.start = pernum * (currentPage - 1);
		this.end = pernum * (currentPage);
		if (this.end > total) {
			this.end = total;
		}
	}

	/**
	 * 当前页是否有上一页
	 * 
	 * @return
	 */
	public boolean hasPreview() {
		if (pages > 1 && currentPage > 1) {
			return true;
		}
		return false;
	}

	/**
	 * 当前页是否有下一页
	 * 
	 * @return
	 */
	public boolean hasNext() {
		if (pages > 1 && currentPage < pages) {
			return true;
		}
		return false;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPernum() {
		return pernum;
	}

	public void setPernum(int pernum) {
		this.pernum = pernum;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}