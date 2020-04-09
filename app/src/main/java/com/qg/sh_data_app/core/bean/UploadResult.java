package com.qg.sh_data_app.core.bean;

public class UploadResult {

    /**
     * message : success
     * code : 1
     * data :
     */

    private String message;
    private String code;
    private String data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
