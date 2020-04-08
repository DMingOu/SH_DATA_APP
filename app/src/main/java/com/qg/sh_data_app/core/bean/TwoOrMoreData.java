package com.qg.sh_data_app.core.bean;

import java.util.List;

/**
 * 两天及以上的迁移数据显示界面
 */

public class TwoOrMoreData {

    /**
     * message : success
     * code : 1
     * data : [{"studentName":"张三","studentId":"3118005342","migrate":[{"from":{"lng":"113.12312","lat":"23.123123","time":"2020-01-01","city":"广州市"},"to":{"lng":"113.12312","lat":"23.123123","time":"2020-01-02","city":"北京市"}}]}]
     */

    private String message;
    private String code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * studentName : 张三
         * studentId : 3118005342
         * migrate : [{"from":{"lng":"113.12312","lat":"23.123123","time":"2020-01-01","city":"广州市"},"to":{"lng":"113.12312","lat":"23.123123","time":"2020-01-02","city":"北京市"}}]
         */

        private String studentName;
        private String studentId;
        private List<MigrateBean> migrate;

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public List<MigrateBean> getMigrate() {
            return migrate;
        }

        public void setMigrate(List<MigrateBean> migrate) {
            this.migrate = migrate;
        }

        public static class MigrateBean {
            /**
             * from : {"lng":"113.12312","lat":"23.123123","time":"2020-01-01","city":"广州市"}
             * to : {"lng":"113.12312","lat":"23.123123","time":"2020-01-02","city":"北京市"}
             */

            private FromBean from;
            private ToBean to;

            public FromBean getFrom() {
                return from;
            }

            public void setFrom(FromBean from) {
                this.from = from;
            }

            public ToBean getTo() {
                return to;
            }

            public void setTo(ToBean to) {
                this.to = to;
            }

            public static class FromBean {
                /**
                 * lng : 113.12312
                 * lat : 23.123123
                 * time : 2020-01-01
                 * city : 广州市
                 */

                private String lng;
                private String lat;
                private String time;
                private String city;

                public String getLng() {
                    return lng;
                }

                public void setLng(String lng) {
                    this.lng = lng;
                }

                public String getLat() {
                    return lat;
                }

                public void setLat(String lat) {
                    this.lat = lat;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }
            }

            public static class ToBean {
                /**
                 * lng : 113.12312
                 * lat : 23.123123
                 * time : 2020-01-02
                 * city : 北京市
                 */

                private String lng;
                private String lat;
                private String time;
                private String city;

                public String getLng() {
                    return lng;
                }

                public void setLng(String lng) {
                    this.lng = lng;
                }

                public String getLat() {
                    return lat;
                }

                public void setLat(String lat) {
                    this.lat = lat;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }
            }
        }
    }
}
