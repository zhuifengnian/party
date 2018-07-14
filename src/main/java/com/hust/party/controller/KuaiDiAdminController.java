package com.hust.party.controller;

import com.hust.party.common.ReturnMessage;
import com.hust.party.pojo.KuaiDiExpress;
import com.hust.party.service.KuaidiAdminService;
import com.hust.party.util.QiNiuUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 快递管理员controller<br/>
 * fan 2018/6/16 19:21
 */
@Controller
@RequestMapping("/kuaidiamdin")
public class KuaiDiAdminController {
    @Autowired
    private KuaidiAdminService kuaidiAdminService;

    @RequestMapping(value = "/uploadFeedbackQrCode", method = RequestMethod.POST)
    @ApiOperation(value = "上传反馈二维码,成功时，返回图片的地址")
    @ResponseBody
    public ReturnMessage uploadFeedbackQrCode(MultipartFile file) {
        String contentType = file.getContentType();
        if(!contentType.contains("image")){
            return new ReturnMessage(201,"请确认您上传的是二维码图片");
        }
        String feedbackQrcodeImgUrl = QiNiuUtil.manageFile(file);
        kuaidiAdminService.uploadFeedbackQrCode(feedbackQrcodeImgUrl);
        return new ReturnMessage(200, feedbackQrcodeImgUrl);
    }
    @RequestMapping(value = "/getFeedbackQrCode", method = RequestMethod.GET)
    @ApiOperation(value = "获取反馈二维码图片地址")
    @ResponseBody
    public ReturnMessage getFeedbackQrCode() {
        String feedbackQrCodeImgUrl = kuaidiAdminService.getFeedbackQrCode();
        return new ReturnMessage(200, feedbackQrCodeImgUrl);
    }
}