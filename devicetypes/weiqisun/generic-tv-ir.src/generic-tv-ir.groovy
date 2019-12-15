/**
 *  Generic TV IR
 *
 *  Copyright 2019 Weiqi Sun
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 */
metadata {
    definition (name: "Generic TV IR", namespace: "weiqisun", author: "Weiqi Sun", cstHandler: true) {
        capability "Switch"
        capability "Switch Level"

        command "mute"
        command "source"
        command "menu"
        command "tools"
        command "Up"
        command "Down"
        command "Left"
        command "Right"
        command "chup"
        command "chdown"
        command "prech"
        command "volup"
        command "voldown"
        command "Enter"
        command "Return"
        command "Exit"
        command "Info"
        command "Stb"
        command "forward"
        command "backward"
        command "pause"
        command "cc"
        command "play"
        command "stop"
    }

    simulator {
        // TODO: define status and reply messages here
    }

    tiles {

    standardTile("switch", "device.switch", canChangeIcon: true) {
        state "default", label:'TV', action:"switch.off", icon:"st.Electronics.electronics15", backgroundColor:"#79b821"
    }
    standardTile("power", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'TV', action:"switch.off", icon:"st.thermostat.heating-cooling-off"
    }
    standardTile("mute", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Mute', action:"mute", icon:"st.custom.sonos.muted"
    }
    standardTile("source", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Source', action:"source", icon:"st.Electronics.electronics15"
    }
    standardTile("menu", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Menu', action:"menu", icon:"st.vents.vent"
    }
    standardTile("tools", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Tools', action:"tools", icon:"st.secondary.tools"
    }
    standardTile("Up", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Up', action:"Up", icon:"st.thermostat.thermostat-up"
    }
    standardTile("Down", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Down', action:"Down", icon:"st.thermostat.thermostat-down"
    }
    standardTile("Left", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Left', action:"Left", icon:"st.thermostat.thermostat-left"
    }
    standardTile("Right", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Right', action:"Right", icon:"st.thermostat.thermostat-right"
    }
    standardTile("chup", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'CH Up', action:"chup", icon:"st.thermostat.thermostat-up"
    }
    standardTile("chdown", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'CH Down', action:"chdown", icon:"st.thermostat.thermostat-down"
    }
    standardTile("prech", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Pre CH', action:"prech", icon:"st.secondary.refresh-icon"
    }
    standardTile("volup", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Vol Up', action:"volup", icon:"st.thermostat.thermostat-up"
    }
    standardTile("voldown", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Vol Down', action:"voldown", icon:"st.thermostat.thermostat-down"
    }
    standardTile("Enter", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Enter', action:"Enter", icon:"st.illuminance.illuminance.dark"
    }
    standardTile("Return", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Return', action:"Return", icon:"st.secondary.refresh-icon"
    }
    standardTile("Exit", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Exit', action:"Exit", icon:"st.locks.lock.unlocked"
    }
    standardTile("Info", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Info', action:"Info", icon:"st.motion.acceleration.active"
    }
    standardTile("Stb", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'STB', action:"stb", icon:"st.samsung.da.RAC_ic_aircon"
    }
    standardTile("hub", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'SMART HUB', action:"hub", icon:"st.samsung.da.RAC_ic_aircon"
    }
    standardTile("guide", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Guide', action:"guide", icon:"st.samsung.da.RAC_ic_aircon"
    }
    standardTile("forward", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Forward', action:"forward", icon:"st.samsung.da.RAC_ic_aircon"
    }
    standardTile("backward", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Backward', action:"backward", icon:"st.samsung.da.RAC_ic_aircon"
    }
    standardTile("pause", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Pause', action:"pause", icon:"st.samsung.da.RAC_ic_aircon"
    }
    standardTile("cc", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'CC', action:"cc", icon:"st.samsung.da.RAC_ic_aircon"
    }
    standardTile("play", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Play', action:"play", icon:"st.samsung.da.RAC_ic_aircon"
    }
    standardTile("stop", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Stop', action:"stop", icon:"st.samsung.da.RAC_ic_aircon"
    }

    main "switch"
    details (["power","source", "Stb", "chup","prech","volup","chdown","mute","voldown", "menu", "tools", "Up", "Info", "Left", "Enter", "Right", "Return", "Down", "Exit", "backward", "pause", "forward", "cc", "play", "stop"])

    }
}

preferences {
    input("gatewayIP", "text", title: "Gateway IP", required: true, displayDuringSetup: true)
    input("gatewayPort", "text", title: "Gateway Port", required: true, displayDuringSetup: true)
}

// parse events into attributes
def parse(String description) {
    log.debug "Parsing '${description}'"
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
    log.debug "Executing 'on'"
    String command = "POWER"
    executeCommand(command)
}

def off() {
    log.debug "Executing 'off'"
    String command = "POWER"
    executeCommand(command)
}

def setLevel() {
    log.debug "Executing 'setLevel'"
    if (val < 0){
        val = 0
    }

    if( val > 100){
        val = 100
    }

    def prevLevel = device.currentValue("level")
    log.info "setLevel $val -- prevLevel $prevLevel"

    if ((val > prevLevel) || ((val == prevLevel) && (val != 0))) {
      volup()
      volup()
      sendEvent(name: "switch", value: "on")
    } else {
      voldown()
      voldown()
    }


    // make sure we don't drive switches past allowed values (command will hang device waiting for it to
    // execute. Never commes back)
    sendEvent(name:"level",value:val)
    sendEvent(name:"switch.setLevel",value:val)
}

def mute() {
    log.debug "executing 'mute'"
    executecommand("MUTE")
}

def source() {
    log.debug "executing 'source'"
    executecommand("SOURCE")
}

def menu() {
    log.debug "executing 'menu'"
    executecommand("MENU")
}

def hubActionResponse(response) {
    log.debug("Executing 'hubActionResponse': '${device.deviceNetworkId}'")

    log.debug("Hub action response: '${response}'")

    /*
    def status = response.headers["tv-status"] ?: ""
    log.debug("switch status: '${status}'")
    if (status != "")
        calibration(status)
    */
}

def calibration(status) {
    sendEvent(name: "switch", value: status, isStateChange: true)
    if (status == "on")
        sendEvent(name: "level", value: (device.currentValue("level").toInteger() % 10), isStateChange: true)
    else
        sendEvent(name: "level", value: (device.currentValue("level").toInteger() % 10 + 90), isStateChange: true)
}

private executeCommand(command){

    def gatewayIPHex = convertIPtoHex(gatewayIP)
    def gatewayPortHex = convertPortToHex(gatewayPort)
    message (device.deviceNetworkId)
    message ("gateway port: $gatewayIP:$gatewayPort")

    def headers = [:]
    headers.put("HOST", "$gatewayIP:$gatewayPort")
    headers.put("ir-command", command)

    try {
      sendHubCommand(new physicalgraph.device.HubAction([
          method: "GET",
          path: "/",
          headers: headers],
          device.deviceNetworkId,
          [callback: "hubActionResponse"]
      ))
    } catch (e) {
      log.debug(e.message)
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
