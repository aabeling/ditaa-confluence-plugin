package de.banapple.confluence;

public interface GridUrlCodec
{
	/**
	 * Encodes plain text.
	 * 
	 * @param text
	 * @return
	 */
	String encode(String text);
	
	/**
	 * Decodes encoded text back to plain text.
	 * 
	 * @param text
	 * @return
	 */
	String decode(String text);
}
