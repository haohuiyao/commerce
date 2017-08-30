package com.meeno.framework.bean;

public class ProcessBarBean implements java.io.Serializable {

	/**
	 * 序列化.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 任务名称.
	 */
	String taskName;
	/**
	 * 总数量.
	 */
	long totalNum;
	/**
	 * 完成数量.
	 */
	long finishedNum;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public long getFinishedNum() {
		return finishedNum;
	}

	public void setFinishedNum(long finishedNum) {
		this.finishedNum = finishedNum;
	}
	public ProcessBarBean() {

	}

	public ProcessBarBean(String taskName, long totalNum, long finishedNum) {
		this.taskName = taskName;
		this.totalNum = totalNum;
		this.finishedNum = finishedNum;
	}

}
