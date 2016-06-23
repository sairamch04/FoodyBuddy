package org.Framework.Handler;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Framework.Model.Model;
/**
 * Implement this interface to setup the Model and render to the view
 * The name of the class which implements this Interface should be same as the view it renders to
 */
public interface AppController {	
	public void handleGet(String view , HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public void handlePost(String view , HttpServletRequest req, HttpServletResponse resp) throws Exception;
	
}
