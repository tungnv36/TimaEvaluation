package vn.tima.timainspection.Util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by DinhAnh on 11/7/2016.
 */

public class Constant {

    public final static int KEY_VAYTINCHAP = 1;
    public final static int KEY_VAYDANGKYXE = 2;
    public final static int KEY_VAYHOKHAU = 4;
    public final static int MAX_MONEY_TINCHAP = 10;

    public static final int ITEM_HEADER_RECYCLERVIEW = 0;
    public static final int ITEM_RECYCLERVIEW = 1;

    public static final int THRESHHOLD_ROUND = 1000000;

    public static final int TYPE_MSG_PDF = 1;
    public static final int TYPE_MSG_TEXT = 0;
    public static final int STATUS_MSG_READ = 1;
    public static final int STATUS_MSG_UNREAD = 0;
    public static final int MENU_TU_VAN_VIEN_DI_LAY_HO_SO = 1;
    public static final int MENU_QLKV_CHO_DI_LAY_HO_SO = 1;
    public static final int MENU_QLKV_CHO_DI_KY_HOP_DONG = 2;
    public static final int MENU_QLKV_XU_LY = 3;
    public static final int MENU_HO_SO_DAI_LY = 1;
    public static final int MENU_DAI_LY_HO_SO_BO_PHAN_KHAC = 2;
    public static final int MENU_TU_VAN_VIEN_DI_KY_HOP_DONG = 2;
    public static final int MENU_DL_TIM_KIEM_KHACH_HANG = 3;
    public static final int MENU_DL_THONG_TIN_VON = 4;
    public static final int MENU_DL_LICH_SU_RUT_TIEN = 5;
    public static final int MENU_DL_HD_NO_XAU = 6;
    public static final int MENU_DL_HD_CAM_CO = 7;
    public static final int MENU_HO_SO_HEN_GIO = 3;
    public static final int MENU_LAY_HO_SO_HUY = 4;
    public static final int MENU_LAY_NOTIFY = 5;
    public static final int MENU_TINH_LAI_CHO_TVV = 6;
    public static final int MENU_GET_DS_KH_SP_CHUYEN = 9;
    public static final int MENU_DK_VAY = 7;
    public static final int MENU_BC_CONG_VIEC_TVV = 8;
    public static final int MENU_KINH_DOANH_CA_THE = 8;
    public static final int SPLASH_SCREEN_DISPLAY_TIME = 1000;
    public static final int SLIDING_MENU_HEADER = 0;
    public static final int SLIDING_MENU_ITEM = 1;

    public static final int TYPE_ID_KHOAN_VAY_TIN_CHAP_THEO_LUONG = 1;
    public static final int TYPE_ID_KHOAN_VAY_THEO_DANG_KY_XE = 2;
    public static final int TYPE_ID_KHOAN_VAY_THEO_DANG_KY_XE_K_CHINH_CHU = 5;
    public static final int TYPE_ID_KHOAN_VAY_THEO_OTO = 6;
    public static final int TYPE_ID_KHOAN_VAY_THEO_OTO_2 = 8;
    public static final int TYPE_ID_KHOAN_VAY_THEO_NHA_DAT = 7;
    public static final int TYPE_ID_KINH_DOANH_CA_THE = 3;
    public static final int TYPE_ID_KHOAN_VAY_THEO_HO_KHAU = 4;
    public static final int STATUS_KHOAN_VAY_SP_TRUC_TIEP_DAY = 9;
    public static final int STATUS_CHO_TU_VAN_VIEN_DI_LAY_HO_SO = 10;
    public static final int STATUS_HO_SO_BI_HUY = -10;
    public static final int STATUS_HO_SO_BI_HUY_N_11 = -11;// Hồ sơ bị trả lại giống trạng thái -10
    public static final int STATUS_HO_SO_BI_HUY_N_12 = -12;// Hồ sơ bị trả lại giống trạng thái -10
    public static final int STATUS_CHO_TU_VAN_VIEN_DI_KY_HOP_DONG = 25;
    public static final int STATUS_KE_TOAN_TRA_LAI_TVV = -25;
    public static final int STATUS_CHO_QLKV_XU_LY_N_50 = -50;
    public static final int STATUS_CHO_KSNB_DUYET_15 = 15;
    public static final int STATUS_DL_TRA_LAI_KSNB_N_15 = -15;
    public static final int STATUS_TVV_HUY_DO_K_KY_DUOC_HD = -16;
    public static final int STATUS_TDTD_HO_SO = 17;

    public static final int STATUS_HO_SO_CUA_QLKV = 0;
    public static final int STATUS_HO_SO_CUA_TOI_CHO_XU_LY = 1;
    public static final int STATUS_HO_SO_CUA_BO_PHAN_KHAC_CHO_XU_LY = 2;
    public static final int STATUS_HO_SO_CHO_QLKV_HUY = 3;
    public static final int STATUS_DS_KH_SP_CHUYEN = 3;
    public static final int STATUS_HO_SO_HEN_GIO = 3;
    public static final int STATUS_HO_SO_DA_HUY = 4;


    public static final String GROUP_ID_DAI_LY = "3";
    public static final String GROUP_ID_DAI_LY_OTHER = "4";

    public static final int STATUS_QL_NHAN_VIEN_KY_HD = 1;
    public static final int STATUS_QL_NHAN_VIEN_TDTD = 1;
    public static final int STATUS_HO_SO_CUA_DAI_LY = 1;
    public static final int STATUS_HO_SO_CUA_BO_PHAN_KHAC_CHO_XU_LY_DAI_LY = 2;

    public static final String KEY_SAVE_TOKEN = "KEY_SAVE_TOKEN";
    public static final String KEY_SEND_DATA = "KEY_SEND_DATA";
    public static final String KEY_SEND_STATUS = "KEY_SEND_STATUS";
    public static final String KEY_SEND_CUSTOMER_CREDIT_ID = "KEY_SEND_CUSTOMER_CREDIT_ID";
    public static final String KEY_SEND_TYPE_ID = "KEY_SEND_TYPE_ID";

    public static final int TYPE_1_POST_ANH_CMNN = 1;
    public static final int TYPE_1_POST_ANH_DANG_KY_XE = 1002;
    public static final int TYPE_1_POST_ANH_SO_HO_KHAU = 2;
    public static final int TYPE_1_POST_ANH_CHUNG_MINH_THU_NHAP = 3;
    public static final int TYPE_1_POST_ANH_MAT = 4;
    public static final int TYPE_1_POST_ANH_NHA = 5;
    public static final int TYPE_1_POST_ANH_CONG_TY = 6;
    public static final int TYPE_1_POST_ANH_XE_DK_VAY = 6;
    public static final int TYPE_1_POST_ANH_HD_TU_VAN = 7;
    public static final int TYPE_1_POST_ANH_HD_CAM_CO = 8;
    public static final int TYPE_1_POST_ANH_HD_GUI_GIU_TAI_SAN = 9;
    public static final int TYPE_1_POST_ANH_HD_THONG_TIN_CHUNG_KHOAN_VAY = 10;

    public static final int TYPE_2_POST_ANH_CMT = 11;
    public static final int TYPE_2_POST_ANH_GIAY_DK_XE = 12;
    public static final int TYPE_2_POST_ANH_NHA = 13;
    public static final int TYPE_2_POST_ANH_MAT = 14;
    public static final int TYPE_2_POST_HD_TU_VAN = 15;
    public static final int TYPE_2_POST_HD_MUA_BAN = 16;
    public static final int TYPE_2_POST_HD_THUE_XE = 17;

    public static final int TYPE_2_POST_HD_TU_VAN_K_CHINH_CHU = 28;
    public static final int TYPE_2_POST_HD_MUA_BAN_K_CHINH_CHU = 29;
    public static final int TYPE_2_POST_HD_THUE_XE_K_CHINH_CHU = 30;

    public static final int TYPE_2_POST_ANH_CA_NHAN = 18;
    public static final int TYPE_2_POST_ANH_GIAY_TO_NHAN_THAN = 19;
    public static final int TYPE_2_POST_ANH_GIAY_TO_CU_TRU = 20;
    public static final int TYPE_2_POST_ANH_HOA_DON = 21;
    public static final int TYPE_2_POST_ANH_DINH_VI_NHA = 22;
    public static final int TYPE_2_POST_ANH_KHAC = 23;
    public static final int TYPE_2_HD_TU_VAN_6_BUOC_2 = 24;
    public static final int TYPE_2_HD_CAM_CO_6_BUOC_2 = 25;
    public static final int TYPE_2_HD_GIU_TAI_SAN_6_BUOC_2 = 26;
    public static final int TYPE_2_KHOAN_VAY_6_BUOC_2 = 27;


    public static final int TYPE_HD_TU_VAN_NHA_DAT = 40;
    public static final int TYPE_HD_MUA_BAN_NHA_DAT = 41;
    public static final int TYPE_HD_THUE_XE_NHA_DAT = 42;


    public static final int TYPE_NHAN_THAN_VAY_THE_CHAP_OTO = 31;
    public static final int TYPE_CU_TRU_VAY_THE_CHAP_OTO = 32;
    public static final int TYPE_CU_CONG_VIEC_THE_CHAP_OTO = 33;
    public static final int TYPE_THU_NHAP_VIEC_THE_CHAP_OTO = 34;
    public static final int TYPE_GIAY_TO_XE_THE_CHAP_OTO = 35;
    public static final int TYPE_HD_TU_VAN_THE_CHAP_OTO = 36;
    public static final int TYPE_ANH_NHA_THE_CHAP_OTO = 37;
    public static final int TYPE_ANH_CTY_THE_CHAP_OTO = 38;
//    public static final int TYPE_HD_TU_VAN_OTO = 37;
//    public static final int TYPE_HD_MUA_BAN_OTO = 38;
//    public static final int TYPE_HD_THUE_XE_OTO = 39;



    /// STATUS FOR COMMENT
    public final static int CODE_CANCEL_FROM_CONTRACT = 1;
    public final static int CODE_CANCEL_FROM_POSTCONTRACT = 2;
    /// STATUS AND CODE VALUES]
    public static final int TOKEN_SUCCESS = 1;
    public static final int TOKEN_OUT_OF_DATE = -1;
    public static final int TOKEN_UNAVAILABLE = -6;
    public static final int TOKEN_FAIL = 0;
    public static final int STATUS_SUCCESS = 2;
    public static final int STATUS_FAIL = 3;
    public static final int TIME_OUT = 5;
    // Group id
    public static final String GROUP_ID_QLKV = "13";
    public static final String GROUP_ID_KSNB_TD_THUC_DIA = "17";
    public static final String GROUP_ID_DL1 = "4";
    public static final String GROUP_ID_DL2 = "3";
    public static final String GROUP_ID_TVV = "10";
    public static final String GROUP_ID_SIGN_CONTRACT = "18";
    public static final String GROUP_ID_QL_TDTD = "20";
    public static final String GROUP_ID_QL_KY_HOP_DONG = "19";
    // SHOP ID
    public static final String SHOP_ID = "3703";

    // Loại thanh toán
    public static final String SPN_ITEM_TIEN_MAT = "1";
    public static final String SPN_ITEM_CHUYEN_KHOAN_QUA_SO_TK = "3";
    public static final String SPN_ITEM_CHUYEN_KHOAN_QUA_SO_THE = "4";
    public static final String SPN_ITEM_CHUYEN_TIEN_VIETTEL = "5";
    public static final String SPN_ITEM_CHON_HINH_THUC_CHUYEN_TIEN = "0";
    // ten trang thai
    public final static String STATUS_FILTER_0_LABEL = "Tất cả";
    public final static String STATUS_FILTER_1_LABEL = " Chờ đi lấy hồ sơ";
    public final static String STATUS_FILTER_2_LABEL = "Chờ đi ký hợp đồng";
    public final static String STATUS_FILTER_3_LABEL = "Chờ KSNB duyệt cho đại lý";
    public final static String STATUS_FILTER_4_LABEL = "Chờ đại lý duyệt";
    public final static String STATUS_FILTER_5_LABEL = "Kế toán trả lại tư vấn viên";
    public final static String STATUS_FILTER_6_LABEL = "KSNB trả lại tư vấn viên";
    public final static String STATUS_FILTER_7_LABEL = "Chờ kế toán giải ngân";
    public final static String STATUS_FILTER_8_LABEL = "TVV Hủy";

    public final static String STATUS_FILTER_QLTD_1_LABEL = "Chờ đi thực địa";
    public final static String STATUS_FILTER_QLTD_2_LABEL = "Chờ xác nhận & điều phối đại lý";
    public final static String STATUS_FILTER_QLTD_3_LABEL = "Chờ đại lý duyệt";
    public final static String STATUS_FILTER_QLTD_4_LABEL = "Chờ ký hợp đồng";
    public final static String STATUS_FILTER_QLTD_5_LABEL = "Chờ giải ngân ";

    public final static String STATUS_FILTER_QL_SIGN_CONTRACT_1_LABEL = "Chờ ký hợp đồng";
    public final static String STATUS_FILTER_QL_SIGN_CONTRACT_2_LABEL = "Chờ giải ngân";
    public final static String STATUS_FILTER_QL_SIGN_CONTRACT_3_LABEL = "Đã giải ngân";


    // danh sach các trạng thái
    public final static String STATUS_N_11_LABEL = "Hồ sơ bị trả lại";
    public final static String STATUS_N_12_LABEL = "Hồ sơ bị trả lại";
    public final static String STATUS_10_LABEL = "Chờ TVV đi lấy hồ sơ";
    public final static String STATUS_25_LABEL = "Chờ đi ký hợp đồng";
    public final static String STATUS_N_25_LABEL = "Kế toán trả lại TVV";
    public final static String STATUS_15_LABEL = "Chờ KSNB duyệt cho đại lý";
    public final static String STATUS_N_15_LABEL = "ĐL trả lại KSNB";
    public final static String STATUS_N_16_LABEL = "TVV trả lại KSNB";
    public final static String STATUS_20_LABEL = "Chờ Đại lý xác nhận";
    public final static String STATUS_5_LABEL = "Chờ Support chuyển TVV";
    public final static String STATUS_1_LABEL = "Chờ Support xác nhận";
    public final static String STATUS_0_LABEL = "Đã hủy";
    public final static String STATUS_N_1_LABEL = "Support bị KSNB trả lại";
    public final static String STATUS_N_5_LABEL = "TVV Hủy Hồ Sơ";
    public final static String STATUS_35_LABEL = "Đang vay";
    public final static String STATUS_100_LABEL = "Đã tất toán";
    public final static String STATUS_30_LABEL = "Chờ Kế toán giải ngân";
    public final static String STATUS_N_10_LABEL = "TVV bị KSNB trả lại";
    public final static String STATUS_N_50_LABEL = "Chờ QLKV xử lý";
    public final static String STATUS_17_LABEL = "Chờ TĐTĐ xử lý";
    // Json tag
    public final static String TAG_MESSAGE = "Message";
    public final static String TAG_CODE = "Code";
    public final static String TAG_STATUS = "Status";
    public final static String TAG_DATA = "Data";
    public final static String TAG_TOKEN = "@";

    //----------------------
    public static final String FIRST_KEY = "c8d9301705293a3068472d13e7d2b2f1";
    public static final String LAST_KEY = "TM!^*@2020";

    public final static String TAG_LOAN_CREADIT = "LoanCredit";
    //key LoanType
    public final static String KEY_LOANTYPE = "loantype";
    public final static String KEY_FIRST_LOAN = "1st_loan";
    public final static int MAX_MONEY_DKXE = 30;
    public final static int MIN_MONEY = 1;
    public final static String LABEL_VAYTINCHAP = "VAY TÍN CHẤP";
    public final static String LABEL_VAYHOKHAU = "VAY THEO HỘ KHẨU";
    public final static String LABEL_VAYDKXE = "VAY THEO ĐĂNG KÝ XE";

    public final static int DAY_10 = 10;
    public final static int DAY_20 = 20;
    public final static int DAY_30 = 30;
    public final static int DAY_10_POS = 0;
    public final static int DAY_20_POS = 1;
    public final static int DAY_30_POS = 2;
    //  public final static int DAY_30_POS = 3;
    //web view
    public final static String KEY_URL = "url";
    public final static String URL_TUVAN = "http://tima.vn/diem-tu-van.htm";
    public final static String URL_CAUHOI = "http://tima.vn/cau-hoi-thuong-gap.htm";
    //type
    public final static int TYPE_FROM_MAIN = 1;
    public final static int TYPE_FROM_INTRO = 0;
    public final static String KEY_SELECT_LOAN = "ksl";
    //noti
    public final static String ERROR_API = "Có lỗi xảy ra";
    // data
    public final static ArrayList<String> data_relationship = new ArrayList<>(Arrays.asList("Chồng/Vợ", "Mẹ", "Bố", "Anh/Em Trai", "Chị/Em gái")); // id = potision +1
    public final static ArrayList<String> data_living_time = new ArrayList<>(Arrays.asList("Dưới 1 năm", "1 -3 năm", "3 - 10 năm", "Hơn 10 năm")); // id = potision +1
    public final static ArrayList<String> data_living_type = new ArrayList<>(Arrays.asList("Sở hữu cá nhân", "Đồng sở hữu", "Thuộc sở hữu của họ hàng", "Thuê", "Sở hữu nhà nước")); // id = potision +1
    public static boolean isLogin = false;
    /// STATUS AND CODE VALUES]

    public final static String KEY_ID_SELECT_ADDRESS = "ID";
    public final static String KEY_TYPE_SELECT_ADDRESS = "Type";
    public final static String KEY_NAME_SELECT_ADDRESS = "Name";
    public final static String KEY_ID_CITY_ADDRESS = "IdCity";
    public final static String KEY_ID_DISTRICT_ADDRESS = "IdDistrict";
    public final static String KEY_ID_WARD_ADDRESS = "IdWard";

    public static final int BANGLAIXE_CMND = 1;
    public static final int PHIEULUONG = 2;
    public static final int SAOKELUONG = 3;
    public static final int SAOKENGANHANG = 4;
    public static final int HOPDONGLAODONG = 5;
    public static final int BAOHIEMYTE = 6;
    public static final int THENHANVIEN = 7;
    public static final int SAOKETUATM = 8;
    public static final int TAMTRU_KT3 = 9;
    public static final int HOADON = 10;


    public final static int CHECKLIST_TYPE_TEXT = 0;
    public final static int CHECKLIST_TYPE_CHECKBOX = 1;
    public final static int CHECKLIST_TYPE_DATETIME = 2;


}

