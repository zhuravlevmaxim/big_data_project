package ru.project.big_data.geo_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.big_data.geo_service.producer.ServiceProducer;

//TODO
@Controller
public class StartAndStopController {

    private ServiceProducer serviceProducer;
    private String initText = "PLEASE PUSH START BUTTON FOR START PRODUCER!";

    @Autowired
    public StartAndStopController(ServiceProducer serviceProducer) {
        this.serviceProducer = serviceProducer;
    }

    @RequestMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("text", initText);
        return "index";
    }

    @RequestMapping("/start")
    public String startProducer(Model model) {
        model.addAttribute("text",
                serviceProducer.sendData(true)
        );
        return "index";
    }

    @RequestMapping("/stop")
    public String stopProducer(Model model) {
        model.addAttribute("text",
                serviceProducer.sendData(false)
        );
        return "index";
    }
}
