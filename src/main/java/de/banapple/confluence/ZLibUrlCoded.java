package de.banapple.confluence;

import java.util.zip.Deflater;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ZLibUrlCoded
	implements GridUrlCodec
{
	private static Log log = LogFactory.getLog(ZLibUrlCoded.class);
	
	private static final String ALPHABET =
		"abcdefghijklmnopqrstuvwxyz"
		+"ABCDEFGHIJKLKMNOPQRSTUVWXYZ"
		+"0123456789"
		+"._";
	
	private ThreadLocal deflaterTL = new ThreadLocal();
	
	public String encode(String text)
    {
    	final String METHOD = "getCompressedText: ";
    	
    	//
    	// should perhaps use a pool of deflaters
    	//
    	Deflater deflater = (Deflater) deflaterTL.get();
    	if (deflater==null) {
    		deflater = new Deflater();
    		deflaterTL.set(deflater);
    	}
    	
    	byte[] input = text.getBytes();
    	log.debug(METHOD+"input size in bits: "+input.length*8);
    	
    	deflater.setInput(input);
    	deflater.finish();
    	byte[] output = new byte[input.length+3];
    	int dataLength = deflater.deflate(output);
    	log.debug(METHOD+"output size in bits: "+dataLength*8);
    	deflater.reset();
    	
    	/* convert bits to characters of the alphabet */
    	StringBuilder result = new StringBuilder();
    	for (int i=0;i<dataLength;i+=3) {
    		int byte0 = output[i]+128;
    		int byte1 = output[i+1]+128;
    		int byte2 = output[i+2]+128;
    		int char0 = byte0 >>> 2;
    		int char1 = ((byte0 & 3) << 4) | (byte1 >>> 4);
    		int char2 = ((byte1 & 15) << 2) | (byte2 >>> 6);
    		int char3 = byte2 & 63;
    		result.append(ALPHABET.charAt(char0))
    			.append(ALPHABET.charAt(char1))
    			.append(ALPHABET.charAt(char2))
    			.append(ALPHABET.charAt(char3));
    	}
    	
    	return result.toString();
    }

	public String decode(String text)
	{
		// TODO to be implemented
		return null;
	}
}
