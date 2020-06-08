package styleshare.task.factory.beanFactory;

import styleshare.task.factory.service.PayService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class PaymentHandlerLocator {

    private ListableBeanFactory beanFactory;

    public PaymentHandlerLocator(ListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public PayService<?> getPaymentHandler(PaymentType paymentType) {
        String beanName = paymentType.toString();
        try {
            return beanFactory.getBean(beanName, PayService.class);
        } catch (BeansException ex) {
            throw new RuntimeException(beanName + "은/는 지원하지 않는 결제 타입 입니다.");
        }
    }
}