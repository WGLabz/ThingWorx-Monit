package twx.entities.templates;

import com.thingworx.logging.LogUtilities;
import com.thingworx.metadata.annotations.ThingworxBaseTemplateDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceResult;
import com.thingworx.things.Thing;
import metrices.oshi.OSHIMetrices;
import org.json.JSONObject;
import org.slf4j.Logger;

@ThingworxBaseTemplateDefinition(name = "GenericThing")
public class OSHIMetrices_TT extends Thing {

  private static Logger _logger = LogUtilities
    .getInstance()
    .getApplicationLogger(OSHIMetrices_TT.class);
  private static final long serialVersionUID = 1L;
  private OSHIMetrices oshiMetrices;

  public OSHIMetrices_TT() {
    oshiMetrices = new OSHIMetrices();
  }

  @ThingworxServiceDefinition(
    name = "GetOSHIMemoryMetrices",
    description = "",
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
  public JSONObject GetOSHIMemoryMetrices() {
    _logger.trace("Entering Service: GetOSHIMemoryMetrices");

    _logger.trace("Exiting Service: GetOSHIMemoryMetrices");
    try {
      return oshiMetrices.getMemoryInfo();
    } catch (Exception e) {
      _logger.error("Error in GetOSHIMemoryMetrices: OSHIMetrices_TT " + e);
      return null;
    }
  }

  @ThingworxServiceDefinition(
    name = "GetOSHIProcessorMetrices",
    description = "",
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
  public JSONObject GetOSHIProcessorMetrices() {
    _logger.trace("Entering Service: GetOSHIProcessorMetrices");

    _logger.trace("Exiting Service: GetOSHIProcessorMetrices");
    try {
      return oshiMetrices.getCPUInfo();
    } catch (Exception e) {
      _logger.error("Error in GetOSHIProcessorMetrices: OSHIMetrices_TT " + e);
      return null;
    }
  }

  @ThingworxServiceDefinition(
    name = "GetOSHISensorsInfo",
    description = "",
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
  public JSONObject GetOSHISensorsInfo() {
    _logger.trace("Entering Service: GetOSHISensorsInfo");

    _logger.trace("Exiting Service: GetOSHISensorsInfo");
    try {
      return oshiMetrices.getSensorsInfo();
    } catch (Exception e) {
      _logger.error("Error in GetOSHISensorsInfo: OSHIMetrices_TT " + e);
      return null;
    }
  }

  @ThingworxServiceDefinition(
    name = "GetOSHIDiskInfo",
    description = "",
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
  public JSONObject GetOSHIDiskInfo() {
    _logger.trace("Entering Service: GetOSHIDiskInfo");

    _logger.trace("Exiting Service: GetOSHIDiskInfo");
    JSONObject diskInfo = new JSONObject();
    try {
      return diskInfo.put("INFO",oshiMetrices.getDiskInfo());
    } catch (Exception e) {
      _logger.error("Error in GetOSHIDiskInfo: OSHIMetrices_TT " + e);
      return null;
    }
  }

  @ThingworxServiceDefinition(
    name = "GetOSHIInternetStats",
    description = "",
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
  public JSONObject GetOSHIInternetStats() {
    _logger.trace("Entering Service: GetOSHIInternetStats");

    _logger.trace("Exiting Service: GetOSHIInternetStats");
    try {
      return oshiMetrices.getInternetStats();
    } catch (Exception e) {
      _logger.error("Error in GetOSHIInternetStats: OSHIMetrices_TT " + e);
      return null;
    }
  }

  @ThingworxServiceDefinition(
    name = "GetOSHINetworkParams",
    description = "",
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
  public JSONObject GetOSHINetworkParams() {
    _logger.trace("Entering Service: GetOSHINetworkParams");

    _logger.trace("Exiting Service: GetOSHINetworkParams");
    try {
      return oshiMetrices.getNetworkParams();
    } catch (Exception e) {
      _logger.error("Error in GetOSHINetworkParams: OSHIMetrices_TT " + e);
      return null;
    }
  }
  
  @ThingworxServiceDefinition(
		    name = "GetOSHIProcessesDetails",
		    description = "",
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
		  public JSONObject GetOSHIProcessesDetails() {
		    _logger.trace("Entering Service: GetOSHIProcessesDetails");

		    _logger.trace("Exiting Service: GetOSHIProcessesDetails");
		    try {
		      return oshiMetrices.getProcesses();
		    } catch (Exception e) {
		      _logger.error("Error in GetOSHIProcessesDetails: OSHIMetrices_TT " + e);
		      return null;
		    }
		  }
}
