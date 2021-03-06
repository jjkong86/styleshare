package styleshare.task.factory.controller;

import styleshare.task.factory.beanFactory.PaymentType;
import styleshare.task.factory.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import styleshare.task.response.ApiCommonResponse;

@RestController
public class PayController {

    PaymentService paymentService;

    public PayController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(value="/{pay}/pay")
    public ApiCommonResponse pay(@PathVariable String pay) {
        return paymentService.prepare(PaymentType.findByName(pay));
    }
}
