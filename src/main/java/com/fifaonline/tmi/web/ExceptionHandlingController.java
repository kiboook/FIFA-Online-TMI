package com.fifaonline.tmi.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ExceptionHandlingController implements ErrorController {

    @RequestMapping(value = "/error")
    public String handleError(HttpServletRequest request, Model model) {

        // 에러 코드 획득
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int statusCode = Integer.parseInt(status.toString());

        // 404 error
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            return "/error/404";
        }

        // 500 error
        if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return "/error/500";
        }

        return "/error/error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}