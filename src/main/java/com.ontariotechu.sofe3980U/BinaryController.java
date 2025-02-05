package com.ontariotechu.sofe3980U;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BinaryController {

    @GetMapping("/")
    public String calculatorPage(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
                                 Model model) {
        model.addAttribute("operand1", operand1);
        model.addAttribute("operand1Focused", !operand1.isEmpty());
        return "calculator";
    }

    @PostMapping("/")
    public String calculate(@RequestParam(name = "operand1") String operand1,
                            @RequestParam(name = "operator") String operator,
                            @RequestParam(name = "operand2") String operand2,
                            Model model) {
        Binary num1 = new Binary(operand1);
        Binary num2 = new Binary(operand2);
        Binary result;

        switch (operator) {
            case "+":
                result = Binary.add(num1, num2);
                break;
            case "*":
                result = Binary.multiply(num1, num2);
                break;
            case "&":
                result = Binary.and(num1, num2);
                break;
            case "|":
                result = Binary.or(num1, num2);
                break;
            default:
                result = new Binary("0");
        }

        model.addAttribute("operand1", operand1);
        model.addAttribute("operand2", operand2);
        model.addAttribute("operator", operator);
        model.addAttribute("result", result.getValue());

        return "result"; 
    }
}
