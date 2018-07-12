package vn.tima.timainspection.Model.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anhnh-dev on 28/11/2017.
 */

public class CheckList {
    @SerializedName("Name")
    public String Name;
    @SerializedName("lstgroupchildren")
    public List<Lstgroupchildren> lstgroupchildren;

    public static class LstAnswer {
        @SerializedName("AnswerName")
        public String AnswerName;
        @SerializedName("IsCorrect")
        public int IsCorrect;
        @SerializedName("IsEdit")
        public int IsEdit;

        public String getAnswerName() {
            return AnswerName;
        }

        public void setAnswerName(String answerName) {
            AnswerName = answerName;
        }

        public int getIsCorrect() {
            return IsCorrect;
        }

        public void setIsCorrect(int isCorrect) {
            IsCorrect = isCorrect;
        }

        public int getIsEdit() {
            return IsEdit;
        }

        public void setIsEdit(int isEdit) {
            IsEdit = isEdit;
        }
    }

    public static class LstGroupAnswer {
        @SerializedName("titleAnswer")
        public String titleAnswer;
        @SerializedName("typeinput")
        public String typeinput;
        @SerializedName("lstAnswer")
        public List<LstAnswer> lstAnswer;

        public String getTitleAnswer() {
            return titleAnswer;
        }

        public void setTitleAnswer(String titleAnswer) {
            this.titleAnswer = titleAnswer;
        }

        public String getTypeinput() {
            return typeinput;
        }

        public void setTypeinput(String typeinput) {
            this.typeinput = typeinput;
        }

        public List<LstAnswer> getLstAnswer() {
            return lstAnswer;
        }

        public void setLstAnswer(List<LstAnswer> lstAnswer) {
            this.lstAnswer = lstAnswer;
        }
    }

    public static class Listquestion {
        @SerializedName("QuestionName")
        public String QuestionName;
        @SerializedName("lstGroupAnswer")
        public List<LstGroupAnswer> lstGroupAnswer;
        @SerializedName("IsEdit")
        public int IsEdit;
        @SerializedName("IsComment")
        public int IsComment;
        @SerializedName("Comment")
        public String Comment;

        public int getIsEdit() {
            return IsEdit;
        }

        public void setIsEdit(int isEdit) {
            IsEdit = isEdit;
        }

        public String getQuestionName() {
            return QuestionName;
        }

        public void setQuestionName(String questionName) {
            QuestionName = questionName;
        }

        public List<LstGroupAnswer> getLstGroupAnswer() {
            return lstGroupAnswer;
        }

        public void setLstGroupAnswer(List<LstGroupAnswer> lstGroupAnswer) {
            this.lstGroupAnswer = lstGroupAnswer;
        }

        public int getIsComment() {
            return IsComment;
        }

        public void setIsComment(int isComment) {
            IsComment = isComment;
        }

        public String getComment() {
            return Comment;
        }

        public void setComment(String comment) {
            Comment = comment;
        }
    }

    public static class Lstgroupchildren {
        @SerializedName("IsRequire")
        public boolean IsRequire;
        @SerializedName("Note")
        public String Note;
        @SerializedName("groupchildrenName")
        public String groupchildrenName;
        @SerializedName("listquestion")
        public List<Listquestion> listquestion;

        public boolean isRequire() {
            return IsRequire;
        }

        public void setRequire(boolean require) {
            IsRequire = require;
        }

        public String getNote() {
            return Note;
        }

        public void setNote(String note) {
            Note = note;
        }

        public String getGroupchildrenName() {
            return groupchildrenName;
        }

        public void setGroupchildrenName(String groupchildrenName) {
            this.groupchildrenName = groupchildrenName;
        }

        public List<Listquestion> getListquestion() {
            return listquestion;
        }

        public void setListquestion(List<Listquestion> listquestion) {
            this.listquestion = listquestion;
        }
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Lstgroupchildren> getLstgroupchildren() {
        return lstgroupchildren;
    }

    public void setLstgroupchildren(List<Lstgroupchildren> lstgroupchildren) {
        this.lstgroupchildren = lstgroupchildren;
    }

    //    @SerializedName("Name")
//    public String Name;
//    @SerializedName("lstgroupchildren")
//    public List<Lstgroupchildren> lstgroupchildren;
//
//    public static class LstGroupAnswer {
//
//        @SerializedName("titleAnswer")
//        public String titleAnswer;
//        @SerializedName("typeinput")
//        public String typeinput;
//        @SerializedName("lstAnswer")
//        public List<LstAnswer> lstAnswer;
//
//        public String getTitleAnswer() {
//            return titleAnswer;
//        }
//
//        public void setTitleAnswer(String titleAnswer) {
//            this.titleAnswer = titleAnswer;
//        }
//
//        public String getTypeinput() {
//            return typeinput;
//        }
//
//        public void setTypeinput(String typeinput) {
//            this.typeinput = typeinput;
//        }
//
//        public List<LstAnswer> getLstAnswer() {
//            return lstAnswer;
//        }
//
//        public void setLstAnswer(List<LstAnswer> lstAnswer) {
//            this.lstAnswer = lstAnswer;
//        }
//    }
//    public static class LstAnswer {
//        @SerializedName("AnswerName")
//        public String AnswerName;
//        @SerializedName("IsCorrect")
//        public int IsCorrect;
//
//        public String getAnswerName() {
//            return AnswerName;
//        }
//
//        public void setAnswerName(String answerName) {
//            AnswerName = answerName;
//        }
//
//        public int getIsCorrect() {
//            return IsCorrect;
//        }
//
//        public void setIsCorrect(int isCorrect) {
//            IsCorrect = isCorrect;
//        }
//    }
//
//    public static class Listquestion {
//        @SerializedName("QuestionName")
//        public String QuestionName;
//        @SerializedName("lstGroupAnswer")
//        public List<LstGroupAnswer> lstGroupAnswer;
//        @SerializedName("IsComment")
//        public int IsComment;
//
//        public String getQuestionName() {
//            return QuestionName;
//        }
//
//        public void setQuestionName(String questionName) {
//            QuestionName = questionName;
//        }
//
//        public List<LstGroupAnswer> getLstGroupAnswer() {
//            return lstGroupAnswer;
//        }
//
//        public void setLstGroupAnswer(List<LstGroupAnswer> lstGroupAnswer) {
//            this.lstGroupAnswer = lstGroupAnswer;
//        }
//
//        public int getIsComment() {
//            return IsComment;
//        }
//
//        public void setIsComment(int isComment) {
//            IsComment = isComment;
//        }
//    }
//
//    public static class Lstgroupchildren {
//        @SerializedName("Note")
//        public String Note;
//        @SerializedName("groupchildrenName")
//        public String groupchildrenName;
//        @SerializedName("listquestion")
//        public List<Listquestion> listquestion;
//
//        public String getNote() {
//            return Note;
//        }
//
//        public void setNote(String note) {
//            Note = note;
//        }
//
//        public String getGroupchildrenName() {
//            return groupchildrenName;
//        }
//
//        public void setGroupchildrenName(String groupchildrenName) {
//            this.groupchildrenName = groupchildrenName;
//        }
//
//        public List<Listquestion> getListquestion() {
//            return listquestion;
//        }
//
//        public void setListquestion(List<Listquestion> listquestion) {
//            this.listquestion = listquestion;
//        }
//    }
//
//    public String getName() {
//        return Name;
//    }
//
//    public void setName(String name) {
//        Name = name;
//    }
//
//    public List<Lstgroupchildren> getLstgroupchildren() {
//        return lstgroupchildren;
//    }
//
//    public void setLstgroupchildren(List<Lstgroupchildren> lstgroupchildren) {
//        this.lstgroupchildren = lstgroupchildren;
//    }
}

