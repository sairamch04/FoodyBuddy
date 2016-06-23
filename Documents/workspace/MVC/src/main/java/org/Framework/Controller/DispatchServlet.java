package org.Framework.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;

import org.Framework.Handler.*;

public class DispatchServlet extends HttpServlet {
	Map<String,String> pathToViewname;
	String viewsHomeDir;
	/**
	 * Initialize the url-mappings here 
	 * Map.key -> The url
	 * Map.value -> corresponding view
	 */
	public void init() {
		pathToViewname= new HashMap<String,String>();
		viewsHomeDir = "org.Framework.Handler";		
		pathToViewname.put("/hello", "Index2");
		pathToViewname.put("/", "Index");		
	}
	
    public void doGet(HttpServletRequest rqst, HttpServletResponse rsp)
            throws ServletException, IOException {
        dispatchReply("GET",rqst, rsp);
    }

    public void doPost(HttpServletRequest rqst, HttpServletResponse rsp)
            throws ServletException, IOException {
        dispatchReply("POST", rqst, rsp);
    }
    /**
     * 
     * @return the viewName corresponding to url
     */
    private String  getJspFileName(String urlPath){
    	if(urlPath == null){
        	urlPath ="/";
        }
        return pathToViewname.get(urlPath); 
    }
    /**
     * Create the object based on classname at runtime
     */
    private AppController getObject(String className) throws Exception{
    	Object  obj = Class.forName(viewsHomeDir + "." + className).newInstance();
    	return (AppController)obj;
    	
    }
    /**
     * routes the request to the corresponding appContainer to handle request
     */
    public void dispatchReply(String method , HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String urlPath = req.getPathInfo();
        String jspViewName =getJspFileName(urlPath);
        
        if(jspViewName == null){
        	PrintWriter out = resp.getWriter(); 
			out.print("Error: No view mapped to this URL:" +  urlPath );
			return;
        }        
        else{
        	try{            
        		AppController appController = getObject(jspViewName);
        		if(method == "GET"){
        			appController.handleGet(jspViewName, req, resp);
            		
            	}
            	else if(method == "POST"){
            		appController.handlePost(jspViewName, req, resp);
            	}  	           	            
	            
        	}
        	catch(Exception ex){
        		PrintWriter out = resp.getWriter(); 
				out.print("Error: Unable to resolve viewfile/ class:" +  jspViewName);
        	}
        	
        }

    }
}