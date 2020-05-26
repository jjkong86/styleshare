package styleshare.task.factory.beanFactory;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PaymentType {
    KAKAOPAY("kakao"), NAVERPAY("naver"), EMPTY("");

    String name;

    PaymentType(String name) {
        this.name = name;
    }

    public static PaymentType findByName(String name) {
        return Arrays.stream(PaymentType.values())
                .filter(paymentType -> paymentType.getName().equals(name))
                .findFirst().orElse(EMPTY);
    }
}
