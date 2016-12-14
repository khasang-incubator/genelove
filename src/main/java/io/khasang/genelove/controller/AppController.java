package io.khasang.genelove.controller;

import io.khasang.genelove.model.InsertTable;
import io.khasang.genelove.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {


        @Autowired
        Message message;
        @Autowired
        InsertTable insertTable;

        @RequestMapping("/")
        public String hello(Model model){
            model.addAttribute("hello", message.getMessageOut());
            return "hello";
        }

        @RequestMapping("/insert")
        public String insertTable(Model model){
            model.addAttribute("create", insertTable.insertTableStatus());
            return "insert";
        }

}
