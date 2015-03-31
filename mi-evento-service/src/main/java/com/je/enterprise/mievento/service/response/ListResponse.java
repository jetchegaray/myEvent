package com.je.enterprise.mievento.service.response;

import java.util.List;

public class ListResponse<F> {

	private Integer limit;
	private Long totalCount;
	private List<F> data;
	
	public ListResponse() {
	}

	public ListResponse(Integer limit, Long totalCount, List<F> data) {
		this.limit = limit;
		this.totalCount = totalCount;
		this.data = data;
	}
	
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public List<F> getData() {
		return data;
	}
	public void setData(List<F> data) {
		this.data = data;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
}
