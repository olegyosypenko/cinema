package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {
    public abstract String process(HttpServletRequest request, HttpServletResponse response);
}
