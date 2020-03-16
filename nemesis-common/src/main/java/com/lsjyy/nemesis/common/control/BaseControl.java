package com.lsjyy.nemesis.common.control;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsjyy.nemesis.common.page.PageVO;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @Author LsjYy
 * @DATE 2020-03-14 22:43
 * @Description:
 */
@Slf4j
public class BaseControl {

    /**
     * 设置请求分页数据
     */
    protected Page startPage() {
        PageVO vo = PageVO.getPageVO();
        Integer pageNum = vo.getPageNum();
        Integer pageSize = vo.getPageSize();
        if (!Objects.isNull(pageNum) && !Objects.isNull(pageSize)) {
            Page page = PageHelper.startPage(pageNum, pageSize,true,false,null);
            return page;
        }
        return null;
    }
}
