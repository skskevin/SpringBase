/**
 * 
 */
package cn.mybase.ssm.util.base;

import java.util.List;

/**
 * <p>Title: PageResult</p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author dongchuan
 * @date 2018年3月18日 下午1:27:29
 * @version 1.0
 */
public class PagedResult<T> extends BaseEntity {  
    
    /*serialVersionUID*/  
    private static final long serialVersionUID = 1L;  
  
    private List<T> data;//数据  
      
    private long pageNo;//当前页  
      
    private long pageSize;//条数  
      
    private long recordsTotal;//总条数  
    private long recordsFiltered;//总条数  
      
    private long pages;//总页面数目  
  
    
  
    /**
	 * 
	 */
	public PagedResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param data
	 * @param pageNo
	 * @param pageSize
	 * @param recordsTotal
	 * @param recordsFiltered
	 * @param pages
	 */
	public PagedResult(List<T> data, long pageNo, long pageSize, long recordsTotal, long recordsFiltered, long pages) {
		super();
		this.data = data;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.pages = pages;
	}

	public long getPageNo() {  
        return pageNo;  
    }  
  
    public void setPageNo(long pageNo) {  
        this.pageNo = pageNo;  
    }  
  
    public long getPageSize() {  
        return pageSize;  
    }  
  
    public void setPageSize(long pageSize) {  
        this.pageSize = pageSize;  
    }  

  
    public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public long getPages() {  
        return pages;  
    }  
  
    public void setPages(long pages) {  
        this.pages = pages;  
    }  
      
}
