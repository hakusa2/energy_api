package com.energy.welfare.utils;

import java.util.HashMap;

public class Define {
    public static HashMap<String, String> AUTH_MAP = new HashMap<String, String>();
    public static final String CODE = "code";
    public static final String MESSAGE = "message";
    public static final String TOKEN = "token";

    public static final String ID_PWD_FAIL_1 = "존재하지 않는 아이디 입니다.";
    public static final String ID_PWD_FAIL_2 = "잘못된 비밀번호 입니다.";

    public static final String IMG_NOTICE_URL = "/file/notice/";
    public static final String IMG_PROMOTION_URL = "/file/promotion/";
    public static final String IMG_FAQ_URL = "/file/faq/";
    public static final String IMG_BANNER_URL = "/file/banner/";
    public static final String IMG_CONSTRUCTION_URL = "/file/construction/";

    public static final String BUSINESS_BTYPE_DATA_1 = "건물형 인프라구축 사업";
    public static final String BUSINESS_BTYPE_DATA_2 = "점포형 에너지비용절감 사업";
    public static final String BUSINESS_BTYPE_DATA_3 = "공동주택형 에너지서비스 사업";
    public static final String BUSINESS_BTYPE_DATA_4 = "단독주택형 에너지서비스 사업";

    public static final String BUSINESS_STATUS_DATA_1 = "신청완료";
    public static final String BUSINESS_STATUS_DATA_2 = "심사중";
    public static final String BUSINESS_STATUS_DATA_3 = "선정";
    public static final String BUSINESS_STATUS_DATA_4 = "미선정";

    public static final int SUCCESS_CODE = 0;
    public static final String SUCCESS_MESSAGE = "성공";

    public static final int SYSTEM_FAIL_CODE = 1001;
    public static final String SYSTEM_FAIL_MESSAGE = "SYSTEM Error";

    public static final int DATABASE_FAIL_CODE = 1002;
    public static final String DATABASE_FAIL_MESSAGE = "Database Error";

    public static final int FILE_FAIL_CODE = 1003;
    public static final String FILE_FAIL_MESSAGE = "File Error";

    public static final int NOTICE_CATEGORY_NOTI = 1;
    public static final int NOTICE_CATEGORY_BODO = 2;

    public static final String NOTICE_CATEGORY_1 = "공지사항";
    public static final String NOTICE_CATEGORY_2 = "보도자료";


    public static final int MYBATIS_EXECUTE_SUCCESS_CODE = 1;
}
