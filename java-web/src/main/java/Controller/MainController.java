package Controller;


import Service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//控制类
@Controller
public class MainController {
    @Autowired(required = false)
    private MainService mainService;

    @RequestMapping("/test.do")
    public void TestConnection(){
        System.out.println("test success");
    }
}
