package legacy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemoTestFormController {
    @GetMapping("/showform")
    public String showForm() {
        return "test-form";
    }
    
    @PostMapping("/processForm")
    public String processFrom(@RequestParam("name") String name, Model model) {
        if (name != null) {
            model.addAttribute("message", name.toUpperCase());
        }
        return "helloworld";
    }
}
