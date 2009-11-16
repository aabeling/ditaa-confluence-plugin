package de.banapple.confluence;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.stathissideris.ascii2image.core.ConversionOptions;
import org.stathissideris.ascii2image.graphics.BitmapRenderer;
import org.stathissideris.ascii2image.graphics.Diagram;
import org.stathissideris.ascii2image.text.TextGrid;

public class Renderer
{
	private static Logger log = LogManager.getLogger(Renderer.class);
	
	public void renderToStream(
			String gridCode,
			OutputStream stream) throws IOException
	{
		final String METHOD = "renderToStream: ";
		log.debug(METHOD+"gridCode="+gridCode);
		
		TextGrid grid = new TextGrid();
		ConversionOptions options = new ConversionOptions();
		grid.initialiseWithText(gridCode, options.processingOptions);
		
		try {
			Diagram diagram = new Diagram(grid, options);
			RenderedImage image = 
				new BitmapRenderer().renderToImage(
						diagram, 
						options.renderingOptions);
			ImageIO.write(image, "png", stream);
		} catch (Exception e) {
			log.error(METHOD+"failed to render, returning exception image",e);
			/* create a simple diagram containing the exception message */
			renderToStream(getExceptionGrid(e), stream);
		}
	}
	
	private String getExceptionGrid(Exception e)
	{
		String[] lines = e.getMessage().split("\n");
		int maxWidth = 0;
		for (String line : lines) {
			maxWidth = Math.max(maxWidth, line.length());
		}
		maxWidth += 5;
		
		StringBuilder result = new StringBuilder();
		result.append("+");
		for (int i=0;i<maxWidth;i++) {
			result.append("-");
		}
		result.append("+\n");
		for (String line : lines) {
			result.append("|cRED ").append(line).append("|\n");
		}
		result.append("+");
		for (int i=0;i<maxWidth;i++) {
			result.append("-");
		}
		result.append("+\n");
		 
		return result.toString();
	}
}
