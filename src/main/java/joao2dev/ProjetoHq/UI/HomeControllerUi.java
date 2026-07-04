package joao2dev.ProjetoHq.UI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControllerUi {
        @GetMapping("/")
        public String home() {
            return "redirect:/auth/ui/login";
        }
    }

