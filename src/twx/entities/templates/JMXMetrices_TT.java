package twx.entities.templates;

import com.thingworx.logging.LogUtilities;
import com.thingworx.metadata.annotations.ThingworxBaseTemplateDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceResult;
import com.thingworx.things.Thing;

import metrices.jmx.JMXMetrices;

import org.json.JSONObject;
import org.slf4j.Logger;

@ThingworxBaseTemplateDefinition(name = "GenericThing")
public class JMXMetrices_TT extends Thing {
	
	private static Logger _logger = LogUtilities.getInstance().getApplicationLogger(JMXMetrices_TT.class);
	private static final long serialVersionUID = -4989526249165009993L;

	public JMXMetrices_TT() {
		// TODO Auto-generated constructor stub
	}

	@ThingworxServiceDefinition(name = "GetJMXMetrices", description = "Returns the JMX Metrices as JSON", category = "Metrices", isAllowOverride = true, aspects = {
			"isAsync:false" })
	@ThingworxServiceResult(name = "Result", description = "", baseType = "JSON", aspects = {})
	public JSONObject GetJMXMetrices() {
		_logger.trace("Entering Service: GetJMXMetrices");
		
		JMXMetrices jmx = new JMXMetrices();
		try {
			_logger.trace("Exiting Service: GetJMXMetrices");
			return jmx.getMetrices();
		}catch(Exception e) {
			_logger.error("Error in JMXMetrices_TT: GetJMXMetrices "+e);
			return null;
		}
	}

}
