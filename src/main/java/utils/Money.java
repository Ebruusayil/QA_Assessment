package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {

    public static BigDecimal parse(String raw) {

        if (raw == null) return BigDecimal.ZERO;

        String cleaned =
                raw.replace("₺", "")
                        .replace("$", "")
                        .replace(",", "")
                        .trim();

        return new BigDecimal(cleaned);
    }


    public static BigDecimal multiply(BigDecimal price, int qty) {
        return price.multiply(BigDecimal.valueOf(qty))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
