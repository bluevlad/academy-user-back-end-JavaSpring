package com.academy.teacher.service;

import com.academy.common.CommonVO;

public class TeacherIntroVO extends CommonVO {
    private static final long serialVersionUID = 1L;

    // Teacher search parameters
    private String searchCategoryCode;
    private String searchSubjectCode;
    private String searchSubjectNm;
    private String searchUserId;
    private String searchUserNm;
    private String searchSeriesCode;
    private String searchPrfCode;
    private String searchLearningCd;
    private String topMenuType;
    private String topMenuGnb;
    private String topMenu;
    private String topMenuName;
    private String menuId;

    // Teacher info
    private String teacherId;
    private String teacherNm;
    private String title;
    private String profile;
    private String profileSummary;
    private String bookLog;
    private String bookLogSummary;
    private String yplan;
    private String lecinfo;
    private String pic1;
    private String pic2;
    private String pic3;
    private String pic4;
    private String pic5;
    private String pic6;
    private String pic7;
    private String pic8;
    private String pic9;
    private String pic10;
    private String onUrl;
    private String brdYn;
    private String prfBrdOn;

    // Offline teacher info
    private String offProfile;
    private String offTitle;
    private String offYplan;
    private String offLecinfo;
    private String offPic1;
    private String offPic2;
    private String offPic3;
    private String offPic4;
    private String offPic5;
    private String offPic6;
    private String offPic7;
    private String offPic8;
    private String offPic9;
    private String offPic10;
    private String offUrl;
    private String offBrdYn;
    private String prfBrdOf;

    // Lecture info
    private String lecCode;
    private String lecTypeChoice;
    private String learningCd;
    private String subjectTitle;
    private String subjectSjtCd;
    private String subjectPrice;
    private String subjectRealPrice;
    private String subjectDiscount;
    private String subjectPeriod;
    private String startDate;
    private String endDate;

    // Book info
    private String rscId;
    private String bookNm;
    private String bookPublishers;
    private String coverType;

    // Cart parameters
    private String cmd;
    private String lecCodeArr;
    private String movieType;
    private String rscType;
    private String kindType;

    // Filter parameters
    private String leftSel;
    private String temp;
    private String searchType;
    private String searchText;

    public String getSearchCategoryCode() {
        return searchCategoryCode;
    }

    public void setSearchCategoryCode(String searchCategoryCode) {
        this.searchCategoryCode = searchCategoryCode;
    }

    public String getSearchSubjectCode() {
        return searchSubjectCode;
    }

    public void setSearchSubjectCode(String searchSubjectCode) {
        this.searchSubjectCode = searchSubjectCode;
    }

    public String getSearchSubjectNm() {
        return searchSubjectNm;
    }

    public void setSearchSubjectNm(String searchSubjectNm) {
        this.searchSubjectNm = searchSubjectNm;
    }

    public String getSearchUserId() {
        return searchUserId;
    }

    public void setSearchUserId(String searchUserId) {
        this.searchUserId = searchUserId;
    }

    public String getSearchUserNm() {
        return searchUserNm;
    }

    public void setSearchUserNm(String searchUserNm) {
        this.searchUserNm = searchUserNm;
    }

    public String getSearchSeriesCode() {
        return searchSeriesCode;
    }

    public void setSearchSeriesCode(String searchSeriesCode) {
        this.searchSeriesCode = searchSeriesCode;
    }

    public String getSearchPrfCode() {
        return searchPrfCode;
    }

    public void setSearchPrfCode(String searchPrfCode) {
        this.searchPrfCode = searchPrfCode;
    }

    public String getSearchLearningCd() {
        return searchLearningCd;
    }

    public void setSearchLearningCd(String searchLearningCd) {
        this.searchLearningCd = searchLearningCd;
    }

    public String getTopMenuType() {
        return topMenuType;
    }

    public void setTopMenuType(String topMenuType) {
        this.topMenuType = topMenuType;
    }

    public String getTopMenuGnb() {
        return topMenuGnb;
    }

    public void setTopMenuGnb(String topMenuGnb) {
        this.topMenuGnb = topMenuGnb;
    }

    public String getTopMenu() {
        return topMenu;
    }

    public void setTopMenu(String topMenu) {
        this.topMenu = topMenu;
    }

    public String getTopMenuName() {
        return topMenuName;
    }

    public void setTopMenuName(String topMenuName) {
        this.topMenuName = topMenuName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherNm() {
        return teacherNm;
    }

    public void setTeacherNm(String teacherNm) {
        this.teacherNm = teacherNm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getProfileSummary() {
        return profileSummary;
    }

    public void setProfileSummary(String profileSummary) {
        this.profileSummary = profileSummary;
    }

    public String getBookLog() {
        return bookLog;
    }

    public void setBookLog(String bookLog) {
        this.bookLog = bookLog;
    }

    public String getBookLogSummary() {
        return bookLogSummary;
    }

    public void setBookLogSummary(String bookLogSummary) {
        this.bookLogSummary = bookLogSummary;
    }

    public String getYplan() {
        return yplan;
    }

    public void setYplan(String yplan) {
        this.yplan = yplan;
    }

    public String getLecinfo() {
        return lecinfo;
    }

    public void setLecinfo(String lecinfo) {
        this.lecinfo = lecinfo;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }

    public String getPic4() {
        return pic4;
    }

    public void setPic4(String pic4) {
        this.pic4 = pic4;
    }

    public String getPic5() {
        return pic5;
    }

    public void setPic5(String pic5) {
        this.pic5 = pic5;
    }

    public String getPic6() {
        return pic6;
    }

    public void setPic6(String pic6) {
        this.pic6 = pic6;
    }

    public String getPic7() {
        return pic7;
    }

    public void setPic7(String pic7) {
        this.pic7 = pic7;
    }

    public String getPic8() {
        return pic8;
    }

    public void setPic8(String pic8) {
        this.pic8 = pic8;
    }

    public String getPic9() {
        return pic9;
    }

    public void setPic9(String pic9) {
        this.pic9 = pic9;
    }

    public String getPic10() {
        return pic10;
    }

    public void setPic10(String pic10) {
        this.pic10 = pic10;
    }

    public String getOnUrl() {
        return onUrl;
    }

    public void setOnUrl(String onUrl) {
        this.onUrl = onUrl;
    }

    public String getBrdYn() {
        return brdYn;
    }

    public void setBrdYn(String brdYn) {
        this.brdYn = brdYn;
    }

    public String getPrfBrdOn() {
        return prfBrdOn;
    }

    public void setPrfBrdOn(String prfBrdOn) {
        this.prfBrdOn = prfBrdOn;
    }

    public String getOffProfile() {
        return offProfile;
    }

    public void setOffProfile(String offProfile) {
        this.offProfile = offProfile;
    }

    public String getOffTitle() {
        return offTitle;
    }

    public void setOffTitle(String offTitle) {
        this.offTitle = offTitle;
    }

    public String getOffYplan() {
        return offYplan;
    }

    public void setOffYplan(String offYplan) {
        this.offYplan = offYplan;
    }

    public String getOffLecinfo() {
        return offLecinfo;
    }

    public void setOffLecinfo(String offLecinfo) {
        this.offLecinfo = offLecinfo;
    }

    public String getOffPic1() {
        return offPic1;
    }

    public void setOffPic1(String offPic1) {
        this.offPic1 = offPic1;
    }

    public String getOffPic2() {
        return offPic2;
    }

    public void setOffPic2(String offPic2) {
        this.offPic2 = offPic2;
    }

    public String getOffPic3() {
        return offPic3;
    }

    public void setOffPic3(String offPic3) {
        this.offPic3 = offPic3;
    }

    public String getOffPic4() {
        return offPic4;
    }

    public void setOffPic4(String offPic4) {
        this.offPic4 = offPic4;
    }

    public String getOffPic5() {
        return offPic5;
    }

    public void setOffPic5(String offPic5) {
        this.offPic5 = offPic5;
    }

    public String getOffPic6() {
        return offPic6;
    }

    public void setOffPic6(String offPic6) {
        this.offPic6 = offPic6;
    }

    public String getOffPic7() {
        return offPic7;
    }

    public void setOffPic7(String offPic7) {
        this.offPic7 = offPic7;
    }

    public String getOffPic8() {
        return offPic8;
    }

    public void setOffPic8(String offPic8) {
        this.offPic8 = offPic8;
    }

    public String getOffPic9() {
        return offPic9;
    }

    public void setOffPic9(String offPic9) {
        this.offPic9 = offPic9;
    }

    public String getOffPic10() {
        return offPic10;
    }

    public void setOffPic10(String offPic10) {
        this.offPic10 = offPic10;
    }

    public String getOffUrl() {
        return offUrl;
    }

    public void setOffUrl(String offUrl) {
        this.offUrl = offUrl;
    }

    public String getOffBrdYn() {
        return offBrdYn;
    }

    public void setOffBrdYn(String offBrdYn) {
        this.offBrdYn = offBrdYn;
    }

    public String getPrfBrdOf() {
        return prfBrdOf;
    }

    public void setPrfBrdOf(String prfBrdOf) {
        this.prfBrdOf = prfBrdOf;
    }

    public String getLecCode() {
        return lecCode;
    }

    public void setLecCode(String lecCode) {
        this.lecCode = lecCode;
    }

    public String getLecTypeChoice() {
        return lecTypeChoice;
    }

    public void setLecTypeChoice(String lecTypeChoice) {
        this.lecTypeChoice = lecTypeChoice;
    }

    public String getLearningCd() {
        return learningCd;
    }

    public void setLearningCd(String learningCd) {
        this.learningCd = learningCd;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getSubjectSjtCd() {
        return subjectSjtCd;
    }

    public void setSubjectSjtCd(String subjectSjtCd) {
        this.subjectSjtCd = subjectSjtCd;
    }

    public String getSubjectPrice() {
        return subjectPrice;
    }

    public void setSubjectPrice(String subjectPrice) {
        this.subjectPrice = subjectPrice;
    }

    public String getSubjectRealPrice() {
        return subjectRealPrice;
    }

    public void setSubjectRealPrice(String subjectRealPrice) {
        this.subjectRealPrice = subjectRealPrice;
    }

    public String getSubjectDiscount() {
        return subjectDiscount;
    }

    public void setSubjectDiscount(String subjectDiscount) {
        this.subjectDiscount = subjectDiscount;
    }

    public String getSubjectPeriod() {
        return subjectPeriod;
    }

    public void setSubjectPeriod(String subjectPeriod) {
        this.subjectPeriod = subjectPeriod;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRscId() {
        return rscId;
    }

    public void setRscId(String rscId) {
        this.rscId = rscId;
    }

    public String getBookNm() {
        return bookNm;
    }

    public void setBookNm(String bookNm) {
        this.bookNm = bookNm;
    }

    public String getBookPublishers() {
        return bookPublishers;
    }

    public void setBookPublishers(String bookPublishers) {
        this.bookPublishers = bookPublishers;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getLecCodeArr() {
        return lecCodeArr;
    }

    public void setLecCodeArr(String lecCodeArr) {
        this.lecCodeArr = lecCodeArr;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getRscType() {
        return rscType;
    }

    public void setRscType(String rscType) {
        this.rscType = rscType;
    }

    public String getKindType() {
        return kindType;
    }

    public void setKindType(String kindType) {
        this.kindType = kindType;
    }

    public String getLeftSel() {
        return leftSel;
    }

    public void setLeftSel(String leftSel) {
        this.leftSel = leftSel;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
