/**
 *  HDMI CEC
 *
 *  Copyright 2017 Weiqi Sun
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
	definition (name: "HDMI CEC", namespace: "weiqisun", author: "Weiqi Sun") {
        capability "Switch Level"
        capability "Switch"

		attribute "source1", "boolean"
		attribute "source2", "boolean"
		attribute "source3", "boolean"

		command "source1"
		command "source2"
		command "source3"
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

        standardTile("HDMI1", "device.source1", width: 1, height: 1, canChangeIcon: true) {
            state("false", label:"HDMI1", action:"source1", icon:"st.Electronics.electronics15", backgroundColor: "#ffffff")
            state("true", label:"HDMI1", action:"source1", icon:"st.Electronics.electronics15", backgroundColor: "#79b821")
        }

        standardTile("HDMI2", "device.source2", width: 1, height: 1, canChangeIcon: true) {
            state("false", label:"HDMI2", action:"source2", icon:"st.Electronics.electronics15", backgroundColor: "#ffffff")
            state("true", label:"HDMI2", action:"source2", icon:"st.Electronics.electronics15", backgroundColor: "#79b821")
        }

        standardTile("HDMI3", "device.source3", width: 1, height: 1, canChangeIcon: true) {
            state("false", label:"HDMI3", action:"source3", icon:"st.Electronics.electronics15", backgroundColor: "#ffffff")
            state("true", label:"HDMI3", action:"source3", icon:"st.Electronics.electronics15", backgroundColor: "#79b821")
        }
        
        controlTile("levelSliderControl", "device.level", "slider", height: 2, width: 1, inactiveLabel: false) {
			state "level", action:"setLevel"
		}

        main("switch")
        details(["switch", "HDMI1", "HDMI2", "HDMI3"])
	}
}

preferences {
  input("gatewayIP", "text", title: "Gateway IP", required: true, displayDuringSetup: true)
  input("gatewayPort", "text", title: "Gateway Port", required: true, displayDuringSetup: true)
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
def on() {
    message("Executing 'on'")
    executeCommand("on")
    sendEvent(name: "switch", value: "on", isStateChange: true)
    source3()
}

def off() {
    message("Executing 'off'")
    executeCommand("off")
    sendEvent(name: "switch", value: "off", isStateChange: true)
    sendEvent(name: "level", value: 0, isStateChange: true)
    sendEvent(name: "source1", value: false, isStateChange: true)
    sendEvent(name: "source2", value: false, isStateChange: true)
    sendEvent(name: "source3", value: false, isStateChange: true)
}

def source1() {
	if (device.currentValue("switch") == "on") {
        message("Changing source to '1'")
        executeCommand("1")
    
        sendEvent(name: "level", value: 1, isStateChange: true)
        sendEvent(name: "source1", value: true, isStateChange: true)
        sendEvent(name: "source2", value: false, isStateChange: true)
        sendEvent(name: "source3", value: false, isStateChange: true)
    }
    else {
        message("Fail changing source to '1', TV is off")
    }
}

def source2() {
	if (device.currentValue("switch") == "on") {
        message("Changing source to '2'")
        executeCommand("2")
    
        sendEvent(name: "level", value: 2, isStateChange: true)
        sendEvent(name: "source1", value: false, isStateChange: true)
        sendEvent(name: "source2", value: true, isStateChange: true)
        sendEvent(name: "source3", value: false, isStateChange: true)
    }
    else {
        message("Fail changing source to '2', TV is off")
    }
}

def source3() {
	if (device.currentValue("switch") == "on") {
        message("Changing source to '3'")
        executeCommand("3")

        sendEvent(name: "level", value: 3, isStateChange: true)
        sendEvent(name: "source1", value: false, isStateChange: true)
        sendEvent(name: "source2", value: false, isStateChange: true)
        sendEvent(name: "source3", value: true, isStateChange: true)
    }
    else {
        message("Fail changing source to '3', TV is off")
    }
}

def setLevel(value) {
    message("Set TV value: $value")

    if (value == 1) {
        source1()
    }
    else if (value == 2) {
        source2()
    }
    else {
        source3()
    }
}

def hubActionResponse(response){
  message("Executing 'hubActionResponse': '${device.deviceNetworkId}'")
}

private executeCommand(command){

    def gatewayIPHex = convertIPtoHex(gatewayIP)
    def gatewayPortHex = convertPortToHex(gatewayPort)
    message (device.deviceNetworkId)
    message ("gateway port: $gatewayIP:$gatewayPort")

    def headers = [:]
    headers.put("HOST", "$gatewayIP:$gatewayPort")
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