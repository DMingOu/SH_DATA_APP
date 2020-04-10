package com.qg.sh_data_app.core.bean;

import java.util.List;

/**
 * 两天及以上的迁移数据显示界面
 */

public class TwoOrMoreData {


    /**
     * message : success
     * code : 1
     * data : [{"studentName":"吴迎岗","studentId":"12345678902","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"李泽创","studentId":"12345678903","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"张鸿彬","studentId":"12345678901","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"李佳明","studentId":"3116004905","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"李梓昊","studentId":"3116004737","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"张展鑫","studentId":"2111905218","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"詹祺豪","studentId":"3116008805","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"郭宗煜","studentId":"2111705003","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"苏熙杰","studentId":"3116004969","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"何华仙","studentId":"3116005133","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"陈悦源","studentId":"3117005020","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"骆进豪","studentId":"3116004968","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"林建炜","studentId":"3117004889","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"黄敦鸿","studentId":"3118005049","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"唐盛俊","studentId":"3117005042","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"黄华","studentId":"3118005050","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"林德泽","studentId":"3117004620","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"黄源钦","studentId":"3118005052","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"吴梓豪","studentId":"2111905219","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"赖学程","studentId":"3118005096","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"何国涛","studentId":"3119005094","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"简露","studentId":"3216005123","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"李东阳","studentId":"3118005055","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"陆冰红","studentId":"3218004712","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"李凤仪","studentId":"3218004710","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"卢浚滨","studentId":"3119004971","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"陈俊伟","studentId":"2111805132","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"刘雅卿","studentId":"3218004711","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"陈静蓓","studentId":"3219005353","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"费显耀","studentId":"3119005092","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"邓丹丹","studentId":"3219005354","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"朱源彬","studentId":"3116005122","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"张勇","studentId":"3117004457","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"梁景涛","studentId":"3117004619","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"卢悦盛","studentId":"3117004623","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]},{"studentName":"林胤","studentId":"2121905161","migrate":[{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]}]
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
         * studentName : 吴迎岗
         * studentId : 12345678902
         * migrate : [{"from":{"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"},"to":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}},{"from":{"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"},"to":{"lng":114.30539,"lat":30.593098,"time":"2020-04-03","city":"湖北省武汉市"}}]
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
             * from : {"lng":112.93881,"lat":28.228209,"time":"2020-04-01","city":"湖南省长沙市"}
             * to : {"lng":121.4737,"lat":31.230415,"time":"2020-04-02","city":"上海市"}
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
                 * lng : 112.93881
                 * lat : 28.228209
                 * time : 2020-04-01
                 * city : 湖南省长沙市
                 */

                private double lng;
                private double lat;
                private String time;
                private String city;

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
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
                 * lng : 121.4737
                 * lat : 31.230415
                 * time : 2020-04-02
                 * city : 上海市
                 */

                private double lng;
                private double lat;
                private String time;
                private String city;

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
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
