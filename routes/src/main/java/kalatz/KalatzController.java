package kalatz;

//import org.springframework.web.servlet.mvc.Controller;

//@Controller

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kalatz")
public class KalatzController {

    @GetMapping()
    public String hello() {
        return "Hello, World!";
    }

}
