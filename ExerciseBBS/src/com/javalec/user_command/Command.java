package com.javalec.user_command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	int execute(HttpServletRequest request, HttpServletResponse response);
}
