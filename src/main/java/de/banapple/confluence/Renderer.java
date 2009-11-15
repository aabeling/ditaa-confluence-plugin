package de.banapple.confluence;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.stathissideris.ascii2image.core.ConversionOptions;
import org.stathissideris.ascii2image.graphics.BitmapRenderer;
import org.stathissideris.ascii2image.graphics.Diagram;
import org.stathissideris.ascii2image.text.TextGrid;

public class Renderer
{
	public void renderToStream(
			String gridCode,
			OutputStream stream) throws IOException
	{
		TextGrid grid = new TextGrid();
		ConversionOptions options = new ConversionOptions();
		grid.initialiseWithText(gridCode, options.processingOptions);
		Diagram diagram = new Diagram(grid, options);
		try {
			RenderedImage image = 
				new BitmapRenderer().renderToImage(
						diagram, 
						options.renderingOptions);
			ImageIO.write(image, "png", stream);
		} catch (IOException e) {
			/* return error image */
		} catch (Exception e) {
			/* create a simple diagram containing the exception message */
			String exceptionGrid = e.getMessage();
			renderToStream(exceptionGrid, stream);
		}
	}
}
