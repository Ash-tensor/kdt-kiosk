package ac.su.kiosk.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class OrderStatisticDTO {
    private Long orderid;
    private Long price;
    private Long menuid;
    private Boolean gender;
    private Long high;
    private Long low;
    private BigDecimal averageHighLow;
    private String ageGroup;

    // 생성자, getter, setter 생략

    public OrderStatisticDTO(Long orderid, Long price, Long menuid, Boolean gender, Long high, Long low, BigDecimal averageHighLow, String ageGroup) {
        this.orderid = orderid;
        this.price = price;
        this.menuid = menuid;
        this.gender = gender;
        this.high = high;
        this.low = low;
        this.averageHighLow = averageHighLow;
        this.ageGroup = ageGroup;
    }
}

