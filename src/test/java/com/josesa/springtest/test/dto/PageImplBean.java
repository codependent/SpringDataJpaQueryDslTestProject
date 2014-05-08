package com.josesa.springtest.test.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageImplBean<T> extends PageImpl<T> {

	private static final long serialVersionUID = 304376897797821540L;
	private int number;
	private int size;
	private int totalPages;
	private int numberOfElements;
	private long totalElements;
	private boolean previousPage;
	private boolean firstPage;
	private boolean nextPage;
	private boolean lastPage;
	private List<T> content;
	private List<SortBean> sort;

	public PageImplBean() {
		super(new ArrayList<T>());
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}
	
	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public boolean isPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(boolean previousPage) {
		this.previousPage = previousPage;
	}

	public boolean isFirstPage() {
		return firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	public boolean isNextPage() {
		return nextPage;
	}

	public void setNextPage(boolean nextPage) {
		this.nextPage = nextPage;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Sort getSort() {
		if(sort==null){
			return null;
		}else{
			SortBean sortBean = sort.get(0);
			Sort sort = new Sort(sortBean.getDirection(), sortBean.getProperty());
			return sort;
		}
	}

	public void setSort(List<SortBean> sort) {
		this.sort = sort;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public PageImpl<T> pageImpl() {
        return new PageImpl<T>(getContent(), new PageRequest(getNumber(),
                getSize(), getSort()), getTotalElements());
    }
}
