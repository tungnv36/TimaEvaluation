package vn.tima.timainspection.Model.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tima on 11/29/17.
 */

public class ContractDetailEntity {
    @SerializedName("Customer")
    public Customer Customer;
    @SerializedName("LoanCredit")
    public LoanCredit LoanCredit;
    @SerializedName("LoanCreditMoneyFeildSale")
    public String LoanCreditMoneyFeildSale;
    @SerializedName("TotalMoneyConsulant")
    public String TotalMoneyConsulant;
    @SerializedName("TotalMoneyPaymentOnePeriod")
    public String TotalMoneyPaymentOnePeriod;
    @SerializedName("TotalMoneyService")
    public String TotalMoneyService;
    @SerializedName("TotalMoneyAccounting")
    public String TotalMoneyAccounting;
    @SerializedName("TotalMoneyInterest")
    public String TotalMoneyInterest;
    @SerializedName("NumberPeriod")
    public String NumberPeriod;
    @SerializedName("InterestPaymentDate")
    public String InterestPaymentDate;
    @SerializedName("FinalSettlementDate")
    public String FinalSettlementDate;
    @SerializedName("IsThamDinhNha")
    public int IsThamDinhNha;
    @SerializedName("IsThamDinhCongTy")
    public int IsThamDinhCongTy;

    public static class Customer {
        @SerializedName("CustomerId")
        public int CustomerId;
        @SerializedName("FullName")
        public String FullName;
        @SerializedName("Gender")
        public int Gender;
        @SerializedName("Birthday")
        public String Birthday;
        @SerializedName("Phone")
        public String Phone;
        @SerializedName("CardNumber")
        public String CardNumber;
        @SerializedName("NameCity")
        public String NameCity;
        @SerializedName("NameDistrict")
        public String NameDistrict;
        @SerializedName("CityNameCompany")
        public String CityNameCompany;
        @SerializedName("DistrictNameCompany")
        public String DistrictNameCompany;
        @SerializedName("NameWard")
        public String NameWard;
        @SerializedName("Email")
        public String Email;
        @SerializedName("Street")
        public String Street;
        @SerializedName("NumberLiving")
        public String NumberLiving;
        @SerializedName("LivingApartmentNumber")
        public String LivingApartmentNumber;
        @SerializedName("LivingTime")
        public String LivingTime;
        @SerializedName("TypeOfOwnership")
        public String TypeOfOwnership;
        @SerializedName("IsResidential")
        public int IsResidential;
        @SerializedName("Job")
        public String Job;
        @SerializedName("WorkingIndustry")
        public String WorkingIndustry;
        @SerializedName("CompanyName")
        public String CompanyName;
        @SerializedName("CompanyPhone")
        public String CompanyPhone;
        @SerializedName("Salary")
        public long Salary;
        @SerializedName("ReceiveYourIncome")
        public String ReceiveYourIncome;
        @SerializedName("RelativeFamily")
        public String RelativeFamily;
        @SerializedName("FullNameFamily")
        public String FullNameFamily;
        @SerializedName("PhoneFamily")
        public String PhoneFamily;
        @SerializedName("FullNameColleague")
        public String FullNameColleague;
        @SerializedName("PhoneColleague")
        public String PhoneColleague;
        @SerializedName("Manufacturer")
        public String Manufacturer;
        @SerializedName("ModelPhone")
        public String ModelPhone;
        @SerializedName("YearMade")
        public String YearMade;
        @SerializedName("CreateDate")
        public String CreateDate;
        @SerializedName("AddressCompany")
        public String AddressCompany;
        @SerializedName("AddressHouseHold")
        public String AddressHouseHold;
        @SerializedName("Facebook")
        public String Facebook;
        @SerializedName("IsMarried")
        public int IsMarried;
        @SerializedName("NumberBaby")
        public int NumberBaby;
        @SerializedName("IsLivingTogether")
        public int IsLivingTogether;
        @SerializedName("CountCall")
        public int CountCall;
        @SerializedName("DocumentId")
        public int DocumentId;
        @SerializedName("DocumentValue")
        public String DocumentValue;
        @SerializedName("ContactNameCompany")
        public String ContactNameCompany;
        @SerializedName("CompanyTaxCode")
        public String CompanyTaxCode;
        @SerializedName("DescriptionPositionJob")
        public String DescriptionPositionJob;
        @SerializedName("CityId")
        public int CityId;
        @SerializedName("DistrictId")
        public int DistrictId;
        @SerializedName("WardId")
        public int WardId;
        @SerializedName("LivingTimeId")
        public int LivingTimeId;
        @SerializedName("TypeOfOwnershipId")
        public int TypeOfOwnershipId;
        @SerializedName("JobId")
        public int JobId;
        @SerializedName("RelativeFamilyId")
        public int RelativeFamilyId;
        @SerializedName("ManufacturerId")
        public int ManufacturerId;
        @SerializedName("CompanyDistrictId")
        public int CompanyDistrictId;
        @SerializedName("CompanyCityId")
        public int CompanyCityId;
        @SerializedName("CarVersion")
        public String CarVersion;
        @SerializedName("CarDesign")
        public String CarDesign;
        @SerializedName("CarNumberSeat")
        public int CarNumberSeat;
        @SerializedName("AddressPersonFamily")
        public String AddressPersonFamily;
        @SerializedName("CompanyCityName")
        public String CompanyCityName;
        @SerializedName("CompanyDistrictName")
        public String CompanyDistrictName;

        public int getCustomerId() {
            return CustomerId;
        }

        public void setCustomerId(int customerId) {
            CustomerId = customerId;
        }

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String fullName) {
            FullName = fullName;
        }

        public int getGender() {
            return Gender;
        }

        public void setGender(int gender) {
            Gender = gender;
        }

        public String getBirthday() {
            return Birthday;
        }

        public void setBirthday(String birthday) {
            Birthday = birthday;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String phone) {
            Phone = phone;
        }

        public String getCardNumber() {
            return CardNumber;
        }

        public void setCardNumber(String cardNumber) {
            CardNumber = cardNumber;
        }

        public String getNameCity() {
            return NameCity;
        }

        public void setNameCity(String nameCity) {
            NameCity = nameCity;
        }

        public String getNameDistrict() {
            return NameDistrict;
        }

        public void setNameDistrict(String nameDistrict) {
            NameDistrict = nameDistrict;
        }

        public String getCityNameCompany() {
            return CityNameCompany;
        }

        public void setCityNameCompany(String cityNameCompany) {
            CityNameCompany = cityNameCompany;
        }

        public String getDistrictNameCompany() {
            return DistrictNameCompany;
        }

        public void setDistrictNameCompany(String districtNameCompany) {
            DistrictNameCompany = districtNameCompany;
        }

        public String getNameWard() {
            return NameWard;
        }

        public void setNameWard(String nameWard) {
            NameWard = nameWard;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getStreet() {
            return Street;
        }

        public void setStreet(String street) {
            Street = street;
        }

        public String getNumberLiving() {
            return NumberLiving;
        }

        public void setNumberLiving(String numberLiving) {
            NumberLiving = numberLiving;
        }

        public String getLivingApartmentNumber() {
            return LivingApartmentNumber;
        }

        public void setLivingApartmentNumber(String livingApartmentNumber) {
            LivingApartmentNumber = livingApartmentNumber;
        }

        public String getLivingTime() {
            return LivingTime;
        }

        public void setLivingTimeName(String livingTime) {
            LivingTime = livingTime;
        }

        public String getTypeOfOwnership() {
            return TypeOfOwnership;
        }

        public void setTypeOfOwnership(String typeOfOwnership) {
            TypeOfOwnership = typeOfOwnership;
        }

        public int getIsResidential() {
            return IsResidential;
        }

        public void setIsResidential(int isResidential) {
            IsResidential = isResidential;
        }

        public String getJob() {
            return Job;
        }

        public void setJob(String job) {
            Job = job;
        }

        public String getWorkingIndustry() {
            return WorkingIndustry;
        }

        public void setWorkingIndustry(String workingIndustry) {
            WorkingIndustry = workingIndustry;
        }

        public String getCompanyName() {
            return CompanyName;
        }

        public void setCompanyName(String companyName) {
            CompanyName = companyName;
        }

        public String getCompanyPhone() {
            return CompanyPhone;
        }

        public void setCompanyPhone(String companyPhone) {
            CompanyPhone = companyPhone;
        }

        public long getSalary() {
            return Salary;
        }

        public void setSalary(long salary) {
            Salary = salary;
        }

        public String getReceiveYourIncome() {
            return ReceiveYourIncome;
        }

        public void setReceiveYourIncome(String receiveYourIncome) {
            ReceiveYourIncome = receiveYourIncome;
        }

        public String getRelativeFamily() {
            return RelativeFamily;
        }

        public void setRelativeFamily(String relativeFamily) {
            RelativeFamily = relativeFamily;
        }

        public String getFullNameFamily() {
            return FullNameFamily;
        }

        public void setFullNameFamily(String fullNameFamily) {
            FullNameFamily = fullNameFamily;
        }

        public String getPhoneFamily() {
            return PhoneFamily;
        }

        public void setPhoneFamily(String phoneFamily) {
            PhoneFamily = phoneFamily;
        }

        public String getFullNameColleague() {
            return FullNameColleague;
        }

        public void setFullNameColleague(String fullNameColleague) {
            FullNameColleague = fullNameColleague;
        }

        public String getPhoneColleague() {
            return PhoneColleague;
        }

        public void setPhoneColleague(String phoneColleague) {
            PhoneColleague = phoneColleague;
        }

        public String getManufacturer() {
            return Manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            Manufacturer = manufacturer;
        }

        public String getModelPhone() {
            return ModelPhone;
        }

        public void setModelPhone(String modelPhone) {
            ModelPhone = modelPhone;
        }

        public String getYearMade() {
            return YearMade;
        }

        public void setYearMade(String yearMade) {
            YearMade = yearMade;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String createDate) {
            CreateDate = createDate;
        }

        public String getAddressCompany() {
            return AddressCompany;
        }

        public void setAddressCompany(String addressCompany) {
            AddressCompany = addressCompany;
        }

        public String getAddressHouseHold() {
            return AddressHouseHold;
        }

        public void setAddressHouseHold(String addressHouseHold) {
            AddressHouseHold = addressHouseHold;
        }

        public String getFacebook() {
            return Facebook;
        }

        public void setFacebook(String facebook) {
            Facebook = facebook;
        }

        public int getIsMarried() {
            return IsMarried;
        }

        public void setIsMarried(int isMarried) {
            IsMarried = isMarried;
        }

        public int getNumberBaby() {
            return NumberBaby;
        }

        public void setNumberBaby(int numberBaby) {
            NumberBaby = numberBaby;
        }

        public int getIsLivingTogether() {
            return IsLivingTogether;
        }

        public void setIsLivingTogether(int isLivingTogether) {
            IsLivingTogether = isLivingTogether;
        }

        public int getCountCall() {
            return CountCall;
        }

        public void setCountCall(int countCall) {
            CountCall = countCall;
        }

        public int getDocumentId() {
            return DocumentId;
        }

        public void setDocumentId(int documentId) {
            DocumentId = documentId;
        }

        public String getDocumentValue() {
            return DocumentValue;
        }

        public void setDocumentValue(String documentValue) {
            DocumentValue = documentValue;
        }

        public String getContactNameCompany() {
            return ContactNameCompany;
        }

        public void setContactNameCompany(String contactNameCompany) {
            ContactNameCompany = contactNameCompany;
        }

        public String getCompanyTaxCode() {
            return CompanyTaxCode;
        }

        public void setCompanyTaxCode(String companyTaxCode) {
            CompanyTaxCode = companyTaxCode;
        }

        public String getDescriptionPositionJob() {
            return DescriptionPositionJob;
        }

        public void setDescriptionPositionJob(String descriptionPositionJob) {
            DescriptionPositionJob = descriptionPositionJob;
        }

        public int getCityId() {
            return CityId;
        }

        public void setCityId(int cityId) {
            CityId = cityId;
        }

        public int getDistrictId() {
            return DistrictId;
        }

        public void setDistrictId(int districtId) {
            DistrictId = districtId;
        }

        public int getWardId() {
            return WardId;
        }

        public void setWardId(int wardId) {
            WardId = wardId;
        }

        public int getLivingTimeId() {
            return LivingTimeId;
        }

        public void setLivingTimeId(int livingTimeId) {
            LivingTimeId = livingTimeId;
        }

        public int getTypeOfOwnershipId() {
            return TypeOfOwnershipId;
        }

        public void setTypeOfOwnershipId(int typeOfOwnershipId) {
            TypeOfOwnershipId = typeOfOwnershipId;
        }

        public int getJobId() {
            return JobId;
        }

        public void setJobId(int jobId) {
            JobId = jobId;
        }

        public int getRelativeFamilyId() {
            return RelativeFamilyId;
        }

        public void setRelativeFamilyId(int relativeFamilyId) {
            RelativeFamilyId = relativeFamilyId;
        }

        public int getManufacturerId() {
            return ManufacturerId;
        }

        public void setManufacturerId(int manufacturerId) {
            ManufacturerId = manufacturerId;
        }

        public int getCompanyDistrictId() {
            return CompanyDistrictId;
        }

        public void setCompanyDistrictId(int companyDistrictId) {
            CompanyDistrictId = companyDistrictId;
        }

        public int getCompanyCityId() {
            return CompanyCityId;
        }

        public void setCompanyCityId(int companyCityId) {
            CompanyCityId = companyCityId;
        }

        public String getCarVersion() {
            return CarVersion;
        }

        public void setCarVersion(String carVersion) {
            CarVersion = carVersion;
        }

        public String getCarDesign() {
            return CarDesign;
        }

        public void setCarDesign(String carDesign) {
            CarDesign = carDesign;
        }

        public int getCarNumberSeat() {
            return CarNumberSeat;
        }

        public void setCarNumberSeat(int carNumberSeat) {
            CarNumberSeat = carNumberSeat;
        }

        public String getAddressPersonFamily() {
            return AddressPersonFamily;
        }

        public void setAddressPersonFamily(String addressPersonFamily) {
            AddressPersonFamily = addressPersonFamily;
        }

        public void setLivingTime(String livingTime) {
            LivingTime = livingTime;
        }

        public String getCompanyCityName() {
            return CompanyCityName;
        }

        public void setCompanyCityName(String companyCityName) {
            CompanyCityName = companyCityName;
        }

        public String getCompanyDistrictName() {
            return CompanyDistrictName;
        }

        public void setCompanyDistrictName(String companyDistrictName) {
            CompanyDistrictName = companyDistrictName;
        }
    }

    public static class LoanCredit {
        @SerializedName("TotalMoneyFirst")
        public int TotalMoneyFirst;
        @SerializedName("TotalReturn")
        public int TotalReturn;
        @SerializedName("MoneyConsultant")
        public int MoneyConsultant;
        @SerializedName("MoneyService")
        public int MoneyService;
        @SerializedName("MoneyLoan")
        public int MoneyLoan;
        @SerializedName("FromDate")
        public String FromDate;
        @SerializedName("ToDate")
        public String ToDate;
        @SerializedName("NextDate")
        public String NextDate;
        @SerializedName("TypeID")
        public int TypeID;
        @SerializedName("Status")
        public int Status;
        @SerializedName("OwerShopId")
        public int OwerShopId;
        @SerializedName("AgentLoanID")
        public int AgentLoanID;
        @SerializedName("IsThamDinh")
        public int IsThamDinh;
        @SerializedName("CountCall")
        public int CountCall;
        @SerializedName("LoanID")
        public int LoanID;
        @SerializedName("ModelPhone")
        public String ModelPhone;
        @SerializedName("CreateDate")
        public String CreateDate;
        @SerializedName("Step")
        public int Step;
        @SerializedName("utm_source")
        public String utm_source;
        @SerializedName("SupportID")
        public int SupportID;
        @SerializedName("SupportLastID")
        public int SupportLastID;
        @SerializedName("CustomerCreditId")
        public int CustomerCreditId;
        @SerializedName("LoanCreditId")
        public int LoanCreditId;
        @SerializedName("TypeReceivingMoney")
        public int TypeReceivingMoney;
        @SerializedName("NameBanking")
        public String NameBanking;
        @SerializedName("NameCardHolder")
        public String NameCardHolder;
        @SerializedName("NumberCardBanking")
        public String NumberCardBanking;
        @SerializedName("AccountNumberBanking")
        public String AccountNumberBanking;
        @SerializedName("Note")
        public String Note;
        @SerializedName("TotalMoney")
        public int TotalMoney;
        @SerializedName("RateType")
        public int RateType;
        @SerializedName("Represent")
        public String Represent;
        @SerializedName("Document")
        public String Document;
        @SerializedName("CoordinatorID")
        public String CoordinatorID;
        @SerializedName("LoanTime")
        public int LoanTime;
        @SerializedName("Frequency")
        public int Frequency;
        @SerializedName("RateConsultant")
        public double RateConsultant;
        @SerializedName("RateService")
        public double RateService;
        @SerializedName("Rate")
        public double Rate;
        @SerializedName("CompanyFieldSurveyUserID")
        public int CompanyFieldSurveyUserID;
        @SerializedName("FieldSurveyUserID")
        public int FieldSurveyUserID;
        @SerializedName("Code")
        public int Code;

        public int getTotalMoneyFirst() {
            return TotalMoneyFirst;
        }

        public void setTotalMoneyFirst(int totalMoneyFirst) {
            TotalMoneyFirst = totalMoneyFirst;
        }

        public int getTotalReturn() {
            return TotalReturn;
        }

        public void setTotalReturn(int totalReturn) {
            TotalReturn = totalReturn;
        }

        public int getMoneyConsultant() {
            return MoneyConsultant;
        }

        public void setMoneyConsultant(int moneyConsultant) {
            MoneyConsultant = moneyConsultant;
        }

        public int getMoneyService() {
            return MoneyService;
        }

        public void setMoneyService(int moneyService) {
            MoneyService = moneyService;
        }

        public int getMoneyLoan() {
            return MoneyLoan;
        }

        public void setMoneyLoan(int moneyLoan) {
            MoneyLoan = moneyLoan;
        }

        public String getFromDate() {
            return FromDate;
        }

        public void setFromDate(String fromDate) {
            FromDate = fromDate;
        }

        public String getToDate() {
            return ToDate;
        }

        public void setToDate(String toDate) {
            ToDate = toDate;
        }

        public String getNextDate() {
            return NextDate;
        }

        public void setNextDate(String nextDate) {
            NextDate = nextDate;
        }

        public int getTypeID() {
            return TypeID;
        }

        public void setTypeID(int typeID) {
            TypeID = typeID;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int status) {
            Status = status;
        }

        public int getOwerShopId() {
            return OwerShopId;
        }

        public void setOwerShopId(int owerShopId) {
            OwerShopId = owerShopId;
        }

        public int getAgentLoanID() {
            return AgentLoanID;
        }

        public void setAgentLoanID(int agentLoanID) {
            AgentLoanID = agentLoanID;
        }

        public int getIsThamDinh() {
            return IsThamDinh;
        }

        public void setIsThamDinh(int isThamDinh) {
            IsThamDinh = isThamDinh;
        }

        public int getCountCall() {
            return CountCall;
        }

        public void setCountCall(int countCall) {
            CountCall = countCall;
        }

        public int getLoanID() {
            return LoanID;
        }

        public void setLoanID(int loanID) {
            LoanID = loanID;
        }

        public String getModelPhone() {
            return ModelPhone;
        }

        public void setModelPhone(String modelPhone) {
            ModelPhone = modelPhone;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String createDate) {
            CreateDate = createDate;
        }

        public int getStep() {
            return Step;
        }

        public void setStep(int step) {
            Step = step;
        }

        public String getUtm_source() {
            return utm_source;
        }

        public void setUtm_source(String utm_source) {
            this.utm_source = utm_source;
        }

        public int getSupportID() {
            return SupportID;
        }

        public void setSupportID(int supportID) {
            SupportID = supportID;
        }

        public int getSupportLastID() {
            return SupportLastID;
        }

        public void setSupportLastID(int supportLastID) {
            SupportLastID = supportLastID;
        }

        public int getCustomerCreditId() {
            return CustomerCreditId;
        }

        public void setCustomerCreditId(int customerCreditId) {
            CustomerCreditId = customerCreditId;
        }

        public int getLoanCreditId() {
            return LoanCreditId;
        }

        public void setLoanCreditId(int loanCreditId) {
            LoanCreditId = loanCreditId;
        }

        public int getTypeReceivingMoney() {
            return TypeReceivingMoney;
        }

        public void setTypeReceivingMoney(int typeReceivingMoney) {
            TypeReceivingMoney = typeReceivingMoney;
        }

        public String getNameBanking() {
            return NameBanking;
        }

        public void setNameBanking(String nameBanking) {
            NameBanking = nameBanking;
        }

        public String getNameCardHolder() {
            return NameCardHolder;
        }

        public void setNameCardHolder(String nameCardHolder) {
            NameCardHolder = nameCardHolder;
        }

        public String getNumberCardBanking() {
            return NumberCardBanking;
        }

        public void setNumberCardBanking(String numberCardBanking) {
            NumberCardBanking = numberCardBanking;
        }

        public String getAccountNumberBanking() {
            return AccountNumberBanking;
        }

        public void setAccountNumberBanking(String accountNumberBanking) {
            AccountNumberBanking = accountNumberBanking;
        }

        public String getNote() {
            return Note;
        }

        public void setNote(String note) {
            Note = note;
        }

        public int getTotalMoney() {
            return TotalMoney;
        }

        public void setTotalMoney(int totalMoney) {
            TotalMoney = totalMoney;
        }

        public int getRateType() {
            return RateType;
        }

        public void setRateType(int rateType) {
            RateType = rateType;
        }

        public String getRepresent() {
            return Represent;
        }

        public void setRepresent(String represent) {
            Represent = represent;
        }

        public String getDocument() {
            return Document;
        }

        public void setDocument(String document) {
            Document = document;
        }

        public String getCoordinatorID() {
            return CoordinatorID;
        }

        public void setCoordinatorID(String coordinatorID) {
            CoordinatorID = coordinatorID;
        }

        public int getLoanTime() {
            return LoanTime;
        }

        public void setLoanTime(int loanTime) {
            LoanTime = loanTime;
        }

        public int getFrequency() {
            return Frequency;
        }

        public void setFrequency(int frequency) {
            Frequency = frequency;
        }

        public double getRateConsultant() {
            return RateConsultant;
        }

        public void setRateConsultant(double rateConsultant) {
            RateConsultant = rateConsultant;
        }

        public double getRateService() {
            return RateService;
        }

        public void setRateService(double rateService) {
            RateService = rateService;
        }

        public double getRate() {
            return Rate;
        }

        public void setRate(double rate) {
            Rate = rate;
        }

        public int getCompanyFieldSurveyUserID() {
            return CompanyFieldSurveyUserID;
        }

        public void setCompanyFieldSurveyUserID(int companyFieldSurveyUserID) {
            CompanyFieldSurveyUserID = companyFieldSurveyUserID;
        }

        public int getFieldSurveyUserID() {
            return FieldSurveyUserID;
        }

        public void setFieldSurveyUserID(int fieldSurveyUserID) {
            FieldSurveyUserID = fieldSurveyUserID;
        }

        public int getCode() {
            return Code;
        }

        public void setCode(int code) {
            Code = code;
        }
    }

    public ContractDetailEntity.Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(ContractDetailEntity.Customer customer) {
        Customer = customer;
    }

    public ContractDetailEntity.LoanCredit getLoanCredit() {
        return LoanCredit;
    }

    public void setLoanCredit(ContractDetailEntity.LoanCredit loanCredit) {
        LoanCredit = loanCredit;
    }

    public String getLoanCreditMoneyFeildSale() {
        return LoanCreditMoneyFeildSale;
    }

    public void setLoanCreditMoneyFeildSale(String loanCreditMoneyFeildSale) {
        LoanCreditMoneyFeildSale = loanCreditMoneyFeildSale;
    }

    public String getTotalMoneyConsulant() {
        return TotalMoneyConsulant;
    }

    public void setTotalMoneyConsulant(String totalMoneyConsulant) {
        TotalMoneyConsulant = totalMoneyConsulant;
    }

    public String getTotalMoneyPaymentOnePeriod() {
        return TotalMoneyPaymentOnePeriod;
    }

    public void setTotalMoneyPaymentOnePeriod(String totalMoneyPaymentOnePeriod) {
        TotalMoneyPaymentOnePeriod = totalMoneyPaymentOnePeriod;
    }

    public String getTotalMoneyService() {
        return TotalMoneyService;
    }

    public void setTotalMoneyService(String totalMoneyService) {
        TotalMoneyService = totalMoneyService;
    }

    public String getTotalMoneyAccounting() {
        return TotalMoneyAccounting;
    }

    public void setTotalMoneyAccounting(String totalMoneyAccounting) {
        TotalMoneyAccounting = totalMoneyAccounting;
    }

    public String getTotalMoneyInterest() {
        return TotalMoneyInterest;
    }

    public void setTotalMoneyInterest(String totalMoneyInterest) {
        TotalMoneyInterest = totalMoneyInterest;
    }

    public String getNumberPeriod() {
        return NumberPeriod;
    }

    public void setNumberPeriod(String numberPeriod) {
        NumberPeriod = numberPeriod;
    }

    public String getInterestPaymentDate() {
        return InterestPaymentDate;
    }

    public void setInterestPaymentDate(String interestPaymentDate) {
        InterestPaymentDate = interestPaymentDate;
    }

    public String getFinalSettlementDate() {
        return FinalSettlementDate;
    }

    public void setFinalSettlementDate(String finalSettlementDate) {
        FinalSettlementDate = finalSettlementDate;
    }

    public int getIsThamDinhNha() {
        return IsThamDinhNha;
    }

    public void setIsThamDinhNha(int isThamDinhNha) {
        IsThamDinhNha = isThamDinhNha;
    }

    public int getIsThamDinhCongTy() {
        return IsThamDinhCongTy;
    }

    public void setIsThamDinhCongTy(int isThamDinhCongTy) {
        IsThamDinhCongTy = isThamDinhCongTy;
    }
}
