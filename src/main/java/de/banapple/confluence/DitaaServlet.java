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
		
		String gridCode = encodedGrid;
//		String gridCode = codec.decode(encodedGrid);
//		System.out.println("encodedGrid="+encodedGrid);
//		System.out.println("gridCode="+gridCode);
		
		TextGrid grid = new TextGrid();
		ConversionOptions options = new ConversionOptions();
//		options.setDebug(true);
		grid.initialiseWithText(gridCode, options.processingOptions);
		Diagram diagram = new Diagram(grid, options);
		RenderedImage image = 
			new BitmapRenderer().renderToImage(
					diagram, 
					options.renderingOptions);
	
		resp.setContentType("image/png");
		ImageIO.write(image, "png", resp.getOutputStream());
	}

}
