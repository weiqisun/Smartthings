/**
 *  Device Poller
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
    name: "Device Poller",
    namespace: "weiqisun",
    author: "Weiqi Sun",
    description: "Poll device periodically",
    category: "My Apps",
    iconUrl: "http://cdn.device-icons.smartthings.com/secondary/smartapps.png",
    iconX2Url: "http://cdn.device-icons.smartthings.com/secondary/smartapps@2x.png",
    iconX3Url: "http://cdn.device-icons.smartthings.com/secondary/smartapps@2x.png")


preferences {
    section("Poll these devices:") {
        input "devices", "capability.polling", multiple: true, required: true, title: "Devices"
	}
    
    section("Period (in min) for polling:") {
        input "period", "enum", required: true, title: "Period", options: ["1 MIN", "5 MIN", "10 MIN", "15 MIN", "30 MIN", "1 HR", "3 HR"]
    }
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
    log.debug "Polling period: ${period}"
    unschedule()
    switch(period) {
        case "1 MIN":
            log.debug "Will poll every 1 min"
            runEvery1Minute(pollHandler)
            break
        case "5 MIN":
            log.debug "Will poll every 5 min"
            runEvery5Minutes(pollHandler)
            break
        case "10 MIN":
            log.debug "Will poll every 10 min"
            runEvery10Minutes(pollHandler)
            break
        case "15 MIN":
            log.debug "Will poll every 15 min"
            runEvery15Minutes(pollHandler)
            break
        case "30 MIN":
            log.debug "Will poll every 30 min"
            runEvery30Minutes(pollHandler)
            break
        case "1 HR":
            log.debug "Will poll every 1 hr"
            runEvery1Hour(pollHandler)
            break
        case "3 HR":
            log.debug "Will poll every 3 hr"
            runEvery3Hours(pollHandler)
            break
        default:
            log.debug "Unkown period ${period}"
    }
}

def pollHandler() {
    log.debug "pollHandler called at ${new Date()}"
	devices.poll()
}