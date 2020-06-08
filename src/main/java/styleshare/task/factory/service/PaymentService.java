package styleshare.task.factory.service;

import styleshare.task.factory.beanFactory.PaymentHandlerLocator;
import styleshare.task.factory.beanFactory.PaymentType;
import org.springframework.stereotype.Service;
import styleshare.task.response.ApiCommonResponse;

@Service
public class PaymentService {

    private PaymentHandlerLocator paymentHandlerLocator;

    PaymentService(PaymentHandlerLocator paymentHandlerLocator) {
        this.paymentHandlerLocator = paymentHandlerLocator;
    }

    public ApiCommonResponse prepare(PaymentType paymentType) {
        PayService<?> handler = paymentHandlerLocator.getPaymentHandler(paymentType);
        return handler.prepare(paymentType);
    }
}
