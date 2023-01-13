package twx.entities.templates;

import com.thingworx.logging.LogUtilities;
import com.thingworx.metadata.annotations.ThingworxBaseTemplateDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceParameter;
import com.thingworx.metadata.annotations.ThingworxServiceResult;
import com.thingworx.things.Thing;
import metrices.jmx.JMXMetrices;
import org.json.JSONObject;
import org.slf4j.Logger;

@ThingworxBaseTemplateDefinition(name = "GenericThing")
public class JMXMetrices_TT extends Thing {

  private static Logger _logger = LogUtilities
    .getInstance()
    .getApplicationLogger(JMXMetrices_TT.class);
  private static final long serialVersionUID = -4989526249165009993L;

  public JMXMetrices_TT() {
    // TODO Auto-generated constructor stub
  }

  @ThingworxServiceDefinition(
    name = "GetJMXMetrices",
    description = "Returns the JMX Metrices as JSON",
    category = "Metrices",
    isAllowOverride = true,
    aspects = { "isAsync:false" }
  )
  @ThingworxServiceResult(
    name = "Result",
    description = "",
    baseType = "JSON",
    aspects = {}
  )
  public JSONObject GetJMXMetrices() {
    _logger.trace("Entering Service: GetJMXMetrices");

    JMXMetrices jmx = new JMXMetrices();
    try {
      _logger.trace("Exiting Service: GetJMXMetrices");
      return jmx.getMetrices();
    } catch (Exception e) {
      _logger.error("Error in JMXMetrices_TT: GetJMXMetrices " + e);
      return null;
    }
  }

  @ThingworxServiceDefinition(
    name = "GetCPULoadAndMem",
    description = "Returns the JMX Metrices as JSON",
    category = "Metrices",
    isAllowOverride = true,
    aspects = { "isAsync:false" }
  )
  @ThingworxServiceResult(
    name = "Result",
    description = "",
    baseType = "JSON",
    aspects = {}
  )
  public JSONObject GetCPULoadAndMem() {
    _logger.trace("Entering Service: GetCPULoadAndMem");

    JMXMetrices jmx = new JMXMetrices();
    try {
      _logger.trace("Exiting Service: GetCPULoadAndMem");
      return jmx.getCPULoadAndMEM();
    } catch (Exception e) {
      _logger.error("Error in JMXMetrices_TT: GetCPULoadAndMem " + e);
      return null;
    }
  }

@ThingworxServiceDefinition(name = "GetDiskSpaceInfo", description = "", category = "", isAllowOverride = false, aspects = {
		"isAsync:false" })
@ThingworxServiceResult(name = "Result", description = "", baseType = "JSON", aspects = {})
public JSONObject GetDiskSpaceInfo(
		@ThingworxServiceParameter(name = "DrivePath", description = "", baseType = "STRING", aspects = {}) String DrivePath) {
	_logger.trace("Entering Service: GetDiskSpaceInfo");
	JMXMetrices jmx = new JMXMetrices();
	try {
		return jmx.getDiskSizeMetrices(DrivePath);
	}catch(Exception e) {

	      _logger.error("Error in JMXMetrices_TT: GetDiskSpaceInfo " + e);
	}
	_logger.trace("Exiting Service: GetDiskSpaceInfo");
	
	return null;
}
  
  
}
