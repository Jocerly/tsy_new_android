package com.tsy.tsy.config;


import com.tsy.tsy.BuildConfig;

/**
 * 项目配置类
 *
 * @author Jocerly
 */
public class URLConfig {

    /**
     * 是否在调试阶段
     */
    public final static boolean isDebug = BuildConfig.DEBUG;

    /**
     * api_host
     */
    public static String URL_HOST = BuildConfig.URL_HOST;
    /**
     * 注册登录host
     * t0-passport.taoshouyou.com:8083
     */
    public static final String LOGIN_URL_HOST = BuildConfig.URL_HOST;

    /**
     * 一键支付host
     */
    public static final String PAY_URL_HOST = BuildConfig.PAY_URL_HOST;

    /**
     * 淘手游图片host
     */
    public static final String URL_IMG_HOST = BuildConfig.URL_IMG_HOST;

    /**
     * H5 host
     */
    public static final String H5_HOST = BuildConfig.H5_HOST;
    /**
     * 激光推送标签定义
     */
    public static String OnTag = BuildConfig.OnTag;

    /**
     * 淘手游url协议
     * 例:taoshouyou://gameaccount?id=1
     */
    public static final String SCHEME_TAOSHOUYOU = "taoshouyou";

    /**
     * 成品号
     */
    public static final String HOST_GAME_ACCOUNT = "gameaccount";
    /**
     * 首充号
     */
    public static final String HOST_FIRST_CHARGE = "firstcharge";
    /**
     * 首充号续充
     */
    public static final String HOST_RECHARGE = "recharge";
    /**
     * 金币
     */
    public static final String HOST_GOLD = "gold";
    /**
     * 钻石
     */
    public static final String HOST_DIAMOND = "diamond";
    /**
     * 苹果代充
     */
    public static final String HOST_APPLE_BEHALF = "applebehalf";
    /**
     * 安卓代充
     */
    public static final String HOST_ANDROID_BEHALF = "androidbehalf";
    /**
     * 装备
     */
    public static final String HOST_EQUIPMENT = "equipment";

    /**
     * 首充送号
     */
    public static final String HOST_FREE_GIVE = "firstAccountFreeGive";

    /**
     * 根据用户安装的应用返回7个游戏
     */
    public static String GET_SEVEN_GAME = URL_HOST + "games/getsevengame";

    /**
     * 图片上传接口
     */
    public static String UPLOAD_PIC = URL_HOST + "uploadpic/savepic";

    /**
     * 头像upload
     */
    public static String URL_IMG_UPLOAD = URL_HOST + "personal/upload-save";

    /**
     * 登录
     */
    public static String URL_LOGIN = LOGIN_URL_HOST + "appuser/login";

    /**
     * 登录校验手机验证码
     */
    public static String URL_LOGIN_SMS_CODE = LOGIN_URL_HOST + "appuser/user-send-code";


    /**
     * 登录 获取图片验证码
     */
    public static String URL_LOGIN_PIC_CODE = LOGIN_URL_HOST + "appuser/captcha";

    /**
     * 获取用户信息
     */
    public static String URL_GET_USER_INFO = URL_HOST + "personal/getuserinfo";

    /**
     * 个人资料修改
     */
    public static String URL_PERSONAL_INFO_MODIFY = URL_HOST + "personal/personalinfomodify";

    /**
     * 个人实名认证
     */
    public static String URL_PERSONAL_AUTH = URL_HOST + "personal/personalauth";

    /**
     * 注册
     */
    public static String URL_REGIEST = LOGIN_URL_HOST + "appuser/register";

    /**
     * 注册短信验证码
     */
    public static String URL_REGIEST_SMS_CODE = LOGIN_URL_HOST + "sms/reg-code";

    /**
     * 发送短信验证码
     */
    public static String URL_SEND_VERIFY_CODE = URL_HOST + "siteauth/sendsms";

    /**
     * 发送手机验证码 （新）
     */
    public static String URL_SEND_VERIFY_CODE_NEW = LOGIN_URL_HOST + "sms/send-code";


    /**
     * 买家 卖家 订单列表
     */
    public static String URL_ORDER_LIST = URL_HOST + "tradelogs/getmygoods";

    /**
     * 商品下架
     */
    public static String URL_GOODS_OFF = URL_HOST + "trades/shelves";

    /**
     * 获取绑定银行卡支付宝信息
     */
    public static String URL_GET_BIND_CARD = URL_HOST + "personal/getbindcards";

    /**
     * 网银账户绑定
     */
    public static String URL_BIND_CARD = URL_HOST + "personal/bindcard";

    /**
     * 充值记录
     */
    public static String URL_RECHARGE_HIS = URL_HOST + "assets/retrechargerecor";

    /**
     * 提现记录
     */
    public static String URL_TAKE_HIS = URL_HOST + "assets/getpresentreccord";

    /**
     * 已发布商品列表
     */
    public static String URL_PUBLISH_GOODS_LIST = URL_HOST + "trades/getgoods";

    /**
     * 获取所有游戏列表
     */
    public static String GET_GAME_LIST = URL_HOST + "games/getallgames";

    public static String SEARCH_GAMES = URL_HOST + "games/searchgames";

    /**
     * banner和常态入口接口
     */
    public static String GET_BANNER_FUNS = URL_HOST + "index/getbannerandfuns";

    /**
     * 热门游戏列表
     */
    public static String HOT_GAMES = URL_HOST + "games/hotgames";

    /**
     * 性价比推荐商品列表
     */
    public static String RECOMMEND_GOODS = URL_HOST + "index/recommended";

    /**
     * 最新成交
     */
    public static String GET_NEW_DEALS = URL_HOST + "index/getnewdeals";

    public static String CHECK_QQ = URL_HOST + "index/checkqq";

    /**
     * 消息列表
     */
    public static String GET_MESSAGE_LIST = URL_HOST + "msg/getmessagelist";

    /**
     * 消息已读未读
     */
    public static String IS_READ = URL_HOST + "msg/isread";

    /**
     * 获取收藏列表
     */
    public static String GET_FAVORITE = URL_HOST + "trades/getfavorite";

    /**
     * 添加/取消收藏
     */
    public static String IS_COLLECTION = URL_HOST + "trades/iscollection";

    /**
     * 获取游戏客户端
     */
    public static String URL_GET_CLIENTS = URL_HOST + "games/getgameclients";

    /**
     * 获取商品列表处  筛选条件 客户端
     */
    public static String URL_GET_TRADE_LIST_CLIENTS = URL_HOST + "games/gettradeclients";

    /**
     * 编辑商品的获取详细信息
     */
    public static String URL_EDIT_TRADES_INFO = URL_HOST + "selltrades/edit-trades-info";

    /**
     * 获取游戏所属区服
     */
    public static String URL_GET_SERVICE = URL_HOST + "selltrades/getgameservices";

    /**
     * 获取商品列表处  筛选条件 区服
     */
    public static String URL_GET_TRADE_LIST_SERVICE = URL_HOST + "selltrades/gettradeservices";

    /**
     * 获取客服
     */
    public static String URL_GET_CUSTOMER_SERVICE = URL_HOST + "buy/getcustomerinfo";

    /**
     * 获取指定游戏的出售属性信息
     */
    public static String GEME_GOODS = URL_HOST + "games/gamegoods";

    /**
     * 搜索商品列表
     */
    public static String GET_PRODUCT_LIST = URL_HOST + "trades/gettradeslist";

    /**
     * 获取成品号游戏属性信息
     */
    public static String GET_ACCOUNT_ATTR = URL_HOST + "selltrades/accountattr";

    /**
     * 成品号商品信息发布
     */
    public static String SAVE_ACCOUNT = URL_HOST + "sell-trade-game-account/save";

    /**
     * 秒收发布保存
     */
    public static String SAVE_QUICK_CHARGE = URL_HOST + "sell-trade-quick-charge/save";

    /**
     * 游戏币商品信息发布
     */
    public static String SAVE_GAME_CURRENCY = URL_HOST + "sell-trade-game-currency/save";

    /**
     * 道具商品信息发布
     */
    public static String SAVE_PROP = URL_HOST + "sell-trade-prop/save";

    /**
     * 代充商品信息发布
     */
    public static String SAVE_BEHALF_CHARGE = URL_HOST + "sell-trade-behalf-recharge/save";

    /**
     * 根据用户所选商品出售类型/商品价格,获取用户所需的服务费
     */
    public static String GET_TRADE_SERVICE_FEE = URL_HOST + "selltrades/trade-service-fee";

    /**
     * 获取商详
     */
    public static String GET_PRODUCT_INFO = URL_HOST + "trades/tradesinfo";


    /**
     * 平台收费标准
     */
    public static String SERVICE_FEE_HELP_URL = "https://m.taoshouyou.com/help-417-345.html?app=1";

    /**
     * 帮助
     */
    public static String SERVICE_HELP_URL = "https://m.taoshouyou.com/help?app=1";

    /**
     * 买家手续费帮助
     */
    public static String SERVICE_BUYYER_FEE_HELP_URL = "https://m.taoshouyou.com/help-486-343.html?app=1";

    /**
     * 卖家手续费帮助
     */
    public static String SERVICE_SELLER_FEE_HELP_URL = " https://m.taoshouyou.com/help-417-345.html?app=1";

    /**
     * 购买指南帮助
     */
    public static String SERVICE_BUY_HELP_URL = "https://m.taoshouyou.com/help-358-344.html?app=1";

    /**
     * 发布指南帮助
     */
    public static String SERVICE_SELL_HELP_URL = " https://m.taoshouyou.com/help-400-345.html?app=1";


    /**
     * 成品号批价
     */
    public static String ACTION_INDEX = URL_HOST + "buy-trade-game-account/index";

    /**
     * 成品号下单
     */
    public static String ACTION_SAVE = URL_HOST + "buy-trade-game-account/save";

    /**
     * 首充号批价接口
     */
    public static String Action_FIRST_RECHARGE_INDEX = URL_HOST + "buy-trade-first-recharge/index";


    /**
     * 首充号需求所需字段
     */
    public static String ACTION_FIRST_ATTR = URL_HOST + "buy/select-first-attr";


    /**
     * 首充号下单
     */
    public static String ACTION_FIRST_RECHARGE_SAVE = URL_HOST + "buy-trade-first-recharge/save";

    /**
     * 代充批价接口
     */
    public static String ACTION_BEHALF_RECHARGE_INDEX = URL_HOST + "buy-trade-behalf-recharge/index";

    /**
     * 代充下单接口
     */
    public static String ACTION_BEHALF_RECHARGE_SAVE = URL_HOST + "buy-trade-behalf-recharge/save";

    /**
     * 游戏币批价接口
     */
    public static String ACTION_GAME_CURRENCY_INDEX = URL_HOST + "buy-trade-game-currency/index";

    /**
     * 游戏币下单接口
     */
    public static String ACTION_GAME_CURRENCY_SAVE = URL_HOST + "buy-trade-game-currency/save";

    /**
     * 装备道具批价接口
     */
    public static String ACTION_PROP_INDEX = URL_HOST + "buy-trade-prop/index";

    /**
     * 装备道具下单接口
     */
    public static String ACTION_PROP_SAVE = URL_HOST + "buy-trade-prop/save";

    /**
     * 续充资格验证
     */
    public static String CONTINUE_ACCOUNT = URL_HOST + "buy/continue-account";

    /**
     * 续充商品列表
     */
    public static String CONTINUE_RECHARE_LIST = URL_HOST + "trades/continue-to-recharge-list";

    /**
     * 续充批价
     */
    public static String CONTINUE_RECHARGE_INDEX = URL_HOST + "buy-trade-continue-to-recharge/index";

    /**
     * 续充下单
     */
    public static String CONTINUE_RECHARGE_SAVE = URL_HOST + "buy-trade-continue-to-recharge/save";

    /**
     * 通过游戏id获取商品类型
     */
    public static String GET_GOODSID_BY_GAMEID = URL_HOST + "games/gamegoods-by-gameid";

    /**
     * QQ登录
     */
    public static String QQ_LOGIN = LOGIN_URL_HOST + "qq/get-user-info";

    /**
     * QQ注册
     */
    public static String QQ_REGIEST = LOGIN_URL_HOST + "qq/register";

    /**
     * 微信注册
     */
    public static String WECHAT_REGIEST = LOGIN_URL_HOST + "wechat/register";

    /**
     * 手机邮箱绑定
     */
    public static String MOBILE_EMAIL_BIND = URL_HOST + "personal/update-mobile";

    /**
     * 密码找回-验证用户名
     */
    public static String FIND_PWD_AUTH_USERNAME = URL_HOST + "personal/mobile-by-username";

    /**
     * 密码找回-重置密码
     */
    public static String RESET_PWD = LOGIN_URL_HOST + "appuser/updatepwd";


    /**
     * 密码找回-重置密码 短信验证码
     */
    public static String RESET_PWD_SMS_CODE = LOGIN_URL_HOST + "sms/pwd-code";

    /**
     * 支付宝获取签名的payinfo
     */
    public static String ACTION_ALIPAY_INDEX = URL_HOST + "pay/index";

    /**
     * 密码修改
     */
    public static String MODIFY_PASSWORD = URL_HOST + "personal/password-modify";

    /**
     * 提现申请
     */
    public static String TAKE_MONEY_REQUEST = URL_HOST + "assets/accountdraw";

    /**
     * 图片上传
     */
    public static String FILE_UPLOADE = URL_HOST + "uploadpic/savepic";

    /**
     * 删除绑定银行卡
     */
    public static String DELETE_BIND_CARD = URL_HOST + "personal/delbindcards";

    /**
     * 修改绑定账户信息
     */
    public static String MODIFY_BIND_CARD = URL_HOST + "personal/updatebank";

    /**
     * 订单详情
     */
    public static String ORDER_DETAIL = URL_HOST + "tradelogs/orderdetails";

    /**
     * 订单支付
     */
    public static String PAY_TRADE = URL_HOST + "tradelogs/paytradelog";

    /**
     * 已买到宝贝 关闭交易
     */
    public static String CLOSE_TRADE_BUYER = URL_HOST + "tradelogs/closepro";

    /**
     * 已买到宝贝 关闭交易 理由
     */
    public static String CLOSE_TRADE_REASON = URL_HOST + "tradelogs/buyerclosereason";

    /**
     * 已买到  账号信息提取
     */
    public static String GET_BUYER_ACCOUT = URL_HOST + "tradelogs/get-buyer-account";

    /**
     * 生成证书
     */
    public static String CREATE_CERTPIC = URL_HOST + "tradelogs/certpic";

    /**
     * 确认收货
     */
    public static String CONFIRM_ORDER_RECEIVE = URL_HOST + "tradelogs/receiptpro";

    /**
     * 卖家 交易关闭
     */
    public static String CLOSE_TRADE_SELLER = URL_HOST + "trades/sellerclosepro";

    /**
     * 卖家 关闭交易理由
     */
    public static String CLOSE_TRADE_SELLER_REASON = URL_HOST + "trades/sellerclosereason";

    /**
     * 卖家 首冲 提取买家需求
     */
    public static String EXTRACT_BUYER_ATTRS = URL_HOST + "trades/extractfirstattr";

    /**
     * 卖家 续充提取需求
     */
    public static String EXTRACT_CONTINUE_ATTRS = URL_HOST + "trades/extractcontinueattr";

    /**
     * 卖家 发货
     */
    public static String SELLER_SHIP = URL_HOST + "trades/sellership";

    /**
     * 店铺详情
     */
    public static String SHOP_DETAIL = URL_HOST + "personal/myshop";

    /**
     * 店铺商品类型
     */
    public static String SHOP_GOODS_TYPES = URL_HOST + "personal/getgamegoods";

    /**
     * 秒收商品状态
     */
    public static String FAST_GOODS_STATES = URL_HOST + "trades/quickstatus";

    /**
     * 秒收商品列表
     */
    public static String FAST_GOODS_LIST = URL_HOST + "trades/trade-quick-charge";

    /**
     * 秒收商品详情
     */
    public static String FAST_GOODS_DETAIL = URL_HOST + "trades/quickchargeinfo";

    /**
     * 我发布商品 修改商品价格
     */
    public static String EDIT_GOODS_PRICE = URL_HOST + "trades/edittradesprice";

    /**
     * 财付通支付
     */
    public static String TENCENT_PAY = "https://m.taoshouyou.com/payment/pay";

    /**
     * 个人中心url
     */
    public static String PC_PERSONAL_CENTER_ADDRESS = "https://m.taoshouyou.com/user/assets/index";

    /**
     * 淘手游注册协议
     */
    public static String TSY_REGIEST_PROTOCL = "https://m.taoshouyou.com/help-468-348.html?app=1";

    /**
     * 约定交易 密码验证
     */
    public static String VERIFY_PROMISS = URL_HOST + "buy/verify-promise-password";

    /**
     * 验证用户是否有权限购买该商品
     */
    public static String VERIFY_ORDERS = URL_HOST + "buy/verify-orders";

    /**
     * 检查更新
     */
//    public static String CHECK_VERSION = URL_HOST + "webhelp/updatedversion";
    public static String CHECK_VERSION = URL_HOST + "app-version/checkversion";

    /**
     * 首冲 续冲商品详情
     */
    public static String GET_TRADEINFO_3_9 = URL_HOST + "trades/first-continue";


    /**
     * 代充提取买家需求
     */
    public static String EXTRACT_BEHALF_ATTR = URL_HOST + "trades/extractbehalfattr";

    /**
     * 是否打开财付通支付
     */
    public static String IS_OPEN_TENCENT_PAY = URL_HOST + "index/tenpay-interface";

    /**
     * 续充下单字段
     */
    public static String CONTINUE_RECHARGE_ATTRS = URL_HOST + "trades/write-continuetorecharge-demand";

    /**
     * 反馈消息列表
     */
    public static String FEED_BACK_MSG_LIST = URL_HOST + "msg/feedback";

    /**
     * 反馈信息保存
     */
    public static String FEED_BACK_MSG_SEND = URL_HOST + "msg/savefeedback";

    /**
     * 提现 妹子团微信展示
     */
    public static String GIRL_WECHAT = URL_HOST + "assets/girl-wechat";

    /**
     * gif广告位
     */
    public static String GIF_URL = URL_HOST + "index/pagead";

    /**
     * 商品列表页过滤条件
     */
    public static String SEARCH_FILTER = URL_HOST + "games/get-bindcertificate-by-gameid";

    /**
     * 神策统计页面访问
     */
    public static String STATISTICS_PAGE_VIEW = URL_HOST + "statistics/page-view";

    /**
     * 神策点击统计
     */
    public static String STATISTICS_CM_CLICK = URL_HOST + "statistics/cm-click";

    /**
     * 我的游戏
     */
    public static String URL_MY_GAME = URL_HOST + "mygames/index";

    /**
     * 退游返现
     */
    public static String URL_QUIT_GAME = URL_HOST + "mygames/quit";

    /**
     * 我的游戏 充值记录
     */
    public static String URL_MYGAME_RECHARGE_HIS = URL_HOST + "mygames/gamerechargeinfo";

    /**
     * 商品id搜索
     */
    public static String URL_GET_TRADE_GOODSID = URL_HOST + "trades/gettradegoodsid";

    /**
     * 在线客服h5
     * http://m.taoshouyou.com/serviceonline
     */
    public static String URL_SERVICE_ONLINE_H5 = "https://m.taoshouyou.com/serviceonline";

    /**
     * h5下单成功页
     */
    public static String URL_H5_TRADELOGS = "https://m.taoshouyou.com/user/tradelogs/?tradelogno=";

    /**
     * h5游戏中心
     * 9.taoshouyou.com
     */
    public static String URL_GAME_CENTER = "http://9.taoshouyou.com/help";

    /**
     * 登录验证手机
     */
    public static String URL_VERIFY_MOBILE = URL_HOST + "siteauth/check-sms";

    /**
     * 微信登录获取accesstoken
     */
    public static String URL_WECHAT_GET_ACCESS_TOKEN = LOGIN_URL_HOST + "wechat/get-access-token";

    /**
     * 微信获取信息
     */
    public static String URL_WECHAT_VERIFY = LOGIN_URL_HOST + "wechat/get-user-info";

    /**
     * 支付 充值
     */
    public static String URL_ONE_PAY = PAY_URL_HOST + "wap/pay";

    /**
     * 提现验证
     */
    public static String URL_ONE_PAY_TAKE_CHECK = PAY_URL_HOST + "apiV1/withdraw/funds-isnormal";

    /**
     * 是否有未读消息
     */
    public static String URL_IS_HAVE_UNREAD_MSG = URL_HOST + "msg/haveunread";

    /**
     * 修改用户名
     */
    public static String URL_MODIFY_USER_NAME = LOGIN_URL_HOST + "appuser/updateuname-app";

    /**
     * 客服评价
     */
    public static String URL_SERVICE_ASSESS = "https://m.taoshouyou.com/user/kfassess?app=1&tradelogno=";

    /**
     * 扫码登录
     */
    public static String URL_SCANNER_LOGIN = LOGIN_URL_HOST + "qrcode/login";

    /**
     * 首页游戏推荐
     */
    public static String URL_RECOMMEND_GAMES = URL_HOST + "index/recommend-games-list";

    /**
     * 首页店铺推荐
     */
    public static String URL_HOME_RECOMMEND_SHOPS = URL_HOST + "index/recommend-shops-list";

    /**
     * 首页最新商品
     */
    public static String URL_HOME_NEW_PUBLISH_GOODS = URL_HOST + "index/new-trades-list";

    /**
     * 微信朋友圈分享
     */
    public static String URL_WECHAT_SHARE_INDEX = URL_HOST + "wechat/index";

    /**
     * 微信分享确认
     */
    public static String URL_WECHAT_SHARE_CONFIRM = URL_HOST + "wechat/wechat-share";

    /**
     * 优惠券列表
     */
    public static String URL_COUPON_LIST = URL_HOST + "index/get-coupon-list";

    /**
     * 通知隐藏优惠券
     */
    public static String URL_COUPON_HIDE = URL_HOST + "index/set-hide-coupon";


    /**
     * 优惠券列表h5
     */
    public static String URL_COUPON_H5_LIST = "https://m.taoshouyou.com/user/coupon/index?app=1";

    /**
     * 首充送号游戏列表
     */
    public static String URL_FREE_GAME_LIST = URL_HOST + "free-game";
    /**
     * 保险保额
     */
    public static String URL_INSURANCE_AMOUNT_LIST = URL_HOST + "buy/getinsuranceinfo";

    /**
     * 保单列表
     */
    public static String URL_INSURANCE_LIST = URL_HOST + "insurance/list";

    /**
     * 获取保单表单
     */
    public static String URL_INSURANCE_FORM = URL_HOST + "insurance/index";

    /**
     * 理赔申诉保存
     */
    public static String URL_INSURANCE_SAVE = URL_HOST + "insurance/save";


    /**
     * 获取省市code
     */
    public static String URL_INSURANCE_CITY_CODE = URL_HOST + "insurance/getcitycode";

    /**
     * 理赔详情
     */
    public static String URL_INSURANCE_DETAIL = URL_HOST + "insurance/info";


    /**
     * 首充送号档位信息
     */
    public static String URL_FREE_GAME_ADD_INFO = URL_HOST + "free-game/addinfo";

    /**
     * 首充送号save
     */
    public static String URL_FREE_GAME_SAVE = URL_HOST + "free-game/save";


    /**
     * 商品详情页-商品议价条件判断
     */
    public static String URL_BARGAIN_CHECK_TRADE = URL_HOST + "bargain/check-trade";

    /**
     * 商品详情页-确定出价
     */
    public static String URL_BARGAIN_COMMIT_BARGAIN = URL_HOST + "bargain/commit-bargain";

    /**
     * 个人中心-买家操作状态
     */
    public static String URL_BARGAIN_BUYER_OPINION = URL_HOST + "bargain/buyer-opinion";

    /**
     *
     */
    public static String URL_BARGAIN_BUY_IS_INVALID = URL_HOST + "bargain/bargain-time";

    /**
     * 个人中心-卖家操作状态
     */
    public static String URL_BARGAIN_SELLER_OPINION = URL_HOST + "bargain/seller-opinion";

    /**
     * 我的出价列表
     */
    public static String URL_BARGAIN_BUYER_LIST = URL_HOST + "bargain/buyer-bargains";

    /**
     * 卖家收到的出家列表
     */
    public static String URL_BARGAIN_SELLER_LIST = URL_HOST + "bargain/seller-bargains";

    /**
     * 收到的出价，议价数量
     */
    public static String URL_BARGAIN_COUNT = URL_HOST + "bargain/bargain-count";

    /**
     * 余额支付短信验证接口
     */
    public static String URL_CHECK_VERIFY_CODE_FOR_BALANCE_PURCHASE = URL_HOST + "siteauth/check-balance-purchase-verify-code";

    /**
     *
     */
    public static String URL_BARGAIN_DETAIL = URL_HOST + "bargain/bargain-details";


    /**
     * 获取余额
     */
    public static String URL_GET_BALANCE = URL_HOST + "personal/get-available-money";


    /**
     * 冻结资金列表
     */
    public static String URL_GET_FROZEN_FUND_LIST = URL_HOST + "assets/freezingexact";


    /**
     * 搜索热词
     */
    public static String URL_SEARCH_HOT_GAMES = URL_HOST + "hot-search-game/get-six-hot-search-game";


    /**
     * 搜索自动补全
     */
    public static String URL_SEARCH_AUTO_COMPLETE = URL_HOST + "search/game/auto-complete";

    /**
     * 游戏搜索
     */
    public static String URL_SEARCH_GAME = URL_HOST + "search/game";


    /**
     * 免费领号列表
     */
    public static String URL_GET_FREE_GAMES = URL_HOST + "free-account-list";

    /**
     * 免费领号区服信息
     */
    public static String URL_GET_FREE_GAMES_AREA_AND_SERVER = URL_HOST + "free-account-demand/edit";

    /**
     * 免费领号资料提交
     */
    public static String URL_FREE_GAMES_COMMIT_INFO = URL_HOST + "free-account-demand/save";


    /**
     * 获取开局号
     */
    public static String URL_GET_GAME_INIT_ACCOUNT = URL_HOST + "free-account-list/obtain-open-account";

    /**
     * 首充送号朋友圈分享
     */
    public static String URL_FREE_GAMES_WECHAT_SHARE = URL_HOST + "wechat/unified-wechat-share";

    /**
     * 首充送号微信分享确认
     */
    public static String URL_FREE_GAMES_WECHAT_SHARE_CALLBACK = URL_HOST + "wechat/unified-wechat-share-callback";

    /**
     * 什么是首充送号帮助
     */
    public static String H5_FREE_GAMES_HELP = H5_HOST + "help/default/what-is-the-fcn";

    /**
     * 忘记密码获取图片验证码
     */
    public static String URL_FIND_PASSWORD_PIC_CODE = LOGIN_URL_HOST + "sms/captcha";

    /**
     * 验证手机号和用户名是否匹配接口
     */
    public static String URL_CHECH_USER_AND_MOBILE = URL_HOST + "siteauth/check-username-mobile";


    /**
     * 验证手机号获取验证码
     */
    public static String URL_CHECK_MOBILE_GET_MSG = URL_HOST + "siteauth/check-mobile-get-msg";

    /**
     * 认证接口
     */
    public static String URL_CHECK_MOBILE_AUTH = URL_HOST + "siteauth/check-mobile";

    /**
     * 扫码取消登录
     */
    public static String URL_QR_LOGIN_CANCEL = LOGIN_URL_HOST + "qrcode/cancel-login";


    /**
     * 店铺搜索
     */
    public static String URL_SEARCH_SHOP = URL_HOST + "shop/search";


    /**
     * 发布成品号获取推荐客服
     */
    public static String URL_PUBLISH_ACCOUNT_GET_CUSTOMER_SERVICE = URL_HOST + "sell-trade-game-account/get-recent-service";

    /**
     * 选择交易属性保存
     */
    public static String URL_SELECT_ATTRS_STEP_ONE = URL_HOST + "sell-trade-game-account/step1";


    /**
     * 订单列表单独购买保险
     */
    public static String URL_ORDER_LIST_BUY_INSURANCE = URL_HOST + "tradelogs/insurance";


    /**
     * 获取个性化搜索项
     */
    public static String URL_GET_SEARCH_ATTRS = URL_HOST + "trades/getattrs";


    /**
     * 微博登录
     */
    public static String URL_WEIBO_LOGIN = URL_HOST + "weibo/login";


    /*** --- 接口API ----*/
    public final class NetConfigt0 {
        /**
         * api_host
         */
        public static final String URL_HOST = "http://cdt0-openapi.taoshouyou.com/api/";

        /**
         * 注册登录host
         * t0-passport.taoshouyou.com:8083
         */
        public static final String LOGIN_URL_HOST = "http://cdt0-passport.taoshouyou.com/api/";

        /**
         * 一键支付host
         */
        public static final String PAY_URL_HOST = "http://cdt0-pay.taoshouyou.com/";

        /**
         * 淘手游图片host
         */
        public static final String URL_IMG_HOST = "http://img-test.taoshouyou.com";

        /**
         * H5 host
         */
        public static final String H5_HOST = "http://cdt0-webapp.taoshouyou.com/";
        public static final String OnTag = "tsy_test";
    }

    public final class NetConfigOnline {
        /**
         * api_host
         */
        public static final String URL_HOST = "http://app.taoshouyou.com/api/";

        /**
         * 注册登录host
         */
        public static final String LOGIN_URL_HOST = "http://passport.taoshouyou.com/api/";

        /**
         * 一键支付host
         */
        public static final String PAY_URL_HOST = "https://pay.taoshouyou.com/";

        /**
         * 淘手游图片host
         */
        public static final String URL_IMG_HOST = "http://img.taoshouyou.com";

        /**
         * H5 host
         */
        public static final String H5_HOST = "https://m.taoshouyou.com/";

        public static final String OnTag = "tsy_online";
    }
}
