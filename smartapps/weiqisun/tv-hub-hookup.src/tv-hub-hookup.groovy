/**
 *  TV Hub Hookup
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
definition(
    name: "TV Hub Hookup",
    namespace: "weiqisun",
    author: "Weiqi Sun",
    description: "Hookup HDMI devices to TV",
    category: "My Apps",
    iconUrl: "http://cdn.device-icons.smartthings.com/Office/office11-icn.png",
    iconX2Url: "http://cdn.device-icons.smartthings.com/Office/office11-icn@2x.png",
    iconX3Url: "http://cdn.device-icons.smartthings.com/Office/office11-icn@2x.png")


preferences {
	section("TV") {
		input "theTV", "capability.switchLevel", multiple: false, required: true, title: "The TV Hub"
	}
    
    section("RetroPie") {
        input "theRetro", "capability.switch", multiple: false, required: true, title: "The RetroPie"
    }
    
    section("PS4") {
        input "thePS4", "capability.switch", multiple: false, required: true, title: "The PS4"
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
	subscribe(theRetro, "switch.on", RetroOnHandler)
    subscribe(theRetro, "switch.off", RetroOffHandler)
    subscribe(thePS4, "switch.on", PS4OnHandler)
    subscribe(thePS4, "switch.off", PS4OffHandler)
}

def RetroOnHandler(evt) {
	if (theTV.switchState.value == "off") theTV.on()
    theTV.updateState(1)
}

def RetroOffHandler(evt) {
	if (theTV.switchState.value == "on") {
    	theTV.restoreState()
    }
}

def PS4OnHandler(evt) {
	if (theTV.switchState.value == "off") theTV.on()
	theTV.updateState(2)
}

def PS4OffHandler(evt) {
	if (theTV.switchState.value == "on") {
    	theTV.restoreState()
    }
}