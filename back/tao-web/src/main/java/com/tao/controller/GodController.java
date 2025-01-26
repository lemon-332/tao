package com.tao.controller;

import com.tao.entity.po.God;
import com.tao.entity.query.GodQuery;
import com.tao.entity.vo.PaginationResultVo;
import com.tao.entity.vo.ResponseVo;
import com.tao.service.GodService;
import com.tao.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("god")
public class GodController extends ABaseController {
    @Resource
    private GodService godService;

    @RequestMapping("godList")
    public ResponseVo godList(String godName, String startTimeStart) {
        GodQuery godQuery = new GodQuery();
        godQuery.setGodName(godName);
        godQuery.setStartTimeStart(startTimeStart);

        PaginationResultVo<God> list = godService.findListByPage(godQuery);

        return getSuccessResponseVo(list);
    }

    @RequestMapping("godAdd")
    public ResponseVo godAdd(God god, String sellerId) {
        god.setGodId(StringUtils.random16());
        god.setSellerId(sellerId);
        god.setStartTime(new Date());
        god.setGodStar(0);
        godService.add(god);
        return getSuccessResponseVo(null);
    }
}
