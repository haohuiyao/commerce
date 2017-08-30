package com.meeno.framework.tag;

import java.util.List;

public class CutPageBean implements java.io.Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 6440705834341043576L;
	private List<?> dataList;
	private int totalRows;
	private boolean isExport;
	public boolean isExport() {
		return isExport;
	}
	public void setExport(boolean isExport) {
		this.isExport = isExport;
	}
	private int pageSize=10;
	private int startIndex;
	private int currentPage;
	public int getTotalPage(){
		if(totalRows==0){
			return 0;
		}
		if(totalRows<=pageSize){
			return 1;
		}
		if(totalRows%pageSize==0){
			return totalRows/pageSize;
		}else{
			return totalRows/pageSize + 1;
		}
	}
	public List<?> getDataList() {
		return dataList;
	}
	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getCurrentPage() {
		/*int totalPages = this.getTotalPage();
		if(currentPage>totalPages){
			currentPage = totalPages;
		}
		if(currentPage==0){
			currentPage = 1;
		}*/
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
}
