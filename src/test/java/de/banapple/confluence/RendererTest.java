package de.banapple.confluence;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import junit.framework.Assert;
import junit.framework.TestCase;

public class RendererTest
	extends TestCase
{
	public void testSuccessfulRender() throws IOException
	{
		Renderer renderer = new Renderer();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		
		String gridCode = 
			"+-----+\n"+
			"|Hallo|\n"+
			"+-----+";
		renderer.renderToStream(gridCode, stream);
		
		Assert.assertTrue(stream.size()>0);
	}

	public void testMessage() throws IOException
	{
		Renderer renderer = new Renderer();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		
		String gridCode = "Hello world";
		renderer.renderToStream(gridCode, stream);
		
		Assert.assertTrue(stream.size()>0);
	}
}
