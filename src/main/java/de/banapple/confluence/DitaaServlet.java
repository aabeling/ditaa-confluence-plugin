package de.banapple.confluence;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DitaaServlet 
	extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GridUrlCodec codec;
	
	public DitaaServlet()
	{
		codec = new DefaultGridUrlCodec();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String encodedGrid = req.getParameter("grid");
		if (encodedGrid==null) {
			// TODO return error image
			return;
		}
		
		String gridCode = codec.decode(encodedGrid);
		
		Renderer renderer = new Renderer();
		
		resp.setContentType("image/png");
		renderer.renderToStream(gridCode, resp.getOutputStream());
	}

}
