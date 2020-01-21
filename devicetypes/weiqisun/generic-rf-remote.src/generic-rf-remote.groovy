/**
 *  Generic RF Remote
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
    definition (name: "Generic RF Remote", namespace: "weiqisun", author: "Weiqi Sun", cstHandler: true) {
        capability "Switch"

        command "up"
        command "down"
        command "stop"
    }

    simulator {
        // TODO: define status and reply messages here
    }

    tiles {

    standardTile("switch", "device.switch", canChangeIcon: true) {
        state "default", label: '', action: "skip", icon: "st.Electronics.electronics15", backgroundColor: "#BBBBBB"
    }
    standardTile("up", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Up', action:"up", icon:"st.thermostat.thermostat-up"
    }
    standardTile("down", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Down', action:"down", icon:"st.thermostat.thermostat-down"
    }
    standardTile("stop", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Stop', action:"stop", icon:"https://raw.githubusercontent.com/weiqisun/Smartthings/master/devicetypes/weiqisun/generic-tv-ir.src/icons/stop-btn%402x.png"
    }

    main "switch"
    details (["up", "down", "stop"])

    }
}

preferences {
    input("gatewayIP", "text", title: "Gateway IP", required: true, displayDuringSetup: true)
    input("gatewayPort", "text", title: "Gateway Port", required: true, displayDuringSetup: true)
    input("remoteName", "text", title: "Remote Name", required: true, displayDuringSetup: true)
}

// parse events into attributes
def parse(String description) {
    log.debug "Parsing '${description}'"
}

// handle commands
def on() {
    log.debug "Executing 'on'"
    up()
}

def off() {
    log.debug "Executing 'off'"
    down()
}

def skip() {
    log.debug "Executing 'skip'"
}

def up() {
    log.debug "executing 'up'"
    executeCommand("up")
}

def down() {
    log.debug "executing 'down'"
    executeCommand("down")
}

def stop() {
    log.debug "executing 'stop'"
    executeCommand("stop")
}

def hubActionResponse(response) {
    log.debug("Executing 'hubActionResponse': '${device.deviceNetworkId}'")
    log.debug("Hub action response: '${response}'")
}

private executeCommand(command){
    log.debug("Executing command: '${command}'")
    log.debug("Device: ${device.deviceNetworkId}; Remote: ${remoteName}; Gateway: ${gatewayIP}:${gatewayPort}")

    def headers = [:]
    headers.put("host", "${gatewayIP}:${gatewayPort}")
    headers.put("ir-command", command)
    headers.put("remote-name", remoteName)
    headers.put("remote-type", "rf")
    log.debug(headers)

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
    
    log.debug("Done executing command: '${command}'")
}