package com.nfc.lyndon.businesscard.app;

public interface ApiService {

    /**
     * 获取验证码
     */
    String GET_CODE_URL = Constants.HOST + "/nfc_server/user/getVerifyCode";

    /**
     * 登录
     */
    String LOGIN_URL = Constants.HOST + "/nfc_server/user/login";

    /**
     * 上传头像
     */
    String UPLOAD_AVATAR = Constants.HOST + "/nfc_server/nfccard/uploadLogo";

    /**
     * 获取名片列表
     */
    String GET_CARD_LIST = Constants.HOST + "/nfc_server/nfccard/getBusinessCardListByUid";

    /**
     * 获取名片详情
     */
    String GET_CARD_DETAIL = Constants.HOST + "/nfc_server/nfccard/getBusinessCardById";

    /**
     * 获取自己名片详情
     */
    String GET_SELF_CARD_DETAIL = Constants.HOST + "/nfc_server/nfccard/getSelfBusinessCard";

    /**
     * 上传名片图片
     */
    String UPLOAD_BUSINESS_CARD = Constants.HOST + "/nfc_server/nfccard/uploadBusinessCard";

    /**
     * 创建名片
     */
    String CREATE_BUSINESS_CARD = Constants.HOST + "/nfc_server/nfccard/createBusinessCard";

    /**
     * 修改名片
     */
    String UPDATE_BUSINESS_CARD = Constants.HOST + "/nfc_server/nfccard/updateBusinessCard";

    /**
     * 删除名片
     */
    String DELETE_BUSINESS_CARD = Constants.HOST + "/nfc_server/nfccard/deleteCard";

}
