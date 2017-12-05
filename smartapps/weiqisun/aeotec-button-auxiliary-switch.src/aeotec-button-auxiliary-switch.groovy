/**
 *  AEOTEC Button Auxiliary Switch
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
definition(
    name: "AEOTEC Button Auxiliary Switch",
    namespace: "weiqisun",
    author: "Weiqi Sun",
    description: "Turn the AEOTEC Panic Button to a Auxiliary Switch",
    category: "My Apps",
    iconUrl: "http://cdn.device-icons.smartthings.com/Home/home30-icn.png",
    iconX2Url: "http://cdn.device-icons.smartthings.com/Home/home30-icn@2x.png",
    iconX3Url: "http://cdn.device-icons.smartthings.com/Home/home30-icn@2x.png")


preferences {
	section("AEOTEC Panic Button:") {
        input "theButton", "capability.button", multiple: false, required: true, title: "Which Button?"
    }

	section("Turn these lights on when button is pushed:") {
        input "theLight", "capability.switch", multiple: false, required: true, title: "Which Lights?"
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
	subscribe(theButton, "button.pushed", buttonPushedHandler)
}

def buttonPushedHandler(evt) {
	flip()
}

def flip() {
	if (theLight.switchState.value == "on") theLight.off()
    else theLight.on()
}