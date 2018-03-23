/**
 * 
 */
package cn.mybase.ssm.util.base;

import java.util.List;

import com.github.pagehelper.Page; 
/**
 * @author dongchuan
 * @date 2018年3月18日 下午1:02:46
 * @version 1.0
 */
public class BeanUtil {  
	  
    public static <T> PagedResult<T> toPagedResult(List<T> datas) {  
        PagedResult<T> result = new PagedResult<T>();  
        if (datas instanceof Page) {  
            Page<T> page = (Page<T>) datas;  
            result.setPageNo(page.getPageNum());  
            result.setPageSize(page.getPageSize());  
            result.setData(page.getResult());
            result.setRecordsTotal(page.getTotal());  
            result.setRecordsFiltered(page.getTotal());
            result.setPages(page.getPages());  
        }  
        else {  
            result.setPageNo(1);  
            result.setPageSize(datas.size());  
            result.setData(datas);  
            result.setRecordsTotal(datas.size());  
            result.setRecordsFiltered(datas.size());  
        }  
  
        return result;  
    }  
  
}  
