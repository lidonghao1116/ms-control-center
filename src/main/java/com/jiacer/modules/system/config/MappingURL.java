package com.jiacer.modules.system.config;


/**
 * @author hzp
 * @Description: 系统请求URL路径
 * @date 2016-5-23
 */
public interface MappingURL {


    public static final String SYSTEM_BASE_URL = "${adminPath}/system";


    //用户（学员）
    public static final String USER_URL = "/user";
    //业务请求--报名学员
    public static final String ORDERS_URL = "/orders";
    //业务请求--考试管理
    public static final String EXAMS_URL = "/exams";
    //业务请求--成绩管理

    //业务请求--课程管理
    public static final String COURSES_URL = "/courses";
    //业务请求--学校管理系统课程管理
    public static final String LEARNTYPE_URL = "/learnType";

    //业务请求--课程销售管理
    public static final String COURSES_PACKAGE_URL = "/coursesPackage";
    //业务请求--合作商管理
    public static final String PARTNERS_URL = "/partners";
    //业务请求--提成管理
    public static final String BROKERAGE_URL = "/brokerage";
    //业务请求--分校管理--课程管理
    public static final String COURSES_BASE_INFO_URL="/coursesBaseInfo";
    //业务请求--分校管理--发证机构
    public static final String AUTHORITY_NAME_URL="/authorityName";
    //业务请求--分校管理--鉴定等级
    public static final String  CFG_PARAMS_GRADE_URL="/cfgParamsOfGrade";
    //业务请求--分校管理--鉴定等级
    public static final String CFG_PARAMS_STATUS_URL="/cfgParamOfStatus";
    //业务请求--分校管理--考试形式
    public static final String CFG_PARAMS_EXAM_TYPE_URL="/cfgParamOfExamType";
    


    /************************学习总控平台***********************************/
    public static final String BASE_ADMIN_URL = "${adminPath}"; //系统基础地址

    public static final String BASE_ADMIN_INDEX_URL = "${adminPath}/index";//系统首页地址
    public static final String LOGIN_URL = "${adminPath}/login";//用户登陆地址
    public static final String MODIFY_PWD_URL = "${adminPath}/modifyPwd";//用户修改密码

    //公用方法请求基础地址--不参与拦截
    public static final String COMMON_BASE_URL = "${adminPath}/common";
    //获取验证码地址
    public static final String CAPTCHA_URL = "/getCaptcha";

    //导出type表示请求的类型 0：excel 1:txt ...
    public static final String EXPORT = "/export/{type}";
    //上传文件 type表示请求的类型 0：表示系统富文本初始化  1:txt 2:图片 3：视频  4：音频  5:excel 6.word 7:pdf
    public static final String UPLOAD_URL = "/upload/{type}";

    //错误请求地址
    public static final String ERROR_403_URL = "${adminPath}/error/403";//403错误地址
    public static final String ERROR_404_URL = "${adminPath}/error/404";//404错误地址
    public static final String ERROR_500_URL = "${adminPath}/error/500";//500错误地址

    //系统基础请求
    public static final String FORM_URL = "/form";//表单页面地址地址
    public static final String LIST_URL = "/list";//list页面地址
    public static final String ADD_URL = "/add";//新增方法地址
    public static final String MODIFY_URL = "/modify";//修改方法地址
    public static final String RESET_PWD_URL = "/resetPwd";//重置密码地址
    public static final String QUERY_URL = "/query";//分页查询地址
    public static final String DELETE_URL = "/delete";//删除方法地址
    public static final String APPROVE_URL = "/approve";//审核方法地址
    public static final String INFO_URL = "/info";//详细信息、个人信息等
    public static final String FLUSH_URL = "/flush/{type}";//刷新
    public static final String CHECK_URL = "/check";//判断是否存在
    public static final String ADD_COURSE_BASE_INFO="/addCourse";//新增基础课程信息页面
    public static final String SAVE_COURSE_BASE_INFO="/saveCourse";//新增基础课程信息保存
    public static final String UPDATE_COURSE_BASE_INFO="/updateCourse";//修改基础课程信息
    public static final String GET_COURSE_BASE_INFO="/getCourseBase";//获取单条课程信息
    
    //系统基础路径
    public static final String USER_BASE_URL = "${adminPath}/user";//业务请求--用户管理
    public static final String COURSE_BASE_URL = "${adminPath}/course";//业务请求--课程管理

    //业务请求--在线学堂--题库管理
    public static final String QUESTION_URL = "/question";

    //业务请求--分校管理--发证机构管理
    public static final String CERT_AUTHORITY_URL = "/certAuthority";

    //业务请求--在线学堂--订单管理
    public static final String ORDER_URL = "/order";

    //业务请求--在线学堂--红包清算
    public static final String RED_PACKET_URL = "/redPacket";

    //业务请求--分校管理--学习记录
    public static final String LEARN_URL = "/learnRecords";

    //业务请求--分校管理--培训管理
    public static final String APPLY_ORDER_URL = "/applyOrder";

    //业务请求--分校管理--课程管理
    public static final String SCHOOL_COURSE_URL = "/schoolCourse";

    //业务请求--分校管理--学校管理
    public static final String SCHOOLS_URL = "/schools";

    //业务请求--分校管理--证书管理
    public static final String CERTIFICATE = "/certificate";

    //缓存
    public static final String CACHE_URL = "/cache";
    //角色
    public static final String ROLE_URL = "/role";
    //字典参数
    public static final String PARAMS_URL = "/params";
    //菜单参数
    public static final String MENU_URL = "/menu";
    //权限参数
    public static final String PURVIEW_URL = "/purview";
    //业务排序
    public static final String SORT_URL = "/sort";
    //区域
    public static final String AREAS_URL = "/areas";

    public static final String AREAS_INIT_URL = "/init";


}
