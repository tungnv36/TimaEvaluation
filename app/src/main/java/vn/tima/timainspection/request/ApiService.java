package vn.tima.timainspection.request;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import vn.tima.timainspection.Model.Entity.InsertBacklog;
import vn.tima.timainspection.Model.Entity.InsertErrorDepartment;
import vn.tima.timainspection.Model.Entity.NotificationEntity;
import vn.tima.timainspection.Model.Entity.objcomment;

/**
 * Created by hoangngoc on 8/15/16.
 */

public interface ApiService {

    String API_SET_NOTIFY_READ = "/api/Counselor/MessageMarkRead";
    String API_GET_DETAIL_CUSTOMER = "/api/Coordinator/GetDetailsCustomer_V2";
    String API_GET_REPORT_INVENTORY = "/api/Coordinator/GetReportInventory";
    String API_LOGIN = "/api/Counselor/CheckLogin";
    String API_UPDATE_CUSTOMER_CREDIT = "/api/Counselor/UpdateCustomerCredit";
    String API_GetListCreditCoordinator = "/api/Coordinator/GetListCreditCoordinator_V2";
    String API_GetListQuestionCheckList = "/api/Coordinator/GetListQuestionCheckList_V2";
    String API_InsertCoordinatorCheckList = "/api/Coordinator/InsertCoordinatorCheckList_V2";
    String API_POST_COMMENT = "/api/Coordinator/InsertNoteLoanCredit_V2";
    String API_SYNC_LOCATION = "/api/Coordinator/InsertLocation_V2";
    String API_GET_HISTORY_COMMENT_CREDIT = "/api/Coordinator/GetHistoryCommentCredit_V2";
    String API_FieldSurveyReturnLoanCredit = "/api/Coordinator/FieldSurveyReturnLoanCredit_V2";
    String API_GetListCoordinatorFortransfer = "/api/Coordinator/GetListCoordinatorFortransfer_V2";
    String API_CoordinatorTransfer = "/api/Coordinator/CoordinatorTransfer_V2";
    String API_InsertProductAppraiser = "/api/Coordinator/InsertProductAppraiser_V2";
    String API_GetListCustomerCreditForCoordinatorAwait = "/api/Coordinator/GetListCustomerCreditForCoordinatorAwait_V2";
    String API_GetReportEvaluationHomeAndCompany = "/api/Coordinator/GetReportEvaluationEveryDay";
    String API_GetReportEvaluationByWeekend = "/api/Coordinator/GetReportEvaluationByWeekend";
    String API_GetReportEvaluationByLoanCredit = "/api/Coordinator/GetReportEvaluationByLoanCredit";
    String API_GetListReason = "/api/Coordinator/GetListReason";
    String API_InsertErrorDepartment = "/api/Coordinator/InsertErrorDepartment";
    String API_InsertBacklog = "/api/Coordinator/InsertFieldEvaluationReport";
    String API_GetReportInventoryField = "/api/Coordinator/GetReportInventoryField";

    String API_GET_CITY = "/api/CustomerCredit/GetListCity";
    String API_GET_District = "/api/CustomerCredit/GetListDistrict";
    String API_GET_Ward = "/api/CustomerCredit/GetListWard";
    String API_GET_PushNotificationDevice = "/api/Counselor/PushNotificationDevice";
    String API_GET_GetInfoIndividualBusiness = "/api/Counselor/GetInfoIndividualBusiness";
    String API_POST_GetInfoIndividualBusiness = "/api/Counselor/InsertIndividualBusiness";

    String POST_CreateCustomerLoanCredit = "/api/Counselor/CreateCustomerLoanCredit";

    String API_GET_NOTIFY = "/api/Counselor/GetListMessage";
    String API_GET_DETAIL_CUSTOMER_AGENCY = "/api/Counselor/AgencyGetDetailsCustomer";
    String API_GET_COUNSERLOR_AREA_MANAGER = "api/Counselor/GetCounselorAreaManager";
    String API_GET_LIST_DISTRICT_OF_AREA_MANAGER = "api/Counselor/GetListDistrictOfAreaManager";
    String API_GET_CONTRACT_LIST = "/api/Counselor/GetListCounselor";
    String API_GET_CONTRACT_LIST_BY_SUPPORT = "/api/Counselor/GetListCustomerForCounselorBySupportNavigation";
    String API_GET_AGENCY_CONTRACT_LIST = "/api/Counselor/GetListCustomerCreditForAgency";
    String API_LOAD_REASON_FOR_REFUSE = "/api/Counselor/LoadReasonForRefuse";
    String API_GET_LIBRARY_IMAGE_LIST = "/api/Counselor/GetListImg";
    String API_FORGOT_PASSWORD = "/api/Counselor/ChangePass";
    String API_POST_CONTRACT = "/api/Counselor/CounselorsConfirm";
    //    String API_POST_APPRAISAL = "/api/Counselor/PostExample2";
    String API_POST_APPRAISAL = "/api/Counselor/UpdateProductResultReview";
    String API_PUT_UPDATE_CONTRACT = "/api/Counselor/CounselorConfirmLevelHight";
    String API_PUT_AGENCY_UPDATE_CONTRACT = "/api/Counselor/AgencyConfirmAndUpdateMoney";
    String API_GET_AGENCY_HISTORY_COMMENT_CREDIT = "/api/Counselor/AgencyGetHistoryCommentCredit";
    String API_CONTRACT_BACK_TO_COORDINATOR = "api/Counselor/CounselorBackToCoordinator";
    String API_AGENCY_REFUSE_INFO_CUSTOMER = "api/Counselor/AgencyRefuseInforCustomer";
    String API_UPLOAD_IMAGE = "/api/Counselor/UploadSingleFile";
    String API_DELETE_IMAGE = "/api/Counselor/DeleteImg";
    String API_SAVE_SCHEDULE = "/api/Counselor/SaveSchedule";
    String API_CANCEL_SCHEDULE = "/api/Counselor/CancelSchedule";
    String API_GET_ALL_PRODUCT = "/api/Counselor/GetListAllProduct";
    String API_GET_QUESTION = "/api/Counselor/GetProductReviewResult";

    String API_CounselorsConfirmLoanCredit = "/api/Counselor/CounselorsConfirmLoanCredit";
    String API_AgencyGetSearchInfoCustomer = "/api/Counselor/AgencyGetSearchInfoCustomer";
    String API_GetFinancialDetaiShopById = "/api/Counselor/GetFinancialDetaiShopById";
    String API_SuggestWithdrawal = "/api/Counselor/SuggestWithdrawal";
    String API_GetHistoryWithDraw = "/api/Counselor/GetHistoryWithDraw";
    String API_SuperVisionCancel = "/api/Counselor/SuperVisionCancel";
    String CounselorCancelToSuperVision = "/api/Counselor/CounselorCancelToSuperVision";
    String API_ListPawnBadDebt = "/api/Counselor/ListPawnBadDebt";
    String API_ListPaymentGeneral = "/api/Counselor/ListPaymentGeneral";
    String API_ListPayment = "/api/Counselor/ListPayment";
    String API_ListLoanExtra = "/api/Counselor/ListLoanExtra";
    String API_GetListExtension = "/api/Counselor/GetListExtension";
    String API_GetFilesContract = "/api/Counselor/GetFilesContract";
    String API_GetListCommentDebtPrompted = "/api/Counselor/GetListCommentDebtPrompted";
    String API_GetInfoTransactionShop = "/api/Counselor/GetInfoTransactionShop";
    String API_GetListReasonFordebtPromted = "/api/Counselor/GetListReasonFordebtPromted";
    String API_GetPawnByID = "/api/Counselor/GetPawnByID";
    String API_SuperVisionBackToCounselor = "/api/Counselor/SuperVisionBackToCounselor";
    String API_CounselorCancelToSuperVision = "/api/Counselor/CounselorCancelToSuperVision";
    String API_GetInformationCustomerCredit = "/api/Counselor/GetInformationCustomerCredit";
    String API_GetCounselorTitileWork = "/api/ReportDaily/GetCounselorTitileWork";
    String API_InsertReportCounselor = "/api/ReportDaily/InsertReportCounselor";
    String API_UpdateReportByReportId = "/api/ReportDaily/UpdateReportByReportId";
    String API_GetReportCounselor = "/api/ReportDaily/GetReportCounselor";
    String API_GetListCoordinatorByArea = "/api/Counselor/GetListCoordinatorByArea";
    String API_GetListEmployeesOfManageFieldSurvey = "/api/Coordinator/GetListEmployeesOfManageFieldSurvey";
    String API_FieldSurveyCancelLoanCredit = "/api/Coordinator/FieldSurveyCancelLoanCredit";
    String API_GetListCustomerCreditForAwardContract = "/api/Counselor/GetListCustomerCreditForAwardContract";
    String GetLoanCreditManageFieldSurvey = "/api/Coordinator/GetLoanCreditManageFieldSurvey";
    String API_UpdateAwardContractAndFieldSurvey = "/api/Coordinator/UpdateAwardContractAndFieldSurvey";
    String API_GetLoanCreditManageAwardContract = "/api/Coordinator/GetLoanCreditManageAwardContract";
    String API_GetListEmployeesOfManageAwardContract = "/api/Coordinator/GetListEmployeesOfManageAwardContract";
    String API_ReadNotify = "/api/Coordinator/ReadNotify";
    String API_AgencyAgreeLoanCredit = "/api/Counselor/AgencyAgreeLoanCredit";
    String API_AgencyCancelCheckLoanCredit = "/api/Counselor/AgencyCancelCheckLoanCredit";
    String API_AwardContractReturnLoanCredit = "/api/Counselor/AwardContractReturnLoanCredit";
    String API_GetListProductType = "/api/Counselor/GetListProductType";
    String API_GetListNumberLoanDay = "/api/Counselor/GetListNumberLoanDay";
    String API_GetListNumberDaysPaid = "/api/Counselor/GetListNumberDaysPaid";
    String API_GetListAmountPaidPeriodic = "/api/Counselor/GetListAmountPaidPeriodic";
    String API_GetListRateTypeForAwardContract = "/api/Counselor/GetListRateTypeForAwardContract";
    String API_GetListNumberDayForAwardContract = "/api/Counselor/GetListNumberDayForAwardContract";
    String API_GetListFrequencyForAwardContract = "/api/Counselor/GetListFrequencyForAwardContract";
    String API_FieldSurveyAddressCompanyComplete = "/api/Coordinator/FieldSurveyAddressCompanyComplete";
    String API_CalculatorMoneyForAwardContract = "/api/Counselor/CalculatorMoneyForAwardContract";

    @GET(API_GET_QUESTION)
    Call<ResponseBody> getListQuestion(@Query("token") String token,
                                       @Query("loanCreditId") String loanCreditId,
                                       @Query("idType") String idType,
                                       @Query("productId") String productId);

    @Headers({"Cache-Control: max-age=640000"})
    @POST(API_POST_APPRAISAL)
    Call<ResponseBody> postAppraisal(@Body String objproductReviewResult);

    @Headers({"Cache-Control: max-age=640000"})
    @POST(API_InsertErrorDepartment)
    Call<ResponseBody> insertErrorDepartment(@Body InsertErrorDepartment obj);

    @Headers({"Cache-Control: max-age=640000"})
    @POST(API_InsertBacklog)
    Call<ResponseBody> insertBacklog(@Body InsertBacklog obj);

    @GET(API_GET_ALL_PRODUCT)
    Call<ResponseBody> getAllProduct(@Query("token") String token,
                                     @Query("loanCreditId") String loanCreditId);

    @GET(API_GET_LIBRARY_IMAGE_LIST)
    Call<ResponseBody> getListImage(@Query("token") String token,
                                    @Query("typeId") String typeId,
                                    @Query("loanCreditId") String loanCreditId);

    //    @FormUrlEncoded
    @GET(API_DELETE_IMAGE)
    Call<ResponseBody> deleteImage(@Query("token") String token,
                                   @Query("userId") String userId,
                                   @Query("loanCreditId") String loanCreditId,
                                   @Query("imgId") String imgId);

    @GET(API_SET_NOTIFY_READ)
    Call<ResponseBody> setNotifyRead(@Query("token") String token,
                                     @Query("MsgId") String MsgId);


    @GET(API_GetListQuestionCheckList)
    Call<ResponseBody> getChecklist(@Query("token") String token,
                                    @Query("loancreditId") int loancreditId);

    @GET(API_GetReportInventoryField)
    Call<ResponseBody> getReportInventoryField(@Query("token") String token,
                                               @Query("fromDate") String fromDate,
                                               @Query("toDate") String toDate);

    @GET(API_GetListReason)
    Call<ResponseBody> getListReason(@Query("token") String token);

    @POST(API_InsertCoordinatorCheckList)
    @FormUrlEncoded
    Call<ResponseBody> insertCoordinatorCheckList(@Field("GroupId") int GroupId,
                                                  @Field("UserId") int UserId,
                                                  @Field("LoanCreditId") int LoanCreditId,
                                                  @Field("ContentCheckList") String ContentCheckList,
                                                  @Field("token") String token);

//    @Headers({"Cache-Control: max-age=640000"})
//    @Headers({"application/x-www-form-urlencoded"})
    @Headers({
        "Accept: application/json",
        "User-Agent: Your-App-Name",
        "Cache-Control: max-age=640000"
    })
    @POST(API_UPDATE_CUSTOMER_CREDIT)
    Call<ResponseBody> updateCustomerCredit(@Body String obj);


    @GET(API_LOGIN)
    Call<ResponseBody> checkLogin(@Query("userName") String username,
                                  @Query("passWord") String password);

    @GET(API_GetListCreditCoordinator)
    Call<ResponseBody> getListCreditCoordinator(@Query("token") String token,
                                                @Query("cusName") String cusName,
                                                @Query("fromDate") String fromDate,
                                                @Query("toDate") String toDate,
                                                @Query("iTop") int iTop,
                                                @Query("status") int status,
                                                @Query("pageCurrent") int pageCurrent,
                                                @Query("loanCreditId") int loanCreditId,
                                                @Query("CoordinatorId") int CoordinatorId,
                                                @Query("TypeId") int TypeId);

    @GET(API_GetListCustomerCreditForCoordinatorAwait)
    Call<ResponseBody> GetListCustomerCreditForCoordinatorAwait(@Query("token") String token,
                                                                @Query("cusName") String cusName,
                                                                @Query("fromDate") String fromDate,
                                                                @Query("toDate") String toDate,
                                                                @Query("iTop") int iTop,
                                                                @Query("status") int status,
                                                                @Query("pageCurrent") int pageCurrent,
                                                                @Query("loanCreditId") int loanCreditId,
                                                                @Query("TypeId") int TypeId);

    @GET(API_GET_DETAIL_CUSTOMER)
    Call<ResponseBody> getContractDetail(@Query("token") String token,
                                         @Query("customerId") int customerId,
                                         @Query("loanCreditId") int loanCreditId);

    @GET(API_GET_REPORT_INVENTORY)
    Call<ResponseBody> getReportInventory(@Query("token") String token,
                                          @Query("fromDate") String fromDate,
                                          @Query("toDate") String toDate);

//    @Headers({"Cache-Control: max-age=640000"})
//    @Headers({"application/x-www-form-urlencoded"})
    @Headers({
            "Content-Type: application/json",//Accept
            "Cache-Control: max-age=640000"
    })
    @POST(API_POST_COMMENT)
    Call<ResponseBody> addComment(@Body objcomment obj);

    @Headers({
            "Content-Type: application/json",//Accept
            "Cache-Control: max-age=640000"
    })
    @POST(API_GET_PushNotificationDevice)
    Call<ResponseBody> pushNotificationDevice(@Body NotificationEntity obj);

    @GET(API_SYNC_LOCATION)
    Call<ResponseBody> putLocation(@Query("sDevice") String sDevice,
                                   @Query("sLat") String sLat,
                                   @Query("sLong") String sLong,
                                   @Query("sAddress") String sAddress,
                                   @Query("sCity") String sCity,
                                   @Query("token") String token,
                                   @Query("LoanCreditId") int LoanCreditId);

    @GET(API_GET_HISTORY_COMMENT_CREDIT)
    Call<ResponseBody> getListComment(@Query("token") String token,
                                      @Query("loanCreditId") int loanCreditId);

    @GET(API_FieldSurveyReturnLoanCredit)
    Call<ResponseBody> fieldSurveyReturnLoanCredit(@Query("token") String token,
                                                   @Query("loanCreditId") int loanCreditId,
                                                   @Query("IsComplete") int IsComplete);

    @GET(API_GetListCoordinatorFortransfer)
    Call<ResponseBody> getListCoordinatorFortransfer(@Query("token") String token,
                                                     @Query("loanCreditId") int loanCreditId);

    @GET(API_CoordinatorTransfer)
    Call<ResponseBody> coordinatorTransfer(@Query("token") String token,
                                           @Query("loanCreditId") int loanCreditId,
                                           @Query("CoordinatorID") int CoordinatorID,
                                           @Query("CoordinatorName") String CoordinatorName,
                                           @Query("IsComplete") int IsComplete);

    @GET(API_InsertProductAppraiser)
    Call<ResponseBody> insertProductAppraiser(@Query("LoanCreditId") int LoanCreditId,
                                              @Query("ProductId") int ProductId,
                                              @Query("Type") int Type,
                                              @Query("Status") int Status,
                                              @Query("token") String token,
                                              @Query("IsWord") int IsWord);
    //,@Query("IsWord") int IsWord

    @GET(API_GetReportEvaluationHomeAndCompany)
    Call<ResponseBody> getReportEvaluationHomeAndCompany(@Query("token") String token,
                                                         @Query("fromDate") String fromDate,
                                                         @Query("toDate") String toDate);

    @GET(API_GetReportEvaluationByWeekend)
    Call<ResponseBody> getReportEvaluationByWeekend(@Query("token") String token,
                                                    @Query("fromDate") String fromDate,
                                                    @Query("toDate") String toDate);

    @GET(API_GetReportEvaluationByLoanCredit)
    Call<ResponseBody> getReportEvaluationByLoanCredit(@Query("token") String token,
                                                       @Query("fromDate") String fromDate,
                                                       @Query("toDate") String toDate);
}
