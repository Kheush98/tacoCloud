package sioa.taco_cloud.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.web.bind.support.SessionStatus;
import sioa.taco_cloud.TacoOrder;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OderController {

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String precessOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
        if(errors.hasErrors()){
            return "orderForm";
        }
        log.info("Oder submitted: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
