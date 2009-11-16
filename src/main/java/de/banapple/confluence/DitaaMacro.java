package de.banapple.confluence;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.atlassian.confluence.setup.settings.SettingsManager;
import com.atlassian.renderer.RenderContext;
import com.atlassian.renderer.v2.RenderMode;
import com.atlassian.renderer.v2.macro.BaseMacro;
import com.atlassian.renderer.v2.macro.MacroException;

public class DitaaMacro extends BaseMacro
{
	private static Log log = LogFactory.getLog(DitaaMacro.class);
	
	private GridUrlCodec codec;
	private SettingsManager settingsManager;
	
    public boolean isInline()
    {
        return false;
    }

    public boolean hasBody()
    {
        return true;
    }

    public RenderMode getBodyRenderMode()
    {
        return RenderMode.NO_RENDER;
    }

    public DitaaMacro()
    {
    	codec = new DefaultGridUrlCodec();
    	log.debug("created");
    }
    
    @SuppressWarnings("unchecked")
	public String execute(Map params, String body, RenderContext renderContext)
        throws MacroException
    {	
    	final String METHOD = "execute: ";
    	
    	log.debug(METHOD+"macro.body="+body);
    	String encodedBody = codec.encode(body);
    	log.debug(METHOD+"macro.encodedBody="+encodedBody);
        StringBuffer result=new StringBuffer();
        result.append("<img src=\"")
        	.append(settingsManager.getGlobalSettings().getBaseUrl())
        	.append("/plugins/servlet/ditaa?grid=")
        	.append(encodedBody)
        	.append("\" />");
        
        return result.toString();
    }

	public void setSettingsManager(SettingsManager settingsManager)
	{
		this.settingsManager = settingsManager;
	}

    
}