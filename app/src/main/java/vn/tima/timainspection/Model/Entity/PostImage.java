package vn.tima.timainspection.Model.Entity;

import java.io.Serializable;

/**
 * Created by hoangdo on 08/11/2016.
 */
public class PostImage implements Serializable {
    int typeId;
    int status;
    int typePost;
    String name;

    public PostImage(int typeId, int status, int typePost, String name) {
        this.typeId = typeId;
        this.status = status;
        this.typePost = typePost;
        this.name = name;
    }

    public PostImage() {
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getTypePost() {
        return typePost;
    }

    public void setTypePost(int typePost) {
        this.typePost = typePost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
