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
        capability "Polling"
        capability "Refresh"
        capability "Switch"
        capability "Switch Level"

        command "power"
        command "mute"
        command "source"
        command "menu"
        command "tools"
        command "up"
        command "down"
        command "left"
        command "right"
        command "chup"
        command "chdown"
        command "prech"
        command "volup"
        command "voldown"
        command "enter"
        command "ret"
        command "exit"
        command "info"
        command "stb"
        command "forward"
        command "backward"
        command "pause"
        command "cc"
        command "play"
        command "stop"
        command "hub"
        command "guide"
        command "seton"
        command "setoff"
    }

    simulator {
        // TODO: define status and reply messages here
    }

    tiles {

    standardTile("switch", "device.switch", canChangeIcon: true) {
        state "off", label: 'Off', action: "switch.on",
               icon: "st.Electronics.electronics15", backgroundColor: "#ffffff"
        state "on", label: 'On', action: "switch.off",
               icon: "st.Electronics.electronics15", backgroundColor: "#79b821"
    }
    standardTile("power", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'TV', action:"power", icon:"st.samsung.da.RC_ic_power"
    }
    standardTile("mute", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Mute', action:"mute", icon:"st.custom.sonos.muted"
    }
    standardTile("source", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Source', action:"source", icon:"st.Electronics.electronics15"
    }
    standardTile("menu", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Menu', action:"menu", icon:"st.Office.office8"
    }
    standardTile("tools", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Tools', action:"tools", icon:"st.secondary.tools"
    }
    standardTile("up", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Up', action:"up", icon:"st.thermostat.thermostat-up"
    }
    standardTile("down", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Down', action:"down", icon:"st.thermostat.thermostat-down"
    }
    standardTile("left", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Left', action:"left", icon:"st.thermostat.thermostat-left"
    }
    standardTile("right", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Right', action:"right", icon:"st.thermostat.thermostat-right"
    }
    standardTile("chup", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'CH Up', action:"chup", icon:"st.thermostat.thermostat-up"
    }
    standardTile("chdown", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'CH Down', action:"chdown", icon:"st.thermostat.thermostat-down"
    }
    standardTile("prech", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Pre CH', action:"prech", icon:"st.motion.motion.active"
    }
    standardTile("volup", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Vol Up', action:"volup", icon:"st.thermostat.thermostat-up"
    }
    standardTile("voldown", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Vol Down', action:"voldown", icon:"st.thermostat.thermostat-down"
    }
    standardTile("enter", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Enter', action:"enter", icon:"st.illuminance.illuminance.dark"
    }
    standardTile("return", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Return', action:"ret", icon:"st.secondary.refresh-icon"
    }
    standardTile("exit", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Exit', action:"exit", icon:"st.locks.lock.unlocked"
    }
    standardTile("info", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Info', action:"info", icon:"st.switches.light.on"
    }
    standardTile("stb", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'STB', action:"stb", icon:"st.samsung.da.RAC_ic_aircon"
    }
    standardTile("hub", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'SMART HUB', action:"hub", icon:"st.Entertainment.entertainment9"
    }
    standardTile("guide", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Guide', action:"guide", icon:"st.motion.acceleration.active"
    }
    standardTile("forward", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Forward', action:"forward", icon:"https://raw.githubusercontent.com/weiqisun/Smartthings/master/devicetypes/weiqisun/generic-tv-ir.src/icons/next-btn@2x.png"
    }
    standardTile("backward", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Backward', action:"backward", icon:"https://raw.githubusercontent.com/weiqisun/Smartthings/master/devicetypes/weiqisun/generic-tv-ir.src/icons/previous-btn%402x.png"
    }
    standardTile("pause", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Pause', action:"pause", icon:"https://raw.githubusercontent.com/weiqisun/Smartthings/master/devicetypes/weiqisun/generic-tv-ir.src/icons/pause-icon%402x.png"
    }
    standardTile("cc", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'CC', action:"cc", icon:"st.Entertainment.entertainment8"
    }
    standardTile("play", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Play', action:"play", icon:"https://raw.githubusercontent.com/weiqisun/Smartthings/master/devicetypes/weiqisun/generic-tv-ir.src/icons/play-icon%402x.png"
    }
    standardTile("stop", "device.switch", decoration: "flat", canChangeIcon: false) {
        state "default", label:'Stop', action:"stop", icon:"https://raw.githubusercontent.com/weiqisun/Smartthings/master/devicetypes/weiqisun/generic-tv-ir.src/icons/stop-btn%402x.png"
    }
    standardTile("refresh", "capability.refresh", decoration: "flat") {
        state("default", label:"Refresh", action:"refresh", icon:"st.secondary.refresh-icon")
    }

    main "switch"
    details (["power","refresh","stb","volup","prech","chup","voldown","mute","chdown","menu", "hub", "guide", "tools","up","source","left","enter","right","return","down","exit","backward","pause","forward","cc","play","stop"])

    }
}

preferences {
    input("gatewayIP", "text", title: "Gateway IP", required: true, displayDuringSetup: true)
    input("gatewayPort", "text", title: "Gateway Port", required: true, displayDuringSetup: true)
    input("remoteName", "text", title: "Remote Name", required: true, displayDuringSetup: true)
    input("irCommandPrefix", "text", title: "Prefix of IR Command", required: true, displayDuringSetup: true)
    input("volumeChangeRepeat", "text", title: "Volume Change Repeat", required: true, displayDuringSetup: true)
}

// parse events into attributes
def parse(String description) {
    log.debug "Parsing '${description}'"
}

// handle commands
def on() {
    log.debug "Executing 'on'"
    executeCommand("on")
    sendEvent(name: "switch", value: "on", isStateChange: true)
}

def off() {
    log.debug "Executing 'off'"
    executeCommand("off")
    sendEvent(name: "switch", value: "off", isStateChange: true)
}

def seton() {
    log.debug "Executing 'seton'"
    sendEvent(name: "switch", value: "on", isStateChange: true)
}

def setoff() {
    log.debug "Executing 'setoff'"
    sendEvent(name: "switch", value: "off", isStateChange: true)
}

def poll(){
    executeCommand("status")
}

def refresh(){
    executeCommand("status")
}

// Alexa send 10 for turning volume low, and 100 for tunning volume up
def setLevel(val) {
    log.debug "Executing 'setLevel' to ${val}"
    def pivot = 50.0 // show be a value between 10 (turn down vol) andd 100 (turn up vol)
    if (val <= pivot) {
        voldown(volumeChangeRepeat.toInteger())
    } else {
        volup(volumeChangeRepeat.toInteger())
    }
}

def power() {
    log.debug "Executing 'power'"
    executeCommand("POWER")
    if (device.currentValue("switch") == "on") {
        sendEvent(name: "switch", value: "off", isStateChange: true)
    } else {
        sendEvent(name: "switch", value: "on", isStateChange: true)
    }
}

def mute() {
    log.debug "executing 'mute'"
    executeCommand("MUTE")
}

def source() {
    log.debug "executing 'source'"
    executeCommand("SOURCE")
}

def menu() {
    log.debug "executing 'menu'"
    executeCommand("MENU")
}

def tools() {
    log.debug "executing 'tools'"
    executeCommand("TOOLS")
}

def up() {
    log.debug "executing 'up'"
    executeCommand("UP")
}

def down() {
    log.debug "executing 'down'"
    executeCommand("DOWN")
}

def left() {
    log.debug "executing 'left'"
    executeCommand("LEFT")
}

def right() {
    log.debug "executing 'right'"
    executeCommand("RIGHT")
}

def chup() {
    log.debug "executing 'chup'"
    executeCommand("CHANNELUP")
}

def chdown() {
    log.debug "executing 'chdown'"
    executeCommand("CHANNELDOWN")
}

def prech() {
    log.debug "executing 'prech'"
    executeCommand("PREVIOUS")
}

def volup(repeat = 0) {
    log.debug "executing 'volup'"
    executeCommand("VOLUMEUP", repeat)
}

def voldown(repeat = 0) {
    log.debug "executing 'voldown'"
    executeCommand("VOLUMEDOWN", repeat)
}

def enter() {
    log.debug "executing 'enter'"
    executeCommand("ENTER")
}

def ret() {
    log.debug "executing 'return'"
    executeCommand("RETURN")
}

def exit() {
    log.debug "executing 'exit'"
    executeCommand("EXIT")
}

def info() {
    log.debug "executing 'info'"
    executeCommand("INFO")
}

def stb() {
    log.debug "executing 'stb'"
    executeCommand("POWER_STB")
}

def forward() {
    log.debug "executing 'forward'"
    executeCommand("FASTFORWARD")
}

def backward() {
    log.debug "executing 'backward'"
    executeCommand("FASTREVERSE")
}

def pause() {
    log.debug "executing 'pause'"
    executeCommand("PAUSE")
}

def cc() {
    log.debug "executing 'cc'"
    executeCommand("CC")
}

def play() {
    log.debug "executing 'play'"
    executeCommand("PLAY")
}

def stop() {
    log.debug "executing 'stop'"
    executeCommand("STOP")
}

def hub() {
    log.debug "executing 'hub'"
    executeCommand("SMART_HUB")
}

def guide() {
    log.debug "executing 'guide'"
    executeCommand("GUIDE")
}

def hubActionResponse(response) {
    log.debug("Executing 'hubActionResponse': '${device.deviceNetworkId}'")
    log.debug("Hub action response: '${response}'")

    def status = response.headers["power-status"] ?: ""
    if (status != "") {
        log.debug("switch status: '${status}'")
        sendEvent(name: "switch", value: status, isStateChange: true)
    }
}

private executeCommand(command, repeat = 0){
    def irCommand = irCommandPrefix + command
    if (command == "on" || command == "off" || command == "status") {
        irCommand = command
    }

    log.debug("Executing command: '${irCommand}'")
    log.debug("Device: ${device.deviceNetworkId}; Remote: ${remoteName}; Gateway: ${gatewayIP}:${gatewayPort}")

    def headers = [:]
    headers.put("host", "${gatewayIP}:${gatewayPort}")
    headers.put("ir-command", irCommand)
    headers.put("remote-name", remoteName)
    if (repeat > 1) {
        headers.put("repeat", repeat)
    }
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
    
    log.debug("Done executing command: '${irCommand}'")
}
