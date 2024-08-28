package ac.su.kiosk.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class OrderStatisticDTO {
    private Long orderid;
    private Long price;
    private int menuid;
    private boolean gender;
    private int high;
    private int low;
    private double averageHighLow;

    // 생성자, getter, setter 생략

    public OrderStatisticDTO(Long orderid, Long price, int menuid, Boolean gender, int high, int low, double averageHighLow) {
        this.orderid = orderid;
        this.price = price;
        this.menuid = menuid;
        this.gender = gender;
        this.high = high;
        this.low = low;
        this.averageHighLow = averageHighLow;
    }
}

