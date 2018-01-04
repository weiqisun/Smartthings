/**
 *  PS4
 *
 *  Copyright 2018 Weiqi Sun
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
	definition (name: "PS4", namespace: "weiqisun", author: "Weiqi Sun") {
		capability "Polling"
		capability "Refresh"
		capability "Switch"
	}


	simulator {
		// TODO: define status and reply messages here
	}

	tiles {
		standardTile("switch", "device.switch", width: 3, height: 2, canChangeIcon: true) {
            state "off", label: 'Off', action: "switch.on",
                  icon: "st.switches.switch.off", backgroundColor: "#ffffff"
            state "on", label: 'On', action: "switch.off",
                  icon: "st.switches.switch.on", backgroundColor: "#79b821"
        }
        
        standardTile("refresh", "capability.refresh", width: 1, height: 1, decoration: "flat") {
            state("default", label:"", action:"refresh", icon:"st.secondary.refresh")
        }
	}
}

preferences {
    input("gatewayIP", "text", title: "Gateway IP", required: true, displayDuringSetup: true)
    input("gatewayPort", "text", title: "Gateway Port", required: true, displayDuringSetup: true)
    input("loggicalAddr", "text", title: "HDMI Loggical Address", required: true, displayDuringSetup: true)
}

def message(msg){
    log.debug(msg)
}

// parse events into attributes
def parse(String description) {
	log.debug "parsing"
    def msg = parseLanMessage(description)

    def headersAsString = msg.header // => headers as a string
    def headerMap = msg.headers      // => headers as a Map
    def body = msg.body              // => request body as a string
    def status = msg.status          // => http status code of the response
    def json = msg.json              // => any JSON included in response body, as a data structure of lists and maps
    def xml = msg.xml                // => any XML included in response body, as a document tree structure
    def data = msg.data              // => either JSON or XML in response body (whichever is specified by content-type header in response)

}

// handle commands
def poll() {
	executeCommand("ps4-status")
}

def refresh() {
	executeCommand("ps4-status")
}

def on() {
	message("Executing 'on'")
    executeCommand("ps4-on")
    sendEvent(name: "switch", value: "on", isStateChange: true)
}

def off() {
	message("Executing 'off'")
    executeCommand("ps4-off")
    sendEvent(name: "switch", value: "off", isStateChange: true)
}

def hubActionResponse(response) {
  	message("Executing 'hubActionResponse': '${device.deviceNetworkId}'")

  	def status = response.headers["status"] ?: ""
	message("PS4 status: '${status}'")
	if (status != "" && status != device.currentValue("switch")) {
		if (status == "error") status = "off"
		sendEvent(name: "switch", value: status, isStateChange: true)
	}
}      

private executeCommand(command){

    def gatewayIPHex = convertIPtoHex(gatewayIP)
    def gatewayPortHex = convertPortToHex(gatewayPort)
    message (device.deviceNetworkId)
    message ("gateway port: $gatewayIP:$gatewayPort")

    def headers = [:]
    headers.put("HOST", "$gatewayIP:$gatewayPort")
    headers.put("device", loggicalAddr)
    headers.put("cec-command", command)

    try {
      sendHubCommand(new physicalgraph.device.HubAction([
          method: "GET",
          path: "/",
          headers: headers],
          device.deviceNetworkId,
          [callback: "hubActionResponse"]
      ))
    } catch (e) {
      message(e.message)
    }
}

private String convertIPtoHex(ipAddress) {
    String hex = ipAddress.tokenize( '.' ).collect {  String.format( '%02x', it.toInteger() ) }.join()
    return hex
}

private String convertPortToHex(port) {
    String hexport = port.toString().format( '%04x', port.toInteger() )
    return hexport
}