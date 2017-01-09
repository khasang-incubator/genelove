package io.khasang.genelove.controller;

import io.khasang.genelove.entity.Question;
import io.khasang.genelove.model.CreateTable;
import io.khasang.genelove.model.Message;
import io.khasang.genelove.model.NewClass;
import io.khasang.genelove.model.SqlExample;
import io.khasang.genelove.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class AppController {
    @Autowired
    Message message;
    @Autowired
    NewClass message2;
    @Autowired
    CreateTable createTable;
    @Autowired
    SqlExample sqlExample;
    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(Model model){
        model.addAttribute("hello", message.getMessageOut());
        model.addAttribute("hello", message2.getMessOut());
        return "hello";
    }

    @RequestMapping(value = "/admin/create", method = RequestMethod.GET)
    public String createTable(Model model){
        model.addAttribute("create", createTable.createTableStatus());
        return "create";
    }

    @RequestMapping(value = "/db/insert", method = RequestMethod.GET)
    public String sqlInsertExecute(Model model){
        model.addAttribute("sql", sqlExample.sqlInsert());
        return "sqlexample";
    }

    @RequestMapping(value = "/sql/join", method = RequestMethod.GET)
    public String sqlJoinExecute(Model model){
        model.addAttribute("sql", sqlExample.sqlJoin());
        return "sqlexample";
    }

    @RequestMapping(value = "/sql/join2", method = RequestMethod.GET)
    public String sqlJoin2Execute(Model model) {
        model.addAttribute("sql", sqlExample.sqlJoin2());
        return "sqlexample";
    }

    @RequestMapping(value = "/sql/attached", method = RequestMethod.GET)
    public String sqlAttachedExecute(Model model) {
        model.addAttribute("sql", sqlExample.sqlAttached());
        return "sqlexample";
    }

    @RequestMapping(value = "/sql/case")
    public String sqlCaseExecute(Model model) {
        model.addAttribute("sql", sqlExample.sqlCase());
        return "sqlexample";
    }

    @RequestMapping(value = {"hello/{name}"}, method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("encode");
        modelAndView.addObject("crypt", new BCryptPasswordEncoder().encode(name));
        return modelAndView;
    }

    @RequestMapping(value = "/db/addQuestion", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object addQuestion(@RequestBody Question question, HttpServletResponse response) {
        try {
            questionService.addQuestion(question);
            return question;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Error adding question: " + e.getMessage();
        }
    }

    @RequestMapping(value = "/db/addQuestion2", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public Object addQuestion2(@RequestBody Question question, HttpServletResponse response) {
        try {
            questionService.addQuestion(question);
            return question;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Error adding question: " + e.getMessage();
        }
    }

    @RequestMapping(value = "/db/allQuestion", method = RequestMethod.GET)
    public String allQuestion(Model model) {
        List<Question> list = questionService.getQuetionList();
        model.addAttribute("allQuestion", list);
        return "questions";
    }

    @RequestMapping(value = "/db/deleteQuestion", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public Object deleteQuestion(@RequestBody Question question, HttpServletResponse response) {
        try {
            questionService.deleteQuestion(question);
            return question;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Error deleting question: " + e.getMessage();
        }
    }

    @RequestMapping(value = "/db/updateQuestion", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object updateQuestion(@RequestBody Question question, HttpServletResponse response) {
        try {
            questionService.updateQuestion(question);
            return question;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Error updating question: " + e.getMessage();
        }
    }
}
