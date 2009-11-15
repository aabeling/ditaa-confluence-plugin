package de.banapple.confluence;

import java.net.URLDecoder;
import java.net.URLEncoder;


public class DefaultGridUrlCodec implements GridUrlCodec
{
	private static final String ENCODING = "UTF-8";
	
	public String decode(String text)
	{
		return text;
	}

	public String encode(String text)
	{
		return URLEncoder.encode(text);
	}

}
