package com.tao.controller;

import com.tao.entity.po.God;
import com.tao.entity.query.GodQuery;
import com.tao.entity.vo.PaginationResultVo;
import com.tao.entity.vo.ResponseVo;
import com.tao.service.GodService;
import com.tao.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;

@RestController
@RequestMapping("god")
public class GodController extends ABaseController {
    @Resource
    private GodService godService;


    @Value("${project.folder}")
    private String projectFolder;

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

    @RequestMapping("/upload")
    public ResponseVo httpUpload(@RequestParam("files") MultipartFile[] files) {
        try {
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue;
                }
                String fileName = file.getOriginalFilename();

                File dest = new File(projectFolder + File.separator + fileName);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }

                file.transferTo(dest);
            }
            return getSuccessResponseVo("Files uploaded successfully");
        } catch (Exception e) {
            return getSuccessResponseVo("File upload failed: " + e.getMessage());
        }
    }

    @RequestMapping("godDelete")
    public ResponseVo godDelete(String godId, String sellerId) {
        God god = godService.getGodByGodIdAndSellerId(godId, sellerId);
        if (god == null) {
            throw new RuntimeException("商品不存在");
        }
        godService.deleteGodByGodIdAndSellerId(godId, sellerId);
        return getSuccessResponseVo(null);
    }

    @RequestMapping("godUpdate")
    public ResponseVo godUpdate(God god, String sellerId, String godId) {
        godService.updateGodByGodIdAndSellerId(god, godId, sellerId);
        return getSuccessResponseVo(null);
    }
}
