package de.banapple.confluence;

import java.awt.image.RenderedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stathissideris.ascii2image.core.ConversionOptions;
import org.stathissideris.ascii2image.graphics.BitmapRenderer;
import org.stathissideris.ascii2image.graphics.Diagram;
import org.stathissideris.ascii2image.text.TextGrid;

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
		System.out.println("encodedGrid="+encodedGrid);
		System.out.println("gridCode="+gridCode);
		
		Renderer renderer = new Renderer();
		
		resp.setContentType("image/png");
		renderer.renderToStream(gridCode, resp.getOutputStream());
	}

}
