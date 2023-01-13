package metrices.oshi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Sensors;
import oshi.software.os.InternetProtocolStats;
import oshi.software.os.NetworkParams;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystem.ProcessFiltering;
import oshi.software.os.OperatingSystem.ProcessSorting;

public class OSHIMetrices {

  private HardwareAbstractionLayer hal;
  private OperatingSystem os;

  public OSHIMetrices() {
    SystemInfo si = new SystemInfo();
    this.hal = si.getHardware();
    this.os = si.getOperatingSystem();
  }

  public JSONObject getMemoryInfo()
    throws JsonProcessingException, JSONException {
    ObjectMapper mapper = new ObjectMapper();
    GlobalMemory mem = this.hal.getMemory();
    return new JSONObject(new JSONTokener(mapper.writeValueAsString(mem)));
  }

  public JSONObject getCPUInfo() throws JsonProcessingException, JSONException {
    ObjectMapper mapper = new ObjectMapper();

    CentralProcessor cpu = this.hal.getProcessor();
    return new JSONObject(new JSONTokener(mapper.writeValueAsString(cpu)));
  }

  public JSONObject getSensorsInfo()
    throws JsonProcessingException, JSONException {
    ObjectMapper mapper = new ObjectMapper();

    Sensors sensors = this.hal.getSensors();
    return new JSONObject(new JSONTokener(mapper.writeValueAsString(sensors)));
  }

  public JSONObject getNetworkParams()
    throws JsonProcessingException, JSONException {
    ObjectMapper mapper = new ObjectMapper();

    NetworkParams nParams = this.os.getNetworkParams();
    return new JSONObject(new JSONTokener(mapper.writeValueAsString(nParams)));
  }

  public JSONObject getInternetStats()
    throws JsonProcessingException, JSONException {
    ObjectMapper mapper = new ObjectMapper();

    InternetProtocolStats stats = this.os.getInternetProtocolStats();
    return new JSONObject(new JSONTokener(mapper.writeValueAsString(stats)));
  }

  public JSONArray getDiskInfo() throws JsonProcessingException, JSONException {
    ObjectMapper mapper = new ObjectMapper();

    List<HWDiskStore> disks = this.hal.getDiskStores();
    //this.os.filest

    JSONArray disksArray = new JSONArray();

    for (HWDiskStore disk : disks) {
      disksArray.put(
        new JSONObject(new JSONTokener(mapper.writeValueAsString(disk)))
      );
    }
    return disksArray;
  }

  public JSONObject getProcesses()
    throws JsonProcessingException, JSONException {
    JSONObject response = new JSONObject();

    List<OSProcess> procs =
      this.os.getProcesses(
          ProcessFiltering.ALL_PROCESSES,
          ProcessSorting.CPU_DESC,
          100
        );
    JSONArray procs_ = new JSONArray();
    for (int i = 0; i < procs.size(); i++) {
      OSProcess p = procs.get(i);

      JSONObject proc_ = new JSONObject();

      proc_.put("PID", p.getProcessID());
      proc_.put(
        "%CPU",
        100d * (p.getKernelTime() + p.getUserTime()) / p.getUpTime()
      );
      proc_.put(
        "%MEM",
        100d * p.getResidentSetSize() / this.hal.getMemory().getTotal()
      );
      proc_.put("NAME", p.getName());
      proc_.put("VSZ", p.getVirtualSize());
      proc_.put("RSS", p.getResidentSetSize());
      proc_.put("NAME", p.getName());

      procs_.put(proc_);
    }
    response.put("INFO", procs_);
    return response;
  }
}
