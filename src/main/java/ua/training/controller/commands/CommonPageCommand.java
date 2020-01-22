package ua.training.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class CommonPageCommand extends Command {
    private final String page;

    public CommonPageCommand(String page) {
        this.page = page;
    }

    @Override
    public String process(HttpServletRequest request) {
        return page;
    }
}
