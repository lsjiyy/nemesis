package com.lsjyy.nemesis.common.page;

import com.lsjyy.nemesis.common.utils.ServletUtils;
import com.lsjyy.nemesis.common.utils.StringUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @Author LsjYy
 * @DATE 2020-03-14 22:39
 * @Description:
 */
@Data
public class PageVO implements Serializable {
    private Integer pageNum;
    private Integer pageSize;


    public static PageVO getPageVO() {
        PageVO pageDomain = new PageVO();
        pageDomain.setPageNum(Integer.valueOf(ServletUtils.getParameter(PageConstants.PAGE_NUM)));
        pageDomain.setPageSize(Integer.valueOf(ServletUtils.getParameter(PageConstants.PAGE_SIZE)));
        return pageDomain;
    }
}
