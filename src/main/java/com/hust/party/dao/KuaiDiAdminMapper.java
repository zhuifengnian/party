package com.hust.party.dao;

import com.hust.party.pojo.KuaiDiAdmin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KuaiDiAdminMapper extends  BaseMapper<KuaiDiAdmin>   {

    /**
     * 判断用户是否是管理员
     */
    List<KuaiDiAdmin> selectByUid(Integer uid);

    /**
     * 上传反馈二维码
     * @param feedbackQrcodeImgUrl
     */
    void uploadFeedbackQrCode(String feedbackQrcodeImgUrl);

    /**
     * 获取反馈二维码
     */
    String getFeedbackQrCode();
}