package org.Framework.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Framework.Model.Model;

public interface View {
    void render(Model mdl, HttpServletRequest rqst,HttpServletResponse rsp) throws Exception;
}