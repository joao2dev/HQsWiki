package joao2dev.ProjetoHq.Revista;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class HqController {

    @GetMapping("/teste")
    public String teste(){
        return "teste";
    }
}
