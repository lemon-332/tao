package com.tao.controller;

import com.tao.entity.po.God;
import com.tao.entity.po.Seller;
import com.tao.entity.query.GodQuery;
import com.tao.entity.vo.PaginationResultVo;
import com.tao.entity.vo.ResponseVo;
import com.tao.exception.BusinessException;
import com.tao.service.GodService;
import com.tao.service.SellerService;
import com.tao.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("god")
public class GodController extends ABaseController {
    @Resource
    private GodService godService;

    @Resource
    private SellerService sellerService;


    @Value("${project.folder}")
    private String projectFolder;

    @RequestMapping("godList")
    public ResponseVo godList(String godNameFuzzy, String startTimeStart) {
        GodQuery godQuery = new GodQuery();
        godQuery.setGodNameFuzzy(godNameFuzzy);
        godQuery.setStartTimeStart(startTimeStart);

        PaginationResultVo<God> list = godService.findListByPage(godQuery);

        return getSuccessResponseVo(list);
    }

    @RequestMapping("godAdd")
    public ResponseVo godAdd(God god) {
        god.setGodId(StringUtils.random16());
        god.setSellerId(god.getSellerId());
        god.setStartTime(new Date());
        god.setGodStar(0);
        Seller seller = sellerService.getSellerBySellerId(god.getSellerId());
        if (seller.getGodIds() == null) {
            seller.setGodIds(god.getGodId());
        } else {
            seller.setGodIds(god.getGodId() + "," + seller.getGodIds());
        }
        sellerService.updateSellerBySellerId(seller, seller.getSellerId());
        godService.add(god);
        return getSuccessResponseVo(null);
    }

    @RequestMapping("/godUpload")
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

    @RequestMapping("/godDownload")
    public void fileDownLoad(HttpServletResponse response, @RequestParam("fileName") String fileName) {
        File file = new File(projectFolder + '/' + fileName);
        if (!file.exists()) {
            throw new BusinessException("文件不存在");
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(file.toPath()))) {
            byte[] buff = new byte[1024];
            OutputStream os = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            throw new BusinessException("下载失败：" + e.getMessage());
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
    public ResponseVo godUpdate(God god) {
        godService.updateGodByGodIdAndSellerId(god, god.getGodId(), god.getSellerId());
        return getSuccessResponseVo(null);
    }

    @RequestMapping("godReport")
    public ResponseVo godReport() {
        List<God> godList = godService.findListByQuery(new GodQuery());
        Map<Integer, List<Integer>> map = godService.statisticCount(godList);
        return getSuccessResponseVo(map);
    }

    @RequestMapping("godListByIds")
    public ResponseVo godListByIds(String godIds) {
        List<God> gods = godService.getGodsByGodIds(godIds);
        return getSuccessResponseVo(gods);
    }

}
