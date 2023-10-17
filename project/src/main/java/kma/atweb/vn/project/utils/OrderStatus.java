package kma.atweb.vn.project.utils;

public enum OrderStatus {
    INIT("Đang chờ xác nhận"),
    CONFIRM("Đã xác nhận"),
    COMPLETE("Hoàn thành"),

    ;

    public String desc;
    OrderStatus(String desc) {
        this.desc = desc;
    }
}
