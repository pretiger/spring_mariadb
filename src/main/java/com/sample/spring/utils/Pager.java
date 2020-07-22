package com.sample.spring.utils;

public class Pager {
	private static final int PAGE_SCALE=10;
	private static final int BLOCK_SCALE=10;
	private int currentPage;
	private int previousPage;
	private int nextPage;
	private int totalPage;
	private int currentBlock;
	private int previousBlock;
	private int nextBlock;
	private int totalBlock;
	private int pageStart;
	private int pageEnd;
	private int blockStart;
	private int blockEnd;
	
	public Pager(int count, int currentPage) {
		this.currentPage=currentPage;
		setTotalPage(count);
		setPageRange();
		setTotalBlock();
		setBlockRange();
	}
	public void setTotalPage(int count) {
		totalPage = (int)Math.ceil(count*1.0/PAGE_SCALE);
	}
	public void setPageRange() {
		pageStart=(currentPage - 1) * PAGE_SCALE + 1;
		pageEnd=pageStart + (PAGE_SCALE - 1);
	}
	public void setTotalBlock() {
		totalBlock = (int)Math.ceil(totalPage*1.0/BLOCK_SCALE);
	}
	public void setBlockRange() {
		currentBlock=(int)((currentPage - 1) / BLOCK_SCALE) + 1;
		blockStart=(currentBlock - 1) * BLOCK_SCALE + 1;
		blockEnd=blockStart + (BLOCK_SCALE - 1);
		if(blockEnd > totalPage) {
			blockEnd = totalPage;
		}
		previousBlock = currentBlock==1 ? 1 : (currentBlock - 1) * BLOCK_SCALE;
		nextBlock = currentBlock > totalBlock ? (currentBlock * BLOCK_SCALE) : (currentBlock * BLOCK_SCALE) + 1;
		if(nextBlock >= totalPage) {
			nextBlock = totalPage;
		}
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getTotalPage() {
		return totalPage;
	}

	public int getCurrentBlock() {
		return currentBlock;
	}
	public void setCurrentBlock(int currentBlock) {
		this.currentBlock = currentBlock;
	}
	public int getPreviousBlock() {
		return previousBlock;
	}
	public void setPreviousBlock(int previousBlock) {
		this.previousBlock = previousBlock;
	}
	public int getNextBlock() {
		return nextBlock;
	}
	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}
	public int getTotalBlock() {
		return totalBlock;
	}

	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	public int getPageEnd() {
		return pageEnd;
	}
	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}
	public int getBlockStart() {
		return blockStart;
	}
	public void setBlockStart(int blockStart) {
		this.blockStart = blockStart;
	}
	public int getBlockEnd() {
		return blockEnd;
	}
	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}
	public static int getPageScale() {
		return PAGE_SCALE;
	}
	public static int getBlockScale() {
		return BLOCK_SCALE;
	}
	
	
}
