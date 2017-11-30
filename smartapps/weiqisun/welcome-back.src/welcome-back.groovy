/**
 *  Welcome Back
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
    name: "Welcome Back",
    namespace: "weiqisun",
    author: "Weiqi Sun",
    description: "Turn on lights when users come back",
    category: "My Apps",
    iconUrl: "https://raw.githubusercontent.com/weiqisun/Smartthings/master/smartapps/weiqisun/welcome-back.src/welcomeback-icon.png",
    iconX2Url: "https://raw.githubusercontent.com/weiqisun/Smartthings/master/smartapps/weiqisun/welcome-back.src/welcomeback-icon@2x.png",
    iconX3Url: "https://raw.githubusercontent.com/weiqisun/Smartthings/master/smartapps/weiqisun/welcome-back.src/welcomeback-icon@2x.png")


preferences {
	section("Turn on lights when door is open:") {
		input "theDoor", "capability.contactSensor", required: true, title: "Which Door?"
	}
    
    section("Turn these lights on:") {
    	input "theLights", "capability.switch", multiple: true, required: true, title: "Which Lights?"
	}
    
    section("Authorized people:") {
    	input "persons", "capability.presenceSensor", multiple:true, required: false, title: "Who?"
    }
    
    section("Send notification to:") {
    	input "phoneNum", "phone", required: false, title: "Phone Number"
    }
    
    section("Delay time[s] for lights on:") {
    	input "waitSec", "number", required: false, title: "Time in Second"
    }
}

def installed() {
	initialize()
}

def updated() {
	unsubscribe()
	initialize()
}

def initialize() {
	subscribe(theDoor, "contact.open", doorOpenHandler)
}

def doorOpenHandler(evt) {
	if (location.currentMode == "Away") {
    	if (persons && !isAnyoneBack(persons))
        	sendAlert()
        else
        	activateHome()
    }
}

def isAnyoneBack(persons) {
	for (person in persons) {
		if (person.presenceState.value == "present")
        	return true
	}
    return false
}

def sendAlert() {
	if (phoneNum) {
    	def timeNow = new Date()
    	def message = "The ${theDoor.displayName} is open at time ${timeNow}!"
    	sendSms(phoneNum, message)
	}
}

def activateHome() {
	location.setMode("Home")
    def sun = getSunriseAndSunset(zipCode: location.zipCode)
    def isDayTime = timeOfDayIsBetween(sun.sunrise, sun.sunset, new Date(), location.timeZone)
    if (!isDayTime) 
    	runIn(waiting(waitSec), turnOn)
}

def waiting(waitSec) {
	def maxWaitingTime = 10
	if (!waitSec || waitSec < 0) 
    	return 0
    else if (waitSec > maxWaitingTime)
    	return maxWaitingTime
    else
    	return waitSec
}

def turnOn() {
	theLights.on()
}