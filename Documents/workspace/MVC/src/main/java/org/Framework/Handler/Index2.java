package org.Framework.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;

import org.Framework.Model.Model;
import org.Framework.View.JspView;
/**
 * A simple example where <b> Index2 </b> implemets AppContainer also the view it renders is <b> Index2 </b>
 */
public class Index2 implements AppController {
	
	public void handleGet(String jspViewName,HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//Create the model
		Model mdl = new Model();
		JspView view = new JspView(jspViewName);
		view.render(mdl, req, resp);		
		
	}
	public void handlePost(String jspViewName,HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//Create the model
		Model mdl = new Model();
		JspView view = new JspView(jspViewName);
		view.render(mdl, req, resp);		
		
	}
	

}
