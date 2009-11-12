package de.banapple.confluence;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class DefaultGridUrlCodec implements GridUrlCodec
{
	private static final String ENCODING = "UTF-8";
	
	public String decode(String text)
	{
//		try {
			return URLDecoder.decode(text);
//		} catch (UnsupportedEncodingException e) {
//			throw new RuntimeException(e);
//		}
	}

	public String encode(String text)
	{
//		try {
			return URLEncoder.encode(text);
//		} catch (UnsupportedEncodingException e) {
//			throw new RuntimeException(e);
//		}
	}

}
