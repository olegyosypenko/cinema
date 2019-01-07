package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;

public abstract class Command {
    public abstract String process(HttpServletRequest request);
}
