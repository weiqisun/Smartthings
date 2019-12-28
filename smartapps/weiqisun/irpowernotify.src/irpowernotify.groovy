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
	section("Allow IR power signal to poll these device...") {
        input "devices", "capability.polling", multiple: false, required: true
	}
}

mappings {
    path("/update") {
        action: [
            GET: "pollDevice"
        ]
    }
}

def pollDevice() {
	log.debug "In pollDevice()"
    log.debug "polling device: ${devices.displayName}"
    def resp = []
    log.debug "Status before polling: ${devices.currentValue("switch")}"
    devices.poll()
    log.debug "Status after polling: ${devices.currentValue("switch")}"
    resp << [name: devices.displayName, value: devices.currentValue("switch")]
    return resp
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
	// TODO: subscribe to attributes, devices, locations, etc.
}

// TODO: implement event handlers