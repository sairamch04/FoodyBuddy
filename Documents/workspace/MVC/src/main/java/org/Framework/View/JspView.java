package org.Framework.View;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Framework.Model.Model;

public class JspView implements View {
	String name="";
	
	public JspView(String  name) {
		this.name = name;
	}	
	public void render(Model mdl, HttpServletRequest rqst,HttpServletResponse rsp) throws Exception {
		try{
			RequestDispatcher rd = rqst.getRequestDispatcher("/" + name + ".jsp");
			loadModel(mdl, rqst, rsp);
			rd.forward(rqst,rsp);
		}
		catch(Exception ex){
			throw new Exception("No such jsp View found: "+ name );
		}
	}
	/**
	 * Add the entries of Model into the HTTP request so that they can be used in views
	 */

	protected void loadModel(Model mdl, HttpServletRequest rqst,HttpServletResponse rsp) {
		
		Map<String, Object> map =mdl.getMap();
		for (Map.Entry<String, Object> e: map.entrySet()) {
			String  key = e.getKey();
			Object  value = e.getValue();
			rqst.setAttribute(key , value);
		}
	}
}