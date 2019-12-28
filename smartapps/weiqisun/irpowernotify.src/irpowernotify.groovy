/**
 *  IrPowerNotify
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
 *
 */
definition(
    name: "IrPowerNotify",
    namespace: "weiqisun",
    author: "Weiqi Sun",
    description: "Notify device to poll when IR power signal is detected",
    category: "My Apps",
    iconUrl: "http://cdn.device-icons.smartthings.com/Health%20&%20Wellness/health9-icn.png",
    iconX2Url: "http://cdn.device-icons.smartthings.com/Health%20&%20Wellness/health9-icn@2x.png",
    iconX3Url: "http://cdn.device-icons.smartthings.com/Health%20&%20Wellness/health9-icn@2x.png",
    oauth: [displayName: "FamilySync", displayLink: "http://localhost:4567"])


preferences {
	section("Allow IR power signal to poll this device...") {
        input "devices", "capability.polling", multiple: false, required: true
	}
}

mappings {
    path("/update") {
        action: [
            GET: "pollDevice"
        ]
    }
    path("/update/:command") {
        action: [
            GET: "updateDevice"
        ]
    }
}

def pollDevice() {
    log.debug "Polling device: ${devices.displayName}"
    devices.poll()
    return [name: devices.displayName, value: devices.currentValue("switch")]
}

def updateDevice() {
    log.debug "Updating device: ${devices.displayName}"
    def command = params.command
    log.debug "request command: ${command}"
    switch(command) {
        case "on":
            devices.seton()
            break
        case "off":
            devices.setoff()
            break
        default:
            httpError(400, "${command} is not a valid command for ${devices.displayName}")
    }
    return [name: devices.displayName, value: devices.currentValue("switch")]
}
def installed() {
	log.debug "Installed with settings: ${settings}"

	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

	unsubscribe()
	initialize()
}

def initialize() {
    log.debug "Initializing IrPowerNotify"
}